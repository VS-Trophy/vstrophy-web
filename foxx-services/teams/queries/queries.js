const aql = require('@arangodb').aql;

module.exports.allTeams = function(){
    return aql`FOR team IN VSTrophyTeams
    return team`
}