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

    public static void createWorld(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        String fileName = parsedArgs.getInputFile();
        String outFileName = parsedArgs.getOutputFile();

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
        int g = color.getG();
        if( world.getVegetation().getTiles().get(tile.getCenter()) != null) {
            g = applyHumidity(g, tile.getHumidityLevel());
        }
        String colorRGB = color.getR() + ":" + g + ":" + color.getB() + ":" + color.getA();

        return colorRGB;
    }
    private static int applyHumidity(int g, int humidityLevel){
        if (humidityLevel != 0){
            if (70 + humidityLevel >= 255) return 255;
            return 70 + humidityLevel;
        }
        return g;
    }
}
