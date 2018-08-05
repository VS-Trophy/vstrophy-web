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
    db._query(queries.news(offset,count))
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