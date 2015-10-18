/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.error;

import ch.vstrophy.exception.VSTrophyException;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class WebPortalException extends VSTrophyException {

    public WebPortalException(String message, Throwable ex) {
        super(message, ex);
    }

}
