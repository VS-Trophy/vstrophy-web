/*
 * © 2015 Burning Hammer
 */
package ch.burninghammer.vstrophy.webportal.gui.ui;

import ch.burninghammer.vstrophy.webportal.gui.main.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.cdimvp.AbstractMVPView;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@CDIUI("")
@Theme("VSTrophyWebPortalTheme")
public class WebPortalUI extends UI {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WebPortalUI.class);
    @Inject
    private Instance<MVPView> views;

    @Override

    protected void init(VaadinRequest request) {
        LOGGER.info("Initializing UI");
        MVPView mainView = views.select(MainView.class).get();
        setContent((Component) mainView);
        ((AbstractMVPView) mainView).enter();
    }

}
