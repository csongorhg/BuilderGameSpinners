package com.mygdx.game.BuildigsClasses;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class WoodCutter extends mapActor {

    public WoodCutter(int x, int y, grassActor g) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y);
        g.addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD_WORKER)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        });
    }
}
