package world.generator.interestPoints;

import geometry.Line;
import islandSet.Isle;
import randomStrategy.RandomContexte;
import world.Tile;
import world.TileColor;
import world.World;
import world.generator.Generator;
import world.generator.biome.Plage;

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
                    if (tile.getRichiness() >= 150 && !tile.getBiome().getType().equals("plage")) {
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
                    if(tile.getBiome().getType().equals("plage")){
                        tile.setPois(POIS.PORTS);
                        num --;
                    }else {
                        boolean adjacentLake = false;
                        for(Line line: tile.getBorder()){
                            if(tile.getLineColor(line) == TileColor.WATERBLUE){

                                adjacentLake = true;
                            }
                        }

                        if(adjacentLake){
                            tile.setPois(POIS.PORTS);
                            num--;
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
                    if (tile.getRichiness() >= 215 && !tile.getBiome().getType().equals("plage")) {
                        isGoodTile = true;
                        for(Tile nTile: w.getNeighbor(tile)){
                            if (nTile.getPois() != POIS.NOTHING) {

                                isGoodTile = false;
                            }
                            if (nTile.getRichiness() <= 200 || nTile.getBiome().getType().equals("plage")) {
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
