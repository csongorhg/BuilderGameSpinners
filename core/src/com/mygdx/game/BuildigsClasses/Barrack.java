package com.mygdx.game.BuildigsClasses;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class Barrack extends mapActor {

    private OneSpriteStaticActor barrack;


    public Barrack(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.BARAKK) : Assets.manager.get(Assets.KIKEPZOSNOW)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

    }

    @Override
    public String toString() {
        return "15";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.BARRACK));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.KIKEPZOSNOW));
    }

}
