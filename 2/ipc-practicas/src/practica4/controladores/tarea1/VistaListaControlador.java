package practica4.controladores.tarea1;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import practica4.modelos.CeldaPersona;
import practica4.modelos.Persona;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VistaListaControlador implements Initializable {
    //=========================================================
    @FXML
    private ListView<Persona> personasListView;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private Button addButton;
    @FXML
    private Button borrarButton;

    //=========================================================
    // DEBEN coincidir los tipo del ListView y de la lista observable
    private ObservableList<Persona> datos = null; // Colección vinculada a la vista.

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // en el código de inicialización del controlador
        ArrayList<Persona> misDatos = new ArrayList<>();
        misDatos.add(new Persona("Pepe", "García"));
        misDatos.add(new Persona("María", "Pérez"));

        //=======================================================
        // creamos la lista observable mediante un metodo de FXCollections
        datos = FXCollections.observableList(misDatos);

        //=======================================================
        //=======================================================
        // vinculamos la lista observables de personas con el ListView
        personasListView.setItems(datos);

        //=======================================================
        //=======================================================
        // Hay que modificar CellFactory para mostrar el objeto Persona
        personasListView.setCellFactory(c -> new CeldaPersona());

        //=======================================================
        // inhabilitar botones al arrancar.
        addButton.setDisable(true);
        // si no hay selección el botón Borrar esta disabled
        borrarButton.disableProperty().bind(Bindings.equal(personasListView.getSelectionModel().selectedIndexProperty(), -1));

        // oyente de foco para el textfield.
        nombreTextField.focusedProperty().addListener((a, b, c) -> {
            // TODO Auto-generated method stub
            if (nombreTextField.isFocused()) {
                addButton.setDisable(false);
                personasListView.getSelectionModel().select(-1);
            }
        });

        // oyente de foco para el listView
        personasListView.focusedProperty().addListener((a, b, c) -> {
            if (personasListView.isFocused()) {
                addButton.setDisable(true);
            }
        });

    }

    @FXML
    void addAccion() {
        // añade a la colección si los campos no son vacíos y no contienen únicamente blancos
        if ((!nombreTextField.getText().isEmpty())
                && (!nombreTextField.getText().trim().isEmpty())
                && (!apellidosTextField.getText().isEmpty())
                && (!apellidosTextField.getText().trim().isEmpty())) {
            //============================================
            // añadimos a la lista
            datos.add(new Persona(nombreTextField.getText(), apellidosTextField.getText()));
            //============================================
            // Vaciamos los campos después de añadir a la lista
            nombreTextField.clear();
            apellidosTextField.clear();
            //cambio del foco al textfield inicial
            nombreTextField.requestFocus();

        }
    }

    @FXML
    void borrarAccion() {
        //================================================
        // borramos de la lista
        datos.remove(personasListView.getSelectionModel().getSelectedIndex());
        personasListView.getSelectionModel().select(null);
        //================================================
    }
}