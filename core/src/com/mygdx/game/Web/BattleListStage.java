package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTimerActor;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class BattleListStage extends MyStage {


private MyTimerActor myTimerActor;

    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;
    List<String> list = new List<String>(game.getListStyle());
    final MyLabel label = new MyLabel("", game.getLabelStyle());
    final MyButton button = new MyButton("Play", game.getTextButtonStyle(140));
    public static String defName;
    int nextScreen = 0;

    public BattleListStage(Viewport viewport, Batch batch, MyGdxGame game) {
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
                    case 11:
                        /*
                        final Random r = new Random();
                        int rn;
                        for(int i = 0; i<7; i++){
                            hm.put("user" + i, String.valueOf(rn = r.nextInt(10)));
                            System.out.print(rn);
                        }
                        */

                        System.out.println();
                        Array<String> users = new Array<String>();
                        for (Map.Entry<String, String> entry : hm.entrySet()) {
                            if (entry.getKey().contains("user") && entry.getKey().compareTo("user") != 0) {
                                users.add(entry.getValue());
                            }
                        }
                        list.getItems().clear();
                        for (String s : users) {
                            if (!list.getItems().contains(s, false)) {
                                list.getItems().add(s);
                            }
                        }
                        label.setVisible(list.getSelected() != null && list.getItems().contains(label.getText().toString(), false));
                        button.setVisible(label.isVisible());
                        break;
                    case 20:
                        nextScreen = 20;
                        break;
                    case 21:
                        nextScreen = 21;
                        break;
                }

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


        label.setPosition(getViewport().getWorldWidth()/4*3-button.getWidth()/2,getViewport().getWorldHeight()/4*3);
        label.setVisible(false);


        button.setSize(300,160);
        button.setPosition(getViewport().getWorldWidth()/4*3-button.getWidth()/2,getViewport().getWorldHeight()/2-button.getHeight()/2);
        button.setVisible(false);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                defName = label.getText().toString();
                httpCommand.getSend().put("defendername", label.getText().toString());
                httpCommand.getSend().put("message", "20");
                httpCommand.sendCommand();
                httpCommand.getSend().put("message", "10");
            }
        });
        list.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(list.getSelected());
                label.setText(list.getSelected());
                label.setVisible(true);
                button.setVisible(true);
            }
        });
        ScrollPane scrollPane = new ScrollPane(list, game.getScrollPaneStyle());
        scrollPane.setSize(getViewport().getWorldWidth()/2,getViewport().getWorldHeight());
        scrollPane.setScrollingDisabled(true, false);
        //scrollPane.setOverscroll(false, false);
        addActor(scrollPane);
        addActor(label);
        addActor(button);
        list.setSize(scrollPane.getWidth()/2,list.getItems().size*list.getStyle().font.getLineHeight());
        list.setX(scrollPane.getX()+scrollPane.getWidth()/2);
        httpCommand.sendCommand();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (nextScreen != 0) {
            switch (nextScreen) {
                case 20: //Megtámadtak
                case 21: //Saját támadás nyugtázva
                    game.setScreen(new ResultScreen(game));
                    break;
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
        //nem volt jó az assetsmanagerben a json
/*        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

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

        addActor(table);*/

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
