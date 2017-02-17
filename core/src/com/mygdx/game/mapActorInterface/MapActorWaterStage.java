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
import com.mygdx.game.PlayingMechanism.Buildings;

/**
 * Created by Kicsi on 2017. 01. 28..
 */

public class MapActorWaterStage extends MapActorStage {

    private OneSpriteStaticActor water,redX;
    private OneSpriteStaticActor faSprite,koSprite,aranySprite,emberSprite,husSprite;
    private MyLabel faLabel,koLabel,aranyLabel,emberLabel,husLabel;
    private float meretek = (meret/2)/4;


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
            if(g.getPosArrayX() > PlayStage.mapWidth-3 || g.getPosArrayX() < 2 || g.getPosArrayY() > PlayStage.mapHeight-3 || g.getPosArrayY() < 2)
                b = false;
            else if(PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()+1] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()][g.getPosArrayY()-1] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()-1][g.getPosArrayY()] instanceof grassActor ||
                    PlayStage.mapActors[g.getPosArrayX()+1][g.getPosArrayY()] instanceof grassActor){
                b = true;
                System.out.println("LÓFASZ");
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

            //halasz
            alapAnyagok(getViewport().getWorldHeight()/2,"2","0","1","0","1");
            //híd
            alapAnyagok(getViewport().getWorldHeight()/2-hid.getHeight(),"10","50","0","0","0");

        } else { // ha ködös
            allRemove();
            MyLabel label = new MyLabel("You can't\nbuild here!\n\nYou haven't\nexplored\nthis side of\n the map yet!",game.getLabelStyle(50));
            getActorGroup().addActor(label);
            label.setAlignment(Align.center);
            label.setPosition(meret/2-label.getWidth()/2,getViewport().getWorldHeight()/2-label.getHeight()/2);
        }



    }

    private void alapAnyagok(float yPos, String aranyl, String kol, String fal, String husl, String emberl){
        aranySprite = new OneSpriteStaticActor(Assets.manager.get(Assets.ARANY));
        aranySprite.setSize(picSize/4,picSize/4);
        aranySprite.setPosition(picSize,yPos+picSize/8);

        koSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE));
        koSprite.setSize(picSize/4,picSize/4);
        koSprite.setPosition(picSize,yPos+picSize/8+aranySprite.getHeight());

        faSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.WOOD));
        faSprite.setSize(picSize/4,picSize/4);
        faSprite.setPosition(picSize,yPos+picSize/8+koSprite.getHeight()+aranySprite.getHeight());

        emberSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.PEOPLE));
        emberSprite.setSize(picSize/4,picSize/4);
        emberSprite.setPosition(picSize+picSize/2, yPos+picSize/4);

        husSprite = new OneSpriteStaticActor(Assets.manager.get(Assets.MEAT));
        husSprite.setSize(picSize/4,picSize/4);
        husSprite.setPosition(picSize+picSize/2, yPos+emberSprite.getHeight()+picSize/4);


        aranyLabel = new MyLabel(aranyl,game.getLabelStyle(25));
        koLabel = new MyLabel(kol,game.getLabelStyle(25));
        faLabel = new MyLabel(fal,game.getLabelStyle(25));
        husLabel = new MyLabel(husl,game.getLabelStyle(25));
        emberLabel = new MyLabel(emberl,game.getLabelStyle(25));

        aranyLabel.setPosition(picSize+aranySprite.getWidth(),yPos);
        koLabel.setPosition(picSize+koSprite.getWidth(), yPos+aranySprite.getHeight());
        faLabel.setPosition(picSize+faSprite.getWidth(), yPos+koSprite.getHeight()+aranySprite.getHeight());
        husLabel.setPosition(picSize+picSize/2+husSprite.getWidth(), yPos+emberSprite.getHeight()+picSize/8);
        emberLabel.setPosition(picSize+picSize/2+emberSprite.getWidth(), yPos+picSize/8);



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
