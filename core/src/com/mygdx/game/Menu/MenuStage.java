package com.mygdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.OtherScr.OtherScreen;
import com.mygdx.game.MyGdxGame;
import com.sun.org.apache.xerces.internal.impl.io.ASCIIReader;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton playTextButton, mapTextButton, quitTextButton;


    private TextButton.TextButtonStyle textButtonStyle;
    private OneSpriteStaticActor musicButton;


    public static Music music;
    public static boolean playing;


    private GateAtlas gateAtlas;


    private clickedButton enumButton;
    private boolean readyToChangeStage;
    private float buttontimer;


    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);

    }


    public void init()
    {

        addBackEventStackListener();



        readyToChangeStage = false;



        //időmérő gombnyomásnál
        gateAtlas = new GateAtlas();
        gateAtlas.setWidth(gateAtlas.getWidth()*(getViewport().getWorldHeight()/gateAtlas.getHeight()));
        gateAtlas.setHeight(getViewport().getWorldHeight());
        gateAtlas.setPosition(getViewport().getWorldWidth()/2 - gateAtlas.getWidth()/2,
                getViewport().getWorldHeight()/2 - gateAtlas.getHeight()/2);
        gateAtlas.setFps(0);
        addActor(gateAtlas);



        //MAPS
        mapTextButton = new MyButton("Maps", game.getTextButtonStyle());
        mapTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                readyToChangeStage = true;
                gateAtlas.setFps(5);
                buttontimer = 0.5f;
                enumButton = clickedButton.MAPS;
            }
        });
        mapTextButton.setWidth(getViewport().getWorldWidth()/4);
        mapTextButton.setPosition(-mapTextButton.getWidth()
                ,getViewport().getWorldHeight()/2 - mapTextButton.getHeight()/2);
        addActor(mapTextButton);



        //PLAYS
        playTextButton = new MyButton("Play", game.getTextButtonStyle());
        playTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                readyToChangeStage = true;
                gateAtlas.setFps(5);
                buttontimer = 0.5f;
                enumButton = clickedButton.PLAY;
            }
        });
        playTextButton.setWidth(getViewport().getWorldWidth()/4);
        playTextButton.setPosition(-playTextButton.getWidth()
                ,mapTextButton.getY() + playTextButton.getHeight()*1.5f);
        addActor(playTextButton);



        //QUIT
        quitTextButton = new MyButton("Quit", game.getTextButtonStyle());
        quitTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                readyToChangeStage = true;
                gateAtlas.setFps(5);
                buttontimer = 0.5f;
                enumButton = clickedButton.QUIT;
            }
        });
        quitTextButton.setWidth(getViewport().getWorldWidth()/4);
        quitTextButton.setPosition(-quitTextButton.getWidth()
                ,mapTextButton.getY() - playTextButton.getHeight()*1.5f);
        addActor(quitTextButton);



        //zene
        musicOnOff();

    }




    @Override
    public void act(float delta) {
        super.act(delta);

        //gombok középre igazitása
        textButtonCenter();

        //zene
        musicIsPlaying();

        //új menü
        newStageTimer();

    }

    @Override
    public void dispose() {
        super.dispose();
    }


    private void textButtonCenter() {
        if (!isButtonCenteredX(playTextButton)
                || !isButtonCenteredX(mapTextButton)
                || !isButtonCenteredX(quitTextButton)) {

            float value = 8;

            playTextButton.setX(playTextButton.getX()+value);

            mapTextButton.setX(mapTextButton.getX()+value);

            quitTextButton.setX(quitTextButton.getX()+value);

        }

    }

    private boolean isButtonCenteredX(TextButton myButton) {
        return myButton.getX() + myButton.getWidth()/2 >= getViewport().getWorldWidth()/2;
    }



    private void musicOnOff(){

        musicButton = new OneSpriteStaticActor( music.getVolume() >= 0.9f ?
                Assets.manager.get(Assets.SOUND) : Assets.manager.get(Assets.NOSOUND) );

        musicButton.setSize(128,128);

        musicButton.setPosition(getViewport().getWorldWidth()-musicButton.getWidth(),getViewport().getWorldHeight()-musicButton.getHeight());

        addActor(musicButton);

        musicButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                musicButton.remove();
                if(music.getVolume()>0.9f){
                    playing = false;
                    music.setVolume(0f);
                }
                else{
                    playing = true;
                    music.setVolume(1f);
                }
                musicOnOff();
            }
        });
    }



    private void musicIsPlaying() {
        if(playing){
            if(!music.isPlaying()){
                music.stop();
                music.play();
            }
        }
    }

    private void newStageTimer() {



        if (readyToChangeStage) {

             //mennyi idő elteltével váltson
            if (0 >= buttontimer) {
                switch (enumButton) {
                    case PLAY: game.setScreen(new OtherScreen(game)); break;
                    case MAPS: game.setScreen(new OtherScreen(game)); break;
                    case QUIT: game.dispose(); System.exit(0);
                }
            }

            buttontimer -= Gdx.graphics.getDeltaTime();

        }
    }

    private enum clickedButton {
        PLAY,
        MAPS,
        QUIT;
    }
}
