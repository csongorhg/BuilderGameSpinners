package com.mygdx.game.Play;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

public class grassActor extends mapActor {


    public grassActor(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
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

}
