/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.entities.match.MatchEntityManager;
import ch.vstrophy.entities.weeks.Week;
import javax.ws.rs.core.Response;
import ch.vstrophy.rest.api.MatchApi;
import ch.vstrophy.rest.api.json.JsonResponseFactory;
import javax.inject.Inject;

/**
 *
 * @author kobashi@vstrophy.ch
 */
public class MatchApiImpl  implements MatchApi{

    @Inject
    private MatchEntityManager entityManager;
    
    @Inject
    private JsonResponseFactory responseFactory;

    @Override
    public Response getMatch(int year, int week) {
       Week seasonWeek = new Week(year, week);
       return
       responseFactory.createJsonResponse(entityManager.getMatches(seasonWeek));
    }

    
}
