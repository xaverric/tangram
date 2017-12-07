package core.game;

import core.components.Rotation;
import core.components.board.Board;
import core.components.tile.Tile;
import core.components.tile.dto.TileDTO;
import core.control.Control;
import core.graphics.GraphicsGame;
import core.graphics.graphicselements.GameGraphicsElements;
import tools.XMLDataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private static List<Tile> listOfTiles = new ArrayList<>();
    private static List<Tile> listOfAllTiles = new ArrayList<>();
    private static Board currentBoard;
    private static Board nextBoard;

    private Game() {
    }

    public static void initialize() throws IOException {
        initializeAllTiles();
        initializeDefaultTiles();
    }

    public static List<Tile> getListOfTiles() {
        return listOfTiles;
    }

    public static List<Tile> getListOfAllTiles() {
        return listOfAllTiles;
    }

    public static Board getCurrentBoard() {
        return currentBoard;
    }

    public static void setCurrentBoard(Board currentBoard) {
        Game.currentBoard = currentBoard;
    }

    public static Board getNextBoard() {
        return nextBoard;
    }

    public static void setNextBoard(Board nextBoard) {
        Game.nextBoard = nextBoard;
    }


    public static void evaluateGame(GameGraphicsElements elements) {
        if (Control.isAllInBoard(elements.getBoard()) && !Control.isIntersectionBetweenTiles()) {
            endGame(elements);
        }
    }

    private static void endGame(GameGraphicsElements elements) {
        GraphicsGame.animateOnEndGame(elements);
    }

    private static void initializeAllTiles() throws IOException {
        List<TileDTO> tiles = XMLDataLoader.loadTilesFromXML();
        Game.listOfAllTiles.clear();
        for (TileDTO tileDTO : tiles) {
            Tile tile = new Tile(tileDTO.getTileType(), tileDTO.getRotation(), tileDTO.getFlippedRotation(), tileDTO.getPoints(), tileDTO.getFlippedPoints(), tileDTO.getImagePath());
            Game.listOfAllTiles.add(tile);
        }
    }

    private static void initializeDefaultTiles() throws IOException {
        List<TileDTO> tiles = XMLDataLoader.loadTilesFromXML()
                .stream()
                .filter(element -> element.getRotation() == Rotation.ANGLE_0)
                .collect(Collectors.toList());
        Game.listOfTiles.clear();
        for (TileDTO tileDTO : tiles) {
            Tile tile = new Tile(tileDTO.getTileType(), tileDTO.getRotation(), tileDTO.getFlippedRotation(), tileDTO.getPoints(), tileDTO.getFlippedPoints(), tileDTO.getImagePath());
            Game.listOfTiles.add(tile);
        }
    }
}
