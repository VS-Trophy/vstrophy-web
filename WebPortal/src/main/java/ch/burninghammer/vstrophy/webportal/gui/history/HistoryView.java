/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.history;

import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface HistoryView extends MVPView {

    void showHistory(String content, boolean isEditable);
}
