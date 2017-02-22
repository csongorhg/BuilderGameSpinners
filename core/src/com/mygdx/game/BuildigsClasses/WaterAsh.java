package com.mygdx.game.BuildigsClasses;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by Vince on 2017. 02. 19..
 */

public class WaterAsh extends mapActor {

    public WaterAsh(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.VIZ_HAMU) : Assets.manager.get(Assets.VIZ_HAMUSNOW)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

    }

    @Override
    public String toString() {
        return "21";
    }

    public Texture getWaterAsh(){return ((OneSpriteStaticActor)getActor()).getSprite().getTexture();}

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.VIZ_HAMU));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.VIZ_HAMUSNOW));
    }
}
