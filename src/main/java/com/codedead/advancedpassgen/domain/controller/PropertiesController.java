package com.codedead.advancedpassgen.domain.controller;

import com.codedead.advancedpassgen.domain.objects.AppSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class PropertiesController {

    private String fileLocation;
    private String resourceLocation;

    /**
     * Initialize a new PropertiesController
     *
     * @param fileLocation     The location of the properties file on local storage
     * @param resourceLocation The location of the resource file that contains the default properties
     */
    public PropertiesController(final String fileLocation, final String resourceLocation) {
        setFileLocation(fileLocation);
        setResourceLocation(resourceLocation);
    }

    /**
     * Create the default properties file from resources
     *
     * @throws IOException When the default properties file could not be read from the application resources
     */
    public final void createDefaultProperties() throws IOException {
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
     * Get the AppSettings object
     *
     * @return The AppSettings object
     * @throws IOException When the Properties could not be loaded
     */
    public final AppSettings getAppSettings() throws IOException {
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

            return appSettings;
        }
    }

    /**
     * Store the AppSettings object
     *
     * @param appSettings The AppSettings object that should be stored
     * @throws IOException When the Properties could not be stored
     */
    public final void saveAppSettings(final AppSettings appSettings) throws IOException {
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
