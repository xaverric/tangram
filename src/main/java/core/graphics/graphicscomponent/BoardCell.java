package core.graphics.graphicscomponent;

import core.components.board.Board;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class BoardCell extends ListCell<Board> {

    @Override
    protected void updateItem(Board item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(getItemContent(item.getMiniature()));
        } else {
            setGraphic(null);
        }
    }

    private HBox getItemContent(Board board) {
        HBox hbox = new HBox();
        Group group = new Group();
        group.getChildren().add(board);
        hbox.getChildren().add(group);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
}
