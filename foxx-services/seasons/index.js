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


router.get('/:seasonNumber', function (req, res) {
  try {
    const seasonParam = req.pathParams.seasonNumber
    var seasons =
    db._query(queries.season(Number(seasonParam)))
    res.send(seasons)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get season', e);
  }
})
.pathParam('seasonNumber', joi.number().required(), 'Season to get')
.response(['application/json'], 'The season with its weeks')
.summary('Season with weeks')
.description('Returns the specified season with its weeks');

router.get('/list', function (req, res) {
  try {
    const seasons =
    db._query(queries.seasonNumbers())
    res.send(seasons)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get seasons', e);
  }
})
.response(['application/json'], 'A list of all seasons')
.summary('The list of all sesasons')
.description('Returns a list with the year numbers of all seasons');
