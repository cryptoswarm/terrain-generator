package UserInterface;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class UserArgs {
    private String inputFile;
    private String outputFile;
    private String shape;
    private String nbWaterSources;
    private String soilType;
    private String heatmap;


    public UserArgs(String[] args){
        CommandLine options = null;

        try {
            options = configure(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inputFile = options.getOptionValue("i");
        outputFile = options.getOptionValue("o");
        shape = options.getOptionValue("shape");
        nbWaterSources =  options.getOptionValue("water") ;
        soilType =  options.getOptionValue("soil");
        heatmap = options.getOptionValue("heatmap");
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

        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(opts, args);
        if (! cl.hasOption("i") || ! cl.hasOption("o"))
            throw new IllegalArgumentException("-i and -o must be provided!");
        return cl;
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
        return Integer.parseInt(nbWaterSources);
    }
    public String getSoilType() {
        return soilType;
    }
    public String getHeatmap() {
        return heatmap;
    }
}
