package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import geometrie.Coordinate;
import map.Tile;
import map.TileColor;
import map.World;
import map.WorldGenerator;
import reader.MeshFileReader;
import reader.Reader;


import static writer.Writer.generateEndMesh;

public class Controller {
    private static World world = new World();

    private enum MODE{
        NORMAL,
        WATER_MAP,
        ALTITUDE_MAP
    }

    private static MODE mode = null;

    public static void createWorld(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        String fileName = parsedArgs.getInputFile();
        String outFileName = parsedArgs.getOutputFile();
        setMode(parsedArgs.getHeatmap());

        Reader reader = new MeshFileReader();

        reader.readFile(fileName);
        world.setSoil(parsedArgs.getSoilType());
        world = WorldGenerator.generateWorld(parsedArgs, world);

        generateEndMesh(outFileName, fileName, world);
    }

    public static void setWorldHeight(int height){
        world.setHeight(height);
    }
    public static void setWorldWidth(int width){
        world.setWidth(width);
    }
    public static void addTile(float x, float y){
        world.addTile(new Tile(new Coordinate(x,y,0)));
    }
    public static void addNeighbor(float x, float y, float nx, float ny) {
        Tile tile = world.getTiles().get(new Coordinate(x,y,0));
        Tile tileNeighbor = world.getTiles().get(new Coordinate(nx,ny,0));
        tile.addNeighbor(tileNeighbor);
    }
    public static String getTileColor(float x, float y){
        Tile tile = world.getTiles().get(new Coordinate(x,y,0));
        TileColor color = getColor(tile);
        String colorRGB = null;
        int value = 0;

        int g = color.getG();

        if(mode == MODE.NORMAL) {
            if (world.getVegetation().getTiles().get(tile.getCenter()) != null) {
                g = applyFactor(g, tile.getHumidityLevel());
            }
            colorRGB = color.getR() + ":" + g + ":" + color.getB() + ":" + color.getA();

        }else if(mode == MODE.WATER_MAP){

            if (world.getVegetation().getTiles().get(tile.getCenter()) != null || world.getPlage().getTiles().get(tile.getCenter()) != null) {
                value = applyFactor(g, tile.getHumidityLevel());
            }else{

                return 0 + ":" + 0 + ":" + 0 + ":" + 255;
            }

            if (value == 0){

                return 0 + ":" + 0 + ":" + 255 + ":" + 255;

            }else {

                colorRGB = color.getR() + ":" + color.getG() + ":" + 255 + ":" + (255 - value);

            }

        }else if(mode == MODE.ALTITUDE_MAP){

            value = applyFactor(0, (int) tile.getAltitude());

            if (value == 0){
                return 0 + ":" + 0 + ":" + 0 + ":" + 255;
            }else{
                colorRGB = color.getR() + ":" + color.getG() + ":" + color.getB() + ":" + value;

            }


        }
        return colorRGB;
    }

    private static int applyFactor(int g, int factor){
        if (factor > 0){
            if (70 + factor >= 255) return 255;
            return 70 + factor;
        }
        return g;
    }

    private static TileColor getColor(Tile tile){

        TileColor color = null;

        switch (mode){

            case NORMAL:
                color = tile.getBackgroundColor();
                break;
            case WATER_MAP:
                color = TileColor.DARKBLUE;
                break;
            case ALTITUDE_MAP:
                color = TileColor.BROWN;
        }

        return color;

    }

    public static void setMode(String userArg){

        if(userArg != null){

            if (userArg.equals("altitude")){
                mode = MODE.ALTITUDE_MAP;
            }else if (userArg.equals("humidity")){
                mode = MODE.WATER_MAP;
            }

        }



    };
}
