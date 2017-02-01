package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Units;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorBarrackStage extends MapActorStage {

    private Barrack mapactor;
    OneSpriteStaticActor sword, bow, cannon, horse;

    public MapActorBarrackStage(MyGdxGame game, Barrack b) {
        super(game, b);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.BARRACK)){
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
        getActorGroup().addActor(sword);
        getActorGroup().addActor(bow);
        getActorGroup().addActor(horse);
        getActorGroup().addActor(cannon);
    }

    @Override
    public void init() {
        super.init();

        bow = new OneSpriteStaticActor(Assets.manager.get(Assets.BOW_MAN));
        bow.setSize(meret/2,meret/2);
        bow.setPosition(0,getViewport().getWorldHeight()/2);
        bow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujIjjasz();
            }
        });

        sword = new OneSpriteStaticActor(Assets.manager.get(Assets.SWORD_MAN));
        sword.setSize(meret/2,meret/2);
        sword.setPosition(0, getViewport().getWorldHeight()/2+bow.getHeight());
        sword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujKardos();
            }
        });

        horse = new OneSpriteStaticActor(Assets.manager.get(Assets.HORSE_MAN));
        horse.setSize(meret/2,meret/2);
        horse.setPosition(0,getViewport().getWorldHeight()/2-horse.getHeight());
        horse.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujLovas();
            }
        });

        cannon = new OneSpriteStaticActor(Assets.manager.get(Assets.CANNON_MAN));
        cannon.setSize(meret/2,meret/2);
        cannon.setPosition(0,getViewport().getWorldHeight()/2-horse.getHeight()-cannon.getHeight());
        cannon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujAgyus();
            }
        });
    }
}
