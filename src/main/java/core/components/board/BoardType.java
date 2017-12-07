package core.components.board;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BoardType {
    /**
     * Geometry shapes
     */
    BOARD_1_GEOMETRY,
    BOARD_2_GEOMETRY,
    BOARD_3_GEOMETRY,
    BOARD_4_GEOMETRY,
    BOARD_5_GEOMETRY,
    BOARD_6_GEOMETRY,
    BOARD_7_GEOMETRY,
    BOARD_8_GEOMETRY,
    BOARD_9_GEOMETRY,
    BOARD_10_GEOMETRY,


    /**
     * Animal shapes
     */
    BOARD_11_ANIMAL,
    BOARD_12_ANIMAL,
    BOARD_13_ANIMAL;

    private static Map<String, BoardType> map;

    public static Map<String, BoardType> getAsMap() {
        if (map == null) {
            initializeMap();
        }
        return map;
    }

    private static void initializeMap() {
        map = new HashMap<>();
        for (BoardType boardType : EnumSet.allOf(BoardType.class)) {
            map.put(boardType.toString(), boardType);
        }
    }

}
