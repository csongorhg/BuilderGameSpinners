package com.mygdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {

    protected MenuStage menuStage;
    private MyStage bgStage;

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //háttér
        bgStage.draw();
        bgStage.act(delta);
        //háttér

        menuStage.act(delta);
        menuStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void dispose() {
        menuStage.dispose();
        super.dispose();
    }

    @Override
    public void init() {
        /*OrthographicCamera orthographicCamera = new OrthographicCamera(1280, 720);
        orthographicCamera.setToOrtho(true);*/
        menuStage = new MenuStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), spriteBatch, game);
        Gdx.input.setInputProcessor(menuStage);



        //háttér
        bgStage = new MyStage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), spriteBatch, game) {

            public OneSpriteStaticActor getBackGroudActor() {
                return backGroudActor;
            }

            private OneSpriteStaticActor backGroudActor;

            @Override
            public void init() {
                r = 1;
                g = 1;
                b = 1;
                backGroudActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND));
                backGroudActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                setCameraZoomXY(backGroudActor.getWidth() / 2, backGroudActor.getHeight() / 2, 40);
                setCameraMoveToXY(backGroudActor.getWidth() / 2
                        , backGroudActor.getHeight() / 2, 1, 80);
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
        //háttér vége
    }

}
