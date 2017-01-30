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

import java.sql.Time;

/**
 * Created by tanulo on 2017. 01. 26..
 */
public class IngameMenu extends MyStage {

    private MyLabel faLabel, koLabel, aranyLabel, nepLabel, etelLabel,faLabelValt, koLabelValt, aranyLabelValt, nepLabelValt, etelLabelValt, katonaLabel;
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
        hatter.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        faLabel = new MyLabel(""+Statistics.fa, labelStyle(hatter.getHeight()/8-10,false));
        koLabel = new MyLabel(""+Statistics.ko, labelStyle(hatter.getHeight()/8-10,false));
        aranyLabel = new MyLabel(""+Statistics.arany, labelStyle(hatter.getHeight()/8-10,false));
        nepLabel = new MyLabel(""+Statistics.lakosokszama, labelStyle(hatter.getHeight()/8-10,false));
        etelLabel = new MyLabel(""+Statistics.kaja, labelStyle(hatter.getHeight()/8-10,false));
        katonaLabel = new MyLabel(""+Statistics.katonakszama, labelStyle(hatter.getHeight()/8-10,false));

        faLabelValt = new MyLabel(""+Statistics.faValt, labelStyle(hatter.getHeight()/8-10,true));
        koLabelValt = new MyLabel(""+Statistics.koValt, labelStyle(hatter.getHeight()/8-10,true));
        aranyLabelValt = new MyLabel(""+Statistics.aranyValt, labelStyle(hatter.getHeight()/8-10,true));
        nepLabelValt = new MyLabel(""+Statistics.lakosokszamaValt, labelStyle(hatter.getHeight()/8-10,true));
        etelLabelValt = new MyLabel(""+Statistics.kajaValt, labelStyle(hatter.getHeight()/8-10,true));

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

        faActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        koActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        aranyActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        nepActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        etelActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);
        katonaActor.setSize(hatter.getHeight()/8-10,hatter.getHeight()/8-10);

        faActor.setPosition(width/40*1,height/30*28);
        koActor.setPosition(width/40*1,height/30*26);
        faLabel.setPosition(width/40*3,height/30*28);
        koLabel.setPosition(width/40*3,height/30*26);
        faLabelValt.setPosition(width/40*5,height/30*28);
        koLabelValt.setPosition(width/40*5,height/30*26);

        aranyActor.setPosition(width/40*7,height/30*28);
        etelActor.setPosition(width/40*7,height/30*26);
        aranyLabel.setPosition(width/40*9,height/30*28);
        etelLabel.setPosition(width/40*9,height/30*26);
        aranyLabelValt.setPosition(width/40*11,height/30*28);
        etelLabelValt.setPosition(width/40*11,height/30*26);

        nepActor.setPosition(width/40*13,height/30*28);
        katonaActor.setPosition(width/40*13,height/30*26);
        nepLabel.setPosition(width/40*15,height/30*28);
        katonaLabel.setPosition(width/40*15,height/30*26);
        nepLabelValt.setPosition(width/40*17,height/30*28);

        napLabel = new MyLabel("DAY "+TimeStepper.elteltnap, labelStyle(hatter.getHeight()/8-10,false));
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

        faLabelValt.setText((TimeStepper.nyarvan?Statistics.faValt:(Statistics.faValt-Statistics.lakosokszama-Statistics.katonakszama))+"");
        koLabelValt.setText(Statistics.koValt+"");
        aranyLabelValt.setText(Statistics.aranyValt+"");
        nepLabelValt.setText(Statistics.lakosokszamaValt+"");
        etelLabelValt.setText((Statistics.kajaValt-Statistics.lakosokszama-Statistics.katonakszama)+"");

        katonaLabel.setText(Statistics.katonakszama+"");
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

    public float getHatterPosition(){return (hatter.getHeight() * 1 / 4 + 10);}
}
