package gui.window.menu;

import core.game.Game;
import core.graphics.GraphicsLayout;
import core.graphics.GraphicsMenu;
import core.graphics.GraphicsTools;
import core.graphics.graphicselements.MenuGraphicsElements;
import gui.window.game.MainGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Button startButton;

    @FXML
    private Label notificationLabel;

    private MenuGraphicsElements menuGraphicsElements;

    public void initialize(URL url, ResourceBundle rb) {
        GraphicsMenu.initializeMenu(getGraphicsElements());
    }

    @FXML
    public void loadGame() throws IOException {
        Game.setCurrentBoard(GraphicsTools.getCurrentListView(tabPane).getSelectionModel().getSelectedItem());
        try {
            Game.setNextBoard(GraphicsTools.getNextBoard(Game.getCurrentBoard()));
        } catch (NoSuchElementException ex) {
            Game.setNextBoard(null);
        }
        GraphicsLayout.openGameScene(new MainGame());
    }

    private MenuGraphicsElements getGraphicsElements() {
        if (menuGraphicsElements == null) {
            initGraphicsElements();
        }
        return menuGraphicsElements;
    }

    private void initGraphicsElements() {
        menuGraphicsElements = new MenuGraphicsElements();
        menuGraphicsElements.setTabPane(this.tabPane);
        menuGraphicsElements.setNotificationLabel(this.notificationLabel);
        menuGraphicsElements.setStartButton(this.startButton);
    }
}
