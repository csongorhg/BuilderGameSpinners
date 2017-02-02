package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.WorldGenerate.Generator;

/**
 * Created by tanulo on 2017. 01. 23..
 */
public class stoneActor extends mapActor {

    private OneSpriteStaticActor stone;


    public stoneActor(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(TimeStepper.nyarvan ? (Generator.vel(0,1) == 1?Assets.manager.get(Assets.STONE_BLOCK):Assets.manager.get(Assets.STONE2_BLOCK)) : (Generator.vel(0,1) == 1?Assets.manager.get(Assets.KO1_SNOW):Assets.manager.get(Assets.KO2_SNOW))){
            @Override
            public void init() {
                super.init();
                setSize(w, h);
            }
        }, x, y, w, h);

    }

    public Texture getStone(){return ((OneSpriteStaticActor)getActor()).getSprite().getTexture();}


    @Override
    public String toString() {
        return "3";
    }

    @Override
    public void setSummer() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Generator.vel(0,1) == 1?Assets.manager.get(Assets.STONE_BLOCK):Assets.manager.get(Assets.STONE2_BLOCK));
    }

    @Override
    public void setWinter() {
        ((OneSpriteStaticActor)getActor()).getSprite().setTexture(Generator.vel(0,1) == 1?Assets.manager.get(Assets.KO1_SNOW):Assets.manager.get(Assets.KO2_SNOW));
    }

}
