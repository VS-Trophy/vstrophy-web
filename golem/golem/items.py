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

class SeasonItem(scrapy.Item):
    season = scrapy.Field()
    pass    