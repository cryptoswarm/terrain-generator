package Translator;

import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import java.io.IOException;
import java.util.Optional;
import ca.uqam.info.inf5153.ptg.Controller;

public class Reader {
    private Structs.Mesh mesh;
    private Controller c;

    public Reader(String fileName, Controller c) {
        try {
            this.mesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.c = c;
    }

    public void readFile(){


        setWorldWidth(Integer.parseInt(readMetadata(mesh, "width")));
        setWorldHeight(Integer.parseInt(readMetadata(mesh, "height")));


        for (Structs.Polygon polygon: mesh.getPolygonsList()) {
            Structs.Point tileCenterCoordinate = mesh.getPoints(polygon.getCentroidIdx());
            float tileCenterX = tileCenterCoordinate.getX();
            float tileCenterY = tileCenterCoordinate.getY();

            addPolygonToWorld(tileCenterX, tileCenterY);

            for(int segmentId: polygon.getSegmentIdxList()) {
               addSegmentToWorld(segmentId,tileCenterX,tileCenterY);
            }
        }

        for (Structs.Polygon polygon: mesh.getPolygonsList()) {
            Structs.Point tileCenterCoordinate = mesh.getPoints(polygon.getCentroidIdx());
            for (int neighborId : polygon.getNeighborsList()) {
                Structs.Point neighborCenterCoordinate =
                        mesh.getPoints(mesh.getPolygons(neighborId).getCentroidIdx());

                c.addWorldNeighbor(tileCenterCoordinate.getX(), tileCenterCoordinate.getY(),
                        neighborCenterCoordinate.getX(), neighborCenterCoordinate.getY());
            }
        }
    }

    private void setWorldWidth(int w){
        c.setWorldWidth(w);
    }
    private void setWorldHeight(int h) {c.setWorldHeight(h);}
    private void addPolygonToWorld(float x, float y){
        c.addWorldTile(x, y);
    }
    private void addSegmentToWorld(int segmentId, float tileCenterX, float tileCenterY){
        Structs.Segment segment = mesh.getSegments(segmentId);
        Structs.Point p1 = mesh.getPoints(segment.getV1Idx());
        Structs.Point p2 = mesh.getPoints(segment.getV2Idx());

        c.addWorldLine(tileCenterX,tileCenterY, p1.getX(),
                p1.getY(), p2.getX(), p2.getY());
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
