package com.codedead.advancedpassgen.domain.controller.ui;

import com.codedead.advancedpassgen.domain.controller.configuration.PropertiesController;
import com.codedead.advancedpassgen.domain.controls.NumberTextField;
import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationProperties;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import com.codedead.advancedpassgen.domain.utils.HelpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public final class MainWindowController {

    @FXML
    private NumberTextField ntfSeed;
    @FXML
    private TextField txtWordListPath;
    @FXML
    private TextField txtCharacterSet;
    @FXML
    private NumberTextField ntfMaxLength;
    @FXML
    private NumberTextField ntfMinLength;
    @FXML
    private CheckBox chbToBase64;
    @FXML
    private CheckBox chbAdvanced;
    @FXML
    private CheckBox chbDuplicates;
    @FXML
    private CheckBox chbBrackets;
    @FXML
    private CheckBox chbSpaces;
    @FXML
    private CheckBox chbNumbers;
    @FXML
    private CheckBox chbSpecialCharacters;
    @FXML
    private CheckBox chbCapitalLetters;
    @FXML
    private CheckBox chbSmallLetters;
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

    private PropertiesController propertiesController;
    private ResourceBundle resourceBundle;

    private final HelpUtils helpUtils;
    private final Logger logger;

    /**
     * Initialize a new MainWindowController
     */
    public MainWindowController() {
        helpUtils = new HelpUtils();
        logger = LoggerFactory.getLogger(MainWindowController.class);
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
     * Reload the current ResourceBundle
     */
    public final void reloadBundle() {
        logger.info("Reloading the resource bundle");
        resourceBundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(propertiesController.getApplicationProperties().getLocale()));
    }

    /**
     * Load the settings into the UI
     */
    private void loadSettings() {
        logger.info("Loading settings");
        if (propertiesController.getApplicationProperties().isSaveOptions()) {
            numLength.setText(String.valueOf(propertiesController.getApplicationProperties().getUserOptions().getLength()));
            numAmount.setText(String.valueOf(propertiesController.getApplicationProperties().getUserOptions().getAmount()));

            chbSmallLetters.setSelected(propertiesController.getApplicationProperties().getUserOptions().isSmallLetters());
            chbCapitalLetters.setSelected(propertiesController.getApplicationProperties().getUserOptions().isCapitalLetters());
            chbSpecialCharacters.setSelected(propertiesController.getApplicationProperties().getUserOptions().isSpecialCharacters());
            chbNumbers.setSelected(propertiesController.getApplicationProperties().getUserOptions().isNumbers());
            chbSpaces.setSelected(propertiesController.getApplicationProperties().getUserOptions().isSpaces());
            chbBrackets.setSelected(propertiesController.getApplicationProperties().getUserOptions().isBrackets());

            chbDuplicates.setSelected(propertiesController.getApplicationProperties().getUserOptions().isAllowDuplicates());
            chbAdvanced.setSelected(propertiesController.getApplicationProperties().getUserOptions().isAdvancedOptions());

            chbToBase64.setSelected(propertiesController.getApplicationProperties().getUserOptions().isToBase64());
            ntfMinLength.setText(String.valueOf(propertiesController.getApplicationProperties().getUserOptions().getMinLength()));
            ntfMaxLength.setText(String.valueOf(propertiesController.getApplicationProperties().getUserOptions().getMaxLength()));
            txtCharacterSet.setText(propertiesController.getApplicationProperties().getUserOptions().getCharacterSet());
        }
    }

    /**
     * Method that is invoked to initialize the controller
     */
    @FXML
    private void initialize() {
        logger.info("Initializing MainWindow");
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
    private void exitAction() {
        System.exit(0);
    }

    /**
     * Method that is called when the AboutWindow should be opened
     */
    @FXML
    private void aboutAction() {
        logger.info("Opening AboutWindow");
        final ApplicationProperties properties = propertiesController.getApplicationProperties();
        try {
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
            logger.error("Error opening AboutWindow", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("aboutWindowError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the home page should be opened
     */
    @FXML
    private void homepageAction() {
        logger.info("Opening CodeDead website");
        helpUtils.openWebsite("https://codedead.com");
    }

    /**
     * Method that is called when the donations page should be opened
     */
    @FXML
    private void donateAction() {
        logger.info("Opening donation website");
        helpUtils.openWebsite("https://codedead.com/?page_id=302");
    }

    /**
     * Method that is called when the license file should be opened
     */
    @FXML
    private void licenseAction() {
        logger.info("Opening the license file");
        try {
            helpUtils.openFileFromResources("license.pdf", "/documents/license.pdf");
        } catch (IOException ex) {
            logger.error("Error opening license file", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("licenseFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the help documentation should be opened
     */
    @FXML
    private void helpAction() {
        logger.info("Opening the help file");
        try {
            helpUtils.openFileFromResources("help.pdf", "/documents/help.pdf");
        } catch (IOException ex) {
            logger.error("Error opening help file", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("helpFileError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when the SettingsWindow should be opened
     */
    @FXML
    private void settingsAction() {
        logger.info("Opening SettingsWindow");
        final ApplicationProperties properties = propertiesController.getApplicationProperties();
        try {
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
            logger.error("Error opening SettingsWindow", ex);
            FxUtils.showErrorAlert(resourceBundle.getString("settingsWindowError"), ex.getMessage(), getClass().getResourceAsStream("/images/key.png"));
        }
    }

    /**
     * Method that is called when a word list file should be selected
     */
    @FXML
    private void chooseWordListAction() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(resourceBundle.getString("selectWordList"));
        final File file = fileChooser.showOpenDialog(txtWordListPath.getScene().getWindow());
        if (file != null && file.exists()) {
            txtWordListPath.setText(file.getAbsolutePath());
        }
    }

    /**
     * Generate a random seed
     */
    @FXML
    private void generateSeedAction() {
        ntfSeed.setText(String.valueOf(new Random().nextLong()));
    }
}
