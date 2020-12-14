package userInterface;

import factory.ModeFactory;
import factory.ShapeFactory;
import org.apache.commons.cli.*;
import world.generator.interestPoints.InterestPointsGenerator;
import world.generator.island.IslandShape;
import world.mode.*;


public class UserArgs  {
    private final String inputFile;
    private final String outputFile;

    private final IslandShape islandShape;
    private final int nbWaterSources;
    private final String soilType;
    private Mode heatmapMode;
    private final int seed;
    private final int maxAltitude;
    private final int rivers;
    private final int nbsIsland;
    private boolean productionActivated;
    private int [] pois = {0,0,0};
    private String localisation;
    boolean road;

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
        islandShape = findShapeUsingFactory( options.getOptionValue("shape") );
        nbWaterSources = setWaterSources(options.getOptionValue("water"));
        soilType = setSoilType(options.getOptionValue("soil"));
        productionActivated = options.hasOption("production");
        heatmapMode = setHeatmap(options.getOptionValue("heatmap"));
        seed = setSeed(options.getOptionValue("seed"));
        maxAltitude = setAltitude(options.getOptionValue("altitude"));
        rivers = setRivers(options.getOptionValue("rivers"));
        nbsIsland = setNbsIsland(options.getOptionValue("archipelago"));
        setPois(options.getOptionValues("pois"));
        localisation = setLocalisation(options.getOptionValue("localisation"));
        road = options.hasOption("roads");
    }



    private CommandLine configure(String[] args) throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("i", "input", true,"Input mesh" ));
        opts.addOption(new Option("o", "output", true,"output file" ));
        opts.addOption(new Option("shape", "shape", true,"carte shape" ));
        opts.addOption(new Option("w", "water", true,"generation des aquifères" ));
        opts.addOption(new Option("s", "soil", true,"soil type" ));
        opts.addOption(new Option("seed", "seed", true,"seed"));
        opts.addOption(new Option("altitude", "altitude", true,"altitude"));
        opts.addOption(new Option("p", "pois", true,"Points of interest"));
        opts.addOption(new Option("r", "rivers", true,"rivers"));
        opts.addOption(new Option("a", "archipelago", true,"archipelago"));
        opts.addOption(new Option("production", "production", false,"heatmap ressources"));
        opts.addOption(new Option("h", "heatmap", true,"heatmap"));
        opts.addOption(new Option("l", "localisation", true, "localisation"));
        opts.addOption(new Option("roads", "roads", false, "road"));


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
  
    /**
     *
     * @param shape the shape of the island
     * @return  IslandShape
     */
    public IslandShape findShapeUsingFactory(String shape) {
        return ShapeFactory
                .getIslandShape(shape)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Shape"));
    }

    public IslandShape getIslandShape(){
        return islandShape;
    }

    private Mode setHeatmap(String heatmap) {

        return ModeFactory
                .getMode(heatmap)
                .orElseThrow( () -> new IllegalArgumentException("Invalid! Heatmap should be set to humidity, altitude or ressources\n"+
                                                                 " Else do not set it") );
    }

    public Mode getHeatmapMode() {
        return heatmapMode;
    }

    private int setWaterSources(String nbWaterSources) {
        if (nbWaterSources != null) return Integer.parseInt(nbWaterSources);
        return 0;
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
        }else{
            localisation = "caribbean";
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

    private void setPois(String[] pois){

        if(pois != null) {

            this.productionActivated = true;

            for (String s : pois) {

                String[] arg = s.split(":");

                int num = 0;

                try {
                    num = Integer.parseInt(arg[1]);
                } catch (NumberFormatException e) {
                    System.out.println("--pois option must have format: x:number");
                }

                switch (arg[0]) {
                    case "cities":

                        this.pois[InterestPointsGenerator.POIS.CITIES.ordinal()] += num;

                        break;
                    case "ports":

                        this.pois[InterestPointsGenerator.POIS.PORTS.ordinal()] += num;

                        break;
                    case "villages":

                        this.pois[InterestPointsGenerator.POIS.VILLAGES.ordinal()] += num;

                        break;
                    default:
                        System.out.println("--pois option must have format: x:number where x is cities, ports or villages");
                        break;
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
    public boolean isRoadActivated(){
        return road;
    }
}