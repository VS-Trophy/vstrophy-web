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
        spider.logger.info("OPENING CONNECTION")
        client = ArangoClient(protocol='http', host='localhost', port=8529)
        # Select the datbase
        self.db = client.db('vs-trophy', username='root',
                            password='')
        # Select the collections
        self.weeks = self.db.collection("weeks")
        self.seasons = self.db.collection("seasons")
        self.weeks_in_season = self.db.collection("weeksInSeason")
        self.matchesVST = self.db.collection("matchesVST")
        self.seasonGraphVST = self.db.graph("seasonVST")
        self.matchesInWeekVST = self.seasonGraphVST.edge_collection(
            "matchesInWeekVST")
        self.rostersVST = self.db.collection("rostersVST")
        self.rosterPlayedInVST = self.seasonGraphVST.edge_collection(
            "rosterPlayedInVST")
        self.rosterOfVST = self.seasonGraphVST.edge_collection("rosterOfVST")

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
        if not self.does_edge_exist('weeksInSeason', edge['_from'], edge['_to']):
            # insert edge between season and week
            self.weeks_in_season.insert(edge)
        return item


class MatchVSTPipeline(ArangoPipeline):
    itemclass = MatchItemVST

    def __init__(self):
        self.insert_count = 0
        self.update_count = 0

    @check_pipeline
    def process_item(self, item, spider):
        week_key = str(item['season']) + '.' + str(item['week'])
        match_id = self.get_match_id(week_key, item['team1'], item['team2'])
        spider.logger.info("MatchID of " + week_key + " " +
                           item['team1'] + " vs " + item['team2'] + " is " + str(match_id))
        if match_id is None:
            spider.logger.info("generating match")
            # insert match
            match_id = self.matchesVST.insert({}, silent=False)['_id']
            # insert edge between match and week
            self.matchesInWeekVST.link('weeks/' + week_key, match_id)

            # insert rosters
            roster1_id = self.rostersVST.insert(
                {'_key': week_key + '.' + item['team1']})['_id']
            roster2_id = self.rostersVST.insert(
                {'_key': week_key + '.' + item['team2']})['_id']

            # insert edges roster -> match and team -> roster
            self.rosterOfVST.link('teamsVST/' + item['team1'], roster1_id)
            self.rosterOfVST.link('teamsVST/' + item['team2'], roster2_id)

            self.rosterPlayedInVST.link(roster1_id, match_id)
            self.rosterPlayedInVST.link(roster2_id, match_id)
            self.insert_count += 1
        else:
            spider.logger.info("Updating match")
            # TODO: update roster and maybe body of rosterPlayedInVST
            self.update_count += 1
        return item

    def close_spider(self, spider):
        spider.logger.info("Inserted " + str(self.insert_count) + " matches.")
        spider.logger.info("Updated " + str(self.update_count) + " matches.")

    def get_match_id(self, week_key, team1, team2):
        query = """
        FOR match IN 1..1 OUTBOUND @weekId matchesInWeekVST
            LET teams = (FOR team IN 2..2 INBOUND match rosterOfVST, rosterPlayedInVST 
            RETURN team._key)
        FILTER teams ALL IN @teams
        LIMIT 1
        return match._id"""

        cursor = self.db.aql.execute(query,
                                     bind_vars={
                                         'weekId': 'weeks/' + week_key,
                                         'teams': [team1, team2]
                                     }, count=False)

        return None if cursor.empty() else cursor.next()
