from arango import ArangoClient
import scrapy
import json
import locale
from datetime import datetime
from datetime import datetime
from scrapy.spidermiddlewares.httperror import HttpError
from ..items import PlayerUpdateItemVST


class GolemFDIDShem(scrapy.Spider):
    """This shem sets the fantasydata.com id of each player. Players with the same name need to have their birthday set, so they can be identified"""

    name = "golem_fd_id_shem"
    FD_BASE_URL = "https://fantasydata.com"
    FD_SEARCH_URL = FD_BASE_URL + "/search"
    SEARCH_BODY_TEMPLATE = "{'searchtext' : {name}}"

    DATE_SEARCH_REGEX = '(?:January|February|March|April|May|June|July|August|September|October|November|December) \d{1,2}, \d{4}'

    def start_requests(self):

        # First we need to see, which players don't have their fdid set yet
        client = ArangoClient(protocol='http', host='localhost', port=8529)

        self.db = client.db('vs-trophy', username=self.settings.get("ARANGO_USER"),
                            password=self.settings.get("ARANGO_PWD"))
        cursor = self.db.aql.execute(
            'FOR player in players FILTER player.fdid == null RETURN player')
        players = [doc for doc in cursor]
        for player in players:
            searchable_player_name = player['name'].lower()
            yield self.create_search_request(player, searchable_player_name)
            if 'alternativeName' in player:
                searchable_player_name = player['alternativeName'].lower()
                yield self.create_search_request(player, searchable_player_name)

    def create_search_request(self, player, searchtext):
        return scrapy.Request(
            url=self.FD_SEARCH_URL,
            method='POST',
            headers={'Content-Type': 'application/json'},
            body='{"searchtext" : "' + searchtext + '"}',
            meta={'player': player},
            callback=self.parse_searchresult,
            errback=self.on_error)

    def parse_searchresult(self, response):
        self.logger.info(response.request.body)
        player = response.request.meta['player']
        body = json.loads(response.body)
        number_of_results = len(body)
        if number_of_results == 1:
            # we found exactly one result. This means we can set the fd_id
            self.logger.info("Update player " + player["_key"] + " " + player["name"] + " " + str(body[0]['PlayerID']))
            yield PlayerUpdateItemVST(player_key=player['_key'], player_fd_id=body[0]['PlayerID'])
        elif number_of_results > 1:
            # there seems to be more than one player with that name. 
            if not player['birthday'] is None:

                #we can try to call all the detail pages and identify the players by their birthday
                for candidate in body:
                    if candidate['League'] == 'NFL':
                        meta = {"player": player, "fdid": candidate['PlayerID']}
                        self.logger.info("Checking " + str(candidate['PlayerID']) + " for " + player["name"])
                        yield scrapy.Request(url=self.FD_BASE_URL + candidate['PlayerUrlString'],
                        meta=meta,
                        callback = self.parse_player_detail,
                        errback=self.on_error)
        else:
            # we found no one with that name. We can try some things to find them anyway
            self.logger.info("UNFINDABLE ")
            if '.' in player['name']:
                # players with dots in their names are sometimes only found without them
                cleaned_name = player['name'].replace('.', '').lower()
                self.logger.info("Trying " + cleaned_name)
                return self.create_search_request(player, cleaned_name)

    def on_error(self,error):
         if error.check(HttpError):
            # these exceptions come from HttpError spider middleware
            # you can get the non-200 response
            response = error.value.response
            self.logger.error('HttpError on %s with body %s', response.url, response.request.body)

    def parse_player_detail(self, response):
        self.logger.info("Detail page for ")
        meta = response.request.meta
        birthday = response.css('.player-info-dl').re_first(self.DATE_SEARCH_REGEX)
        date_object = datetime.strptime(birthday, '%B %d, %Y')
        normalized_bday_string = date_object.strftime('%d.%m.%Y')
        if meta['player']['birthday'] == normalized_bday_string :
            #the player has the same name and the same birthday. that will do
            #we can store the id
            return PlayerUpdateItemVST(player_key=meta['player']['_key'], player_fd_id=meta['fdid'])
        else:
            self.logger.info(meta['player']['name'] + ' Birthdays do not match: ' + normalized_bday_string + " " + meta['player']['birthday'])

