package Domain;

public class Stomp extends Comodin{
    public Stomp(){
        this.tipo = "Stomp";
    }

    @Override
    public void action(int coordX, int coordY, int coordX2, int coordY2, int numPlayer)throws DamasException{
        int[][] juego = Table.getInstance().getJuego();
        if(juego[coordX][coordY] == numPlayer && juego[coordX2][coordY2] != numPlayer){
            try{
                Table.getInstance().comerFicha(coordX, coordY, coordX2, coordY2, 0, 0);
            }catch(DamasException e){
                throw e;
            }
            throw new DamasException(DamasException.STOMP_COMODIN_ACTIVATED);
        }
        else{
            throw new DamasException(DamasException.COMODIN_WRONG_USED);
        }
        
    }
}
