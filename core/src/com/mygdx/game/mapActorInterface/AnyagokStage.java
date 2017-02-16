package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
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
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;

import static com.badlogic.gdx.scenes.scene2d.utils.ScissorStack.getViewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class AnyagokStage extends Group {
    private MyLabel fa,ko,arany,hus,lakos;
    private OneSpriteStaticActor fak,kok,aranyk,husk,lakosk;
    String fas,kos,aranys,huss,lakoss;

    Viewport vp;
    int x,y;

    private Label.LabelStyle style;

    public AnyagokStage(Viewport v,int x,int y,int f,int k,int a,int h,int l) {
        super();
        this.x = x;
        this.y = y;
        vp = v;

        fas = f+"";
        kos = k+"";
        aranys = a+"";
        huss= h+"";
        lakoss=l+"";

        init();
    }


    public void init() {

        fak = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        kok = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        aranyk = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        husk = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        lakosk = new OneSpriteStaticActor(Assets.manager.get(Assets.PEOPLE));

        fak.setSize(getViewport().height/10,getViewport().height/10);
        kok.setSize(getViewport().height/10,getViewport().height/10);
        aranyk.setSize(getViewport().height/10,getViewport().height/10);
        husk.setSize(getViewport().height/10,getViewport().height/10);
        lakosk.setSize(getViewport().height/10,getViewport().height/10);

        fak.setPosition(x+vp.getWorldWidth()/4*3+85,y+vp.getWorldHeight()/4*3+50);
        kok.setPosition(x+vp.getWorldWidth()/4*3+135,y+vp.getWorldHeight()/4*3+50);
        aranyk.setPosition(x+vp.getWorldWidth()/4*3+85,y+vp.getWorldHeight()/4*3+25);
        husk.setPosition(x+vp.getWorldWidth()/4*3+135,y+vp.getWorldHeight()/4*3+25);
        lakosk.setPosition(x+vp.getWorldWidth()/4*3+85,y+vp.getWorldHeight()/4*3);

        addActor(fak);
        addActor(kok);
        addActor(aranyk);
        addActor(husk);
        addActor(lakosk);

        labelStyle();
        fa = new MyLabel(fas,style);
        fa.setAlignment(Align.center);
        fa.setSize(getViewport().width/10,getViewport().height/10);
        fa.setPosition(x+vp.getWorldWidth()/4*3+100,y+vp.getWorldHeight()/4*3+50);
        addActor(fa);


        ko = new MyLabel(kos,style);
        ko.setAlignment(Align.center);
        ko.setSize(getViewport().width/10,getViewport().height/10);
        ko.setPosition(x+vp.getWorldWidth()/4*3 + 150,y+vp.getWorldHeight()/4*3+50);
        addActor(ko);


        arany = new MyLabel(aranys,style);
        arany.setAlignment(Align.center);
        arany.setSize(getViewport().width/10,getViewport().height/10);
        arany.setPosition(x+vp.getWorldWidth()/4*3+100,y+vp.getWorldHeight()/4*3+25);
        addActor(arany);


        hus = new MyLabel(huss,style);
        hus.setAlignment(Align.center);
        hus.setSize(getViewport().width/10,getViewport().height/10);
        hus.setPosition(x+vp.getWorldWidth()/4*3+150,y+vp.getWorldHeight()/4*3+25);
        addActor(hus);


        lakos = new MyLabel(lakoss,style);
        lakos.setAlignment(Align.center);
        lakos.setSize(getViewport().width/10,getViewport().height/10);
        lakos.setPosition(x+vp.getWorldWidth()/4*3+100,y+vp.getWorldHeight()/4*3);
        addActor(lakos);

    }

    private void labelStyle(){
        style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        //átméretezés
/*        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = 25;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();*/
        style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT25);

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
