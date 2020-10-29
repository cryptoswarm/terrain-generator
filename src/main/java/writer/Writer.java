package writer;


import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.MeshWriter;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Dot;
import map.Atoll;
import map.Carte;
import map.Tile;
import map.Tortuga;

import java.io.IOException;
import java.util.Map;


public class Writer { //writer

    //reader lire le mesh  pour generer la carte
    //writer generer le  mesh a partir de la carte

    /**
     *
     * @param parsedArgs  les arguments entrés par l'utilisateur
     * @param carte   la carte qu'on a précedement construit a partir du mesh initiale
     * @return
     */

    public  Structs.Mesh generateEndMesh(UserArgs parsedArgs, Carte carte){

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

                Atoll atoll = carte.createAtoll();

                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );
                    carte.createLake(atoll.getVegetation(), nbWaterSources);
                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
               Tortuga tortuga = carte.createATortuga();
                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );
                    carte.createLake(tortuga.getVegetation(), nbWaterSources);
                }
            }
        }

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(parsedArgs, carte);
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
     * @param carte  la carte apres qu'on ait ajouté tous ce qu'on besoin
     *
     * @return  un mesh
     */

    private Structs.Mesh syncMeshBuilderWithMap(UserArgs parsedArgs, Carte carte) {

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(parsedArgs.getInputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert startMesh != null;
        Structs.Mesh.Builder builder = startMesh.toBuilder();

        for(Map.Entry<Dot, Tile> entry:carte.getTiles().entrySet() ) {

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
