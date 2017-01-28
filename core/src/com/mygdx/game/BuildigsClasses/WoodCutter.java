package com.mygdx.game.BuildigsClasses;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class WoodCutter extends mapActor {

    private OneSpriteStaticActor woodCutter;


    public WoodCutter(int x, int y, final float w, final float h) {
        super(new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK)){
            @Override
            public void init() {
                super.init();
                setSize(128,128);
            }
        }, x, y, w, h);

        addActor(woodCutter = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD_WORKER)){
            @Override
            public void init() {
                super.init();
                setSize(w,h);
            }
        });

        woodCutter.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });

    }




    @Override
    public String toString() {
        return "11";
    }

}
