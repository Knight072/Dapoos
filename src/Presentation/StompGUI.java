package Presentation;

public class StompGUI extends ComodinGUI{
    private static StompGUI instance;

    private StompGUI(){
        message = "Stomp: Primero seleccione una ficha suya y luego la enemiga que quiere aplastar";
    }

    public static StompGUI getInstance(){
        if(instance == null){
            instance = new StompGUI();
        }
        return instance;
    }
}
