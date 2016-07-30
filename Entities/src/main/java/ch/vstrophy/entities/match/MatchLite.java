/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.vstrophy.entities.match;

import ch.vstrophy.entities.match.Match;

/**
 *
 * @author kobashi@vstrophy.ch
 */
public class MatchLite {
    public int firstTeamId;
    public int secondTeamId;
    public double firstTeamPoints;
    public double secondTeamPoints;
    
    public MatchLite(int firstId, int secondId, double firstPoints, double secondPoints){
        this.firstTeamId = firstId;
        this.secondTeamId = secondId;
        this.firstTeamPoints = firstPoints;
        this.secondTeamPoints = secondPoints;
    }
    
    public MatchLite(Match match){
        firstTeamId = match.getFirstTeam().getId();
        secondTeamId = match.getSecondTeam().getId();
        firstTeamPoints = match.getFirstTeamPoints();
        secondTeamPoints = match.getSecondTeamPoints();
    }
}
