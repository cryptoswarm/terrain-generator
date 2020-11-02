package map;

public enum soilType {
    DRY("dry", 100),
    REGULAR("regular",200),
    WET("wet", 400);

    soilType(String soilString, int affectedDistance) {
        this.soilString = soilString;
        this.affectedDistance = affectedDistance;
    }

    private final String soilString;
    private final int affectedDistance;

    public int getAffectedDistance() {
        return affectedDistance;
    }
    public static soilType getSoilType(String soil){
        if(soil.equals(DRY.soilString)) return DRY;
        if(soil.equals(REGULAR.soilString)) return REGULAR;
        if(soil.equals(WET.soilString)) return WET;
        return null;
    }

    @Override
    public String toString() {
        return soilString;
    }
}
