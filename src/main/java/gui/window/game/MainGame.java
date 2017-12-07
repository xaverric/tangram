package gui.window.game;

import core.graphics.GraphicsLayout;
import gui.components.alert.Alerts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainGame extends Stage {

    public static Stage stage;

    public MainGame() throws IOException {
        setTitle("Tangram");
        setScene(createScene());
        setResizable(false);
        sizeToScene();
        MainGame.stage = this;
        MainGame.stage.getIcons().add(new Image(MainGame.class.getClassLoader().getResourceAsStream("icon/tangram-icon.png")));
        setOnCloseRequest(event -> {
            Optional<ButtonType> respond = Alerts.showExitAppConfirmationDialog();
            if (respond.get() == ButtonType.OK) {
                GraphicsLayout.openMenuScene();
            } else if (respond.get() == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    private Scene createScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/graphics/fxml/game.fxml"));
        return new Scene(root);
    }
}
