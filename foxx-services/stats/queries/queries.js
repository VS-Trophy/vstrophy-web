const aql = require('@arangodb').aql;
module.exports.topPerformances = function(ascdesc,limit,week,season){
return aql`
FOR teamPerformance IN TeamPlayedIn
SORT teamPerformance.points ${ascdesc}


LET team = DOCUMENT(teamPerformance._from)
LET match = DOCUMENT(teamPerformance._to)

FOR week IN 1..1 ANY match MatchesInWeek
FILTER ${week}==null || week.number == ${week} 
FOR season IN 1..1 ANY week WeeksInSeason
FILTER ${season}==null || season.number == ${season} 
LET seasonweek =  {"season" : season.number, "week" : week.number}
LIMIT ${limit}
LET opponentPerformance = FIRST(
FOR opp,oppPerf IN 1..1 ANY match TeamPlayedIn
FILTER opp!=team
RETURN {"opponent" : opp.name, "points" : oppPerf.points}
) 
RETURN {"points" : teamPerformance.points, "team" : team.name, "season" : seasonweek.season, "week": seasonweek.week, "opponent": opponentPerformance.opponent, "opponentPoints" : opponentPerformance.points }`
}

module.exports.closestMatches = function(ascdesc,limit,week,season){
return aql`FOR match IN Matches
LET performances = (FOR team,performance IN 1..1 ANY match TeamPlayedIn
RETURN {"team" : team.name, "points" : performance.points})
LET margin = ABS(performances[0].points - performances[1].points)
SORT margin ${ascdesc}

FOR week IN 1..1 ANY match MatchesInWeek
FILTER ${week}==null || week.number == ${week} 
FOR season IN 1..1 ANY week WeeksInSeason
FILTER ${season}==null || season.number == ${season} 
LET seasonweek =  {"season" : season.number, "week" : week.number}

LIMIT ${limit}
RETURN {"firstTeam": performances[0].team,"firstTeamPoints": performances[0].points,"secondTeam": performances[1].team,"secondTeamPoints": performances[1].points, "margin" : margin,"season" : seasonweek.season, "week": seasonweek.week}`
}

module.exports.highestScoringMatches = function(ascdesc,limit,week,season){
    return aql`FOR match IN Matches
    LET performances = (FOR team,performance IN 1..1 ANY match TeamPlayedIn
    RETURN {"team" : team.name, "points" : performance.points})
    LET score = performances[0].points + performances[1].points
    SORT score ${ascdesc}
    
    FOR week IN 1..1 ANY match MatchesInWeek
    FILTER ${week}==null || week.number == ${week} 
    FOR season IN 1..1 ANY week WeeksInSeason
    FILTER ${season}==null || season.number == ${season} 
    LET seasonweek =  {"season" : season.number, "week" : week.number}
    
    LIMIT ${limit}
    RETURN {"firstTeam": performances[0].team,"firstTeamPoints": performances[0].points,"secondTeam": performances[1].team,"secondTeamPoints": performances[1].points, "totalScore" : score,"season" : seasonweek.season, "week": seasonweek.week}`
}


module.exports.winlossrecord = function(team, opponent, season){
    return aql`LET seasonMatches = (
        FOR season IN Seasons
        FILTER ${season} == null || season.number == ${season}
            FOR week IN 1..1 ANY season WeeksInSeason
                FOR match IN 1..1 ANY week MatchesInWeek
                RETURN match._id
        )
        
        LET winloss =  MERGE (
        FOR team IN VSTrophyTeams 
        FILTER team.nflId == ${team}
        FOR vertex, performance, path IN 2..2 ANY team TeamPlayedIn
        FILTER ${opponent} == null || path.vertices[2].nflId == ${opponent}  
        FILTER ${season} == null || path.vertices[1]._id IN seasonMatches
        
        COLLECT category = path.edges[0].points > path.edges[1].points ? "wins" : "losses" WITH COUNT INTO ammount
        return {[category] : ammount})
        let matchCount = winloss.wins+winloss.losses
        RETURN {"wins" : winloss.wins?:0, "losses" : winloss.losses?:0, "ratio" : matchCount>0?(winloss.wins / (matchCount)):0}`
}

module.exports.winlossoverview = function(team, season){
    return aql`LET team = 
    FIRST (
        FOR team IN VSTrophyTeams
        FILTER team.nflId == ${team}
        RETURN team
    )
    
    LET seasonMatches = (
    FOR season IN Seasons
    FILTER ${season} == null || season.number == ${season}
        FOR week IN 1..1 ANY season WeeksInSeason
            FOR match IN 1..1 ANY week MatchesInWeek
            RETURN match._id
    )
    
    
    FOR node,edge,path IN 2..2 ANY team TeamPlayedIn
    FILTER ${season} == null || path.vertices[1]._id IN seasonMatches
    COLLECT opponent = node.nflId
    AGGREGATE wins = SUM(path.edges[0].points > path.edges[1].points ? 1 : 0), losses= SUM(path.edges[0].points > path.edges[1].points ? 0 : 1)
    let matchCount = wins+losses
    RETURN {"opponent" : opponent, "record" : {"wins" : wins, "losses": losses,"ratio" : matchCount>0?(wins / (matchCount)):0}}`
}