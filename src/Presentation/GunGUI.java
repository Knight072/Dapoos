package Presentation;

public class GunGUI extends ComodinGUI{
    private static GunGUI instance;
    private GunGUI(){
        message = "Gun: Dar doble click sobre la ficha enemiga a la que quiere eliminar";
    }

    public static GunGUI getInstance(){
        if(instance == null){
            instance = new GunGUI();
        }
        return instance;
    }

}
