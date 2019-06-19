const aql = require('@arangodb').aql;
const weekmod = require('./week.js')
module.exports.matches = function(season, week, team1, team2){
    return aql`FOR season IN seasons
    FILTER  ${season} == null || season._key == TO_STRING(${season})
        FOR week IN 1..1 OUTBOUND season weeksInSeason
        FILTER ${week} == null || week.number == ${week}
            FOR match IN 1..1 OUTBOUND week matchesInWeekVST
                FOR roster,performance IN 1..1 INBOUND match rosterPlayedInVST
                    FOR team IN 1..1 INBOUND roster rosterOfVST
                    SORT team._key
                    COLLECT daMatch = match._key INTO teamPerfs
                        LET featuredTeams = [teamPerfs[0].team._key,teamPerfs[1].team._key]
                        FILTER ${team1} == null  || POSITION(featuredTeams, ${team1})
                        FILTER ${team2} == null || POSITION(featuredTeams, ${team2})
                        SORT teamPerfs[0].season._key DESC, teamPerfs[0].week.number DESC
                    RETURN 
                        {"firstTeamId" : teamPerfs[0].team._key, 
                        "firstTeamPoints" : teamPerfs[0].performance.points, 
                        "secondTeamId" : teamPerfs[1].team._key, 
                        "secondTeamPoints" : teamPerfs[1].performance.points,
                        "season" : teamPerfs[0].season._key,
                        "week" :  teamPerfs[0].week.number}`
}

module.exports.closestMatches = function(ascdesc,limit,season,week){
    var currentWeek =  weekmod.currentWeek()
    var currentSeason = weekmod.currentSeason();
return aql`FOR match IN matchesVST
LET performances = (FOR roster,performance IN 1..1 INBOUND match rosterPlayedInVST
    FOR team IN 1..1 INBOUND roster rosterOfVST
RETURN {"team" : team, "points" : performance.points})
LET margin = ABS(performances[0].points - performances[1].points)
SORT margin ${ascdesc}

FOR week IN 1..1 INBOUND match matchesInWeekVST
FILTER ${week}==null || week.number == ${week} 
FOR season IN 1..1 INBOUND week weeksInSeason
FILTER ${season}==null || season._key == TO_STRING(${season})
FILTER week.number != ${currentWeek} || season._key != TO_STRING(${currentSeason})
LET seasonweek =  {"season" : season._key, "week" : week.number}

LIMIT ${limit}
RETURN {"firstTeamId": performances[0].team._key,"firstTeamPoints": performances[0].points,"secondTeamId": performances[1].team._key,"secondTeamPoints": performances[1].points, "margin" : margin,"season" : seasonweek.season, "week": seasonweek.week}`
}

module.exports.highestScoringMatches = function(ascdesc,limit,season,week){
    var currentWeek =  weekmod.currentWeek()
    var currentSeason = weekmod.currentSeason();
    return aql`FOR match IN matchesVST
    LET performances = (FOR roster,performance IN 1..1 INBOUND match rosterPlayedInVST
    FOR team IN 1..1 INBOUND roster rosterOfVST
    RETURN {"team" : team, "points" : performance.points})
    LET score = performances[0].points + performances[1].points
    SORT score ${ascdesc}
    
    FOR week IN 1..1 INBOUND match matchesInWeekVST
    FILTER ${week}==null || week.number == ${week} 
    FOR season IN 1..1 INBOUND week weeksInSeason
    FILTER ${season}==null || season._key == TO_STRING(${season})
    FILTER week.number != ${currentWeek} || season._key != TO_STRING(${currentSeason})
    LET seasonweek =  {"season" : season._key, "week" : week.number}
    
    LIMIT ${limit}
    RETURN {"firstTeamId": performances[0].team._key,"firstTeamPoints": performances[0].points,"secondTeamId": performances[1].team._key,"secondTeamPoints": performances[1].points, "totalScore" : score,"season" : seasonweek.season, "week": seasonweek.week}`
}