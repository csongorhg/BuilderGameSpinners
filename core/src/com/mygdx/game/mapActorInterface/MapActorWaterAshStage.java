package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.Ash;
import com.mygdx.game.BuildigsClasses.WaterAsh;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 02. 19..
 */

public class MapActorWaterAshStage extends MapActorStage {

    public MapActorWaterAshStage(MyGdxGame game, WaterAsh h) {
        super(game, h);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.VIZ_HAMU)) {
            @Override
            public void init() {
                super.init();
                setSize(meret / 2, meret / 2);
                setPosition(meret / 4, getViewport().getWorldHeight() - meret / 4 - getWidth());
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }
        });
        allRemove();
    }

}
