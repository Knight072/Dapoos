package Domain;

import java.util.ArrayList;

public class Mine extends Casilla{
    public Mine(int coordX, int coordY){
        this.tipo = "Mine";
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public ArrayList<Integer[]> action(){
        ArrayList<Integer[]> positions = new ArrayList<Integer[]>();
        int size = Table.getInstance().getSize();
        for(int i = -1; i <= 1; i++){
            if(coordX+i > -1 && coordY+i>-1 && coordX+i<size && coordY+i<size){
                Integer[] coords = {coordX+i, coordY+i,0};
                positions.add(coords);
            }
        }
        for(int i = -1; i <= 1; i++){
            if(coordX-i > -1 && coordY+i>-1 && coordX-i<size && coordY+i<size){
                Integer[] coords = {coordX-i, coordY+i,0};
                positions.add(coords);
            }
        }
    return positions;
    }
}
