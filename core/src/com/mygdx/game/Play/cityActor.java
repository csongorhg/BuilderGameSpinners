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
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

        if(count == 4) {
            addActor(cityhall = new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_HALL)) {
                @Override
                public void init() {
                    super.init();
                    setSize(w, h);
                    setPosition(-128, 0);
                }

            });
        }
    }

    public Texture getCityHall(){return cityhall.getTexture();}


    @Override
    public String toString() {
        return "9";
    }
}
