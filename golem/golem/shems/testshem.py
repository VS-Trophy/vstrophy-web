from .golemshembase import GolemShemBase
import scrapy
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game
from ..playerstatsparser import get_offensive_performance, get_kicker_performance, get_defense_performance


class TestShem(GolemShemBase):
    name = "test_shem"
    RESEARCH_BASE_URL = "https://fantasy.nfl.com/research/players"
    BASE_URL = "https://fantasy.nfl.com"
    # 1. First go to the history page to get all seasons

    def start_scraping(self, session_cookies):
        week = WeekItem(season=2018, week=16)

        return scrapy.Request(url=self.RESEARCH_BASE_URL + "?statSeason=2012&statType=weekStats&statWeek=11&position=8",
                            cookies=session_cookies,
                             meta={'week': week},
                             callback=self.parse_defense_playerstats_week)

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

   