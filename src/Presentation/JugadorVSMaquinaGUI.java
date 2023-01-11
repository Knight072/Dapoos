package Presentation;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Domain.*;

public class JugadorVSMaquinaGUI extends JDialog {
    private final JRadioButton principiante = new JRadioButton("Principiante");
    private final JRadioButton aprendiz = new JRadioButton("Aprendiz");
    private final JRadioButton normal = new JRadioButton("Normal");
    private final JRadioButton quicktime = new JRadioButton("Quicktime");
    private final JRadioButton relampago = new JRadioButton("Relampago");
    private final JRadioButton permanente = new JRadioButton("Permanente");

    private final JButton jugar = new JButton("Empezar a Jugar");
    private JTextField nombreJugador1,porcentaje;
    private String modoMaquina,tipo,tipoCasilla;

    public JugadorVSMaquinaGUI(){
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
        ingresarNombre.setLayout(new GridLayout(1, 1));

        JPanel panelFichas = new JPanel();
        panelFichas.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Configuracion")));

        JPanel panelMaquina = new JPanel();
        panelMaquina
                .setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Configure la maquina")));

        JLabel jugador = new JLabel("Ingrese Su Nombre:");
        jugador.setBounds(20, 40, 200, 50);
        nombreJugador1 = new JTextField();
        nombreJugador1.setBounds(150, 50, 180, 30);
        JLabel dificultadMaquina = new JLabel("Nivel de Dificultad de la Maquina:");
        dificultadMaquina.setBounds(20, 100, 200, 20);
        principiante.setBounds(50, 140, 100, 20);
        aprendiz.setBounds(200, 140, 100, 20);
        JLabel modos = new JLabel("Modo de Juego:");
        modos.setBounds(20, 180, 200, 20);
        normal.setBounds(50, 220, 100, 20);
        quicktime.setBounds(200, 220, 100, 20);
        JLabel casillas = new JLabel("Visualización de casillas:");
        casillas.setBounds(20, 260, 200, 20);
        permanente.setBounds(50, 300, 100, 20);
        relampago.setBounds(200, 300, 100, 20);
        JLabel pregunta = new JLabel("Digite el porcentade de casillas especiales:");
        pregunta.setBounds(20,340,300,20);
        porcentaje = new JTextField();
        porcentaje.setBounds(300,340,40,20);
        jugar.setBounds(75, 370, 200, 50);



        panelFichas.setLayout(null);
        panelFichas.add(jugador);
        panelFichas.add(nombreJugador1);
        panelFichas.add(jugar);
        panelFichas.add(modos);
        panelFichas.add(principiante);
        panelFichas.add(aprendiz);
        panelFichas.add(casillas);
        panelFichas.add(permanente);
        panelFichas.add(relampago);
        panelFichas.add(dificultadMaquina);
        panelFichas.add(normal);
        panelFichas.add(quicktime);
        panelFichas.add(pregunta);
        panelFichas.add(porcentaje);
        
        ButtonGroup modosMaquina = new ButtonGroup();
        modosMaquina.add(principiante);
        modosMaquina.add(aprendiz);
        ButtonGroup tipoCasillas = new ButtonGroup();
        tipoCasillas.add(permanente);
        tipoCasillas.add(relampago);
        ButtonGroup modosJuego = new ButtonGroup();
        modosJuego.add(normal);
        modosJuego.add(quicktime);
        

        pantallaCarga.setLayout(new GridLayout(1, 1));
        pantallaCarga.add(panelFichas);

        add(pantallaCarga);
    }

    public void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowListener Cerrar = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        };
        this.addWindowListener(Cerrar);
        principiante.addActionListener(e -> checkBox());
        aprendiz.addActionListener(e -> checkBox());
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
        if (principiante.isSelected()) {
            modoMaquina ="principiante";
        }
        if (aprendiz.isSelected()) {
        	modoMaquina = "aprendiz";
        }
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
        if ( modoMaquina != null && tipo != null && tipoCasilla != null && !porcentaje.getText().isEmpty() && !nombreJugador1.getText().isEmpty()){
            try{
                int number =Integer.parseInt(porcentaje.getText());
                String nombre = nombreJugador1.getText();
                String maquina = "DaPooInteligancia01";
                TableGUI tablero = new TableGUI(nombre,maquina,number,modoMaquina,tipo);
                tablero.setVisible(true);
                this.dispose();
            }catch(NumberFormatException e){
                throw new DamasException(DamasException.BAD_PERCENTAGE);
            }
        }else if ( modoMaquina == null || tipo == null || tipoCasilla == null|| nombreJugador1.getText().isEmpty() || porcentaje.getText().isEmpty() ) {
            throw new DamasException(DamasException.IS_EMPTY);
        }
    }
}

