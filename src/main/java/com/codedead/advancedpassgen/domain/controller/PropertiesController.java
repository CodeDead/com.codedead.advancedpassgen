package com.codedead.advancedpassgen.domain.controller;

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
     * Get the Properties object
     *
     * @return The Properties object
     * @throws IOException When the Properties object could not be loaded
     */
    public final Properties getProperties() throws IOException {
        try (final InputStream input = new FileInputStream(getFileLocation())) {
            final Properties prop = new Properties();
            prop.load(input);
            return prop;
        }
    }

    /**
     * Store the properties
     *
     * @param properties The properties that should be stored
     * @throws IOException When the Properties could not be stored
     */
    public final void setProperties(final Properties properties) throws IOException {
        try (final OutputStream out = new FileOutputStream(getFileLocation())) {
            properties.store(out, null);
        }
    }
}
