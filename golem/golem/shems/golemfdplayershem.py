import scrapy
from arango import ArangoClient
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game
from ..playerstatsparser import get_offensive_performance, get_kicker_performance, get_defense_performance


class GolemFDPlayerBShem(scrapy.Spider):
    """This shem scrapes the player performances. Creates or updates players and player performances."""

    def __init__(self, complete=False, **kwargs):
        self.complete = complete

        super().__init__(**kwargs)  # python3

    name = "golem_fd_player_shem"
    FOOTBALLDB_WEEK_TEMPLATE = "https://www.footballdb.com/fantasy-football/index.html" + \
        "?pos={pos}&yr={yr}&wk={wk}"

    def start_requests(self):
        if self.complete:
            self.logger.info("Starting complete players crawl")
            return self.iterate_over_all_weeks(session_cookies, self.parse_playerstats_week)
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
        cursor = self.db.aql.execute(query)

        return parse_playerstats_week(self, cursor.next())

    def parse_playerstats_week(self, weekObject):
        season = str(weekObject['season'])
        week = str(weekObject['week'])

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
