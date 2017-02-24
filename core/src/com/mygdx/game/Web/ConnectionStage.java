package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTextField;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;

import java.util.Map;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ConnectionStage extends MyStage{

    private MyTextField user, password;
    private MyLabel userLabel, passwordLabel, title;
    private MyButton submit;

    private int nextScreen = 0;

    //pref
    private Preferences pref_user_pw;
    private static String save = "";

    private HttpCommand httpCommand = null;

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

        //user = new MyTextField(pref_user_pw.getString(ConnectionScreen.PREFS,"").equals("") ? "" : pref_user_pw.getString(ConnectionScreen.PREFS,"").split("\t")[0],game.getTextFieldStyle());
        user = new MyTextField(pref_user_pw.getString("user",""),game.getTextFieldStyle());
        user.setWidth(getViewport().getWorldWidth()/8*3);
        user.setPosition(getViewport().getWorldWidth()/2,userLabel.getY());
        addActor(user);

        passwordLabel = new MyLabel("Password:",game.getLabelStyle(50));
        passwordLabel.setAlignment(Align.center);
        passwordLabel.setSize(getViewport().getWorldWidth()/2,passwordLabel.getHeight());
        passwordLabel.setPosition(0,getViewport().getWorldHeight()/2-passwordLabel.getHeight());
        addActor(passwordLabel);

        password = new MyTextField(pref_user_pw.getString("password",""),game.getTextFieldStyle());
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
                //httpCommand = new HttpCommand("http://spinner.localhost/index.php"){
                httpCommand = new HttpCommand("http://193.224.143.135:9999"){
                    @Override
                    protected void failed(HttpErrors httpErrors) {

                    }

                    @Override
                    protected void responsed() {
                        System.out.println("Üzenet: " + getReceive().get("message"));
                        int m = Integer.valueOf(getReceive().get("message"));
                            switch (m) {
                                case 41:
                                    nextScreen = 41;
                                    break;
                                case 42:
                                    break;
                                case 60:
                                    break;
                                case 62:
                                    break;

                            }
                    }
                };
                httpCommand.getSend().put("user",user.getText());
                httpCommand.getSend().put("password",password.getText());
                httpCommand.getSend().put("message", String.valueOf(MessageTypes.CONNECT.value));
                httpCommand.getSend().put("offense_soldier","99");
                httpCommand.getSend().put("defense_soldier","111");
                httpCommand.sendCommand();
                pref_user_pw.putString("user", user.getText());
                pref_user_pw.putString("password", password.getText());
            }
        });
        addActor(submit);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (nextScreen!=0){
            switch (nextScreen){
                case 41:
                    game.setScreen(new BattleListScreen(game));
                    break;
            }

        }
    }
}
