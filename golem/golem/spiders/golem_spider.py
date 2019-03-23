import json

import scrapy
from scrapy.shell import inspect_response
from scrapy.utils.response import open_in_browser


class GolemSpyder(scrapy.Spider):
    name = "golem"

    USERNAME = "vstrophy"
    handle_httpstatus_list = [400]
    # First we need to login to GIGYA (wathever that is)

    def start_requests(self):
        GIGYA_URL = "https://accounts.us1.gigya.com/accounts.login"

        PASSWORD = "g04l3m080815"
        NFL_API_KEY = "3_JuHa5qkVIfY_KsNk1TuudSQT5Nif4axD-QXug1OgBmAgVieKA56umYvWPnoursaC"

        yield scrapy.FormRequest(url=GIGYA_URL,
                                 formdata={
                                     'loginID': self.USERNAME, 'password': PASSWORD, 'ApiKey': NFL_API_KEY},
                                 callback=self.after_gigya_login)

    # After the gigya login we need to call the reroute address to login to nfl.com
    def after_gigya_login(self, response):
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

    # The nfl login provides a bearer token. We need ot send this token
    # to another url to get all the cookies we need to start collecting data
    def after_nfl_login(self, response):
        NFL_COOKIE_ADDRESS = "https://api.nfl.com/v1/cookie"

        jsonresponse = json.loads(response.body_as_unicode())
        # We need to send the bearer token received in the header to get the session cookies
        yield scrapy.Request(url=NFL_COOKIE_ADDRESS,
                             headers={"Authorization": "Bearer " +
                                      jsonresponse["access_token"]},
                             callback=self.start_collecting)

    # The starting point after all loging in is done and whe have the session cookies
    def start_collecting(self, response):
        self.logger.info("Successfully loged in! ")
        self.logger.info(response.headers)