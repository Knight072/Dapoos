package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Domain.Damas;

public class TableGUI extends JFrame{
	    private static final int sizeTable = 10;
	    Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
	    private JPanel[][] colors = new JPanel[sizeTable][sizeTable];
	    private JButton[][] botones = new JButton[sizeTable][sizeTable];
	    private JMenuItem guardar,salvar,salir,iniciarJugador,iniciarMaquina;
	    private JFileChooser selecArchivo;
	    private static String jugador1,jugador2,Cjugador1,Cjugador2,maquina,modoJuego;
	    private int porcentaje,intentosJugador1,intentosJugador2;

	    public TableGUI(String Jugador1,String Jugador2, int porcentaje,String maquina, String modoJuego){
	    	setTitle("DaPOO");
	    	setSize(dimensions.width , dimensions.height);
	        jugador1 = Jugador1;
	        jugador2 = Jugador2;
	        this.Cjugador1 = "Azul";
	        this.Cjugador2 = "Rojo";
	        this.maquina = maquina;
	        this.modoJuego = modoJuego;
	        this.porcentaje = porcentaje;
	        JPanel pantallas = preparePantalla();
	        this.getContentPane().add(pantallas);
	        prepareElementsMenu();
	        prepareBottons();
	        prepareActions();
	        prepareActionMenu();
	    }

    
    public JPanel preparePantalla() {
   	    JPanel ego = new JPanel();
        JPanel informacion = new JPanel();
        ego.setLayout(new GridLayout(1, 2));
        JPanel palatalTable = prepareTable();
        informacion.setLayout(null);
        JLabel informe = new JLabel("INFORMACION");
        JLabel Jugador1 = new JLabel("Jugador 1 :" + jugador1);
        JLabel Jugador2 = new JLabel("Jugador 2 :" + jugador2);
        JLabel intentos1 = new JLabel("Intentos Jugador 1: " + intentosJugador1);
        JLabel intentos2 = new JLabel("Intentos Jugador 2: " + intentosJugador2);
        informacion.setBorder(
                (Border) new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Información de Juego")));
        Jugador1.setBounds(50, 100, 500, 50);
        Jugador2.setBounds(50, 350, 500, 50);
        informe.setBounds(250, 50, 100, 50);
        intentos1.setBounds(50, 150, 1200, 50);
        intentos2.setBounds(50, 400, 200, 50);

        informacion.add(Jugador1);
        informacion.add(Jugador2);
        informacion.add(informe);
        informacion.add(intentos1);
        informacion.add(intentos2);

        ego.add(palatalTable);
        ego.add(informacion);
        

        
        return ego;
    }

    private JPanel prepareTable(){
        Damas.getInstance().setPlayers("p1", "p2",null);
    	String[][] table = Damas.getInstance().reconvertCasillas(porcentaje);
        JPanel juego = new JPanel();
        juego.setLayout(new GridLayout(sizeTable,sizeTable));
        for (int i = 0; i < sizeTable; i++) {
            for (int j = 0; j < sizeTable; j++){
                JButton boton = new JButton();
                boton.setOpaque(false);
                boton.setContentAreaFilled(false);
                boton.setBorderPainted(false);
                if(table[i][j] != null) {
                    try{
                        colors[i][j] = (CasillaGUI) Class.forName("Presentation."+table[i][j]+"GUI").getConstructor().newInstance();
                        colors[i][j].add(boton);
                        botones[i][j] = boton;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    colors[i][j] = new VAciaGUI();
                    colors[i][j].setBackground(Color.WHITE);
                }
            juego.add(colors[i][j]);
            }
        }
        return juego;
    }

    private void prepareBottons(){
    	int[][] juego = Damas.getInstance().getJuego();
        for(int i = 0; i < colors.length; i++){
            for(int j = 0; j < colors.length; j++){
                if(juego[i][j] == 1){
                    botones[i][j].setText("1");
                }
                else if(juego[i][j] == 2){
                	botones[i][j].setText("2");
                	botones[i][j].setBackground(Color.blue);
                }
                else if(juego[i][j] == 3){
                	botones[i][j].setText("0");
                    botones[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void prepareActions(){
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        gameButtonsOptions();
    }

    private void gameButtonsOptions(){
        for(int i=0; i<sizeTable; i++){
            for(int j=0; j<sizeTable; j++){
                if(botones[i][j] != null){
                    botones[i][j].addActionListener(ActionsButtonsTable.getInstance());
                }
            }
        }
        ActionsButtonsTable.getInstance().setButtons(botones);
    }

    public void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        guardar = new JMenuItem("Guardar Partida");
        salir = new JMenuItem("Salir del Juego");
        salvar = new JMenuItem("Cargar Partida");
        JMenu inicarPartida = new JMenu("Nueva Partida");
        iniciarJugador = new JMenuItem("Jugar vs Jugador");
        iniciarMaquina = new JMenuItem("Jugar vs Maquina");

        inicarPartida.add(iniciarMaquina);
        inicarPartida.add(iniciarJugador);

        menu.add(inicarPartida);
        menu.addSeparator();
        menu.add(guardar);
        menu.addSeparator();
        menu.add(salvar);
        menu.addSeparator();
        menu.add(salir);
        menuBar.add(menu);
    }

    public void prepareActionMenu(){
        guardar.addActionListener(e -> guardarPartida());
        salvar.addActionListener(e -> cargarPartida());
        salir.addActionListener(e -> exit());
        iniciarJugador.addActionListener(e -> VsJugador());
        iniciarMaquina.addActionListener(e -> VsMaquina());
    }

    /**
     * Me permite salir del juego
     */
    public void exit() {
        if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro que desea salir?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Me permite cargar una partida que tenia guardadas
     */
    public void cargarPartida() {
        selecArchivo = new JFileChooser();
        selecArchivo.setVisible(true);
        int action = selecArchivo.showOpenDialog(salvar);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = selecArchivo.getSelectedFile();
            JOptionPane.showMessageDialog(salvar,
                    "El archivo de nombre " + archivo.getName() + " que se trata de abrir en la ruta " + archivo
                            + "\n NO se pudo abrir ya que esta funcion se encuentra en construccion.");
        }
        selecArchivo.setVisible(false);
    }

    /**
     * Me permite guardar partidas.
     */
    public void guardarPartida() {
        selecArchivo = new JFileChooser();
        selecArchivo.setVisible(true);
        int action = selecArchivo.showSaveDialog(guardar);
        if (action == JFileChooser.APPROVE_OPTION) {
            File archivo = selecArchivo.getSelectedFile();
            JOptionPane.showMessageDialog(guardar,
                    "El archivo de nombre " + archivo.getName() + " que se trata de abrir en la ruta " + archivo
                            + "\n NO se pudo abrir ya que esta funcion se encuentra en construccion.");
        }
        selecArchivo.setVisible(false);
    }
    
    public void VsJugador() {
    	if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro que desea iniciar otra partida contra otro jugador",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    		JugadorVsJugadorGUI config = new JugadorVsJugadorGUI();
            config.setVisible(true);
            this.dispose();
        }
    }
    
    public void VsMaquina() {
    	if (JOptionPane.showConfirmDialog(rootPane, "Esta seguro que desea iniciar otra partida contra otro jugador",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    		JugadorVSMaquinaGUI config = new JugadorVSMaquinaGUI();
            config.setVisible(true);
            this.dispose();
        }
    }
}
