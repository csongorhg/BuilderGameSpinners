package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTimerActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class BattleListStage extends MyStage {


private MyTimerActor myTimerActor;

    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;

    public BattleListStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);
        httpCommand = new HttpCommand("http://193.224.143.135:9999"){
            @Override
            protected void failed(HttpErrors httpErrors) {

            }

            @Override
            protected void responsed() {

            }
        };
        httpCommand.getSend().put("user",pref_user_pw.getString("user"));
        httpCommand.getSend().put("password",pref_user_pw.getString("password"));
        httpCommand.getSend().put("message", String.valueOf(MessageTypes.HELLO.value));

        addActor(myTimerActor = new MyTimerActor() {

            @Override
            public void init() {
                super.init();
            }

            @Override
            public void tick() {
                httpCommand.sendCommand();
            }
        });
        myTimerActor.start();
        myTimerActor.setInterval(4);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void init() {

    }
}
