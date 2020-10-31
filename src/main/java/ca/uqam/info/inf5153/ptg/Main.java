package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import map.World;
import reader.MeshFileReader;
import reader.Reader;
import writer.Writer;


public class Main {

    public static void main(String[] args) {

        UserArgs parsedArgs = new UserArgs(args);

        Reader reader = new MeshFileReader();
        World world =  reader.readFile( parsedArgs.getInputFile());

        Writer writer = new Writer();
        writer.generateEndMesh(parsedArgs, world);

    }
    //atoll ou tortga est une liste de tuiles
}
