package practica1.tresEnRaya;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TresEnRayaController implements Initializable {

    @FXML
    private GridPane tablero;
    @FXML
    private Text mensajesText;

    private int turno;
    private static final String[] turnosLabel = {"X", "O"};

    private boolean iniciado;

    private final int[][] matriz = {{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};

    static class CeldaTablero extends Button {

        int col, row;
        public CeldaTablero(int col, int row) {
            super("");
            this.col = col;
            this.row = row;
            setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }

        public int getCol() { return col; }
        public int getRow() { return row; }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                CeldaTablero celda = new CeldaTablero(col, row);
                celda.setOnMousePressed(this::celdaPressedHandler);
                tablero.add(celda, col, row);
            }
        }
    }

    private boolean verificarTablero(int col, int row) {
        if (matriz[0][row] == matriz[1][row] && matriz[0][row] == matriz[2][row]) return true;
        if (matriz[col][0] == matriz[col][1] && matriz[col][0] == matriz[col][2]) return true;
        if (col == row)
            return ((matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2]) ||
                    (col == 1)) && matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0];
        if ((col == 0 && row == 2) || (col == 2 && row == 0))
            return (matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0]);
        return false;
    }

    private void terminarJuego() {
        iniciado = false;
        mensajesText.setText("Ha ganado " + turnosLabel[turno]);
    }

    private void celdaPressedHandler(MouseEvent e) {
        CeldaTablero b = (CeldaTablero) e.getSource();
        if (!iniciado || !b.getText().isEmpty()) return;
        b.setText(turnosLabel[turno]);
        matriz[b.getCol()][b.getRow()] = turno;
        if (!verificarTablero(b.getCol(), b.getRow())){
            turno = (turno + 1) % 2;
            mensajesText.setText("Turno: " + turnosLabel[turno]);
        } else terminarJuego();
    }

    @FXML
    private void iniciarHandler() {
        iniciado = true;
        turno = 0;
        mensajesText.setText("Turno: X");
        //TODO: Poder reiniciar (limpiar botones)
    }
}
