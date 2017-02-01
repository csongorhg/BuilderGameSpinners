package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BuildigsClasses.Bridge;
import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.waterActor;

/**
 * Created by Kicsi on 2017. 01. 28..
 */

public class MapActorWaterStage extends MapActorStage {

    private OneSpriteStaticActor water,redX;

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

            waterbuildings();

            boolean b = false;

            if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof grassActor){
                b = true;

            }
            /*else if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof Bridge ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof Bridge){
                b = false;

            }
            else if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof FishDock ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof FishDock){
                if((PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                        PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor)){
                    b = false;
                }

            }
            else if(PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof FishDock ||
                    PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof FishDock){

                if(PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof waterActor &&
                        PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof waterActor){
                    b = false;
                }
            }*/
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
            else{
                redX = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
                redX.setSize(meret/2,meret/2);
                redX.setPosition(halasz.getX(),halasz.getY());
                getActorGroup().addActor(redX);

                redX = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
                redX.setSize(meret/2,meret/2);
                redX.setPosition(hid.getX(),hid.getY());
                getActorGroup().addActor(redX);
            }

        } else { // ha ködös
            allRemove();
            MyLabel label = new MyLabel("You can't\nbuild here!\n\nYou haven't\nexplored\nthis side of\n the map yet!",labelStyle(50));
            getActorGroup().addActor(label);
            label.setAlignment(Align.center);
            label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight()/2);
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
