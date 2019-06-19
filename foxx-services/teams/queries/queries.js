const aql = require('@arangodb').aql;

module.exports.allTeams = function(){
    return aql`FOR team IN teamsVST
        FOR division IN 1..1 INBOUND team teamsInDivision
        LET divisionAttribute = {"division" : division.name}
    return merge(divisionAttribute, team)`
}