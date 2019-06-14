from .golemshembase import GolemShemBase
import scrapy
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game


class GolemMatchShem(GolemShemBase):
    """This shem scrapes the matches. Creates or updates seasons, weeks, matches and rosters."""
    name = "golem_matches_shem"

    # First go to the history page to get all seasons
    def start_scraping(self, session_cookies):
        return self.itarte_over_all_weeks(session_cookies, self.parse_games_of_week)


    #  Call all the gamecenters for the week
    def parse_games_of_week(self, response):
        for suffix in response.css('.matchupLink > a::attr(href)').getall():
            GAME_CENTER_URL = "https://fantasy.nfl.com" + \
            suffix + \
            '&trackType=fbs'
            yield scrapy.Request(url=GAME_CENTER_URL,
                             meta={'week': response.request.meta["week"]},
                             callback=parse_game)
