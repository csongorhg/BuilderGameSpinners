package com.mygdx.game.Menu;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;

/**
 * Created by mordes on 2017.01.11..
 */
public class GateAtlas extends OneSpriteAnimatedActor {

    public GateAtlas() {
        super(Assets.manager.get(Assets.GATE_TEXTUREATLAS));
        setFps(3);
    }

}
