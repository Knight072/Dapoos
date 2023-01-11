package Domain;

import java.util.ArrayList;

public class NCasilla extends Casilla{
    public NCasilla(int coordX, int coordY){
        this.tipo = "NCasilla";
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public ArrayList<Integer[]> action() {
        return null;
    }

    public String getTipo() {
        return tipo;
    }

}
