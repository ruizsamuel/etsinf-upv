package practica1.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author ruizsamuel
 */
public class LoginApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader= new  FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("IPC Login");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
