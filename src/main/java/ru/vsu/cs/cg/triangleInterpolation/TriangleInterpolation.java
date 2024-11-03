package ru.vsu.cs.cg.triangleInterpolation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TriangleInterpolation extends Application {

    private static final double[] A = {100, 100};
    private static final double[] B = {100, 300};
    private static final double[] C = {300, 300};


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Triangle Interpolation");

        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        interpolation(gc);

        Group root = new Group();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    private void interpolation(GraphicsContext gc) {
        Color colorA = Color.RED;
        Color colorB = Color.GREEN;
        Color colorC = Color.BLUE;

        double minX = Math.min(A[0], Math.min(B[0], C[0]));
        double maxX = Math.max(A[0], Math.max(B[0], C[0]));
        double minY = Math.min(A[1], Math.min(B[1], C[1]));
        double maxY = Math.max(A[1], Math.max(B[1], C[1]));

        for (double x = minX; x <= maxX; x++) {
            for (double y = minY; y <= maxY; y++) {
                double[] barycentric = barycentricCoordinates(x + .5, y + .5);

                if (barycentric != null) {
                    double r = barycentric[0] * colorA.getRed() + barycentric[1] * colorB.getRed() + barycentric[2] * colorC.getRed();
                    double g = barycentric[0] * colorA.getGreen() + barycentric[1] * colorB.getGreen() + barycentric[2] * colorC.getGreen();
                    double b = barycentric[0] * colorA.getBlue() + barycentric[1] * colorB.getBlue() + barycentric[2] * colorC.getBlue();

                    gc.getPixelWriter().setColor((int) x, (int) y,
                            new Color(r, g, b, 1.0));
                }
            }
        }
    }

    private double[] barycentricCoordinates(double x, double y) {
        double denominator = (B[1] - C[1]) * (A[0] - C[0]) + (C[0] - B[0]) * (A[1] - C[1]);

        double alpha = ((B[1] - C[1]) * (x - C[0]) + (C[0] - B[0]) * (y - C[1])) / denominator;
        double beta = ((C[1] - A[1]) * (x - C[0]) + (A[0] - C[0]) * (y - C[1])) / denominator;
        double gamma = 1 - alpha - beta;

        if (alpha < 0 || beta < 0 || gamma < 0)
            return null;

        return new double[]{ alpha, beta, gamma };
    }
}