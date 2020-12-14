package factory;

import world.mode.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModeFactory {

    static Map<String, Mode> modeMap = new HashMap<>();
    static {
        modeMap.put(null, new Normal());
        modeMap.put("altitude", new Altitude());
        modeMap.put("humidity", new Humidity());
        modeMap.put("ressources", new Ressources());

        // more logics
    }

    public static Optional<Mode> getMode(String mode) {
        return Optional.ofNullable(modeMap.get(mode));
    }
}
