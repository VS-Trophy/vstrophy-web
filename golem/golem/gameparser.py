from .items import MatchItemVST,  RosterItemVST, RosterSpotItemVST, PlayerItemVST
import sys


def parse_game(response):
    week = response.request.meta["week"]
    # First get the basics of the roster items
    roster1 = getRosterPerformance(
        response.css(".teamWrap-1 > .teamTotal"))
    roster2 = getRosterPerformance(
        response.css(".teamWrap-2 > .teamTotal"))

    fill_roster_spots(response.css(
        "#teamMatchupFullBoxScore .teamWrap-1"), roster1)
    fill_roster_spots(response.css(
        "#teamMatchupFullBoxScore .teamWrap-2"), roster2)

    match = MatchItemVST(
        roster1=dict(roster1),
        roster2=dict(roster2),
        season=week["season"],
        week=week["week"])
    yield match


'''
Fills the roster spots including player performances of the roster
'''


def fill_roster_spots(stats_table, roster_item):
    offense_table = stats_table.css("#tableWrap-O")
    kicker_table = stats_table.css("#tableWrap-K")
    defense_table = stats_table.css("#tableWrap-DT")
    spot_list = []
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-QB-0"), "qb"))
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-RB-0"),"rb1"))
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-RB-1"),"rb2"))
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-WR-0"),"wr1"))
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-WR-1"),"wr2"))
    spot_list.append(create_roster_spot(
        offense_table.css("tr.player-TE-0"),"te"))
    spot_list.append(create_roster_spot(
        offense_table.css(r"tr.player-W\/R-0"),"flex"))
    spot_list.append(create_roster_spot(
        kicker_table.css("tr.player-K-0"),"k"))
    spot_list.append(create_roster_spot(
        defense_table.css("tr.player-DEF-0"),"d"))

    offense_bench_classes = offense_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(offense_bench_classes):
        spot_name = "bn" + str(idx+1)
        spot_list.append(create_roster_spot(
            offense_table.css("tr." + css_class),spot_name))

    defense_bench_classes = defense_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(defense_bench_classes):
        spot_name = "bn" + str(idx+1)
        spot_list.append(create_roster_spot(
            offense_table.css("tr." + css_class),spot_name))

    kicker_bench_classes = kicker_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(kicker_bench_classes):
        spot_name = "bn" + str(idx+1)
        spot_list.append(create_roster_spot(
            offense_table.css("tr." + css_class),spot_name))

    roster_item["spots"] = spot_list


def create_roster_spot(player_row, spot_name):
  
    if player_row.css(".playerCard::attr(href)").get() is None:
        return None
    try:
        return RosterSpotItemVST(
            player=PlayerItemVST(
                player_key=player_row.css(
                    ".playerCard::attr(href)").re_first(r'playerId=(\d+)'),
                player_name=player_row.css('.playerCard::text').get()),
            spot_name=spot_name,            
        )
    except:
        print("Unexpected error:", sys.exc_info())
        print(player_row.get())


'''
    Expects the teamTotal div of a teamWrap of the gamecenter
    and returns the roster item containing only the key and 
    total points
    '''


def getRosterPerformance(perftag):
    return RosterItemVST(
        team_key=perftag.re_first(r'teamId-(\d+)'),
        points= float(perftag.xpath('.//text()').get())
    )
