# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from arango import ArangoClient
from .items import *
from .checkpipeline import *


class ArangoPipeline(object):

    def open_spider(self, spider):
        client = ArangoClient(protocol='http', host='localhost', port=8529)
        # Select the datbase
        self.db = client.db('vs-trophy', username='root',
                            password='')
        # Select the collections
        self.weeks = self.db.collection("weeks")
        self.seasons = self.db.collection("seasons")
        self.weeks_in_season = self.db.collection("weeksInSeason")

    def insert_if_not_present(self, collection, document, silent=True):
        if not collection.has(document):
            return collection.insert(document, silent=silent)

    def does_edge_exist(self, edge_collection, from_id, to_id):
        cursor = self.db.aql.execute('FOR edge IN @@edge_collection FILTER edge._from == @from_id AND edge._to == @to_id LIMIT 1 RETURN edge',
                                     bind_vars={'@edge_collection': edge_collection,
                                                'from_id': from_id, 'to_id': to_id},
                                     count=True)
        exists = cursor.count() > 0
        cursor.close()
        return exists


class WeekPipeline(ArangoPipeline):
    itemclass = WeekItem

    @check_pipeline
    def process_item(self, item, spider):

        # insert season
        self.insert_if_not_present(self.seasons, {'_key': item['season']})

        # insert week
        week_key = str(item['season']) + '.' + str(item['week'])
        self.insert_if_not_present(
            self.weeks, {'_key': week_key, 'number': item['week']})

        edge = {'_from': 'seasons/' +
                str(item['season']), '_to': 'weeks/' + week_key}
        if not self.does_edge_exist('weeksInSeason',edge['_from'], edge['_to']):
            # insert edge between season and week
            self.weeks_in_season.insert(edge)
        return item
