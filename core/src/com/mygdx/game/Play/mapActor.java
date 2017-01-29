package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

abstract public class mapActor extends Group {
    private int posArrayX,posArrayY;
    protected float mapActorWidth, mapActorHeight;
    private static float posX = 0,posY = 99*128;
    private static int n = 0;
    public boolean isFog() {
        return fog;
    }

    public void setFog(boolean fog) {

        if(fog){
            addActor(fogActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)));
            fogActor.setSize(actor.getWidth(), actor.getHeight());
            //fogActor.setZIndex(Integer.MAX_VALUE);
        }
        fogActor.setVisible(fog);
        //actor.setVisible(!fog);
        this.fog = fog;
    }

    private boolean fog = true;
    private OneSpriteStaticActor fogActor;

    public Actor getActor() {
        return actor;
    }

    private Actor actor;


    public mapActor(Actor a, int x, int y, float w, float h) {
        posArrayX = x;
        posArrayY = y;

        mapActorWidth = w;
        mapActorHeight = h;

        addActor(a);
        n++;
        //addActor(fogActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)));
        //fogActor.setColor(1f,1f,1f,1f);
        actor = a;
        setPosition(posX, posY);
        if(n == 100) {
            n = 0;
            posY -= 128;
            posX = 0;
        }
        else {
            posX += 128;
        }

    }

    public float getMapActorWidth() {
        return mapActorWidth;
    }

    public float getMapActorHeight() {
        return mapActorHeight;
    }


    public int getPosArrayX() {
        return posArrayX;
    }

    public int getPosArrayY() {
        return posArrayY;
    }


}