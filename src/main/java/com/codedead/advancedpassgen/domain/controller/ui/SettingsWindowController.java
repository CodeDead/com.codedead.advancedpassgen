package com.codedead.advancedpassgen.domain.controller.ui;

import com.codedead.advancedpassgen.domain.controller.configuration.PropertiesController;
import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationProperties;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public final class SettingsWindowController {

    @FXML
    private TextField txtCsvDelimiter;
    @FXML
    private CheckBox chbExportLength;
    @FXML
    private CheckBox chbExportStrength;
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

    private final Logger logger;

    /**
     * Initialize a new SettingsWindowController
     */
    public SettingsWindowController() {
        logger = LoggerFactory.getLogger(SettingsWindowController.class);
    }

    /**
     * Method that is invoked to initialize the controller
     */
    @FXML
    private void initialize() {
        logger.info("Initializing SettingsWindow");
        tabGeneral.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/home.png"))));
        tabAdvanced.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/settings.png"))));
    }

    /**
     * Get the PropertiesController object
     *
     * @return The PropertiesController object
     */
    public final PropertiesController getPropertiesController() {
        return propertiesController;
    }

    /**
     * Set the PropertiesController object
     *
     * @param propertiesController The PropertiesController object
     */
    public final void setPropertiesController(final PropertiesController propertiesController) {
        if (propertiesController == null) throw new NullPointerException("PropertiesController cannot be null!");

        this.propertiesController = propertiesController;
        reloadBundle();
        loadSettings();
    }

    /**
     * Reload the ResourceBundle
     */
    public final void reloadBundle() {
        logger.info("Reloading the resource bundle");
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getApplicationProperties().getLocale()));
    }

    /**
     * Load the settings
     */
    public final void loadSettings() {
        logger.info("Loading settings");
        final ApplicationProperties properties = propertiesController.getApplicationProperties();
        chbAutoUpdate.setSelected(properties.isAutoUpdate());
        chbKeepWindowSize.setSelected(properties.isKeepWindowSize());
        chbPasswordStrength.setSelected(properties.isDisplayPasswordStrength());
        chbSaveOptions.setSelected(properties.isSaveOptions());
        txtCharacterSet.setText(properties.getUserOptions().getCharacterSet());
        cboLanguage.getSelectionModel().select(properties.getLanguageIndex());
        chbExportLength.setSelected(properties.isExportLength());
        chbExportStrength.setSelected(properties.isExportStrength());
        txtCsvDelimiter.setText(properties.getCsvDelimiter());
    }

    /**
     * Save the settings
     */
    @FXML
    private void saveAction() {
        final ApplicationProperties applicationProperties = propertiesController.getApplicationProperties();

        applicationProperties.setAutoUpdate(chbAutoUpdate.isSelected());
        applicationProperties.setKeepWindowSize(chbKeepWindowSize.isSelected());
        applicationProperties.setDisplayPasswordStrength(chbPasswordStrength.isSelected());
        applicationProperties.setSaveOptions(chbSaveOptions.isSelected());
        applicationProperties.getUserOptions().setCharacterSet(txtCharacterSet.getText());
        applicationProperties.setLanguageIndex(cboLanguage.getSelectionModel().getSelectedIndex());
        applicationProperties.setExportLength(chbExportLength.isSelected());
        applicationProperties.setExportStrength(chbExportStrength.isSelected());
        applicationProperties.setCsvDelimiter(txtCsvDelimiter.getText());

        try {
            propertiesController.saveApplicationProperties();
        } catch (IOException ex) {
            logger.error("Unable to save ApplicationProperties", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("savePropertiesError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }
}
