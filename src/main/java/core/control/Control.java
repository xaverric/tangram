package core.control;

import core.components.board.Board;
import core.components.tile.Tile;
import core.components.tile.TileType;
import core.game.Game;
import core.graphics.GraphicsTools;
import core.settings.Configuration;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.EnumSet;
import java.util.List;

public class Control {

    private Control() {
    }

    public static boolean isInBounds(MouseEvent event) {
        return event.getSceneX() >= 0 && event.getSceneX() <= Configuration.BOARD_SIZE_X
                && event.getSceneY() >= 0 && event.getSceneY() <= Configuration.BOARD_SIZE_Y;
    }

    public static boolean isIntersectionBetweenTiles() {
        for (Tile tile1 : Game.getListOfTiles()) {
            for (Tile tile2 : Game.getListOfTiles()) {
                if (!tile1.equals(tile2) && !Shape.intersect(tile1, tile2).getBoundsInLocal().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAllInBoard(Pane pane) {
        Board board = (Board) pane.lookup("#" + Game.getCurrentBoard().getBoardType().toString());
        for (TileType tileType : EnumSet.allOf(TileType.class)) {
            Tile tile = (Tile) pane.lookup("#" + tileType.toString());
            if (tile == null || !isCenterOfTileInBoard(board, tile) || !isTileInBoard(tile, board)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPointInPolygon(Point2D localPoint, Polygon polygon) {
        List<Point2D> points = GraphicsTools.getPointsLocalToParent(polygon);
        boolean result = false;
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            double xi = points.get(i).getX();
            double xj = points.get(j).getX();
            double yi = points.get(i).getY();
            double yj = points.get(j).getY();
            if (((yi > localPoint.getY()) != (yj > localPoint.getY())) &&
                    (localPoint.getX() < (xj - xi) * (localPoint.getY() - yi) / (yj - yi) + xi)
                    ) {
                result = !result;
            }
        }
        return result;
    }

    public static boolean isPointOnAnyLineOfPolygon(Point2D point, Polygon polygon) {
        List<Point2D> points = GraphicsTools.getPointsLocalToParent(polygon);
        for (int i = 0, j = points.size() - 1; i < points.size(); j = i++) {
            if (isPointBetweenPointsInLine(points.get(j), points.get(i), point)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPointBetweenPointsInLine(Point2D lineA, Point2D lineB, Point2D point) {
        return GraphicsTools.distance(lineA, point) + GraphicsTools.distance(lineB, point) == GraphicsTools.distance(lineA, lineB);
    }

    private static boolean isTileInBoard(Tile tile, Board board) {
        List<Point2D> tilePoints = GraphicsTools.getPointsLocalToParent(tile);
        for (Point2D point : tilePoints) {
            if (!board.contains(point)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCenterOfTileInBoard(Board board, Tile tile) {
        return board.contains(tile.localToParent(GraphicsTools.getLocalCenterOfTile(tile)));
    }
}
