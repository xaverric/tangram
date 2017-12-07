package core.components.board.dto;

import core.components.board.BoardType;
import core.components.category.CategoryType;

public class BoardDTO {

    private BoardType boardType;
    private CategoryType categoryType;
    private double[] points;

    public BoardType getBoardType() {
        return boardType;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public double[] getPoints() {
        return points;
    }
}
