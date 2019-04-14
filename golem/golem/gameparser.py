from .items import MatchItemVST,  RosterItemVST, RosterSpotItemVST, OffensivePlayerPerformanceItemVST, KickPlayerPerformanceItemVST, DefensivePlayerPerformanceItemVST


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
    roster_item["qb"] = create_offense_roster_spot(
        offense_table.css("tr.player-QB-0"))
    roster_item["rb1"] = create_offense_roster_spot(
        offense_table.css("tr.player-RB-0"))
    roster_item["rb2"] = create_offense_roster_spot(
        offense_table.css("tr.player-RB-1"))
    roster_item["wr1"] = create_offense_roster_spot(
        offense_table.css("tr.player-WR-0"))
    roster_item["wr2"] = create_offense_roster_spot(
        offense_table.css("tr.player-WR-1"))
    roster_item["te"] = create_offense_roster_spot(
        offense_table.css("tr.player-TE-0"))
    roster_item["flex"] = create_offense_roster_spot(
        offense_table.css(r"tr.player-W\/R-0"))
    roster_item["k"] = create_kicker_roster_spot(
        kicker_table.css("tr.player-K-0"))
    roster_item["d"] = create_defense_roster_spot(
        defense_table.css("tr.player-DEF-0"))

    offense_bench_classes = offense_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(offense_bench_classes):
        roster_item["bn" + str(idx+1)] = create_offense_roster_spot(
            offense_table.css("tr." + css_class))

    defense_bench_classes = defense_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(defense_bench_classes):
        roster_item["bn" + str(idx+1)] = create_defense_roster_spot(
            defense_table.css("tr." + css_class))

    kicker_bench_classes = kicker_table.css(
        "tr::attr(class)").re(r'(player-BN-\d+)')
    for idx, css_class in enumerate(kicker_bench_classes):
        roster_item["bn" + str(idx+1)] = create_kicker_roster_spot(
            kicker_table.css("tr." + css_class))


def create_base_player_roster_spot(player_row):
    opponent_string = player_row.css("td.playerOpponent::text").get()

    return RosterSpotItemVST(
        player_key=player_row.css(
            ".playerCard::attr(href)").re_first(r'playerId=(\d+)'),
        player_nfl_team=player_row.css("div.c").re_first(r'c-(\w+)').upper(),
        player_nfl_position=player_row.css(
            ".playerNameAndInfo em::text").get().split(" - ")[0],
        player_nfl_opponent=opponent_string.replace("@", ""),
        player_nfl_result=player_row.css("span.rt::text").get(),
        player_nfl_score=player_row.css(
            "em.s-a::text").get() + '-' + player_row.css("em.s-h::text").get(),
        player_nfl_home=("@" in opponent_string)
    )


def create_offense_roster_spot(player_row):
    roster_spot = create_base_player_roster_spot(player_row)
    performance = OffensivePlayerPerformanceItemVST(
        points=float(player_row.css(
            "span.playerTotal::text").get().replace("-", "0")),
        pass_yds=float(player_row.css(
            "span.statId-5::text").get().replace("-", "0")),
        pass_tds=int(player_row.css(
            "span.statId-6::text").get().replace("-", "0")),
        pass_ints=int(player_row.css(
            "span.statId-7::text").get().replace("-", "0")),
        rush_yds=float(player_row.css(
            "span.statId-14::text").get().replace("-", "0")),
        rush_tds=int(player_row.css(
            "span.statId-15::text").get().replace("-", "0")),
        rec_yds=float(player_row.css(
            "span.statId-21::text").get().replace("-", "0")),
        rec_tds=int(player_row.css(
            "span.statId-22::text").get().replace("-", "0")),
        fum_tds=int(player_row.css(
            "span.statId-29::text").get().replace("-", "0")),
        two_pts=int(player_row.css(
            "span.statId-32::text").get().replace("-", "0")),
        fum_lost=int(player_row.css(
            "span.statId-30::text").get().replace("-", "0"))
    )
    roster_spot["performance"] = performance
    return roster_spot


def create_kicker_roster_spot(player_row):
    roster_spot = create_base_player_roster_spot(player_row)
    performance = KickPlayerPerformanceItemVST(
        points=float(player_row.css(
            "span.playerTotal::text").get().replace("-", "0")),
        pats=int(player_row.css(
            "span.statId-33::text").get().replace("-", "0")),
        twenty_minus_made=int(player_row.css(
            "span.statId-35::text").get().replace("-", "0")),
        twenty_thirty_made=int(player_row.css(
            "span.statId-36::text").get().replace("-", "0")),
        thirty_forthy_made=int(player_row.css(
            "span.statId-37::text").get().replace("-", "0")),
        forthy_fifty_made=int(player_row.css(
            "span.statId-38::text").get().replace("-", "0")),
        fifty_plus_made=int(player_row.css(
            "span.statId-39::text").get().replace("-", "0"))
    )
    roster_spot["performance"] = performance
    return roster_spot


def create_defense_roster_spot(player_row):
    roster_spot = create_base_player_roster_spot(player_row)
    performance = DefensivePlayerPerformanceItemVST(
         points=float(player_row.css(
            "span.playerTotal::text").get().replace("-", "0")),
        sacks=int(player_row.css(
              "span.statId-45::text").get().replace("-", "0")),
        ints=int(player_row.css(
              "span.statId-46::text").get().replace("-", "0")),
        fum_rec=int(player_row.css(
              "span.statId-47::text").get().replace("-", "0")),
        safeties=int(player_row.css(
              "span.statId-49::text").get().replace("-", "0")),
        def_tds=int(player_row.css(
              "span.statId-50::text").get().replace("-", "0")),
        ret_tds=int(player_row.css(
              "span.statId-53::text").get().replace("-", "0")),
        allowed_pts=int(player_row.css(
              "span.statId-54::text").get().replace("-", "0")),
    )
    roster_spot["performance"] = performance
    return roster_spot


'''
    Expects the teamTotal div of a teamWrap of the gamecenter
    and returns the roster item containing only the key and 
    total points
    '''


def getRosterPerformance(perftag):
    return RosterItemVST(
        team_key=perftag.re_first('teamId-(\d+)'),
        points=perftag.xpath('.//text()').get()
    )
