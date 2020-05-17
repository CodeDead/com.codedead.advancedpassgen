package com.codedead.advancedpassgen;

import com.codedead.advancedpassgen.domain.controller.ui.MainWindowController;
import com.codedead.advancedpassgen.domain.controller.configuration.PropertiesController;
import com.codedead.advancedpassgen.domain.objects.configuration.ApplicationProperties;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JavaFXApplication extends Application {

    private static final String PROP_FILE_LOCATION = "default.properties";
    private static final Logger logger = LoggerFactory.getLogger(JavaFXApplication.class);

    /**
     * Main function
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the JavaFX application
     *
     * @param primaryStage The Stage object that can be used to display UI interaction
     */
    @Override
    public void start(final Stage primaryStage) {
        final PropertiesController propertiesController = new PropertiesController(PROP_FILE_LOCATION, PROP_FILE_LOCATION);
        final File propFile = new File(PROP_FILE_LOCATION);

        if (!propFile.exists()) {
            logger.info("Properties file does not exist");

            try {
                propertiesController.createDefaultProperties();
            } catch (IOException ex) {
                logger.error("Error creating default properties file", ex);
                return;
            }
        }

        // Load the application settings from disk
        try {
            propertiesController.loadAppSettings();
        } catch (IOException ex) {
            logger.error("Error loading application settings", ex);
            return;
        }

        final ApplicationProperties settings = propertiesController.getApplicationProperties();
        final String localeTag = settings.getLocale();

        final ResourceBundle bundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(localeTag));
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"), bundle);
        Parent root;
        try {
            root = loader.load();
        } catch (IOException ex) {
            logger.error("Unable to load FXML file", ex);
            return;
        }

        final MainWindowController mainWindowController = loader.getController();

        mainWindowController.setPropertiesController(propertiesController);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));

        final boolean customSize = settings.isKeepWindowSize();
        double width = 550;
        double height = 350;

        if (customSize) {
            width = settings.getMainWindowWidth();
            height = settings.getMainWindowHeight();
        }

        FxUtils.initializeStage(primaryStage, root, "Advanced PassGen", width, height);
        primaryStage.show();
    }
}
