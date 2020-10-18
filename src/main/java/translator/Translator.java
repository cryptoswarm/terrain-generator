package translator;

import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Coordonnee;
import geometrie.Dot;
import map.Carte;
import map.Tile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class Translator {

    public static Structs.Mesh readMeshFromFile(String fileName){
        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return startMesh;
    }

    public static Carte generateMapFromMesh(Structs.Mesh startMesh) {
        int width = Integer.parseInt(readMetadata(startMesh, "width"));
        int height = Integer.parseInt(readMetadata(startMesh, "height"));

        Carte carte = new Carte(width,height);

        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {

            Structs.Point tileCenter = startMesh.getPoints(polygon.getCentroidIdx());

            Dot dot = new Dot( new Coordonnee(tileCenter.getX(), tileCenter.getY(), 0) );
            Tile newTile = new Tile(polygon, dot, polygon.getCentroidIdx() );


            for (int neighborId: polygon.getNeighborsList()) {
                Structs.Point pt = startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx());

                newTile.addNeighborPseudoCenter(new Dot(new Coordonnee(pt.getX(), pt.getY(), 0)));
            }

            carte.addTile(newTile);

        }
        return carte;
    }

    public static Structs.Mesh syncMeshBuilderWithMap(Structs.Mesh startMesh, Carte map) {
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

    private static String readMetadata(Structs.Mesh m, String key) {
        Optional<Structs.Property> prop = m.getPropertiesList().stream().filter(p -> p.getKey().equals(key)).findFirst();
        if (prop.isPresent()) {
            return prop.get().getValue();
        } else {
            throw new IllegalArgumentException("Missing property [" + key + "]");
        }
    }
}
