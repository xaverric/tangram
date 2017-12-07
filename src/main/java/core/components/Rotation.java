package core.components;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Rotation {
    ANGLE_0,
    ANGLE_45,
    ANGLE_90,
    ANGLE_135,
    ANGLE_180,
    ANGLE_225,
    ANGLE_270,
    ANGLE_315;

    private static Map<String, Rotation> map;

    public static Map<String, Rotation> getAsMap() {
        if (map == null) {
            initializeMap();
        }
        return map;
    }

    private static void initializeMap() {
        map = new HashMap<>();
        for (Rotation rotation : EnumSet.allOf(Rotation.class)) {
            map.put(rotation.toString(), rotation);
        }
    }
}
