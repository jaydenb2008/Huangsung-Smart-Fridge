module Client {
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.controls;
    requires Common;
    requires org.testfx;

    exports edu.sdccd.cisc191.client to javafx.graphics;
}
