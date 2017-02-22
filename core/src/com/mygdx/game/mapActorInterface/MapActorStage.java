package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Bluetooth.TestScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.mapActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorStage extends MyStage {

    protected mapActor mapactor;
    protected Actor selectActor = null;
    protected final int meret = 256;
    protected OneSpriteStaticActor banya, barrak, favago, halasz, haz, kut, mezo, hid;
    private static boolean elsoinditas = true;
    private OneSpriteStaticActor oneSpriteStaticActor;
    public static float meretes;
    public static float fightSize = 100;

    protected float picSize = getViewport().getWorldHeight()/6 > meret/2 ? meret/2 : getViewport().getWorldHeight()/6; //építős ikonok mérete

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
        //actorGroup.addActor(textButton);
        actorGroup.addActor(favago);
        actorGroup.addActor(banya);
        actorGroup.addActor(haz);
        actorGroup.addActor(barrak);
        actorGroup.addActor(kut);
        actorGroup.addActor(halasz);
        actorGroup.addActor(mezo);
        actorGroup.addActor(hid);

        /*if(g != null)
            if(g.isFog()) allRemove();*/

        if(elsoinditas){
            allRemove();
            elsoinditas = false;
        }
    }

    public void redXmaker(float posx,float posy){
        OneSpriteStaticActor X = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
        X.setSize(meret/2,meret/2);
        X.setPosition(posx,posy);
        actorGroup.addActor(X);
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

    protected void standardBuildings(){
        hid.remove();
        halasz.remove();
        favago.setPosition(0,getViewport().getWorldHeight()/2);
        banya.setPosition(0,getViewport().getWorldHeight()/2+favago.getHeight());
        haz.setPosition(0,getViewport().getWorldHeight()/2+favago.getHeight()+banya.getHeight());

        barrak.setPosition(0,getViewport().getWorldHeight()/2-barrak.getHeight());
        mezo.setPosition(0,getViewport().getWorldHeight()/2-barrak.getHeight()-mezo.getHeight());
        kut.setPosition(0,getViewport().getWorldHeight()/2-barrak.getHeight()-mezo.getHeight()-kut.getHeight());
    }

    protected  void waterbuildings(){
        favago.remove();
        banya.remove();
        haz.remove();
        barrak.remove();
        kut.remove();
        mezo.remove();

        halasz.setPosition(0,getViewport().getWorldHeight()/2);
        hid.setPosition(0,getViewport().getWorldHeight()/2-hid.getHeight());
    }

    public void ujepuletFeltolt(int t[]){
        for(int i=0; i<4; i++) PlayStage.ujepulet[i] = t[i];
    }

    @Override
    public void init() {

        favago = new OneSpriteStaticActor(Assets.manager.get(Assets.FAVAGO)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(0, getViewport().getWorldHeight()/2);
            }
        };
        //----------------------------------------
        banya = new OneSpriteStaticActor(Assets.manager.get(Assets.BANYA)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(meret/2, favago.getY()+favago.getHeight());
            }
        };
        //----------------------------------------
        haz = new OneSpriteStaticActor(Assets.manager.get(Assets.HAZ)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(0, banya.getY()+banya.getHeight());
            }
        };
        //----------------------------------------
        barrak = new OneSpriteStaticActor(Assets.manager.get(Assets.BARAKK)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(meret/2, getViewport().getWorldHeight()/2-getHeight());
            }
        };
        //----------------------------------------
        kut = new OneSpriteStaticActor(Assets.manager.get(Assets.KUT)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(0, barrak.getY()-getHeight());
            }
        };
        //----------------------------------------
        mezo = new OneSpriteStaticActor(Assets.manager.get(Assets.MEZO)){
        @Override
        public void init() {
            super.init();
            setSize(picSize,picSize);
            setPosition(0,kut.getY()-getHeight());
        }
        };
        //VÍZ----------------------------------------VÍZ
        halasz = new OneSpriteStaticActor(Assets.manager.get(Assets.HALASZ)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(0, getViewport().getWorldHeight()/2);
            }
        };
        //----------------------------------------

        hid = new OneSpriteStaticActor(Assets.manager.get(Assets.HID)){
            @Override
            public void init() {
                super.init();
                setSize(picSize,picSize);
                setPosition(0, getViewport().getWorldHeight()/2-getHeight());
            }
        };
        oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FIGHTBLUE));
        oneSpriteStaticActor.setSize(fightSize, fightSize);
        meretes = oneSpriteStaticActor.getWidth();
        oneSpriteStaticActor.setPosition(getViewport().getWorldWidth() - oneSpriteStaticActor.getWidth() - 256,
                getViewport().getWorldHeight() - oneSpriteStaticActor.getHeight());
        addActor(oneSpriteStaticActor);
        oneSpriteStaticActor.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new TestScreen(game));
            }
        });

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


/*
    public static Label.LabelStyle labelStyle(int a) {
        Label.LabelStyle style = new Label.LabelStyle();

        switch (a){
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
    }*/

}
