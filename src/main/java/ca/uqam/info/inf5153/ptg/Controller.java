package ca.uqam.info.inf5153.ptg;

import Translator.Writer;
import UserInterface.UserArgs;
import World.*;
import Translator.Reader;

public class Controller {
    private World world;
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
        world.setMaxAltitude(parsedArgs.getMaxAltitude());
        world.setNbsRiversSrc(parsedArgs.getRivers());

        Reader reader = new Reader(fileName, this);
        reader.readFile();

        world.generateWorld();

        Writer writer = new Writer(this, fileName, outFileName);
        writer.generateEndMesh();
    }

    public void setWorldHeight(int h){
        world.setHeight(h);
    }
    public void setWorldWidth(int w){
        world.setWidth(w);
    }
    public void addWorldTile(float x, float y){
        world.addTile(x,y);
    }
    public void addWorldNeighbor(float x, float y, float nx, float ny) {
        world.addNeighbor(x,y,nx,ny);
    }
    public void addWorldLine(float x, float y, float x1, float y1, float x2, float y2) {
        world.addLine(x,y,x1,y1,x2,y2);
    }
    public  String getWorldTileColor(float x, float y){
        return world.getTileColor(x,y);
    }
    public String getWorldLineColor(float x1, float y1, float x2, float y2){
        return world.getLineColor(x1,y1,x2,y2);
    }

}
