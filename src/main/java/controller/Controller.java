package controller;


import map.Carte;
import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.Structs;

import static translator.Translator.*;

public class Controller {
    public static Structs.Mesh generateMap(UserArgs parsedArgs){

        Structs.Mesh startMesh = readMeshFromFile(parsedArgs.getInputFile());

        Carte carte = generateMapFromMesh(startMesh);

        //Alter Map to create Atoll and Laguna (Move to generator)
        System.out.println( " parsedArgs.getShapeForm() should be atol or turtoga =  "+parsedArgs.getShapeSpecification() );
        //System.out.println("parsedArgs.getShapeAsAtoll() ="+parsedArgs.getShapeAsAtoll());
       // String shape = parsedArgs.getShapeForm();
       // System.out.println(" parsedArgs.getShapeForm() should = --shape "+parsedArgs.getShapeForm());
        if( parsedArgs.getShapeSpecification() != null) {
            if (parsedArgs.getShapeSpecification().equals("atoll")) {
                carte.createAtoll();
            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
                carte.createATortuga();
                //do nothing for the moment
            }
        }


       // }

        //carte.createAtoll();

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(startMesh, carte);
        //Mesh is now resynced

        return endMesh;
    }
}
