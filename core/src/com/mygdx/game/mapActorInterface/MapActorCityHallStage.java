package com.mygdx.game.mapActorInterface;

import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.cityActor;

/**
 * Created by tanulo on 2017. 01. 26..
 */
public class MapActorCityHallStage extends MapActorStage {

    private cityActor mapactor;

    public MapActorCityHallStage(MyGdxGame game, cityActor g) {
        super(game, g);
        getActorGroup().addActor(new MyLabel("Tree",game.getLabelStyle()){
            @Override
            public void init() {
                super.init();
                setPosition(50,50);
            }
        });
        getActorGroup().addActor(new OneSpriteStaticActor(g.getCityHall()){
            @Override
            public void init() {
                super.init();
                setSize(140, 140);
                setPosition(70, getViewport().getWorldHeight()-70-getWidth());
            }
        });
    }
}
