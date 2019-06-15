from .golemshembase import GolemShemBase
import scrapy
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game
from ..playerstatsparser import get_offensive_performance, get_kicker_performance, get_defense_performance


class GolemPlayerShem(GolemShemBase):
    """This shem scrapes the player performances. Creates or updates players and player performances."""

    name = "golem_players_shem"
    RESEARCH_BASE_URL = "https://fantasy.nfl.com/research/players"
    BASE_URL = "https://fantasy.nfl.com"

    def start_scraping(self, session_cookies):
        if self.complete :
            self.logger.info("Starting complete players crawl")
            return self.iterate_over_all_weeks(session_cookies, self.parse_playerstats_week)
        else:
            self.logger.info("Starting quick players crawl")
            return self.execute_current_week(session_cookies, self.parse_playerstats_week)

    def parse_playerstats_week(self, response):
        season = str(response.request.meta['week']['season'])
        week = str(response.request.meta['week']['week'])
        request_url = self.RESEARCH_BASE_URL + "?statSeason=" + season + "&statType=weekStats&statWeek=" + week

        #get offensive performances
        yield scrapy.Request(url= request_url + "&position=O",
                             meta=response.request.meta,
                             callback=self.parse_offensive_playerstats_week)

        #get defensive performances
        yield scrapy.Request(url= request_url + "&position=8",
                             meta=response.request.meta,
                             callback=self.parse_defense_playerstats_week)

        #get kicker performances
        yield scrapy.Request(url= request_url + "&position=7",
                             meta=response.request.meta,
                             callback=self.parse_kicker_playerstats_week)                     

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
