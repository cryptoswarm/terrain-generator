package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.*;
import ca.uqam.ace.inf5153.mesh.io.Structs.*;

import static Controller.Controller.generateMap;

public class Main {

    public static void main(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        Mesh endMesh = generateMap(parsedArgs);
        parsedArgs.createOutputFile(endMesh);
    }
}
