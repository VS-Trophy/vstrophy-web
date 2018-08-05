const aql = require('@arangodb').aql;

module.exports.news = function(offset, count){
    return aql`FOR newsItem IN NewsItems
    SORT newsItem.publicationDate DESC
    LIMIT ${offset}, ${count}
    return newsItem`
}