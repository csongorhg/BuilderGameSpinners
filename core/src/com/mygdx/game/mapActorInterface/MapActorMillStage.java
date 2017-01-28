package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorMillStage extends MapActorStage {

    private Mill mapactor;

    public MapActorMillStage(MyGdxGame game, Mill m) {
        super(game, m);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.MEZO)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
