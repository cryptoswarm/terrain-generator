package writer;


import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.MeshWriter;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import ca.uqam.info.inf5153.ptg.Controller;
import geometrie.Coordinate;
import map.*;

import java.io.IOException;
import java.lang.reflect.Constructor;


public class Writer {

    /**
     *
     * @param parsedArgs  les arguments entrés par l'utilisateur
     * @param world   la carte qu'on a précedement construit a partir du mesh initiale
     * @return
     */
    public static void generateEndMesh(String outFileName, String fileName, World world){
        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Structs.Mesh endMesh = syncMeshBuilderWithMap(startMesh, world);
        createOutputFile(endMesh, outFileName);
    }


    public static Structs.Mesh syncMeshBuilderWithMap(Structs.Mesh startMesh, World world) {
        Structs.Mesh.Builder builder = startMesh.toBuilder();
        for (int i = 0; i < builder.getPolygonsCount(); i++) {
            Structs.Polygon p = builder.getPolygons(i);
            float x = builder.getPoints(p.getCentroidIdx()).getX();
            float y = builder.getPoints(p.getCentroidIdx()).getY();
            String tileColor = Controller.getTileColor(x, y);
            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(tileColor).build();
            builder.getPolygonsBuilder(i).addProperties(color);
        }
        Structs.Mesh endMesh = builder.build();
        return endMesh;
    }

    private static void createOutputFile(Structs.Mesh endMesh, String fileName){
        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
