package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorFishDockStage extends MapActorStage {

    private FishDock mapactor;

    public MapActorFishDockStage(MyGdxGame game, FishDock f) {
        super(game, f);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.HALASZ)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
