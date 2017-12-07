package core.components.tile;

import core.components.Rotation;
import core.game.Game;
import core.graphics.GraphicsGame;
import core.graphics.GraphicsPositions;
import core.graphics.GraphicsTools;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class Tile extends Polygon implements Comparable<Tile> {

    private TileType tileType;
    private Rotation rotation;
    private Rotation flippedRotation;
    private double[] flippedPoints;
    private boolean flipped;
    private String imagePath;

    public Tile() {
    }

    public Tile(TileType tileType, Rotation rotation, Rotation flippedRotation, double[] points, double[] flippedPoints, String imagePath) {
        super(points);
        this.tileType = tileType;
        this.rotation = rotation;
        this.flippedRotation = flippedRotation;
        this.flippedPoints = flippedPoints;
        this.flipped = false;
        this.imagePath = imagePath;
        this.setFill(new ImagePattern(new Image(imagePath)));
        this.setId(tileType.toString());
        GraphicsGame.setDefaultStroke(this);
    }

    public TileType getTileType() {
        return tileType;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Rotation getFlippedRotation() {
        return flippedRotation;
    }

    public double[] getFlippedPoints() {
        return flippedPoints;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setPoints(double[] points) {
        getPoints().clear();
        for (Double point : points) {
            getPoints().add(point);
        }
    }

    public void rotate() {
        Point2D pointBefore = GraphicsTools.getLocalCenterOfTile(this);
        increaseAngle();
        setPoints(GraphicsTools.convertToArray(getTileCoordinations(tileType, rotation).getPoints()));
        Point2D pointAfter = GraphicsTools.getLocalCenterOfTile(this);
        GraphicsPositions.alignToCenterAfterRotation(pointBefore, pointAfter, this);
        GraphicsPositions.snapToGrid(this);
    }

    public void flip() {
        Point2D pointBefore = GraphicsTools.getLocalCenterOfTile(this);
        flipped = !flipped;
        setPoints(getTileCoordinations(tileType, rotation).getFlippedPoints());
        setRotation(getTileCoordinations(tileType, rotation).getFlippedRotation());
        Point2D pointAfter = GraphicsTools.getLocalCenterOfTile(this);
        GraphicsPositions.alignToCenterAfterRotation(pointBefore, pointAfter, this);
        GraphicsPositions.snapToGrid(this);
    }

    @Override
    public int compareTo(Tile o) {
        return this.getTileType().compareTo(o.getTileType());
    }

    private void increaseAngle() {
        switch (rotation) {
            case ANGLE_0:
                this.rotation = Rotation.ANGLE_45;
                break;
            case ANGLE_45:
                this.rotation = Rotation.ANGLE_90;
                break;
            case ANGLE_90:
                this.rotation = Rotation.ANGLE_135;
                break;
            case ANGLE_135:
                increaseToNextAngleOrZeroAngle();
                break;
            case ANGLE_180:
                this.rotation = Rotation.ANGLE_225;
                break;
            case ANGLE_225:
                this.rotation = Rotation.ANGLE_270;
                break;
            case ANGLE_270:
                this.rotation = Rotation.ANGLE_315;
                break;
            case ANGLE_315:
                increaseToNextAngleOr180Angle();
                break;
        }
    }

    private void increaseToNextAngleOrZeroAngle() {
        if (tileType == TileType.PARALLELOGRAM && !flipped) {
            this.rotation = Rotation.ANGLE_0;
        } else {
            this.rotation = Rotation.ANGLE_180;
        }
    }

    private void increaseToNextAngleOr180Angle() {
        if (tileType == TileType.PARALLELOGRAM && flipped) {
            this.rotation = Rotation.ANGLE_180;
        } else {
            this.rotation = Rotation.ANGLE_0;
        }
    }

    private Tile getTileCoordinations(TileType tileType, Rotation rotation) {
        return Game.getListOfAllTiles().stream()
                .filter(tile -> tile.getTileType() == tileType && tile.getRotation() == rotation)
                .findFirst()
                .get();
    }
}
