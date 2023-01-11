package Domain;

import java.util.ArrayList;

public abstract class Casilla{
    protected String tipo;
    protected Table table;
    protected int coordX;
    protected int coordY;
    private Comodin comodin;

    public String getTipo() {
        return tipo;
    }

    public abstract ArrayList<Integer[]> action();

    public void setComodin(Comodin comodin) {
        this.comodin = comodin;
    }

    public Comodin getComodin() {
        Comodin retorno = this.comodin;
        this.comodin = null;
        return retorno;
    }
}
