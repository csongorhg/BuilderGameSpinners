package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Bridge;
import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WaterWell;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.Game.IngameMenu;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.cityActor;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorStage extends MyStage {

    protected mapActor mapactor;
    protected Actor selectActor = null;
    protected final int meret = 256;
    private TextButton textButton;
    protected OneSpriteStaticActor banya, barrak, favago, halasz, haz, kut, mezo, hid;
    private static boolean elsoinditas = true;

    public Group getActorGroup() {
        return actorGroup;
    }

    private Group actorGroup;

    public MapActorStage(MyGdxGame game, mapActor g) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), new SpriteBatch(), game);
        if (g != null) {
            mapactor = g;
            addSelectActor(g.getStage());
        }

        addActor(actorGroup = new Group());
        actorGroup.setPosition(getViewport().getWorldWidth()-meret, 0);
        actorGroup.setSize(meret, getViewport().getWorldHeight());


        OneSpriteStaticActor oneSpriteStaticActor;
        actorGroup.addActor(oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FAHATTER)){
            @Override
            public void init() {
                super.init();
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }
        });
        oneSpriteStaticActor.setPosition(0,0);
        oneSpriteStaticActor.setSize(actorGroup.getWidth(), actorGroup.getHeight());

        init();
        actorGroup.addActor(textButton);
        actorGroup.addActor(favago);
        actorGroup.addActor(banya);
        actorGroup.addActor(haz);
        actorGroup.addActor(barrak);
        actorGroup.addActor(kut);
        actorGroup.addActor(halasz);
        actorGroup.addActor(mezo);
        actorGroup.addActor(hid);

        if(g != null)
            if(g.isFog()) allRemove();

        if(elsoinditas){
            allRemove();
            elsoinditas = false;
        }
    }

    protected void allRemove(){
        favago.remove();
        banya.remove();
        haz.remove();
        barrak.remove();
        kut.remove();
        halasz.remove();
        mezo.remove();
        hid.remove();
    }

    private void ujepuletFeltolt(int t[]){
        for(int i=0; i<4; i++) PlayStage.ujepulet[i] = t[i];
    }

    @Override
    public void init() {

        favago = new OneSpriteStaticActor(Assets.manager.get(Assets.FAVAGO)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(0, getViewport().getWorldHeight()/2);
            }
        };
        favago.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 11};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        banya = new OneSpriteStaticActor(Assets.manager.get(Assets.BANYA)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/2, getViewport().getWorldHeight()/2);
            }
        };
        banya.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 16};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        haz = new OneSpriteStaticActor(Assets.manager.get(Assets.HAZ)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(0, getViewport().getWorldHeight()/2-meret/2);
            }
        };
        haz.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 14};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        barrak = new OneSpriteStaticActor(Assets.manager.get(Assets.BARAKK)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/2, getViewport().getWorldHeight()/2-meret/2);
            }
        };
        barrak.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 15};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        kut = new OneSpriteStaticActor(Assets.manager.get(Assets.KUT)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(0, getViewport().getWorldHeight()/2-meret);
            }
        };
        kut.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 17};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        halasz = new OneSpriteStaticActor(Assets.manager.get(Assets.HALASZ)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/2, getViewport().getWorldHeight()/2-meret);
            }
        };
        halasz.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 12};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        mezo = new OneSpriteStaticActor(Assets.manager.get(Assets.MEZO)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(0, getViewport().getWorldHeight()/2-meret-meret/2);
            }
        };
        mezo.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 18};
                ujepuletFeltolt(t);
            }
        });
        //----------------------------------------
        hid = new OneSpriteStaticActor(Assets.manager.get(Assets.HID)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/2, getViewport().getWorldHeight()/2-meret-meret/2);
            }
        };
        hid.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 13};
                ujepuletFeltolt(t);
            }
        });


        textButton = new MyButton("Exit game", textButtonStyle(50));
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                elsoinditas = true;
                game.setScreen(new MenuScreen(game));
            }
        });
        textButton.setSize(meret, textButton.getHeight());
        textButton.setPosition(0, 0);
    }


    public void addSelectActor(Stage stage){
        if(mapactor != null && !mapactor.isFog()) {
            this.selectActor = new OneSpriteStaticActor(Assets.manager.get(Assets.KIJELOLES)) {
                @Override
                public void init() {
                    super.init();
                    setSize(mapactor.getMapActorWidth(), mapactor.getMapActorHeight());
                    setPosition(mapactor.getX()-(mapactor.getMapActorWidth()-128), mapactor.getY());
                    setTouchable(Touchable.disabled);
                }
            };
            stage.addActor(selectActor);
        }
    }

    @Override
    public void dispose() {
        if (selectActor != null && selectActor.getStage()!=null) {
            selectActor.getStage().getActors().removeValue(selectActor, true);
        }
        super.dispose();
    }

    private TextButton.TextButtonStyle textButtonStyle(int a){
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
        p.setColor(0.1f,0.2f,0.2f, 0.5f);
        p.fill();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
        p.setColor(0.3f,0.5f,0.8f, 0.5f);
        p.fill();
        style.over = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
        p.setColor(1f,0.5f,0.8f, 1f);
        p.fill();
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = a;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;

        return style;
    }

}