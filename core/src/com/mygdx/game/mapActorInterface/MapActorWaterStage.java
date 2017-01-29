package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.Bridge;
import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.waterActor;

/**
 * Created by Kicsi on 2017. 01. 28..
 */

public class MapActorWaterStage extends MapActorStage {

    private OneSpriteStaticActor water;

    public MapActorWaterStage(MyGdxGame game, waterActor g) {
        super(game, g);
        if(!g.isFog()) {
            getActorGroup().addActor(water = new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK)) {
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
            //vízre nem építhető épületek
            redXmaker(0,meret+meret/2);
            redXmaker(0,meret);
            redXmaker(0,meret/2);
            redXmaker(0,0);
            redXmaker(meret/2,meret);
            redXmaker(meret/2,meret+meret/2);

            boolean b = true;

            if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof waterActor &&
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof waterActor &&
                    PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                    PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor){
                redXmaker(meret/2,meret/2);
                redXmaker(meret/2,0);
                b = false;

            }
            else if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof Bridge ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof Bridge){

                redXmaker(meret/2,meret/2);
                redXmaker(meret/2,0);
                b = false;

            }
            else if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof FishDock ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof FishDock){
                if((PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                        PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor)){
                    redXmaker(meret/2,meret/2);
                    redXmaker(meret/2,0);
                    b = false;
                }

            }
            else if(PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof FishDock ||
                    PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof FishDock){

                if(PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                        PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor){
                    redXmaker(meret/2,meret/2);
                    redXmaker(meret/2,0);
                    b = false;
                }
            }
            if(b){
                halasz.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 12};
                        ujepuletFeltolt(t);
                    }
                });
                hid.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 13};
                        ujepuletFeltolt(t);
                    }
                });
            }

        } else { // ha ködös
            redXmaker(0,meret+meret/2);
            redXmaker(0,meret);
            redXmaker(0,meret/2);
            redXmaker(0,0);
            redXmaker(meret/2,meret);
            redXmaker(meret/2,meret+meret/2);
            redXmaker(meret/2,meret/2);
            redXmaker(meret/2,0);
        }
        /*if((PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof waterActor &&
                PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof waterActor &&
                PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor) ||
                (PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof Bridge ||
                        PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof Bridge) ||
                (PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof FishDock ||
                        PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof FishDock ||
                        PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof FishDock||
                        PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof FishDock)){
            getActorGroup().removeActor(water);*/
    }

}
