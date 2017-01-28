package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorStoneWorkerStage extends MapActorStage {

    private StoneWorker mapactor;

    public MapActorStoneWorkerStage(MyGdxGame game, StoneWorker s) {
        super(game, s);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.BANYA)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
