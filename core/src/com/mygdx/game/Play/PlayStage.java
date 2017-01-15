package com.mygdx.game.Play;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.WorldGenerate.Generator;

/**
 * Created by mordes on 2017.01.14..
 */
public class PlayStage extends MyStage {

    private TextButton textButton;

    private OneSpriteStaticActor grassBlock, waterBlock, treeBlock, stoneBlock;

    private Generator generator;

    private float x,y;


    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init() {

        addBackEventStackListener();


        //camera
        x= 0; y=0;



        fillArea();



        textButton = new MyButton("Vissza", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });

        //addActor(textButton);

    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        moveCamera((screenX - x) / 10 * -1, (screenY - y) / 5 );
        return super.touchDragged(screenX, screenY, pointer);
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        x = screenX;
        y = screenY;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    private void fillArea() {

        generator = new Generator(100,100); //100x100-as terület

        int[][] world = generator.getWORLD();
        float posx = 0;
        float posy = getViewport().getWorldHeight();

        OneSpriteStaticActor oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
        oneSpriteStaticActor.setSize(128,128);

        for (int[] aWorld : world) {

            posy -= oneSpriteStaticActor.getHeight();
            posx = 0;


            for (int anAWorld : aWorld) {

                switch (anAWorld) {
                    case 0:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);
                        break;
                    case 1:
                        waterBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.WATER_BLOCK));
                        waterBlock.setSize(128, 128);
                        waterBlock.setPosition(posx, posy);
                        addActor(waterBlock);
                        break;
                    case 2:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);

                        int n = Generator.vel(0,2);
                        switch (n) {
                            case 0:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                break;
                            case 1:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE2_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                break;
                            case 2:
                                treeBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.TREE3_BLOCK));
                                treeBlock.setSize(128, 128);
                                treeBlock.setPosition(posx, posy);
                                addActor(treeBlock);
                                break;
                        }

                        break;
                    case 3:
                        grassBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
                        grassBlock.setSize(128, 128);
                        grassBlock.setPosition(posx, posy);
                        addActor(grassBlock);
                        n = Generator.vel(0,1);
                        switch (n) {
                            case 0:
                                stoneBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE_BLOCK));
                                stoneBlock.setSize(128, 128);
                                stoneBlock.setPosition(posx, posy);
                                addActor(stoneBlock);
                                break;
                            case 1:
                                stoneBlock = new OneSpriteStaticActor(Assets.manager.get(Assets.STONE2_BLOCK));
                                stoneBlock.setSize(128, 128);
                                stoneBlock.setPosition(posx, posy);
                                addActor(stoneBlock);
                                break;
                        }


                }

                posx += oneSpriteStaticActor.getWidth();

            }
        }
    }


}
