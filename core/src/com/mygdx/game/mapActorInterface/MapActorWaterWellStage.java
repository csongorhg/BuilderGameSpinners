package com.mygdx.game.mapActorInterface;

import com.mygdx.game.BuildigsClasses.WaterWell;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorWaterWellStage extends MapActorStage {

    private WaterWell mapactor;

    public MapActorWaterWellStage(MyGdxGame game, WaterWell w) {
        super(game, w);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.KUT)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
    }
}
