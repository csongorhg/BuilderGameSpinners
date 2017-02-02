package com.mygdx.game.Play;

import com.mygdx.game.MyBaseClasses.MyLabel;

/**
 * Created by Kicsi on 2017. 02. 02..
 */

public class TamadasTextActor extends MyLabel {

    public TamadasTextActor(LabelStyle style, float x, float y) {
        super("LOW ENERGY!", style);
        setPosition(x-this.getWidth()/2,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setVisible(((int) (elapsedtime * 5)) % 2 == 0);
    }

}
