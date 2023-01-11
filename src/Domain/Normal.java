package Domain;


public class Normal extends Ficha{

    public Normal(int num){
        this.tipo = "Normal";
        this.numero = num;
        this.comible = 0;
        this.yaComio = 0;
    }

    @Override
    public boolean moverOComer(int positionX, int positionY, int positionX2, int positionY2) throws DamasException{
        boolean rtrn = true;
        if(numero == 1){
            rtrn = confirmarMov(positionX, positionY, positionX2, positionY2, -1);
        }
        else if(numero == 2){
            rtrn = confirmarMov(positionX, positionY, positionX2, positionY2, +1);
        }
        return rtrn;
    }

    private boolean confirmarMov(int positionX, int positionY, int positionX2, int positionY2, int fila)throws DamasException{
        int[][] table = Table.getInstance().getJuego();
        boolean changeOrNot = true;
            if(positionX+fila == positionX2 && positionY-1 == positionY2 && table[positionX2][positionY2] == 3){
                if(yaComio == 0){
                    Comodin cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX2,positionY2);
                    if(cmdin != null){
                        setComodin(cmdin);
                        throw new DamasException(DamasException.COMODIN_CAPTURE);
                    }
                }else{
                    changeOrNot = false;
                }
            }
            else if(positionX+fila == positionX2 && positionY+1 == positionY2 && table[positionX2][positionY2] == 3){
                if(yaComio == 0){
                    Comodin cmdin = Table.getInstance().changeFicha(positionX, positionY, positionX2, positionY2);
                    if(cmdin != null){ 
                        setComodin(cmdin);
                        throw new DamasException(DamasException.COMODIN_CAPTURE);
                    }
                }else{
                    changeOrNot = false;
                }
            }
            else if(positionX+(2*fila) == positionX2 && positionY-2 == positionY2 && table[positionX+fila][positionY-1] != numero && table[positionX+fila][positionY-1] != 3){
                Comodin cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, fila, -1);
                if(cmdin != null){ 
                    setComodin(cmdin);
                    throw new DamasException(DamasException.COMODIN_CAPTURE);
                }
                changeOrNot = fichasComiblesCercanas(positionX2, positionY2, fila);
            }
            else if(positionX+(2*fila) == positionX2 && positionY+2 == positionY2 && table[positionX+fila][positionY+1] != numero && table[positionX+fila][positionY+1] != 3){
                Comodin cmdin = Table.getInstance().comerFicha(positionX, positionY, positionX2, positionY2, fila, +1);
                if(cmdin != null){ 
                    setComodin(cmdin);
                    throw new DamasException(DamasException.COMODIN_CAPTURE);
                }
                changeOrNot = fichasComiblesCercanas(positionX2, positionY2, fila);
            }
        return changeOrNot;
    }

    private boolean fichasComiblesCercanas(int positionX, int positionY, int fila){
        boolean confirm = true;
        int[][] table = Table.getInstance().getJuego();
        int size = Table.getInstance().getSize();
        if(fila == -1){
            if(positionX+(2*fila)>-1 && positionY-2>-1){
                if(confirm) confirm = (table[positionX+(2*fila)][positionY-2] == 3 && table[positionX+fila][positionY-1] != numero && table[positionX+fila][positionY-1] != 3)? false:true;
            }
            if(positionX+(2*fila)>-1 && positionY+2<size){
                if(confirm) confirm = (table[positionX+(2*fila)][positionY+2] == 3 && table[positionX+fila][positionY+1] != numero && table[positionX+fila][positionY+1] != 3)? false:true;
            }
        }
        else if(fila == 1){
            if(positionX+(2*fila)<size && positionY-2>-1){
                if(confirm) confirm = (table[positionX+(2*fila)][positionY-2] == 3 && table[positionX+fila][positionY-1] != numero && table[positionX+fila][positionY-1] != 3)? false:true;
            }
            if(positionX+(2*fila)<size && positionY+2<size){
                if(confirm) confirm = (table[positionX+(2*fila)][positionY+2] == 3 && table[positionX+fila][positionY+1] != numero && table[positionX+fila][positionY+1] != 3)? false:true;
            }
        }
        if(confirm){
            yaComio = 0;
        }
        else{
            yaComio = 1;
        }
        return confirm;
    }
}
