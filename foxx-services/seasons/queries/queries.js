const aql = require('@arangodb').aql;

module.exports.seasons = function(){
    return aql`FOR season in seasons 
    LET weeks =( FOR week IN 1..1 OUTBOUND season weeksInSeason
    SORT week.numner
    return week.number)
    sort season._key
RETURN {"season" : season._key, "weeks" : weeks}`
}

module.exports.seasonNumbers = function(){
    return aql`FOR season in seasons
    SORT season._key DESC
    return season._key`
}


module.exports.season = function(season){
    return aql`FOR season in seasons 
    FILTER season._key == TO_STRING(${season})
        LET weeks =( FOR week IN 1..1 OUTBOUND season weeksInSeason
        SORT week.number
        return week.number)
    RETURN {"season" : season._key, "weeks" : weeks}`
}