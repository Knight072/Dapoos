package Domain;

public class Reina extends Ficha{
    public Reina(int num){
        this.tipo = "Reina";
        this.numero = num;
        this.comible = 0;
        this.yaComio = 0;
    }

    @Override
    public boolean moverOComer(int positionX, int positionY, int positionX2, int positionY2) throws DamasException{
        boolean changeOrNot = true;
        int[][] juego = Table.getInstance().getJuego();
        int fila = -1,columna = -1;
        for(int i = -positionX,j = positionY + i;  i < juego.length && j < juego.length; i++, j++){
            if(i > 0){
                fila = 1;
                columna = 1;
            }
            if(positionX+i == positionX2 && j == positionY2){
                changeOrNot = confirmarMov(positionX, positionY, positionX2, positionY2, fila, columna);
                break;
            }
        }
        fila = 1;
        columna = -1;
        System.out.println(positionX);
            System.out.println(positionY);
        for(int k = (juego.length-1)-positionX,l = -positionY; k > -(juego.length) && l<juego.length ; k--, l++){
            if(positionX+k == positionX && positionY+l == positionY){
                fila = -1;
                columna = 1;
            }
            if(positionX+k == positionX2 && positionY+l == positionY2){
                changeOrNot = confirmarMov(positionX, positionY, positionX2, positionY2, fila, columna);
                break;
            }
        }
        return changeOrNot;
    }

    private boolean confirmarMov(int positionX, int positionY, int positionX2, int positionY2, int fila, int columna) throws DamasException{
        boolean yesOrNot = false;
        boolean changeOrNot = true;
        int i= fila, j= columna;
        while(positionX+i != positionX2+fila && positionY+j != positionY2+columna){
            if(Table.getInstance().getJuego()[positionX+i][positionY+j] == 3){
                yesOrNot = true;
            }
            else if(Table.getInstance().getJuego()[positionX+i][positionY+j] != numero && Table.getInstance().getJuego()[positionX+(2*i)][positionY+(2*j)] == 3){
                yesOrNot = true;
            }
            else{
                yesOrNot = false;
                break;
            }
            i += fila;
            j += columna;
        }
        if(yesOrNot){
            changesOrEat(positionX, positionY, positionX2, positionY2, fila, columna, null);
        }
        return changeOrNot;
    }

    private Comodin changesOrEat(int positionX, int positionY, int positionX2, int positionY2, int fila, int columna, Comodin cmdin) throws DamasException{
        if(positionX == positionX2 && positionY == positionY2){
            return cmdin;
        }
        else if(Table.getInstance().getJuego()[positionX+fila][positionY+columna] == 3){
            cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX+fila, positionY+columna);
            if(cmdin != null){ 
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
            changesOrEat(positionX+fila, positionY+columna, positionX2, positionY2, fila, columna, cmdin);
        }
        else if(Table.getInstance().getJuego()[positionX+fila][positionY+columna] != numero && Table.getInstance().getJuego()[positionX+(2*fila)][positionY+(2*columna)] == 3){
            cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX+(2*fila), positionY+(2*columna),fila,columna);
            if(cmdin != null){ 
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
            changesOrEat(positionX+(2*fila), positionY+(2*columna), positionX2, positionY2, fila, columna, cmdin);
        }
        return cmdin;
    }

}
