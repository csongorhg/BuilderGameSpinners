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
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.PlayingMechanism.Units;

import java.util.HashMap;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class ResultStage extends MyStage {
    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;
    private volatile boolean refresh = false;
    int nextScreen = 0;
    private int wood, gold, stone, food;
    MyLabel foodLabel, woodLabel, stoneLabel, goldLabel;

    private Preferences prefstatistic;
    private static String saveStatistic ="";

    public ResultStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);
        prefstatistic = Gdx.app.getPreferences(PlayScreen.PREFstatistic);
        httpCommand = new HttpCommand("http://193.224.143.135:9999"){
            @Override
            protected void failed(HttpErrors httpErrors) {

            }

            @Override
            protected void responsed() {

                HashMap<String, String> hm = getReceive();

                wood = Integer.valueOf(getReceive().get("wood"));
                gold = Integer.valueOf(getReceive().get("gold"));
                stone = Integer.valueOf(getReceive().get("stone"));
                food = Integer.valueOf(getReceive().get("food"));

                Units.katonaNullazas();
                tartalom();
                refresh = true;
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
            refresh = false;
            if(BattleListStage.defName != null){
                Statistics.arany += gold;
                Statistics.fa += wood;
                Statistics.kaja += food;
                Statistics.ko += stone;
                statisticSave();
            } else{
                Statistics.arany -= gold;
                Statistics.fa -= wood;
                Statistics.kaja -= food;
                Statistics.ko -= stone;
                statisticSave();
            }
            if(goldLabel != null)goldLabel.setText(gold+"");
            if(woodLabel != null)woodLabel.setText(wood+"");
            if(foodLabel != null)foodLabel.setText(food+"");
            if(stoneLabel != null)stoneLabel.setText(stone+"");
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

    private void statisticSave(){
        saveStatistic = "" ;
        saveStatistic = Statistics.legtobblakos+";"+Statistics.lakosokszama+";"
                +Statistics.fa+";"+Statistics.ko+";"+Statistics.arany+";"+Statistics.kaja+";"
                +Statistics.lakosokszamaValt+";"+Statistics.faValt+";"+Statistics.koValt+";"+Statistics.aranyValt+";"+Statistics.kajaValt+";"
                +Statistics.epuletekszama+";"+Statistics.kutakszama+";"+ TimeStepper.elteltnap+";"+TimeStepper.elteltido+";"
                +0+";"+0+";"+0+";"+0+";"+0;
        prefstatistic.putString(PlayScreen.PREFstatistic,saveStatistic);
        prefstatistic.flush();
    }

    private void tartalom(){
        if(BattleListStage.defName != null){
            System.out.println("VÉDŐNÉV: "+BattleListStage.defName);
            if(food != 0 || wood != 0 || stone != 0 || gold != 0){
                MyLabel label = new MyLabel("Congratulations!\nYou won your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()-label.getHeight()-20);
                addActor(label);
                MyLabel rev = new MyLabel("Your rewards: ",game.getLabelStyle(50));
                rev.setPosition(getViewport().getWorldWidth()/2-rev.getWidth()/2, getViewport().getWorldHeight()/2);
                addActor(rev);
                rewards(rev.getY());
            }
            else{
                MyLabel label = new MyLabel("You have lost your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()/2-label.getHeight()/2);
                addActor(label);
            }
        }
        else{
            if(food != 0 || wood != 0 || stone != 0 || gold != 0){
                MyLabel label = new MyLabel("You have lost your battle!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()-label.getHeight()-20);
                addActor(label);
                MyLabel rev = new MyLabel("You have lost: ",game.getLabelStyle(80));
                rev.setPosition(getViewport().getWorldWidth()/2-rev.getWidth()/2, getViewport().getWorldHeight()/2);
                addActor(rev);
                rewards(rev.getY());
            }
            else{
                MyLabel label = new MyLabel("Congratulations!\nYou have defended your city!", game.getLabelStyle(80));
                label.setAlignment(Align.center);
                label.setSize(getViewport().getWorldWidth(),label.getHeight());
                label.setPosition(0, getViewport().getWorldHeight()/2-label.getHeight()/2);
                addActor(label);
            }
        }
    }

    private void rewards(float posY){
        OneSpriteStaticActor foodSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        OneSpriteStaticActor woodSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        OneSpriteStaticActor stoneSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        OneSpriteStaticActor goldSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));

        foodLabel = new MyLabel("1000",game.getLabelStyle(50));
        woodLabel = new MyLabel("1000",game.getLabelStyle(50));
        stoneLabel = new MyLabel("1000",game.getLabelStyle(50));
        goldLabel = new MyLabel("1000",game.getLabelStyle(50));

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
        prefstatistic.flush();
        super.dispose();
    }
}
