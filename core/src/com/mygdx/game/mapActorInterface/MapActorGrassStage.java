package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WaterWell;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.PlayStage;
import com.mygdx.game.Play.grassActor;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class MapActorGrassStage extends MapActorStage {

    private OneSpriteStaticActor redX;

    public MapActorGrassStage(MyGdxGame game, grassActor g) {
        super(game, g);

        if(!g.isFog()){
            standardBuildings();

            favago.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 11};
                    ujepuletFeltolt(t);
                }
            });

            banya.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 16};
                    ujepuletFeltolt(t);
                }
            });
            haz.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 14};
                    ujepuletFeltolt(t);
                }
            });
            barrak.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 15};
                    ujepuletFeltolt(t);
                }
            });
            kut.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 17};
                    ujepuletFeltolt(t);
                }
            });
            if(mapactor.getPosArrayX() < (PlayStage.mapWidth-1) && mapactor.getPosArrayX() > 0 && mapactor.getPosArrayY() < (PlayStage.mapHeight-1) && mapactor.getPosArrayY() > 0 &&
                    PlayStage.mapActors[mapactor.getPosArrayX()+1][mapactor.getPosArrayY()] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()+1][mapactor.getPosArrayY()+1] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()+1][mapactor.getPosArrayY()-1] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()-1][mapactor.getPosArrayY()] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()-1][mapactor.getPosArrayY()-1] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()][mapactor.getPosArrayY()+1] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()][mapactor.getPosArrayY()-1] instanceof grassActor &&
                    PlayStage.mapActors[mapactor.getPosArrayX()-1][mapactor.getPosArrayY()+1] instanceof grassActor) {
                mezo.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 18};
                        ujepuletFeltolt(t);
                    }
                });
            }
            else{
                redX = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
                redX.setSize(meret/2,meret/2);
                redX.setPosition(mezo.getX(),mezo.getY());
                getActorGroup().addActor(redX);
            }
        } else {
            allRemove();
            MyLabel label = new MyLabel("You can't\nbuild here!\n\nYou haven't\nexplored\nthis side of\n the map yet!",labelStyle(50));
            getActorGroup().addActor(label);
            label.setAlignment(Align.center);
            label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight()/2);
        }
    }
}
