package core.graphics;

import core.components.tile.Tile;
import core.control.Control;
import core.game.Game;
import core.graphics.graphicselements.GameGraphicsElements;
import core.settings.Configuration;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;

/**
 * class with defined actions for tiles
 */
public class GraphicsActions {

    //scene coordinates
    private static BigDecimal coordX;
    private static BigDecimal coordY;

    //tile coordinates
    private static BigDecimal coordTX;
    private static BigDecimal coordTY;

    private GraphicsActions() {
    }

    public static void registerPressOnTile(MouseEvent event, GameGraphicsElements elements) {
        if (!Configuration.notificationHidden) {
            Configuration.notificationHidden = true;
            GraphicsLayout.hideNotification(elements);
        }

        Tile tile = GraphicsTools.getTile(event);
        tile.toFront();
        coordX = new BigDecimal(event.getSceneX());
        coordY = new BigDecimal(event.getSceneY());
        coordTX = new BigDecimal(tile.getTranslateX());
        coordTY = new BigDecimal(tile.getTranslateY());


        elements.getListView().setEffect(null);
        GraphicsGame.getMenuItems().remove(tile);
        elements.getListView().setItems(GraphicsGame.getMenuItems());
        GraphicsLayout.enableAllAfterTileAddedAndMoved(elements);
    }

    public static void registerDraggOnTile(MouseEvent event) {
        Tile tile = GraphicsTools.getTile(event);
        BigDecimal deltaX = new BigDecimal(event.getSceneX()).subtract(coordX);
        BigDecimal deltaY = new BigDecimal(event.getSceneY()).subtract(coordY);
        BigDecimal deltaTX = coordTX.add(deltaX);
        BigDecimal deltaTY = coordTY.add(deltaY);

        tile.setTranslateX(deltaTX.doubleValue());
        tile.setTranslateY(deltaTY.doubleValue());
        GraphicsGame.setEffectOnDragg(event, tile);
    }

    public static void registerRotateFlipOnTile(MouseEvent event, GameGraphicsElements elements) {
        if (event.getButton() == MouseButton.PRIMARY) {
            registerRotateOnTile(event);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            registeFlipOnTile(event);
        }
        Game.evaluateGame(elements);
    }

    public static void registerReleaseOnTile(MouseEvent event, GameGraphicsElements elements) {
        Tile tile = GraphicsTools.getTile(event);
        if (Control.isInBounds(event)) {
            GraphicsPositions.snapToGrid(tile);
            GraphicsGame.setDefaultStroke(tile);
            Game.evaluateGame(elements);
        } else {
            GraphicsGame.addTileToMenu(tile, elements);
        }
        tile.setEffect(null);
    }

    private static void registerRotateOnTile(MouseEvent event) {
        if (new BigDecimal(event.getSceneX()).compareTo(coordX) == 0 && new BigDecimal(event.getSceneY()).compareTo(coordY) == 0) {
            GraphicsTools.getTile(event).rotate();
        }
    }

    private static void registeFlipOnTile(MouseEvent event) {
        if (new BigDecimal(event.getSceneX()).compareTo(coordX) == 0 && new BigDecimal(event.getSceneY()).compareTo(coordY) == 0) {
            GraphicsTools.getTile(event).flip();
        }
    }
}
