package com.mygdx.game.PlayingMechanism;

/**
 * Created by tanulo on 2017. 02. 01..
 */
public class Units {

    static int kardosLetszam = 0;
    static int ijjaszLetszam = 0;
    static int lovasLetszam = 0;
    static int agyusLetszam = 0;

    static int kardosEro = 1;
    static int ijjaszEro = 2;
    static int lovasEro = 3;
    static int agyusEro = 4;


    //lakos,fa,ko,arany
    static int[] kardosKoltseg = {1,1,1,2};
    static int[] ijjaszKoltseg = {1,3,1,3};
    static int[] lovasKoltseg = {2,1,1,4};
    static int[] agyusKoltseg = {3,2,3,5};

    static public int getLetszam(){
        int i = 0;
        i+=kardosLetszam;
        i+=ijjaszLetszam;
        i+=lovasLetszam*lovasKoltseg[0];
        i+=agyusLetszam*agyusKoltseg[0];
        return i;
    }
    
    static public int harc(int[] egy,int[] ketto){
        int csapategy = egy[0]*kardosEro + egy[1]*ijjaszEro + egy[2]*lovasEro + egy[3]*agyusEro;
        int csapatketto = ketto[0]*kardosEro + ketto[1]*ijjaszEro + ketto[2]*lovasEro + ketto[3]*agyusEro;

        return csapategy-csapatketto;
    }

    static public void kivon(int egyseg){
        boolean[] t;
        for (int i = 0; i < egyseg; i++) {
            t = new boolean[]{kardosLetszam > 0, ijjaszLetszam > 0, lovasLetszam > 0, agyusLetszam > 0};
            if(t[0] || t[1] || t[2] || t[3]){
                int a = TimeStepper.vel(0,3);
                levon(a);
            }
        }
    }

    static void levon(int a){
        boolean sikerult = false;
        while(!sikerult){
            if(a == 0) {
                if(kardosLetszam>0){
                    kardosLetszam--;
                    sikerult = true;
                }
            }else if(a == 1){
                if(ijjaszLetszam>0){
                    ijjaszLetszam--;
                    sikerult = true;
                }
            }else if(a == 2){
                if(lovasLetszam>0){
                    lovasLetszam--;
                    sikerult = true;
                }
            }else if(a == 3){
                if(agyusLetszam>0){
                    agyusLetszam--;
                    sikerult = true;
                }
            }
            a++;
            if(a>3) a=0;
        }
    }

}

