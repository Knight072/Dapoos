package Domain;


public abstract class Ficha {
    protected String color;
    protected int numero;
    protected int comible;
    protected String tipo;
    protected int yaComio;
    protected Comodin comodin;

    public abstract boolean moverOComer(int positionX, int positionY, int positionX2, int positionY2) throws DamasException;

    public int getNumero(){
        return numero;
    }

    public String getColor() {
        return color;
    }

    public void subtComible(){
        if(comible > 0){
            comible--;
        }
    }

    public int getComible() {
        return comible;
    }

    public String getTipo() {
        return tipo;
    }

    public Comodin getComodin(){
        Comodin comdin = null;
        if(this.comodin != null){
            comdin = this.comodin;
        }
        return comdin; 
    }

    public void setComodin(Comodin comodin) {
        this.comodin = comodin;
    }

    public void resetComodin(){
        this.comodin = null;
    }

}
