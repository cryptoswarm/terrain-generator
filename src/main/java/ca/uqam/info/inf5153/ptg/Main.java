package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.*;
import ca.uqam.ace.inf5153.mesh.io.Structs.*;

import static Controller.Controller.generateMap;

public class Main {

    public static void main(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        Mesh endMesh = generateMap(parsedArgs);
        try {
            //Write to mesh file
            MeshWriter writer = new MeshWriter();
            writer.writeToFile(endMesh, parsedArgs.getOutputFile());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }







}
