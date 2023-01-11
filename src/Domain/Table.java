package Domain;

import java.util.ArrayList;
import java.util.Random;


public class Table{
    private Casilla[][] casillas = new Casilla[SIZE][SIZE];
    private Ficha[][] piezasJuego = new Ficha[SIZE][SIZE];
    private int[][] juego = new int[SIZE][SIZE];
    private int[][] parImpar = new int[SIZE][SIZE];
    private static final int SIZE = 10;
    private static Table instance;

    private Table(){
        parImparCreate();
    }

    /**
     * Devuelve una instancia de la clase Table si ya está creada, o la crea si no lo está
     * @return Table instance; devuelve una instancia de la clase Table
     */
    public static Table getInstance(){
        if(instance == null){
            instance =  new Table();
        }
        return instance;
    }

    /**
     * Devuelve una matriz de enteros que está organizada como un tablero de ajedrez
     * @return int[][] parImpar; matriz de enteros
     */
    public int[][] getParImpar() {
        return parImpar;
    }

    /**
     * Devuelve una matriz de Casillas organizadas en forma de un tablero de ajed
     * @return Casilla[][] casillas; matriz de casillas
     */
    public Casilla[][] getCasillas() {
        return casillas;
    }

    /**
     * Devuelve una matriz de enteros en donde se refleja el estado actual del juego
     * @return int[][] juego; matriz de enteros
     */
    public int[][] getJuego(){
        return juego;
    }

    /**
     * Devuelve una matiz de fichas en donde estan las fichas en cada momento del juego 
     * @return Ficha[][] piezasJuego; matriz de fichas
     */
    public Ficha[][] getPiezasJuego() {
        return piezasJuego;
    }

    private void parImparCreate(){
        for(int i = 0; i < SIZE; i++){
            for(int j=0; j < SIZE;j++){
                if(i % 2 != 0){
                    if(j % 2 == 0){
                        parImpar[i][j] = 1;
                    }
                    else{
                        parImpar[i][j] = 0;
                    }
                }
                else if(i % 2 == 0){
                    if(j % 2 == 0){
                        parImpar[i][j] = 0;
                    }
                    else{
                        parImpar[i][j] = 1;
                    }
                }
                
            }
        }
    }

    // crea las casillas de forma aleatoria en el tablero de 10x10 bajo el porcentaje de casillas que se ingresa 
    /**
     * Genera la matriz de casillas, especiales y normales, que componen al tablero 
     * @param percentage, entero que indica cuanto es el porcentaje de fichas especiales en el tablero
     */
    public void casillasCreate(int percentage){
        int numCasillasEspec = (percentage*50)/100; // calculo numero de fichas especiales
        Random rand = new Random();
        String[] casillasByName = {"Mine","Teleport"}; // Arreglo del nombre de las casillas que existen
        int cont = 1;
        while(cont <= numCasillasEspec){
            String casilla = casillasByName[rand.nextInt(casillasByName.length)];
            int i = rand.nextInt(SIZE);
            int j = rand.nextInt(SIZE);
            if(parImpar[i][j] == 1){
                try{
                    casillas[i][j] = (Casilla) Class.forName("Domain."+casilla).getConstructor(Integer.TYPE, Integer.TYPE).newInstance(i,j);
                    cont++;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                continue;
            }
        }
        for(int i=0; i < SIZE; i++){
            for(int j=0; j < SIZE; j++){
                if(parImpar[i][j] == 1 && casillas[i][j] == null){
                    try{
                        casillas[i][j] = (Casilla) Class.forName("Domain.NCasilla").getConstructor(Integer.TYPE, Integer.TYPE).newInstance(i,j);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Coloca los comodines en el tablero de juego según el porcentaje escogido 
     * @param percentage int; el porcentaje de comodines que se quieren tener en una partida
     */
    public void putComodines(int percentage){
        int numComodinesEspec = (percentage*50)/100; // calculo numero de fichas especiales
        Random rand = new Random();
        String[] comodinByName = {"Gun", "Stomp"}; // Arreglo del nombre de los comodines que existen
        int cont = 1;
        while(cont <= numComodinesEspec){
            String comodin = comodinByName[rand.nextInt(comodinByName.length)];
            int i = rand.nextInt(SIZE);
            int j = rand.nextInt(SIZE);
            if(parImpar[i][j] == 1){
                try{
                    casillas[i][j].setComodin((Comodin) Class.forName("Domain."+comodin).getConstructor().newInstance());
                    cont++;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                continue;
            }
        }
    }

    // se crean las fichas iniciales del juego
    /**
     *Pone las fichas iniciales sobre el tablero  
     */ 
    public void putFichas(){
        for(int i=0; i < SIZE; i++){
            for(int j=0; j < SIZE; j++){
                if(0<=i && i<= (SIZE/2)-2 && parImpar[i][j] == 1){
                    piezasJuego[i][j] = new Normal(2);
                    juego[i][j] = 2; 
                }
                else if((SIZE-1)-(SIZE/2-2)<=i && i<=(SIZE-1) && parImpar[i][j] == 1){
                    piezasJuego[i][j] = new Normal(1);
                    juego[i][j] = 1; 
                }
                else if(parImpar[i][j] == 1){
                    juego[i][j] = 3;
                }
            }
        }
    }

    /**
     * @param type String; el tipo de ficha que se quiere poner
     * @param coordX int; la coordenada de la fila donde se va a colocar la nueva ficha
     * @param coordY int; la coordenada de la columna donde se va a colocar la nueva ficha
     */
    public void newFicha(String type, int coordX, int coordY){
        try{
            piezasJuego[coordX][coordY] = (Ficha) Class.forName("Domain."+type).getConstructor(Integer.TYPE).newInstance(juego[coordX][coordY]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Realiza el movimiento de la ficha si es posible
     * @param coordX1 int; la coordenada inicial de la fila donde se encuentra la ficha que se quiere mover
     * @param coordY1 int; la coordenada inicial de la columna donde se encuentra la ficha que se quiere mover
     * @param coordX2 int; la coordenada final de la fila donde se quiere cambiar la ficha
     * @param coordY2 int; la coordenada final de la columna donde se quiere cambiar la ficha
     * @return Comodin hayComodin; retorna un comodin, si una casilla tiene, o nulo, si no hay 
     * @throws DamasException PLAYER1_CAN_CHOOSE_PIECE, PLAYER2_CAN_CHOOSE_PIECE
     */
    public Comodin changeFicha(int coordX1, int coordY1, int coordX2, int coordY2) throws DamasException{
        moveEatLogic(coordX1, coordY1, coordX2, coordY2);
        Comodin hayComodin = casillasAction(coordX2, coordY2);
        chooseNewFicha(coordX2, coordY2);
        return hayComodin;
    }

    /**
     * @param coordX1 int; la coordenada inicial de la fila donde se encuentra la ficha que se quiere mover
     * @param coordY1 int; la coordenada inicial de la columna donde se encuentra la ficha que se quiere mover
     * @param coordX2 int; la coordenada final de la fila donde se quiere cambiar la ficha
     * @param coordY2 int; la coordenada final de la columna donde se quiere cambiar la ficha
     * @return Comodin hayComodin; retorna un comodin, si una casilla tiene, o nulo, si no hay
     * @throws DamasException PLAYER1_CAN_CHOOSE_PIECE, PLAYER2_CAN_CHOOSE_PIECE
     */
    public Comodin comerFicha(int coordX1, int coordY1, int coordX2, int coordY2, int fila, int columna) throws DamasException{
        moveEatLogic(coordX1, coordY1, coordX2, coordY2);
        if(piezasJuego[coordX1+fila][coordY1+columna].getComible() == 0){
            piezasJuego[coordX1+fila][coordY1+columna] = null;
            juego[coordX1+fila][coordY1+columna] = 3;
        }
        else{
            piezasJuego[coordX1+fila][coordY1+columna].subtComible();
        }
        Comodin hayComodin = casillasAction(coordX2, coordY2);
        if(piezasJuego[coordX2][coordY2] != null){
            chooseNewFicha(coordX2, coordY2);
        }
        return hayComodin;
    }

    /**
     * Metodo que realiza el cambio de las posiciones de las fichas
     */
    private void moveEatLogic(int coordX1, int coordY1, int coordX2, int coordY2){
        Ficha ficha = piezasJuego[coordX2][coordY2];
        piezasJuego[coordX2][coordY2] = piezasJuego[coordX1][coordY1];
        piezasJuego[coordX1][coordY1] = ficha;
        int position = juego[coordX2][coordY2];
        juego[coordX2][coordY2] = juego[coordX1][coordY1];
        juego[coordX1][coordY1] = position;
    }

    private Comodin casillasAction(int coordX2, int coordY2){
        Comodin cmdin = casillas[coordX2][coordY2].getComodin();
        ArrayList<Integer[]> positions = casillas[coordX2][coordY2].action();
        if(positions != null){
            if(positions.size() > 2){
                for(Integer[] pos: positions){
                    if(pos[2]== 0){
                        juego[pos[0]][pos[1]] = 3;
                        piezasJuego[pos[0]][pos[1]] = null;
                    }
                }
            }
            else if(positions.size() == 2){
                moveEatLogic(positions.get(0)[0], positions.get(0)[1], positions.get(1)[0], positions.get(1)[1]);
            }
        }
        return cmdin;
    }

    private void chooseNewFicha(int coordX, int coordY) throws DamasException{
        if(piezasJuego[coordX][coordY]!= null && piezasJuego[coordX][coordY].getTipo() == "Normal"){
            if(piezasJuego[coordX][coordY].getNumero() == 1 && coordX == 0){
                throw new DamasException(DamasException.PLAYER1_CAN_CHOOSE_PIECE);
            }
            else if(piezasJuego[coordX][coordY].getNumero() == 2 && coordX == SIZE-1){
                throw new DamasException(DamasException.PLAYER2_CAN_CHOOSE_PIECE);
            }
        }
    }

    public int getSize(){
        return SIZE;
    }

}
