package reader;

import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Coordonnee;
import geometrie.Dot;
import map.Carte;
import map.Tile;

import java.io.IOException;
import java.util.Optional;

public class MeshFileReader implements Reader {

    @Override
    public Carte readFile(String fileName){

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = Integer.parseInt(readMetadata(startMesh, "width"));
        int height = Integer.parseInt(readMetadata(startMesh, "height"));

        Carte carte = new Carte(width,height);

        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {

            Structs.Point tileCenter = startMesh.getPoints(polygon.getCentroidIdx());

            Dot dot = new Dot( new Coordonnee(tileCenter.getX(), tileCenter.getY(), 0) );
            Tile newTile = new Tile(polygon, dot, polygon.getCentroidIdx() );



            //for(int segment_idx : polygon.getSegmentIdxList()){
            /*
            for(int i=0; i< polygon.getSegmentIdxCount(); i++){
                //System.out.println( "polygon.getSegmentIdx(segment_idx) = "+polygon.getSegmentIdx(segment_idx));
                //startMesh.
                //System.out.println(" segment_idx ="+segment_idx);
                System.out.println( polygon.getSegmentIdx(i) );
                Structs.Segment segment = startMesh.getSegments(i);
                System.out.println(segment.toString());
                //System.out.println(polygon.getSegmentIdxList());
                //System.out.println( polygon.getSegmentIdxList().get(0));
            }
            */

            for (int neighborId: polygon.getNeighborsList()) {
                Structs.Point pt = startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx());

                newTile.addNeighborPseudoCenter(new Dot(new Coordonnee(pt.getX(), pt.getY(), 0)));
            }

            carte.addTile(newTile);

        }
        return carte;

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
