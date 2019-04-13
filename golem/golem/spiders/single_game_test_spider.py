from .golemspiderbase import GolemSpiderBase
import scrapy
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser


class GolemCompleteSpider(GolemSpiderBase):
    name = "single_game_golem"

    # 1. First go to the history page to get all seasons
    def start_scraping(self, session_cookies):
        week = WeekItem(season=2018, week=16)
        GAME_CENTER_URL = "https://fantasy.nfl.com/league/1268875/history/2018/teamgamecenter?teamId=4&week=16&trackType=fbs" 
        yield scrapy.Request(url=GAME_CENTER_URL,
                             meta={'week': week},
                             callback=self.parse_game)