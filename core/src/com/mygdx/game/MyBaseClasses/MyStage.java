package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage implements InitableInterface {
    public final MyGdxGame game;


    public MyStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch);
        this.game = game;
        init();
        //setDebugAll(true);
    }

    public void addBackEventStackListener() {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) {
                    game.setScreenBackByStackPop();
                }
                return true;
            }
        });
    }

    public Actor getLastAdded() {
        return getActors().get(getActors().size-1);
    }

    public void moveCamera(float dx, float dy) {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        c.position.add(dx, dy, 0);
        c.update();
    }

    public void setCameraZoomXY(float x, float y, float zoom)
    {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom=zoom;
        c.position.set(x,y,0);
        c.update();
    }

    private float cameraTargetX = 0;
    private float cameraTargetY = 0;
    private float cameraTargetZoom = 0;
    private float cameraMoveSpeed = 0;



    public float getCameraMoveSpeed() {
        return cameraMoveSpeed;
    }

    public void setCameraMoveSpeed(float cameraMoveSpeed) {
        this.cameraMoveSpeed = cameraMoveSpeed;
    }

    public float getCameraTargetX() {
        return cameraTargetX;
    }

    public void setCameraTargetX(float cameraTargetX) {
        this.cameraTargetX = cameraTargetX;
    }

    public float getCameraTargetY() {
        return cameraTargetY;
    }

    public void setCameraTargetY(float cameraTargetY) {
        this.cameraTargetY = cameraTargetY;
    }

    public float getCameraTargetZoom() {
        return cameraTargetZoom;
    }

    public void setCameraTargetZoom(float cameraTargetZoom) {
        this.cameraTargetZoom = cameraTargetZoom;
    }

    public void setCameraMoveToXY(float x, float y, float zoom, float speed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
    }

    public void updateFrustum(){
        Camera c = getCamera();
        for (Actor a: getActors()) {
            a.setVisible(isActorShowing(c,a));
        }
    }

    public void updateFrustum(float margin){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        float z = c.zoom;
        c.zoom *= margin;
        c.update();
        for (Actor a: getActors()) {
            a.setVisible(isActorShowing(c,a));
        }
        c.zoom = z;
        c.update();
    }

    private static boolean isActorShowing(Camera c, Actor a){
        return c.frustum.pointInFrustum(a.getX(), a.getY(), 0) || c.frustum.pointInFrustum(a.getX() + a.getWidth(), a.getY() + a.getHeight(), 0) ||
                c.frustum.pointInFrustum(a.getX() + a.getWidth(), a.getY(), 0) || c.frustum.pointInFrustum(a.getX(), a.getY() + a.getHeight(), 0);
    }

    private static boolean isActorShowing(OrthographicCamera c, Actor a, float zoom){
        float z = c.zoom;
        c.zoom *= zoom;
        c.update();
        boolean b = isActorShowing(c,a);
        c.zoom = z;
        c.update();
        return b;
        /*return isActorShowing(c, a) || c.frustum.pointInFrustum(a.getX() - margin, a.getY() - margin, 0) || c.frustum.pointInFrustum(a.getX()  + a.getWidth() + margin, a.getY() + a.getHeight() + margin, 0) ||
                c.frustum.pointInFrustum(a.getX() + a.getWidth() + margin, a.getY() - margin, 0) || c.frustum.pointInFrustum(a.getX() - margin, a.getY() + a.getHeight() + margin, 0);
    */
    }

    public void setCameraResetToCenterOfScreen()
    {
        if (getViewport() instanceof ExtendViewport) {
            OrthographicCamera c = (OrthographicCamera) getCamera();
            ExtendViewport v = (ExtendViewport) getViewport();
            c.setToOrtho(false, getViewport().getWorldWidth(), getViewport().getWorldHeight());
            c.translate((v.getWorldWidth() - v.getMinWorldWidth() / 2) < 0 ? 0 : -((v.getWorldWidth() - v.getMinWorldWidth()) / 2),
                    ((v.getWorldHeight() - v.getMinWorldHeight()) / 2) < 0 ? 0 : -((v.getWorldHeight() - v.getMinWorldHeight()) / 2));
            c.update();
        }
    }
    public void setCameraResetToLeftBottomOfScreen(){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        Viewport v = getViewport();
        setCameraZoomXY(v.getWorldWidth()/2, v.getWorldHeight()/2,1);
        c.update();

    }

    public void resize(int screenWidth, int screenHeight){
        getViewport().update(screenWidth, screenHeight, true);
        resized();
    }

    protected void resized(){
        setCameraResetToCenterOfScreen();
    };

    @Override
    public void act(float delta) {
        super.act(delta);
        OrthographicCamera c = (OrthographicCamera)getCamera();
        if (cameraTargetX!=c.position.x || cameraTargetY!=c.position.y || cameraTargetZoom!=c.zoom){
            if (Math.abs(c.position.x-cameraTargetX)<cameraMoveSpeed*delta) {
                c.position.x = (c.position.x + cameraTargetX) / 2;
            } else {
                if (c.position.x<cameraTargetX){
                    c.position.x += cameraMoveSpeed*delta;
                }else{
                    c.position.x -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.position.y-cameraTargetY)<cameraMoveSpeed*delta) {
                c.position.y = (c.position.y + cameraTargetY) / 2;
            } else {
                if (c.position.y<cameraTargetY){
                    c.position.y += cameraMoveSpeed*delta;
                }else{
                    c.position.y -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.zoom-cameraTargetZoom)<cameraMoveSpeed*delta) {
                c.zoom = (c.zoom + cameraTargetZoom) / 2;
            } else {
                if (c.zoom<cameraTargetZoom){
                    c.zoom += cameraMoveSpeed*delta;
                }else{
                    c.zoom -= cameraMoveSpeed*delta;
                }
            }
            c.update();

        }

    }
}
