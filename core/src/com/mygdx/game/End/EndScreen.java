package com.mygdx.game.End;

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
public class EndScreen extends MyScreen {
    protected EndStage endStage;
    private MyStage bgStage;

    public EndScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        bgStage.act();
        bgStage.draw();

        endStage.act(delta);
        endStage.draw();


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
                backGroudActor = new OneSpriteStaticActor(Assets.manager.get(Assets.WALL));
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

        endStage = new EndStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
        Gdx.input.setInputProcessor(endStage);

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
