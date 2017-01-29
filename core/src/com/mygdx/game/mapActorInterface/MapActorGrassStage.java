package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WaterWell;
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

    private OneSpriteStaticActor redX;

    public MapActorGrassStage(MyGdxGame game, grassActor g) {
        super(game, g);

        if(!g.isFog()){
            //hid halasz
            redXmaker(meret/2,meret/2);
            redXmaker(meret/2,0);

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
            if(mapactor.getPosArrayX() < 99 && mapactor.getPosArrayX() > 0 && mapactor.getPosArrayY() < 99 && mapactor.getPosArrayY() > 0 &&
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
                redXmaker(0,0);
            }
        } else {
            redXmaker(0,meret+meret/2);
            redXmaker(0,meret);
            redXmaker(0,meret/2);
            redXmaker(0,0);
            redXmaker(meret/2,meret);
            redXmaker(meret/2,meret+meret/2);
            redXmaker(meret/2,meret/2);
            redXmaker(meret/2,0);
        }
    }
}
