package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2017. 02. 01..
 */
public class FireAtlas extends OneSpriteAnimatedActor{
    private float dx, dy;

    public FireAtlas() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.FIRE_TEXTUREATLAS));
        setSize(128,128);
        setFps(10);
        dx = (float)Math.random()*400f-200f;
        dy = (float)Math.random()*100f-30f;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        Color c = sprite.getColor();
        if (elapsedTime/3 < 1) {
            sprite.setColor(c.r, c.g, c.b, 1 - elapsedTime/3);
            setPosition(getX() + dx*delta, getY() + dy*delta);
        } else {
            getStage().getActors().removeValue(this, true);
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setZIndex(Integer.MAX_VALUE);
    }
}
