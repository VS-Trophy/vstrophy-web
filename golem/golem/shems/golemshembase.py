import json

import scrapy
from ..items import WeekItem, MatchItemVST
from scrapy.shell import inspect_response
from scrapy.utils.response import open_in_browser


class GolemShemBase(scrapy.Spider):

    USERNAME = "vstrophy"

    '''
    ================== LOGIN PROCEDURE ==================
    The following methods authenticate us against nfl.com
    =====================================================
    '''
    # 1. First we need to login to GIGYA (wathever that is)

    def start_requests(self):
        GIGYA_URL = "https://accounts.us1.gigya.com/accounts.login"

        PASSWORD = "g04l3m080815"
        NFL_API_KEY = "3_JuHa5qkVIfY_KsNk1TuudSQT5Nif4axD-QXug1OgBmAgVieKA56umYvWPnoursaC"

        yield scrapy.FormRequest(url=GIGYA_URL,
                                 formdata={
                                     'loginID': self.USERNAME, 'password': PASSWORD, 'ApiKey': NFL_API_KEY},
                                 callback=self.after_gigya_login)

    # 2. After the gigya login we need to call the reroute address to login to nfl.com
    def after_gigya_login(self, response):
        self.logger.info("Gigya login successful. Obtaining nfl access-token.")
        NFL_REROUTE_URL = "https://api.nfl.com/v1/reroute"
        jsonresponse = json.loads(response.body_as_unicode())
        # We send the "gigya_signature" to nfl.com to receive the bearer token
        yield scrapy.FormRequest(url=NFL_REROUTE_URL,
                                 headers={"x-domain-id": "100"},
                                 formdata={'username': self.USERNAME,
                                           'grant_type': "gigya_signature",
                                           'gigya_UID': jsonresponse["UID"],
                                           'gigya_signature': jsonresponse["UIDSignature"],
                                           'gigya_signature_timestamp': jsonresponse["signatureTimestamp"]},
                                 callback=self.after_nfl_login)

    # 3. The nfl login provides a bearer token. We need ot send this token
    # to another url to get all the cookies we need to start collecting data
    def after_nfl_login(self, response):
        self.logger.info("Obtained access-token. Getting session cookies")
        NFL_COOKIE_ADDRESS = "https://api.nfl.com/v1/cookie"

        jsonresponse = json.loads(response.body_as_unicode())
        # We need to send the bearer token received in the header to get the session cookies
        yield scrapy.Request(url=NFL_COOKIE_ADDRESS,
                             headers={"Authorization": "Bearer " +
                                      jsonresponse["access_token"]},
                             callback=self.after_cookies)

    # 4. We got the cookies! Time to parse them and put them in the cookies
    def after_cookies(self, response):
        self.logger.info(
            "Got session cookies. Login completed successfully. Let's get scraping!")
        
        jsonresponse = json.loads(response.body_as_unicode())
        session_cookies = jsonresponse["cookies"]
        self.logger.debug("Cookies : " + str(session_cookies))
        # The starting point after all logging in is done and whe have the session cookies
        yield self.start_scraping(session_cookies)

    # This method will be implemented from the child classes
    def start_scraping(self, session_cookies):
        raise NotImplementedError("This method should be overwritten")

    def itarte_over_all_weeks(self, session_cookies, weekly_callback):
        self.logger.info("iterate over all weeks")
        HISTORY_URL = "https://fantasy.nfl.com/league/1268875/history/"
        return scrapy.Request(url=HISTORY_URL, cookies=session_cookies,
                            meta={'weekly_callback' : weekly_callback},
                              callback=self.parse_seasons)

    def parse_seasons(self, response):
        self.logger.info("parse seasons")
        HISTORY_SCHEDULE_URL = "https://fantasy.nfl.com/league/1268875/history/{}/schedule"
        for season in response.css(".st-menu > a::text").getall():
            season = season[:4]
            url = HISTORY_SCHEDULE_URL.format(season)
            response.request.meta['season'] = season
            yield scrapy.Request(url=url,
                                    meta=response.request.meta,
                                    callback=self.parse_weeks)

    def parse_weeks(self, response):
        self.logger.info("parse weeks")
        season = response.request.meta['season']
        weekly_callback = response.request.meta['weekly_callback']
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
                                 callback=weekly_callback
                                 )    
