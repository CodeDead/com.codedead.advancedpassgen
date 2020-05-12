package com.codedead.advancedpassgen.domain.controller;

import com.codedead.advancedpassgen.domain.objects.AppSettings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public final class SettingsWindowController {

    @FXML
    private ComboBox<String> cboLanguage;
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
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getAppSettings().getLocale()));
    }

    /**
     * Load the settings
     * @throws IOException When the Properties file could not be loaded
     */
    public final void loadSettings() throws IOException {
        final AppSettings properties = propertiesController.getAppSettings();
        chbAutoUpdate.setSelected(properties.isAutoUpdate());
        chbKeepWindowSize.setSelected(properties.isKeepWindowSize());
        chbPasswordStrength.setSelected(properties.isDisplayPasswordStrength());
        chbSaveOptions.setSelected(properties.isSaveOptions());
        txtCharacterSet.setText(properties.getDefaultCharacterSet());
        cboLanguage.getSelectionModel().select(properties.getLanguageIndex());
    }
}
