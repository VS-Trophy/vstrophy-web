from .golemshembase import GolemShemBase
import scrapy
from scrapy.shell import inspect_response
from ..items import WeekItem, MatchItemVST
from scrapy.utils.response import open_in_browser
from ..gameparser import parse_game


class GolemMatchShem(GolemShemBase):
    """This shem scrapes the matches. Creates or updates seasons, weeks, matches and rosters."""
    name = "golem_match_shem"

    # 1. First go to the history page to get all seasons
    def start_scraping(self, session_cookies):
        self.logger.info("Starting to scrape by calling the history page")
        HISTORY_URL = "https://fantasy.nfl.com/league/1268875/history/"
        return scrapy.Request(url=HISTORY_URL, cookies=session_cookies,
                              callback=self.parse_seasons)

    # 2. Parse all the seasons and call the history schedule for each season to get the weeks
    def parse_seasons(self, response):
        self.logger.info("Parsing seasons")
        HISTORY_SCHEDULE_URL = "https://fantasy.nfl.com/league/1268875/history/{}/schedule"
        for season in response.css(".st-menu > a::text").getall():
            season = season[:4]
            url = HISTORY_SCHEDULE_URL.format(season)
            yield scrapy.Request(url=url,
                                 meta={'season': season},
                                 callback=self.parse_weeks)
    # 3. Parse all the weeks and call the schedule of the week

    def parse_weeks(self, response):
        season = response.request.meta['season']
        # get the last week
        lastWeek = int(response.css(
           ".scheduleWeekNav > .last > a > span.title > span::text").get())
       
        for week in range(1, lastWeek + 1):
            weekItem = WeekItem(season=season, week=week)
            yield weekItem
            WEEK_SECHEDULE_URL = "https://fantasy.nfl.com/league/1268875/history/"+season+"/schedule?scheduleDetail=" + \
                str(week)
            yield scrapy.Request(url=WEEK_SECHEDULE_URL,
                                 meta={'week': weekItem},
                                 callback=self.parse_games_of_week
                                 )

    # 4. Call all the gamecenters for the week
    def parse_games_of_week(self, response):
        for suffix in response.css('.matchupLink > a::attr(href)').getall():
            GAME_CENTER_URL = "https://fantasy.nfl.com" + \
            suffix + \
            '&trackType=fbs'
            yield scrapy.Request(url=GAME_CENTER_URL,
                             meta={'week': response.request.meta["week"]},
                             callback=parse_game)
