package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTextField;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ConnectionStage extends MyStage{

    private MyTextField user, password;
    private MyLabel userLabel, passwordLabel, title;
    private MyButton submit;

    //pref
    private Preferences pref_user_pw;
    private static String save = "";

    public ConnectionStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {

        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);

        userLabel = new MyLabel("Username: ",game.getLabelStyle(50));
        userLabel.setAlignment(Align.center);
        userLabel.setSize(getViewport().getWorldWidth()/2,userLabel.getHeight());
        userLabel.setPosition(0,getViewport().getWorldHeight()/2);
        addActor(userLabel);

        user = new MyTextField(pref_user_pw.getString(ConnectionScreen.PREFS,"").equals("") ? "" : pref_user_pw.getString(ConnectionScreen.PREFS,"").split("\t")[0],game.getTextFieldStyle());
        user.setWidth(getViewport().getWorldWidth()/8*3);
        user.setPosition(getViewport().getWorldWidth()/2,userLabel.getY());
        addActor(user);

        passwordLabel = new MyLabel("Password:",game.getLabelStyle(50));
        passwordLabel.setAlignment(Align.center);
        passwordLabel.setSize(getViewport().getWorldWidth()/2,passwordLabel.getHeight());
        passwordLabel.setPosition(0,getViewport().getWorldHeight()/2-passwordLabel.getHeight());
        addActor(passwordLabel);

        password = new MyTextField(pref_user_pw.getString(ConnectionScreen.PREFS,"").equals("") ? "" : pref_user_pw.getString(ConnectionScreen.PREFS,"").split("\t")[1], game.getTextFieldStyle());
        password.setWidth(getViewport().getWorldWidth()/8*3);
        password.setPosition(getViewport().getWorldWidth()/2,passwordLabel.getY());
        addActor(password);

        title = new MyLabel("Connect to online battle",game.getLabelStyle(100));
        title.setPosition(getViewport().getWorldWidth()/2-title.getWidth()/2,userLabel.getY()+userLabel.getHeight() + (getViewport().getWorldHeight() - (userLabel.getY()+userLabel.getHeight()))/2 - title.getHeight()/2);
        addActor(title);

        submit = new MyButton("CONNECT",game.getTextButtonStyle(100));
        submit.setPosition(getViewport().getWorldWidth()/2-submit.getWidth()/2, 10);
        submit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("OKÉ");
                pref_user_pw.putString(ConnectionScreen.PREFS,user.getText()+"\t"+password.getText());
            }
        });
        addActor(submit);
    }
}
