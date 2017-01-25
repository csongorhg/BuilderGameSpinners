package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2017. 01. 23..
 */

abstract public class mapActor extends Group {
    public boolean isFog() {
        return fog;
    }

    public void setFog(boolean fog) {
        fogActor.setVisible(fog);
        actor.setVisible(!fog);
        this.fog = fog;
    }

    private boolean fog = true;
    private OneSpriteStaticActor fogActor;

    public Actor getActor() {
        return actor;
    }

    private Actor actor;

    public mapActor(Actor a, int x, int y) {
        addActor(a);
        addActor(fogActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)));
        //fogActor.setColor(1f,1f,1f,1f);
        actor = a;
        fogActor.setSize(a.getWidth(), a.getHeight());
        setPosition(128*x, 128*y);
    }



}
