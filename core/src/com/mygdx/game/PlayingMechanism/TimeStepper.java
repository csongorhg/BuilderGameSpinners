package com.mygdx.game.PlayingMechanism;

import static com.mygdx.game.PlayingMechanism.Statistics.*;

/**
 * Created by Pocok on 2017.01.21..
 */

public class TimeStepper {

    public static float elteltido = -1;
    public static int elteltnap = 0;

    public static int egynap = 10;
    public static int ev = 60; //tél és nyár = év/2
    public static boolean nyarvan = true;

    public static void STEP(float delta){
        elteltido+=delta;
        if(elteltnap*egynap < elteltido){
            elteltnap++;
            nap();
            System.out.println(elteltnap+". nap");
            if(elteltnap%(ev/2) == ev/2-1){
                nyarvan = !nyarvan;
                System.out.println("Beköszöntött a " + (nyarvan ? "nyár" : "tél"));
            }
        }
    }

    private static void nap() {
        arany += aranyValt;
        fa += faValt;
        kaja += kajaValt;
        ko += koValt;
        lakosokszama += lakosokszamaValt;

        //---------------------------------------



        //---------------------------------------
        if(lakosokszama <= 0){
            System.out.println("Játék vége!");
        }
    }


    public static int vel(int a,int b){return (int)(Math.random()*(b-a+1)+a);}
}
