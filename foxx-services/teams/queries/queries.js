const aql = require('@arangodb').aql;

module.exports.allTeams = function(){
    return aql`FOR team IN VSTrophyTeams
        FOR division IN 1..1 INBOUND team TeamsInDivision
        LET divisionAttribute = {"division" : division.name}
    return merge(divisionAttribute, team)`
}