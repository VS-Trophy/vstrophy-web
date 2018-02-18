/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.statistic;

import ch.vstrophy.common.WeekInfoProvider;
import ch.vstrophy.entities.match.Match;
import ch.vstrophy.entities.teams.Team;
import ch.vstrophy.exception.VSTrophyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@Stateful
@LocalBean
public class TeamStatisticProvider {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeamStatisticProvider.class);
    @Inject
    private WeekInfoProvider weekInfo;

    private Team team;

    private List<Match> allMatches;
    private List<Match> currentMatches;

    public void setTeam(Team team) {
      throw new UnsupportedOperationException();
    }

    public TeamRecord getTotalRecord() throws VSTrophyException {
throw new UnsupportedOperationException();
    }

    public TeamRecord getCurrentRecord() throws VSTrophyException {
 throw new UnsupportedOperationException();
    }

    public TeamRecord getCurrentDivisionRecord() throws VSTrophyException {
      throw new UnsupportedOperationException();
    }

    private TeamRecord getRecordForTeam(List<Match> matches) throws VSTrophyException {
      throw new UnsupportedOperationException();
    }

    public NumericStatisticPoint getCurrentSeasonPointsForTeam() throws VSTrophyException {
throw new UnsupportedOperationException();    }

    public NumericStatisticPoint getPointsForTeam(Team team) throws VSTrophyException {
throw new UnsupportedOperationException();    }


}
