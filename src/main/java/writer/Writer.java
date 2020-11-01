package writer;


import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.MeshWriter;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Dot;
import map.*;

import java.io.IOException;


public class Writer {

    /**
     *
     * @param parsedArgs  les arguments entrés par l'utilisateur
     * @param world   la carte qu'on a précedement construit a partir du mesh initiale
     * @return
     */
    public static Structs.Mesh generateEndMesh(UserArgs parsedArgs, World world){
        Structs.Mesh endMesh = syncMeshBuilderWithMap(parsedArgs, world);
        createOutputFile(endMesh, parsedArgs);
        return endMesh;
    }

    /**
     * Itérer sur les tuiles composant la carte et synchroniser avec les polygones composant le mesh
     * @param parsedArgs  les arguments de l'utilisateur
     * @param world  la carte apres qu'on ait ajouté tous ce qu'on besoin
     *
     * @return  un mesh
     */
    private static Structs.Mesh syncMeshBuilderWithMap(UserArgs parsedArgs, World world) {

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(parsedArgs.getInputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert startMesh != null;
        Structs.Mesh.Builder builder = startMesh.toBuilder();

        for(java.util.HashMap.Entry<Dot, Tile> entry: world.getTiles().entrySet() ) {

            Dot center = entry.getKey();
            Tile b = entry.getValue();
            if(b.getBackgroundColor() != null) {
                Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(b.getBackgroundColor().toString()).build();
                builder.getPolygonsBuilder(b.getPolygonId()).addProperties(color);
            }
        }
        return builder.build();
    }

    private static void createOutputFile(Structs.Mesh endMesh, UserArgs parsedArgs){
        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, parsedArgs.getOutputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
