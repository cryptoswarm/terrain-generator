package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import map.World;
import reader.MeshFileReader;
import reader.Reader;
import writer.Writer;
import map.WorldGenerator;

import static java.lang.System.exit;


public class Main {

    public static void main(String[] args) {



        UserArgs parsedArgs = new UserArgs(args);
        if(!parsedArgs.validateUserArgs()){
            exit(1);
        }

        Reader reader = new MeshFileReader();
        World world =  reader.readFile( parsedArgs.getInputFile());



        world = WorldGenerator.generateWorld(parsedArgs, world);

        Writer writer = new Writer();
        writer.generateEndMesh(parsedArgs, world);

    }
}
