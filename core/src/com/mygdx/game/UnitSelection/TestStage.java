package com.mygdx.game.UnitSelection;

/**
 * Created by tanulo on 2017. 02. 22..
 */


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Units;
import com.mygdx.game.Web.ConnectionScreen;

public class TestStage extends MyStage {

    private OneSpriteStaticActor harc1, harc2, harc3, harc4;
    private OneSpriteStaticActor plusz1, minusz1, plusz2, minusz2, plusz3, minusz3, plusz4, minusz4;
    private MyLabel osszesites, maxunit1, maxunit2, maxunit3, maxunit4;
    public static int osszletszam;
    public static int harc1letszam, harc2letszam, harc3letszam, harc4letszam;
    private OneSpriteStaticActor min, max, textBackground;
    private MyButton fight;
    private float width, height;

    private int berakott_kardos, berakott_ijasz, berakott_lovas, berakott_agyu;
    private int talonban_kardos, talonban_ijasz, talonban_lovas, talonban_agyu;

    private float hatod, koz;

    public TestStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setInputProcessor(this);
    }

    public void init(){
        osszletszam = 0;

        talonban_kardos = Units.kardosLetszam;
        talonban_ijasz = Units.ijjaszLetszam;
        talonban_lovas = Units.lovasLetszam;
        talonban_agyu = Units.agyusLetszam;

        berakott_kardos = 0;
        berakott_ijasz = 0;
        berakott_lovas = 0;
        berakott_agyu = 0;

        addBackEventStackListener();

        width = getViewport().getWorldWidth();
        height = getViewport().getWorldHeight();
        hatod = height/4;
        koz = hatod/2; //2 egységes cucc közötti hely

        lovas();
        agyu();
        ijasz();
        kardos();

        fight = new MyButton("Go Online", game.getTextButtonStyle(100));
        addActor(fight);
        fight.setSize(fight.getWidth()+fight.getWidth()/4,fight.getHeight());
        fight.setPosition(width/2-fight.getWidth()/2, 10);
        fight.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ConnectionScreen(game));
            }
        });

        osszesites = new MyLabel(osszesites == null ? "All forces: 0" : "All forces: "+osszesites, game.getLabelStyle(100));
        addActor(osszesites);
        osszesites.setPosition(width/2-osszesites.getWidth()/2, (fight.getY()+fight.getHeight()+max.getY())/2-osszesites.getHeight()/2);

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(osszesites.getZIndex()-1);
        textBackground.setSize(osszesites.getWidth()+osszesites.getWidth()/4,osszesites.getHeight());
        textBackground.setPosition(osszesites.getX()+osszesites.getWidth()/2 -textBackground.getWidth()/2,osszesites.getY());
    }

    private void lovas(){
        //LOVAS-------------------------------------------
        harc3 = new OneSpriteStaticActor(Assets.manager.get(Assets.HORSE_MAN));
        addActor(harc3);
        harc3.setSize(hatod*0.8f, hatod*0.8f);
        harc3.setPosition(width/2 + koz/2, height/2+height/8);

        minusz3 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz3);
        minusz3.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        minusz3.setPosition(harc3.getX(), harc3.getY()- minusz3.getHeight());
        minusz3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((berakott_lovas - 1) >= 0){
                    talonban_lovas ++;
                    berakott_lovas --;
                    osszletszam -= 3;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(talonban_lovas+" left");
                }
            }
        });

        plusz3 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ_JEL));
        addActor(plusz3);
        plusz3.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        plusz3.setPosition(minusz3.getX()+ plusz3.getWidth(), harc3.getY()- plusz3.getHeight());
        plusz3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((talonban_lovas - 1) >= 0){
                    talonban_lovas--;
                    berakott_lovas++;
                    osszletszam += 3;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(talonban_lovas+" left");
                }
            }
        });

        min = new OneSpriteStaticActor(Assets.manager.get(Assets.MIN));
        min.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        min.setPosition(minusz3.getX(), minusz3.getY()-min.getHeight());
        min.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                talonban_lovas += berakott_lovas;
                osszletszam -= berakott_lovas*3;
                berakott_lovas = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit3.setText(talonban_lovas+" left");
            }
        });
        addActor(min);

        max = new OneSpriteStaticActor(Assets.manager.get(Assets.MAX));
        max.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        max.setPosition(plusz3.getX(), plusz3.getY()-max.getHeight());
        max.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                berakott_lovas += talonban_lovas;
                osszletszam += talonban_lovas*3;
                talonban_lovas = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit3.setText(talonban_lovas+" left");
            }
        });
        addActor(max);

        maxunit3 = new MyLabel(talonban_lovas == 0 ? "0 left" : talonban_lovas+" left", game.getLabelStyle(50));
        addActor(maxunit3);
        maxunit3.setAlignment(Align.center);
        maxunit3.setWidth(harc3.getWidth());
        maxunit3.setPosition(harc3.getX() + harc3.getWidth() / 2 - (int)maxunit3.getWidth()/2,
                harc3.getY() + harc3.getHeight());

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(maxunit3.getZIndex()-1);
        textBackground.setSize(harc3.getWidth(),maxunit3.getHeight());
        textBackground.setPosition(maxunit3.getX(),maxunit3.getY());
        //LOVAS-------------------------------------------VÉGE
    }

    private void agyu(){
        //ÁGYU-------------------------------------------
        harc4 = new OneSpriteStaticActor(Assets.manager.get(Assets.CANNON_MAN));
        addActor(harc4);
        harc4.setSize(hatod*0.8f, hatod*0.8f);
        harc4.setPosition(harc3.getX()+harc3.getWidth()+koz, height/2+height/8);

        minusz4 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz4);
        minusz4.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        minusz4.setPosition(harc4.getX(), harc4.getY()- minusz4.getHeight());
        minusz4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((berakott_agyu - 1) >= 0){
                    talonban_agyu ++;
                    berakott_agyu --;
                    osszletszam -= 4;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(talonban_agyu+" left");
                }
            }
        });

        plusz4 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ_JEL));
        addActor(plusz4);
        plusz4.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        plusz4.setPosition(minusz4.getX()+ plusz4.getWidth(), harc4.getY()- plusz4.getHeight());
        plusz4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((talonban_agyu - 1) >= 0){
                    talonban_agyu--;
                    berakott_agyu++;
                    osszletszam += 4;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(talonban_agyu+" left");
                }
            }
        });
        min = new OneSpriteStaticActor(Assets.manager.get(Assets.MIN));
        min.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        min.setPosition(minusz4.getX(), minusz4.getY()-min.getHeight());
        min.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                talonban_agyu += berakott_agyu;
                osszletszam -= berakott_agyu*4;
                berakott_agyu = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit4.setText(talonban_agyu+" left");
            }
        });
        addActor(min);

        max = new OneSpriteStaticActor(Assets.manager.get(Assets.MAX));
        max.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        max.setPosition(plusz4.getX(), plusz4.getY()-max.getHeight());
        max.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                berakott_agyu += talonban_agyu;
                osszletszam += talonban_agyu*4;
                talonban_agyu = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit4.setText(talonban_agyu+" left");
            }
        });
        addActor(max);

        maxunit4 = new MyLabel(talonban_agyu == 0 ? "0 left" : talonban_agyu+" left", game.getLabelStyle(50));
        addActor(maxunit4);
        maxunit4.setAlignment(Align.center);
        maxunit4.setWidth(harc4.getWidth());
        maxunit4.setPosition(harc4.getX() + harc4.getWidth() / 2 - (int)maxunit4.getWidth()/2,
                harc4.getY() + harc4.getHeight());

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(maxunit4.getZIndex()-1);
        textBackground.setSize(harc4.getWidth(),maxunit4.getHeight());
        textBackground.setPosition(maxunit4.getX(),maxunit4.getY());
        //ÁGYU-------------------------------------------VÉGE
    }

    private void ijasz(){
        //IJÁSZ-------------------------------------------
        harc2 = new OneSpriteStaticActor(Assets.manager.get(Assets.BOW_MAN));
        addActor(harc2);
        harc2.setSize(hatod*0.8f, hatod*0.8f);
        harc2.setPosition(width/2-koz/2-harc2.getWidth(), height/2+height/8);
        minusz2 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz2);
        minusz2.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        minusz2.setPosition(harc2.getX(), harc2.getY()-minusz2.getHeight());
        minusz2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((berakott_ijasz - 1) >= 0){
                    talonban_ijasz ++;
                    berakott_ijasz --;
                    osszletszam -= 2;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(talonban_ijasz+" left");
                }
            }
        });

        plusz2 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ_JEL));
        addActor(plusz2);
        plusz2.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        plusz2.setPosition(minusz2.getX()+plusz2.getWidth(), harc2.getY()-plusz2.getHeight());
        plusz2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((talonban_ijasz - 1) >= 0){
                    talonban_ijasz--;
                    berakott_ijasz++;
                    osszletszam += 2;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(talonban_ijasz+" left");
                }
            }
        });

        min = new OneSpriteStaticActor(Assets.manager.get(Assets.MIN));
        min.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        min.setPosition(minusz2.getX(), minusz2.getY()-min.getHeight());
        min.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                talonban_ijasz += berakott_ijasz;
                osszletszam -= berakott_ijasz*2;
                berakott_ijasz = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit2.setText(talonban_ijasz+" left");
            }
        });
        addActor(min);

        max = new OneSpriteStaticActor(Assets.manager.get(Assets.MAX));
        max.setSize(harc2.getWidth()/2, harc2.getWidth()/2);
        max.setPosition(plusz2.getX(), plusz2.getY()-max.getHeight());
        max.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                berakott_ijasz += talonban_ijasz;
                osszletszam += talonban_ijasz*2;
                talonban_ijasz = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit2.setText(talonban_ijasz+" left");
            }
        });
        addActor(max);

        maxunit2 = new MyLabel(talonban_ijasz == 0 ? "0 left" : talonban_ijasz+" left", game.getLabelStyle(50));
        addActor(maxunit2);
        maxunit2.setAlignment(Align.center);
        maxunit2.setWidth(harc2.getWidth());
        maxunit2.setPosition(harc2.getX() + harc2.getWidth() / 2 - (int)maxunit2.getWidth()/2,
                harc2.getY() + harc2.getHeight());

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(maxunit2.getZIndex()-1);
        textBackground.setSize(harc2.getWidth(),maxunit2.getHeight());
        textBackground.setPosition(maxunit2.getX(),maxunit2.getY());
        //IJÁSZ-------------------------------------------VÉGE
    }

    private void kardos(){
        //KARDOS-------------------------------------------
        harc1 = new OneSpriteStaticActor(Assets.manager.get(Assets.SWORD_MAN));
        addActor(harc1);
        harc1.setSize(hatod*0.8f, hatod*0.8f);
        harc1.setPosition(harc2.getX()-koz-harc1.getWidth(), height/2+height/8);

        minusz1 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz1);
        minusz1.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        minusz1.setPosition(harc1.getX(), harc1.getY()-minusz1.getHeight());
        minusz1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((berakott_kardos - 1) >= 0){
                    berakott_kardos --;
                    talonban_kardos ++;
                    osszletszam --;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(talonban_kardos+" left");
                }
            }
        });

        plusz1 = new OneSpriteStaticActor(Assets.manager.get(Assets.PLUSZ_JEL));
        addActor(plusz1);
        plusz1.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        plusz1.setPosition(minusz1.getX()+minusz1.getWidth(), harc1.getY()-minusz1.getHeight());
        plusz1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((talonban_kardos - 1) >= 0){
                    berakott_kardos ++;
                    talonban_kardos --;
                    osszletszam ++;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(talonban_kardos+" left");
                }
            }
        });

        min = new OneSpriteStaticActor(Assets.manager.get(Assets.MIN));
        min.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        min.setPosition(minusz1.getX(), minusz1.getY()-min.getHeight());
        min.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                talonban_kardos += berakott_kardos;
                osszletszam -= berakott_kardos;
                berakott_kardos = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit1.setText(talonban_kardos+" left");

            }
        });
        addActor(min);

        max = new OneSpriteStaticActor(Assets.manager.get(Assets.MAX));
        max.setSize(harc1.getWidth()/2, harc1.getWidth()/2);
        max.setPosition(plusz1.getX(), plusz1.getY()-max.getHeight());
        max.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                berakott_kardos += talonban_kardos;
                osszletszam += talonban_kardos;
                talonban_kardos = 0;
                osszesites.setText("All forces: "+osszletszam);
                maxunit1.setText(talonban_kardos+" left");
            }
        });
        addActor(max);

        maxunit1 = new MyLabel(talonban_kardos == 0 ? "0  left" : talonban_kardos+" left", game.getLabelStyle(50));
        addActor(maxunit1);
        maxunit1.setAlignment(Align.center);
        maxunit1.setWidth(harc1.getWidth());
        maxunit1.setPosition(harc1.getX() + harc1.getWidth() / 2 - (int)maxunit1.getWidth()/2,
                harc1.getY() + harc1.getHeight());

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(maxunit1.getZIndex()-1);
        textBackground.setSize(harc1.getWidth(),maxunit1.getHeight());
        textBackground.setPosition(maxunit1.getX(),maxunit1.getY());
        //KARDOS-------------------------------------------VÉGE
    }

    public void draw(){
        super.draw();
    }

    public void act(float delta){
        super.act(delta);
    }

}