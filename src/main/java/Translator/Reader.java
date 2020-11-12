package Translator;

import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import java.io.IOException;
import java.util.Optional;
import ca.uqam.info.inf5153.ptg.Controller;

public class Reader {
    public void readFile(Controller c,String fileName){
        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        c.setWorldWidth(Integer.parseInt(readMetadata(startMesh, "width")));
        c.setWorldHeight(Integer.parseInt(readMetadata(startMesh, "height")));


        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {
            Structs.Point tileCenterCoordinate = startMesh.getPoints(polygon.getCentroidIdx());
            c.addWorldTile(tileCenterCoordinate.getX(), tileCenterCoordinate.getY());
        }

        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {
            Structs.Point tileCenterCoordinate = startMesh.getPoints(polygon.getCentroidIdx());
            for (int neighborId : polygon.getNeighborsList()) {
                Structs.Point neighborCenterCoordinate =
                        startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx());

                c.addWorldNeighbor(tileCenterCoordinate.getX(), tileCenterCoordinate.getY(),
                        neighborCenterCoordinate.getX(), neighborCenterCoordinate.getY());
            }
        }
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
