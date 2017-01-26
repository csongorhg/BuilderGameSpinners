package com.mygdx.game.mapActorInterface;

import com.mygdx.game.MyBaseClasses.MyLabel;
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
        getActorGroup().addActor(new MyLabel("Tree",game.getLabelStyle()){
            @Override
            public void init() {
                super.init();
                setPosition(50,50);
            }
        });
        System.out.println(g.getPosArrayX() + " - " + g.getPosArrayY());
    }

}
