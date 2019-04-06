from .golemspiderbase import GolemSpiderBase
import scrapy
from scrapy.shell import inspect_response
from ..items import *


class GolemCompleteSpider(GolemSpiderBase):
    name = "golem_complete"

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
    # 3. Parse all the weeks
    def parse_weeks(self, response):
        season = response.request.meta['season']
        # get the last week
        lastWeek = int(response.css(
            ".scheduleWeekNav > .last > a > span.title > span::text").get())
        for week in range(1, lastWeek + 1):
            yield WeekItem(season=season,week=week)
