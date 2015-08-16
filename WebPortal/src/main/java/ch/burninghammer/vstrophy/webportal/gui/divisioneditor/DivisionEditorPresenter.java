/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.divisioneditor;

import ch.burninghammer.vstrophy.webportal.entities.divisions.Division;
import ch.burninghammer.vstrophy.webportal.entities.divisions.DivisionEntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(DivisionEditorView.class)
public class DivisionEditorPresenter extends AbstractMVPPresenter<DivisionEditorView> {

    @Inject
    private DivisionEntityManager divisionEntityManager;

    @Override
    public void viewEntered() {
        view.showDivisionList(divisionEntityManager.getAllDivisions());
    }

    protected void divisionSelected(@Observes @CDIEvent(DivisionEditorCDIEvents.DIVISION_SELECTED) final ParameterDTO parameters) {
        try {
            Division division = parameters.getPrimaryParameter(Division.class);
            view.showDivision(division);
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "Could not cast selected division");
        }
    }

    protected void saveDivision(@Observes @CDIEvent(DivisionEditorCDIEvents.DIVISION_CHANGED) final ParameterDTO parameters) {
        try {
            Division division = parameters.getPrimaryParameter(Division.class);
            divisionEntityManager.saveDivision(division);
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "Could not cast selected division");
        }
        view.showDivisionList(divisionEntityManager.getAllDivisions());
    }

    protected void addDivision(@Observes @CDIEvent(DivisionEditorCDIEvents.DIVISION_ADD) final ParameterDTO parameters) {
        Division division = new Division();
        view.showDivision(division);
    }

}
