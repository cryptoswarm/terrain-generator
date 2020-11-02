package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import geometrie.Coordinate;
import map.*;
import reader.MeshFileReader;
import reader.Reader;


import static writer.Writer.generateEndMesh;

public class Controller {
    private static World world = new World();
    private static MODE m;
    private enum MODE{
        NORMAL,
        WATER_MAP,
        ALTITUDE_MAP
    }

    private static Mode mode = null;

    public static void createWorld(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        String fileName = parsedArgs.getInputFile();
        String outFileName = parsedArgs.getOutputFile();
        m = null;
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
        TileColor color = tile.getBackgroundColor();
        int factor = 0;
        if(m == MODE.NORMAL || m == MODE.WATER_MAP) factor = tile.getHumidityLevel();
        if(m == MODE.ALTITUDE_MAP) factor = (int)tile.getAltitude();

        return mode.getColor(color.getR(), color.getG(), color.getB(), color.getA(), factor);

    }

    public static void setMode(String userArg){
        if(userArg != null){
            if (userArg.equals("altitude")){
                mode = new Altitude();
                m = MODE.ALTITUDE_MAP;
            }else if (userArg.equals("humidity")){
                mode = new Humidity();
                m = MODE.WATER_MAP;
            } else {
                mode = new Normal();
                m = MODE.NORMAL;
            }
        }
        m = MODE.NORMAL;
    };
}
