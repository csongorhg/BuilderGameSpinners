package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.21..
 */

public class Statistics {

    public static int legtobblakos = 10;

    public static int lakosokszama = 10;
    public static int fa = 40;
    public static int ko = 40;
    public static int arany = 40;
    public static int kaja = 80;

    public static int lakosokszamaValt = 0;
    public static int faValt = 0;
    public static int koValt = 0;
    public static int aranyValt = 0;
    public static int kajaValt = 0;

    public static int epuletekszama = 1;
    public static int kutakszama = 1;
    public static int katonakszama = 5;

    public static void setDefaultStatistics() {
        legtobblakos = 10;

        lakosokszama = 10;
        fa = 40;
        ko = 40;
        arany = 40;
        kaja = 80;

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
