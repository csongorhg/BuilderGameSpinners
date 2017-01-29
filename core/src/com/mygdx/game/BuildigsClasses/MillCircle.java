package com.mygdx.game.BuildigsClasses;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by mordes on 2017.01.29..
 */

public class MillCircle extends mapActor {

    private OneSpriteStaticActor millCircle;

    public MillCircle(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

        addActor(millCircle = new OneSpriteStaticActor(Assets.manager.get(Assets.MILLMEZO)){
            @Override
            public void init() {
                super.init();
                setSize(w,h);
            }
        });

    }

    @Override
    public String toString() {
        return "19";
    }

}
