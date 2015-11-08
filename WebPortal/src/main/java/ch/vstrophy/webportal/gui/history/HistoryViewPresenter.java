/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.gui.history;

import ch.vstrophy.entities.persistenttexts.PersistentText;
import ch.vstrophy.entities.persistenttexts.PersistentTextEntityManager;
import ch.vstrophy.webportal.gui.main.login.LoginProvider;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPPresenter;
import org.vaadin.addon.cdimvp.CDIEvent;
import org.vaadin.addon.cdimvp.ParameterDTO;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@AbstractMVPPresenter.ViewInterface(HistoryView.class)
public class HistoryViewPresenter extends AbstractMVPPresenter<HistoryView> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HistoryViewPresenter.class);
    @Inject
    private PersistentTextEntityManager textEntityManager;

    @Inject
    private LoginProvider loginProvider;

    private static final int HISTORY_TEXT_ID = 1;

    @Override
    public void viewEntered() {
        String content;
        try {
            PersistentText text = textEntityManager.getPersistentText(HISTORY_TEXT_ID);
            content = text.getContent();
        } catch (Exception ex) {
            LOGGER.error("Could not load history text", ex);
            content = "History";
        }
        view.showHistory(content, loginProvider.getUser() == null ? false : loginProvider.getUser().isAdmin());
    }

    protected void historyTextChanged(@Observes @CDIEvent(HistoryViewCDIEvents.HISTORY_VIEW_PERSISTENT_TEXT_CHANGED) final ParameterDTO parameters) {
        try {
            String content = parameters.getPrimaryParameter(String.class);
            PersistentText pText = new PersistentText();
            pText.setId(HISTORY_TEXT_ID);
            pText.setContent(content);
            textEntityManager.savePersistentText(pText);
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "Could not cast saved text");
        }
    }

}