package core.graphics.graphicscomponent;

import core.components.tile.Tile;
import core.graphics.GraphicsGame;
import core.graphics.GraphicsPositions;
import core.graphics.GraphicsTools;
import core.graphics.graphicselements.GameGraphicsElements;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class TileCell extends ListCell<Tile> {

    private GameGraphicsElements elements;

    public TileCell(GameGraphicsElements elements) {
        this.elements = elements;
    }

    @Override
    protected void updateItem(Tile item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            Tile tileMenu = createTileForMenu(item);
            setGraphicActions(tileMenu, item);
            setGraphic(getItemContent(tileMenu));
        } else {
            setGraphic(null);
        }
    }

    private void setGraphicActions(Tile tileMenu, Tile tileGame) {
        tileMenu.setOnMouseClicked(event -> {
            Point2D point = new Point2D(GraphicsTools.getTile(event).getTranslateX(), GraphicsTools.getTile(event).getTranslateY());
            Point2D samePointInParent = GraphicsTools.getTile(event).localToScene(point);
            GraphicsGame.addTileToBoardAndDragg(tileGame, elements);
            GraphicsPositions.alignToSameScreenPosition(tileGame, samePointInParent);
        });
    }

    private HBox getItemContent(Tile tile) {
        HBox hbox = new HBox();
        hbox.getChildren().add(tile);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private Tile createTileForMenu(Tile tile) {
        Tile menuTile = new Tile();
        menuTile.setFill(tile.getFill());
        menuTile.setPoints(GraphicsTools.convertToArray(tile.getPoints()));
        menuTile.setStrokeWidth(1);
        menuTile.setStrokeType(StrokeType.INSIDE);
        menuTile.setStroke(Color.BLACK);
        return menuTile;
    }
}
