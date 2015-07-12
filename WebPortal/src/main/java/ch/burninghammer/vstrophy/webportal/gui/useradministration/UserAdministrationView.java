/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.useradministration;

import ch.burninghammer.vstrophy.webportal.entities.user.User;
import java.util.List;
import org.vaadin.addon.cdimvp.MVPView;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public interface UserAdministrationView extends MVPView {

    public void showUserDetails(User User);

    public void showUserList(List<User> User);
}
