package Domain;

public class Ninja extends Ficha{
    public Ninja(int num){
        this.tipo = "Ninja";
        this.numero = num;
        this.comible = 1;
    }

    public boolean moverOComer(int positionX, int positionY, int positionX2, int positionY2) throws DamasException{
        int[][] table = Table.getInstance().getJuego();
        boolean changeOrNot = true;
        if(positionX-1 == positionX2 && positionY-1 == positionY2 && table[positionX2][positionY2] == 3){
            Comodin cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX2,positionY2);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX-1 == positionX2 && positionY+1 == positionY2 && table[positionX2][positionY2] == 3){
            Comodin cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX2,positionY2);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX+1 == positionX2 && positionY-1 == positionY2 && table[positionX2][positionY2] == 3){
            Comodin cmdin =Table.getInstance().changeFicha(positionX, positionY, positionX2,positionY2);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX+1 == positionX2 && positionY+1 == positionY2 && table[positionX2][positionY2] == 3){
            Comodin cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX2,positionY2);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX-2 == positionX2 && positionY-2 == positionY2 && table[positionX-1][positionY-1] != numero && table[positionX-1][positionY-1] != 3){
            Comodin cmdin =Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, -1, -1);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX-2 == positionX2 && positionY+2 == positionY2 && table[positionX-1][positionY+1] != numero && table[positionX-1][positionY+1] != 3){
            Comodin cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, -1, +1);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX+2 == positionX2 && positionY-2 == positionY2 && table[positionX+1][positionY-1] != numero && table[positionX+1][positionY-1] != 3){
            Comodin cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, +1, -1);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        else if(positionX+2 == positionX2 && positionY+2 == positionY2 && table[positionX+1][positionY+1] != numero && table[positionX+1][positionY+1] != 3){
            Comodin cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, +1, +1);
            if(cmdin != null){
                setComodin(cmdin);
                throw new DamasException(DamasException.COMODIN_CAPTURE);
            }
        }
        return changeOrNot;
    }

}
