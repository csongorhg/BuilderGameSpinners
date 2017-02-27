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
        float otkaraktereshossz = new MyLabel("-MMMMM", game.getLabelStyle(25,Color.BLACK)).getWidth();

        faLabel = new MyLabel(""+Statistics.fa, game.getLabelStyle(25,Color.WHITE));
        koLabel = new MyLabel(""+Statistics.ko, game.getLabelStyle(25,Color.WHITE));
        aranyLabel = new MyLabel(""+Statistics.arany, game.getLabelStyle(25,Color.WHITE));
        nepLabel = new MyLabel(""+Statistics.lakosokszama, game.getLabelStyle(25,Color.WHITE));
        etelLabel = new MyLabel(""+Statistics.kaja, game.getLabelStyle(25,Color.WHITE));
        katonaLabel = new MyLabel(""+Units.getEro(), game.getLabelStyle(25,Color.WHITE));

        faLabelValt = new MyLabel(""+Statistics.faValt, game.getLabelStyle(25,(Statistics.faValt>=0?Color.GREEN:Color.BLACK)));
        koLabelValt = new MyLabel(""+Statistics.koValt, game.getLabelStyle(25,(Statistics.koValt>=0?Color.GREEN:Color.BLACK)));
        aranyLabelValt = new MyLabel(""+Statistics.aranyValt, game.getLabelStyle(25,(Statistics.aranyValt>=0?Color.GREEN:Color.BLACK)));
        nepLabelValt = new MyLabel(""+Statistics.lakosokszamaValt, game.getLabelStyle(25,(Statistics.lakosokszamaValt>=0?Color.GREEN:Color.BLACK)));
        etelLabelValt = new MyLabel(""+Statistics.kajaValt, game.getLabelStyle(25,(Statistics.kajaValt>=0?Color.GREEN:Color.BLACK)));

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
        katonaActor = new OneSpriteStaticActor(Assets.manager.get(Assets.KARD_FENN));

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
        faLabel.setPosition(faActor.getX()+otkaraktereshossz,faActor.getY());
        koLabel.setPosition(koActor.getX()+otkaraktereshossz,koActor.getY());
        faLabelValt.setPosition(faActor.getX()+faActor.getWidth(),faActor.getY());
        koLabelValt.setPosition(koActor.getX()+koActor.getWidth(),koActor.getY());

        aranyActor.setPosition(negyed,faActor.getY());
        etelActor.setPosition(aranyActor.getX(),aranyActor.getY()-etelActor.getHeight());
        aranyLabel.setPosition(aranyActor.getX()+otkaraktereshossz,aranyActor.getY());
        etelLabel.setPosition(etelActor.getX()+otkaraktereshossz,etelActor.getY());
        aranyLabelValt.setPosition(aranyActor.getX()+aranyActor.getWidth(),aranyActor.getY());
        etelLabelValt.setPosition(etelActor.getX()+etelActor.getWidth(),etelActor.getY());

        nepActor.setPosition(negyed*2,aranyActor.getY());
        katonaActor.setPosition(nepActor.getX(),nepActor.getY()-katonaActor.getHeight());
        nepLabel.setPosition(nepActor.getX()+otkaraktereshossz,nepActor.getY());
        katonaLabel.setPosition(katonaActor.getX()+otkaraktereshossz,katonaActor.getY());
        nepLabelValt.setPosition(nepActor.getX()+nepActor.getWidth(),nepActor.getY());

        napLabel = new MyLabel("DAY "+TimeStepper.elteltnap, game.getLabelStyle(25,Color.WHITE));
        addActor(napLabel);
        napLabel.setPosition(width-150,getViewport().getWorldHeight()-(fel+napLabel.getHeight()/2));

    }


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
        aranyLabelValt.setText(Statistics.aranyValt+"");
        etelLabelValt.setText((Statistics.kajaValt-Statistics.lakosokszama-Units.getLetszam())+"");
        nepLabelValt.setText(Statistics.lakosokszamaValt-Statistics.getlakosValt()+"");

        faLabelValt.setColor((TimeStepper.nyarvan?Statistics.faValt:(Statistics.faValt-Statistics.lakosokszama- Units.getLetszam()))>=0?Color.GREEN:Color.BLACK);
        koLabelValt.setColor(Statistics.koValt>=0?Color.GREEN:Color.BLACK);
        aranyLabelValt.setColor(Statistics.aranyValt>=0?Color.GREEN:Color.BLACK);
        etelLabelValt.setColor(Statistics.kajaValt-Statistics.lakosokszama-Units.getLetszam()>=0?Color.GREEN:Color.BLACK);
        nepLabelValt.setColor(Statistics.lakosokszamaValt-Statistics.getlakosValt()>=0?Color.GREEN:Color.BLACK);


        katonaLabel.setText(Units.getEro()+"");
        napLabel.setText("DAY "+TimeStepper.elteltnap);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

/*
    private Label.LabelStyle labelStyle(int a, Color color){
        Label.LabelStyle style;
        style = new Label.LabelStyle();

        style.fontColor = color;

        switch (a){
            case 10:
                style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT10);
                break;
            case 25:
                style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT25);
                break;
            case 50:
                style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT50);
                break;
            case 80:
                style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT80);
                break;
            default:
                new Exception("Nem kezelt betűméret: " + a);
        }
        return style;
    }
*/
    public float getHatterPosition(){return height+64;}
}
