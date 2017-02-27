package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

import java.util.HashMap;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ResultStage extends MyStage {
    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;
    private boolean refresh = false;
    int nextScreen = 0;

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
                    case 50:
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
