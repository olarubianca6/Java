module com.company.documentmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;


    opens com.company to javafx.fxml;
    exports com.company;
}