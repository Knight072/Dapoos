package Domain;

public class DamasException extends Exception{
    public static final String ERROR_BLANK_SPACE = "Primero debe seleccionar su ficha";
    public static final String NOT_TURN_PLAYER1 = "No es el turno del jugador1";
    public static final String NOT_TURN_PLAYER2 = "No es el turno del jugador2";
    public static final String PLAYER1_CAN_CHOOSE_PIECE = "Jugador1 puede escoger una nueva ficha";
    public static final String PLAYER2_CAN_CHOOSE_PIECE = "Jugador2 puede escoger una nueva ficha";
	public static final String CHOOSE_COMODIN_ACTION = "Escoja la ficha o fichas a las que se les aplicara el comodin";
    public static final String COMODIN_CAPTURE = "Se ha capturado un comodín";
    public static final String GUN_COMODIN_ACTIVATED = "Un comodín gun ha sido utilizado";
    public static final String STOMP_COMODIN_ACTIVATED = "Un comodín stomp ha sido utilizado";
    public static final String COMODIN_WRONG_USED = "Comodin mal usado, se pierde poder";
    public static final String SAME_COLOR = "Jugadores con el mismo color";
    public static final String BAD_PERCENTAGE = "Se ingreso un mal valor para el procentaje";
    public static final String IS_EMPTY = "Hay un campo sin llenar";

    public DamasException(String msm){
        super(msm);
    }
}
