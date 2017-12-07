package core.graphics;

import core.components.board.Board;
import core.components.tile.Tile;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GraphicsTools {

    private GraphicsTools() {
    }

    public static Point2D getLocalCenterOfTile(Tile t) {
        double x = 0.0;
        double y = 0.0;
        ObservableList<Double> points = t.getPoints();
        for (int i = 0; i < points.size(); i += 2) {
            x += points.get(i);
            y += points.get(i + 1);
        }
        return new Point2D(x / (points.size() / 2), y / (points.size() / 2));
    }

    public static double distance(Point2D pointA, Point2D pointB) {
        return Math.sqrt(Math.pow(pointA.getX() - pointB.getX(), 2) + Math.pow(pointA.getY() - pointB.getY(), 2));
    }

    public static List<Point2D> getPointsLocalToParent(final Polygon polygon) {
        List<Point2D> resultPoints = new ArrayList<>();
        for (int i = 0; i < polygon.getPoints().size(); i += 2) {
            Point2D resultPoint = polygon.localToParent(polygon.getPoints().get(i), polygon.getPoints().get(i + 1));
            resultPoints.add(resultPoint);
        }
        return resultPoints;
    }

    public static Tile getTile(MouseEvent event) {
        return (Tile) event.getSource();
    }

    public static ListView<Board> getCurrentListView(TabPane tabPane) {
        return (ListView<Board>) tabPane.getSelectionModel().getSelectedItem().getContent();
    }

    public static Board getNextBoard(Board currentBoard) {
        if (currentBoard == null) {
            throw new NoSuchElementException("There is no next element");
        }
        boolean found = false;
        for (Board board : GraphicsMenu.getListOfBoards()) {
            if (found) {
                return board;
            }
            if (board.getBoardType() == currentBoard.getBoardType()) {
                found = true;
            }
        }
        throw new NoSuchElementException("There is no next element");
    }

    public static double[] convertToArray(ObservableList<Double> points) {
        double[] result = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            result[i] = points.get(i);
        }
        return result;
    }
}
