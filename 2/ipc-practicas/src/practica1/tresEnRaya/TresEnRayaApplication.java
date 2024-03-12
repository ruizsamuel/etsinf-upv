package practica1.tresEnRaya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ruizsamuel
 */
public class TresEnRayaApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("TresEnRaya.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("3 en Raya");
        stage.show();
    }

    /**
     * Main method. Run the Application
     * @param args command line arguments
     */
    public static void main(String[] args) { launch(args); }
}
