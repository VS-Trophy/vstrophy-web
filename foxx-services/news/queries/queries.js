const aql = require('@arangodb').aql;

module.exports.newsItems = function(offset, count){
    return aql`FOR newsItem IN newsItems
    SORT newsItem.publicationDate DESC
    LIMIT ${offset}, ${count}
    return newsItem`
}

module.exports.newsItem = function(newsItemId){
    return aql`FOR newsItem IN newsItems
    FILTER newsItem._key == ${newsItemId}
    LIMIT 1
    return newsItem`
}