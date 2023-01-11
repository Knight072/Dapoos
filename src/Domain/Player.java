package Domain;


public class Player extends Actor{
    private String name;
    private int numero;
    private Comodin comodinActive;

    public Player(String name, int num){
        this.numero = num;
        this.name = name;
    }

    @Override
    public boolean moverOComer(int coordX1, int coordY1, int coordX2, int coordY2) throws DamasException{
        boolean changeOrNot = true;
        try{
            changeOrNot = fichas.get(Integer.toString(coordX1)+","+Integer.toString(coordY1)).moverOComer(coordX1, coordY1, coordX2, coordY2);
        }catch(DamasException e){
            if(e.getMessage() == DamasException.COMODIN_CAPTURE){
                comodinActive = fichas.get(Integer.toString(coordX1)+","+Integer.toString(coordY1)).getComodin();
                fichas.get(Integer.toString(coordX1)+","+Integer.toString(coordY1)).resetComodin();
                throw e;
            }
            else{
                throw e;
            }
        }
        return changeOrNot;
    }

    public String getName() {
        return name;
    }

    public void useComodin(int coordX, int coordY, int coordX2, int coordY2) throws DamasException{
        comodinActive.action(coordX,coordY, coordX2, coordY2, numero);
    }

    public String getComodinTipo(){
        return comodinActive.getTipo();
    }

}
