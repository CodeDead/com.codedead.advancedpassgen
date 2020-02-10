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

    @SuppressWarnings("SpellCheckingInspection")
    private static String PROPFILE = "default.properties";

    /**
     * Main function
     * @param args Program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String localeTag = "en-US";

        try {
            final File propFile = new File(PROPFILE);
            if (!propFile.exists()) {
                createDefaultProperties();
            }
            final Properties properties = getProperties();
            localeTag = properties.getProperty("locale");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            final ResourceBundle bundle = ResourceBundle.getBundle("languages.MainWindow", Locale.forLanguageTag(localeTag));
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"), bundle);
            final Parent root = loader.load();

            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));

            final double width = 550;
            final double height = 300;

            FxUtils.initializeStage(primaryStage, root, "Advanced PassGen", width, height);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties getProperties() throws IOException {
        try (InputStream input = new FileInputStream(PROPFILE)) {
            Properties prop = new Properties();

            prop.load(input);
            return prop;
        }
    }

    private void createDefaultProperties() throws IOException {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPFILE)) {
            if (is != null) {
                Files.copy(is, Paths.get(PROPFILE));
            } else {
                throw new NullPointerException("Could not load default properties from application resources!");
            }
        }
    }
}
