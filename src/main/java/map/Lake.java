package map;

public class Lake {

    private Aquifere aquifere;

    public Lake(Aquifere aquifere) {
        this.aquifere = aquifere;
    }

    public void setColor(){
       aquifere.setColorAquifere(TileColor.WATERBLUE);
    }

}
