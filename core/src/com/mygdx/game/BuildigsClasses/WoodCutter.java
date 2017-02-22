package com.mygdx.game.BuildigsClasses;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class WoodCutter extends mapActor {

    private OneSpriteStaticActor woodCutter;


    public WoodCutter(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? Assets.manager.get(Assets.WOOD_WORKER) : Assets.manager.get(Assets.FAVAGOSNOW)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

    }




    @Override
    public String toString() {
        return "11";
    }

    public Texture getWoodCutter(){return ((OneSpriteStaticActor)getActor()).getSprite().getTexture();}

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.WOOD_WORKER));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.FAVAGOSNOW));
    }

}
