/*
 * © 2015 Burning Hammer
 */
package ch.burninghammer.vstrophy.webportal.gui.main;

import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface MainView extends MVPView {

    public void setView(MVPView view);

    public void showAllButtons();

    public void showPublicButtons();

}
