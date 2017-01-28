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
    public static boolean vege = false;



    public static void STEP(float delta){
        elteltido+=delta;
        if(elteltnap*egynap < elteltido){
            elteltnap++;
            if(elteltnap%(ev/2) == ev/2-1){
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
        lakosokszama += lakosokszamaValt;

        //---------------------------------------

        if(legtobblakos < lakosokszama){
            legtobblakos = lakosokszama;
        }

        if(!nyarvan){
            if(fa > lakosokszama/2){
                fa-=lakosokszama/2;
            }else{
                lakosokszama = (lakosokszama/2-(lakosokszama/2-fa))*2;
                fa = 0;
            }

            if(fa > katonakszama/2){
                fa-=katonakszama/2;
            }else{
                katonakszama = (katonakszama/2-(katonakszama/2-fa))*2;
                fa = 0;
            }
        }

        if(kaja > lakosokszama){
            kaja-=lakosokszama;
        }else{
            lakosokszama = lakosokszama-(lakosokszama-kaja);
            kaja = 0;
        }

        if(kaja > katonakszama){
            kaja-=katonakszama;
        }else{
            katonakszama = katonakszama-(katonakszama-kaja);
            kaja = 0;
        }

        if(epuletekszama/5.0 > kutakszama){
            if(vel(0,30) == 5){
                System.out.println("Tűz van!");
            }
        }

        if(vel(0,30) == 10 && elteltnap > 30){
            int egysegek = vel(5,30);
            System.out.println("Megtámadott "+egysegek+" barbár!");
            if(egysegek/2 < katonakszama){
                katonakszama-=egysegek/2;
            }else{
                int minusz =(egysegek/2-katonakszama)*2;
                arany-=minusz;
                if(arany<0) arany = 0;
                fa-=minusz;
                if(fa < 0) fa = 0;
                ko-=minusz;
                if(ko < 0) ko = 0;
                kaja-=minusz;
                if(kaja< 0) kaja = 0;
                lakosokszama-=minusz;
                if(lakosokszama < 0) lakosokszama = 0;
            }
        }

        //---------------------------------------

        if(lakosokszama <= 0){
            System.out.println("Játék vége!");
            vege = true;
        }
    }


    public static int vel(int a,int b){return (int)(Math.random()*(b-a+1)+a);}
}
