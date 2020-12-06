package userInterface;

import world.mode.*;
import org.apache.commons.cli.*;

public class UserArgs  {
    private final String inputFile;
    private final String outputFile;
    private final String shape;
    private final int nbWaterSources;
    private final String soilType;
    private String heatmap;
    private final int seed;
    private final int maxAltitude;
    private final int rivers;
    private final int nbsIsland;
    private boolean productionActivated;
    private String localisation;

    public UserArgs(String[] args) {
        CommandLine options = null;

        try {
            options = configure(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert options != null;
        inputFile = setInputFile(options.getOptionValue("i"));
        outputFile = setOutputFile(options.getOptionValue("o"));
        shape = setShape(options.getOptionValue("shape"));
        nbWaterSources = setWaterSources(options.getOptionValue("water"));
        soilType = setSoilType(options.getOptionValue("soil"));
        productionActivated = isProductionActivated( options.getOptionValue("production"), options);
        //heatmap = setHeatmap( options.getOptionValue("heatmap") );
        seed = setSeed(options.getOptionValue("seed"));
        maxAltitude = setAltitude(options.getOptionValue("altitude"));
        rivers = setRivers(options.getOptionValue("rivers"));
        nbsIsland = setNbsIsland(options.getOptionValue("archipelago"));
        localisation = setLocalisation(options.getOptionValue("localisation"));
    }

    private static CommandLine configure(String[] args) throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("i", "input", true,"Input mesh" ));
        opts.addOption(new Option("o", "output", true,"output file" ));
        opts.addOption(new Option("shape", "shape", true,"carte shape" ));
        opts.addOption(new Option("w", "water", true,"generation des aquifères" ));
        opts.addOption(new Option("s", "soil", true,"soil type" ));
        opts.addOption(new Option("seed", "seed", true,"seed"));
        opts.addOption(new Option("altitude", "altitude", true,"altitude"));
        opts.addOption(new Option("r", "rivers", true,"rivers"));
        opts.addOption(new Option("a", "archipelago", true,"archipelago"));
        opts.addOption(new Option("production", "production", true,"heatmap ressources"));
        opts.addOption(new Option("h", "heatmap", true,"heatmap"));
        opts.addOption(new Option("l", "localisation", true, "localisation"));

        CommandLineParser parser = new DefaultParser();
        return parser.parse(opts, args);
    }

    private String setInputFile(String inputFile) {
        if(inputFile != null){

            return inputFile;
        }else {
            throw new IllegalArgumentException("-i must be provided!");
        }

    }
    private String setOutputFile(String outputFile) {
        if(inputFile != null){
            return outputFile;
        }else {
            throw new IllegalArgumentException("-o must be provided!");
        }

    }
    private String setShape(String shape){
        if(shape != null){
            if(shape.equals("atoll") || shape.equals("tortuga") || shape.equals("archipelago")){
                return shape;
            }else {
                throw new IllegalArgumentException("Undefined island shape");
            }
        }else {
            return "atoll";
        }

    }
    private int setWaterSources(String nbWaterSources) {
        if (nbWaterSources != null) return Integer.parseInt(nbWaterSources);
        return 0;
    }


    private boolean isProductionActivated(String production, CommandLine options){
        boolean productionActivated = true;
        String[] tab;
        if( production != null ){
            //tab = production.split(" ");
            //setHeatmap(tab[1]);
            heatmap = setHeatmap("ressources");
        }else {
            heatmap = setHeatmap( options.getOptionValue("heatmap") );
            productionActivated = false;
        }
        return productionActivated;
    }


    private String setHeatmap(String heatmap) {

        if (heatmap != null) {
            if (heatmap.equals("altitude") || heatmap.equals("humidity") || heatmap.equals("ressources")) {
                return heatmap;
            } else {
                throw new IllegalArgumentException("Undefined heatmap");
            }
        } else {
            return  "normal";
        }
    }

    public Mode getHeatmap() {

        Mode mode;

        if( heatmap.equals("altitude")){
            mode = new Altitude();
        }else if( heatmap.equals("humidity")) {
            mode = new Humidity();
        }else if( heatmap.equals("ressources")){
            mode = new Ressources();
        }else{
            mode = new Normal();
        }
        return mode;
    }

    private String setLocalisation(String localisation) {

        if(localisation != null) {
            if(!localisation.equals("baiejames") &&
                    !localisation.equals("caribbean") &&
                    !localisation.equals("sahara") &&
                    !localisation.equals("antarctica") &&
                    !localisation.equals("africa") &&
                    !localisation.equals("manitoba")){
                localisation = "caribbean";
            }
        }
        return localisation;
    }

    private int setAltitude(String altitude) {
        if(altitude == null){
            return 100;
        }else{
            return Integer.parseInt(altitude);
        }
    }

    private String setSoilType(String soil){
        if (soil != null){
            if(soil.equals("wet") || soil.equals("dry") || soil.equals("regular")){
                return soil;
            }else {
                throw new IllegalArgumentException("Undefined soil type");
            }
        }else {
            return "regular";
        }

    }
    private int setSeed(String seed){
        if (seed != null){
            return Integer.parseInt(seed);
        }else {
            return 0;
        }
    }
    private int setRivers(String rivers) {
        if (rivers != null){
            return Integer.parseInt(rivers);
        }else {
            return 0;
        }
    }
    private int setNbsIsland(String nbsIsland){
        if(nbsIsland != null) return Integer.parseInt(nbsIsland);
        return 1;
    }

    public String getOutputFile() {
        return outputFile;
    }
    public String getInputFile() {
        return inputFile;
    }
    public String getShape() {
        return shape;
    }

    public int getNbWaterSources() {
        return nbWaterSources;
    }
    public String getSoilType() {
        return soilType;
    }



    public int getMaxAltitude() {
        return maxAltitude;
    }
    public int getSeed(){
        return seed;
    }
    public int getRivers() {return rivers;}
    public int getNbsIsland() {
        return nbsIsland;
    }

    public String getLocalisation() {
        return localisation;
    }
    public boolean isProductionActivated() {
        return productionActivated;
    }
}