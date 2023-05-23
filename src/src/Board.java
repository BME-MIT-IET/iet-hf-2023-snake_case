package src;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board implements Serializable {
    //A jatekmezoket tarolja.
    private static ArrayList<Field> fields = new ArrayList<>();
    private static ArrayList<Virologist> virologusok = new ArrayList<>();
    private static ArrayList<Equipment> felszerelesek = new ArrayList<>();
    private static ArrayList<GCode> genetikaiKodok = new ArrayList<>();
    
    //A jatekmezo koordinatakat tarolja, egy sor: (n,x1,y1,x2,y2,...,xn,yn)
    private static ArrayList<int[]> polyKoordok = new ArrayList<>();  //Ez szukseges a polygon kirajzolashoz -Dani
    
    /*Szamon tartja a jatekos hatralevo akcioit, ahol a jatekos a virologist0 (0 idx-u virologus)*/
    private boolean VirAction = true;
    private boolean VirMove = true;

    //Getterek
    public ArrayList<Virologist> getVirologusok(){
        return virologusok;
    }
    public ArrayList<Field> getMezok(){
        return fields;
    }
    public ArrayList<Equipment> getFelszerelesek(){
        return felszerelesek;
    }
    public ArrayList<GCode> getGenetikaiKodok(){
        return genetikaiKodok;
    }
    public boolean getAction() {
    	return VirAction;
    }
    public boolean getMove() {
    	return VirMove;
    }
    public ArrayList<int[]> getPolyKoordok(){   //Uj! -Dani
    	return polyKoordok;
    }

    //Setterek
    public void SetNULLVirologusok(){
        virologusok = new ArrayList<>();
    }
    public void SetNULLMezok(){
        fields = new ArrayList<>();
    }
    public void SetNULLFelszerelesek(){
        felszerelesek = new ArrayList<>();
    }
    public void SetNULLGenetikaiKodok(){
        genetikaiKodok = new ArrayList<>();
    }
    public void setAction(boolean b) {
    	VirAction = b;
    }
    public void setMove(boolean b) {
    	VirMove = b;
    }
    public void setNULLPolyKoordok(){   //Uj! -Dani
    	polyKoordok = new ArrayList<>();
    }
    public void setVirologusok(ArrayList<Virologist> vr){
        virologusok = vr;
    }
    public void setFelszerelesek(ArrayList<Equipment> el){
        felszerelesek = el;
    }
    public void setGenetikaiKodok(ArrayList<GCode> gcl){
        genetikaiKodok = gcl;
    }
    public void setFields(ArrayList<Field> fl){
        fields = fl;
    }

    public Board(){
    }

    //A jatekter egy mezojet hozza letre.
    public void FieldGen(){
    }
}
