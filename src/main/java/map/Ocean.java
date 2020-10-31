package map;

import geometrie.Dot;

import java.util.HashMap;
import java.util.Map;

public class Ocean { //c'est bon interafce tuile ,,,, ocean implements tuile

    private Map<Dot, Tile> ocean;

    public Ocean(){
        this.ocean = new HashMap<>();
    }

    /**
     *  Sauvergader les tuiles composant les biome ocean
     * @param dot  centre
     * @param tuile une tuile
     */
    public void constructOcean(Dot dot, Tile tuile){
        ocean.put( dot, tuile);
    }

    /**
     *
     * @return  les  tuiles composant les biome ocean
     */

    public Map<Dot, Tile> getOcean() {
        return ocean;
    }

    /**
     *
     * @param dot centre
     * @return  retourner la tuile equivalente
     */

    public Tile getOceanTuildId(Dot dot) {
        return ocean.get(dot);
    }

    /**
     *
     * @return le nombre total des tuiles composant le biome ocean
     */
    public int oceanTuildNbr(){
        return ocean.size();
    }

    /**
     * Vérifier si une tuile est voisine aux tuiles composant le biome ocean
     * Chaque tuile connait ses voisines
     * eterer sur la listes des tuiles composant le biome ocean puis les voisines de  this tuile
     * Si that tuile est parmis les voisines on sort de la boucle en ayant true sinon false
     * @param tile  une tuile
     * @return
     */

    public boolean isNeighbor( Tile tile){
        boolean isPresent = false;
        for(Map.Entry<Dot, Tile> entry:ocean.entrySet() ) {
            //Dot center = entry.getKey();
            Tile b = entry.getValue();
            for( Dot val : b.getNeighborPseudoCenters() )
            {
                if(val.equals(tile.getTileCenter())){
                    isPresent = true;
                    break;
                }
            }
            if(isPresent) {
                break;
            }
        }
        return isPresent;
    }
/*
    public boolean isNeighbor1( Dot dot){
        boolean isPresent = false;

        if( ocean.containsKey(dot) ) {
            isPresent = true;
        }
        return isPresent;
    }

 */
}
