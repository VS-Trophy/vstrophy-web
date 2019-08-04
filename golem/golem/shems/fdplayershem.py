import scrapy
from arango import ArangoClient
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game
from ..playerstatsparser import get_offensive_performance, get_kicker_performance, get_defense_performance


class FDPlayerBShem(scrapy.Spider):
    """This shem scrapes the player performances. Creates or updates players and player performances."""

    def __init__(self, complete=False, **kwargs):
        self.complete = complete

        super().__init__(**kwargs)  # python3

    name = "fd_player_shem"
    FANTASYDATA_URL = "https://fantasydata.com/FantasyStatsNFL/FantasyStats_Read"

    FORM_DATA_TEMPLATE = "sort=FantasyPoints-desc&pageSize=1&group=&filter=&filters.position=1&filters.team=&filters.season={season}&filters.seasontype=1&filters.scope=2&filters.subscope=1&filters.redzonescope=&filters.scoringsystem=&filters.leaguetype=&filters.searchtext=&filters.week={week}&filters.startweek={week}&filters.endweek={week}&filters.minimumsnaps=&filters.teamaspect=&filters.stattype=&filters.exportType=&filters.desktop=&filters.dfsoperator=&filters.dfsslateid=&filters.dfsslategameid=&filters.dfsrosterslot=&filters.page=&filters.showfavs=&filters.posgroup=&filters.aggregatescope=1&filters.rangescope=&filters.range=1"

    def start_requests(self):
        if self.complete:
            self.logger.info("Starting complete players crawl")
            return self.iterate_over_all_weeks(self.parse_playerstats_week)
        else:
            self.logger.info("Starting quick players crawl")
            return self.execute_current_week(self.parse_playerstats_week)

    def execute_current_week(self, callback):
        # First we need to get the current week
        client = ArangoClient(protocol='http', host='localhost', port=8529)

        self.db = client.db('vs-trophy', username=self.settings.get("ARANGO_USER"),
                            password=self.settings.get("ARANGO_PWD"))
        query = """
        FOR season IN seasons
            SORT season._key DESC
            LIMIT 1
            FOR week IN 1..1 OUTBOUND season weeksInSeason
            SORT week.number DESC
            LIMIT 1
            RETURN {"season": season._key, "week": TO_STRING(week.number)}"""
        week = self.db.aql.execute(query).next()
        return self.parse_playerstats_week(week)

    def after_post(self, response):
        self.logger.info(response.body)

    def parse_playerstats_week(self, weekObject):
        season = str(weekObject['season'])
        week = str(weekObject['week'])
        yield scrapy.Request(url=self.FANTASYDATA_URL,
                             headers={
                                 'Content-Type': "application/x-www-form-urlencoded"},
                             method='POST',
                             body=self.FORM_DATA_TEMPLATE.format(**weekObject),
                             callback=self.after_post)
        # # get offensive performances
        # yield scrapy.Request(url=request_url + "&position=O",
        #                      meta=response.request.meta,
        #                      callback=self.parse_offensive_playerstats_week)

        # # get defensive performances
        # yield scrapy.Request(url=request_url + "&position=8",
        #                      meta=response.request.meta,
        #                      callback=self.parse_defense_playerstats_week)

        # # get kicker performances
        # yield scrapy.Request(url=request_url + "&position=7",
        #                      meta=response.request.meta,
        #                      callback=self.parse_kicker_playerstats_week)

    def parse_offensive_playerstats_week(self, response):
        next_suffix = response.css("li.next > a::attr(href)").get()
        if next_suffix is not None:
            next_url = self.RESEARCH_BASE_URL + next_suffix
            yield scrapy.Request(
                url=next_url,
                meta={'week':  response.request.meta["week"]},
                callback=self.parse_offensive_playerstats_week
            )
        for player_row in response.css("table.tableType-player > tbody > tr"):
            yield get_offensive_performance(player_row, response.request.meta["week"])

    def parse_kicker_playerstats_week(self, response):
        next_suffix = response.css("li.next > a::attr(href)").get()
        if next_suffix is not None:
            next_url = self.RESEARCH_BASE_URL + next_suffix
            yield scrapy.Request(
                url=next_url,
                meta={'week':  response.request.meta["week"]},
                callback=self.parse_kicker_playerstats_week
            )
        for player_row in response.css("table.tableType-player > tbody > tr"):
            yield get_kicker_performance(player_row, response.request.meta["week"])

    def parse_defense_playerstats_week(self, response):
        next_suffix = response.css("li.next > a::attr(href)").get()
        if next_suffix is not None:
            next_url = self.RESEARCH_BASE_URL + next_suffix
            yield scrapy.Request(
                url=next_url,
                meta={'week':  response.request.meta["week"]},
                callback=self.parse_defense_playerstats_week
            )
        for player_row in response.css("table.tableType-player > tbody > tr"):
            yield get_defense_performance(player_row, response.request.meta["week"])
