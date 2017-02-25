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




        //KARDOS-------------------------------------------
        harc1 = new OneSpriteStaticActor(Assets.manager.get(Assets.SWORD_MAN));
        addActor(harc1);
        harc1.setSize(hatod*0.8f, hatod*0.8f);
        harc1.setPosition(hatod+(hatod/2)-harc1.getWidth()/2, height/2);

        minusz1 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
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
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(Units.kardosLetszam - harc1letszam+" left");
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
                if((harc1letszam + 1) <= Units.kardosLetszam){
                    harc1letszam ++;
                    osszletszam ++;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(Units.kardosLetszam - harc1letszam+" left");
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
                if((harc1letszam - 1) >= 0){
                    osszletszam -= harc1letszam;
                    harc1letszam = 0;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(Units.kardosLetszam+" left");
                }
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
                if((harc1letszam + 1) <= Units.kardosLetszam){
                    harc1letszam = Units.kardosLetszam - harc1letszam;
                    osszletszam += harc1letszam;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit1.setText(0+" left");
                }
            }
        });
        addActor(max);

        maxunit1 = new MyLabel(Units.kardosLetszam == 0 ? "0  left" : Units.kardosLetszam+" left", game.getLabelStyle(50));
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




        //IJÁSZ-------------------------------------------
        harc2 = new OneSpriteStaticActor(Assets.manager.get(Assets.BOW_MAN));
        addActor(harc2);
        harc2.setSize(hatod*0.8f, hatod*0.8f);
        harc2.setPosition(2*hatod+(hatod/2)-harc2.getWidth()/2, height/2);
        minusz2 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
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
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(Units.ijjaszLetszam - harc2letszam+" left");
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
                if((harc2letszam + 1) <= Units.ijjaszLetszam){
                    harc2letszam ++;
                    osszletszam += 2;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(Units.ijjaszLetszam - harc2letszam+" left");
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
                if((harc2letszam - 1) >= 0){
                    osszletszam -= harc2letszam*2;
                    harc2letszam = 0;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(Units.ijjaszLetszam+" left");
                }
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
                if((harc2letszam + 1) <= Units.ijjaszLetszam){
                    harc2letszam = Units.ijjaszLetszam - harc2letszam;
                    osszletszam += harc2letszam*2;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit2.setText(0+" left");
                }
            }
        });
        addActor(max);

        maxunit2 = new MyLabel(Units.ijjaszLetszam == 0 ? "0 left" : Units.ijjaszLetszam+" left", game.getLabelStyle(50));
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




        //LOVAS-------------------------------------------
        harc3 = new OneSpriteStaticActor(Assets.manager.get(Assets.HORSE_MAN));
        addActor(harc3);
        harc3.setSize(hatod*0.8f, hatod*0.8f);
        harc3.setPosition(3*hatod+(hatod/2)- harc3.getWidth()/2, height/2);

        minusz3 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz3);
        minusz3.setSize(harc3.getWidth()/2, harc3.getWidth()/2);
        minusz3.setPosition(harc3.getX(), harc3.getY()- minusz3.getHeight());
        minusz3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc3letszam - 1) >= 0){
                    harc3letszam --;
                    osszletszam -= 3;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(Units.lovasLetszam - harc3letszam+" left");
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
                if((harc3letszam + 1) <= Units.lovasLetszam){
                    harc3letszam ++;
                    osszletszam += 3;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(Units.lovasLetszam - harc3letszam+" left");
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
                if((harc3letszam - 1) >= 0){
                    osszletszam -= harc3letszam*3;
                    harc3letszam = 0;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(Units.lovasLetszam+" left");
                }
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
                if((harc3letszam + 1) <= Units.lovasLetszam){
                    harc3letszam = Units.lovasLetszam - harc3letszam;
                    osszletszam += harc3letszam*3;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit3.setText(0+" left");
                }
            }
        });
        addActor(max);

        maxunit3 = new MyLabel(Units.lovasLetszam == 0 ? "0 left" : Units.lovasLetszam+" left", game.getLabelStyle(50));
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




        //ÁGYU-------------------------------------------
        harc4 = new OneSpriteStaticActor(Assets.manager.get(Assets.CANNON_MAN));
        addActor(harc4);
        harc4.setSize(hatod*0.8f, hatod*0.8f);
        harc4.setPosition(4*hatod+(hatod/2)- harc4.getWidth()/2, height/2);

        minusz4 = new OneSpriteStaticActor(Assets.manager.get(Assets.MINUSZ_JEL));
        addActor(minusz4);
        minusz4.setSize(harc4.getWidth()/2, harc4.getWidth()/2);
        minusz4.setPosition(harc4.getX(), harc4.getY()- minusz4.getHeight());
        minusz4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if((harc4letszam - 1) >= 0){
                    harc4letszam --;
                    osszletszam -= 4;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(Units.agyusLetszam - harc4letszam+" left");
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
                if((harc4letszam + 1) <= Units.agyusLetszam){
                    harc4letszam ++;
                    osszletszam += 4;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(Units.agyusLetszam - harc4letszam+" left");
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
                if((harc4letszam - 1) >= 0){
                    osszletszam -= harc4letszam*4;
                    harc4letszam = 0;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(Units.agyusLetszam+" left");
                }
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
                if((harc4letszam + 1) <= Units.agyusLetszam){
                    harc4letszam = Units.agyusLetszam - harc4letszam;
                    osszletszam += harc4letszam*4;
                    osszesites.setText("All forces: "+osszletszam);
                    maxunit4.setText(0+" left");
                }
            }
        });
        addActor(max);

        maxunit4 = new MyLabel(Units.agyusLetszam == 0 ? "0 left" : Units.agyusLetszam+" left", game.getLabelStyle(50));
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




        osszesites = new MyLabel(osszesites == null ? "All forces: 0" : "All forces: "+osszesites, game.getLabelStyle(100));
        addActor(osszesites);
        osszesites.setPosition(width/2-osszesites.getWidth()/2, height/4-osszesites.getHeight()/2);

        textBackground = new OneSpriteStaticActor(Assets.manager.get(Assets.TEXT_BG));
        addActor(textBackground);
        textBackground.setZIndex(osszesites.getZIndex()-1);
        textBackground.setSize(osszesites.getWidth()+osszesites.getWidth()/4,osszesites.getHeight());
        textBackground.setPosition(osszesites.getX()+osszesites.getWidth()/2 -textBackground.getWidth()/2,osszesites.getY());


        fight = new MyButton("Go Online", game.getTextButtonStyle(100));
        addActor(fight);
        fight.setPosition(width/2-fight.getWidth()/2, 0);
        fight.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ConnectionScreen(game));

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