module AdvancedPassGen {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires org.apache.logging.log4j;

    opens com.codedead.advancedpassgen.domain.controller to javafx.fxml;

    exports com.codedead.advancedpassgen;
    exports com.codedead.advancedpassgen.domain.controls;
}
