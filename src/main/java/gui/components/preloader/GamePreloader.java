package gui.components.preloader;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GamePreloader extends Preloader {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/graphics/fxml/preloader.fxml"));
        Scene scene = new Scene(root);
        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.setTitle("Tangram");
        this.stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
