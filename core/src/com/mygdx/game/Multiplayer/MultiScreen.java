package com.mygdx.game.Multiplayer;

import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 02. 22..
 */

public class MultiScreen extends MyScreen {
    MultiConnectionStage multiConnectionStage;

    public MultiScreen(MyGdxGame game) {
        super(game);
        multiConnectionStage = new MultiConnectionStage(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        multiConnectionStage.act(delta);
        multiConnectionStage.draw();
    }
}
