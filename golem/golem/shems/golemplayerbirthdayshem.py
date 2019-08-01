from arango import ArangoClient
import scrapy
import datetime
from ..items import PlayerBirthdayItemVST


class GolemPlayerBirthdayShem(scrapy.Spider):
    """This shem scrapes the players birthdays from nfl.com. Updates players."""

    name = "golem_player_birthday_shem"
    PLAYER_PROFILE_URL_TEMPLATE = "http://www.nfl.com/player/{name}/{id}/profile"

    def start_requests(self):
        #First we need to see, which players don't have theyr birthday yet
        client = ArangoClient(protocol='http', host='localhost', port=8529)
        # Select the datbase
        self.db = client.db('vs-trophy', username=self.settings.get("ARANGO_USER"),
                            password=self.settings.get("ARANGO_PWD"))
        cursor = self.db.aql.execute('FOR player in players FILTER player.birthday == null RETURN player')
        players = [doc for doc in cursor]
        for player in players:
            if player['name'] == '' or player['name'] is None:
                self.logger.error(player)
            url = self.PLAYER_PROFILE_URL_TEMPLATE.format(
                name=player['name'].lower().replace(" ", ""),
                id=player['_key'])
            yield scrapy.Request(url=url,meta={'key': player['_key']},callback=self.parse_playercard)

    def parse_playercard(self,response):
        date_string =response.xpath("//p[contains(., 'Born')]").re_first('[0-9]{1,2}\/[0-9]{1,2}\/[0-9]{2}')
        if date_string is not None:
            date_object = datetime.datetime.strptime(date_string, '%m/%d/%y')
            return PlayerBirthdayItemVST(player_key=response.request.meta['key'], player_birthday=datetime.datetime.timestamp(date_object))
            

