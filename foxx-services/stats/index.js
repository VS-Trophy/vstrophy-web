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

// continued
router.get('/performances/:topflop', function (req, res) {
  try {

    const ascDesc = mapTopFlop(req.pathParams.topflop)
    const limit = req.queryParams.limit
    const performances = 
    db._query(queries.topPerformances(ascDesc,limit))
    res.send(performances)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(404, 'The entry does not exist', e);
  }
})
.pathParam('topflop', joi.string().allow('top').allow('flop'), 'Either top or flop depending on, if the best or worst performances should be returned')
.queryParam('limit',joi.number().integer().positive().default(100), 'How many performances should be returned')
.response(['application/json'], 'An Object containing all important informations regarding the performance')
.summary('Best or worst team performances')
.description('Returns the best or worst team performances');


function mapTopFlop(topflop){
  switch(topflop.toUpperCase()){
    case 'TOP':
      return 'DESC';
    case 'FLOP':
    return 'ASC';
    default:
    throw "Unexpected value. Expected either TOP or FLOP but got " + topFlop
  }
  
}