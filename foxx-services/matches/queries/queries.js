const aql = require('@arangodb').aql;

module.exports.matches = function(season, week){
    return aql`FOR season IN Seasons
    FILTER  ${season} == null || season.number == ${season}
        FOR week IN 1..1 ANY season WeeksInSeason
        FILTER ${week} == null || week.number == ${week}
            FOR match IN 1..1 ANY week MatchesInWeek
                FOR team,performance IN 1..1 ANY match TeamPlayedIn
                COLLECT daMatch = match._key INTO teamPerfs
                    RETURN 
                    {"firstTeamId" : teamPerfs[0].team.nflId, 
                    "firstTeamPoints" : teamPerfs[0].performance.points, 
                    "secondTeamId" : teamPerfs[1].team.nflId, 
                    "secondTeamPoints" : teamPerfs[1].performance.points,
                    "season" : teamPerfs[0].season.number,
                    "week" :  teamPerfs[0].week.number}`
}

module.exports.closestMatches = function(ascdesc,limit,season,week){
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

module.exports.highestScoringMatches = function(ascdesc,limit,season,week){
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