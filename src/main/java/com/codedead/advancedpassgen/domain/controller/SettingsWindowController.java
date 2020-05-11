package com.codedead.advancedpassgen.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public final class SettingsWindowController {

    @FXML
    private TextField txtCharacterSet;
    @FXML
    private CheckBox chbAutoUpdate;
    @FXML
    private CheckBox chbKeepWindowSize;
    @FXML
    private CheckBox chbPasswordStrength;
    @FXML
    private CheckBox chbSaveOptions;
    @FXML
    private Tab tabGeneral;
    @FXML
    private Tab tabAdvanced;

    private PropertiesController propertiesController;
    private ResourceBundle resourceBundle;

    @FXML
    public final void initialize() {
        tabGeneral.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/home.png"))));
        tabAdvanced.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/settings.png"))));
    }

    /**
     * Get the PropertiesController object
     * @return The PropertiesController object
     */
    public final PropertiesController getPropertiesController() {
        return propertiesController;
    }

    /**
     * Set the PropertiesController object
     * @param propertiesController The PropertiesController object
     */
    public final void setPropertiesController(final PropertiesController propertiesController) throws IOException {
        if (propertiesController == null) throw new NullPointerException("PropertiesController cannot be null!");

        this.propertiesController = propertiesController;
        reloadBundle();
        loadSettings();
    }

    /**
     * Reload the ResourceBundle
     * @throws IOException When the ResourceBundle could not be loaded
     */
    public final void reloadBundle() throws IOException {
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getProperties().getProperty("locale")));
    }

    /**
     * Load the settings
     * @throws IOException When the Properties file could not be loaded
     */
    public final void loadSettings() throws IOException {
        final Properties properties = propertiesController.getProperties();
        chbAutoUpdate.setSelected(Boolean.parseBoolean(properties.getProperty("autoUpdate")));
        chbKeepWindowSize.setSelected(Boolean.parseBoolean(properties.getProperty("keepWindowSize")));
        chbPasswordStrength.setSelected(Boolean.parseBoolean(properties.getProperty("displayPasswordStrength")));
        chbSaveOptions.setSelected(Boolean.parseBoolean(properties.getProperty("saveOptions")));
        txtCharacterSet.setText(properties.getProperty("defaultCharacterSet"));
    }
}
