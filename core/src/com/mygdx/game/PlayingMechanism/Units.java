package com.mygdx.game.PlayingMechanism;

/**
 * Created by tanulo on 2017. 02. 01..
 */
public class Units {

    int kardosLetszam = 0;
    int ijjaszLetszam = 0;
    int lovasLetszam = 0;
    int agyusLetszam = 0;

    int kardosEro = 1;
    int ijjaszEro = 2;
    int lovasEro = 3;
    int agyusEro = 4;


    //lakos,fa,ko,arany
    int[] kardosKoltseg = {1,1,1,2};
    int[] ijjaszKoltseg = {1,3,1,3};
    int[] lovasKoltseg = {2,1,1,4};
    int[] agyusKoltseg = {3,2,3,5};

    public int getLetszam(){
        int i = 0;
        i+=kardosLetszam;
        i+=ijjaszLetszam;
        i+=lovasLetszam*lovasKoltseg[0];
        i+=agyusLetszam*agyusKoltseg[0];
        return i;
    }
    
    public int harc(int[] egy,int[] ketto){
        int csapategy = egy[0]*kardosEro + egy[1]*ijjaszEro + egy[2]*lovasEro + egy[3]*agyusEro;
        int csapatketto = ketto[0]*kardosEro + ketto[1]*ijjaszEro + ketto[2]*lovasEro + ketto[3]*agyusEro;

        return csapategy-csapatketto;
    }

}
