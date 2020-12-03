package translator;


import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import ca.uqam.info.inf5153.ptg.WorldGenerator;
import geometry.Coordinate;
import geometry.Line;

import java.io.IOException;


public class MeshWriter implements Writer{

    private WorldGenerator c;
    private Structs.Mesh mesh;
    private String outFileName;

    public MeshWriter(WorldGenerator c, String fileName, String outFileName) {
        this.c = c;
        this.outFileName = outFileName;
        try {
            this.mesh = new MeshReader().readFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateEndMesh(){
        Structs.Mesh endMesh = syncMeshBuilderWithMap();
        createOutputFile(endMesh, outFileName);
    }


    private Structs.Mesh syncMeshBuilderWithMap() {
        Structs.Mesh.Builder builder = mesh.toBuilder();

        setPolygonColor(builder);
        setSegmentColor(builder);

        return builder.build();
    }

    private void setPolygonColor(Structs.Mesh.Builder builder){
        for (int i = 0; i < builder.getPolygonsCount(); i++) {
            Structs.Polygon p = mesh.getPolygons(i);
            float x = builder.getPoints(p.getCentroidIdx()).getX();
            float y = builder.getPoints(p.getCentroidIdx()).getY();
            String tileColor = c.getWorldTileColor(new Coordinate(x,y,0) );
            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(tileColor).build();
            builder.getPolygonsBuilder(i).addProperties(color);

        }
    }

    private void setSegmentColor(Structs.Mesh.Builder builder){
        for (int i = 0; i < builder.getSegmentsCount(); i++) {
            Structs.Segment segment = mesh.getSegments(i);
            Structs.Point p1 = mesh.getPoints(segment.getV1Idx());
            Structs.Point p2 = mesh.getPoints(segment.getV2Idx());

            Line line = new Line(new Coordinate(p1.getX(),p1.getY(),0), new Coordinate(p2.getX(),p2.getY(),0));
            String lineColor = c.getWorldLineColor( line );

            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(lineColor).build();
            builder.getSegmentsBuilder(i).addProperties(color);

            Structs.Property thickness = Structs.Property.newBuilder().setKey("thickness").setValue("1").build();
            builder.getSegmentsBuilder(i).addProperties(thickness);
        }
    }

    private void createOutputFile(Structs.Mesh endMesh, String fileName){
        ca.uqam.ace.inf5153.mesh.io.MeshWriter writer = new ca.uqam.ace.inf5153.mesh.io.MeshWriter();
        try {
            writer.writeToFile(endMesh, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
