package Domain;

public abstract class Comodin {
    protected String tipo;

    public abstract void action(int coordX, int  coordY, int coordX2, int coordY2, int numPlayer) throws DamasException;

	public String getTipo() {
		return tipo;
	}
}
