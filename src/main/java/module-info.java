module ru.vsu.cs.cg.trianglefillinterpolation {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.cs.cg to javafx.fxml;
    exports ru.vsu.cs.cg;
}