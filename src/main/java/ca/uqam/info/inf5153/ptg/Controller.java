package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import World.*;
import Translator.Reader;


import static Translator.Writer.generateEndMesh;

public class Controller {
    private  World world;
    public  void createWorld(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        String fileName = parsedArgs.getInputFile();
        String outFileName = parsedArgs.getOutputFile();

        world = new World();
        world.setSeed(parsedArgs.getSeed());
        world.setMode(parsedArgs.getHeatmap());
        world.setSoil(parsedArgs.getSoilType());
        world.setNbsWaterSource(parsedArgs.getNbWaterSources());
        world.setShape(parsedArgs.getShape());


        Reader reader = new Reader();
        reader.readFile(this, fileName);

        world.generateWorld();

        generateEndMesh(this,outFileName, fileName);
    }

    public  void setWorldHeight(int h){
        world.setHeight(h);
    }
    public  void setWorldWidth(int w){
        world.setWidth(w);
    }
    public  void addWorldTile(float x, float y){
        world.addTile(x,y);
    }
    public  void addWorldNeighbor(float x, float y, float nx, float ny) {
        world.addNeighbor(x,y,nx,ny);
    }
    public  String getWorldTileColor(float x, float y){
        return world.getTileColor(x,y);
    }

}
