import org.junit.Test;
import world.generator.biome.*;

import static org.junit.Assert.assertTrue;

public class BiomeTest {
    @Test
    public void validLocalizationAntarcticaToundra(){
        Localization l = Localization.ANTARCTICA;
        Toundra t = new Toundra(l);
        assertTrue(t.validLocalization(l, -15, -5, 0, 100));
    }
    @Test
    public void validLocalizationAntarcticaIceBeach(){
        Localization l = Localization.ANTARCTICA;
        IceBeach t = new IceBeach(l);
        assertTrue(t.validLocalization(l, -30, -5, 0, 500));
    }

}
