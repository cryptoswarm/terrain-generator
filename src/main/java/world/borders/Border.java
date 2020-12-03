package world.borders;

import geometry.Coordinate;
import geometry.Line;
import world.TileColor;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Border {

    private HashSet<Line> lines;
    HashSet<Coordinate> corners;

    public Border() {
        this.corners = new LinkedHashSet<>();
        this.lines = new LinkedHashSet<>();
    }

    public void addTileLines(Line line){
        lines.add(line);
    }

    public HashSet<Line> getLines() {
        return lines;
    }

    public TileColor getLineColor(Line line){
        TileColor color = null;
        for(Line line1:lines){
            if(line1.equals(line)){
                color = line1.getColor();
            }
        }
        return color;
    }

    public HashSet<Coordinate> getCorners(){

        for(Line line:lines){
            corners.addAll(line.getCorners());
        }
        return corners;
    }

}
