package factory;

import world.generator.island.CircularIsland;
import world.generator.island.EllipticIsland;
import world.generator.island.IslandShape;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShapeFactory {

    static Map<String, IslandShape> operationMap = new HashMap<>();
    static {
        operationMap.put("atoll", new CircularIsland());
        operationMap.put("tortuga", new EllipticIsland());
        // more logics
    }

    public static Optional<IslandShape> getIslandShape(String shape) {
        return Optional.ofNullable(operationMap.get(shape));
    }
}
