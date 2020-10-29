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

    public  Structs.Mesh generateEndMesh(UserArgs parsedArgs, Carte carte){

        //Alter Map to create Atoll and Laguna (Move to generator)
        System.out.println( " parsedArgs.getShapeForm() should be atol or turtoga =  "+parsedArgs.getShapeSpecification() );
        //System.out.println("parsedArgs.getShapeAsAtoll() ="+parsedArgs.getShapeAsAtoll());
       // String shape = parsedArgs.getShapeForm();
       // System.out.println(" parsedArgs.getShapeForm() should = --shape "+parsedArgs.getShapeForm());
        if( parsedArgs.getShapeSpecification() != null) {

            if (parsedArgs.getShapeSpecification().equals("atoll")) {

                Atoll atoll = carte.createAtoll();

                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );
                    //carte.createAquifere(atoll.getVegetation(), nbWaterSources );
                    carte.createLake(atoll.getVegetation(), nbWaterSources);
                }

            } else if (parsedArgs.getShapeSpecification().equals("tortuga")) {
               Tortuga tortuga = carte.createATortuga();
                if(parsedArgs.getNbWaterSources() != null){
                    int nbWaterSources = Integer.parseInt( parsedArgs.getNbWaterSources() );
                    //carte.createAquifere(tortuga.getVegetation(), nbWaterSources );
                    carte.createLake(tortuga.getVegetation(), nbWaterSources);
                }
            }

        }

        //carte.createAtoll();

        //Resync Mesh with changes done inside Map (Move to Converter)
        Structs.Mesh endMesh = syncMeshBuilderWithMap(parsedArgs, carte);
        //Mesh is now resynced

        return endMesh;
    }

    private Structs.Mesh syncMeshBuilderWithMap(UserArgs parsedArgs, Carte map) {

        //Structs.Mesh startMesh = readMeshFromFile(parsedArgs.getInputFile());

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(parsedArgs.getInputFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return startMesh;

        assert startMesh != null;
        Structs.Mesh.Builder builder = startMesh.toBuilder();

        for(Map.Entry<Dot, Tile> entry:map.getTiles().entrySet() ) {

            Dot center = entry.getKey();
            Tile b = entry.getValue();
            if(b.getBackgroundColor() != null) {
                Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(b.getBackgroundColor().toString()).build();
                builder.getPolygonsBuilder(b.getPolygonId()).addProperties(color);
            }
        }
        return builder.build();
    }

    public void createOutputFile(Structs.Mesh endMesh, UserArgs parsedArgs){
        MeshWriter writer = new MeshWriter();
        try {
            writer.writeToFile(endMesh, parsedArgs.getOutputFile() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
