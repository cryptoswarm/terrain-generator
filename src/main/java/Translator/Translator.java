package Translator;

import Carte.Map;
import Carte.PseudoPoint;
import Carte.Tile;
import Carte.TileColor;
import ca.uqam.ace.inf5153.mesh.io.Structs;

import java.util.Optional;

public class Translator {
    public static Map generateMapFromMesh(Structs.Mesh startMesh) {
        int width = Integer.parseInt(readMetadata(startMesh, "width"));
        int height = Integer.parseInt(readMetadata(startMesh, "height"));
        Map map = new Map(width,height);

        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {
            Structs.Point tileCenter = startMesh.getPoints(polygon.getCentroidIdx());
            Tile newTile = new Tile(polygon, tileCenter);

            for (int neighborId: polygon.getNeighborsList()) {
                newTile.addNeighborPseudoCenter(new PseudoPoint(startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx())));
            }

            map.addTile(newTile);
        }
        return map;
    }

    public static Structs.Mesh syncMeshBuilderWithMap(Structs.Mesh startMesh, Map map) {
        Structs.Mesh.Builder builder = startMesh.toBuilder();
        for (int i = 0; i < builder.getPolygonsCount(); i++) {
            Structs.Polygon p = builder.getPolygons(i);
            TileColor tileColor = map.getTileColor(builder.getPoints(p.getCentroidIdx()));
            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(tileColor.toString()).build();
            builder.getPolygonsBuilder(i).addProperties(color);
        }
        Structs.Mesh endMesh = builder.build();
        return endMesh;
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
