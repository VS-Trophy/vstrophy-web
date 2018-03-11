'use strict';
const createRouter = require('@arangodb/foxx/router');
const router = createRouter();
const joi = require('joi');
const db = require('@arangodb').db;
const queries = require('./queries/queries.js')
const errors = require('@arangodb').errors;
const teamCollection = db._collection('VSTrophyTeams');
const DOC_NOT_FOUND = errors.ERROR_ARANGO_DOCUMENT_NOT_FOUND.code;

module.context.use(router);


router.get('/performances/', function (req, res) {
  try {
    const ascDesc = req.queryParams.sortorder
    const week = req.queryParams.week
    const season = req.queryParams.season
    const limit = req.queryParams.limit
    const performances = 
    db._query(queries.topPerformances(ascDesc,limit,week,season))
    res.send(performances)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get performances', e);
  }
})
.queryParam('sortorder',joi.string().allow("desc").allow("asc").insensitive().default('desc'), 'Sort order. ASC or DESC')
.queryParam('limit',joi.number().integer().positive().default(10), 'How many matches should be returned')
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.queryParam('week',joi.number().integer().positive().default(null), 'If set only results of this week are displayed')
.response(['application/json'], 'An Object containing all important informations regarding the performance')
.summary('Best or worst team performances')
.description('Returns the best or worst team performances');

router.get('/matches/margin', function (req, res) {
  try {
    const ascDesc = req.queryParams.sortorder
    const week = req.queryParams.week
    const season = req.queryParams.season
    const limit = req.queryParams.limit
    const matches = 
    db._query(queries.closestMatches(ascDesc,limit,week,season))
    res.send(matches)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get matches', e);
  }
}).queryParam('sortorder',joi.string().allow("desc").allow("asc").insensitive().default('asc'), 'Sort order. ASC or DESC')
.queryParam('limit',joi.number().integer().positive().default(10), 'How many matches should be returned')
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.queryParam('week',joi.number().integer().positive().default(null), 'If set only results of this week are displayed')
.response(['application/json'], 'An Object containing all important informations regarding the matches')
.summary('Highest or lowest scoring matches')
.description('Returns the closest or clearest matches');



router.get('/matches/totalpoints', function (req, res) {
  try {
    const ascDesc = req.queryParams.sortorder
    const week = req.queryParams.week
    const season = req.queryParams.season
    const limit = req.queryParams.limit
    const matches = 
    db._query(queries.highestScoringMatches(ascDesc,limit,week,season))
    res.send(matches)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get matches', e);
  }
})
.queryParam('sortorder',joi.string().allow("desc").allow("asc").insensitive().default('desc'), 'Sort order. ASC or DESC')
.queryParam('limit',joi.number().integer().positive().default(10), 'How many matches should be returned')
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.queryParam('week',joi.number().integer().positive().default(null), 'If set only results of this week are displayed')
.response(['application/json'], 'An Object containing all important informations regarding the matches')
.summary('Highest or lowest scoring matches')
.description('Returns the closest or clearest matches');

