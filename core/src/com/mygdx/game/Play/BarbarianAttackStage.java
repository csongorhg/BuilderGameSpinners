package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.mapActorInterface.MapActorStage;

/**
 * Created by tanulo on 2017. 02. 24..
 */
public class BarbarianAttackStage extends MyStage {

    private MyLabel label;
    private float elapseTime;
    public BarbarianAttackStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        label = new MyLabel("99 barbarians attacked you!", game.getLabelStyle(80, Color.RED));
        label.setPosition(0, getViewport().getWorldHeight()- MapActorStage.meretes-label.getHeight());
        addActor(label);
    }

    public void setVisibiliy(boolean b){
        if(b){
            label.setText(TimeStepper.barbarSzam+" barbarians attacked you!");
            label.setZIndex(Integer.MAX_VALUE);
        }
        label.setVisible(b);
    }
}
