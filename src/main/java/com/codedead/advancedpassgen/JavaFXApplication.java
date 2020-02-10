package com.codedead.advancedpassgen;

import com.codedead.advancedpassgen.domain.utils.FxUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public final class JavaFXApplication extends Application {

    /**
     * Main function
     * @param args Program arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final FXMLLoader loader = new FXMLLoader();
        try {
            final Parent root = loader.load(getClass().getResource("/views/MainWindow.fxml").openStream());
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/key.png")));

            final double width = 550;
            final double height = 300;

            FxUtils.initializeStage(primaryStage, root, "Advanced PassGen", width, height);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
