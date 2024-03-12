package practica2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MoverCirculoController implements Initializable {

    @FXML
    private Circle circulo;
    private double xOffset, yOffset;

    @FXML
    private GridPane grid;

    private boolean circuloFill = true;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider sizeSlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        circulo.radiusProperty().bind(sizeSlider.valueProperty());
        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> circulo.requestFocus());

        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (circuloFill) circulo.setFill(newValue);
            else circulo.setStroke(newValue);
        });

        circulo.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
    }

    @FXML
    private void handleMousePressed(MouseEvent e) {
        GridPane grid = (GridPane) e.getSource();
        int col = GridUtils.columnCalc(grid, e.getSceneX());
        int row = GridUtils.rowCalc(grid, e.getSceneY());

        GridPane.setColumnIndex(circulo, col);
        GridPane.setRowIndex(circulo, row);
    }

    @FXML
    private void handleMouseDragged(MouseEvent e) {
        circulo.setTranslateX(e.getSceneX() - xOffset);
        circulo.setTranslateY(e.getSceneY() - yOffset);
    }

    @FXML
    private void handleMouseDraggedExit(MouseEvent e) {
        circulo.setTranslateX(0);
        circulo.setTranslateY(0);
        int col = GridUtils.columnCalc(grid, e.getSceneX());
        int row = GridUtils.rowCalc(grid, e.getSceneY());

        GridPane.setColumnIndex(circulo, col);
        GridPane.setRowIndex(circulo, row);
        e.consume();
    }

    @FXML
    private void handleKeyPressed(KeyEvent e) {
        int[] move = new int[2];
        GridPane grid = (GridPane) e.getSource();

        switch (e.getCode()) {
            case UP -> move[1] = -1;
            case DOWN -> move[1] = 1;
            case LEFT -> move[0] = -1;
            case RIGHT -> move[0] = 1;
            default -> {
                return;
            }
        }

        GridPane.setRowIndex(circulo, GridUtils.rowNorm(grid, GridPane.getRowIndex(circulo) + move[1]));
        GridPane.setColumnIndex(circulo, GridUtils.columnNorm(grid,GridPane.getColumnIndex(circulo) + move[0]));
    }
    @FXML
    private void handleToggleFill() {
        circuloFill = !circuloFill;
        if (circuloFill) {
            circulo.setFill(circulo.getStroke());
            circulo.setStroke(Color.BLACK);
        } else {
            circulo.setStroke(circulo.getFill());
            circulo.setFill(Color.TRANSPARENT);
        }
    }
}