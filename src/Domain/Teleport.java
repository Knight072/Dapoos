package Domain;

import java.util.ArrayList;
import java.util.Random;

public class Teleport extends Casilla{
    public Teleport(int coordX, int coordY){
        this.tipo = "Teleport";
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public ArrayList<Integer[]> action(){
        Random rand = new Random();
        ArrayList<Integer[]> positions = new ArrayList<>();
        int[][] juego = Table.getInstance().getJuego();
        int size = Table.getInstance().getSize();
        while(true){
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if(juego[x][y] == 3){
                Integer[] pos1 = {coordX,coordY};
                Integer[] pos2 = {x,y};
                positions.add(pos1);
                positions.add(pos2);
                break;
            }
        }
        return positions;
    }
}
