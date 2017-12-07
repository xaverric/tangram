package core.graphics.graphicselements;

import core.components.tile.Tile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class GameGraphicsElements {

    private Pane board;
    private Pane moveMenuBody;
    private Pane gameMenuBody;
    private ToggleButton buttonMoveMenu;
    private ToggleButton buttonGameMenu;
    private ListView<Tile> listView;
    private Label labelPerfect;
    private Label labelNotBad;
    private Label labelBrilliant;
    private Button buttonNextGame;
    private Button buttonGameAgain;
    private Label notificationLabel;
    private Pane notificationPopup;
    private Label timeLabel;

    public Pane getBoard() {
        return board;
    }

    public void setBoard(Pane board) {
        this.board = board;
    }

    public Pane getMoveMenuBody() {
        return moveMenuBody;
    }

    public void setMoveMenuBody(Pane moveMenuBody) {
        this.moveMenuBody = moveMenuBody;
    }

    public Pane getGameMenuBody() {
        return gameMenuBody;
    }

    public void setGameMenuBody(Pane gameMenuBody) {
        this.gameMenuBody = gameMenuBody;
    }

    public ToggleButton getButtonMoveMenu() {
        return buttonMoveMenu;
    }

    public void setButtonMoveMenu(ToggleButton buttonMoveMenu) {
        this.buttonMoveMenu = buttonMoveMenu;
    }

    public ToggleButton getButtonGameMenu() {
        return buttonGameMenu;
    }

    public void setButtonGameMenu(ToggleButton buttonGameMenu) {
        this.buttonGameMenu = buttonGameMenu;
    }

    public ListView<Tile> getListView() {
        return listView;
    }

    public void setListView(ListView<Tile> listView) {
        this.listView = listView;
    }

    public Label getLabelPerfect() {
        return labelPerfect;
    }

    public void setLabelPerfect(Label labelPerfect) {
        this.labelPerfect = labelPerfect;
    }

    public Label getLabelNotBad() {
        return labelNotBad;
    }

    public void setLabelNotBad(Label labelNotBad) {
        this.labelNotBad = labelNotBad;
    }

    public Label getLabelBrilliant() {
        return labelBrilliant;
    }

    public void setLabelBrilliant(Label labelBrilliant) {
        this.labelBrilliant = labelBrilliant;
    }

    public Button getButtonNextGame() {
        return buttonNextGame;
    }

    public void setButtonNextGame(Button buttonNextGame) {
        this.buttonNextGame = buttonNextGame;
    }

    public Button getButtonGameAgain() {
        return buttonGameAgain;
    }

    public void setButtonGameAgain(Button buttonGameAgain) {
        this.buttonGameAgain = buttonGameAgain;
    }

    public Label getNotificationLabel() {
        return notificationLabel;
    }

    public void setNotificationLabel(Label notificationLabel) {
        this.notificationLabel = notificationLabel;
    }

    public Pane getNotificationPopup() {
        return notificationPopup;
    }

    public void setNotificationPopup(Pane notificationPopup) {
        this.notificationPopup = notificationPopup;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }
}
