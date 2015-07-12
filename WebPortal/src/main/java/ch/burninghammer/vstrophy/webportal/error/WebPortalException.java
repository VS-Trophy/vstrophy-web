/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.error;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class WebPortalException extends Exception {

    public WebPortalException(String message, Throwable ex) {
        super(message, ex);
    }

}
