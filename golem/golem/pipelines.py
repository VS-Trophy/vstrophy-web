# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from pyArango.connection import Connection
from .items import *
from .checkpipeline import *


class ArangoPipeline(object):
    conn = None

    def open_spider(self, spider):
        self.conn = Connection(username="root", password="")

    def close_spider(self, spider):
        self.conn.disconnectSession()


class SeasonPipeline(ArangoPipeline):
    itemclass = SeasonItem

    @check_pipeline
    def process_item(self, item, spider):
        spider.logger.info("Season Pipeline ")
        return item


class WeekPipeline(ArangoPipeline):
    itemclass = WeekItem

    @check_pipeline
    def process_item(self, item, spider):
        spider.logger.info("Week Pipeline ")
        return item
