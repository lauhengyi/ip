package laura;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import laura.exception.LauraException;
import laura.ui.MainWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Laura laura = new Laura();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLaura(laura);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LauraException e) {
            e.printStackTrace();
        }
    }
}
