module cs1622.projecttwo.pacifistgeometrygame {
    requires javafx.controls;
    requires javafx.fxml;


    opens cs1622.projecttwo.pacifistgeometrygame to javafx.fxml;
    exports cs1622.projecttwo.pacifistgeometrygame;
}