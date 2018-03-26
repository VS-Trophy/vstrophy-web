const aql = require('@arangodb').aql;

module.exports.seasons = function(){
    return aql`FOR season in Seasons 
    LET weeks =( FOR week IN 1..1 ANY season WeeksInSeason
    SORT week.number
    return week.number)
    sort season.number
RETURN {"season" : season.number, "weeks" : weeks}`
}

module.exports.seasonNumbers = function(){
    return aql`FOR season in Seasons
    SORT season.number DESC
    return season.number`
}


module.exports.season = function(season){
    return aql`FOR season in Seasons 
    FILTER season.number == ${season}
        LET weeks =( FOR week IN 1..1 ANY season WeeksInSeason
        SORT week.number
        return week.number)
    RETURN {"season" : season.number, "weeks" : weeks}`
}