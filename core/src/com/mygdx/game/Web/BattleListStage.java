package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
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

    private static final String reallyLongString = "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n";


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
    public void init() {//nem volt jó az assetsmanagerben a json
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label text = new Label(reallyLongString, skin);
        text.setAlignment(Align.center);
        text.setWrap(true);
        Label text2 = new Label("This is a short string!", skin);
        text2.setAlignment(Align.center);
        text2.setWrap(true);
        Label text3 = new Label(reallyLongString, skin);
        text3.setAlignment(Align.center);
        text3.setWrap(true);

        Table scrollTable = new Table();
        scrollTable.add(text);
        scrollTable.row();
        scrollTable.add(text2);
        scrollTable.row();
        scrollTable.add(text3);

        ScrollPane scroller = new ScrollPane(scrollTable);

        Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand();

        addActor(table);
    }
}
