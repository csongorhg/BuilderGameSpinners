package com.mygdx.game.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.BluetoothConnectedStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BTGameStage extends BluetoothConnectedStage {

    public BTGameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    @Override
    public void init() {
        Gdx.input.setInputProcessor(this);
        addBackEventStackListener();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //return super.touchDown(event, x, y, pointer, button);
                addStar(x,y);
                Gdx.app.log("BTM", "Send: " + x + ";" + y);
                sendMessage(x + ";" + y);
                return true;
            }
        });
    }

    protected void addStar(final float x, final float y){
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_HALL)){
            @Override
            public void init() {
                super.init();
                setPosition(x,y);
                setSize(20,20);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        String s;
        if ((s = getMessage()) != null){
            Gdx.app.log("BTM","Receive: " + s);
            String[] v = s.split(";");
            if (v.length==2) {
                addStar(Float.valueOf(v[0]), Float.valueOf(v[1]));
            }
        }
    }
}
