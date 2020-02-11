package com.codedead.advancedpassgen.domain.utils;

import com.codedead.advancedpassgen.domain.interfaces.IRunnableHelper;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class HelpUtils {

    /**
     * Initialize a new HelpUtils
     */
    public HelpUtils() {

    }

    /**
     * Open the CodeDead website
     *
     * @param url The url that should be opened
     */
    public final void openWebsite(final String url) {
        if (Desktop.isDesktopSupported()) {
            final RunnableSiteOpener siteOpener = new RunnableSiteOpener(url, new IRunnableHelper() {
                @Override
                public final void executed() {

                }

                @Override
                public final void exceptionOccurred(Exception ex) {

                }
            });
            new Thread(siteOpener).start();
        }
    }

    /**
     * Open a file (from resources)
     *
     * @param location The location where the file can be found
     * @param resource The resource file where the file can be retrieved if it cannot be found on the disk
     * @throws IOException When the file could not be opened
     */
    public final void openFile(final String location, final String resource) throws IOException {
        if (Desktop.isDesktopSupported()) {
            final File file = new File(location);

            // Check if PDF file already exists
            if (!file.exists()) {
                // Write the file as a temporary file
                try (final InputStream jarPdf = this.getClass().getResourceAsStream(resource)) {
                    if (jarPdf == null) throw new IOException("File not found!");

                    try (final FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(jarPdf.readAllBytes());
                    }
                }
            }
            final RunnableFileOpener opener = new RunnableFileOpener(file, new IRunnableHelper() {
                @Override
                public final void executed() {

                }

                @Override
                public final void exceptionOccurred(Exception ex) {

                }
            });
            new Thread(opener).start();
        }
    }

}
