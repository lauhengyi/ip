package laura;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import laura.exception.LauraException;
import laura.ui.MainWindow;

/**
 * The main application initialization
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Laura laura = new Laura();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setLaura(laura);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("L.A.U.R.A");
            stage.show();
        } catch (IOException | LauraException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
