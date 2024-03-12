/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6.controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class MainViewController implements Initializable {

    @FXML
    private BorderPane main;

    /* File Menu */
    @FXML
    private MenuItem salirMenuItem;

    /* Options_BuyIn Menu */
    @FXML
    private RadioMenuItem amazonRadioMenuItem;
    @FXML
    private RadioMenuItem ebayRadioMenuItem;

    /* Launch Menu */
    @FXML
    private MenuItem lanzarAmazonMenuItem;
    @FXML
    private MenuItem lanzarBloggerMenuItem;
    @FXML
    private MenuItem lanzarEbayMenuItem;
    @FXML
    private MenuItem lanzarFacebookMenuItem;
    @FXML
    private MenuItem lanzarGooglePlusMenuItem;


    /* ToolBar Items */
    @FXML
    private Button amazonButton;
    @FXML
    private Button bloggerButton;
    @FXML
    private Button ebayButton;
    @FXML
    private Button facebookButton;
    @FXML
    private Button googlePlusButton;

    @FXML
    private Label statusLabel;

    private final WebView wv = new WebView();

    private void initializeHandlers() {
        salirMenuItem.setOnAction(salirHandler);
        
        amazonButton.setOnAction(lanzar);
        bloggerButton.setOnAction(lanzar);
        ebayButton.setOnAction(lanzar);
        facebookButton.setOnAction(lanzar);
        googlePlusButton.setOnAction(lanzar);

        lanzarAmazonMenuItem.setOnAction(lanzar);
        lanzarBloggerMenuItem.setOnAction(lanzar);
        lanzarEbayMenuItem.setOnAction(lanzar);
        lanzarFacebookMenuItem.setOnAction(lanzar);
        lanzarGooglePlusMenuItem.setOnAction(lanzar);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeHandlers();
    }    

    /* Handlers */
    EventHandler<ActionEvent> salirHandler = e -> {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setContentText("¿Seguro que quieres salir?");
        Optional<ButtonType> result = alerta.showAndWait();
        result.ifPresent(buttonType -> {if (buttonType == ButtonType.OK) main.getScene().getWindow().hide();});
    };

    EventHandler<ActionEvent> lanzar = e -> {
        String url = "";

        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Error en la selección");

        Object s = e.getSource();
        if (s.equals(amazonButton) || s.equals(lanzarAmazonMenuItem)) {
            if (amazonRadioMenuItem.isSelected()) {
                url = "https://www.amazon.es";
                statusLabel.setText("Visitando Amazon");
            } else {
                errorAlert.setHeaderText("No se puede comprar en Amazon");
                errorAlert.setContentText("Por favor, cambie la selección actual en el menú Opciones");
                errorAlert.showAndWait();
                url = "https://www.ebay.com";
                statusLabel.setText("Visitando Ebay");
            }
        } else if (s.equals(bloggerButton) || s.equals(lanzarBloggerMenuItem)) {
            seleccionarBlog().ifPresent(blog -> statusLabel.setText("Visitando " + blog));
            url = "https://www.blogger.com";
        } else if (s.equals(ebayButton) || s.equals(lanzarEbayMenuItem)) {
            if (ebayRadioMenuItem.isSelected()) {
                url = "https://www.ebay.com";
                statusLabel.setText("Visitando Ebay");
            }
            else {
                errorAlert.setHeaderText("No se puede comprar en eBay");
                errorAlert.setContentText("Por favor, cambie la selección actual en el menú Opciones");
                errorAlert.showAndWait();
                url = "https://www.amazon.es";
                statusLabel.setText("Visitando Amazon");
            }
        } else if (s.equals(facebookButton) || s.equals(lanzarFacebookMenuItem)) {
            url = "https://www.facebook.com";
            nombreFacebook().ifPresent(nombre -> statusLabel.setText("Mensaje enviado como " + nombre));
        } else if (s.equals(googlePlusButton) || s.equals(lanzarGooglePlusMenuItem)) {
            url = "https://www.google.com";
            statusLabel.setText("Visitando Google+");
        }
        wv.getEngine().load(url);
        main.setCenter(wv);
        e.consume();
    };

    /* Dialogs */
    Optional<String> seleccionarBlog() {
        List<String> choices = new ArrayList<>();

        // Blogs Disponibles
        choices.add("El blog de Athos");
        choices.add("El blog de Porthos");
        choices.add("El blog de Aramis");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
        dialog.setTitle("Selecciona un blog");
        dialog.setHeaderText("¿Qué blog quieres visitar?");
        dialog.setContentText("Elige: ");

        return dialog.showAndWait();
    }

    Optional<String> nombreFacebook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introduce tu nombre");
        dialog.setHeaderText("¿Con qué usuario quieres escribir en Facebook?");
        dialog.setContentText("Introduce tu nombre: ");

        return dialog.showAndWait();
    }

}