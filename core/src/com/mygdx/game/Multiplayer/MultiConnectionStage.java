package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Web.MessageTypes;

/**
 * Created by tuskeb on 2017. 02. 22..
 */

public class MultiConnectionStage extends MyStage {

    HttpCommand httpCommand = null;

    public MultiConnectionStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), new SpriteBatch(), game);
        addActor(new MyButton("Connect", game.getTextButtonStyle(100)){
            @Override
            public void init() {
                super.init();
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
            /*            httpCommand = new HttpCommand("localhost");
                        httpCommand.getSend().put("user","android");
                        httpCommand.getSend().put("password","12345");
                        httpCommand.getSend().put("message", String.valueOf(MessageTypes.CONNECT.value));*/
                    }
                });
            }

        });
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
