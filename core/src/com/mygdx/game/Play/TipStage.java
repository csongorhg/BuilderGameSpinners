package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.WorldGenerate.Generator;

/**
 * Created by Vince on 2017. 02. 20..
 */

public class TipStage extends MyStage {

    private Array<String> tips;
    private OneSpriteStaticActor tipBackgound;
    private MyLabel tipLabel;
    private boolean visibility = false;

    public TipStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        tips = new Array<String>();
        tips.add("Water well protects your houses.");
        tips.add("Barbarians can attack you.");
        tips.add("Fish dock and Mill create food.");
        tips.add("Build bridge to cross the river.");
        tips.add("Developed by Green Burger Spinners.");
        tips.add("Do online battle.");
        tips.add("House pays 1 coin every day.");
        tips.add("You need to pay service pay to soldiers.");


        tipBackgound = new OneSpriteStaticActor(Assets.manager.get(Assets.TIP_BG));
        tipBackgound.setPosition(0,0);
        tipBackgound.setSize(getViewport().getWorldWidth()/5*2,50);
        addActor(tipBackgound);
        tipBackgound.setVisible(false);

        tipLabel = new MyLabel(tips.get(Generator.vel(0,tips.size-1)),game.getLabelStyle(25));
        tipLabel.setAlignment(Align.center);
        tipLabel.setWidth(tipBackgound.getWidth());
        tipLabel.setPosition(0,tipBackgound.getHeight()/2-tipLabel.getHeight()/2);
        addActor(tipLabel);
        tipLabel.setVisible(false);
    }

    public void Visibility(boolean b){
        visibility = b;
        tipBackgound.setVisible(b);
        if(b){
            tipLabel.setText(tips.get(Generator.vel(0,tips.size-1)));
        }
        tipLabel.setVisible(b);
    }

    public boolean getVisibility() {
        return visibility;
    }
}
