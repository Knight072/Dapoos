package Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Domain.Damas;
import Domain.DamasException;

public class DamasTest{
    @Test
    public void shouldSetTwoPlayers(){
        Damas damas = new Damas();
        damas.setPlayers("P1", "P2", null);
        assertTrue(damas.ok());
    }

    @Test
    public void shouldSetOnePlayerOneMachine(){
        Damas damas = new Damas();
        damas.setPlayers("P1","P2", "Principiante");
        assertTrue(Damas.getInstance().ok());
    }

    @Test
    public void shouldNotBeEqualAnInstanceWithAnother(){
        Damas damas = new Damas();
        assertNotEquals(Damas.getInstance(), damas);
    }

    @Test
    public void shouldCreateAMatrixWithOneAndZeroLikeAChessTable(){
        int[][] object={{0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0}};
        assertArrayEquals(Damas.getInstance().getTableDraw(), object);
    }

    @Test
    public void shouldReturnTheInitialPositionOfTheGamePieces(){
        int[][] object={{0,2,0,2,0,2,0,2,0,2},
                        {2,0,2,0,2,0,2,0,2,0},
                        {0,2,0,2,0,2,0,2,0,2},
                        {2,0,2,0,2,0,2,0,2,0},
                        {0,3,0,3,0,3,0,3,0,3},
                        {3,0,3,0,3,0,3,0,3,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0}};
        Damas.getInstance().reconvertCasillas(0);
        assertArrayEquals(Damas.getInstance().getJuego(), object);
    }

    @Test
    public void shouldCreateTheSpacesOfTheTableWhioutSpecialSpaces(){
        String[][] object= {{null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null}};
        assertArrayEquals(Damas.getInstance().reconvertCasillas(0), object);
    }

    @Test
    public void shouldCreateTheSpacesOfTheTableWhitSpecialSpaces(){
        String[][] object= {{null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null},
                            {null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla"},
                            {"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null,"NCasilla",null}};
        assertNotEquals(Damas.getInstance().reconvertCasillas(50), object);
    }

    @Test
    public void shouldPlayerOneMoveATokenAtTheLeft(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,2,0,2,0,2,0,2,0,2},
                        {2,0,2,0,2,0,2,0,2,0},
                        {0,2,0,2,0,2,0,2,0,2},
                        {2,0,2,0,2,0,2,0,2,0},
                        {0,3,0,3,0,3,0,3,0,3},
                        {1,0,3,0,3,0,3,0,3,0},
                        {0,3,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 1, 5, 0);
        }catch(DamasException e){
            e.printStackTrace();
        }
        assertArrayEquals(Damas.getInstance().getJuego(), object);
    }

    @Test
    public void shouldPlayerOneCreateANinjaToken(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,1,0,2,0,2,0,2,0,2},
                        {2,0,3,0,2,0,2,0,2,0},
                        {0,2,0,3,0,2,0,2,0,2},
                        {2,0,2,0,1,0,3,0,2,0},
                        {0,2,0,3,0,2,0,3,0,3},
                        {1,0,1,0,3,0,3,0,3,0},
                        {0,1,0,3,0,3,0,3,0,1},
                        {1,0,3,0,1,0,1,0,1,0},
                        {0,1,0,1,0,1,0,1,0,1},
                        {1,0,1,0,1,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 5, 5, 4);//juego jugador1
            Damas.getInstance().moverOComer(3, 4, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(5, 4, 4, 3);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 6);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 4, 5);//juego jugador1
            Damas.getInstance().moverOComer(2, 3, 3, 4);//juego jugador2
            Damas.getInstance().moverOComer(4, 5, 2, 3);//juego jugador1
            Damas.getInstance().moverOComer(3, 6, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(6, 3, 5, 2);//juego jugador1
            Damas.getInstance().moverOComer(3, 0, 4, 1);//juego jugador2
            Damas.getInstance().moverOComer(4, 3, 3, 4);//juego jugador1
            Damas.getInstance().moverOComer(2, 1, 3, 0);//juego jugador2
            Damas.getInstance().moverOComer(6, 1, 5, 0);//juego jugador1
            Damas.getInstance().moverOComer(1, 2, 2, 1);//juego jugador2
            Damas.getInstance().moverOComer(7, 2, 6, 1);//juego jugador1
            Damas.getInstance().moverOComer(0, 1, 1, 2);//juego jugador2
            Damas.getInstance().moverOComer(2, 3, 0, 1);//juego jugador1
        }catch(DamasException e){
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha("Ninja", 0, 1);
                assertEquals(Damas.getInstance().getTipoFicha(0, 1), "Ninja");
            }
        }
    }

    @Test
    public void shouldPlayerTwoCreateANinjaToken(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,1,0,2,0,2,0,2,0,2},
                        {2,0,3,0,2,0,2,0,2,0},
                        {0,2,0,3,0,2,0,2,0,2},
                        {2,0,2,0,1,0,3,0,2,0},
                        {0,2,0,3,0,3,0,3,0,3},
                        {1,0,1,0,3,0,3,0,3,0},
                        {0,1,0,3,0,1,0,3,0,1},
                        {1,0,3,0,1,0,1,0,1,0},
                        {0,1,0,3,0,1,0,1,0,1},
                        {1,0,1,0,2,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 5, 5, 4);//juego jugador1
            Damas.getInstance().moverOComer(3, 4, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(5, 4, 4, 3);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 6);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 4, 5);//juego jugador1
            Damas.getInstance().moverOComer(2, 3, 3, 4);//juego jugador2
            Damas.getInstance().moverOComer(4, 5, 2, 3);//juego jugador1
            Damas.getInstance().moverOComer(3, 6, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(6, 3, 5, 2);//juego jugador1
            Damas.getInstance().moverOComer(3, 0, 4, 1);//juego jugador2
            Damas.getInstance().moverOComer(4, 3, 3, 4);//juego jugador1
            Damas.getInstance().moverOComer(2, 1, 3, 0);//juego jugador2
            Damas.getInstance().moverOComer(6, 1, 5, 0);//juego jugador1
            Damas.getInstance().moverOComer(1, 2, 2, 1);//juego jugador2
            Damas.getInstance().moverOComer(7, 2, 6, 1);//juego jugador1
            Damas.getInstance().moverOComer(0, 1, 1, 2);//juego jugador2
            Damas.getInstance().moverOComer(2, 3, 0, 1);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 4, 6, 5);//juego jugador1
            Damas.getInstance().moverOComer(5, 4, 6, 3);//juego jugador2
            Damas.getInstance().moverOComer(8, 3, 7, 4);//juego jugador1
            Damas.getInstance().moverOComer(6, 3, 7, 2);//juego jugador2
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador1
            Damas.getInstance().moverOComer(7, 2, 9, 4);//juego jugador2
        }catch(DamasException e){
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha("Ninja", 9, 4);
                assertEquals(Damas.getInstance().getTipoFicha(9, 4), "Ninja");
            }
        }
    }

    @Test
    public void shouldPlayerTwoCreateAReinaToken(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,1,0,2,0,2,0,2,0,2},
                        {2,0,3,0,2,0,2,0,2,0},
                        {0,2,0,3,0,2,0,2,0,2},
                        {2,0,2,0,1,0,3,0,2,0},
                        {0,2,0,3,0,3,0,3,0,3},
                        {1,0,1,0,3,0,3,0,3,0},
                        {0,1,0,3,0,1,0,3,0,1},
                        {1,0,3,0,1,0,1,0,1,0},
                        {0,1,0,3,0,1,0,1,0,1},
                        {1,0,1,0,2,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 5, 5, 4);//juego jugador1
            Damas.getInstance().moverOComer(3, 4, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(5, 4, 4, 3);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 6);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 4, 5);//juego jugador1
            Damas.getInstance().moverOComer(2, 3, 3, 4);//juego jugador2
            Damas.getInstance().moverOComer(4, 5, 2, 3);//juego jugador1
            Damas.getInstance().moverOComer(3, 6, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(6, 3, 5, 2);//juego jugador1
            Damas.getInstance().moverOComer(3, 0, 4, 1);//juego jugador2
            Damas.getInstance().moverOComer(4, 3, 3, 4);//juego jugador1
            Damas.getInstance().moverOComer(2, 1, 3, 0);//juego jugador2
            Damas.getInstance().moverOComer(6, 1, 5, 0);//juego jugador1
            Damas.getInstance().moverOComer(1, 2, 2, 1);//juego jugador2
            Damas.getInstance().moverOComer(7, 2, 6, 1);//juego jugador1
            Damas.getInstance().moverOComer(0, 1, 1, 2);//juego jugador2
            Damas.getInstance().moverOComer(2, 3, 0, 1);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 4, 6, 5);//juego jugador1
            Damas.getInstance().moverOComer(5, 4, 6, 3);//juego jugador2
            Damas.getInstance().moverOComer(8, 3, 7, 4);//juego jugador1
            Damas.getInstance().moverOComer(6, 3, 7, 2);//juego jugador2
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador1
            Damas.getInstance().moverOComer(7, 2, 9, 4);//juego jugador2
        }catch(DamasException e){
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha("Reina", 9, 4);
                assertEquals(Damas.getInstance().getTipoFicha(9, 4), "Reina");
            }
        }
    }

    @Test
    public void shouldPlayerTwoMoveANinjaToken(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,1,0,2,0,2,0,2,0,2},
                        {2,0,3,0,2,0,2,0,2,0},
                        {0,2,0,3,0,2,0,2,0,2},
                        {2,0,2,0,1,0,3,0,2,0},
                        {0,2,0,3,0,3,0,3,0,3},
                        {1,0,1,0,3,0,3,0,3,0},
                        {0,1,0,3,0,1,0,1,0,1},
                        {1,0,3,0,1,0,3,0,1,0},
                        {0,1,0,2,0,1,0,1,0,1},
                        {1,0,1,0,3,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 5, 5, 4);//juego jugador1
            Damas.getInstance().moverOComer(3, 4, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(5, 4, 4, 3);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 6);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 4, 5);//juego jugador1
            Damas.getInstance().moverOComer(2, 3, 3, 4);//juego jugador2
            Damas.getInstance().moverOComer(4, 5, 2, 3);//juego jugador1
            Damas.getInstance().moverOComer(3, 6, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(6, 3, 5, 2);//juego jugador1
            Damas.getInstance().moverOComer(3, 0, 4, 1);//juego jugador2
            Damas.getInstance().moverOComer(4, 3, 3, 4);//juego jugador1
            Damas.getInstance().moverOComer(2, 1, 3, 0);//juego jugador2
            Damas.getInstance().moverOComer(6, 1, 5, 0);//juego jugador1
            Damas.getInstance().moverOComer(1, 2, 2, 1);//juego jugador2
            Damas.getInstance().moverOComer(7, 2, 6, 1);//juego jugador1
            Damas.getInstance().moverOComer(0, 1, 1, 2);//juego jugador2
            Damas.getInstance().moverOComer(2, 3, 0, 1);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 4, 6, 5);//juego jugador1
            Damas.getInstance().moverOComer(5, 4, 6, 3);//juego jugador2
            Damas.getInstance().moverOComer(8, 3, 7, 4);//juego jugador1
            Damas.getInstance().moverOComer(6, 3, 7, 2);//juego jugador2
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador1
            Damas.getInstance().moverOComer(7, 2, 9, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 6, 6, 7);//juego jugador1
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador2
        }catch(DamasException e){
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha("Ninja", 9, 4);
                assertArrayEquals(Damas.getInstance().getJuego(), object);
            }
        }
    }

    @Test
    public void shouldPlayerTwoMoveAReinaToken(){
        Damas.getInstance().setPlayers("P1", "P2", null);
        Damas.getInstance().reconvertCasillas(0);
        int[][] object={{0,1,0,2,0,2,0,2,0,2},
                        {2,0,3,0,2,0,2,0,2,0},
                        {0,2,0,3,0,2,0,2,0,2},
                        {2,0,2,0,1,0,2,0,2,0},
                        {0,2,0,3,0,3,0,3,0,3},
                        {1,0,1,0,3,0,3,0,1,0},
                        {0,1,0,3,0,1,0,3,0,1},
                        {1,0,3,0,1,0,1,0,1,0},
                        {0,1,0,3,0,1,0,3,0,1},
                        {1,0,1,0,3,0,1,0,1,0}};
        try{
            Damas.getInstance().moverOComer(6, 5, 5, 4);//juego jugador1
            Damas.getInstance().moverOComer(3, 4, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(5, 4, 4, 3);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 6);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 4, 5);//juego jugador1
            Damas.getInstance().moverOComer(2, 3, 3, 4);//juego jugador2
            Damas.getInstance().moverOComer(4, 5, 2, 3);//juego jugador1
            Damas.getInstance().moverOComer(3, 6, 4, 5);//juego jugador2
            Damas.getInstance().moverOComer(6, 3, 5, 2);//juego jugador1
            Damas.getInstance().moverOComer(3, 0, 4, 1);//juego jugador2
            Damas.getInstance().moverOComer(4, 3, 3, 4);//juego jugador1
            Damas.getInstance().moverOComer(2, 1, 3, 0);//juego jugador2
            Damas.getInstance().moverOComer(6, 1, 5, 0);//juego jugador1
            Damas.getInstance().moverOComer(1, 2, 2, 1);//juego jugador2
            Damas.getInstance().moverOComer(7, 2, 6, 1);//juego jugador1
            Damas.getInstance().moverOComer(0, 1, 1, 2);//juego jugador2
            Damas.getInstance().moverOComer(2, 3, 0, 1);//juego jugador1
            Damas.getInstance().moverOComer(4, 5, 5, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 4, 6, 5);//juego jugador1
            Damas.getInstance().moverOComer(5, 4, 6, 3);//juego jugador2
            Damas.getInstance().moverOComer(8, 3, 7, 4);//juego jugador1
            Damas.getInstance().moverOComer(6, 3, 7, 2);//juego jugador2
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador1
            Damas.getInstance().moverOComer(7, 2, 9, 4);//juego jugador2
            Damas.getInstance().moverOComer(7, 6, 6, 7);//juego jugador1
            Damas.getInstance().moverOComer(9, 4, 8, 3);//juego jugador2
            Damas.getInstance().moverOComer(6, 7, 5, 8);//juego jugador1
            Damas.getInstance().moverOComer(8, 3, 7, 2);//juego jugador2
            Damas.getInstance().moverOComer(8, 7, 7, 6);//juego jugador1
            Damas.getInstance().moverOComer(7, 2, 3, 6);//juego jugador2
        }catch(DamasException e){
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha("Reina", 9, 4);
                assertArrayEquals(Damas.getInstance().getJuego(), object);
            }
        }
    }
}