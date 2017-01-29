package com.mygdx.game.OtherScr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Menu.MenuStage;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OtherStage extends MyStage {
    private TextButton yesButton, noButton;
    private MyLabel myLabel;

    private Label.LabelStyle style;

    public static final String PREFS = "MAP";
    private Preferences preferences,prefstatistic;

    public OtherStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {

        prefstatistic = Gdx.app.getPreferences(PlayScreen.PREFstatistic);
        preferences = Gdx.app.getPreferences(PlayScreen.PREFS);

        labelStyle();
        myLabel = new MyLabel("Are you sure?",style);
        myLabel.setAlignment(Align.center);
        myLabel.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight()/5);
        myLabel.setPosition(0,getViewport().getWorldHeight()/5*3);
        addActor(myLabel);

        yesButton = new MyButton("YES", game.getTextButtonStyle());
        yesButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                prefstatistic.putString(PlayScreen.PREFstatistic,"");
                preferences.putString(PlayScreen.PREFS,"");

                Statistics.setDefaultStatistics(); //statisztikák nullázása
                TimeStepper.setDefaultTime(); //idő nullázása

                game.setScreen(new PlayScreen(game));
            }
        });
        yesButton.setSize(getViewport().getWorldWidth()/5,getViewport().getWorldHeight()/5);
        yesButton.setPosition(getViewport().getWorldWidth()/5-yesButton.getWidth()/2,getViewport().getWorldHeight()/5*2-yesButton.getHeight()/2);
        addActor(yesButton);

        noButton = new MyButton("NO", game.getTextButtonStyle());
        noButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        noButton.setSize(getViewport().getWorldWidth()/5,getViewport().getWorldHeight()/5);
        noButton.setPosition(getViewport().getWorldWidth()/5*4-noButton.getWidth()/2,getViewport().getWorldHeight()/5*2-noButton.getHeight()/2);
        addActor(noButton);

    }

    private void labelStyle(){
        style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        //átméretezés
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 140;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!MenuStage.music.isPlaying() && MenuStage.playing){
            MenuStage.music.stop();
            MenuStage.music.play();
        }
    }
}
