package com.mygdx.game.Play;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tanulo on 2017. 01. 23..
 */
public class waterActor extends mapActor {

    private int posArrayX,posArrayY;


    public waterActor(int x, int y) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y);
        posArrayX = x;
        posArrayY = y;
    }

    public int getPosArrayX() {
        return posArrayX;
    }

    public int getPosArrayY() {
        return posArrayY;
    }

    @Override
    public String toString() {
        return "1";
    }

}
