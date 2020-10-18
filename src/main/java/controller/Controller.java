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
        carte.createAtoll();

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(startMesh, carte);
        //Mesh is now resynced

        return endMesh;
    }
}
