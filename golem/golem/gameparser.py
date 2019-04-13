from .items import MatchItemVST

def parse_game(response):
    week = response.request.meta["week"]
    team1_perf = getTeamperformance(
        response.css(".teamWrap-1 > .teamTotal"))
    team2_perf = getTeamperformance(
        response.css(".teamWrap-2 > .teamTotal"))
    match = MatchItemVST(
        team1=team1_perf["id"],
        team1_points=team1_perf["points"],
        team2=team2_perf["id"],
        team2_points=team2_perf["points"],
        season=week["season"],
        week=week["week"])
    yield match


'''
    Expects the teamTotal div of a teamWrap of the gamecenter and returns a
    dict containing the points and the id
    '''


def getTeamperformance(perftag):
        teamperf = {}
        teamperf["points"] = perftag.xpath('.//text()').get()
        teamperf["id"] = perftag.re_first('teamId-(\d+)')
        return teamperf
