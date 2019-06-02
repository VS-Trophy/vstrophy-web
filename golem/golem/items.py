# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy

class WeekItem(scrapy.Item):
    season = scrapy.Field()
    week = scrapy.Field()
    pass     

class MatchItemVST(scrapy.Item):
    season = scrapy.Field()
    week = scrapy.Field()
    roster1 = scrapy.Field()
    roster2 = scrapy.Field()

class RosterItemVST(scrapy.Item):
    team_key = scrapy.Field()
    points = scrapy.Field()
    qb = scrapy.Field()
    rb1 = scrapy.Field()
    rb2 = scrapy.Field()
    wr1 = scrapy.Field()
    wr2 = scrapy.Field()
    te = scrapy.Field()
    flex = scrapy.Field()
    k = scrapy.Field()
    d = scrapy.Field()
    bn1 = scrapy.Field()
    bn2 = scrapy.Field()
    bn3 = scrapy.Field()
    bn4 = scrapy.Field()
    bn5 = scrapy.Field()
    bn6 = scrapy.Field()
    bn7 = scrapy.Field()
    bn8 = scrapy.Field()
    bn9 = scrapy.Field()
    bn10 = scrapy.Field()
    bn11 = scrapy.Field()
    bn12 = scrapy.Field()
    bn13 = scrapy.Field()
    bn14 = scrapy.Field()
    bn15 = scrapy.Field()

class PlayerItemVST(scrapy.Item):
    player_key = scrapy.Field()
    player_name = scrapy.Field()

class RosterSpotItemVST(scrapy.Item):
    player = scrapy.Field()
    player_nfl_team = scrapy.Field()
    player_nfl_position = scrapy.Field()
    player_nfl_opponent = scrapy.Field()
    player_nfl_home = scrapy.Field()
    player_nfl_result = scrapy.Field()
    player_nfl_score = scrapy.Field()

class PlayerPerformanceItemVST(scrapy.Item):
    player = scrapy.Field()
    points = scrapy.Field()
    week = scrapy.Field()

class OffensivePlayerPerformanceItemVST(PlayerPerformanceItemVST):
    pass_yds = scrapy.Field()
    pass_tds = scrapy.Field()
    pass_ints = scrapy.Field()
    rush_yds = scrapy.Field()
    rush_tds = scrapy.Field()
    rec_yds = scrapy.Field()
    rec_tds = scrapy.Field()
    fum_tds = scrapy.Field()
    two_pts = scrapy.Field()
    fum_lost = scrapy.Field()
class KickPlayerPerformanceItemVST(PlayerPerformanceItemVST):
    pats = scrapy.Field()
    twenty_minus_made = scrapy.Field()
    twenty_thirty_made = scrapy.Field()
    thirty_forthy_made = scrapy.Field()
    forthy_fifty_made = scrapy.Field()
    fifty_plus_made = scrapy.Field()
class DefensivePlayerPerformanceItemVST(PlayerPerformanceItemVST):
    sacks = scrapy.Field()
    ints = scrapy.Field()
    fum_rec = scrapy.Field()
    safeties = scrapy.Field()
    def_tds = scrapy.Field()
    ret_tds = scrapy.Field()
    allowed_pts = scrapy.Field()