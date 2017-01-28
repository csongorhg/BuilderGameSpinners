package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.waterActor;

/**
 * Created by Kicsi on 2017. 01. 28..
 */

public class MapActorWaterStage extends MapActorStage {

    public MapActorWaterStage(MyGdxGame game, waterActor g) {
        super(game, g);
        if(!g.isFog())
            getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK)){
                @Override
                public void init() {
                    super.init();
                    setSize(meret/2, meret/2);
                    setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
                    addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                        }
                    });
                }
            });
        favago.remove();
        banya.remove();
        barrak.remove();
        haz.remove();
        mezo.remove();
        kut.remove();
    }

}
