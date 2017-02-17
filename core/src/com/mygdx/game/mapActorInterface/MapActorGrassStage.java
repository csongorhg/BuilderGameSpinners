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
import com.mygdx.game.Play.stoneActor;
import com.mygdx.game.Play.woodActor;
import com.mygdx.game.PlayingMechanism.Buildings;

/**
 * Created by Kicsi on 2017. 01. 26..
 */

public class MapActorGrassStage extends MapActorStage {

    private OneSpriteStaticActor redX, faSprite,koSprite,aranySprite,emberSprite,husSprite;
    private MyLabel faLabel,koLabel,aranyLabel,emberLabel,husLabel;
    private float meretek = (meret/2)/4;

    public MapActorGrassStage(MyGdxGame game, grassActor g) {
        super(game, g);

        if(!g.isFog()){
            standardBuildings();

            boolean bWoodCutter = false;
            for(int i=-1; i<=1; i++){
                for(int j=-1; j<=1; j++){
                    if(g.getPosArrayX()+i < PlayStage.mapWidth && g.getPosArrayX()+i > 0 && g.getPosArrayY()+j < PlayStage.mapHeight && g.getPosArrayY()+j > 0)
                        if(PlayStage.mapActors[g.getPosArrayX()+i][g.getPosArrayY()+j] instanceof woodActor) bWoodCutter = true;
                }
            }
            for(int i=-2; i<=2; i++){
                for(int j=-2; j<=2; j++){
                    if (!(g.getPosArrayX() + i > PlayStage.mapWidth - 1 || g.getPosArrayX() + i < 0 ||
                            g.getPosArrayY() + j > PlayStage.mapHeight - 1 || g.getPosArrayY() + j < 0)) {
                        if(PlayStage.mapActors[g.getPosArrayX()+i][g.getPosArrayY()+j] instanceof WoodCutter) bWoodCutter = false;

                    }
                }
            }
            if(bWoodCutter) {
                favago.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 11};
                        ujepuletFeltolt(t);
                    }
                });
                //bWoodCutter = false;
            }
            else{
                redX = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
                redX.setSize(picSize,picSize);
                redX.setPosition(favago.getX(),favago.getY());
                getActorGroup().addActor(redX);
                favago.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }

            boolean bStoneWorker = false;
            for(int i=-1; i<=1; i++){
                for(int j=-1; j<=1; j++){
                    if(g.getPosArrayX()+i < PlayStage.mapWidth && g.getPosArrayX()+i > 0 && g.getPosArrayY()+j < PlayStage.mapHeight && g.getPosArrayY()+j > 0)
                            if(PlayStage.mapActors[g.getPosArrayX()+i][g.getPosArrayY()+j] instanceof stoneActor) bStoneWorker = true;
                }
            }
            for(int i=-2; i<=2; i++){
                for(int j=-2; j<=2; j++){
                    if (!(g.getPosArrayX() + i > PlayStage.mapWidth - 1 || g.getPosArrayX() + i < 0 ||
                            g.getPosArrayY() + j > PlayStage.mapHeight - 1 || g.getPosArrayY() + j < 0)) {
                        if(PlayStage.mapActors[g.getPosArrayX()+i][g.getPosArrayY()+j] instanceof StoneWorker) bStoneWorker = false;
                    }
                }
            }
            if(bStoneWorker) {
                banya.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        int t[] = {1, mapactor.getPosArrayX(), mapactor.getPosArrayY(), 16};
                        ujepuletFeltolt(t);
                    }
                });
                //bStoneWorker = false;
            }
            else{
                redX = new OneSpriteStaticActor(Assets.manager.get(Assets.REDX));
                redX.setSize(picSize,picSize);
                redX.setPosition(banya.getX(),banya.getY());
                getActorGroup().addActor(redX);
                banya.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }

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
                redX.setSize(picSize,picSize);
                redX.setPosition(mezo.getX(),mezo.getY());
                getActorGroup().addActor(redX);
                mezo.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }
        } else {
            allRemove();
            MyLabel label = new MyLabel("You can't\nbuild here!\n\nYou haven't\nexplored\nthis side of\n the map yet!",game.getLabelStyle(50));
            getActorGroup().addActor(label);
            label.setAlignment(Align.center);
            label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight()/2);
        }

        //haz
        alapAnyagok(getViewport().getWorldHeight()/2+favago.getHeight()+banya.getHeight(),"5","0","3","6","2");
        //banya
        alapAnyagok(getViewport().getWorldHeight()/2+favago.getHeight(),"4","0","5","0","2");
        //fakitermelő
        alapAnyagok(getViewport().getWorldHeight()/2,"2","0","0","0","2");
        //kaszárnya
        alapAnyagok(getViewport().getWorldHeight()/2-barrak.getHeight(),"15","15","15","15","10");
        //malom
        alapAnyagok(getViewport().getWorldHeight()/2-barrak.getHeight()-mezo.getHeight(),"5","0","10","0","5");
        //kut
        alapAnyagok(getViewport().getWorldHeight()/2-barrak.getHeight()-mezo.getHeight()-kut.getHeight(),"5","0","5","0","0");


    }

    private void alapAnyagok(float yPos, String aranyl, String kol, String fal, String husl, String emberl){
        aranySprite = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        aranySprite.setSize(picSize/4,picSize/4);
        aranySprite.setPosition(picSize,yPos);

        koSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        koSprite.setSize(picSize/4,picSize/4);
        koSprite.setPosition(picSize,yPos+aranySprite.getHeight());

        faSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        faSprite.setSize(picSize/4,picSize/4);
        faSprite.setPosition(picSize,yPos+koSprite.getHeight()+aranySprite.getHeight());

        husSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        husSprite.setSize(picSize/4,picSize/4);
        husSprite.setPosition(picSize+picSize/2, yPos+koSprite.getHeight()+aranySprite.getHeight());

        emberSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.PEOPLE));
        emberSprite.setSize(picSize/4,picSize/4);
        emberSprite.setPosition(picSize+picSize/2, yPos+koSprite.getHeight());

        aranyLabel = new MyLabel(aranyl,game.getLabelStyle(25));
        koLabel = new MyLabel(kol,game.getLabelStyle(25));
        faLabel = new MyLabel(fal,game.getLabelStyle(25));
        husLabel = new MyLabel(husl,game.getLabelStyle(25));
        emberLabel = new MyLabel(emberl,game.getLabelStyle(25));

        aranyLabel.setPosition(picSize+aranySprite.getWidth(),yPos);
        koLabel.setPosition(picSize+koSprite.getWidth(), yPos+aranySprite.getHeight());
        faLabel.setPosition(picSize+faSprite.getWidth(), yPos+aranySprite.getHeight()+koSprite.getHeight());

        emberLabel.setPosition(picSize+picSize/2+emberSprite.getWidth(), yPos+koSprite.getHeight());
        husLabel.setPosition(picSize+picSize/2+husSprite.getWidth(), yPos+koSprite.getHeight()+emberLabel.getHeight());


        getActorGroup().addActor(aranySprite);
        getActorGroup().addActor(koSprite);
        getActorGroup().addActor(faSprite);
        getActorGroup().addActor(husSprite);
        getActorGroup().addActor(emberSprite);

        getActorGroup().addActor(aranyLabel);
        getActorGroup().addActor(koLabel);
        getActorGroup().addActor(faLabel);
        getActorGroup().addActor(husLabel);
        getActorGroup().addActor(emberLabel);
    }

}
