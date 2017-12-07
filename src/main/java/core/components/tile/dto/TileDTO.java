package core.components.tile.dto;

import core.components.Rotation;
import core.components.tile.TileType;

public class TileDTO {

    private TileType tileType;
    private Rotation rotation;
    private Rotation flippedRotation;
    private double[] points;
    private double[] flippedPoints;
    private String imagePath;

    public TileType getTileType() {
        return tileType;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Rotation getFlippedRotation() {
        return flippedRotation;
    }

    public double[] getPoints() {
        return points;
    }

    public double[] getFlippedPoints() {
        return flippedPoints;
    }

    public String getImagePath() {
        return imagePath;
    }
}
