package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Vince on 2017. 01. 28..
 */

public class MapActorHouseStage extends MapActorStage {

    private House mapactor;
    private OneSpriteStaticActor person;
    private MyLabel label;

    public MapActorHouseStage(MyGdxGame game, House h) {
        super(game, h);
        getActorGroup().addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.HOUSE)){
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

        getActorGroup().addActor(person);
        getActorGroup().addActor(label);
    }

    @Override
    public void init() {
        super.init();

        person = new OneSpriteStaticActor(Assets.manager.get(Assets.PEOPLE));
        person.setSize(meret/2,meret/2);
        person.setPosition(meret/4, getViewport().getWorldHeight()/2);

        label = new MyLabel("1 / day",labelStyle(80));
        label.setAlignment(Align.center);
        label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight());

    }
}
