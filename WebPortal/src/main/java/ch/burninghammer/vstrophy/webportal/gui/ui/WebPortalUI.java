/*
 * © 2015 Burning Hammer
 */
package ch.burninghammer.vstrophy.webportal.gui.ui;

import ch.burninghammer.vstrophy.webportal.gui.main.MainViewImpl;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 *
 * @author kobashi@burninghammer.ch
 */
@CDIUI("")
@Theme("runo")
public class WebPortalUI extends UI {

    @Inject
    private Instance<MainViewImpl> mainView;

    @Override

    protected void init(VaadinRequest request) {
        setContent(mainView.get());
    }

}
