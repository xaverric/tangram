package core.components.category;

import core.components.board.Board;

import java.util.List;

public class Category {

    private List<Board> boards;
    private CategoryType categoryType;

    public Category(List<Board> boards, CategoryType categoryType) {
        this.boards = boards;
        this.categoryType = categoryType;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }
}
