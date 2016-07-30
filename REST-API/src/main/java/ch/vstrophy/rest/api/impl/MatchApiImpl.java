/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.match.MatchEntityManager;
import ch.vstrophy.entities.weeks.Week;
import javax.ws.rs.core.Response;
import ch.vstrophy.rest.api.MatchApi;
import ch.vstrophy.entities.match.MatchLite;
import ch.vstrophy.rest.api.json.JsonResponseFactory;
import java.util.List;
import java.util.stream.Collectors;
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
       List<MatchLite> matchLites = entityManager.getMatchLites(seasonWeek);
       return
       responseFactory.createJsonResponse(matchLites);
    }

    
}
