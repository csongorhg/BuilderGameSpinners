package com.mygdx.game.Play;

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
import com.mygdx.game.PlayingMechanism.Units;
import com.mygdx.game.mapActorInterface.MapActorStage;

import java.sql.Time;

/**
 * Created by tanulo on 2017. 01. 26..
 */
public class IngameMenu extends MyStage {

    private MyLabel faLabel, koLabel, aranyLabel, nepLabel, etelLabel,faLabelValt, koLabelValt, aranyLabelValt, nepLabelValt, etelLabelValt, katonaLabel;
    private MyLabel napLabel;
    private OneSpriteStaticActor hatter, oneSpriteStaticActor;
    private OneSpriteStaticActor faActor, koActor, aranyActor, nepActor, etelActor, katonaActor;
    private float width, height;

    public IngameMenu(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void init() {

        hatter = new OneSpriteStaticActor(Assets.manager.get(Assets.FAHATTER));
        addActor(hatter);
        hatter.setSize(getViewport().getWorldWidth()-256- MapActorStage.meretes, hatter.getHeight());
        hatter.setPosition(0, getViewport().getWorldHeight() - hatter.getHeight() * 1 / 4);
        hatter.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        width = hatter.getWidth();
        height = hatter.getHeight() * 1/4;
        float negyed = width/4;
        float fel = height/2;

        faLabel = new MyLabel(""+Statistics.fa, labelStyle(fel,false));
        koLabel = new MyLabel(""+Statistics.ko, labelStyle(fel,false));
        aranyLabel = new MyLabel(""+Statistics.arany, labelStyle(fel,false));
        nepLabel = new MyLabel(""+Statistics.lakosokszama, labelStyle(fel,false));
        etelLabel = new MyLabel(""+Statistics.kaja, labelStyle(fel,false));
        katonaLabel = new MyLabel(""+Units.getLetszam(), labelStyle(fel,false));

        faLabelValt = new MyLabel(""+Statistics.faValt, labelStyle(fel,true));
        koLabelValt = new MyLabel(""+Statistics.koValt, labelStyle(fel,true));
        aranyLabelValt = new MyLabel(""+Statistics.aranyValt, labelStyle(fel,true));
        nepLabelValt = new MyLabel(""+Statistics.lakosokszamaValt, labelStyle(fel,true));
        etelLabelValt = new MyLabel(""+Statistics.kajaValt, labelStyle(fel,true));

        addActor(faLabel);
        addActor(koLabel);
        addActor(aranyLabel);
        addActor(nepLabel);
        addActor(etelLabel);
        addActor(katonaLabel);

        addActor(faLabelValt);
        addActor(koLabelValt);
        addActor(aranyLabelValt);
        addActor(nepLabelValt);
        addActor(etelLabelValt);

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

        faActor.setSize(fel,fel);
        koActor.setSize(fel,fel);
        aranyActor.setSize(fel,fel);
        nepActor.setSize(fel,fel);
        etelActor.setSize(fel,fel);
        katonaActor.setSize(fel,fel);

        faActor.setPosition(0,getViewport().getWorldHeight()-fel);
        koActor.setPosition(faActor.getX(),faActor.getY()-koActor.getHeight());
        faLabel.setPosition(faActor.getX()+(negyed*(1f/3f)),faActor.getY());
        koLabel.setPosition(koActor.getX()+(negyed*(1f/3f)),koActor.getY());
        faLabelValt.setPosition(faActor.getX()+(negyed*(2f/3f)),faActor.getY());
        koLabelValt.setPosition(koActor.getX()+(negyed*(2f/3f)),koActor.getY());

        aranyActor.setPosition(negyed,faActor.getY());
        etelActor.setPosition(aranyActor.getX(),aranyActor.getY()-etelActor.getHeight());
        aranyLabel.setPosition(aranyActor.getX()+(negyed*(1f/3f)),aranyActor.getY());
        etelLabel.setPosition(etelActor.getX()+(negyed*(1f/3f)),etelActor.getY());
        aranyLabelValt.setPosition(aranyActor.getX()+(negyed*(2f/3f)),aranyActor.getY());
        etelLabelValt.setPosition(etelActor.getX()+(negyed*(2f/3f)),etelActor.getY());

        nepActor.setPosition(negyed*2,aranyActor.getY());
        katonaActor.setPosition(nepActor.getX(),nepActor.getY()-katonaActor.getHeight());
        nepLabel.setPosition(nepActor.getX()+(negyed*(1f/3f)),nepActor.getY());
        katonaLabel.setPosition(katonaActor.getX()+(negyed*(1f/3f)),katonaActor.getY());
        nepLabelValt.setPosition(nepActor.getX()+(negyed*(2f/3f)),nepActor.getY());

        napLabel = new MyLabel("DAY "+TimeStepper.elteltnap, labelStyle(fel,false));
        addActor(napLabel);
        napLabel.setPosition((width-negyed/2)-napLabel.getWidth()/2,getViewport().getWorldHeight()-(fel+napLabel.getHeight()/2));

    }

    TamadasTextActor tamadasTextActor = null;

    @Override
    public void act(float delta) {
        super.act(delta);
        faLabel.setText(Statistics.fa+"");
        koLabel.setText(Statistics.ko+"");
        aranyLabel.setText(Statistics.arany+"");
        nepLabel.setText(Statistics.lakosokszama+"");
        etelLabel.setText(Statistics.kaja+"");

        faLabelValt.setText((TimeStepper.nyarvan?Statistics.faValt:(Statistics.faValt-Statistics.lakosokszama- Units.getLetszam()))+"");
        koLabelValt.setText(Statistics.koValt+"");
        aranyLabelValt.setText(Statistics.aranyValt-Units.getZsold()+"");
        etelLabelValt.setText((Statistics.kajaValt-Statistics.lakosokszama-Units.getLetszam())+"");
        nepLabelValt.setText(Statistics.lakosokszamaValt-Statistics.getlakosValt()+"");

        katonaLabel.setText(Units.getEro()+"");
        napLabel.setText("DAY "+TimeStepper.elteltnap);


        tamadas(TimeStepper.megtamadtak);
    }

    private void tamadas(boolean b){
        if(b) {
            if (tamadasTextActor == null) {
                addActor(tamadasTextActor = new TamadasTextActor(MapActorStage.labelStyle(80), getViewport().getWorldWidth()/2, getViewport().getWorldHeight()/2));
                tamadasTextActor.setZIndex(Integer.MAX_VALUE);
            }
        }
        else if(tamadasTextActor != null){
            tamadasTextActor.remove();
            tamadasTextActor = null;
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    private Label.LabelStyle labelStyle(float a,boolean green){
        Label.LabelStyle style;
        style = new Label.LabelStyle();
        if(green){
            style.fontColor = Color.GREEN;
        }else{
            style.fontColor = Color.WHITE;
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = (int)a;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;
        return style;
    }

    public float getHatterPosition(){return height+64;}
}
