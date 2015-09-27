/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.entities.match;

import ch.burninghammer.vstrophy.entities.teams.Team;
import ch.burninghammer.vstrophy.entities.weeks.Week;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_team_id")
    private Team firstTeam;
    @ManyToOne
    @JoinColumn(name = "second_team_id")
    private Team secondTeam;
    @Column(name = "first_team_points")
    private double firstTeamPoints;
    @Column(name = "second_team_points")
    private double secondTeamPoints;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id", nullable = false)
    private Week week;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public double getFirstTeamPoints() {
        return firstTeamPoints;
    }

    public void setFirstTeamPoints(double firstTeamPoints) {
        this.firstTeamPoints = firstTeamPoints;
    }

    public double getSecondTeamPoints() {
        return secondTeamPoints;
    }

    public void setSecondTeamPoints(double secondTeamPoints) {
        this.secondTeamPoints = secondTeamPoints;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

}
