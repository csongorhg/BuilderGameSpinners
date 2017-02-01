package com.mygdx.game.Play;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tanulo on 2017. 01. 23..
 */
public class waterActor extends mapActor {

    public waterActor(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(w,h);
            }
        }, x, y, w, h);
    }


    @Override
    public String toString() {
        return "1";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.WATER_BLOCK));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.VIZSNOW_BLOCK));
    }

}
