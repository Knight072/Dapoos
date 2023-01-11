package Domain;

public class Gun extends Comodin{
    public Gun(){
        this.tipo = "Gun";
    } 

    @Override
    public void action(int coordX, int coordY, int coordX2, int coordY2, int numPlayer) throws DamasException{
        int[][] juego = Table.getInstance().getJuego();
        if(coordX == coordX2 && coordY == coordY2 && juego[coordX][coordY] != numPlayer){
            try{
                Table.getInstance().comerFicha(coordX, coordY, coordX2, coordY2, 0, 0);
            }catch(DamasException e){
                throw e;
            }
            throw new DamasException(DamasException.GUN_COMODIN_ACTIVATED);
        }
        else{
            throw new DamasException(DamasException.COMODIN_WRONG_USED);
        }
    }
}
