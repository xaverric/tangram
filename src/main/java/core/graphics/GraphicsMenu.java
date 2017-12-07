package core.graphics;

import core.components.board.Board;
import core.components.board.BoardType;
import core.components.board.dto.BoardDTO;
import core.components.category.Category;
import core.components.category.CategoryType;
import core.graphics.graphicscomponent.BoardCell;
import core.graphics.graphicselements.MenuGraphicsElements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import tools.XMLDataLoader;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class GraphicsMenu {

    private static List<Board> listOfBoards;

    private GraphicsMenu() {
    }


    public static void initializeMenu(MenuGraphicsElements elements) {
        initializeBoards();
        addTabsWithBoards(elements);
        setActionsTabPane(elements);
    }

    public static List<Board> getListOfBoards() {
        return listOfBoards;
    }

    private static void initializeBoards() {
        if (listOfBoards == null) {
            listOfBoards = new ArrayList<>();
        }
        List<BoardDTO> boards = XMLDataLoader.loadBoardsFromXML();
        for (BoardDTO boardDTO : boards) {
            GraphicsMenu.listOfBoards.add(new Board(boardDTO.getCategoryType(), boardDTO.getBoardType(), boardDTO.getPoints()));
        }
    }

    private static void addTabsWithBoards(MenuGraphicsElements elements) {
        for (Category category : getCategories()) {
            addNewTab(category.getCategoryType().toString(), getBoardsMenu(category), elements.getTabPane());
        }
    }

    private static void clearSelections(MenuGraphicsElements elements) {
        elements.getStartButton().setVisible(false);
        elements.getNotificationLabel().setVisible(true);
        for (Tab tab : elements.getTabPane().getTabs()) {
            ((ListView<BoardType>) tab.getContent()).getSelectionModel().clearSelection();
        }
    }

    private static ListView<Board> getBoardsMenu(Category category) {
        ListView<Board> boardsMenu = new ListView<>();
        ObservableList<Board> observableList = FXCollections.observableArrayList();
        observableList.addAll(category.getBoards());
        boardsMenu.setItems(observableList);
        boardsMenu.setOrientation(Orientation.HORIZONTAL);
        boardsMenu.setCellFactory((ListView<Board> list) -> new BoardCell());
        return boardsMenu;
    }

    private static void addNewTab(String name, ListView<Board> listView, TabPane tabPane) {
        Tab tab = new Tab(name);
        tab.setContent(listView);
        tabPane.getTabs().add(tab);
    }

    private static List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        for (CategoryType categoryType : EnumSet.allOf(CategoryType.class)) {
            categories.add(initializeCategory(categoryType));
        }
        return categories;
    }

    private static List<Board> getBoardsForCategory(CategoryType categoryType) {
        return GraphicsMenu.listOfBoards
                .stream()
                .filter(element -> element.getCategoryType() == categoryType)
                .collect(Collectors.toList());
    }

    private static Category initializeCategory(CategoryType categoryType) {
        return new Category(getBoardsForCategory(categoryType), categoryType);
    }

    private static void setActionsTabPane(MenuGraphicsElements elements) {
        for (Tab tab : elements.getTabPane().getTabs()) {
            tab.getContent().setOnMouseClicked(event -> {
                if (((ListView<BoardType>) tab.getContent()).getSelectionModel().getSelectedItem() != null) {
                    elements.getNotificationLabel().setVisible(false);
                    elements.getStartButton().setVisible(true);
                }
            });
        }
        elements.getTabPane().getSelectionModel().selectedItemProperty().addListener(event ->
                GraphicsMenu.clearSelections(elements)
        );
    }
}
