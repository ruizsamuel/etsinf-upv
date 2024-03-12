package practica2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ruizsamuel
 */
public class MoverCirculoApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("MoverCirculo.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Ejercicio Mover Círculo");
        stage.show();
    }

    /**
     *
     * @param args command line arguments
     */
    public static void main(String[] args) { launch(args); }
}