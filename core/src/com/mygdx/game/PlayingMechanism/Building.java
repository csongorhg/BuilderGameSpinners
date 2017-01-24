package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.22..
 */

public class Building {

    //létrehozásnál
    protected int faLetrehoz = 0;
    protected int koLetrehoz = 0;
    protected int aranyLetrehoz = 0;
    protected int kajaLetrehoz = 0;
    protected int lakossagLetrehoz = 0;

    //létrehozásnál
    protected int faLebont = 0;
    protected int koLebont = 0;
    protected int aranyLebont = 0;
    protected int kajaLebont = 0;
    protected int lakossagLebont = 0;

    //naponta
    protected int faNaponta = 0;
    protected int koNaponta = 0;
    protected int aranyNaponta = 0;
    protected int kajaNaponta = 0;
    protected int lakossagNaponta = 0;

    public Building(int faletrehoz,int koletrehoz,int aranyletrehoz,int kajaletrehoz,int lakossagletrehoz,int fanaponta,int konaponta,int aranynaponta,int kajanaponta,int lakossagnaponta,int falebont,int kolebont,int aranylebont,int kajalebont,int lakossaglebont) {
        faLetrehoz = faletrehoz;
        koLetrehoz = koletrehoz;
        aranyLetrehoz = aranyletrehoz;
        kajaLetrehoz = kajaletrehoz;
        lakossagLetrehoz = lakossagletrehoz;

        faNaponta = fanaponta;
        koNaponta = konaponta;
        aranyNaponta = aranynaponta;
        kajaNaponta = kajanaponta;
        lakossagNaponta = lakossagnaponta;

        faLebont = falebont;
        koLebont = kolebont;
        aranyLebont = aranylebont;
        kajaLebont = kajalebont;
        lakossagLebont = lakossaglebont;
    }

    public int[] getLebont() {
        int[] t = {faLebont,koLebont,aranyLebont,kajaLebont,lakossagLebont};
        return t;
    }

    public int[] getNaponta() {
        int[] t = {faNaponta,koNaponta,aranyNaponta,kajaNaponta,lakossagNaponta};
        return t;
    }

    public int[] getLetrehoz() {
        int[] t = {faLetrehoz,koLetrehoz,aranyLetrehoz,kajaLetrehoz,lakossagLetrehoz};
        return t;
    }
}
