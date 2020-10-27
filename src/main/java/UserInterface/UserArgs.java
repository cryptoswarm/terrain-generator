package UserInterface;

import ca.uqam.ace.inf5153.mesh.io.MeshWriter;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import org.apache.commons.cli.*;

import java.io.IOException;

public class UserArgs {
    private String inputFile;
    private String outputFile;
    //private String shapeForm;
    //private String shapeAsAtoll;
    //private String shapeAsTortuga;
    private String shapeSpecification;
    private String nbWaterSources;


    public UserArgs(String[] args){
        CommandLine options = null;
        try {
            options = configure(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inputFile = options.getOptionValue("i");
        outputFile = options.getOptionValue("o");
        //shapeForm = options.getOptionValue("shape");
        shapeSpecification= options.getOptionValue("shape");
        nbWaterSources =  options.getOptionValue("water") ;
        //shapeAsTortuga = options.getOptionValue("tortuga");
    }

    private static CommandLine configure(String[] args) throws ParseException {
        Options opts = new Options();
        opts.addOption(new Option("i", "input", true,"Input mesh" ));
        opts.addOption(new Option("o", "output", true,"output file" ));
        opts.addOption(new Option("shape", "shape", true,"carte shape" ));
        opts.addOption(new Option("atoll", "atoll", false,"carte shape as atoll" ));
        opts.addOption(new Option("tortuga", "tortuga", false,"carte shape as tortuga" ));
        opts.addOption(new Option("water", "water", true,"generation des aquiferes" ));

        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(opts, args);
        if (! cl.hasOption("i") || ! cl.hasOption("o"))
            throw new IllegalArgumentException("-i and -o must be provided!");
        return cl;
    }

    public void createOutputFile(Structs.Mesh endMesh){
        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOutputFile() {
        return outputFile;
    }
    public String getInputFile() {
        return inputFile;
    }
/*
    public String getShapeForm() {
        return shapeForm;
    }

    public String getShapeAsAtoll() {
        return shapeAsAtoll;
    }

    public String getShapeAsTortuga() {
        return shapeAsTortuga;
    }

 */

    public String getShapeSpecification() {
        return shapeSpecification;
    }

    public String getNbWaterSources() {
        return nbWaterSources;
    }
}
