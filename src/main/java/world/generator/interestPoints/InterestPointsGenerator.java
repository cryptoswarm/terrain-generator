package world.generator.interestPoints;

import geometry.Line;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;

import static world.TileColor.*;
import static world.TileColor.WHITE;

public class InterestPointsGenerator implements Generator {

    public enum POIS{
        PORTS(POWDERBLUE),
        VILLAGES(PINK),
        CITIES(DARK),
        NOTHING(WHITE);


        POIS(TileColor color){

            tileColor = color;
        };

        private TileColor tileColor;

        public TileColor getTileColor() {
            return tileColor;
        }
    }

    private int [] pois;
    private RandomContexte random;
    private final float RICHNESS_LIMIT = 150;

    public InterestPointsGenerator(int [] pois, RandomContexte random){

        this.pois = pois;
        this.random = random;
    }

    @Override
    public void generate(World w) {

        addCities(w);
        addVillages(w);
        addPorts(w);

    }

    private void addVillages(World w) {

        for(Isle isle: w.getIsleList()){

            int num = pois[POIS.VILLAGES.ordinal()];
            while(num > 0) {
                Tile tile = isle.findRandomTile(random);

                if (tile.getPois() == POIS.NOTHING) {
                    if (tile.getRichness() >= 150 && !tile.getItem().getType().equals("beach") && !tile.getItem().getType().equals("lake")) {
                        tile.setPois(POIS.VILLAGES);
                        num--;
                    }
                }
            }
        }

    }

    private void addPorts(World w) {

        for(Isle isle: w.getIsleList()){
            int num = pois[POIS.PORTS.ordinal()];
            while(num > 0){

                Tile tile = isle.findRandomTile(random);

                if (tile.getPois() == POIS.NOTHING){
                    if(tile.getItem().getType().equals("beach")){
                        tile.setPois(POIS.PORTS);
                        num --;
                    }else {

                        if (!tile.getItem().getType().equals("lake")){
                            for (Line line : tile.getBorder()) {
                                if (tile.getLineColor(line) == TileColor.WATERBLUE) {
                                    tile.setPois(POIS.PORTS);
                                    num--;
                                }
                            }
                        }
                    }
                }
            }


        }

    }

    private void addCities(World w){

        for(Isle isle: w.getIsleList()){

            int num = pois[POIS.CITIES.ordinal()];
            while(num > 0) {
                Tile tile = isle.findRandomTile(random);
                boolean isGoodTile = false;
                if (tile.getPois() == POIS.NOTHING) {
                    if (tile.getRichness() >= 215 && !tile.getItem().getType().equals("beach")
                            && !tile.getItem().getType().equals("lake")) {
                        isGoodTile = true;
                        for(Tile nTile: w.getNeighbor(tile)){
                            if (nTile.getPois() != POIS.NOTHING) {

                                isGoodTile = false;
                            }
                            if (nTile.getRichness() <= 200 || nTile.getItem().getType().equals("beach")) {
                                isGoodTile = false;
                            }

                        }
                    }
                }

                if(isGoodTile){
                    tile.setPois(POIS.CITIES);
                    num--;
                }
            }
        }
    }
}
