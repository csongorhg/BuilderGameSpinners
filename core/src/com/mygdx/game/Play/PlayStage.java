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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.WorldGenerate.Generator;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayStage extends MyStage implements GestureDetector.GestureListener{

    private TextButton textButton;

    private OneSpriteStaticActor grassBlock, waterBlock, treeBlock, stoneBlock;
    private OneSpriteStaticActor cityhall;

    private OneSpriteStaticActor[][] worldOneSprite;

    private Generator generator;

    private float x,y;

    private int mapWidth;
    private int mapHeight;

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


        //camera
        x= 0; y=0;


        mapWidth=100;
        mapHeight=100;
        fillArea();



        //setCameraZoomXY(getViewport().getWorldWidth(),getViewport().getWorldHeight(),1);



        textButton = new MyButton("Vissza", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
/*
        addListener(new ClickListener(){
            private float dx = 0, dy = 0;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dx=0;
                dy=0;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
*/
        //addActor(textButton);
   /*     addListener(new ActorGestureListener(){

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                super.pan(event, x, y, deltaX, deltaY);
                if (Math.abs(deltaX)<8){
                    deltaX = 0;
                }
                if (Math.abs(deltaY)<8){
                    deltaY = 0;
                }
                //setCameraZoomXY(getCamera().position.x-deltaX, getCamera().position.y-deltaY,1);
                //moveCamera((screenX - x) / 10 * -1, (screenY - y) / 5 );
                //System.out.println("x=" + x + " y=" + y + " deltaX=" + deltaX + " deltaY=" + deltaY);
                setCameraTargetX(getCameraTargetX()-deltaX*2);
                setCameraTargetY(getCameraTargetY()-deltaY*2);
                setCameraMoveSpeed(4096);
                setCameraTargetZoom(2);
            }
        });*/
    }

/*
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(getCamera().position.x);



        moveCamera((screenX - x) / 10 * -1, (screenY - y) / 5 );
        return super.touchDragged(screenX, screenY, pointer);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        x = screenX;
        y = screenY;
        return super.touchDown(screenX, screenY, pointer, button);
    }
*/
    private void fillArea() {

        generator = new Generator(mapWidth,mapHeight); //100x100-as terület
        worldOneSprite = new OneSpriteStaticActor[100][100];

        generator = new Generator(100,100); //100x100-as terület

        int[][] world = generator.getWORLD();
        float posx = 0;
        float posy = getViewport().getWorldHeight();

        OneSpriteStaticActor oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
        oneSpriteStaticActor.setSize(128,128);

        int i = 0;
        int j;

        for (int[] aWorld : world) {

            posy -= oneSpriteStaticActor.getHeight();
            posx = 0;
            j = 0;

            for (int anAWorld : aWorld) {

                switch (anAWorld) {
                    case 0:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);
                        worldOneSprite[i][j] = grassBlock;
                        break;
                    case 1:
                        waterBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK));
                        waterBlock.setSize(128, 128);
                        waterBlock.setPosition(posx, posy);
                        addActor(waterBlock);
                        worldOneSprite[i][j] = waterBlock;
                        break;
                    case 2:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);


                        int n = Generator.vel(0,2);
                        switch (n) {
                            case 0:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                worldOneSprite[i][j] = treeBlock;
                                break;
                            case 1:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE2_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                worldOneSprite[i][j] = treeBlock;
                                break;
                            case 2:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE3_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                worldOneSprite[i][j] = treeBlock;
                                break;
                        }


                        break;
                    case 3:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);
                        n = Generator.vel(0,1);
                        switch (n) {
                            case 0:
                                stoneBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE_BLOCK));
                                stoneBlock.setSize(128, 128);
                                stoneBlock.setPosition(posx, posy);
                                addActor(stoneBlock);
                                worldOneSprite[i][j] = stoneBlock;
                                break;
                            case 1:
                                stoneBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE2_BLOCK));
                                stoneBlock.setSize(128, 128);
                                stoneBlock.setPosition(posx, posy);
                                addActor(stoneBlock);
                                worldOneSprite[i][j] = stoneBlock;
                                break;
                        }
                        break;
                    case 9:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);
                        cityhall = new OneSpriteStaticActor(Assets.manager.get(Assets.CITY_HALL));
                        cityhall.setSize(256, 256);
                        cityhall.setPosition(posx-128,posy);
                        //setCameraMoveToXY(posx,posy+128,1,2000);
                        setCameraZoomXY(posx,posy+128, ((OrthographicCamera)getCamera()).zoom);
                        setCameraTargetX(posx);
                        setCameraTargetY(posy+128);
                        addActor(cityhall);
                        break;
                }
                j++;
                posx += oneSpriteStaticActor.getWidth();

            }
            i++;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fixCamera();
        //itt kezeli az eltelt időt
        TimeStepper.STEP(delta);
        if (isUpdateFustrumNeed()){
            updateFrustum(1.3f);
            updateFustrumNeed = false;
        }
        if (zoomcount > 0) {
            zoomcount--;
        }
    }


    private float cameraX=0, cameraY=0, cameraZoom=0;
    public boolean isUpdateFustrumNeed(){
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (Math.abs(cameraX - c.position.x)>256 || Math.abs(cameraY - c.position.y)>256 || Math.abs(cameraZoom - c.zoom)>0.2){
            updateFustrumNeed = true;
            cameraX = c.position.x;
            cameraY = c.position.y;
            cameraZoom = c.zoom;
        }
        return updateFustrumNeed;
    }

    private void fixCamera(){
        OrthographicCamera c = (OrthographicCamera)getCamera();

        //System.out.println(c.position.x + ":" + c.position.y);
        if (c.position.x<getViewport().getWorldWidth()/2*c.zoom){
            c.position.x = getViewport().getWorldWidth()/2*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.x>mapWidth*128-getViewport().getWorldWidth()/2*c.zoom){
            c.position.x = mapWidth*128-getViewport().getWorldWidth()/2*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.y > 0) {
            c.position.y = 0;
            setCameraTargetY(c.position.y);
        }
        if (c.position.y < -mapHeight*128+getViewport().getWorldHeight()*c.zoom) {
            c.position.y = -mapHeight*128+getViewport().getWorldHeight()*c.zoom;
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
        //System.out.println("x=" + x + " y=" + y + " deltaX=" + deltaX + " deltaY=" + deltaY);
        OrthographicCamera c = (OrthographicCamera)getCamera();
        setCameraTargetX(getCameraTargetX()-deltaX*c.zoom*(getViewport().getWorldWidth()/(float)Gdx.graphics.getWidth()));
        setCameraTargetY(getCameraTargetY()+deltaY*c.zoom*(getViewport().getWorldHeight()/(float)Gdx.graphics.getHeight()));
        setCameraMoveSpeed(2048);
        //setCameraTargetZoom(1);
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
