package com.codedead.advancedpassgen.domain.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class MainWindowController {

    @FXML
    private MenuItem MniHelp;
    @FXML
    private MenuItem MniUpdate;
    @FXML
    private MenuItem MniHomepage;
    @FXML
    private MenuItem MniLicense;
    @FXML
    private MenuItem MniDonate;
    @FXML
    private MenuItem MniAbout;
    @FXML
    private MenuItem MniSettings;
    @FXML
    private MenuItem MniExit;
    @FXML
    private MenuItem MniExport;

    @FXML
    public void initialize() {
        MniExport.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/export.png"))));
        MniExit.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/remove.png"))));
        MniSettings.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/settings.png"))));
        MniAbout.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/about.png"))));
        MniDonate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/donate.png"))));
        MniLicense.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/license.png"))));
        MniHomepage.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/home.png"))));
        MniUpdate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/update.png"))));
        MniHelp.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/help.png"))));
    }
}
