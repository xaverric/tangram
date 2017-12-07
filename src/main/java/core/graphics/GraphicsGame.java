package core.graphics;

import core.components.board.Board;
import core.components.tile.Tile;
import core.components.tile.TileType;
import core.control.Control;
import core.game.Game;
import core.graphics.graphicscomponent.TileCell;
import core.graphics.graphicselements.GameGraphicsElements;
import core.init.Initialization;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import java.text.ParseException;
import java.util.Collections;
import java.util.EnumSet;

public class GraphicsGame {

    private static final Color COLOR_BLUE = new Color(0.0, 119.0 / 255, 204.0 / 255, 1);
    private static final DropShadow DROP_SHADOW = new DropShadow(40, 20, 20, Color.BLACK);
    private static final BoxBlur BOX_BLUR = new BoxBlur(10, 10, 3);
    private static ObservableList<Tile> menuItems;

    private GraphicsGame() {
    }

    public static void initialize(GameGraphicsElements elements) {
        if (menuItems == null) {
            menuItems = FXCollections.observableArrayList();
        }
        menuItems.clear();
        for (Tile tile : Game.getListOfTiles()) {
            menuItems.add(tile);
        }
        initializeMenu(elements);
        addBoard(elements);
    }

    public static ObservableList<Tile> getMenuItems() {
        return GraphicsGame.menuItems;
    }

    public static void setDefaultStroke(Polygon polygon) {
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(1.0);
        polygon.setStrokeType(StrokeType.INSIDE);
    }

    public static void setEffectOnDragg(MouseEvent event, Tile tile) {
        tile.setEffect(DROP_SHADOW);
        if (Control.isInBounds(event)) {
            GraphicsGame.setDraggStroke(tile);
        } else {
            GraphicsGame.setOutOfBoardStroke(tile);
        }
    }

    public static void addTileToBoard(Tile tile, GameGraphicsElements elements) {
        setActions(tile, elements);
        elements.getBoard().getChildren().add(tile);
        GraphicsPositions.alignToCenterOfBoard(tile);
        GraphicsGame.setDefaultStroke(tile);
    }

    public static void addTileToBoardAndDragg(Tile tile, GameGraphicsElements elements) {
        setActions(tile, elements);
        elements.getBoard().getChildren().add(tile);
        GraphicsGame.setDefaultStroke(tile);

        tile.setEffect(DROP_SHADOW);
        elements.getListView().setEffect(BOX_BLUR);
        GraphicsLayout.disableAllOnAddTile(tile, elements);
    }

    public static void addTileToMenu(Tile tile, GameGraphicsElements elements) {
        menuItems.add(tile);
        Collections.sort(menuItems);
        elements.getBoard().getChildren().remove(tile);
        elements.getListView().setItems(menuItems);
    }

    public static void allFromMenuToBoard(GameGraphicsElements elements) {
        for (Tile tile : menuItems) {
            GraphicsGame.addTileToBoard(tile, elements);
        }
        menuItems.clear();
        elements.getListView().setItems(menuItems);
    }

    public static void allFromBoardToMenu(Pane pane) {
        for (TileType tileType : EnumSet.allOf(TileType.class)) {
            Tile tile = (Tile) pane.lookup("#" + tileType.toString());
            if (tile != null) {
                menuItems.add(tile);
                pane.getChildren().remove(tile);
            }
        }
        Collections.sort(menuItems);
    }

    public static void animateOnEndGame(GameGraphicsElements elements) {
        Pane board = elements.getBoard();
        for (TileType tileType : EnumSet.allOf(TileType.class)) {
            Tile tile = (Tile) board.lookup("#" + tileType.toString());
            if (tile != null) {
                ScaleTransition st = new ScaleTransition(Duration.millis(900), tile);
                st.setByX(-0.7);
                st.setByY(-0.7);
                st.setAutoReverse(true);
                st.setCycleCount(2);
                st.setOnFinished(event -> {
                    try {
                        showButtons(elements);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
                st.play();
            }
        }
    }

    public static void disableAllTiles() {
        for (Tile tile : Game.getListOfTiles()) {
            if (tile != null) {
                tile.setDisable(true);
            }
        }
    }

    public static void enableAllTiles() {
        for (Tile tile : Game.getListOfTiles()) {
            if (tile != null) {
                tile.setDisable(false);
            }
        }
    }

    public static void disableAllTilesExcept(Tile excludedTile) {
        for (Tile tile : Game.getListOfTiles()) {
            if (tile != null && tile.getTileType() != excludedTile.getTileType()) {
                tile.setDisable(true);
            }
        }
    }

    private static void initializeMenu(GameGraphicsElements elements) {
        ListView<Tile> listView = elements.getListView();
        listView.setItems(menuItems);
        listView.setCellFactory((ListView<Tile> list) -> new TileCell(elements));
    }

    private static void addBoard(GameGraphicsElements elements){
        Board board = Game.getCurrentBoard();
        board.setId(board.getBoardType().toString());
        elements.getBoard().getChildren().add(board);
        GraphicsPositions.alignToCenterOfBoard(board);
    }

    private static void showButtons(GameGraphicsElements elements) throws ParseException {
        elements.getBoard().setEffect(BOX_BLUR);
        elements.getButtonNextGame().setVisible(true);
        elements.getButtonGameAgain().setVisible(true);
        showResultMessage(elements);
        GraphicsLayout.disableAllOnSuccessGame(elements);
    }

    private static void showResultMessage(GameGraphicsElements elements){
        long timePassed = System.currentTimeMillis() - Initialization.timeStart;
        if (timePassed > 60000){
            elements.getLabelNotBad().setVisible(true);
        } else if (timePassed > 30000 ){
            elements.getLabelPerfect().setVisible(true);
        } else {
            elements.getLabelBrilliant().setVisible(true);
        }
    }

    private static void setDraggStroke(Tile tile) {
        tile.setStroke(Color.WHITE);
        tile.setStrokeWidth(5.0);
        tile.setStrokeType(StrokeType.INSIDE);
    }

    private static void setOutOfBoardStroke(Tile tile) {
        tile.setStroke(COLOR_BLUE);
        tile.setStrokeWidth(5.0);
        tile.setStrokeType(StrokeType.INSIDE);
    }

    private static void setActions(Tile tile, GameGraphicsElements elements) {
        tile.setOnMousePressed(event -> GraphicsActions.registerPressOnTile(event, elements));
        tile.setOnMouseDragged(GraphicsActions::registerDraggOnTile);
        tile.setOnMouseClicked(event -> GraphicsActions.registerRotateFlipOnTile(event, elements));
        tile.setOnMouseReleased(event -> GraphicsActions.registerReleaseOnTile(event, elements));
    }
}

