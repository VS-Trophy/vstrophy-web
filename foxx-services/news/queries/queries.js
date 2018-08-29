const aql = require('@arangodb').aql;

module.exports.newsItems = function(offset, count){
    return aql`FOR newsItem IN NewsItems
    SORT newsItem.publicationDate DESC
    LIMIT ${offset}, ${count}
    return MERGE(newsItem,{id: newsItem._key})`
}

module.exports.newsItem = function(newsItemId){
    return aql`FOR newsItem IN NewsItems
    FILTER newsItem._key == ${newsItemId}
    LIMIT 1
    return MERGE(newsItem,{id: newsItem._key})`
}