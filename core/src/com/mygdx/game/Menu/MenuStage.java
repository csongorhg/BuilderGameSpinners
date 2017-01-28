package com.mygdx.game.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.OtherScr.OtherScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayScreen;
import com.mygdx.game.WorldGenerate.Generator;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {

    private TextButton playTextButton, mapTextButton, quitTextButton;


    private TextButton.TextButtonStyle textButtonStyle;
    private OneSpriteStaticActor musicButton;


    public static Music music;
    public static boolean playing;


    private OneSpriteStaticActor gate;
    private OneSpriteStaticActor grid;

    private clickedButton enumButton;
    private boolean readyToChangeStage;
    private float buttontimer;



    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {

        addBackEventStackListener();

        //System.out.println(new Generator(100,100).toString());

        readyToChangeStage = false;



        //rács
        grid = new OneSpriteStaticActor(Assets.manager.get(Assets.GRID));
        grid.setWidth(grid.getWidth()*(getViewport().getWorldHeight()/ grid.getHeight()));
        grid.setHeight(getViewport().getWorldHeight());
        grid.setPosition(getViewport().getWorldWidth()/2 - grid.getWidth()/2,
                getViewport().getWorldHeight()/2 - grid.getHeight()/2);
        addActor(grid);



        //időmérő gombnyomásnál
        gate = new OneSpriteStaticActor(Assets.manager.get(Assets.GATEWALL));
        gate.setWidth(getViewport().getWorldWidth());
        gate.setHeight(getViewport().getWorldHeight());
        gate.setPosition(getViewport().getWorldWidth()/2 - gate.getWidth()/2,
                getViewport().getWorldHeight()/2 - gate.getHeight()/2);
        addActor(gate);



        //MAPS
        mapTextButton = new MyButton("New World", game.getTextButtonStyle());
        mapTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setParamsForNewStage(clickedButton.MAPS);
            }
        });
        mapTextButton.setWidth(getViewport().getWorldWidth()/5*2);
        mapTextButton.setPosition(getViewport().getWorldWidth()/2-mapTextButton.getWidth()/2
                ,getViewport().getWorldHeight()/2 - mapTextButton.getHeight()/2);
        addActor(mapTextButton);



        //PLAYS
        playTextButton = new MyButton("Play", game.getTextButtonStyle());
        playTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setParamsForNewStage(clickedButton.PLAY);
            }
        });
        playTextButton.setWidth(getViewport().getWorldWidth()/5*2);
        playTextButton.setPosition(getViewport().getWorldWidth()/2-playTextButton.getWidth()/2
                ,mapTextButton.getY() + playTextButton.getHeight()*1.5f);
        addActor(playTextButton);



        //QUIT
        quitTextButton = new MyButton("Quit", game.getTextButtonStyle());
        quitTextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setParamsForNewStage(clickedButton.QUIT);
            }
        });
        quitTextButton.setWidth(getViewport().getWorldWidth()/5*2);
        quitTextButton.setPosition(getViewport().getWorldWidth()/2-quitTextButton.getWidth()/2
                ,mapTextButton.getY() - playTextButton.getHeight()*1.5f);
        addActor(quitTextButton);



        //zene
        musicOnOff();

    }




    @Override
    public void act(float delta) {
        super.act(delta);

        //zene
        musicIsPlaying();

        //új menü
        newStage();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    //Zene
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
    //Zene



    //Új stage előtt az időzitök

    private void setParamsForNewStage(clickedButton clickedButton) {

        readyToChangeStage = true;
        buttontimer = 1;
        enumButton = clickedButton;

    }



    private void newStage() {

        if (readyToChangeStage) {

            grid.setY(grid.getY()+8);

             //mennyi idő elteltével váltson
            if (0 >= buttontimer) {
                switch (enumButton) {
                    case PLAY: game.setScreen(new PlayScreen(game)); break;
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
    //Új stage előtt az időzitök
}
