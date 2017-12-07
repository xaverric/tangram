package core.graphics;

import core.components.tile.Tile;
import core.settings.Configuration;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class GraphicsPositions {

    private GraphicsPositions() {
    }

    public static void alignToCenterAfterRotation(Point2D centerBefore, Point2D centerAfter, Tile tile) {
        if (centerAfter.getX() < centerBefore.getX()) {
            tile.setTranslateX(tile.localToParent(0, 0).getX() + (centerBefore.getX() - centerAfter.getX()));
        } else if (centerAfter.getX() > centerBefore.getX()) {
            tile.setTranslateX(tile.localToParent(0, 0).getX() - (centerAfter.getX() - centerBefore.getX()));
        }
        if (centerAfter.getY() < centerBefore.getY()) {
            tile.setTranslateY(tile.localToParent(0, 0).getY() + (centerBefore.getY() - centerAfter.getY()));
        } else if (centerAfter.getY() > centerBefore.getY()) {
            tile.setTranslateY(tile.localToParent(0, 0).getY() - (centerAfter.getY() - centerBefore.getY()));
        }
    }

    public static void alignToCenterOfBoard(Polygon polygon) {
        polygon.setTranslateX((Configuration.BOARD_SIZE_X - polygon.getBoundsInLocal().getWidth()) / 2);
        polygon.setTranslateY((Configuration.BOARD_SIZE_Y - polygon.getBoundsInLocal().getHeight()) / 2);
        snapToGrid(polygon);
    }

    public static void alignToSameScreenPosition(Tile tile, Point2D positionBefore) {
        tile.setTranslateX(positionBefore.getX());
        tile.setTranslateY(positionBefore.getY());
    }

    public static void snapToGrid(Polygon polygon) {
        polygon.setTranslateX(Math.round(polygon.getTranslateX() / Configuration.GRID_SIZE) * Configuration.GRID_SIZE);
        polygon.setTranslateY(Math.round(polygon.getTranslateY() / Configuration.GRID_SIZE) * Configuration.GRID_SIZE);
    }
}
