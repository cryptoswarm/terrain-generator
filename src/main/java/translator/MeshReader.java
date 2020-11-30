package translator;

import ca.uqam.ace.inf5153.mesh.io.Structs;
import ca.uqam.info.inf5153.ptg.WorldGenerator;
import geometry.Coordinate;
import geometry.Line;
import world.Tile;

import java.io.IOException;
import java.util.Optional;

public class MeshReader implements Reader {
    private Structs.Mesh mesh;
    private final  WorldGenerator c;

    public MeshReader(String fileName, WorldGenerator c) {
        try {
            this.mesh = new ca.uqam.ace.inf5153.mesh.io.MeshReader().readFromFile(fileName);
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
            Tile tile = new Tile(new Coordinate( tileCenterX, tileCenterY, -1) );
            addPolygonToWorld(tile );

            for(int segmentId: polygon.getSegmentIdxList()) {
               addSegmentToWorld(segmentId,tileCenterX,tileCenterY);
            }
        }
    }

    private void setWorldWidth(int w){
        c.setWorldWidth(w);
    }
    private void setWorldHeight(int h) {c.setWorldHeight(h);}
    private void addPolygonToWorld(Tile tile){
        c.addWorldTile(tile);
    }
    private void addSegmentToWorld(int segmentId, float tileCenterX, float tileCenterY){
        Structs.Segment segment = mesh.getSegments(segmentId);
        Structs.Point p1 = mesh.getPoints(segment.getV1Idx());
        Structs.Point p2 = mesh.getPoints(segment.getV2Idx());

        Coordinate c1 = new Coordinate(p1.getX(), p1.getY(),-1);
        Coordinate c2 = new Coordinate(p2.getX(),p2.getY(),-1);
        Line line = new Line(c1,c2);
        Coordinate coordinate = new Coordinate(tileCenterX, tileCenterY, -1 );

        c.addWorldLine(coordinate, line);
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
