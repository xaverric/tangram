package gui.window.game;

import core.components.tile.Tile;
import core.game.Game;
import core.graphics.GraphicsGame;
import core.graphics.GraphicsLayout;
import core.graphics.GraphicsTools;
import core.graphics.graphicselements.GameGraphicsElements;
import core.init.Initialization;
import gui.components.alert.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    @FXML
    private Pane board;

    @FXML
    private Pane moveMenuBody;

    @FXML
    private Pane gameMenuBody;

    @FXML
    private ToggleButton buttonMoveMenu;

    @FXML
    private ToggleButton buttonGameMenu;

    @FXML
    private ListView<Tile> listView;

    @FXML
    private Label labelPerfect;

    @FXML
    private Label labelNotBad;

    @FXML
    private Label labelBrilliant;

    @FXML
    private Button buttonNextGame;

    @FXML
    private Button buttonGameAgain;

    @FXML
    private Label notificationLabel;

    @FXML
    private Pane notificationPopup;

    @FXML
    private Label timeLabel;

    private GameGraphicsElements gameGraphicsElements;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            Initialization.initialize(getGraphicsElements());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void insertToBoard() {
        GraphicsGame.allFromMenuToBoard(getGraphicsElements());
        GraphicsLayout.closeMoveMenu(getGraphicsElements());
    }

    @FXML
    public void insertToMenu() {
        GraphicsGame.allFromBoardToMenu(board);
        GraphicsLayout.closeMoveMenu(getGraphicsElements());
    }

    @FXML
    public void exitApp() {
        Optional<ButtonType> respond = Alerts.showExitAppConfirmationDialog();
        if (respond.isPresent() && respond.get() == ButtonType.OK) {
            GraphicsLayout.openMenuScene();
        }
    }

    @FXML
    public void newGame() throws IOException {
        Optional<ButtonType> respond = Alerts.showNewGameConfirmationDialog();
        if (respond.isPresent() && respond.get() == ButtonType.OK) {
            MainGame.stage.close();
            MainGame game = new MainGame();
            game.show();
        }
    }

    @FXML
    public void playAgain() throws IOException {
        MainGame.stage.close();
        MainGame game = new MainGame();
        game.show();
    }

    @FXML
    public void playNext() throws IOException {
        if (Game.getNextBoard() == null) {
            Alerts.showNoNextBoardWarningDialog();
            GraphicsLayout.openMenuScene();
        } else {
            try {
                Game.setCurrentBoard(Game.getNextBoard());
                Game.setNextBoard(GraphicsTools.getNextBoard(Game.getNextBoard()));
            } catch (NoSuchElementException ex) {
                Game.setNextBoard(null);
            }
            GraphicsLayout.restartGameScene();
        }
    }

    private GameGraphicsElements getGraphicsElements() {
        if (gameGraphicsElements == null) {
            initGraphicsElements();
        }
        return gameGraphicsElements;
    }

    private void initGraphicsElements() {
        gameGraphicsElements = new GameGraphicsElements();
        gameGraphicsElements.setBoard(this.board);
        gameGraphicsElements.setButtonGameAgain(this.buttonGameAgain);
        gameGraphicsElements.setButtonGameMenu(this.buttonGameMenu);
        gameGraphicsElements.setButtonMoveMenu(this.buttonMoveMenu);
        gameGraphicsElements.setButtonNextGame(this.buttonNextGame);
        gameGraphicsElements.setGameMenuBody(this.gameMenuBody);
        gameGraphicsElements.setLabelPerfect(this.labelPerfect);
        gameGraphicsElements.setLabelBrilliant(this.labelBrilliant);
        gameGraphicsElements.setLabelNotBad(this.labelNotBad);
        gameGraphicsElements.setListView(this.listView);
        gameGraphicsElements.setMoveMenuBody(this.moveMenuBody);
        gameGraphicsElements.setNotificationLabel(this.notificationLabel);
        gameGraphicsElements.setNotificationPopup(this.notificationPopup);
        gameGraphicsElements.setTimeLabel(this.timeLabel);
    }
}
