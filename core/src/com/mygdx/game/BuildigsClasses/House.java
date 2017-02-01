package com.mygdx.game.BuildigsClasses;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class House extends mapActor {

    private OneSpriteStaticActor house;


    public House(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

        addActor(house = new OneSpriteStaticActor(Assets.manager.get(Assets.HOUSE)){
            @Override
            public void init() {
                super.init();
                setSize(w,h);
            }
        });

    }

    @Override
    public String toString() {
        return "14";
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
