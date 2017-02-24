package com.mygdx.game.PlayingMechanism;

import static com.mygdx.game.PlayingMechanism.Statistics.*;

/**
 * Created by Pocok on 2017.01.21..
 */

public class TimeStepper {

    public static float elteltido = -1;
    public static int elteltnap = 0;

    public static int barbarSzam = 0;

    public static int egynap = 10;
    public static int ev = 10; //tél és nyár = év/2
    public static boolean nyarvan = true;
    public static boolean vege = false;
    //public static boolean tuzvaltott = false;
    public static boolean tuzvan = false;
    public static boolean megtamadtak = false;

    private static final int max = 9999;

    public static void setDefaultTime() {
        elteltido = -1;
        elteltnap = 0;

        egynap = 10;
        ev = 10; //tél és nyár = év/2
        nyarvan = true;

        vege = false;
    }


    public static void STEP(float delta) {
        elteltido += delta;
        if (elteltnap * egynap < elteltido) {
            elteltnap++;
            System.out.println("Eltelt egy nap: " + elteltnap);
            if (elteltnap % (ev / 2) == ev / 2 - 1) {
                nyarvan = !nyarvan;
                System.out.println("Beköszöntött a " + (nyarvan ? "nyár" : "tél"));
            }
            nap();
        }
    }

    private static void nap() {
        arany += aranyValt;
        fa += faValt;
        kaja += kajaValt;
        ko += koValt;
        if (kaja > lakosokszamaValt) {
            kaja -= lakosokszamaValt;
            lakosokszama += lakosokszamaValt;
        } else {
            lakosokszama += kaja;
            kaja = 0;
        }

        //---------------------------------------

        if (legtobblakos < lakosokszama) {
            legtobblakos = lakosokszama;
        }

        if (!nyarvan) {
            if (fa > lakosokszama / 2) {
                fa -= lakosokszama / 2;
            } else {
                lakosokszama = (lakosokszama / 2 - (lakosokszama / 2 - fa)) * 2;
                fa = 0;
            }
        }

        if (kaja > Units.getLetszam()) {
            kaja -= Units.getLetszam();
        } else {
            Units.kivon(Units.getLetszam() - kaja);
            kaja = 0;
        }

        arany -= Units.getZsold();

        if (kaja > lakosokszama) {
            kaja -= lakosokszama;
        } else {
            lakosokszama = lakosokszama - (lakosokszama - kaja);
            kaja = 0;
        }

        if (epuletekszama > kutakszama * 5) {
            if (vel(4, 5) == 5 && elteltnap > 1) {
                tuzvan = true;
            }
        } else {
            tuzvan = false;
        }

        if(megtamadtak) megtamadtak = false;
        if(vel(10,30) == 20 && elteltnap > 30){ //30
            barbarSzam = vel(5,30);
            megtamadtak = true;
            System.out.println("Megtámadott "+barbarSzam+" barbár!");
            if(barbarSzam/2 < Units.getLetszam()){
                Units.kivon(barbarSzam/2);
            }else{
                int minusz =(barbarSzam/2-Units.getLetszam())*2;
                arany-=minusz;
                if(arany<0) arany = 0;
                fa-=minusz;
                if(fa < 0) fa = 0;
                ko-=minusz;
                if(ko < 0) ko = 0;
                kaja-=minusz;
                if(kaja< 0) kaja = 0;

                if((lakosokszama - minusz) <= 0) lakosokszama = 0;
                else lakosokszama-=minusz;

            }
        }

        //---------------------------------------

        if (lakosokszama <= 0) {
            System.out.println("Játék vége!");
            vege = true;
        }

        if (fa > max) fa = max;
        if (ko > max) ko = max;
        if (arany > max) arany = max;
        if (kaja > max) kaja = max;
        if (lakosokszama > max) lakosokszama = max;
    }


    public static int vel(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }
}
