package practica4.controladores.tarea2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import practica4.modelos.Persona;

import java.net.URL;
import java.util.ResourceBundle;

public class VistaPersonaControlador implements Initializable {

    private boolean aceptado = false;
    private Persona persona;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persona = new Persona("", "");
    }

    @FXML
    void aceptar() {
        if (!nombreTextField.getText().isEmpty() && !apellidosTextField.getText().isEmpty()) {
            persona.setNombre(nombreTextField.getText());
            persona.setApellidos(apellidosTextField.getText());
            aceptado = true;
        }
        nombreTextField.getScene().getWindow().hide();
    }

    @FXML
    void cancelar() {
        nombreTextField.getScene().getWindow().hide();
    }

    public boolean aceptarPulsado() {
        return aceptado;
    }

    public Persona getNueva() {
        return persona;
    }

    public void setPersona(Persona p) {
        nombreTextField.setText(p.getNombre());
        apellidosTextField.setText(p.getApellidos());
    }
}
