package core.components.board;

import core.components.category.CategoryType;
import core.control.Control;
import javafx.geometry.Point2D;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

public class Board extends Polygon {

    private CategoryType categoryType;
    private BoardType boardType;

    public Board(CategoryType categoryType, BoardType boardType, double... points) {
        super(points);
        this.categoryType = categoryType;
        this.boardType = boardType;
        initializeBoard();
    }

    @Override
    public boolean contains(Point2D localPoint) {
        return Control.isPointInPolygon(localPoint, this) || Control.isPointOnAnyLineOfPolygon(localPoint, this);
    }

    public Board getMiniature() {
        Board board = new Board(this.categoryType, this.boardType);
        board.getPoints().addAll(this.getPoints());
        board.setScaleX(0.3);
        board.setScaleY(0.3);
        board.setEffect(null);
        board.setFill(new Color(0.3, 0.3, 0.3, 1));
        board.setStroke(Color.WHITE);
        board.setStrokeType(StrokeType.OUTSIDE);
        board.setStrokeWidth(10);
        return board;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    private void initializeBoard() {
        setFill(Color.WHITE);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.OUTSIDE);
        setEffect(new InnerShadow(500, Color.LIGHTGRAY));
        setStrokeWidth(1);
    }
}
