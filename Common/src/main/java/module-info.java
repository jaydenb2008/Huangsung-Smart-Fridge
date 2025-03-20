module Common {
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires java.desktop;

    exports edu.sdccd.cisc191.template to javafx.graphics;
}