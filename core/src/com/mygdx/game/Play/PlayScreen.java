package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Menu.MenuStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayScreen extends MyScreen{

    protected PlayStage playStage;
    private MyStage bgStage;

    public static final String PREFS = "MAP";
    private Preferences preferences = Gdx.app.getPreferences(PREFS);

    public PlayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //háttér
        bgStage.draw();
        bgStage.act(delta);
        //háttér

        playStage.act(delta);
        playStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void dispose() {
        playStage.dispose();
        preferences.flush();
        super.dispose();
    }

    @Override
    public void hide() {
        preferences.flush();
        super.hide();
    }

    @Override
    public void init() {
        /*OrthographicCamera orthographicCamera = new OrthographicCamera(1280, 720);
        orthographicCamera.setToOrtho(true);*/
        playStage = new PlayStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), spriteBatch, game);
        //Gdx.input.setInputProcessor(playStage);



        //háttér
        bgStage = new MyStage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), spriteBatch, game) {

            public OneSpriteStaticActor getBackGroudActor() {
                return backGroudActor;
            }

            private OneSpriteStaticActor backGroudActor;

            @Override
            public void init() {
                r = 0;
                g = 0;
                b = 0;
            }

            @Override
            protected void resized() {

            }

            @Override
            public void act(float delta) {
                super.act(delta);
            }
        };
        //háttér vége
    }

}
