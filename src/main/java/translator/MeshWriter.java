package translator;


import ca.uqam.ace.inf5153.mesh.io.MeshReader;
import ca.uqam.ace.inf5153.mesh.io.Structs;
import ca.uqam.info.inf5153.ptg.WorldGenerator;
import geometry.Coordinate;
import geometry.Line;

import java.awt.*;
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
        setPointColor(builder);
        setRoadColor(builder);

        return builder.build();
    }

    private void setRoadColor(Structs.Mesh.Builder builder) {

        int idx1 = -1;
        int idx2 = -1;

        for(Line line: c.getRoads()){
            for(Structs.Polygon pol: mesh.getPolygonsList()){
                Structs.Point point = mesh.getPoints(pol.getCentroidIdx());
                if(point.getX() == line.getC1().getX() && point.getY() == line.getC1().getY()){
                    idx1 = pol.getCentroidIdx();
                }
                if(point.getX() == line.getC2().getX() && point.getY() == line.getC2().getY()){
                    idx2 = pol.getCentroidIdx();
                }
            }

            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue("0:0:0:255").build();
            Structs.Property thickness = Structs.Property.newBuilder().setKey("thickness").setValue("2").build();
            Structs.Property style = Structs.Property.newBuilder().setKey("style").setValue("dashed").build();

            Structs.Segment seg = Structs.Segment.newBuilder().setV1Idx(idx1).setV2Idx(idx2).addProperties(color).addProperties(thickness).addProperties(style).build();

            builder.addSegments(seg);
        }
    }

    private void setPointColor(Structs.Mesh.Builder builder){

        for (int i = 0; i < builder.getPolygonsCount(); i++) {

            Structs.Point point =  mesh.getPoints(mesh.getPolygons(i).getCentroidIdx());

            String pointColor = c.getPointColor(new Coordinate(point.getX(), point.getY(), 0) );
            Structs.Property color = Structs.Property.newBuilder().setKey("color").setValue(pointColor).build();

            Structs.Property thickness = Structs.Property.newBuilder().setKey("thickness").setValue("5").build();


            builder.getPointsBuilder(mesh.getPolygons(i).getCentroidIdx()).addProperties(color);
            builder.getPointsBuilder(mesh.getPolygons(i).getCentroidIdx()).addProperties(thickness);

        }

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
