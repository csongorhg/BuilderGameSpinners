package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.WorldGenerate.Generator;

import java.awt.Point;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayStage extends MyStage implements GestureDetector.GestureListener{

    private TextButton textButton;

    private mapActor[][] mapActors;

    private Generator generator;

    private int cityx,cityy;

    private int mapWidth;
    private int mapHeight;
    private int citycount;

    private boolean updateFustrumNeed = true;

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        GestureDetector gd = new GestureDetector(20, 0.5f, 2, 0.15f, this);
        InputMultiplexer im = new InputMultiplexer(gd, this);
        Gdx.input.setInputProcessor(im);
        setCameraTargetX(0);
        setCameraTargetY(0);
        setCameraTargetZoom(2);
        setCameraMoveSpeed(999999);
        setCameraZoomXY(0,0,2);
        addBackEventStackListener();


        mapWidth=100;
        mapHeight=100;
        fillArea();

        setCameraMoveToXY(cityx*128,(mapHeight-1-cityy)*128+128,((OrthographicCamera)getCamera()).zoom,9999);

        //kiködösítés
        int seged;

        seged = cityy;
        cityy = cityx;
        cityx = seged;

        fog((byte)cityx,(byte)cityy);

        System.out.println(cityx+" "+cityy);

        textButton = new MyButton("Vissza", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
    }


    private void fillArea() {

        generator = new Generator(mapWidth,mapHeight); //100x100-as terület
        mapActors = new mapActor[generator.getWORLD().length][generator.getWORLD()[0].length];

        generator = new Generator(generator.getWORLD().length,generator.getWORLD()[0].length); //100x100-as terület

        int[][] world = generator.getWORLD();
        System.out.println(generator.toString());

        citycount = 0;

        OneSpriteStaticActor oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
        oneSpriteStaticActor.setSize(128,128);

        //int i = mapActors.length-1;
        //int j;

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                switch (world[i][j]){
                    case 0:
                        mapActors[i][j] = new grassActor(i,j);
                        addActor(mapActors[i][j]);
                        break;
                    case 1:
                        mapActors[i][j] = new waterActor(i,j);
                        addActor(mapActors[i][j]);
                        cityx = j;
                        cityy = i;
                        System.out.println("bement");
                        break;
                    case 2:
                        mapActors[i][j] = new woodActor(i,j);
                        addActor(mapActors[i][j]);
                        break;
                    case 3:
                        mapActors[i][j] = new stoneActor(i,j);
                        addActor(mapActors[i][j]);
                        break;
                    case 9:
                        citycount++;
                        mapActors[i][j] = new cityActor(i,j,citycount);
                        addActor(mapActors[i][j]);
                        if(citycount == 4){
                            cityx = j;
                            cityy = i;
                        }
                        break;
                }
            }
        }

        /*for (int[] aWorld : world) {

            j = 0;

            for (int anAWorld : aWorld) {

                switch (anAWorld) {
                    case 0:
                        mapActors[j][i] = new grassActor(j,i);
                        addActor(mapActors[j][i]);
                        break;
                    case 1:
                        mapActors[j][i] = new waterActor(j,i);
                        addActor(mapActors[j][i]);
                        break;
                    case 2:
                        mapActors[j][i] = new woodActor(j,i);
                        addActor(mapActors[j][i]);
                        break;
                    case 3:
                        mapActors[j][i] = new stoneActor(j,i);
                        addActor(mapActors[j][i]);
                        break;
                    case 9:
                        citycount++;
                        mapActors[j][i] = new cityActor(j,i,citycount);
                        addActor(mapActors[j][i]);
                        if(citycount == 4){
                            cityx = j;
                            cityy = i;
                        }
                        break;
                }
                j++;

            }
            i--;
        }*/
    }

    private void fog(byte x, byte y){

        Queue<Point> points = new Queue<Point>();

        //Kezdetben minden legyen köd!
        for (int i = 0; i< mapActors.length; i++){
            for(int j = 0; j<mapActors[i].length; j++)
            {
                mapActors[i][j].setFog(true);
            }
        }

        points.addLast(new Point(x,y));

        while (points.size>0){
            Point p = points.removeFirst();
            //if(!(mapActors[p.x][p.y] instanceof waterActor) && !mapActors[p.x][p.y].isFog()){
                if (p.x<mapWidth-1 && mapActors[p.x + 1][p.y].isFog() && !(mapActors[p.x+1][p.y] instanceof waterActor)) {
                    points.addLast(new Point(p.x + 1, p.y));
                    mapActors[p.x + 1][p.y].setFog(false);
                }
                if (p.x>0 && mapActors[p.x - 1][p.y].isFog() && !(mapActors[p.x-1][p.y] instanceof waterActor)) {
                    points.addLast(new Point(p.x - 1, p.y));
                    mapActors[p.x - 1][p.y].setFog(false);
                }
                if (p.y<mapHeight-1 && mapActors[p.x][p.y +1 ].isFog() && !(mapActors[p.x][p.y+1] instanceof waterActor)) {
                    points.addLast(new Point(p.x, p.y + 1));
                    mapActors[p.x][p.y + 1].setFog(false);
                }
                if (p.y>0 && mapActors[p.x][p.y-1].isFog() && !(mapActors[p.x][p.y-1] instanceof waterActor)) {
                    points.addLast(new Point(p.x, p.y - 1));
                    mapActors[p.x][p.y - 1].setFog(false);
                }
            //}
            //System.out.println(p.x + " - " + p.y);
        }
        /*
        for (int i = 1; i< mapActors.length; i++){
            for(int j = 1; j<mapActors[i].length; j++)
            {
            }
        }
        */

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fixCamera();
        //itt kezeli az eltelt időt
        TimeStepper.STEP(delta);
        if (isUpdateFustrumNeed()){
            updateFrustum(1.4f);
            updateFustrumNeed = false;
        }
        if (zoomcount > 0) {
            zoomcount--;
        }
    }


    private float cameraX=0, cameraY=0, cameraZoom=0;
    public boolean isUpdateFustrumNeed(){
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (Math.abs(cameraX - c.position.x)>128/c.zoom || Math.abs(cameraY - c.position.y)>128/c.zoom || Math.abs(cameraZoom - c.zoom)>0.2){
            updateFustrumNeed = true;
            cameraX = c.position.x;
            cameraY = c.position.y;
            cameraZoom = c.zoom;
        }
        return updateFustrumNeed;
    }

    private void fixCamera(){
        OrthographicCamera c = (OrthographicCamera)getCamera();

        if (c.position.x<getViewport().getWorldWidth()/2*c.zoom){
            c.position.x = getViewport().getWorldWidth()/2*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.x>mapWidth*128-getViewport().getWorldWidth()/2*c.zoom){
            c.position.x = mapWidth*128-getViewport().getWorldWidth()/2*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.y < getViewport().getWorldHeight()/2*c.zoom) {
            c.position.y = getViewport().getWorldHeight()/2*c.zoom;
            setCameraTargetY(c.position.y);
        }
        if (c.position.y>mapHeight*128-getViewport().getWorldHeight()/2*c.zoom){
            c.position.y = mapHeight*128-getViewport().getWorldHeight()/2*c.zoom;
            setCameraTargetY(c.position.y);
        }
    }


    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        setCameraTargetX(getCameraTargetX()-deltaX*c.zoom*(getViewport().getWorldWidth()/(float)Gdx.graphics.getWidth()));
        setCameraTargetY(getCameraTargetY()+deltaY*c.zoom*(getViewport().getWorldHeight()/(float)Gdx.graphics.getHeight()));
        setCameraMoveSpeed(2048);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }


    private int zoomcount = 2;
    private float zoom;

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.error("Builder",initialDistance + " : " + distance);
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (zoomcount==0){
            zoom = c.zoom;
        }else {
            c.zoom = zoom * initialDistance / distance;
        }
        if (c.zoom<0.5f){
            c.zoom = 0.5f;
        }
        if (c.zoom>3f){
            c.zoom = 3f;
        }
        c.update();
        setCameraTargetZoom(c.zoom);
        zoomcount = 2;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
