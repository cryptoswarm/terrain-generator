package UserInterface;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class UserArgs {
    final private String inputFile;
    final private String outputFile;
    final private String shape;
    final private int nbWaterSources;
    final private String soilType;
    final private String heatmap;
    final private int seed;
    final private int maxAltitude;
    final private int rivers;
    final private int nbsIsland;

    public UserArgs(String[] args){
        CommandLine options = null;

        try {
            options = configure(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inputFile = setInputFile(options.getOptionValue("i"));
        outputFile = setOutputFile(options.getOptionValue("o"));
        shape = setShape(options.getOptionValue("shape"));
        nbWaterSources = setWaterSources(options.getOptionValue("water"));
        soilType = setSoilType(options.getOptionValue("soil"));
        heatmap = setHeatmap(options.getOptionValue("heatmap"));
        seed = setSeed(options.getOptionValue("seed"));
        maxAltitude = setAltitude(options.getOptionValue("altitude"));
        rivers = setRivers(options.getOptionValue("rivers"));
        nbsIsland = setNbsIsland(options.getOptionValue("archipelago"));
    }

    private static CommandLine configure(String[] args) throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("i", "input", true,"Input mesh" ));
        opts.addOption(new Option("o", "output", true,"output file" ));
        opts.addOption(new Option("shape", "shape", true,"carte shape" ));
        opts.addOption(new Option("atoll", "atoll", false,"carte shape as atoll" ));
        opts.addOption(new Option("tortuga", "tortuga", false,"carte shape as tortuga" ));
        opts.addOption(new Option("water", "water", true,"generation des aquifères" ));
        opts.addOption(new Option("soil", "soil", true,"soil type" ));
        opts.addOption(new Option("heatmap", "heatmap", true,"heatmap"));
        opts.addOption(new Option("seed", "seed", true,"seed"));
        opts.addOption(new Option("altitude", "altitude", true,"altitude"));
        opts.addOption(new Option("rivers", "rivers", true,"rivers"));
        opts.addOption(new Option("archipelago", "archipelago", true,"archipelago"));

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
        if (heatmap != null){
            if(heatmap.equals("altitude") || heatmap.equals("humidity")){
                return heatmap;
            } else {
                throw new IllegalArgumentException("Undefined heatmap");
            }
        } else {
            return "normal";
        }
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
        return 0;
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
    public String getHeatmap() {
        return heatmap;
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
}