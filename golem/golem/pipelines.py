# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
from arango import ArangoClient
from .items import WeekItem, MatchItemVST, PlayerPerformanceItemVST
from .checkpipeline import check_pipeline


class ArangoPipeline(object):
    def __init__(self):
        self.player_insert_count = 0

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
        self.playedInVST = self.seasonGraphVST.edge_collection(
            "playedInVST")
        self.rosterOfVST = self.seasonGraphVST.edge_collection("rosterOfVST")

        self.players = self.db.collection("players")
        self.performed_in_week = self.seasonGraphVST.edge_collection(
            "performedInWeek")

    def insert_if_not_present(self, collection, document, silent=True):
        if not collection.has(document):
            return collection.insert(document, silent=silent)

    def get_edge_id(self, edge_collection, from_id, to_id):
        cursor = self.db.aql.execute('FOR edge IN @@edge_collection FILTER edge._from == @from_id AND edge._to == @to_id LIMIT 1 RETURN edge',
                                     bind_vars={'@edge_collection': edge_collection,
                                                'from_id': from_id, 'to_id': to_id},
                                     count=True)
        assert cursor.count() < 2, "Two edges between " + from_id + " and " + to_id
        return None if cursor.empty() else cursor.next()

    def insert_player_if_not_present(self, item):
        playerDoc = {'_key': item['player_key'],
                     'name': item['player_name']}
        inserted = self.insert_if_not_present(
            self.players, playerDoc, silent=True)
        if inserted:
            print("INSERTED ONE")
            self.player_insert_count += 1
        else:
            print("FUCKER ALREADY EXISTS")
        return inserted

    def get_week_key(self, season, week):
        return str(season) + '.' + str(week)

    def get_roster_key(self, week_key, roster):
        return week_key + '.' + str(roster['team_key'])


class WeekPipeline(ArangoPipeline):
    itemclass = WeekItem

    @check_pipeline
    def process_item(self, item, spider):

        # insert season
        self.insert_if_not_present(self.seasons, {'_key': item['season']})

        # insert week
        week_key = self.get_week_key(item["season"], item["week"])
        self.insert_if_not_present(
            self.weeks, {'_key': week_key, 'number': item['week']})

        edge = {'_from': 'seasons/' +
                str(item['season']), '_to': 'weeks/' + week_key}
        if self.get_edge_id('weeksInSeason', edge['_from'], edge['_to']) is not None:
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
        week_key = self.get_week_key(item["season"], item["week"])
        week_id = 'weeks/' + week_key
        match_id = self.get_match_id(
            week_key, item['roster1']['team_key'], item['roster2']['team_key'])
        if match_id is None:
            spider.logger.info("Inserting match")
            # insert match
            match_id = self.matchesVST.insert({}, silent=False)['_id']

        if self.get_edge_id(self.matchesInWeekVST.name, week_id, match_id) is None:
            # insert edge between match and week
            self.matchesInWeekVST.link('weeks/' + week_key, match_id)

        self.update_roster(item['roster1'], week_key, match_id)
        self.update_roster(item['roster2'], week_key, match_id)

        return item

    def update_roster(self, roster, week_key, match_id):
        roster_key = self.get_roster_key(week_key, roster)
        roster_id = 'rostersVST/' + roster_key
        if not self.rostersVST.has(roster_key):
            # insert roster
            self.rostersVST.insert({'_key': roster_key})

            # link roster and team
            team_id = 'teamsVST/' + roster['team_key']
            if self.get_edge_id(self.rosterOfVST.name, team_id, roster_id) is None:
                self.rosterOfVST.link(team_id, roster_id)

            # link roster and match
            played_in_id = self.get_edge_id(
                self.rosterPlayedInVST.name, roster_id, match_id)
            played_in_doc = {'points': roster['points']}
            if played_in_id is None:
                self.rosterPlayedInVST.link(roster_id, match_id, played_in_doc)
            else:
                played_in_doc['_id'] = played_in_id
                self.rosterPlayedInVST.replace(played_in_doc)

            # remove all played in edges
            self.delete_all_played_in_edges(roster_id)

            # link the players to the roster

            self.insert_and_link_player_of_roster(
                roster_id, 'qb', roster['qb'])
            self.insert_and_link_player_of_roster(
                roster_id, 'rb1', roster['rb1'])
            self.insert_and_link_player_of_roster(
                roster_id, 'rb2', roster['rb2'])
            self.insert_and_link_player_of_roster(
                roster_id, 'wr1', roster['wr1'])
            self.insert_and_link_player_of_roster(
                roster_id, 'wr2', roster['wr2'])
            self.insert_and_link_player_of_roster(
                roster_id, 'te', roster['te'])
            self.insert_and_link_player_of_roster(
                roster_id, 'flex', roster['flex'])
            self.insert_and_link_player_of_roster(roster_id, 'k', roster['k'])
            self.insert_and_link_player_of_roster(roster_id, 'd', roster['d'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn1', roster['bn1'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn2', roster['bn2'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn3', roster['bn3'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn4', roster['bn4'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn5', roster['bn5'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn6', roster['bn6'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn7', roster['bn7'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn8', roster['bn8'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn9', roster['bn9'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn10', roster['bn10'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn11', roster['bn11'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn12', roster['bn12'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn13', roster['bn13'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn14', roster['bn14'])
            self.insert_and_link_player_of_roster(
                roster_id, 'bn15', roster['bn15'])

    def insert_and_link_player_of_roster(self, roster_id, roster_spot_name, roster_spot):
        if roster_spot is not None:
            # insert the player
            self.insert_player_if_not_present(roster_spot['player'])
            player_id = 'players/' + roster_spot['player']['player_key']

            # the edge should not exist, and if it does, we delete it
            edge_id = self.get_edge_id(self.playedInVST.name, player_id, roster_id)
            if self.get_edge_id(self.playedInVST, player_id, roster_id) is not None:
                self.playedInVST.delete(edge_id)

            # link player and roster
            roster_spot['spot_name'] = roster_spot_name
            self.playedInVST.link(player_id, roster_id, dict(roster_spot))

    def delete_all_played_in_edges(self, roster_id):
        query = """FOR edge IN playedInVST
            FILTER edge._to == @rosterId
            REMOVE edge IN playedInVST"""

        self.db.aql.execute(query,
                            bind_vars={
                                'rosterId': roster_id
                            }, count=False)

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


class PlayerPerformanceVSTPipeline(ArangoPipeline):
    itemclass = PlayerPerformanceItemVST

    def __init__(self):
        super(PlayerPerformanceVSTPipeline, self).__init__()
        self.player_performance_insert_count = 0
        self.player_performance_update_count = 0

    def close_spider(self, spider):
        spider.logger.info(
            "Inserted " + str(self.player_insert_count) + " players.")
        spider.logger.info(
            "Inserted " + str(self.player_performance_insert_count) + " player performances.")
        spider.logger.info(
            "Updated " + str(self.player_performance_update_count) + " player performances.")

    @check_pipeline
    def process_item(self, item, spider):

        if self.insert_player_if_not_present(item['player']):
            self.player_insert_count += 1
        week_id = 'weeks/' + \
            self.get_week_key(item['week']['season'], item['week']['week'])
        player_id = 'players/' + item['player']['player_key']
        itemDict = dict(item)
        data = {i: itemDict[i]
                for i in itemDict if (i != 'week' and i != 'player')}
        data['_key'] = str(itemDict['week']['season']) + '.' + \
            str(itemDict['week']['week']) + '.' + \
            itemDict['player']['player_key']
        if self.get_edge_id('performedInWeek', player_id, week_id) is not None:
            self.performed_in_week.link(player_id, week_id, data=data)
            self.player_performance_insert_count += 1
        else:
            self.performed_in_week.update(data, silent=True)
            self.player_performance_update_count += 1

        return item
