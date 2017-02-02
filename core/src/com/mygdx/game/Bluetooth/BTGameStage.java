package com.mygdx.game.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.BluetoothConnectedStage;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BTGameStage extends BluetoothConnectedStage {

    public static MyButton myButton;
    BluetoothConnectedStage bluetoothConnectedStage;

    public BTGameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    @Override
    public void init() {
        //Gdx.input.setInputProcessor(this);
        addBackEventStackListener();

        //Itt kellene a bluetooth által kicserélt
        //public static int osszletszam; értéke mind két eszköznek

        myButton = new MyButton("", game.getTextButtonStyle());
        myButton.setPosition(200,200);
        myButton.setSize(300,300);
        addActor(myButton);


    }




    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
