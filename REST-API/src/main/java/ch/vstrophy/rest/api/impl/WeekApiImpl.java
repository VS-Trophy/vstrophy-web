/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.entities.weeks.Week;
import ch.vstrophy.entities.weeks.WeekEntityManager;
import ch.vstrophy.rest.api.WeekApi;
import ch.vstrophy.rest.api.json.JsonResponseFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 *
 * @author kobashi@vstrophy.ch
 */
@Stateless
public class WeekApiImpl implements WeekApi {

    @Inject
    private WeekEntityManager entityManager;
    
    @Inject
    private JsonResponseFactory responseFactory;

    @Override
    public Response getWeeks(int season) {
        List<Week> weekList = entityManager.getWeeksOfSeason(season);
        weekList.forEach(wk -> wk.setMatches(null));
        Response resp = responseFactory.createJsonResponse(weekList);
        return resp;
    }
}
