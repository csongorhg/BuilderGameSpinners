package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyActor;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

abstract public class mapActor extends Group {
    private int posArrayX,posArrayY;
    protected float mapActorWidth, mapActorHeight;
    private static float posX = 0,posY = (PlayStage.mapHeight-1)*128;
    private static int n = 0;
    private boolean fire = false;

    private boolean fog = true;
    private OneSpriteStaticActor fogActor;

    private OneSpriteAnimatedActor fireActor;

    public boolean isFog() {
        return fog;
    }

    public void setFog(boolean fog) {


        if(fog){
            if (fogActor==null) {
                addActor(fogActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)));
                fogActor.setSize(actor.getWidth(), actor.getHeight());
            }
            //fogActor.setZIndex(Integer.MAX_VALUE);
        }
        else {
            removeActor(fogActor, true);
        }
        //if(fogActor != null)fogActor.setVisible(fog);
        //actor.setVisible(!fog);
        this.fog = fog;
    }


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

        //addActor(fogActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)));
        //fogActor.setColor(1f,1f,1f,1f);
        actor = a;
        n++;
        setPosition(posX, posY);
        if(n == PlayStage.mapWidth) {
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

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        if(fire){
            if (fireActor==null) {
                addActor(fireActor = new OneSpriteAnimatedActor(Assets.manager.get(Assets.FIRE_TEXTUREATLAS)));
                fireActor.setSize(actor.getWidth(), actor.getHeight());
            }
            //fogActor.setZIndex(Integer.MAX_VALUE);
        }
        else {
            removeActor(fireActor, true);
        }
        this.fire = fire;
    }

    abstract public void setWinter();
    abstract public void setSummer();
}
