package com.mygdx.game.BuildigsClasses;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by Vince on 2017. 02. 02..
 */

public class Hamu extends mapActor {

    public Hamu(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.HAMU) : Assets.manager.get(Assets.HAMUSNOW)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

    }

    @Override
    public String toString() {
        return "20";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.HAMU));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.HAMUSNOW));
    }
}
