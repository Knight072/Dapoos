package Presentation;

import Domain.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class JugadorVsJugadorGUI extends JDialog {

    private final JRadioButton normal = new JRadioButton("Normal");
    private final JRadioButton quicktime = new JRadioButton("Quicktime");
    private final JRadioButton relampago = new JRadioButton("Relampago");
    private final JRadioButton permanente = new JRadioButton("Permanente");
    private final JButton jugar = new JButton("Empezar a Jugar");
    private JTextField nombreJugador1, nombreJugador2,porcentaje;
    private String tipo,tipoCasilla;

    public JugadorVsJugadorGUI() {
    	setTitle("DaPOO");
        setSize(370,480);
        prepareElements();
        prepareActions();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void prepareElements(){
        JPanel pantallaCarga = new JPanel();
        JPanel ingresarNombre = new JPanel();
        ingresarNombre.setLayout(new GridLayout(1, 2));

        JPanel panelFichas = new JPanel();
        panelFichas.setBorder(
                new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Configuracion")));
        
        JLabel jugador = new JLabel("Ingrese Su Nombre Jugador 1:");
        jugador.setBounds(20, 40, 200, 50);
        nombreJugador1 = new JTextField();
        nombreJugador1.setBounds(200, 50, 140, 30);
        JLabel jugador2 = new JLabel("Ingrese Su Nombre Jugador 2:");
        jugador2.setBounds(20, 100, 200, 50);
        nombreJugador2 = new JTextField();
        nombreJugador2.setBounds(200, 110, 140, 30);
        JLabel casillas = new JLabel("Visualización de casillas:");
        casillas.setBounds(20, 150, 200, 50);
        permanente.setBounds(50, 175, 100, 50);
        relampago.setBounds(200, 175, 100, 50);
        JLabel modos = new JLabel("Modo de Juego:");
        modos.setBounds(20, 200, 200, 50);
        normal.setBounds(50, 225, 100, 50);
        quicktime.setBounds(200, 225, 100, 50);
        JLabel pregunta = new JLabel("Digite el porcentade de casillas especiales:");
        pregunta.setBounds(20,275,300,50);
        porcentaje = new JTextField();
        porcentaje.setBounds(300,285,40,20);
        jugar.setBounds(75, 350, 200, 50);
        



        panelFichas.setLayout(null);
        panelFichas.add(jugador);
        panelFichas.add(nombreJugador1);
        panelFichas.add(casillas);
        panelFichas.add(relampago);
        panelFichas.add(permanente);
        panelFichas.add(jugar);
        panelFichas.add(nombreJugador2);
        panelFichas.add(jugador2);
        panelFichas.add(normal);
        panelFichas.add(quicktime);
        panelFichas.add(modos);
        panelFichas.add(porcentaje);
        panelFichas.add(pregunta);

        ButtonGroup modosJuego = new ButtonGroup();
        modosJuego.add(normal);
        modosJuego.add(quicktime);
        

        ButtonGroup tipoCasillas = new ButtonGroup();
        tipoCasillas.add(permanente);
        tipoCasillas.add(relampago);

        pantallaCarga.setLayout(new GridLayout(1,1));
        pantallaCarga.add(panelFichas);

        add(pantallaCarga);
    }

    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener Cerrar = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
        this.addWindowListener(Cerrar);
        normal.addActionListener(e -> checkBox());
        quicktime.addActionListener(e -> checkBox());
        permanente.addActionListener(e -> checkBox());
        relampago.addActionListener(e -> checkBox());
        jugar.addActionListener(e -> {
            try {
                empezarJuego();
            } catch (DamasException e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage());
            }
        });
    }

    private void checkBox(){
        if (normal.isSelected()) {
            tipo = "normal";
        }
        if (quicktime.isSelected()) {
            tipo = "quicktime";
        }if (permanente.isSelected()) {
        	tipoCasilla = "permanente";
        }
        if (relampago.isSelected()) {
            tipoCasilla = "relampago";
        }
    }

    private void empezarJuego() throws DamasException {
    	if ( tipo != null && tipoCasilla != null && !nombreJugador2.getText().isEmpty() && !nombreJugador1.getText().isEmpty()){
            try{
                int number =Integer.parseInt(porcentaje.getText());
                String nombre = nombreJugador1.getText();
                String nombre2 = nombreJugador2.getText();
                TableGUI tablero = new TableGUI(nombre,nombre2,number,"no",tipo);
                tablero.setVisible(true);
                this.dispose();
            }catch(NumberFormatException e){
                throw new DamasException(DamasException.BAD_PERCENTAGE);
            }
        }else if ( nombreJugador2.getText().isEmpty()  || nombreJugador1.getText().isEmpty() || tipo != null || tipoCasilla != null) {
            throw new DamasException(DamasException.IS_EMPTY);
        }
    }
}

