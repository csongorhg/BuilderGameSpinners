package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.grassActor;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class MapActorGrassStage extends MapActorStage {

    private grassActor mapactor;
    private OneSpriteStaticActor favago;

    public MapActorGrassStage(MyGdxGame game, grassActor g) {
        super(game, g);
        getActorGroup().addActor(favago = new OneSpriteStaticActor(Assets.manager.get(Assets.FAVAGO)){
            @Override
            public void init() {
                super.init();
                setSize(meret/2, meret/2);
                setPosition(meret/4, getViewport().getWorldHeight()-meret/4-getWidth());
            }
        });
        mapactor = g;

        favago.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 11};
                ujepuletFeltolt(t);
            }
        });
    }

    private void ujepuletFeltolt(int t[]){
        for(int i=0; i<4; i++) PlayStage.ujepulet[i] = t[i];
    }
}
