from arango import ArangoClient
import scrapy
import json
from datetime import datetime
from ..items import PlayerUpdateItemVST


class GolemFDIDShem(scrapy.Spider):
    """This shem sets the fantasydata.com id of each player. Players with the same name need to have their birthday set, so they can be identified"""

    name = "golem_fd_id_shem"
    FD_SEARCH_URL = "https://fantasydata.com/search"
    SEARCH_BODY_TEMPLATE = "{'searchtext' : {name}}"

    def start_requests(self):
        self.doubles_count = 0 
        self.unique_count = 0
        self.unfindable_count = 0 
        # First we need to see, which players don't have their fdid set yet
        client = ArangoClient(protocol='http', host='localhost', port=8529)

        self.db = client.db('vs-trophy', username=self.settings.get("ARANGO_USER"),
                            password=self.settings.get("ARANGO_PWD"))
        cursor = self.db.aql.execute(
            'FOR player in players FILTER player.fdid == null RETURN player')
        players = [doc for doc in cursor]
        for player in players:
            searchable_player_name = player['name'].replace('.','').lower()
            yield scrapy.Request(
                url=self.FD_SEARCH_URL,
                method='POST',
                headers = {'Content-Type':'application/json'},
                body = "{'searchtext' : '" + searchable_player_name + "'}",
                meta={'player': player},
                callback=self.parse_searchresult)

    def closed(self,reason):
        self.logger.info("Unique: " + str(self.unique_count))
        self.logger.info("Double: " + str(self.doubles_count))
        self.logger.info("Unfindable: " + str(self.unfindable_count))

    def parse_searchresult(self, response):
        self.logger.info(response.request.body)
        body = json.loads(response.body)
        number_of_results = len(body)
        if number_of_results == 1:
            return PlayerUpdateItemVST(player_key=response.request.meta['player']['_key'],player_fd_id=body[0]['PlayerID'])
            self.unique_count += 1
        elif number_of_results > 1:
           # self.logger.info(number_of_results)
            self.doubles_count += 1
        else:
            self.logger.info("UNFINDABLE ")
