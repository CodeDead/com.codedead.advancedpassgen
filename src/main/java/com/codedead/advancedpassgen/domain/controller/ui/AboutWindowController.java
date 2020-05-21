package com.codedead.advancedpassgen.domain.controller.ui;

import com.codedead.advancedpassgen.domain.controller.configuration.PropertiesController;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import com.codedead.advancedpassgen.domain.utils.HelpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public final class AboutWindowController {

    @FXML
    private Label aboutLabel;
    @FXML
    private ImageView aboutImageView;

    private final HelpUtils helpUtils;
    private PropertiesController propertiesController;
    private ResourceBundle resourceBundle;

    private final Logger logger;

    /**
     * Initialize a new AboutWindowController
     */
    public AboutWindowController() {
        helpUtils = new HelpUtils();
        logger = LoggerFactory.getLogger(AboutWindowController.class);
    }

    /**
     * Get the PropertiesController
     *
     * @return The PropertiesController
     */
    public final PropertiesController getPropertiesController() {
        return propertiesController;
    }

    /**
     * Set the PropertiesController
     *
     * @param propertiesController The PropertiesController
     */
    public final void setPropertiesController(final PropertiesController propertiesController) {
        if (propertiesController == null) throw new NullPointerException("PropertiesController cannot be null!");

        this.propertiesController = propertiesController;
        reloadBundle();
        aboutLabel.setText(aboutLabel.getText().replace("{x}", propertiesController.getApplicationProperties().getApplicationVersion().toString()));
    }

    /**
     * Reload the resource bundle
     */
    public final void reloadBundle() {
        logger.info("Reloading the resource bundle");
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getApplicationProperties().getLocale()));
    }

    /**
     * Method that is invoked to initialize the controller
     */
    @FXML
    public final void initialize() {
        logger.info("Initializing AboutWindow");
        aboutImageView.setFitHeight(96);
        aboutImageView.setFitWidth(96);
        aboutImageView.setImage(new Image(getClass().getResourceAsStream("/images/key.png")));
    }

    /**
     * Method that is called when the close button is selected
     *
     * @param actionEvent The object that called this method
     */
    @FXML
    public final void closeAction(final ActionEvent actionEvent) {
        final Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Method that is called when the license button is selected
     */
    @FXML
    public final void licenseAction() {
        logger.info("Opening the license file");
        try {
            helpUtils.openFileFromResources("license.pdf", "/documents/license.pdf");
        } catch (IOException ex) {
            logger.error("Error opening license file", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("licenseFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the CodeDead button is selected
     */
    @FXML
    public final void codeDeadAction() {
        logger.info("Opening CodeDead website");
        helpUtils.openWebsite("https://codedead.com");
    }
}
