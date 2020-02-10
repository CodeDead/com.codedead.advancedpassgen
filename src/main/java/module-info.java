module Advanced.PassGen {
    requires javafx.fxml;
    requires javafx.controls;

    opens com.codedead.advancedpassgen.domain.controller to javafx.fxml;

    exports com.codedead.advancedpassgen;
    exports com.codedead.advancedpassgen.domain.controls;
}
