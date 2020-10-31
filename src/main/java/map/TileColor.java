package map;

public enum TileColor {
    OCEANBLUE("0:64:128:255"),
    WATERBLUE("0:255:255:255"),
    SAND("223:159:0:255"),
    LIGHTGREEN("0:255:63:255"),
    MIDGREEN("0:191:63:255"),
    DARKGREEN("0:127:63:255"),
    GREEN("0:127:63:230"),
    LIGHTGREEN2("0:191:63:179");
    
    private final String rgba;
    
    TileColor(String rgba) {
        this.rgba = rgba;
    }

    @Override
    public String toString() {
        return rgba;
    }


}
