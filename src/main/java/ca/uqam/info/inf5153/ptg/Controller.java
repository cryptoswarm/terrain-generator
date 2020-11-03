package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import World.*;
import Translator.Reader;


import static Translator.Writer.generateEndMesh;
import static World.WorldGenerator.*;

public class Controller {

    public static void createWorld(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        String fileName = parsedArgs.getInputFile();
        String outFileName = parsedArgs.getOutputFile();

        newWorld();
        setMode(parsedArgs.getHeatmap());
        setSoil(parsedArgs.getSoilType());
        setNbsWaterSource(parsedArgs.getNbWaterSources());
        setShape(parsedArgs.getShape());


        Reader reader = new Reader();
        reader.readFile(fileName);

        WorldGenerator.generateWorld();

        generateEndMesh(outFileName, fileName);
    }

    public static void setWorldHeight(int height){
        setHeight(height);
    }
    public static void setWorldWidth(int width){
        setWidth(width);
    }
    public static void addWorldTile(float x, float y){
        addTile(x,y);
    }
    public static void addWorldNeighbor(float x, float y, float nx, float ny) {
        addNeighbor(x,y,nx,ny);
    }
    public static String getWorldTileColor(float x, float y){
        return getTileColor(x,y);
    }

}
