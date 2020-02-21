package com.codedead.advancedpassgen.domain.controller;

import com.codedead.advancedpassgen.domain.utils.FxUtils;
import com.codedead.advancedpassgen.domain.utils.HelpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public final class AboutWindowController {

    @FXML
    private ImageView aboutImageView;

    private HelpUtils helpUtils;
    private PropertiesController propertiesController;
    private ResourceBundle resourceBundle;

    /**
     * Initialize a new AboutWindowController
     */
    public AboutWindowController() {
        helpUtils = new HelpUtils();
    }

    /**
     * Get the PropertiesController
     * @return The PropertiesController
     */
    public final PropertiesController getPropertiesController() {
        return propertiesController;
    }

    /**
     * Set the PropertiesController
     * @param propertiesController The PropertiesController
     * @throws IOException When the ResourceBundle could not be loaded
     */
    public final void setPropertiesController(final PropertiesController propertiesController) throws IOException {
        if (propertiesController == null) throw new NullPointerException("PropertiesController cannot be null!");

        this.propertiesController = propertiesController;
        reloadBundle();
    }

    public final void reloadBundle() throws IOException {
        resourceBundle = ResourceBundle.getBundle("languages.AboutWindow", Locale.forLanguageTag(propertiesController.getProperties().getProperty("locale")));
    }

    @FXML
    public final void initialize() {
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
        try {
            helpUtils.openFile("license.pdf", "/documents/license.pdf");
        } catch (IOException ex) {
            FxUtils.showErrorAlert(resourceBundle.getString("licenseFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the CodeDead button is selected
     */
    @FXML
    public final void codeDeadAction() {
        helpUtils.openWebsite("https://codedead.com");
    }
}
