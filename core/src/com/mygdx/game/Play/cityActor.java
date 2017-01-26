package com.mygdx.game.Play;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tanulo on 2017. 01. 23..
 */
public class cityActor extends mapActor {


    public cityActor(int x, int y, int count) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y);
        if(count == 4) {
            addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_HALL)) {
                @Override
                public void init() {
                    super.init();
                    setSize(256, 256);
                    setPosition(-128, 0);
                }
            });
        }
    }

}
