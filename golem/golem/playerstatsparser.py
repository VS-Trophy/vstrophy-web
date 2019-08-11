
from .items import PlayerItemVST, PlayerPerformanceItemVST


def get_player_performance(player_stats):

    #Remove all stats that are not in the item class. How pythonic
    filtered_stats = {k: v for k, v in player_stats.items() if k in vars(PlayerPerformanceItemVST)['fields']}
    return PlayerPerformanceItemVST(**filtered_stats)


def create_player(player_row):
    return PlayerItemVST(
        player_key=player_row.re_first(r'player-(\d+)'),
        player_name=player_row.css('a.playerName::text').get()
    )
