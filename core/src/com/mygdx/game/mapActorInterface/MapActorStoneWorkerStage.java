package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorStoneWorkerStage extends MapActorStage {

    private StoneWorker mapactor;
    private OneSpriteStaticActor coin,stone;
    private MyLabel coinLabel,stoneLabel;
    private TextButton destroy;

    public MapActorStoneWorkerStage(MyGdxGame game, StoneWorker s) {
        super(game, s);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.STONE_WORKER)){
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
    }

    @Override
    public void init() {
        super.init();

        coin = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        coin.setSize(meret/2,meret/2);
        coin.setPosition(meret/4, getViewport().getWorldHeight()/2+meret+meret);

    }
}
