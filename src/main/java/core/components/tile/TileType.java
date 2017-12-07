package core.components.tile;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TileType implements Comparable<TileType> {
    LARGE_TRIANGLE_1,
    LARGE_TRIANGLE_2,
    MEDIUM_TRIANGLE,
    SMALL_TRIANGLE_1,
    SMALL_TRIANGLE_2,
    PARALLELOGRAM,
    SQUARE;

    private static Map<String, TileType> map;

    public static Map<String, TileType> getAsMap() {
        if (map == null) {
            initializeMap();
        }
        return map;
    }

    private static void initializeMap() {
        map = new HashMap<>();
        for (TileType tileType : EnumSet.allOf(TileType.class)) {
            map.put(tileType.toString(), tileType);
        }
    }


}
