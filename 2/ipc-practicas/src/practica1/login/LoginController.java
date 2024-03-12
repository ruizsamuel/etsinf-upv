package practica1.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField texto_usuario;
    @FXML
    private Text mensaje_usuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void pulsadoIniciar() {
        mensaje_usuario.setText("Bienvenido " + texto_usuario.getText());
    }
}
