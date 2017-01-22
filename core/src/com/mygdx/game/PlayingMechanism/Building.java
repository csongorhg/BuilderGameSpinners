package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.22..
 */

public class Building {

    //létrehozásnál
    private int fa = 25;
    private int ko = 25;
    private int arany = 25;
    private int kaja = 25;

    //naponta
    private int faNaponta = 0;
    private int koNaponta = 0;
    private int aranyNaponta = 0;
    private int kajaNaponta = 0;

    public Building(int fa,int ko,int arany,int kaja,int fanaponta,int konaponta,int aranynaponta,int kajanaponta) {
        this.fa = fa;
        this.ko = ko;
        this.arany = arany;
        this.kaja = kaja;

        faNaponta = fanaponta;
        koNaponta = konaponta;
        aranyNaponta = aranynaponta;
        kajaNaponta = kajanaponta;
    }
}
