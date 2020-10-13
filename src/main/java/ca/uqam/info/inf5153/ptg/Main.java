package ca.uqam.info.inf5153.ptg;

import Carte.Map;
import Carte.PseudoPoint;
import Carte.Tile;
import Carte.TileColor;
import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.*;
import ca.uqam.ace.inf5153.mesh.io.Structs.*;
import ca.uqam.ace.inf5153.mesh.io.Structs.Point;
import ca.uqam.ace.inf5153.mesh.io.Structs.Polygon;

import java.util.Optional;

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
            Mesh.Builder builder = startMesh.toBuilder();
            Mesh endMesh = syncMeshBuilderWithMap(builder, map);
            //Mesh is now resynced
            
            //Write to mesh file
            MeshWriter writer = new MeshWriter();
            writer.writeToFile(endMesh, parsedArgs.getOutputFile());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Mesh syncMeshBuilderWithMap(Mesh.Builder builder, Map map) {
        for (int i = 0; i < builder.getPolygonsCount(); i++) {
            Polygon p = builder.getPolygons(i);
            TileColor tileColor = map.getTileColor(builder.getPoints(p.getCentroidIdx()));
            Property color = Property.newBuilder().setKey("color").setValue(tileColor.toString()).build();
            builder.getPolygonsBuilder(i).addProperties(color);
        }
        Mesh endMesh = builder.build();
        return endMesh;
    }

    private static Map generateMapFromMesh(Mesh startMesh) {
        int width = Integer.parseInt(readMetadata(startMesh, "width"));
        int height = Integer.parseInt(readMetadata(startMesh, "height"));
        Map map = new Map(width,height);

        for (Polygon polygon: startMesh.getPolygonsList()) {
            Point tileCenter = startMesh.getPoints(polygon.getCentroidIdx());
            Tile newTile = new Tile(polygon, tileCenter);
            
            for (int neighborId: polygon.getNeighborsList()) {
                newTile.addNeighborPseudoCenter(new PseudoPoint(startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx())));
            }
            
            map.addTile(newTile);
        }
        return map;
    }

    private static String readMetadata(Mesh m, String key) {
        Optional<Property> prop = m.getPropertiesList().stream().filter(p -> p.getKey().equals(key)).findFirst();
        if (prop.isPresent()) {
            return prop.get().getValue();
        } else {
            throw new IllegalArgumentException("Missing property [" + key + "]");
        }
    }

}
