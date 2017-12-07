package gui.window.menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainMenu extends Application {

    public static Stage stage;

    @Override
    public void init() throws Exception {
        super.init();
        TimeUnit.SECONDS.sleep(3);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/graphics/fxml/menu.fxml"));
        Scene scene = new Scene(root);
        MainMenu.stage = stage;
        MainMenu.stage.getIcons().add(new Image(MainMenu.class.getClassLoader().getResourceAsStream("icon/tangram-icon.png")));
        MainMenu.stage.setTitle("Tangram");
        MainMenu.stage.setScene(scene);
        MainMenu.stage.setResizable(false);
        MainMenu.stage.sizeToScene();
        MainMenu.stage.show();
    }
}
