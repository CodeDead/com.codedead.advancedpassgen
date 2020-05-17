package com.codedead.advancedpassgen.domain.controller.ui;

import com.codedead.advancedpassgen.domain.controller.PropertiesController;
import com.codedead.advancedpassgen.domain.controls.NumberTextField;
import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationProperties;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import com.codedead.advancedpassgen.domain.utils.HelpUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public final class MainWindowController {

    private PropertiesController propertiesController;
    private ResourceBundle resourceBundle;

    private HelpUtils helpUtils;

    @FXML
    private NumberTextField numAmount;
    @FXML
    private NumberTextField numLength;
    @FXML
    private Tab tabList;
    @FXML
    private Tab tabAdvisor;
    @FXML
    private Tab tabAdvanced;
    @FXML
    private Tab tabOptions;
    @FXML
    private MenuItem mniHelp;
    @FXML
    private MenuItem mniUpdate;
    @FXML
    private MenuItem mniHomepage;
    @FXML
    private MenuItem mniLicense;
    @FXML
    private MenuItem mniDonate;
    @FXML
    private MenuItem mniAbout;
    @FXML
    private MenuItem mniSettings;
    @FXML
    private MenuItem mniExit;
    @FXML
    private MenuItem mniExport;

    /**
     * Initialize a new MainWindowController
     */
    public MainWindowController() {
        helpUtils = new HelpUtils();
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
    }

    /**
     * Reload the current ResourceBundle
     */
    public final void reloadBundle() {
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getApplicationProperties().getLocale()));
    }

    /**
     * Method that is invoked to initialize the controller
     */
    @FXML
    public void initialize() {
        // Menu items
        mniExport.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/export.png"))));
        mniExit.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/remove.png"))));
        mniSettings.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/settings.png"))));
        mniAbout.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/about.png"))));
        mniDonate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/donate.png"))));
        mniLicense.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/license.png"))));
        mniHomepage.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/home.png"))));
        mniUpdate.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/update.png"))));
        mniHelp.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/help.png"))));
        // Tab items
        tabOptions.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/home.png"))));
        tabAdvanced.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/settings.png"))));
        tabList.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/list.png"))));
        tabAdvisor.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/advisor.png"))));
    }

    /**
     * Method that is called when the Exit menu item is selected
     */
    @FXML
    public final void exitAction() {
        System.exit(0);
    }

    /**
     * Method that is called when the AboutWindow should be opened
     */
    @FXML
    public final void aboutAction() {
        try {
            final ApplicationProperties properties = propertiesController.getApplicationProperties();

            final ResourceBundle bundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(properties.getLocale()));
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AboutWindow.fxml"), bundle);
            final Parent root = loader.load();

            final AboutWindowController aboutWindowController = loader.getController();
            aboutWindowController.setPropertiesController(propertiesController);

            final double width = properties.getAboutWindowWidth();
            final double height = properties.getAboutWindowHeight();

            final Stage primaryStage = new Stage();
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));
            FxUtils.initializeStage(primaryStage, root, "Advanced PassGen - " + bundle.getString("about.title"), width, height);

            primaryStage.show();
        } catch (IOException | NumberFormatException ex) {
            FxUtils.showErrorAlert(resourceBundle.getString("aboutWindowError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the home page should be opened
     */
    @FXML
    public void homepageAction() {
        helpUtils.openWebsite("https://codedead.com");
    }

    /**
     * Method that is called when the donations page should be opened
     */
    @FXML
    public void donateAction() {
        helpUtils.openWebsite("https://codedead.com/?page_id=302");
    }

    /**
     * Method that is called when the license file should be opened
     */
    @FXML
    public void licenseAction() {
        try {
            helpUtils.openFile("license.pdf", "/documents/license.pdf");
        } catch (IOException ex) {
            FxUtils.showErrorAlert(resourceBundle.getString("licenseFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the help documentation should be opened
     */
    @FXML
    public void helpAction() {
        try {
            helpUtils.openFile("help.pdf", "/documents/help.pdf");
        } catch (IOException ex) {
            FxUtils.showErrorAlert(resourceBundle.getString("helpFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the SettingsWindow should be opened
     */
    public void settingsAction() {
        try {
            final ApplicationProperties properties = propertiesController.getApplicationProperties();
            final ResourceBundle bundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(properties.getLocale()));
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingsWindow.fxml"), bundle);
            final Parent root = loader.load();

            final SettingsWindowController settingsWindowController = loader.getController();
            settingsWindowController.setPropertiesController(propertiesController);

            final double width = properties.getSettingsWindowWidth();
            final double height = properties.getSettingsWindowHeight();

            final Stage primaryStage = new Stage();
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));
            FxUtils.initializeStage(primaryStage, root, "Advanced PassGen - " + bundle.getString("settings.title"), width, height);

            primaryStage.show();
        } catch (IOException ex) {
            FxUtils.showErrorAlert(resourceBundle.getString("settingsWindowError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }
}
