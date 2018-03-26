const aql = require('@arangodb').aql;

module.exports.seasons = function(){
    return aql`FOR season in Seasons 
    LET weeks =( FOR week IN 1..1 ANY season WeeksInSeason
    SORT week.number
    return week.number)
    sort season.number
RETURN {"season" : season.number, "weeks" : weeks}`
}