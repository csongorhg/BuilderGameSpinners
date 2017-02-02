package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Buildings;
import com.mygdx.game.PlayingMechanism.Units;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorBarrackStage extends MapActorStage {

    private Barrack mapactor;
    OneSpriteStaticActor sword, bow, cannon, horse;
    private AnyagokStage kard,ij,lo,agyu;

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

        kard = new AnyagokStage(getViewport(),125,-28-(128*1)+128/2,Units.kardosKoltseg[1],Units.kardosKoltseg[2],Units.kardosKoltseg[3],0,Units.kardosKoltseg[0]);
        kard.setSize(100,100);
        kard.setPosition(0,0);
        addActor(kard);

        ij = new AnyagokStage(getViewport(),125,-28-(128*2)+128/2,Units.ijjaszKoltseg[1],Units.ijjaszKoltseg[2],Units.ijjaszKoltseg[3],0,Units.ijjaszKoltseg[0]);
        ij.setSize(100,100);
        ij.setPosition(0,0);
        addActor(ij);

        lo = new AnyagokStage(getViewport(),125,-28-(128*3)+128/2,Units.lovasKoltseg[1],Units.lovasKoltseg[2],Units.lovasKoltseg[3],0,Units.lovasKoltseg[0]);
        lo.setSize(100,100);
        lo.setPosition(0,0);
        addActor(lo);

        agyu = new AnyagokStage(getViewport(),125,-28-(128*4)+128/2,Units.agyusKoltseg[1],Units.agyusKoltseg[2],Units.agyusKoltseg[3],0,Units.agyusKoltseg[0]);
        agyu.setSize(100,100);
        agyu.setPosition(0,0);
        addActor(agyu);
        
        bow = new OneSpriteStaticActor(Assets.manager.get(Assets.BOW_MAN));
        bow.setSize(meret/2,meret/2);
        bow.setPosition(0,getViewport().getWorldHeight()/2-meret/3);
        bow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujIjjasz();
            }
        });

        sword = new OneSpriteStaticActor(Assets.manager.get(Assets.SWORD_MAN));
        sword.setSize(meret/2,meret/2);
        sword.setPosition(0, getViewport().getWorldHeight()/2-meret/3+bow.getHeight());
        sword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujKardos();
            }
        });

        horse = new OneSpriteStaticActor(Assets.manager.get(Assets.HORSE_MAN));
        horse.setSize(meret/2,meret/2);
        horse.setPosition(0,getViewport().getWorldHeight()/2-meret/3-horse.getHeight());
        horse.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujLovas();
            }
        });

        cannon = new OneSpriteStaticActor(Assets.manager.get(Assets.CANNON_MAN));
        cannon.setSize(meret/2,meret/2);
        cannon.setPosition(0,getViewport().getWorldHeight()/2-meret/3-horse.getHeight()-cannon.getHeight());
        cannon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Units.ujAgyus();
            }
        });
    }
}
