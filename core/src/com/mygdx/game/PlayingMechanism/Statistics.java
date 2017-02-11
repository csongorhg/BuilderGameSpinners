package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.21..
 */

public class Statistics {

    public static int legtobblakos = 10;

    public static int lakosokszama = 10;
    public static int fa = 4000;
    public static int ko = 4000;
    public static int arany = 2000;
    public static int kaja = 8000;

    public static int lakosokszamaValt = 0;
    public static int faValt = 0;
    public static int koValt = 0;
    public static int aranyValt = 0;
    public static int kajaValt = 0;

    public static int epuletekszama = 1;
    public static int kutakszama = 1;
    public static int katonakszama = 0;

    public static void setDefaultStatistics() {
        legtobblakos = 10;

        lakosokszama = 10;
        fa = 4000;
        ko = 4000;
        arany = 2000;
        kaja = 8000;

        lakosokszamaValt = 0;
        faValt = 0;
        koValt = 0;
        aranyValt = 0;
        kajaValt = 0;

        epuletekszama = 1;
        kutakszama = 1;
    }

    public static int getlakosValt() {
        if(kaja < Units.getLetszam()+lakosokszama){
            int i = (Units.getLetszam()+lakosokszama)-kaja;
            if(i>lakosokszama) return lakosokszama;
            else return i;
        }
        else return 0;
    }
}
