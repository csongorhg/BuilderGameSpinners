package com.mygdx.game.Play;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

public class grassActor extends mapActor {


    public grassActor(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.GRASS_BLOCK) : Assets.manager.get(Assets.FUSNOW_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(w,h);
            }
        }, x, y, w, h);
    }



    @Override
    public String toString() {
        return "0";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.GRASS_BLOCK));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.FUSNOW_BLOCK));
    }
}
