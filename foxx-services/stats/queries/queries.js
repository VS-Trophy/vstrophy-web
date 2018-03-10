const aql = require('@arangodb').aql;
module.exports.topPerformances = function(ascdesc, limit){
return aql`
FOR teamPerformance IN TeamPlayedIn
SORT teamPerformance.points ${ascdesc}

LIMIT ${limit}
LET team = DOCUMENT(teamPerformance._from)
LET match = DOCUMENT(teamPerformance._to)

LET seasonweek = FIRST(
FOR week IN 1..1 ANY match MatchesInWeek
FOR season IN 1..1 ANY week WeeksInSeason
RETURN {"season" : season.number, "week" : week.number}
)

LET opponentPerformance = FIRST(
FOR opp,oppPerf IN 1..1 ANY match TeamPlayedIn
FILTER opp!=team
RETURN {"opponent" : opp.name, "points" : oppPerf.points}
) 
RETURN {"points" : teamPerformance.points, "team" : team.name, "season" : seasonweek.season, "week": seasonweek.week, "opponent": opponentPerformance.opponent, "opponentPoints" : opponentPerformance.points }`
}