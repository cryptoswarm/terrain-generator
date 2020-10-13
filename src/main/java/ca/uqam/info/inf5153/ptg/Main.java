package ca.uqam.info.inf5153.ptg;

import Carte.Map;
import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.*;
import ca.uqam.ace.inf5153.mesh.io.Structs.*;
import static Translator.Translator.*;

public class Main {

    public static void main(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);
        try {
            //From file to Map (Move to Converter?) 
            Mesh startMesh = new MeshReader().readFromFile(parsedArgs.getInputFile());
            Map map = generateMapFromMesh(startMesh);

            //Alter Map to create Atoll and Laguna (Move to generator)
            map.createAtoll();
            
            //Resync Mesh with changes done inside Map (Move to Converter)
            Mesh endMesh = syncMeshBuilderWithMap(startMesh, map);
            //Mesh is now resynced
            
            //Write to mesh file
            MeshWriter writer = new MeshWriter();
            writer.writeToFile(endMesh, parsedArgs.getOutputFile());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }







}
