module cs1622.projecttwo.pacifistgeometrygame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens cs1622.projecttwo.pacifistgeometrygame to javafx.fxml;
    exports cs1622.projecttwo.pacifistgeometrygame;
}