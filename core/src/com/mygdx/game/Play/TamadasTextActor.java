package com.mygdx.game.Play;

import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.PlayingMechanism.TimeStepper;

/**
 * Created by Kicsi on 2017. 02. 02..
 */

public class TamadasTextActor extends MyLabel {

    private float elapseTime;

    public TamadasTextActor(LabelStyle style, float x, float y) {
        super("You have been attacked!", style);
        this.elapseTime = 0;
        setPosition(x-this.getWidth()/2-128,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setVisible(((int) (elapsedtime * 5)) % 2 == 0);
        elapseTime += delta;
        if (elapseTime > 2) {
            TimeStepper.megtamadtak = false;
        }

    }

}
