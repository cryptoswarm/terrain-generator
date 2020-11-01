package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import map.World;
import reader.MeshFileReader;
import reader.Reader;
import map.WorldGenerator;
import static writer.Writer.generateEndMesh;


public class Main {

    public static void main(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);

        Reader reader = new MeshFileReader();
        World world =  reader.readFile( parsedArgs.getInputFile());
        world = WorldGenerator.generateWorld(parsedArgs, world);
        generateEndMesh(parsedArgs, world);

    }
}
