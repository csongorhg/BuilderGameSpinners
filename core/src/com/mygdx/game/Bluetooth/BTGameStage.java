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
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BTGameStage extends BluetoothConnectedStage {

    public static MyButton myButton;

    private final float sendPositionInterval = 0.05f;
    private float lastSendPosition = 0f;
    private float time = 0f;
    BluetoothConnectedStage bluetoothConnectedStage;

    public BTGameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }


    @Override
    public void init() {
        //Gdx.input.setInputProcessor(this);
        addBackEventStackListener();

        bluetoothConnectedStage = new BluetoothConnectedStage(new ExtendViewport(1, 1, new OrthographicCamera(1, 1)), new SpriteBatch(), game) {
            @Override
            public void disconnected() {
            }

            @Override
            public void init() {

            }
        };

        int ellenfelosszletszam;
        //Itt kellene a bluetooth által kicserélt (oszletszam változóban)
        //public static int osszletszam; értéke mind két eszköznek
        //Server védekezik, kliens támad védekezőnek * 1,1 osszletszam

        ellenfelosszletszam = 10; //ide kellene az ellenfél értéke
        if (ellenfelosszletszam >= TestStage.osszletszam) {
            Statistics.lakosokszama = 0;
            Statistics.kutakszama = 0;
            Statistics.fa = 0;
            Statistics.ko = 0;
            Statistics.arany = 0;
            Statistics.kaja = 0;
        }
        else {
            //ellenfél nyersanyagainak (preferenciák) átvétele
        }

        myButton = new MyButton("", game.getTextButtonStyle());
        myButton.setPosition(200,200);
        myButton.setSize(300,300);
        addActor(myButton);

    }




    @Override
    public void act(float delta) {
        super.act(delta);
        /*time += delta;
        bluetoothConnectedStage.act();
        if (lastSendPosition + sendPositionInterval < time) {
            lastSendPosition = time;
            bluetoothConnectedStage.sendMessage("" + TestStage.osszletszam);
        }
        String m;
        while ((m = bluetoothConnectedStage.getMessage()) != null) {
            myButton.setText(m);
        }*/
    }
}
