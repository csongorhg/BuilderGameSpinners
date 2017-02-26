package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ConnectionScreen extends MyScreen {

    private ConnectionStage connectionStage;


    private MyStage bgStage;

    public static final String PREFS = "USER_PW";
    private Preferences preferences = Gdx.app.getPreferences(PREFS);

    public ConnectionScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        bgStage.act();
        bgStage.draw();

        connectionStage.act(delta);
        connectionStage.draw();

    }

    @Override
    public void init() {
        r = 1;
        g = 0.5f;
        b = 0.3f;

        bgStage = new MyStage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), spriteBatch, game) {

            private OneSpriteStaticActor backGroudActor;

            @Override
            public void init() {
                r = 0;
                g = 0;
                b = 0;
                backGroudActor = new OneSpriteStaticActor(Assets.manager.get(Assets.WEB_BG));
                backGroudActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                addActor(backGroudActor);
            }

            @Override
            protected void resized() {

            }

            @Override
            public void act(float delta) {
                super.act(delta);
            }
        };

        connectionStage = new ConnectionStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
        Gdx.input.setInputProcessor(connectionStage);


    }

    @Override
    public void dispose() {
        preferences.flush();
        connectionStage.dispose();
        super.dispose();
    }

    @Override
    public void hide() {
        preferences.flush();
        super.hide();
    }
}
