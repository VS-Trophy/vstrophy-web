'use strict';
const createRouter = require('@arangodb/foxx/router');
const router = createRouter();
const joi = require('joi');
const db = require('@arangodb').db;
const queries = require('./queries/queries.js')
const errors = require('@arangodb').errors;
const DOC_NOT_FOUND = errors.ERROR_ARANGO_DOCUMENT_NOT_FOUND.code;

module.context.use(router);


router.get('/', function (req, res) {
  try {
    const offset = req.queryParams.offset
    const count = req.queryParams.count
    const newsItems = 
    db._query(queries.newsItems(offset,count))
    res.send(newsItems)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get news items', e);
  }
})
.queryParam('count',joi.number().integer().positive().default(10), 'Number of news items to be returned (pagination)')
.queryParam('offset',joi.number().integer().min(0).default(0), 'Offset of the first news item to be returned (pagination)')
.response(['application/json'], 'A list of news items')
.summary('News Items')
.description('Returns news items');

router.get('/:newsItemId', function (req, res) {
  try {
    const newsItemId = req.pathParams.newsItemId;
    const newsItem = 
    db._query(queries.newsItem(newsItemId))
    res.send(newsItem)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(404, 'Could not get news item ' + newsItemId, e);
  }
})
.pathParam('newsItemId',joi.string().required(), 'The id of the news Item to get')
.response(['application/json'], 'The requested news item')
.summary('News Items')
.description('Returns news a news item by id');


router.post('/', function (req, res) {
  try {
    const doc = req.body;
    var answer;
    const collection = db._collection('NewsItems');
    if(doc._key != null){
      answer =  collection.replace(doc,doc)
    } else {
      delete doc._key
      answer =  collection.insert(doc)
    }
    doc._key = answer._key
    doc._id = answer._key
    doc._rev = answer._rev
    res.send(doc);
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(404, 'Could not get news item ' + newsItemId, e);
  }
})
.response(['application/json'], 'The created / update object')
.body(joi.object().required(), 'Entry to store in the collection.')
.summary('News Items')
.description('Creates or updates a news item');