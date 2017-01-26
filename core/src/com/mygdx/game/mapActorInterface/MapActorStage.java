package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorStage extends MyStage {

    protected mapActor mapactor;
    protected Actor selectActor = null;


    public Group getActorGroup() {
        return actorGroup;
    }

    private Group actorGroup;

    public MapActorStage(MyGdxGame game, mapActor g) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), new SpriteBatch(), game);
        if (g != null) {
            mapactor = g;
            addSelectActor(g.getStage());
            System.out.println(g.getPosArrayX() + " - " + g.getPosArrayY());
        }

        addActor(actorGroup = new Group());
        actorGroup.setPosition(getViewport().getWorldWidth()-280, 0);
        actorGroup.setSize(280, getViewport().getWorldHeight());


        OneSpriteStaticActor oneSpriteStaticActor;
        actorGroup.addActor(oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FAHATTER)));
        oneSpriteStaticActor.setPosition(0,0);
        oneSpriteStaticActor.setSize(actorGroup.getWidth(), actorGroup.getHeight());


    }

    @Override
    public void init() {

    }


    public void addSelectActor(Stage stage){
        this.selectActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)){
            @Override
            public void init() {
                super.init();
                setPosition(mapactor.getX(), mapactor.getY());
                setSize(128,128);
                setTouchable(Touchable.disabled);
            }
        };
        stage.addActor(selectActor);
    }

    @Override
    public void dispose() {
        if (selectActor !=null){
            selectActor.getStage().getActors().removeValue(selectActor, true);
        }
        super.dispose();
    }

}
