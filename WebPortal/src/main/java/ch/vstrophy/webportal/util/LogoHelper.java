/*
 * Copyright 2015 Burning Hammer. All rights reserved.
 */
package ch.vstrophy.webportal.util;

import com.vaadin.server.StreamResource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author kobashi@burninghammer.ch
 */
public class LogoHelper {

    public static StreamResource createLogoResource(byte[] data, String name) {
        return new StreamResource(new ByteArrayStreamSource(data), name);
    }

    private static class ByteArrayStreamSource implements StreamResource.StreamSource {

        public byte[] data;

        public ByteArrayStreamSource(final byte[] data) {
            this.data = data;

        }

        @Override
        public InputStream getStream() {
            return new ByteArrayInputStream(data);
        }

    }
}
