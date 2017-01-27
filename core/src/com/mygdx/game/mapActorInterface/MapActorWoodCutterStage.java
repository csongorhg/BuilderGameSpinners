package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class MapActorWoodCutterStage extends MapActorStage {

    private WoodCutter mapactor;

    public MapActorWoodCutterStage(MyGdxGame game, WoodCutter g) {
        super(game, g);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.FAVAGO)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
