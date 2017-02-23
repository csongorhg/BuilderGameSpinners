package com.mygdx.game.MyBaseClasses;

/**
 * Created by tuskeb on 2017. 02. 23..
 */

abstract public class MyTimerActor extends MyActor {
    float elapsedTimerTime = 0;
    boolean run = false;
    float interval = 1;


    public boolean isRun() {
        return run;
    }



    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }


    @Override
    public void init() {

    }

    public void start(){
        run = true;
        elapsedTimerTime =0;
    }

    public  void stop(){
        run = false;
    }

    abstract public void tick();


    @Override
    public void act(float delta) {
        super.act(delta);
        if (run) {
            elapsedTimerTime += delta;
            //System.out.println(elapsedTimerTime);
            if (elapsedTimerTime > interval) {
                tick();
                elapsedTimerTime = elapsedTimerTime - interval;
            }
        }
    }
}
