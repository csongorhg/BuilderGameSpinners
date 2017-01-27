package com.mygdx.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;

import java.sql.Time;

/**
 * Created by tanulo on 2017. 01. 26..
 */
public class IngameMenu extends MyStage {

    private MyLabel faLabel, koLabel, aranyLabel, nepLabel, etelLabel, katonaLabel;
    private MyLabel napLabel;
    private OneSpriteStaticActor hatter;
    private OneSpriteStaticActor faActor, koActor, aranyActor, nepActor, etelActor, katonaActor;
    private float width, height;

    public IngameMenu(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void init() {
        width = ((ExtendViewport)getViewport()).getWorldWidth();
        height = ((ExtendViewport)getViewport()).getWorldHeight();

        hatter = new OneSpriteStaticActor(Assets.manager.get(Assets.FAHATTER));
        addActor(hatter);
        hatter.setSize(width/2+150, hatter.getHeight());
        hatter.setPosition(0, height - hatter.getHeight() * 1 / 4 - 10);

        Label.LabelStyle style = labelStyle(hatter.getHeight()/8-10);

        faLabel = new MyLabel(""+Statistics.fa, style);
        koLabel = new MyLabel(""+Statistics.ko, style);
        aranyLabel = new MyLabel(""+Statistics.arany, style);
        nepLabel = new MyLabel(""+Statistics.lakosokszama, style);
        etelLabel = new MyLabel(""+Statistics.kaja, style);
        katonaLabel = new MyLabel(""+Statistics.katonakszama, style);

        addActor(faLabel);
        addActor(koLabel);
        addActor(aranyLabel);
        addActor(nepLabel);
        addActor(etelLabel);
        addActor(katonaLabel);

        faActor = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        koActor = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        aranyActor = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        nepActor = new OneSpriteStaticActor(Assets.manager.get(Assets.PEOPLE));
        etelActor = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        katonaActor = new OneSpriteStaticActor(Assets.manager.get(Assets.BARAKK));

        addActor(faActor);
        addActor(koActor);
        addActor(aranyActor);
        addActor(nepActor);
        addActor(etelActor);
        addActor(katonaActor);

        faActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        koActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        aranyActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        nepActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        etelActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        katonaActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);

        faActor.setPosition(5, height-5-faActor.getHeight());
        koActor.setPosition(5, faActor.getY()-10-koActor.getHeight());
        faLabel.setPosition(faActor.getX()+faActor.getWidth()+20, faActor.getY()-10);
        koLabel.setPosition(koActor.getX()+koActor.getWidth()+20, koActor.getY()-10);

        aranyActor.setPosition(faActor.getX()+faActor.getWidth()+150, faActor.getY());
        etelActor.setPosition(koActor.getX()+koActor.getWidth()+150, koActor.getY());
        aranyLabel.setPosition(aranyActor.getX()+aranyActor.getWidth()+20, aranyActor.getY()-10);
        etelLabel.setPosition(etelActor.getX()+etelActor.getWidth()+20, etelActor.getY()-10);

        nepActor.setPosition(aranyActor.getX()+aranyActor.getWidth()+150, aranyActor.getY());
        katonaActor.setPosition(etelActor.getX()+etelActor.getWidth()+150, etelActor.getY());
        nepLabel.setPosition(nepActor.getX()+nepActor.getWidth()+20, nepActor.getY()-10);
        katonaLabel.setPosition(katonaActor.getX()+katonaActor.getWidth()+20, katonaActor.getY()-10);

        napLabel = new MyLabel(TimeStepper.elteltnap+". nap", style);
        addActor(napLabel);
        napLabel.setPosition(nepActor.getX()+nepActor.getWidth()+150, height-((hatter.getHeight()/8)+napLabel.getHeight()/2)-5);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        faLabel.setText(Statistics.fa+"");
        koLabel.setText(Statistics.ko+"");
        aranyLabel.setText(Statistics.arany+"");
        nepLabel.setText(Statistics.lakosokszama+"");
        etelLabel.setText(Statistics.kaja+"");
        katonaLabel.setText(Statistics.katonakszama+"");
        napLabel.setText(TimeStepper.elteltnap+". nap");
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    private Label.LabelStyle labelStyle(float a){
        Label.LabelStyle style;
        style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = (int)a;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;
        return style;
    }

    public float getHatterPosition(){return (hatter.getHeight() * 1 / 4 + 10);}
}
