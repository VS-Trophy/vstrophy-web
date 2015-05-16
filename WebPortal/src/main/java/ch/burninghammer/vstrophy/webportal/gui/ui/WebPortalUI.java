/*
 * © 2015 Burning Hammer
 */
package ch.burninghammer.vstrophy.webportal.gui.ui;

import ch.burninghammer.vstrophy.webportal.gui.main.MainView;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@CDIUI("")
public class WebPortalUI extends UI {

    @Inject
    private Instance<MVPView> views;

    @Override

    protected void init(VaadinRequest request) {
        MVPView mainView = views.select(MainView.class).get();
        setContent((Component) mainView);
        ((AbstractMVPView) mainView).enter();
    }

}
