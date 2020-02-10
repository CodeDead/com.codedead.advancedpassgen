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

public final class AboutWindowController {

    @FXML
    private ImageView aboutImageView;

    private HelpUtils helpUtils;

    /**
     * Initialize a new AboutWindowController
     */
    public AboutWindowController() {
        helpUtils = new HelpUtils();
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
    public void CloseAction(ActionEvent actionEvent) {
        final Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Method that is called when the license button is selected
     */
    @FXML
    public void LicenseAction() {
        try {
            helpUtils.openFile("license.pdf", "/documents/license.pdf");
        } catch (IOException ex) {
            FxUtils.showErrorAlert("Error opening license file!", ex.getMessage(), AboutWindowController.class.getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the CodeDead button is selected
     */
    @FXML
    public void CodeDeadAction() {
        helpUtils.openWebsite("https://codedead.com");
    }
}
