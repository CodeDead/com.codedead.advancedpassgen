package com.codedead.advancedpassgen;

import com.codedead.advancedpassgen.domain.utils.FxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public final class JavaFXApplication extends Application {

    private static String propFile = "default.properties";

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
        final File propFile = new File(JavaFXApplication.propFile);
        if (!propFile.exists()) {
            createDefaultProperties();
        }
        final Properties properties = getProperties();
        String localeTag = properties.getProperty("locale");

        final ResourceBundle bundle = ResourceBundle.getBundle("languages.MainWindow", Locale.forLanguageTag(localeTag));
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow/MainWindow.fxml"), bundle);
        final Parent root = loader.load();

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));

        final boolean customSize = Boolean.parseBoolean(properties.getProperty("keepWindowSize"));
        double width = 550;
        double height = 350;

        if (customSize) {
            width =  Double.parseDouble(properties.getProperty("mainWindowWidth"));
            height = Double.parseDouble(properties.getProperty("mainWindowHeight"));
        }

        FxUtils.initializeStage(primaryStage, root, "Advanced PassGen", width, height);
        primaryStage.show();
    }

    /**
     * Get the Properties object
     *
     * @return The Properties object
     * @throws IOException When the Properties object could not be loaded
     */
    private Properties getProperties() throws IOException {
        try (final InputStream input = new FileInputStream(propFile)) {
            final Properties prop = new Properties();
            prop.load(input);
            return prop;
        }
    }

    /**
     * Create the default properties file from resources
     *
     * @throws IOException When the default properties file could not be read from the application resources
     */
    private void createDefaultProperties() throws IOException {
        try (final InputStream is = this.getClass().getClassLoader().getResourceAsStream(propFile)) {
            if (is != null) {
                Files.copy(is, Paths.get(propFile));
            } else {
                throw new IOException("Could not load default properties from application resources!");
            }
        }
    }
}
