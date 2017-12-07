package core.graphics;

import core.components.tile.Tile;
import core.graphics.graphicselements.GameGraphicsElements;
import core.init.Initialization;
import core.settings.Configuration;
import gui.window.game.MainGame;
import gui.window.menu.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraphicsLayout {

    private GraphicsLayout() {
    }

    public static void initialize(GameGraphicsElements elements){
        enableAllTiles();
        setMenuActions(elements);
        showElapsedTime(elements.getTimeLabel());
        showOrHideNotification(elements);
    }

    public static void setMenuActions(GameGraphicsElements elements) {
        elements.getButtonMoveMenu().setOnAction(event -> {
            if (elements.getButtonMoveMenu().isSelected()) {
                GraphicsLayout.openMoveMenu(elements);
            } else {
                GraphicsLayout.closeMoveMenu(elements);
            }
        });
        elements.getButtonGameMenu().setOnAction(event -> {
            if (elements.getButtonGameMenu().isSelected()) {
                GraphicsLayout.openGameMenu(elements);
            } else {
                GraphicsLayout.closeGameMenu(elements);
            }
        });
    }

    public static void openGameScene(MainGame mainGame) {
        mainGame.show();
        MainMenu.stage.hide();
    }

    public static void restartGameScene() throws IOException {
        MainGame.stage.close();
        MainGame game = new MainGame();
        game.show();
    }

    public static void openMenuScene() {
        MainGame.stage.close();
        MainMenu.stage.show();
    }

    public static void closeMoveMenu(GameGraphicsElements elements) {
        elements.getMoveMenuBody().setVisible(false);
        elements.getListView().setEffect(null);
        elements.getButtonMoveMenu().setSelected(false);
        enableAllOnCloseMenu(elements);
    }

    public static void closeGameMenu(GameGraphicsElements elements) {
        elements.getGameMenuBody().setVisible(false);
        elements.getListView().setEffect(null);
        elements.getButtonGameMenu().setSelected(false);
        enableAllOnCloseMenu(elements);
    }

    public static void openMoveMenu(GameGraphicsElements elements) {
        closeGameMenu(elements);
        elements.getListView().setEffect(new BoxBlur(10, 10, 3));
        elements.getMoveMenuBody().setVisible(true);
        disableAllOnOpenMenu(elements);
    }

    public static void openGameMenu(GameGraphicsElements elements) {
        closeMoveMenu(elements);
        elements.getListView().setEffect(new BoxBlur(10, 10, 3));
        elements.getGameMenuBody().setVisible(true);
        disableAllOnOpenMenu(elements);
    }

    public static void disableAllOnAddTile(Tile tile, GameGraphicsElements elements) {
        elements.getListView().setDisable(true);
        elements.getButtonMoveMenu().setDisable(true);
        elements.getButtonGameMenu().setDisable(true);
        GraphicsGame.disableAllTilesExcept(tile);
    }

    public static void enableAllAfterTileAddedAndMoved(GameGraphicsElements elements) {
        elements.getListView().setDisable(false);
        elements.getButtonMoveMenu().setDisable(false);
        elements.getButtonGameMenu().setDisable(false);
        GraphicsGame.enableAllTiles();
    }

    public static void disableAllOnSuccessGame(GameGraphicsElements elements) {
        elements.getButtonMoveMenu().setDisable(true);
        GraphicsGame.disableAllTiles();
    }

    public static void enableAllTiles() {
        GraphicsGame.enableAllTiles();
    }

    public static void hideNotification(GameGraphicsElements elements) {
        Configuration.notificationHidden = true;
        elements.getNotificationLabel().setVisible(false);
        elements.getNotificationPopup().setVisible(false);
    }

    public static void showElapsedTime(Label timeLabel) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        Initialization.timeStart = System.currentTimeMillis();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                keyValue -> {
                    long timePassed = System.currentTimeMillis() - Initialization.timeStart;
                    Date date = new Date(timePassed);
                    DateFormat formatter = new SimpleDateFormat("mm:ss");
                    String dateFormatted = formatter.format(date);
                    timeLabel.setText(dateFormatted);
                }));
        timeline.play();
    }

    private static void showOrHideNotification(GameGraphicsElements elements){
        if (Configuration.notificationHidden) {
            hideNotification(elements);
        } else {
            elements.getNotificationPopup().toFront();
            elements.getNotificationLabel().toFront();
        }
    }

    private static void disableAllOnOpenMenu(GameGraphicsElements elements) {
        elements.getBoard().setDisable(true);
        elements.getListView().setDisable(true);
    }

    private static void enableAllOnCloseMenu(GameGraphicsElements elements) {
        elements.getBoard().setDisable(false);
        elements.getListView().setDisable(false);
    }

}
