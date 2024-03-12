package practica5.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import practica5.modelos.Persona;
import practica5.modelos.celda.ImagenComboBoxCelda;

import java.net.URL;
import java.util.*;

public class PersonaControlador implements Initializable {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private ComboBox<String> imageComboBox;
    @FXML
    private Button aceptarButton;
    @FXML
    private Button cancelarButton;
    @FXML
    private Stage stage;

    private Persona persona;

    private boolean aceptado = false;

    private void inicializarHandlers() {
        aceptarButton.setOnAction(aceptar);
        cancelarButton.setOnAction(cancelar);
        stage.setOnShowing((e) -> aceptado = false);
    }

    private void inicializarComboBox() {
        /* Inicializar Combo Box (Selección de imagen) */
        String[] rutas = {
                "/practica5/recursos/Lloroso.png",
                "/practica5/recursos/Pregunta.png",
                "/practica5/recursos/Sonriente.png",
        };
        ObservableList<String> imgList = FXCollections.observableList(Arrays.asList(rutas));
        imageComboBox.setItems(imgList);
        imageComboBox.setCellFactory(c -> new ImagenComboBoxCelda());
        imageComboBox.setButtonCell(new ImagenComboBoxCelda());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarHandlers();
        inicializarComboBox();
    }
    EventHandler<ActionEvent> aceptar = e -> {
        if (!nombreTextField.getText().isEmpty() && !apellidosTextField.getText().isEmpty()) {
            persona = new Persona(nombreTextField.getText(), apellidosTextField.getText(), imageComboBox.getValue());
            aceptado = true;
        }
        nombreTextField.getScene().getWindow().hide();
    };

    EventHandler<ActionEvent> cancelar = e -> stage.hide();

    public boolean aceptarPulsado() {
        return aceptado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona p) {
        if (p == null) {
            nombreTextField.setText("");
            apellidosTextField.setText("");
            imageComboBox.setValue(null);
        } else {
            nombreTextField.setText(p.getNombre());
            apellidosTextField.setText(p.getApellidos());
            imageComboBox.setValue(p.getRutaImagen());
        }
    }
}