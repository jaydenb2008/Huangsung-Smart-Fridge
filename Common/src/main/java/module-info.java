module Common {
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;

    exports edu.sdccd.cisc191.common to Client, Server, Common;
}