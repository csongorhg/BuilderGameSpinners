package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tanulo on 2017. 01. 23..
 */
public class cityActor extends mapActor {

    private OneSpriteStaticActor cityhall;


    public cityActor(int x, int y, int count, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_HALL)){
            @Override
            public void init() {
                super.init();
                setSize(256,256);
                setPosition(-128,0);
            }
        }, x, y, w, h);

    }

    public Texture getCityHall(){return ((OneSpriteStaticActor)getActor()).getSprite().getTexture();}


    @Override
    public String toString() {
        return "9";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.CITY_HALL));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Assets.manager.get(Assets.VARSNOW));
    }
}
