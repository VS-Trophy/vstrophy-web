/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.rest.api.impl;

import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.entities.teams.TeamEntityManager;
import ch.vstrophy.rest.api.TeamApi;
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
public class TeamApiImpl implements TeamApi {

    @Inject
    private TeamEntityManager teamEntityManager;
    
    @Inject
    private JsonResponseFactory responseFactory;

    @Override
    public Response getTeams() {
        List<Team> teamList = teamEntityManager.getAllTeams();
        return responseFactory.createJsonResponse(teamList);
    }

    @Override
    public Response getTeam(int id) {
        Team team = teamEntityManager.getTeam(id);
        return responseFactory.createJsonResponse(team);
    }


    
}
