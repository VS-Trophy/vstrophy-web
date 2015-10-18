/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.results;

import ch.vstrophy.entities.match.Match;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface ResultsView extends MVPView {

    public void setSeasonList(List<String> seasonList);

    public void setWeekList(List<String> weekList);

    public void setMatchList(List<Match> matchList);
}
