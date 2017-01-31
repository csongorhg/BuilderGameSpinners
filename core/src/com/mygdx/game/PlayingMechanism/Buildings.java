package com.mygdx.game.PlayingMechanism;

/**
 * Created by Pocok on 2017.01.22..
 */

public class Buildings {

    public static Building[] haz = {new Building(-2,-6,-3,0,-5,1,-2,0,0,1,2,0,1,0,0),new Building(-5,-12,-10,-6,-8,3,-4,0,0,3,5,0,3,1,0),new Building(-10,-18,-25,-10,-10,5,-8,0,0,5,10,0,10,3,0)};
    public static Building[] banya ={new Building(-2,0,-5,0,-4,0,0,0,3,2,2,0,2,0,0),new Building(-5,-2,-10,-5,-6,0,-1,0,10,4,5,0,6,2,0),new Building(-12,-4,-20,-10,-12,0,-2,0,25,6,12,0,10,5,0)};
    public static Building[] farm ={new Building(-1,0,-1,0,-2,0,2,0,0,0,1,0,0,0,0),new Building(-2,-4,-4,-1,-4,0,8,0,0,0,2,0,2,0,0),new Building(-3,-8,-8,-3,-6,0,16,0,0,0,3,0,4,1,0)};
    public static Building[] faKitermelo ={new Building(-2,0,0,0,-2,0,0,3,0,0,2,0,0,0,0),new Building(-6,-2,-8,-4,-8,0,-1,10,0,-1,6,0,6,1,0),new Building(-12,-4,-16,-8,-10,0,-2,25,0,-2,12,0,8,4,0)};
    public static Building[] kut = {new Building(0,0,-5,-5,0,0,0,0,0,0,0,0,2,2,0)};
    public static Building[] kikepzo = {new Building(-10,-15,-15,-15,-15,0,0,0,0,0,10,0,5,5,0)};

    public static void epuletFejlesztes(String kod,int szint,boolean szint_no){
        Building[] b = null;
        if (kod.equals("haz")) {
            b = haz;
        } else if (kod.equals("banya")) {
            b = banya;
        } else if (kod.equals("farm")) {
            b = farm;
        } else if (kod.equals("faKitermelo")) {
            b = faKitermelo;
        } else if (kod.equals("kut")) {
            b = kut;
        } else if (kod.equals("kikepzo")) {
            b = kikepzo;
        }
        epul(szint_no,b,szint);
    }

    private static void epul(boolean no,Building[] b,int szint){
        if((szint == 3 && no) || (szint == 0 && !no)) System.out.println("HIBA! 1");
        else if((szint == 1 && !no)){
            Statistics.arany += b[szint-1].aranyLebont;
            Statistics.fa += b[szint-1].faLebont;
            Statistics.ko += b[szint-1].koLebont;
            Statistics.kaja += b[szint-1].kajaLebont;
            Statistics.lakosokszama += b[szint-1].lakossagLebont;

            Statistics.aranyValt += -b[szint-1].aranyNaponta;
            Statistics.faValt += -b[szint-1].faNaponta;
            Statistics.koValt += -b[szint-1].koNaponta;
            Statistics.kajaValt += -b[szint-1].kajaNaponta;
            Statistics.lakosokszamaValt += -b[szint-1].lakossagNaponta;
        }else if(szint == 0 && no){
            Statistics.arany += b[0].aranyLetrehoz;
            Statistics.fa += b[0].faLetrehoz;
            Statistics.ko += b[0].koLetrehoz;
            Statistics.kaja += b[0].kajaLetrehoz;
            Statistics.lakosokszama += b[0].lakossagLetrehoz;

            Statistics.aranyValt += b[0].aranyNaponta;
            Statistics.faValt += b[0].faNaponta;
            Statistics.koValt += b[0].koNaponta;
            Statistics.kajaValt += b[0].kajaNaponta;
            Statistics.lakosokszamaValt += b[0].lakossagNaponta;
        }
        else{
            if(no){
                Statistics.arany += b[szint-1].aranyLebont + b[szint].aranyLetrehoz;
                Statistics.fa += b[szint-1].faLebont + b[szint].faLetrehoz;
                Statistics.ko += b[szint-1].koLebont + b[szint].koLetrehoz;
                Statistics.kaja += b[szint-1].kajaLebont + b[szint].kajaLetrehoz;
                Statistics.lakosokszama += b[szint-1].lakossagLebont + b[szint].lakossagLetrehoz;

                Statistics.aranyValt += -b[szint-1].aranyNaponta + b[szint].aranyNaponta;
                Statistics.faValt += -b[szint-1].faNaponta + b[szint].faNaponta;
                Statistics.koValt += -b[szint-1].koNaponta + b[szint].koNaponta;
                Statistics.kajaValt += -b[szint-1].kajaNaponta + b[szint].kajaNaponta;
                Statistics.lakosokszamaValt += -b[szint-1].lakossagNaponta + b[szint].lakossagNaponta;
            }else{
                Statistics.arany += b[szint-1].aranyLebont + b[szint-2].aranyLetrehoz;
                Statistics.fa += b[szint-1].faLebont + b[szint-2].faLetrehoz;
                Statistics.ko += b[szint-1].koLebont + b[szint-2].koLetrehoz;
                Statistics.kaja += b[szint-1].kajaLebont + b[szint-2].kajaLetrehoz;
                Statistics.lakosokszama += b[szint-1].lakossagLebont + b[szint-2].lakossagLetrehoz;

                Statistics.aranyValt += -b[szint-1].aranyNaponta + b[szint-2].aranyNaponta;
                Statistics.faValt += -b[szint-1].faNaponta + b[szint-2].faNaponta;
                Statistics.koValt += -b[szint-1].koNaponta + b[szint-2].koNaponta;
                Statistics.kajaValt += -b[szint-1].kajaNaponta + b[szint-2].kajaNaponta;
                Statistics.lakosokszamaValt += -b[szint-1].lakossagNaponta + b[szint-2].lakossagNaponta;
            }
        }
    }

    public static void lerombol(String kod,int szint){
        Building[] b = null;
        if (kod.equals("haz")) {
            b = haz;
        } else if (kod.equals("banya")) {
            b = banya;
        } else if (kod.equals("farm")) {
            b = farm;
        } else if (kod.equals("faKitermelo")) {
            b = faKitermelo;
        } else if (kod.equals("kut")) {
            b = kut;
        } else if (kod.equals("kikepzo")) {
            b = kikepzo;
        }
        Statistics.arany += b[szint-1].aranyLebont;
        Statistics.fa += b[szint-1].faLebont;
        Statistics.ko += b[szint-1].koLebont;
        Statistics.kaja += b[szint-1].kajaLebont;
        Statistics.lakosokszama += b[szint-1].lakossagLebont;

        Statistics.aranyValt += -b[szint-1].aranyNaponta;
        Statistics.faValt += -b[szint-1].faNaponta;
        Statistics.koValt += -b[szint-1].koNaponta;
        Statistics.kajaValt += -b[szint-1].kajaNaponta;
        Statistics.lakosokszamaValt += -b[szint-1].lakossagNaponta;
    }

}
