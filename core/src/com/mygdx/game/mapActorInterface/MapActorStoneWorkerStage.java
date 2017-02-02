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

        getActorGroup().addActor(coinLabel);
        getActorGroup().addActor(coin);

        getActorGroup().addActor(stone);
        getActorGroup().addActor(stoneLabel);
    }

    @Override
    public void init() {
        super.init();

        coinLabel = new MyLabel("2 / day",labelStyle(80));
        coinLabel.setPosition(meret/2-coinLabel.getWidth()/2, getViewport().getWorldHeight()/2-meret/2);

        coin = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        coin.setSize(meret/2,meret/2);
        coin.setPosition(meret/4, getViewport().getWorldHeight()/2-meret/2+coinLabel.getHeight());



        stone = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        stone.setSize(meret/2,meret/2);
        stone.setPosition(meret/4, getViewport().getWorldHeight()/2-meret/2-stone.getHeight());

        stoneLabel = new MyLabel("3 / day", labelStyle(80));
        stoneLabel.setPosition(meret/2-stoneLabel.getWidth()/2, getViewport().getWorldHeight()/2-meret/2-stone.getHeight()-stoneLabel.getHeight());

    }
}
