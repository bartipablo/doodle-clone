module com.developerex.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.developerex.client to javafx.fxml;
    exports com.developerex.client;
}