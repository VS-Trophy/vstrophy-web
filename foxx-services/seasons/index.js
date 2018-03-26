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
    const seasons =
    db._query(queries.seasons())
    res.send(seasons)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get seasons', e);
  }
})
.response(['application/json'], 'All seasons with their weeks')
.summary('Seasons with weeks')
.description('Returns all season with their weeks');