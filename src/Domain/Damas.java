package Domain;


public class Damas{
    private boolean confirm = true;
    private int[][] drawTable;
    private String[][] casillasPosition;
    private Player jugador1;
    private Player jugador2;
    private Maquina maquina;
    private static Damas instance;

    /**
     * Crea a los jugadores por medio del nombre y coloca los colores para cada jugador
     * @param name1 String; el nombre del primer jugador
     * @param name2 String; el nombre del segundo jugador
     * @param color1 String; el color del primer jugador
     * @param color2 String; el color del segundo jugador
     */
    public void setPlayers(String name1, String name2, String tipo){
        if(tipo == null){
            try{
                jugador1 = (Player) Class.forName("Domain.Player").getConstructor(String.class, Integer.TYPE).newInstance(name1,1);
                jugador1.setTurn(true);
                jugador2 = (Player) Class.forName("Domain.Player").getConstructor(String.class,Integer.TYPE).newInstance(name2,2);
                jugador2.setTurn(false);
            }
            catch(Exception e){
                e.printStackTrace();
                confirm = false;
            }
        }else{
            try{
                jugador1 = (Player) Class.forName("Domain.Player").getConstructor(String.class).newInstance(name1);
                jugador1.setTurn(true);
                maquina = (Maquina) Class.forName("Domain."+tipo).getConstructor().newInstance();
            }
            catch(Exception e){
                e.printStackTrace();
                confirm = false;
            }
        }
    }

    /**
     * Devulve una instancia de la clase si ya esta creado, sino, creara una instancia de clase
     * @return Damas istance; instancia de la clase Damas
     */
    public static Damas getInstance(){
        if(instance == null){
            instance = new Damas();
        }
        return instance;
    }

    /**
     * Devuelve una matriz en donde los unos son casillas negras y los ceros casillas blancas
     * @return in[][] drawTable; matriz de enteros con las posiciones donde hay casillas negras y blancas
     */
    public int[][] getTableDraw(){
        this.drawTable = Table.getInstance().getParImpar();
        return drawTable;
    }

    /**
     * Devuelve el tablero después de realizar un movimiento
     * @return int[][] Juego; matriz de enteros del juego 
     */
    public int[][] getJuego(){
        int[][] Juego = Table.getInstance().getJuego();
        return Juego;
    }

    /**
    * Metodo que devuelve el tablero con las casillas especiales creadas
    * @param int percentage: porcentaje del núemero de casillas especialesS
    * @return String[][] casillasPosition; matiz de cadenas de texto con el tipo de casillas  
    */
    public String[][] reconvertCasillas(int percentage){
        Table.getInstance().casillasCreate(percentage);
        Table.getInstance().putComodines(50);
        Casilla[][] casillas = Table.getInstance().getCasillas();
        casillasPosition = new String[casillas.length][casillas.length];
        for(int i = 0; i < casillas.length; i++){
            for(int j=0; j < casillas.length; j++){
                if(casillas[i][j] != null){
                    casillasPosition[i][j] = casillas[i][j].getTipo();
                } 
            }
        }
        Table.getInstance().putFichas();
        putFichas();
        return casillasPosition;    
    }

    /**
     * Metodo que pone las fichas a cada jugador, o maquina, cuando se reliza un movimiento o cuando 
     * se inicia una partida
     */
    public void putFichas(){
        Ficha[][] fichas = Table.getInstance().getPiezasJuego();
        for(int i = 0; i < fichas.length; i++){
            for(int j = 0; j < fichas.length; j++){
                if(fichas[i][j] != null && fichas[i][j].getNumero() == 1 && jugador1 != null){
                    jugador1.setFicha(i,j, fichas[i][j]);
                }
                else if(fichas[i][j] != null && fichas[i][j].getNumero() == 2 && jugador2 != null){
                    jugador2.setFicha(i,j, fichas[i][j]);
                }
                else if(fichas[i][j] != null && fichas[i][j].getNumero() == 2 && maquina != null){
                    System.out.println(fichas[i][j]);
                    maquina.setFicha(i,j, fichas[i][j]);
                }
            }
        }
    }

    /**
     * Permite mover, o comer, las fichas enemigas dependiendo de cual jugador esta en turno
     * @param coordX1 int; coordenada inicial, de la fila, en la que se encuentra la ficha que va a mover o comer
     * @param coordY1 int; coordenada inicial, de la columna, en la que se encuentra la ficha que va a mover o comer
     * @param coordX2 int; coordenada final, de la fila, en la que va a estar la ficha después de comer o mover 
     * @param coordY2 int; coordenada final, de la columna, en la que va a estar la ficha después de comer o mover
     * @throws DamasException
     */
    public void moverOComer(int coordX1, int coordY1, int coordX2, int coordY2) throws DamasException{
        if(jugador1 != null && jugador1.containsFicha(coordX1, coordY1)){
            if(jugador1.isTurn()){
                if(jugador1.moverOComer(coordX1, coordY1, coordX2, coordY2)) changeTurn();
                jugador1.resetFichas();
            }
            else{
                throw new DamasException(DamasException.NOT_TURN_PLAYER1);
            }
        }
        else if(jugador2 != null && jugador2.containsFicha(coordX1, coordY1)){
            if(jugador2.isTurn()){
                if(jugador2.moverOComer(coordX1, coordY1, coordX2, coordY2)) changeTurn();
                jugador2.resetFichas();
            }
            else{
                throw new DamasException(DamasException.NOT_TURN_PLAYER2);
            }
        }
        else if(maquina != null && maquina.containsFicha(coordX1, coordY1)){
            if(maquina.isTurn()){
                if(maquina.moverOComer(coordX1, coordY1, coordX2, coordY2)) changeTurn();
                maquina.resetFichas();
            }
        }
        else{
            throw new DamasException(DamasException.ERROR_BLANK_SPACE);
        }
        putFichas();
    }


    /**
     * Permite poner un nuevo tipo de ficha después de que un jugador a coronado
     * @param type String; el tipo de ficha que se quiere poner
     * @param coordX int; coordenanda, de la fila, donde se va a poner la nueva ficha
     * @param coordY int; coordenanda, de la columna, donde se va a poner la nueva ficha
     */
    public void putNewFicha(String type,int coordX, int coordY){
        if(jugador1.isTurn()){
            Table.getInstance().newFicha(type, coordX, coordY);
            changeTurn();
            jugador1.resetFichas();
        }
        else if(jugador2.isTurn()){
            Table.getInstance().newFicha(type, coordX, coordY);
            changeTurn();
            jugador2.resetFichas();
        }
        putFichas();
    }

    /**
     * Permite usar un comodin después de que una ficha del jugador en turno la ha capturado
     * @param coordX int; coordenada inicial, de la fila, en donde se va a usar el comodin
     * @param coordY int; coordenada inicial, de la columna, en donde se va a usar el comodin 
     * @param coordX2 int; coordenada final, de la fila, en donde se va a usar el comodin
     * @param coordY2 int; coordenada final, de la columna, en donde se va a usar el comodin 
     * @throws DamasException
     */
    public void useComodin(int coordX, int coordY, int coordX2, int coordY2) throws DamasException{
        if(jugador1.isTurn()){
            changeTurn();
            jugador1.useComodin(coordX, coordY, coordX2, coordY2);
        }
        else if(jugador2.isTurn()){
            changeTurn();
            jugador2.useComodin(coordX, coordY, coordX2, coordY2);
        }
        //if(maquina.isTurn())
    }

    /**
     * Devuelve el comodin que ha sido capturado por el jugador en turno
     * @return String tipoComodin; el tipo de comodin que tiene el jugador
     */
    public String playerWithComodin(){
        String tipoComodin = "";
        if(jugador1.isTurn()){
            tipoComodin = jugador1.getComodinTipo();
        }
        else if(jugador2.isTurn()){
            tipoComodin = jugador2.getComodinTipo(); 
        }
        return tipoComodin;
    }

    /**
     * Cambia los turnos de los jugadores después de realizar un movimiento
     */
    private void changeTurn(){
        if(jugador1.isTurn()){
            jugador1.setTurn(false);
            jugador2.setTurn(true);
        }
        else if(jugador2.isTurn()){
            jugador2.setTurn(false);
            jugador1.setTurn(true);
        } 
    }

    /**
     * Retorna el tipo de ficha que un jugador tiene en una posición determinda
     * @param coordX int; coordenada de la fila donde se quiere conocer el tipo de ficha
     * @param coordY int; int; coordenada de la columna donde se quiere conocer el tipo de ficha
     * @return String tipoFicha; 
     */
    public String getTipoFicha(int coordX, int coordY){
        String tipoFicha = "";
        if(jugador1.containsFicha(coordX, coordY)){
            tipoFicha = jugador1.getTipoFicha(coordX, coordY);
        }
        else if(jugador2.containsFicha(coordX, coordY)){
            tipoFicha = jugador2.getTipoFicha(coordX, coordY);
        }
        return tipoFicha;
    }

    /**
     * Permite saber si se pudo realizar un método o no
     * @return boolean confirm; devuelve falso o verdadero si se pudo realizar un método
     */
    public boolean ok(){
        return confirm;
    }

}
