package com.codedead.advancedpassgen.domain.utils;

import com.codedead.advancedpassgen.domain.interfaces.IRunnableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class HelpUtils {

    private final Logger logger;

    /**
     * Initialize a new HelpUtils
     */
    public HelpUtils() {
        logger = LoggerFactory.getLogger(HelpUtils.class);
    }

    /**
     * Open the CodeDead website
     *
     * @param url The url that should be opened
     */
    public final void openWebsite(final String url) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Opening website %s", url));
        }

        if (Desktop.isDesktopSupported()) {
            final RunnableSiteOpener siteOpener = new RunnableSiteOpener(url, new IRunnableHelper() {
                @Override
                public final void executed() {
                    // ignored
                }

                @Override
                public final void exceptionOccurred(final Exception ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Unable to open website (%s)", url), ex);
                    }
                }
            });
            new Thread(siteOpener).start();
        } else {
            logger.info("Desktop is not supported on this platform!");
        }
    }

    /**
     * Open a file from resources
     *
     * @param location The location where the file can be found
     * @param resource The resource file where the file can be retrieved if it cannot be found on the disk
     * @throws IOException When the file could not be opened
     */
    public final void openFileFromResources(final String location, final String resource) throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info(String.format("Opening file %s from resources", location));
        }
        if (Desktop.isDesktopSupported()) {
            final Path filePath = Paths.get(location);

            // Check if file already exists
            if (!Files.exists(filePath)) {
                if (logger.isInfoEnabled()) {
                    logger.info(String.format("File (%1$s) does not exist. Creating it from resources (%2$s)", location, resource));
                }

                // Write the file
                try (final InputStream jarPdf = this.getClass().getResourceAsStream(resource)) {
                    if (jarPdf == null) throw new IOException("Resource file not found!");

                    try (final FileOutputStream fos = new FileOutputStream(location)) {
                        fos.write(jarPdf.readAllBytes());
                    }
                }
            }
            final RunnableFileOpener opener = new RunnableFileOpener(location, new IRunnableHelper() {
                @Override
                public final void executed() {
                    // ignored
                }

                @Override
                public final void exceptionOccurred(final Exception ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Unable to open file (%s)", location), ex);
                    }
                }
            });
            new Thread(opener).start();
        } else {
            logger.info("Desktop is not supported on this platform!");
        }
    }
}
