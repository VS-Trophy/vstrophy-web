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
.summary('Closest or clearest matches')
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


router.get('/team/:team/winloss', function (req, res) {
  try {
    const season = req.queryParams.season
    const team = req.pathParams.team
    const opponent = req.queryParams.opponent
    const record = 
    db._query(queries.winlossrecord(team, opponent,season))
    res.send(record._documents[0])
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get record', e);
  }
})
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.pathParam('team',joi.string().required(), 'The record of this team will be calculated')
.queryParam('opponent',joi.string().default(null), 'The record against this team will be calculated. If null the overall record will be calculated')
.response(['application/json'], 'An Object containing informations regarding the win loss record of this team')
.summary('The win / loss record of a team.')
.description('Returns the win / loss record of a team. May be narrowed down by season and / or opponent');

router.get('/team/:team/pointstats', function (req, res) {
  try {
    const team = req.pathParams.team
    const record = 
    db._query(queries.pointstats(team))
    res.send(record._documents[0])
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get pointstats', e);
  }
})
.pathParam('team',joi.string().required(), 'Stats of this team will be returned')
.response(['application/json'], 'Highest, lowest, and total points scored, average points per game and number of games')
.summary('Highest, lowest, and total points scored, average points per game and number of games')
.description('Highest, lowest, and total points scored, average points per game and number of games');

router.get('/teams/pointstats', function (req, res) {
  try {
    const filteredWeek = req.queryParams.week
    const filteredSeason = req.queryParams.season
    const record = 
    db._query(queries.pointstats(filteredWeek,filteredSeason))
    res.send(record._documents)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get pointstats', e);
  }
})
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.queryParam('week',joi.number().integer().positive().default(null), 'If set only results of this week are displayed')
.response(['application/json'], 'Highest, lowest, and total points scored, average points per game and number of games')
.summary('Highest, lowest, and total points scored, average points per game and number of games')
.description('Highest, lowest, and total points scored, average points per game and number of games');


router.get('/team/:team/winloss/opponents', function (req, res) {
  try {
    const season = req.queryParams.season
    const team = req.pathParams.team
    const record = 
    db._query(queries.winlossoverview(team, season))
    res.send(record)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get matches', e);
  }
})
.queryParam('season',joi.number().integer().positive().default(null), 'If set only results of this season are displayed')
.pathParam('team',joi.string().required(), 'The record of this team will be calculated')
.response(['application/json'], 'An Object containing informations regarding the win loss record of this team')
.summary('The win / loss record of a team.')
.description('Returns the win / loss record of a team. May be narrowed down by season');