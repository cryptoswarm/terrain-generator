package world.generator.biome;


public enum Localization {
    BAIEJAMES("baiejames", 100, -5),
    CARIBBEAN("caribbean", 150, 25),
    SAHARA("sahara", 0, 30),
    ANTARCTICA("antarctica",50, -10),
    AFRICA("africa", 100, 30),
    MANITOBA("manitoba",100,15);

    Localization(String name, int precipitationAverage, int temperatureAverage) {
        this.name = name;
        this.precipitationAverage = precipitationAverage;
        this.temperatureAverage = temperatureAverage;
    }

    private String name;
    private int precipitationAverage;
    private int temperatureAverage;

    public int getPrecipitationAverage() {
        return precipitationAverage;
    }

    public int getTemperatureAverage() {
        return temperatureAverage;
    }

    public String getName() {
        return name;
    }

    public static Localization getLocalization(String local){
        if(local.equals(BAIEJAMES.getName())) return BAIEJAMES;
        if(local.equals(CARIBBEAN.getName())) return CARIBBEAN;
        if(local.equals(SAHARA.getName())) return SAHARA;
        if(local.equals(ANTARCTICA.getName())) return ANTARCTICA;
        if(local.equals(AFRICA.getName())) return AFRICA;
        if(local.equals(MANITOBA.getName())) return MANITOBA;
        return null;
    }
}
