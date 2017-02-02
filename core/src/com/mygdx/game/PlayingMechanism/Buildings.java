package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.22..
 */

public class Buildings {

    public static Building haz = new Building(-2,-6,-3,0,-5,1,-2,0,0,1,2,0,1,0,0);
    public static Building banya =new Building(-2,0,-5,0,-4,0,0,0,3,2,2,0,2,0,0);
    public static Building farm =new Building(-1,0,-1,0,-2,0,2,0,0,0,1,0,0,0,0);
    public static Building faKitermelo =new Building(-2,0,0,0,-2,0,0,3,0,0,2,0,0,0,0);
    public static Building kut = new Building(0,0,-5,-5,0,0,0,0,0,0,0,0,2,2,0);
    public static Building kikepzo = new Building(-10,-15,-15,-15,-15,0,0,0,0,0,10,0,5,5,0);

    public static void epuletFejlesztes(String kod){
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
        epul(b);
    }

    private static void epul(Building b){
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
    }

}
