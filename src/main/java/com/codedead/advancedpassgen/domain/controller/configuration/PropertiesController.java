package com.codedead.advancedpassgen.domain.controller.configuration;

import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationProperties;
import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

public final class PropertiesController {

    private String fileLocation;
    private String resourceLocation;
    private ApplicationProperties applicationProperties;

    private final Logger logger;

    /**
     * Initialize a new PropertiesController
     *
     * @param fileLocation     The location of the properties file on local storage
     * @param resourceLocation The location of the resource file that contains the default properties
     */
    public PropertiesController(final String fileLocation, final String resourceLocation) {
        setFileLocation(fileLocation);
        setResourceLocation(resourceLocation);
        logger = LoggerFactory.getLogger(PropertiesController.class);
    }

    /**
     * Create the default properties file from resources
     *
     * @throws IOException When the default properties file could not be read from the application resources
     */
    public final void createDefaultProperties() throws IOException {
        logger.info("Creating default properties file");
        try (final InputStream is = getClass().getClassLoader().getResourceAsStream(getResourceLocation())) {
            if (is != null) {
                Files.copy(is, Paths.get(getFileLocation()));
            } else {
                throw new IOException("Could not load default properties from application resources!");
            }
        }
    }

    /**
     * Get the file location of the properties file
     *
     * @return The file location of the properties file
     */
    public final String getFileLocation() {
        return fileLocation;
    }

    /**
     * Set the file location of the properties file
     *
     * @param fileLocation The file location of the properties file
     */
    public final void setFileLocation(final String fileLocation) {
        if (fileLocation == null) throw new NullPointerException("File location cannot be null!");
        if (fileLocation.length() == 0) throw new IllegalArgumentException("File location cannot be empty!");

        this.fileLocation = fileLocation;
    }

    /**
     * Get the resource location of the default properties file
     *
     * @return The resource location of the default properties file
     */
    public final String getResourceLocation() {
        return resourceLocation;
    }

    /**
     * Set the resource location of the default properties file
     *
     * @param resourceLocation The resource location of the default properties file
     */
    public final void setResourceLocation(final String resourceLocation) {
        if (resourceLocation == null) throw new NullPointerException("Default resource location cannot be null!");
        if (resourceLocation.length() == 0)
            throw new IllegalArgumentException("Default resource location cannot be empty!");

        this.resourceLocation = resourceLocation;
    }

    /**
     * Set the ApplicationProperties object
     *
     * @param applicationProperties The ApplicationProperties object
     */
    public final void setApplicationProperties(final ApplicationProperties applicationProperties) {
        if (applicationProperties == null) throw new NullPointerException("ApplicationProperties cannot be null!");

        this.applicationProperties = applicationProperties;
    }

    /**
     * Get the ApplicationProperties object
     *
     * @return The ApplicationProperties object
     */
    public final ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    /**
     * Load the ApplicationProperties object
     *
     * @throws IOException When the Properties could not be loaded
     */
    public final void loadAppSettings() throws IOException {
        logger.info(String.format("Retrieving ApplicationProperties from file %s", getFileLocation()));

        if (getFileLocation() == null)
            throw new NullPointerException("Properties file location cannot be null!");
        if (getFileLocation().trim().length() == 0)
            throw new IllegalArgumentException("Properties file location cannot be empty!");

        try (final InputStream input = new FileInputStream(getFileLocation())) {
            final Properties prop = new Properties();
            prop.load(input);

            final ApplicationProperties settings = new ApplicationProperties();
            final ApplicationVersion version = new ApplicationVersion(2);

            settings.setApplicationVersion(version);
            settings.setAutoUpdate(Boolean.parseBoolean(prop.getProperty("autoUpdate")));
            settings.setLocale(prop.getProperty("locale"));
            settings.setKeepWindowSize(Boolean.parseBoolean(prop.getProperty("keepWindowSize")));
            settings.setSaveOptions(Boolean.parseBoolean(prop.getProperty("saveOptions")));
            settings.setDisplayPasswordStrength(Boolean.parseBoolean(prop.getProperty("displayPasswordStrength")));
            settings.setMainWindowWidth(Double.parseDouble(prop.getProperty("mainWindowWidth")));
            settings.setMainWindowHeight(Double.parseDouble(prop.getProperty("mainWindowHeight")));
            settings.setAboutWindowWidth(Double.parseDouble(prop.getProperty("aboutWindowWidth")));
            settings.setAboutWindowHeight(Double.parseDouble(prop.getProperty("aboutWindowHeight")));
            settings.setSettingsWindowWidth(Double.parseDouble(prop.getProperty("settingsWindowWidth")));
            settings.setSettingsWindowHeight(Double.parseDouble(prop.getProperty("settingsWindowHeight")));
            settings.setDefaultCharacterSet(prop.getProperty("defaultCharacterSet"));
            settings.setExportLength(Boolean.parseBoolean(prop.getProperty("exportLength")));
            settings.setExportStrength(Boolean.parseBoolean(prop.getProperty("exportStrength")));

            setApplicationProperties(settings);
        }
    }

    /**
     * Store the ApplicationProperties object
     *
     * @throws IOException When the Properties could not be stored
     */
    public final void saveAppSettings() throws IOException {
        logger.info(String.format("Saving ApplicationProperties to %s", getFileLocation()));
        try (final OutputStream out = new FileOutputStream(getFileLocation())) {

            final Properties properties = new Properties();

            properties.setProperty("autoUpdate", String.valueOf(applicationProperties.isAutoUpdate()));
            properties.setProperty("locale", applicationProperties.getLocale());
            properties.setProperty("keepWindowSize", String.valueOf(applicationProperties.isKeepWindowSize()));
            properties.setProperty("saveOptions", String.valueOf(applicationProperties.isSaveOptions()));
            properties.setProperty("displayPasswordStrength", String.valueOf(applicationProperties.isDisplayPasswordStrength()));
            properties.setProperty("mainWindowWidth", String.valueOf(applicationProperties.getMainWindowWidth()));
            properties.setProperty("mainWindowHeight", String.valueOf(applicationProperties.getMainWindowHeight()));
            properties.setProperty("aboutWindowWidth", String.valueOf(applicationProperties.getAboutWindowWidth()));
            properties.setProperty("aboutWindowHeight", String.valueOf(applicationProperties.getAboutWindowHeight()));
            properties.setProperty("settingsWindowWidth", String.valueOf(applicationProperties.getSettingsWindowWidth()));
            properties.setProperty("settingsWindowHeight", String.valueOf(applicationProperties.getSettingsWindowHeight()));
            properties.setProperty("defaultCharacterSet", applicationProperties.getDefaultCharacterSet());
            properties.setProperty("exportLength", String.valueOf(applicationProperties.isExportLength()));
            properties.setProperty("exportStrength", String.valueOf(applicationProperties.isExportStrength()));

            properties.store(out, new Date().toString());
        }
    }
}
