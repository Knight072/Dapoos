package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DamasGUI extends JDialog {
    private JButton maquina;
    private JButton jugador;

    public DamasGUI(){
    	setTitle("DaPOO");
        setSize(730,480);
        prepareElements();
        prepareActions();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void prepareElements(){
        JPanel pantalla = new JPanel();
        pantalla.setLayout(null);
        Dimension pantallas = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantallas.height;
        int width = pantallas.width;
        setSize(width / 2, height / 2);
        setVisible(true);
        JLabel titulo = new JLabel("DAPOO");
        titulo.setFont(new Font("Madison Street", Font.BOLD, 100));

        maquina = new JButton("Jugador vs Maquina");
        jugador = new JButton("Jugador vs Jugador");
        titulo.setBounds(170, 0, 400, 100);
        maquina.setBounds(230, 150, 200, 50);
        jugador.setBounds(230, 250, 200, 50);

        pantalla.add(titulo);
        pantalla.add(maquina);
        pantalla.add(jugador);
        add(pantalla);
    }

    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener Cerrar = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
        this.addWindowListener(Cerrar);
        maquina.addActionListener(e-> configuracionMaquina());
        jugador.addActionListener(e->configuracionJuego());
    }

    public void configuracionMaquina(){
        JugadorVSMaquinaGUI config = new JugadorVSMaquinaGUI();
        config.setVisible(true);
        this.dispose();

    }

    public void configuracionJuego(){
        JugadorVsJugadorGUI config = new JugadorVsJugadorGUI();
        config.setVisible(true);
        this.dispose();
    }

    public static void main(String[] agrs){
        DamasGUI prueba = new DamasGUI();
        prueba.setVisible(true);
    }
}