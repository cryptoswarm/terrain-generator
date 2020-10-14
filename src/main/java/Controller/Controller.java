package Controller;


import Map.Map;
import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.Structs;

import static Translator.Translator.*;

public class Controller {
    public static Structs.Mesh generateMap(UserArgs parsedArgs){

        Structs.Mesh startMesh = readMeshFromFile(parsedArgs.getInputFile());

        Map map = generateMapFromMesh(startMesh);

        //Alter Map to create Atoll and Laguna (Move to generator)
        map.createAtoll();

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(startMesh, map);
        //Mesh is now resynced

        return endMesh;
    }
}
