package practica4.controladores.tarea2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import practica4.modelos.CeldaPersona;
import practica4.modelos.Persona;

public class VistaListaControlador implements Initializable {

    @FXML
    private ListView<Persona> personasListView;
    @FXML
    private Button modificarButton;
    @FXML
    private Button borrarButton;

    private ObservableList<Persona> datos = null; // Colección vinculada a la vista.

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Persona> misDatos = new ArrayList<>();
        //=======================================================
        // creamos la lista observable mediante un metodo de FXCollections
        datos = FXCollections.observableArrayList(misDatos);

        //=======================================================
        //=======================================================
        // vinculamos la lista observable de personas con el ListView
        personasListView.setItems(datos); // vinculación entre la vista y el modelo
        datos = personasListView.getItems(); // no creo la lista observable, utilizo la que tiene vacia el listview
        datos.add(new Persona("Pepe", "García"));
        datos.add(new Persona("María", "Pérez"));

        //=======================================================
        //=======================================================
        // Hay que modificar CellFactory para mostrar el objeto Persona
        personasListView.setCellFactory(c -> new CeldaPersona());
        //=======================================================
        // disable de los botones modificar y borrar.

        BooleanBinding elementoSeleccionado = Bindings.equal(personasListView.getSelectionModel().selectedIndexProperty(), -1);

        modificarButton.disableProperty().bind(elementoSeleccionado);
        borrarButton.disableProperty().bind(elementoSeleccionado);
    }

    @FXML
    private void addAccion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/practica4/vistas/tarea2/VistaPersona.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        VistaPersonaControlador controlador = loader.getController();

        stage.setScene(scene);
        stage.setTitle("Añadir Persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if (controlador.aceptarPulsado()) datos.add(controlador.getNueva());
    }

    @FXML
    private void modificarAccion() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/practica4/vistas/tarea2/VistaPersona.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        VistaPersonaControlador controlador = loader.getController();
        controlador.setPersona(personasListView.getSelectionModel().getSelectedItem());

        stage.setScene(scene);
        stage.setTitle("Modificar Persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if (controlador.aceptarPulsado())
            datos.set(personasListView.getSelectionModel().getSelectedIndex(), controlador.getNueva());
    }

    @FXML
    private void borrarAccion() {
        datos.remove(personasListView.getSelectionModel().getSelectedIndex());
    }
}