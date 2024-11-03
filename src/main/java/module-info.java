module ru.vsu.cs.cg.trianglefillinterpolation {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.cs.cg.triangleInterpolation to javafx.fxml;
    exports ru.vsu.cs.cg.triangleInterpolation;
}