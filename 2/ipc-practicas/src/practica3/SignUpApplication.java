package practica3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author ruizsamuel
 */
public class SignUpApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader= new  FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SignUp");
        stage.show();
    }

    /**
     *
     * @param args command line arguments
     */
    public static void main(String[] args) { launch(args); }
}
