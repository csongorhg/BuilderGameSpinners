package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.Units;
import com.mygdx.game.UnitSelection.TestStage;

import java.util.Map;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ConnectionStage extends MyStage {

    private MyTextField user, password;
    private MyLabel userLabel, passwordLabel, title;
    private MyButton submit;
    private volatile String info = "";
    public static String staticUser;

    private int nextScreen = 0;

    //pref
    private Preferences pref_user_pw;
    private static String save = "";

    private HttpCommand httpCommand = null;

    private MyLabel informLabel;

    public ConnectionStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        addBackEventStackListener();
        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);

        userLabel = new MyLabel("Username: ", game.getLabelStyle(50));
        userLabel.setAlignment(Align.center);
        userLabel.setSize(getViewport().getWorldWidth() / 2, userLabel.getHeight());
        userLabel.setPosition(0, getViewport().getWorldHeight() / 2);
        addActor(userLabel);

        //user = new MyTextField(pref_user_pw.getString(ConnectionScreen.PREFS,"").equals("") ? "" : pref_user_pw.getString(ConnectionScreen.PREFS,"").split("\t")[0],game.getTextFieldStyle());
        user = new MyTextField(pref_user_pw.getString("user", ""), game.getTextFieldStyle());
        user.setWidth(getViewport().getWorldWidth() / 8 * 3);
        user.setPosition(getViewport().getWorldWidth() / 2, userLabel.getY());
        addActor(user);

        passwordLabel = new MyLabel("Password:", game.getLabelStyle(50));
        passwordLabel.setAlignment(Align.center);
        passwordLabel.setSize(getViewport().getWorldWidth() / 2, passwordLabel.getHeight());
        passwordLabel.setPosition(0, getViewport().getWorldHeight() / 2 - passwordLabel.getHeight());
        addActor(passwordLabel);

        password = new MyTextField(pref_user_pw.getString("password", ""), game.getTextFieldStyle());
        password.setWidth(getViewport().getWorldWidth() / 8 * 3);
        password.setPosition(getViewport().getWorldWidth() / 2, passwordLabel.getY());
        addActor(password);

        title = new MyLabel("Connect to online battle", game.getLabelStyle(100));
        title.setPosition(getViewport().getWorldWidth() / 2 - title.getWidth() / 2, userLabel.getY() + userLabel.getHeight() + (getViewport().getWorldHeight() - (userLabel.getY() + userLabel.getHeight())) / 2 - title.getHeight() / 2);
        addActor(title);

        submit = new MyButton("CONNECT", game.getTextButtonStyle(100));
        submit.setSize(submit.getWidth()+submit.getWidth()/4,submit.getHeight());
        submit.setPosition(getViewport().getWorldWidth() / 2 - submit.getWidth() / 2, 10);
        submit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (!user.getText().equals("") && !password.getText().equals("") &&
                        user.getText().length() >= 4 && user.getText().length() <= 18 &&
                        password.getText().length() >= 4 && password.getText().length() <= 10) {

                    info = "";
                    informLabel.setText("Connecting...");
                    pref_user_pw.putString("user", user.getText());
                    pref_user_pw.putString("password", password.getText());
                    staticUser = user.getText();
                    pref_user_pw.flush();
                    informLabel.setPosition(getViewport().getWorldWidth() / 2 - informLabel.getWidth() / 2, submit.getY() + submit.getHeight() + informLabel.getHeight());
                    informLabel.setAlignment(Align.center);
                    //httpCommand = new HttpCommand("http://spinner.localhost/index.php"){
                    httpCommand = new HttpCommand("http://193.224.143.135:9999") {
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
                                    info = "You already connected.";
                                    break;
                                case 60:
                                    info = "Password or username error.";
                                    break;
                                case 62:
                                    info = "Username already used.";
                                    break;
                                case 404:
                                case 101010:
                                    game.setScreen(new LostConnectionScreen(game));
                                    break;
                            }
                        }
                    };
                    httpCommand.getSend().put("user", user.getText());
                    httpCommand.getSend().put("password", password.getText());
                    httpCommand.getSend().put("message", String.valueOf(MessageTypes.CONNECT.value));
                    httpCommand.getSend().put("offense_soldier", Units.getAttack()+"");
                    httpCommand.getSend().put("defense_soldier", Units.getDeffense()+"");
                    httpCommand.getSend().put("gold", Statistics.arany+"");
                    httpCommand.getSend().put("wood", Statistics.fa+"");
                    httpCommand.getSend().put("stone", Statistics.ko+"");
                    httpCommand.getSend().put("food", Statistics.kaja+"");
                    httpCommand.sendCommand();
                } else {
                    informLabel.setText("Bad incredential(s)!");
                    informLabel.setPosition(getViewport().getWorldWidth() / 2 - informLabel.getWidth() / 2, submit.getY() + submit.getHeight() + informLabel.getHeight());
                    informLabel.setAlignment(Align.center);
                }


            }
        });
        addActor(submit);

        informLabel = new MyLabel("Enter your username & password", game.getLabelStyle(50));
        informLabel.setPosition(getViewport().getWorldWidth() / 2 - informLabel.getWidth() / 2, submit.getY() + submit.getHeight() + informLabel.getHeight());
        addActor(informLabel);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (nextScreen != 0) {
            switch (nextScreen) {
                case 41:
                    game.setScreen(new BattleListScreen(game));
                    break;
            }
        }
        if (info.compareTo("")!=0){
            informLabel.setText(info);
            info = "";
        }
    }

    @Override
    public void dispose() {
        pref_user_pw.flush();
        super.dispose();
    }
}
