package Presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Domain.Damas;
import Domain.DamasException;

public class ActionsButtonsTable implements ActionListener{
    private static ArrayList<Integer> buttonA = new ArrayList<>();
    private static ArrayList<Integer> buttonB = new ArrayList<>();
    private static ActionsButtonsTable instance;
    private JButton[][] botones = new JButton[sizeTable][sizeTable];
    private static final int sizeTable = 10;
    private boolean comodinOno;

    public static ActionsButtonsTable getInstance(){
        if(instance == null){
            instance = new ActionsButtonsTable();
        }
        return instance;
    }
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        if(buttonA.isEmpty()){
            for(int i = 0; i< sizeTable; i++){
                for(int j = 0; j< sizeTable; j++){
                    if(botones[i][j] == button){
                        buttonA.add(i);
                        buttonA.add(j);
                    }
                }
            }
        }
        else if(buttonB.isEmpty() && !(buttonA.isEmpty())){
            for(int i = 0; i< sizeTable; i++){
                for(int j = 0; j< sizeTable; j++){
                    if(botones[i][j] == button){
                        buttonB.add(i);
                        buttonB.add(j);
                    }
                }
            }
            if(comodinOno){
                useComodin();
            }
            else{
                this.moverOComer();
            }
        }
    }
    public void moverOComer(){
        try{
            Damas.getInstance().moverOComer(buttonA.get(0), buttonA.get(1), buttonB.get(0), buttonB.get(1));
        }catch(DamasException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            if(e.getMessage() == DamasException.PLAYER1_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha(JOptionPane.showInputDialog("Digite la ficha que quiere escoger"), buttonB.get(0), buttonB.get(1));
            }
            else if(e.getMessage() == DamasException.PLAYER2_CAN_CHOOSE_PIECE){
                Damas.getInstance().putNewFicha(JOptionPane.showInputDialog("Digite la ficha que quiere escoger"), buttonB.get(0), buttonB.get(1));
            }
            else if(e.getMessage() == DamasException.COMODIN_CAPTURE){
                if(Damas.getInstance().playerWithComodin()== "Gun"){
                    JOptionPane.showMessageDialog(null,GunGUI.getInstance().getMessage());
                }
                else if(Damas.getInstance().playerWithComodin() == "Stomp"){
                    JOptionPane.showMessageDialog(null,StompGUI.getInstance().getMessage());
                }
                comodinOno = true;
            }   
        }
        buttonA.clear();
        buttonB.clear();
        resetPositions();
    }

    private void resetPositions(){
        int[][] fichasJuego = Damas.getInstance().getJuego();
        for(int i = 0; i< sizeTable; i++){
            for(int j = 0; j<sizeTable; j++){
                if(fichasJuego[i][j] == 1){
                    botones[i][j].setText(Damas.getInstance().getTipoFicha(i, j));
                    botones[i][j].setOpaque(true);
                    botones[i][j].setContentAreaFilled(true);
                    botones[i][j].setBackground(Color.red);
                }
                else if(fichasJuego[i][j] == 2){
                    botones[i][j].setText(Damas.getInstance().getTipoFicha(i, j));
                    botones[i][j].setOpaque(true);
                    botones[i][j].setContentAreaFilled(true);
                    botones[i][j].setBackground(Color.blue);
                }
                else if(fichasJuego[i][j] == 3){
                    botones[i][j].setText("0");
                    botones[i][j].setOpaque(false);
                    botones[i][j].setContentAreaFilled(false);
                    botones[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void useComodin(){
        comodinOno = false;
        try{
            Damas.getInstance().useComodin(buttonA.get(0), buttonA.get(1), buttonB.get(0), buttonB.get(1));
        }catch(DamasException e){
            buttonA.clear();
            buttonB.clear();
            resetPositions();
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        resetPositions();
    }

    public void setButtons(JButton[][] buttons){
        this.botones = buttons;
    }
}
