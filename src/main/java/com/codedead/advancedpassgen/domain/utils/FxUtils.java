package com.codedead.advancedpassgen.domain.utils;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public final class FxUtils {

    /**
     * Initialize a new Stage using shared settings
     * @param stage The Stage object that should be initialized
     * @param root   The Parent object to which the Scene is linked
     * @param title  The title of the Stage object
     * @param width  The width of the Stage object
     * @param height The height of the Stage object
     */
    public static void initializeStage(final Stage stage, final Parent root, final String title, final double width, final double height) {
        if (stage == null) throw new NullPointerException("Stage cannot be null!");
        if (root == null) throw new NullPointerException("Parent cannot be null!");
        if (title == null) throw new NullPointerException("Title cannot be null!");

        if (width < 1) throw new IllegalArgumentException("Width cannot be smaller than or equal to zero!");
        if (height < 1) throw new IllegalArgumentException("Height cannot be smaller than or equal to zero!");

        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));

        final Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - width) / 2);
        stage.setY((primScreenBounds.getHeight() - height) / 2);
    }
}
