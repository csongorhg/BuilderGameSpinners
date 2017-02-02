package com.mygdx.game.BuildigsClasses;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by mordes on 2017.01.29..
 */

public class MillCircle extends mapActor {

    private OneSpriteStaticActor millCircle;

    public MillCircle(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.MILLMEZO) : Assets.manager.get(Assets.MEZOSNOW)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

    }

    @Override
    public String toString() {
        return "19";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.MILLMEZO));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.MEZOSNOW));
    }

}
