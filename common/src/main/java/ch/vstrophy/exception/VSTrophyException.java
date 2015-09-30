/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.exception;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class VSTrophyException extends Exception {

    public VSTrophyException(String message) {
        super(message);
    }

    public VSTrophyException(String message, Throwable cause) {
        super(message, cause);
    }

    public VSTrophyException(Throwable cause) {
        super(cause);
    }

}
