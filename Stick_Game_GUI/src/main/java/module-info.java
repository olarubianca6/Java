module com.company.stick_game_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.company.stick_game_gui to javafx.fxml;
    exports com.company.stick_game_gui;
}