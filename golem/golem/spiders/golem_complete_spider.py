from .golem_spider_base import GolemSpiderBase
from ..items import WeekItem

class GolemCompleteSpider(GolemSpiderBase):
    name = "golem_complete"

    def get_week_items(self, sessionCookies):
        season_range = range(2012,2017)
        for season in season_range:
            yield WeekItem(season = season, week=7)