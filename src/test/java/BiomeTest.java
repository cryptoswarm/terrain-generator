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
    @Test
    public void validLocalizationBaieJamesTaiga(){
        Localization l = Localization.BAIEJAMES;
        Taiga t = new Taiga(l);
        assertTrue(t.validLocalization(l, -5, 5, 50, 200));
    }
    @Test
    public void validLocalizationBaieJamesRockBeach(){
        Localization l = Localization.BAIEJAMES;
        RockBeach t = new RockBeach(l);
        assertTrue(t.validLocalization(l, -5, 10, 0, 500));
    }
    @Test
    public void validLocalizationManitobaPrairie(){
        Localization l = Localization.MANITOBA;
        Prairie t = new Prairie(l);
        assertTrue(t.validLocalization(l, -5, 20, 20, 100));
    }
    @Test
    public void validLocalizationAfricaSavane(){
        Localization l = Localization.AFRICA;
        Savane t = new Savane(l);
        assertTrue(t.validLocalization(l, 15, 30, 0, 500));
    }
    @Test
    public void validLocalizationSaharaDesert(){
        Localization l = Localization.SAHARA;
        Desert t = new Desert(l);
        assertTrue(t.validLocalization(l, -5, 30, 0, 50));
    }

}
