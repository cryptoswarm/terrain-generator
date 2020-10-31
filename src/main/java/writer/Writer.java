package writer;


import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.MeshWriter;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Dot;
import map.*;

import java.io.IOException;


public class Writer { //writer

    //reader lire le mesh  pour generer la carte
    //writer generer le  mesh a partir de la carte

    /**
     *
     * @param parsedArgs  les arguments entrés par l'utilisateur
     * @param world   la carte qu'on a précedement construit a partir du mesh initiale
     * @return
     */

    public  Structs.Mesh generateEndMesh(UserArgs parsedArgs, World world){

        //Alter Map to create Atoll and Laguna (Move to generator)
        //System.out.println( " parsedArgs.getShapeForm() should be atol or turtoga =  "+parsedArgs.getShapeSpecification() );
        //System.out.println("parsedArgs.getShapeAsAtoll() ="+parsedArgs.getShapeAsAtoll());
       // String shape = parsedArgs.getShapeForm();
       // System.out.println(" parsedArgs.getShapeForm() should = --shape "+parsedArgs.getShapeForm());
/*
        for(Map.Entry<Tile, HashSet<Tile>> entry:carte.getTileAndNeighbors2().entrySet() ){
            Tile tile = entry.getKey();
            HashSet<Tile> b = entry.getValue();
            System.out.println("tile = "+tile.getTilePseudoCenter().getCoordonnee().toString());
            System.out.println("tile voisins = ");
            for (Tile tile1 : b) {
                System.out.println(tile1.getTilePseudoCenter().getCoordonnee().toString());
            }
        }
*/
        if( parsedArgs.getShapeSpecification() != null) {

            if (parsedArgs.getShapeSpecification().equals("atoll")) {

                Atoll atoll = world.createAtoll();

                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );

                    //carte.createLake(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                    world.createNape(atoll.getVegetation(), nbWaterSources, parsedArgs.getSoilType());
                }
                if( parsedArgs.getSoilType() != null){

                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
               Tortuga tortuga = world.createATortuga();
                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );
                    world.createLake(tortuga.getVegetation(), nbWaterSources, parsedArgs.getSoilType() );
                }
            }
        }

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(parsedArgs, world);
        //Mesh is now resynced

        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, parsedArgs.getOutputFile() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return endMesh;
    }

    /**
     * Eterer sur les tuiles composant la carte et synchroniser avec les polygones composant le mesh
     * @param parsedArgs  les arguments de l'utilisateur
     * @param world  la carte apres qu'on ait ajouté tous ce qu'on besoin
     *
     * @return  un mesh
     */

    private Structs.Mesh syncMeshBuilderWithMap(UserArgs parsedArgs, World world) {

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(parsedArgs.getInputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert startMesh != null;
        Structs.Mesh.Builder builder = startMesh.toBuilder();

        for(java.util.Map.Entry<Dot, Tile> entry: world.getTiles().entrySet() ) {

            Dot center = entry.getKey();
            Tile b = entry.getValue();
            if(b.getBackgroundColor() != null) {
                Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(b.getBackgroundColor().toString()).build();
                builder.getPolygonsBuilder(b.getPolygonId()).addProperties(color);
            }
        }
/*
        for(Map.Entry<Tile, HashSet<Dot>> entry:carte.getTileAndNeighbors().entrySet() ) {

            Tile tile = entry.getKey();

            if(tile.getBackgroundColor() != null) {
                Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(tile.getBackgroundColor().toString()).build();
                builder.getPolygonsBuilder(tile.getPolygonId()).addProperties(color);
            }

        }
*/
        return builder.build();
    }



    /*
    public void createOutputFile(Structs.Mesh endMesh, UserArgs parsedArgs){
        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, parsedArgs.getOutputFile() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 */

}
