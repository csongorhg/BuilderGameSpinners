package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.mapActorInterface.MapActorCityHallStage;
import com.mygdx.game.mapActorInterface.MapActorGrassStage;
import com.mygdx.game.mapActorInterface.MapActorStoneStage;
import com.mygdx.game.mapActorInterface.MapActorStage;
import com.mygdx.game.mapActorInterface.MapActorWoodCutterStage;
import com.mygdx.game.mapActorInterface.MapActorWoodStage;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayScreen extends MyScreen{

    protected PlayStage playStage;
    protected MapActorStage mapActorGlobalStage;
    private InputMultiplexer im;
    private MyStage bgStage;

    public static final String PREFS = "MAP";
    public static final String PREFstatistic = "STATISTIC";

    private Preferences prefstatistic = Gdx.app.getPreferences(PREFstatistic);
    private Preferences preferences = Gdx.app.getPreferences(PREFS);
    public PlayScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //háttér
        bgStage.draw();
        bgStage.act(delta);
        //háttér

        playStage.act(delta);
        playStage.draw();

        if (mapActorGlobalStage != null) {
            mapActorGlobalStage.act(delta);
            mapActorGlobalStage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void dispose() {
        playStage.dispose();
        prefstatistic.flush();
        preferences.flush();
        mapActorGlobalStage.dispose();
        super.dispose();
    }

    @Override
    public void hide() {
        prefstatistic.flush();
        preferences.flush();
        super.hide();
    }

    @Override
    public void init() {
        /*OrthographicCamera orthographicCamera = new OrthographicCamera(1280, 720);
        orthographicCamera.setToOrtho(true);*/
        mapActorGlobalStage = new MapActorStage(game, null);

        playStage = new PlayStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), spriteBatch, game){
            @Override
            public void selectMapActor(mapActor mapActor) {
                mapActorGlobalStage.dispose();
                im.removeProcessor(mapActorGlobalStage);
                mapActorGlobalStage = null;
                if (mapActor instanceof stoneActor) {
                    mapActorGlobalStage = new MapActorStoneStage(game, (stoneActor)mapActor);
                }
                else if (mapActor instanceof woodActor) {
                    mapActorGlobalStage = new MapActorWoodStage(game, (woodActor)mapActor);
                }
                else if (mapActor instanceof cityActor) {
                    mapActorGlobalStage = new MapActorCityHallStage(game, (cityActor)mapActor);
                }
                else if (mapActor instanceof grassActor) {
                    mapActorGlobalStage = new MapActorGrassStage(game, (grassActor)mapActor);
                }
                else if(mapActor instanceof WoodCutter){
                    mapActorGlobalStage = new MapActorWoodCutterStage(game, (WoodCutter)mapActor);
                }
                else if (mapActorGlobalStage == null) {
                    mapActorGlobalStage = new MapActorStage(game, null);
                }

                im.addProcessor(0,mapActorGlobalStage);
            }
        };


        //háttér
        bgStage = new MyStage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), spriteBatch, game) {

            public OneSpriteStaticActor getBackGroudActor() {
                return backGroudActor;
            }

            private OneSpriteStaticActor backGroudActor;

            @Override
            public void init() {
                r = 0;
                g = 0;
                b = 0;
            }

            @Override
            protected void resized() {

            }

            @Override
            public void act(float delta) {
                super.act(delta);
            }
        };
        //háttér vége
        GestureDetector gd;
        gd = new GestureDetector(20, 0.5f, 2, 0.15f, playStage);
        im = new InputMultiplexer(mapActorGlobalStage, gd, playStage);
        Gdx.input.setInputProcessor(im);

    }

}
