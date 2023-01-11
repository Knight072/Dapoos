package Domain;

import java.util.HashMap;

public abstract class Actor{
    protected HashMap<String,Ficha> fichas = new HashMap<>();
    protected boolean turn;

    boolean moverOComer(int coordX1, int coordY1, int coordX2, int coordY2) throws DamasException{
        return true;
    }

    public boolean containsFicha(int coordX, int coordY){
        return fichas.containsKey(Integer.toString(coordX)+","+Integer.toString(coordY));
    }

    public void resetFichas(){
        fichas.clear();
    }

    public void setFicha(int coordX, int coordY, Ficha ficha){
        fichas.put(Integer.toString(coordX)+","+Integer.toString(coordY),ficha);
    }

    public String getTipoFicha(int coordX, int coordY){
        return fichas.get(Integer.toString(coordX)+","+Integer.toString(coordY)).getTipo();
    }

    public boolean isTurn(){
        return turn;
    }

    public void setTurn(boolean change){
        this.turn = change;
    }
}
