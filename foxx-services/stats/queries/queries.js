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