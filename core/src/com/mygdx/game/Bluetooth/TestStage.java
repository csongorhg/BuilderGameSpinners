package com.mygdx.game.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Units;
import com.mygdx.game.mapActorInterface.MapActorStage;

/**
 * Created by Kicsi on 2017. 02. 02..
 */

public class TestStage extends MyStage {

    private OneSpriteStaticActor harc1, harc2, harc3, harc4;
    private OneSpriteStaticActor plusz1, minusz1, plusz2, minusz2, plusz3, minusz3, plusz4, minusz4;
    private MyLabel osszesites;
    public static int osszletszam;
    public static int harc1letszam, harc2letszam, harc3letszam, harc4letszam;
    private MyButton fight;
    private float width, height;

    public TestStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setInputProcessor(this);
    }

    public void init(){
        osszletszam = 0;

        harc1letszam = 0;
        harc2letszam = 0;
        harc3letszam = 0;
        harc4letszam = 0;

        System.out.println("KARDOS: "+Units.kardosLetszam);
        System.out.println("IJÁSZ: "+Units.ijjaszLetszam);
        System.out.println("ÁGYUS: "+Units.agyusLetszam);
        System.out.println("LOVAS: "+Units.lovasLetszam);

        addBackEventStackListener();

        width = getViewport().getWorldWidth();
        height = getViewport().getWorldHeight();
        final float hatod = width/6;

        harc1 = new OneSpriteStaticActor(Assets.manager.get(Assets.SWORD_MAN));
        addActor(harc1);
        harc1.setSize(hatod*0.8f, hatod*0.8f);
        harc1.setPosition(hatod+(hatod/2)-harc1.getWidth()/2, height/2);

        minusz1 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ));
        addActor(minusz1);
        minusz1.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        minusz1.setPosition(harc1.getX(), harc1.getY()-minusz1.getHeight());
        minusz1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc1letszam - 1) >= 0){
                    harc1letszam --;
                    osszletszam --;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        plusz1 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ));
        addActor(plusz1);
        plusz1.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        plusz1.setPosition(minusz1.getX()+minusz1.getWidth(), harc1.getY()-minusz1.getHeight());
        plusz1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc1letszam + 1) <= Units.kardosLetszam){
                    harc1letszam ++;
                    osszletszam ++;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        harc2 = new OneSpriteStaticActor(Assets.manager.get(Assets.BOW_MAN));
        addActor(harc2);
        harc2.setSize(hatod*0.8f, hatod*0.8f);
        harc2.setPosition(2*hatod+(hatod/2)-harc2.getWidth()/2, height/2);
        minusz2 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ));
        addActor(minusz2);
        minusz2.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        minusz2.setPosition(harc2.getX(), harc2.getY()-minusz2.getHeight());
        minusz2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc2letszam - 1) >= 0){
                    harc2letszam --;
                    osszletszam -= 2;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        plusz2 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ));
        addActor(plusz2);
        plusz2.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        plusz2.setPosition(minusz2.getX()+plusz2.getWidth(), harc2.getY()-plusz2.getHeight());
        plusz2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc2letszam + 1) <= Units.ijjaszLetszam){
                    harc2letszam ++;
                    osszletszam += 2;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        harc3 = new OneSpriteStaticActor(Assets.manager.get(Assets.CANNON_MAN));
        addActor(harc3);
        harc3.setSize(hatod*0.8f, hatod*0.8f);
        harc3.setPosition(3*hatod+(hatod/2)-harc3.getWidth()/2, height/2);

        minusz3 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ));
        addActor(minusz3);
        minusz3.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        minusz3.setPosition(harc3.getX(), harc3.getY()-minusz3.getHeight());
        minusz3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc3letszam - 1) >= 0){
                    harc3letszam --;
                    osszletszam -= 4;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        plusz3 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ));
        addActor(plusz3);
        plusz3.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        plusz3.setPosition(minusz3.getX()+plusz3.getWidth(), harc3.getY()-plusz3.getHeight());
        plusz3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc3letszam + 1) <= Units.agyusLetszam){
                    harc3letszam ++;
                    osszletszam += 4;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        harc4 = new OneSpriteStaticActor(Assets.manager.get(Assets.HORSE_MAN));
        addActor(harc4);
        harc4.setSize(hatod*0.8f, hatod*0.8f);
        harc4.setPosition(4*hatod+(hatod/2)-harc4.getWidth()/2, height/2);

        minusz4 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ));
        addActor(minusz4);
        minusz4.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        minusz4.setPosition(harc4.getX(), harc4.getY()-minusz4.getHeight());
        minusz4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc4letszam - 1) >= 0){
                    harc4letszam --;
                    osszletszam -= 3;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        plusz4 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ));
        addActor(plusz4);
        plusz4.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        plusz4.setPosition(minusz4.getX()+plusz4.getWidth(), harc4.getY()-plusz4.getHeight());
        plusz4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc4letszam + 1) <= Units.lovasLetszam){
                    harc4letszam ++;
                    osszletszam += 3;
                    osszesites.setText(osszletszam+"");
                }
            }
        });

        osszesites = new MyLabel(osszesites+"", game.getLabelStyle(100));
        addActor(osszesites);
        osszesites.setPosition(width/2-osszesites.getWidth()/2, height/4-osszesites.getHeight()/2);

        fight = new MyButton("Fight", game.getTextButtonStyle());
        addActor(fight);
        fight.setPosition(width/2-fight.getWidth()/2, 0);
        fight.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new BluetoothScreen(game));
            }
        });

    }

    public void draw(){
        super.draw();
    }

    public void act(float delta){
        super.act(delta);
    }

}
