package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.grassActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorGrassStage extends MapActorGlobalStage {


    public MapActorGrassStage(MyGdxGame game, grassActor g) {
        super(game, g);
        getActorGroup().addActor(new MyLabel("Grass",game.getLabelStyle()){
            @Override
            public void init() {
                super.init();
                setPosition(50,50);
            }
        });
    }
}
