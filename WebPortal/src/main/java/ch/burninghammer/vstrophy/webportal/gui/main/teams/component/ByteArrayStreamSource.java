/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.burninghammer.vstrophy.webportal.gui.main.teams.component;

import com.vaadin.server.StreamResource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class ByteArrayStreamSource implements StreamResource.StreamSource {

    public byte[] data;

    public ByteArrayStreamSource(final byte[] data) {
        this.data = data;

    }

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(data);
    }

}
