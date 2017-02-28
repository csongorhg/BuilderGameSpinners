package com.mygdx.game.End;

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
import com.mygdx.game.Menu.MenuScreen;
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
public class EndStage extends MyStage {
    private TextButton button;
    private MyLabel myLabel1,myLabel;

    private Label.LabelStyle style;

    public static final String PREFS = "MAP";
    private Preferences preferences;

    public EndStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {

        preferences = Gdx.app.getPreferences(PlayScreen.PREFS);

        addBackEventStackListener();

        labelStyle();
        myLabel = new MyLabel("You survived "+TimeStepper.elteltnap+" days.",style);
        myLabel.setAlignment(Align.center);
        myLabel.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight()/5);
        myLabel.setPosition(0,getViewport().getWorldHeight()/5*3);
        addActor(myLabel);

        button = new MyButton("Back To Menu", game.getTextButtonStyle(100));
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                preferences.putString(PlayScreen.PREFS,"");
                game.setScreen(new MenuScreen(game));
            }
        });
        button.setSize(getViewport().getWorldWidth()/2,getViewport().getWorldHeight()/5);
        button.setPosition(getViewport().getWorldWidth()/2- button.getWidth()/2,getViewport().getWorldHeight()/10*2);
        addActor(button);

    }

    private void labelStyle(){
        style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        //átméretezés
        /*
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 75;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();*/
        style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT80);

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
