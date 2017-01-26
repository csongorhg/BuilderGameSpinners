package com.mygdx.game.Play;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

public class grassActor extends mapActor {

    private int posArrayX,posArrayY;


    public grassActor(int x, int y) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
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
        return "0";
    }

}
