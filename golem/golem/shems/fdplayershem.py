import scrapy
import json
from arango import ArangoClient
from ..playerstatsparser import get_player_performance


class FDPlayerBShem(scrapy.Spider):
    """This shem scrapes the player performances. Creates or updates players and player performances."""

    def __init__(self, complete=False, **kwargs):
        self.complete = complete

        super().__init__(**kwargs)  # python3

    name = "fd_player_shem"

    position_codes = {'OFFENSE': 1, 'QB': 2,
                      'RB': 3, 'WR': 4, 'TE': 5, 'K': 6, 'DEF': 7}

    FANTASYDATA_URL = "https://fantasydata.com/FantasyStatsNFL/FantasyStats_Read"

    FORM_DATA_TEMPLATE = "sort=FantasyPoints-desc&pageSize=99999&group=&filter=&filters.position={position}&filters.team=&filters.season={season}&filters.seasontype=1&filters.scope=2&filters.subscope=1&filters.redzonescope=&filters.scoringsystem=&filters.leaguetype=&filters.searchtext=&filters.week={week}&filters.startweek={week}&filters.endweek={week}&filters.minimumsnaps=&filters.teamaspect=&filters.stattype=&filters.exportType=&filters.desktop=&filters.dfsoperator=&filters.dfsslateid=&filters.dfsslategameid=&filters.dfsrosterslot=&filters.page=&filters.showfavs=&filters.posgroup=&filters.aggregatescope=1&filters.rangescope=&filters.range=1"

    def start_requests(self):
        client = ArangoClient(protocol='http', host='localhost', port=8529)

        self.db = client.db('vs-trophy', username=self.settings.get("ARANGO_USER"),
                        password=self.settings.get("ARANGO_PWD"))
        if self.complete:
            self.logger.info("Starting complete player performance crawl")
            return self.iterate_over_all_weeks()

        else:
            self.logger.info("Starting quick player performance crawl")
            returnValue = self.execute_current_week()
            self.logger.info(" RETURN VALUE " + str(returnValue))
            return returnValue

    def iterate_over_all_weeks(self):
        
        query = """
        FOR season IN seasons
            FOR week IN 1..1 OUTBOUND season weeksInSeason
            RETURN {"season": season._key, "week": TO_STRING(week.number)}"""
        weeks = self.db.aql.execute(query)
        try:
            for week in weeks:
                yield from self.scrap_playersats_week(week)
        finally:
            weeks.close(True)

    def execute_current_week(self):
        # First we need to get the current week
        query = """
        FOR season IN seasons
            SORT season._key DESC
            LIMIT 1
            FOR week IN 1..1 OUTBOUND season weeksInSeason
            SORT week.number DESC
            LIMIT 1
            RETURN {"season": season._key, "week": TO_STRING(week.number)}"""
        week = self.db.aql.execute(query).next()
        return self.scrap_playersats_week(week)

    def scrap_playersats_week(self, weekObject):
        # get offensive performances
        yield scrapy.Request(url=self.FANTASYDATA_URL,
                             headers={
                                 'Content-Type': "application/x-www-form-urlencoded"},
                             method='POST',
                             body=self.FORM_DATA_TEMPLATE.format(
                                 position=self.position_codes['OFFENSE'], **weekObject),
                                 meta = {**weekObject, 'pos':'OFFENSE'},
                             callback=self.parse_playerstats_week)

        # get defensive performances
        yield scrapy.Request(url=self.FANTASYDATA_URL,
                             headers={
                                 'Content-Type': "application/x-www-form-urlencoded"},
                             method='POST',
                             body=self.FORM_DATA_TEMPLATE.format(
                                 position=self.position_codes['DEF'], **weekObject),
                                 meta = {**weekObject, 'pos':'DEF'},
                             callback=self.parse_playerstats_week)

        # get kicker performances
        yield scrapy.Request(url=self.FANTASYDATA_URL,
                             headers={
                                 'Content-Type': "application/x-www-form-urlencoded"},
                             method='POST',
                             body=self.FORM_DATA_TEMPLATE.format(
                                 position=self.position_codes['K'], **weekObject),
                             meta = {**weekObject, 'pos':'K'},
                             callback=self.parse_playerstats_week)

    def parse_playerstats_week(self, response):
        stats_list = json.loads(response.body)['Data']
        for stats in stats_list:
            yield get_player_performance(stats)
