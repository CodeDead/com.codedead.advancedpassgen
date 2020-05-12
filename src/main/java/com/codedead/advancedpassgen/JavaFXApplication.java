package com.codedead.advancedpassgen;

import com.codedead.advancedpassgen.domain.controller.MainWindowController;
import com.codedead.advancedpassgen.domain.controller.PropertiesController;
import com.codedead.advancedpassgen.domain.objects.AppSettings;
import com.codedead.advancedpassgen.domain.utils.FxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public final class JavaFXApplication extends Application {

    private static final String PROP_FILE_LOCATION = "default.properties";

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
    public void start(final Stage primaryStage) throws IOException {
        final PropertiesController propertiesController = new PropertiesController(PROP_FILE_LOCATION, PROP_FILE_LOCATION);
        final File propFile = new File(JavaFXApplication.PROP_FILE_LOCATION);

        if (!propFile.exists()) {
            propertiesController.createDefaultProperties();
        }

        final AppSettings settings = propertiesController.getAppSettings();
        final String localeTag = settings.getLocale();

        final ResourceBundle bundle = ResourceBundle.getBundle("languages.translations", Locale.forLanguageTag(localeTag));
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"), bundle);
        final Parent root = loader.load();
        final MainWindowController mainWindowController = loader.getController();

        mainWindowController.setPropertiesController(propertiesController);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));

        final boolean customSize = settings.isKeepWindowSize();
        double width = 550;
        double height = 350;

        if (customSize) {
            width =  settings.getMainWindowWidth();
            height = settings.getMainWindowHeight();
        }

        FxUtils.initializeStage(primaryStage, root, "Advanced PassGen", width, height);
        primaryStage.show();
    }
}
