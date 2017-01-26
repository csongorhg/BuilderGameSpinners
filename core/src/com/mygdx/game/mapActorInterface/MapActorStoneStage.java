package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.stoneActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorStoneStage extends MapActorStage {




    public MapActorStoneStage(MyGdxGame game, stoneActor g) {
        super(game, g);
        getActorGroup().addActor(new MyLabel("Stone",game.getLabelStyle()){
            @Override
            public void init() {
                super.init();
                setPosition(50,50);
            }
        });
        getActorGroup().addActor(new OneSpriteStaticActor(g.getStone()){
            @Override
            public void init() {
                super.init();
                setSize(140, 140);
                setPosition(70, getViewport().getWorldHeight()-70-getWidth());
            }
        });
    }
}
