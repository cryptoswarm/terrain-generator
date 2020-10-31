package reader;

import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import geometrie.Coordonnee;
import geometrie.Dot;
import map.World;
import map.Tile;

import java.io.IOException;
import java.util.Optional;

public class MeshFileReader implements Reader {

    @Override
    public World readFile(String fileName){

        Structs.Mesh startMesh = null;
        try {
            startMesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int width = Integer.parseInt(readMetadata(startMesh, "width"));
        int height = Integer.parseInt(readMetadata(startMesh, "height"));

        World world = new World(width,height);

        for (Structs.Polygon polygon: startMesh.getPolygonsList()) {

            Structs.Point tileCenter = startMesh.getPoints(polygon.getCentroidIdx());

            Dot dot = new Dot( new Coordonnee(tileCenter.getX(), tileCenter.getY(), 0) );
            Tile newTile = new Tile( dot, polygon.getCentroidIdx() );



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


            //HashSet<Dot> neighbors = new HashSet<>();  // les centre des tuiles voisines a une quelconque  tuile

            for (int neighborId: polygon.getNeighborsList()) {

                Structs.Point pt = startMesh.getPoints(startMesh.getPolygons(neighborId).getCentroidIdx());

                //neighbors.add(new Dot(new Coordonnee(pt.getX(), pt.getY(), 0)));

                newTile.addNeighborPseudoCenter(new Dot(new Coordonnee(pt.getX(), pt.getY(), 0)));
            }
           // carte.addTileAndNeighbors(newTile, neighbors);

            world.addTile(newTile);
        }
        return world;

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
