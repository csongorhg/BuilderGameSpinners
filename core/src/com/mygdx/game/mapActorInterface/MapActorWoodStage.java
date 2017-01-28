package com.mygdx.game.mapActorInterface;

import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.woodActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorWoodStage extends MapActorStage {
    private grassActor mapactor;

    public MapActorWoodStage(MyGdxGame game, woodActor g) {
        super(game, g);
        getActorGroup().addActor(new OneSpriteStaticActor(g.getWood()){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }

}
