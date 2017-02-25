package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Bridge;
import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.BuildigsClasses.Ash;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.MillCircle;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WaterAsh;
import com.mygdx.game.BuildigsClasses.WaterWell;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.mapActorInterface.MapActorBarrackStage;
import com.mygdx.game.mapActorInterface.MapActorBridgeStage;
import com.mygdx.game.mapActorInterface.MapActorCityHallStage;
import com.mygdx.game.mapActorInterface.MapActorFishDockStage;
import com.mygdx.game.mapActorInterface.MapActorGrassStage;
import com.mygdx.game.mapActorInterface.MapActorAshStage;
import com.mygdx.game.mapActorInterface.MapActorHouseStage;
import com.mygdx.game.mapActorInterface.MapActorMillFieldStage;
import com.mygdx.game.mapActorInterface.MapActorMillStage;
import com.mygdx.game.mapActorInterface.MapActorStoneStage;
import com.mygdx.game.mapActorInterface.MapActorStage;
import com.mygdx.game.mapActorInterface.MapActorStoneWorkerStage;
import com.mygdx.game.mapActorInterface.MapActorWaterAshStage;
import com.mygdx.game.mapActorInterface.MapActorWaterStage;
import com.mygdx.game.mapActorInterface.MapActorWaterWellStage;
import com.mygdx.game.mapActorInterface.MapActorWoodCutterStage;
import com.mygdx.game.mapActorInterface.MapActorWoodStage;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayScreen extends MyScreen{

    protected PlayStage playStage;
    protected MapActorStage mapActorGlobalStage;
    private InputMultiplexer im;

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
        super.dispose();
        prefstatistic.flush();
        preferences.flush();
    }

    @Override
    public void hide() {
        super.hide();
        prefstatistic.flush();
        preferences.flush();
    }

    @Override
    public void init() {
        /*OrthographicCamera orthographicCamera = new OrthographicCamera(1280, 720);
        orthographicCamera.setToOrtho(true);*/
        mapActorGlobalStage = new MapActorStage(game, null);

        playStage = new PlayStage(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), spriteBatch, game){
            @Override
            public void selectMapActor(mapActor mapActor) {

                im.removeProcessor(mapActorGlobalStage);
                mapActorGlobalStage.dispose();
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
                else if(mapActor instanceof Barrack){
                    mapActorGlobalStage = new MapActorBarrackStage(game, (Barrack)mapActor);
                }
                else if(mapActor instanceof Bridge){
                    mapActorGlobalStage = new MapActorBridgeStage(game, (Bridge)mapActor);
                }
                else if(mapActor instanceof FishDock){
                    mapActorGlobalStage = new MapActorFishDockStage(game, (FishDock)mapActor);
                }
                else if(mapActor instanceof House){
                    mapActorGlobalStage = new MapActorHouseStage(game, (House)mapActor);
                }
                else if(mapActor instanceof Mill){
                    mapActorGlobalStage = new MapActorMillStage(game, (Mill)mapActor);
                }
                else if(mapActor instanceof StoneWorker){
                    mapActorGlobalStage = new MapActorStoneWorkerStage(game, (StoneWorker)mapActor);
                }
                else if(mapActor instanceof WaterWell){
                    mapActorGlobalStage = new MapActorWaterWellStage(game, (WaterWell)mapActor);
                }
                else if(mapActor instanceof waterActor){
                    mapActorGlobalStage = new MapActorWaterStage(game, (waterActor) mapActor);
                }
                else if(mapActor instanceof MillCircle){
                    mapActorGlobalStage = new MapActorMillFieldStage(game, (MillCircle)mapActor);
                }
                else if(mapActor instanceof Ash){
                    mapActorGlobalStage = new MapActorAshStage(game, (Ash)mapActor);
                }
                else if(mapActor instanceof WaterAsh){
                    mapActorGlobalStage = new MapActorWaterAshStage(game, (WaterAsh)mapActor);
                }
                else mapActorGlobalStage = new MapActorStage(game, null);

                im.addProcessor(0,mapActorGlobalStage);
            }
        };

        GestureDetector gd;
        gd = new GestureDetector(20, 0.5f, 2, 0.15f, playStage);
        im = new InputMultiplexer(mapActorGlobalStage, gd, playStage);
        Gdx.input.setInputProcessor(im);

    }

}
