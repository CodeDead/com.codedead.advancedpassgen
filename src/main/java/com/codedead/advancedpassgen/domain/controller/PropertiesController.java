package com.codedead.advancedpassgen.domain.controller;

import com.codedead.advancedpassgen.domain.objects.AppSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesController {

    private String fileLocation;
    private String resourceLocation;
    private AppSettings appSettings;

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
        logger = LogManager.getLogger(PropertiesController.class);
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
     * Set the AppSettings object
     *
     * @param appSettings The AppSettings object
     */
    public final void setAppSettings(final AppSettings appSettings) {
        if (appSettings == null) throw new NullPointerException("AppSettings cannot be null!");

        this.appSettings = appSettings;
    }

    /**
     * Get the AppSettings object
     *
     * @return The AppSettings object
     */
    public final AppSettings getAppSettings() {
        return appSettings;
    }

    /**
     * Load the AppSettings object
     *
     * @throws IOException When the Properties could not be loaded
     */
    public final void loadAppSettings() throws IOException {
        logger.info(String.format("Retrieving AppSettings from file %s", getFileLocation()));

        if (getFileLocation() == null)
            throw new NullPointerException("Properties file location cannot be null!");
        if (getFileLocation().trim().length() == 0)
            throw new IllegalArgumentException("Properties file location cannot be empty!");

        try (final InputStream input = new FileInputStream(getFileLocation())) {
            final Properties prop = new Properties();
            prop.load(input);

            final AppSettings appSettings = new AppSettings();

            appSettings.setAutoUpdate(Boolean.parseBoolean(prop.getProperty("autoUpdate")));
            appSettings.setLocale(prop.getProperty("locale"));
            appSettings.setKeepWindowSize(Boolean.parseBoolean(prop.getProperty("keepWindowSize")));
            appSettings.setSaveOptions(Boolean.parseBoolean(prop.getProperty("saveOptions")));
            appSettings.setDisplayPasswordStrength(Boolean.parseBoolean(prop.getProperty("displayPasswordStrength")));
            appSettings.setMainWindowWidth(Double.parseDouble(prop.getProperty("mainWindowWidth")));
            appSettings.setMainWindowHeight(Double.parseDouble(prop.getProperty("mainWindowHeight")));
            appSettings.setAboutWindowWidth(Double.parseDouble(prop.getProperty("aboutWindowWidth")));
            appSettings.setAboutWindowHeight(Double.parseDouble(prop.getProperty("aboutWindowHeight")));
            appSettings.setSettingsWindowWidth(Double.parseDouble(prop.getProperty("settingsWindowWidth")));
            appSettings.setSettingsWindowHeight(Double.parseDouble(prop.getProperty("settingsWindowHeight")));
            appSettings.setDefaultCharacterSet(prop.getProperty("defaultCharacterSet"));

            setAppSettings(appSettings);
        }
    }

    /**
     * Store the AppSettings object
     *
     * @throws IOException When the Properties could not be stored
     */
    public final void saveAppSettings() throws IOException {
        logger.info(String.format("Saving AppSettings to %s", getFileLocation()));
        try (final OutputStream out = new FileOutputStream(getFileLocation())) {

            final Properties properties = new Properties();

            properties.setProperty("autoUpdate", String.valueOf(appSettings.isAutoUpdate()));
            properties.setProperty("locale", appSettings.getLocale());
            properties.setProperty("keepWindowSize", String.valueOf(appSettings.isKeepWindowSize()));
            properties.setProperty("saveOptions", String.valueOf(appSettings.isSaveOptions()));
            properties.setProperty("displayPasswordStrength", String.valueOf(appSettings.isDisplayPasswordStrength()));
            properties.setProperty("mainWindowWidth", String.valueOf(appSettings.getMainWindowWidth()));
            properties.setProperty("mainWindowHeight", String.valueOf(appSettings.getMainWindowHeight()));
            properties.setProperty("aboutWindowWidth", String.valueOf(appSettings.getAboutWindowWidth()));
            properties.setProperty("aboutWindowHeight", String.valueOf(appSettings.getAboutWindowHeight()));
            properties.setProperty("settingsWindowWidth", String.valueOf(appSettings.getSettingsWindowWidth()));
            properties.setProperty("settingsWindowHeight", String.valueOf(appSettings.getSettingsWindowHeight()));
            properties.setProperty("defaultCharacterSet", appSettings.getDefaultCharacterSet());

            properties.store(out, null);
        }
    }
}
