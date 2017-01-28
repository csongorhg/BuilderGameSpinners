package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorBarrackStage extends MapActorStage {

    private Barrack mapactor;

    public MapActorBarrackStage(MyGdxGame game, Barrack b) {
        super(game, b);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.BARAKK)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
