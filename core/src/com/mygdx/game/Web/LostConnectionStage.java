package com.mygdx.game.Web;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

import java.util.Stack;

/**
 * Created by tanulo on 2017. 02. 23..
 */
public class LostConnectionStage extends MyStage {

    public LostConnectionStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        addBackEventStackListener();

        MyLabel label = new MyLabel("You have lost your connection!",game.getLabelStyle(100));
        label.setPosition(getViewport().getWorldWidth()/2 - label.getWidth()/2, getViewport().getWorldHeight()/2 - label.getHeight()/2);
        addActor(label);

        MyButton back = new MyButton("Back to Menu", game.getTextButtonStyle(100));
        back.setPosition(getViewport().getWorldWidth()/2 - back.getWidth()/2, 10);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                game.backButtonStack.removeAllElements();
                game.setScreen(new MenuScreen(game));
            }
        });
        addActor(back);


    }

    @Override
    public void addBackEventStackListener() {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    game.backButtonStack.removeAllElements();
                    game.setScreen(new MenuScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
