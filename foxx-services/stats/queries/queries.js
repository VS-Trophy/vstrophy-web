const aql = require('@arangodb').aql;
const week = require('./week.js')


module.exports.winlossrecord = function (team, opponent, season) {
    var currentWeek = week.currentWeek()
    var currentSeason = week.currentSeason();
    team = "teamsVST/"+team
    return aql`LET seasonMatches = (
        FOR season IN seasons
    FILTER ${season} == null || season._key == TO_STRING(${season})
        FOR week IN 1..1 OUTBOUND season weeksInSeason
        FILTER season._key != TO_STRING(${currentSeason}) || week.number != ${currentWeek} 
            FOR match IN 1..1 OUTBOUND week matchesInWeekVST
            RETURN match._id
        )
        LET winloss =  MERGE (
        FOR vertex, performance, path IN 4..4 ANY ${team} rosterOfVST, rosterPlayedInVST
        FILTER ${opponent} == null || path.vertices[4]._key == ${opponent}  
        FILTER path.vertices[2]._id IN seasonMatches
        
        COLLECT category = path.edges[1].points > path.edges[2].points ? "wins" : "losses" WITH COUNT INTO ammount
        return {[category] : ammount})
        let matchCount = winloss.wins+winloss.losses
        RETURN {"wins" : winloss.wins?:0, "losses" : winloss.losses?:0, "ratio" : matchCount>0?(winloss.wins / (matchCount)):0}`
}

module.exports.winlossoverview = function (team, season) {
    var currentWeek = week.currentWeek()
    var currentSeason = week.currentSeason();
    team = "teamsVST/"+team
    return aql`
    LET seasonMatches = (
        FOR season IN seasons
    FILTER ${season} == null || season._key == TO_STRING(${season})
        FOR week IN 1..1 OUTBOUND season weeksInSeason
        FILTER season._key != TO_STRING(${currentSeason}) || week.number != ${currentWeek} 
            FOR match IN 1..1 OUTBOUND week matchesInWeekVST
            RETURN match._id
        )
    
    
    FOR node,edge,path IN 4..4 ANY ${team} rosterOfVST, rosterPlayedInVST
    FILTER  path.vertices[2]._id IN seasonMatches
    COLLECT opponent = node._key
    AGGREGATE wins = SUM(path.edges[1].points > path.edges[2].points ? 1 : 0), losses= SUM(path.edges[1].points > path.edges[2].points ? 0 : 1)
    LET matchCount = wins+losses
    LET ratio =  matchCount>0?(wins / (matchCount)):0
    SORT ratio DESC
    RETURN {"opponent" : opponent, "record" : {"wins" : wins, "losses": losses,"ratio" : ratio}}`
}

module.exports.pointstats = function (team) {
    var currentWeek = week.currentWeek();
    var currentSeason = week.currentSeason();
    return aql`

    FOR team IN VSTrophyTeams FILTER team.nflId == ${team}  LIMIT 1
    FOR match, performance IN 1..1 OUTBOUND team TeamPlayedIn
    LET isMatchOngoing = LENGTH(
        FOR week IN 1..1 INBOUND match MatchesInWeek 
            FOR season IN 1..1 INBOUND week WeeksInSeason
            FILTER week.number == ${currentWeek} && season.number == ${currentSeason}
                RETURN season
    )
    FILTER isMatchOngoing == 0
    COLLECT AGGREGATE averagePoints = AVERAGE(performance.points), minPoints = MIN(performance.points), maxPoints = MAX(performance.points), totalPoints = SUM(performance.points), matches = LENGTH(match)
    RETURN {'team': ${team}, 
    "average" : averagePoints,
    "max": maxPoints,
    "min" : minPoints,
    "total": totalPoints,
    "matches": matches}`
}

module.exports.pointstatsTeams = function (filteredWeek, filteredSeason) {
    var currentWeek = week.currentWeek();
    var currentSeason = week.currentSeason();
    return aql`

    FOR team IN VSTrophyTeams

    FOR match, performance,path IN 2..2 ANY team TeamPlayedIn
    LET isMatchInValid = LENGTH(
        FOR week IN 1..1 INBOUND path.vertices[1] MatchesInWeek 
        FOR season IN 1..1 INBOUND week WeeksInSeason
        FILTER
        (${filteredSeason} !=null && season.number != ${filteredSeason} ) 
        OR
        (${filteredWeek} != null && week.number != ${filteredWeek})
        OR
        (week.number == ${currentWeek} && season.number == ${currentSeason})
        RETURN season
    )
    FILTER isMatchInValid == 0
    LET isWin = path.edges[0].points > path.edges[1].points ? 1 : 0
    COLLECT teamKey = team.nflId 
    AGGREGATE averagePoints = AVERAGE(path.edges[0].points), 
    minPoints = MIN(path.edges[0].points), 
    maxPoints = MAX(path.edges[0].points), 
    totalPoints = SUM(path.edges[0].points), 
    matches = LENGTH(match), 
    wins = SUM(isWin)
    
    RETURN {'team': teamKey,
    'wins': wins,
    'losses': (matches - wins),
    "average" : averagePoints,
    "ratio" : (wins / (matches)),
    "max": maxPoints,
    "min" : minPoints,
    "total": totalPoints,
    "matches": matches}`
}