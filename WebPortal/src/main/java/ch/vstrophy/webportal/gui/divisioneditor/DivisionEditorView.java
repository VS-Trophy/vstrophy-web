/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.divisioneditor;

import ch.vstrophy.entities.divisions.Division;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface DivisionEditorView extends MVPView {

    public void showDivision(Division division);

    public void showDivisionList(List<Division> divisionList);
}
