package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class MapActorWoodCutterStage extends MapActorStage {

    private WoodCutter mapactor;
    private OneSpriteStaticActor wood;
    private MyLabel label;

    public MapActorWoodCutterStage(MyGdxGame game, WoodCutter g) {
        super(game, g);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD_WORKER)){
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
        allRemove();
        init();

        getActorGroup().addActor(wood);
        getActorGroup().addActor(label);
    }

    @Override
    public void init() {
        super.init();

        wood = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        wood.setSize(meret/2,meret/2);
        wood.setPosition(meret/4, getViewport().getWorldHeight()/2);

        label = new MyLabel("3 / day",labelStyle(80));
        label.setAlignment(Align.center);
        label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight());

    }
}
