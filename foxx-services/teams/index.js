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

    const teams =
    db._query(queries.allTeams())
    res.send(teams)
  } catch (e) {
    if (!e.isArangoError || e.errorNum !== DOC_NOT_FOUND) {
      throw e;
    }
    res.throw(500, 'Could not get teams', e);
  }
})
.response(['application/json'], 'All VS-Trophy teams')
.summary('Teams')
.description('Returns all VS-Trophy teams');
