package userInterface;

import world.TileColor;
import world.generator.interestPoints.InterestPointsGenerator;
import world.mode.*;
import org.apache.commons.cli.*;

import static world.TileColor.*;


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
    private int [] pois = {0,0,0};



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
        productionActivated = options.hasOption("production");
        heatmap = setHeatmap(options.getOptionValue("heatmap"));
        seed = setSeed(options.getOptionValue("seed"));
        maxAltitude = setAltitude(options.getOptionValue("altitude"));
        rivers = setRivers(options.getOptionValue("rivers"));
        nbsIsland = setNbsIsland(options.getOptionValue("archipelago"));
        setPois(options.getOptionValues("pois"));
    }

    private static CommandLine configure(String[] args) throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("i", "input", true,"Input mesh" ));
        opts.addOption(new Option("o", "output", true,"output file" ));
        opts.addOption(new Option("shape", "shape", true,"carte shape" ));
        opts.addOption(new Option("water", "water", true,"generation des aquifères" ));
        opts.addOption(new Option("soil", "soil", true,"soil type" ));
        opts.addOption(new Option("seed", "seed", true,"seed"));
        opts.addOption(new Option("altitude", "altitude", true,"altitude"));
        opts.addOption(new Option("rivers", "rivers", true,"rivers"));
        opts.addOption(new Option("archipelago", "archipelago", true,"archipelago"));
        opts.addOption(new Option("production", "production", false,"heatmap ressources"));
        opts.addOption(new Option("heatmap", "heatmap", true,"heatmap"));
        opts.addOption(new Option("pois", "pois", true,"Points of interest"));

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


    private String setHeatmap(String heatmap) {

        if (heatmap != null) {
            if (heatmap.equals("altitude") || heatmap.equals("humidity") || heatmap.equals("ressources")) {
                if(heatmap.equals("ressources")){

                    productionActivated = true;
                }
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

    private void setPois(String[] pois){

        if(pois != null) {

            this.productionActivated = true;

            for (int i = 0; i < pois.length; i++) {

                String[] arg = pois[i].split(":");

                int num = 0;

                try {
                    num = Integer.parseInt(arg[1]);
                } catch (NumberFormatException e) {
                    System.out.println("--pois option must have format: x:number");
                }


                if (arg[0].equals("cities")) {

                    this.pois[InterestPointsGenerator.POIS.CITIES.ordinal()] += num;

                } else if (arg[0].equals("ports")) {

                    this.pois[InterestPointsGenerator.POIS.PORTS.ordinal()] += num;

                } else if (arg[0].equals("villages")) {

                    this.pois[InterestPointsGenerator.POIS.VILLAGES.ordinal()] += num;

                } else {
                    System.out.println("--pois option must have format: x:number where x is cities, ports or vilages");
                }
            }


        }

    }

    public int[] getPois(){
        return this.pois;
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

    public boolean isProductionActivated() {
        return productionActivated;
    }
}