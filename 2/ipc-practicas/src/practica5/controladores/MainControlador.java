package practica5.controladores;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import practica5.modelos.Persona;
import practica5.modelos.celda.ImagenPersonaCelda;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainControlador implements Initializable {
    private ObservableList<Persona> datos = null;

    @FXML
    private Button addButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button borrarButton;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;
    @FXML
    private TableColumn<Persona, String> imagenColumn;
    @FXML
    private TableView<Persona> personasTableV;

    private Stage personaVentana;
    private PersonaControlador personaControlador;

    private void inicializaModelo() {
        ArrayList<Persona> misDatos = new ArrayList<>();
        misDatos.add(new Persona("Pepe", "García", "/practica5/recursos/Pregunta.png"));
        misDatos.add(new Persona("María", "Pérez", "/practica5/recursos/Lloroso.png"));

        datos = FXCollections.observableList(misDatos);
        personasTableV.setItems(datos);

        nombreColumn.setCellValueFactory((persona) -> persona.getValue().NombreProperty());
        apellidosColumn.setCellValueFactory((persona) -> persona.getValue().ApellidosProperty());
        imagenColumn.setCellValueFactory((persona) -> new SimpleStringProperty(persona.getValue().getRutaImagen()));
        imagenColumn.setCellFactory(c -> new ImagenPersonaCelda());

    }
    private void inicializarHandlers() {
        addButton.setOnAction(addHandler);
        modificarButton.setOnAction(modificarHandler);
        borrarButton.setOnAction(borrarHandler);
    }

    private void inicializarBindings() {
        BooleanBinding elementoSeleccionado = Bindings.equal(personasTableV.getSelectionModel().selectedIndexProperty(), -1);
        modificarButton.disableProperty().bind(elementoSeleccionado);
        borrarButton.disableProperty().bind(elementoSeleccionado);
    }

    private void inicializarVentanas() throws IOException {
        /* Ventana Persona */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/practica5/vistas/PersonaVista.fxml"));

        personaVentana = loader.load();
        personaControlador = loader.getController();

        personaVentana.setTitle("Modificar/Editar Persona");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaModelo();
        inicializarHandlers();
        inicializarBindings();
        try {
            inicializarVentanas();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Handlers */

    EventHandler<ActionEvent> addHandler = (e) -> {
        personaControlador.setPersona(null);
        personaVentana.showAndWait();
        if (personaControlador.aceptarPulsado()) {
            datos.add(personaControlador.getPersona());
        }
        e.consume();
    };
    EventHandler<ActionEvent> borrarHandler = (e) -> {
        datos.remove(personasTableV.getSelectionModel().getSelectedIndex());
        e.consume();
    };
    EventHandler<ActionEvent> modificarHandler = (e) -> {
        personaControlador.setPersona(personasTableV.getSelectionModel().getSelectedItem());
        personaVentana.showAndWait();
        if (personaControlador.aceptarPulsado()) {
            datos.set(personasTableV.getSelectionModel().getSelectedIndex(), personaControlador.getPersona());
        }
        e.consume();
    };
}
