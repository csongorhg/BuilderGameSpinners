package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.21..
 */

public class Statistics {

    public static int legtobblakos = 5;

    public static int lakosokszama = 100;
    public static int fa = 200;
    public static int ko = 200;
    public static int arany = 100;
    public static int kaja = 400;

    public static int lakosokszamaValt = 0;
    public static int faValt = 0;
    public static int koValt = 0;
    public static int aranyValt = 0;
    public static int kajaValt = 0;

    public static int epuletekszama = 0;
    public static int kutakszama = 0;
    public static int katonakszama = 0;

    public static void setDefaultStatistics() {
        legtobblakos = 5;

        lakosokszama = 100;
        fa = 200;
        ko = 200;
        arany = 100;
        kaja = 400;

        lakosokszamaValt = 0;
        faValt = 0;
        koValt = 0;
        aranyValt = 0;
        kajaValt = 0;

        epuletekszama = 0;
        kutakszama = 0;
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
