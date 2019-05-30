
from .items import PlayerItemVST, OffensivePlayerPerformanceItemVST, KickPlayerPerformanceItemVST, DefensivePlayerPerformanceItemVST


def get_offensive_performance(player_row, week):
    performance = OffensivePlayerPerformanceItemVST(
        week=week,
        player=create_player(player_row),
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
    return performance


def get_kicker_performance(player_row, week):
    performance = KickPlayerPerformanceItemVST(
        week=week,
        player=create_player(player_row),
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
    return performance


def get_defense_performance(player_row, week):
    performance = DefensivePlayerPerformanceItemVST(
        week=week,
        player=create_player(player_row),
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
    return performance


def create_player(player_row):
    return PlayerItemVST(
        player_key=player_row.re_first(r'player-(\d+)'),
        player_name=player_row.css('a.playerName::text').get()
    )
