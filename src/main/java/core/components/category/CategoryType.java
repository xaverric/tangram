package core.components.category;

import java.util.HashMap;
import java.util.Map;

public enum CategoryType {
    GEOMETRY("Geometry"),
    ANIMALS("Animals");

    private static Map<String, CategoryType> map;

    private String name;

    CategoryType(String name) {
        this.name = name;
    }

    public static Map<String, CategoryType> getAsMap() {
        if (map == null) {
            initializeMap();
        }
        return map;
    }

    private static void initializeMap() {
        map = new HashMap<>();
        map.put("GEOMETRY", GEOMETRY);
        map.put("ANIMALS", ANIMALS);
    }

    @Override
    public String toString() {
        return name;
    }
}
