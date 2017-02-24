package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.22..
 */

public class Buildings {
    //populáció, hus, fa, kö, arany
    public static Building haz = new Building(-2,-6,-3,-2,-5,           1,0,0,0,1,0,0,0,0,0);
    public static Building banya =new Building(-2,0,-5,0,-4,            0,0,0,3,2,0,0,0,0,0);
    public static Building farm =new Building(-1,0,-3,-3,-2,            0,2,0,0,0,0,0,0,0,0);
    public static Building farmnagy =new Building(-5,0,-10,0,-10,       0,10,0,0,0,0,0,0,0,0);
    public static Building faKitermelo =new Building(-2,0,0,-3,-2,      0,0,3,0,0,0,0,0,0,0);
    public static Building kut = new Building(0,0,-5,-5,-1,             0,0,0,0,0,0,0,0,0,0);
    public static Building hid = new Building(0,0,-10,-50,0,            0,0,0,0,0,0,0,0,0,0);
    public static Building kikepzo = new Building(-15,-15,-15,-15,-15,  0,0,0,0,0,0,0,0,0,0);

    public static boolean epuletFejlesztes(String kod){
        Building b = null;
        if (kod.equals("haz")) {
            b = haz;
        } else if (kod.equals("banya")) {
            b = banya;
        } else if (kod.equals("farm")) {
            b = farm;
        } else if (kod.equals("faKitermelo")) {
            b = faKitermelo;
        } else if (kod.equals("kut")) {
            b = kut;
        } else if (kod.equals("kikepzo")) {
            b = kikepzo;
        } else if (kod.equals("hid")){
            b = hid;
        }else if (kod.equals("farmnagy")) {
            b = farmnagy;
        }
        return epul(b);
    }

    private static boolean epul(Building b){
        if(
            Statistics.arany + b.aranyLetrehoz >= 0 &&
            Statistics.fa + b.faLetrehoz >= 0 &&
            Statistics.ko + b.koLetrehoz >= 0 &&
            Statistics.kaja + b.kajaLetrehoz >= 0 &&
            Statistics.lakosokszama + b.lakossagLetrehoz >= 0
                ){
            Statistics.arany += b.aranyLetrehoz;
            Statistics.fa += b.faLetrehoz;
            Statistics.ko += b.koLetrehoz;
            Statistics.kaja += b.kajaLetrehoz;
            Statistics.lakosokszama += b.lakossagLetrehoz;

            Statistics.aranyValt += b.aranyNaponta;
            Statistics.faValt += b.faNaponta;
            Statistics.koValt += b.koNaponta;
            Statistics.kajaValt += b.kajaNaponta;
            Statistics.lakosokszamaValt += b.lakossagNaponta;

            return true;
        }else {
            return false;
        }
    }

    public static void lerombol(String kod){
        Building b = null;
        if (kod.equals("haz")) {
            b = haz;
        } else if (kod.equals("banya")) {
            b = banya;
        } else if (kod.equals("farm")) {
            b = farm;
        } else if (kod.equals("faKitermelo")) {
            b = faKitermelo;
        } else if (kod.equals("kut")) {
            b = kut;
        } else if (kod.equals("kikepzo")) {
            b = kikepzo;
        }
        Statistics.arany += b.aranyLebont;
        Statistics.fa += b.faLebont;
        Statistics.ko += b.koLebont;
        Statistics.kaja += b.kajaLebont;
        Statistics.lakosokszama += b.lakossagLebont;

        Statistics.aranyValt += -b.aranyNaponta;
        Statistics.faValt += -b.faNaponta;
        Statistics.koValt += -b.koNaponta;
        Statistics.kajaValt += -b.kajaNaponta;
        Statistics.lakosokszamaValt += -b.lakossagNaponta;
        Statistics.epuletekszama--; //1épület lebontása a tüz által
        System.out.println("épületszám "+Statistics.epuletekszama);
    }

}
