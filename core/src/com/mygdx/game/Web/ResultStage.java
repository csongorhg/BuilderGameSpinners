package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

import java.util.HashMap;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ResultStage extends MyStage {
    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;
    private volatile boolean refresh = false;
    int nextScreen = 0;
    private boolean tamado_e, nyert;

    public ResultStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);
        httpCommand = new HttpCommand("http://193.224.143.135:9999"){
            @Override
            protected void failed(HttpErrors httpErrors) {

            }

            @Override
            protected void responsed() {

                HashMap<String, String> hm = getReceive();
                int m = Integer.valueOf(getReceive().get("message"));
                switch (m) {
                    case 60:
                        nextScreen = 60;
                        break;
                    case 62:
                        nextScreen = 62;
                        break;
                    case 42:
                        nextScreen = 42;
                        break;
                    case 404:
                        nextScreen = 404;
                        break;
                    case 101010:
                        nextScreen = 101010;
                        break;
                    case 51:
                        refresh = true;
                        break;
                }

            }
        };
        httpCommand.getSend().put("user",pref_user_pw.getString("user"));
        httpCommand.getSend().put("password",pref_user_pw.getString("password"));
        httpCommand.getSend().put("message", String.valueOf(MessageTypes.GETDATA.value));
        httpCommand.sendCommand();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (refresh){



        }

        if (nextScreen != 0) {
            switch (nextScreen) {
                case 404:
                case 60:
                case 62:
                case 42:
                case 101010:
                    game.setScreen(new LostConnectionScreen(game));
                    break;
            }
        }
    }

    @Override
    public void init() {
        addBackEventStackListener();

        tamado_e = true;
        nyert = true;

        if(tamado_e){
            if(nyert){
                MyLabel label = new MyLabel("Congratulations!\nYou won your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()-label.getHeight()-20);
                addActor(label);
                MyLabel rev = new MyLabel("Your revards: ",game.getLabelStyle(50));
                rev.setPosition(getViewport().getWorldWidth()/2-rev.getWidth()/2, getViewport().getWorldHeight()/2);
                addActor(rev);
                revards(rev.getY());
            }
            else{
                MyLabel label = new MyLabel("You have lost your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()/2);
                addActor(label);
            }
        }
        else{
            if(nyert){
                MyLabel label = new MyLabel("Congratulations!\nYou have defended your city!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()-label.getHeight()/2);
                addActor(label);
            }
            else{
                MyLabel label = new MyLabel("You have lost your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()-label.getHeight()-20);
                addActor(label);
                MyLabel rev = new MyLabel("You have lost: ",game.getLabelStyle(80));
                rev.setPosition(getViewport().getWorldWidth()/2-rev.getWidth()/2, getViewport().getWorldHeight()/2);
                addActor(rev);
                revards(rev.getY());
            }
        }

        MyButton button = new MyButton("Back to menu",game.getTextButtonStyle(100));
        button.setSize(button.getWidth()+button.getWidth()/4,button.getHeight());
        button.setPosition(getViewport().getWorldWidth()/2 - button.getWidth()/2, 10);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();
                game.backButtonStack.removeAllElements();
                game.setScreen(new MenuScreen(game));
            }
        });
        addActor(button);

    }

    private void revards(float posY){
        OneSpriteStaticActor foodSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        OneSpriteStaticActor woodSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        OneSpriteStaticActor stoneSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        OneSpriteStaticActor goldSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));

        MyLabel foodLabel = new MyLabel("1000",game.getLabelStyle(50));
        MyLabel woodLabel = new MyLabel("1000",game.getLabelStyle(50));
        MyLabel stoneLabel = new MyLabel("1000",game.getLabelStyle(50));
        MyLabel goldLabel = new MyLabel("1000",game.getLabelStyle(50));

        foodSprite.setSize(foodLabel.getHeight(),foodLabel.getHeight());
        woodSprite.setSize(foodSprite.getWidth(),foodSprite.getHeight());
        stoneSprite.setSize(foodSprite.getWidth(),foodSprite.getHeight());
        goldSprite.setSize(foodSprite.getWidth(),foodSprite.getHeight());

        stoneSprite.setPosition(getViewport().getWorldWidth()/2, posY-stoneSprite.getHeight());
        stoneLabel.setPosition(stoneSprite.getX()+stoneSprite.getWidth(),posY-stoneSprite.getHeight());

        goldSprite.setPosition(stoneLabel.getX() + stoneLabel.getWidth() + 20, posY-goldSprite.getHeight());
        goldLabel.setPosition(goldSprite.getX()+goldSprite.getWidth(),posY-goldSprite.getHeight());

        woodLabel.setPosition(stoneSprite.getX()-20-woodLabel.getWidth(), posY-woodSprite.getHeight());
        woodSprite.setPosition(woodLabel.getX()-foodSprite.getWidth(), posY-woodSprite.getHeight());

        foodLabel.setPosition(woodSprite.getX() - 20 - foodLabel.getWidth(),posY-foodSprite.getHeight());
        foodSprite.setPosition(foodLabel.getX()-foodSprite.getWidth(), posY-foodSprite.getHeight());

        addActor(foodLabel);
        addActor(woodLabel);
        addActor(stoneLabel);
        addActor(goldLabel);

        addActor(foodSprite);
        addActor(woodSprite);
        addActor(stoneSprite);
        addActor(goldSprite);
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
        pref_user_pw.flush();
        super.dispose();
    }
}
