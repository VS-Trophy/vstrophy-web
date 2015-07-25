/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.events;

import org.vaadin.addon.cdimvp.AbstractMVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class VSTrophyMVPView extends AbstractMVPView {

    public void fireCDIViewEvent(String event, Object primaryParameter, Object... secondaryParameters) {
        fireViewEvent(event, primaryParameter, secondaryParameters);
    }
}