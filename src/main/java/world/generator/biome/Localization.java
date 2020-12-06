package world.generator.biome;

public enum Localization {
    BAIEJAMES(100, -5),
    CARIBBEAN(150, 25);

    Localization(int precipitationAverage, int temperatureAverage) {
        this.precipitationAverage = precipitationAverage;
        this.temperatureAverage = temperatureAverage;
    }

    private int precipitationAverage;
    private int temperatureAverage;

    public int getPrecipitationAverage() {
        return precipitationAverage;
    }

    public int getTemperatureAverage() {
        return temperatureAverage;
    }
}
