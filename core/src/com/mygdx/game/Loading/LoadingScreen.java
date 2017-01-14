package com.mygdx.game.Loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Menu.MenuScreen;
import com.mygdx.game.Menu.MenuStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.GlobalClasses.*;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;


public class LoadingScreen extends MyScreen {


	private Stage stage;
	private OneSpriteStaticActor text;
	private float elapsedTime = 0;
	private OneSpriteAnimatedActor picture,picture2;
	private OneSpriteStaticActor backGroudActor;

    public LoadingScreen(MyGdxGame game) {
		super(game);
		stage = new Stage();
		//háttér
		backGroudActor = new OneSpriteStaticActor("Menu/wall.png");
		backGroudActor.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		backGroudActor.setPosition(0,0);
		stage.addActor(backGroudActor);
		//bal oldali burger
		picture = new OneSpriteAnimatedActor("Menu/load.txt")
		{
			@Override
			public void init() {
				super.init();
				setFps(12);
			}

			@Override
			public void act(float delta) {
				super.act(delta);
				setRotation(360-elapsedTime*100);
			}
		};
		stage.addActor(picture);
		picture.setSize(Gdx.graphics.getWidth()/7,Gdx.graphics.getWidth()/7);
		picture.setPosition(Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/3);
		//jobb oldali burger
		picture2 = new OneSpriteAnimatedActor("Menu/load.txt")
		{
			@Override
			public void init() {
				super.init();
				setFps(12);
			}

			@Override
			public void act(float delta) {
				super.act(delta);
				setRotation(360-elapsedTime*100);
			}
		};
		stage.addActor(picture2);
		picture2.setSize(Gdx.graphics.getWidth()/7,Gdx.graphics.getWidth()/7);
		picture2.setPosition(Gdx.graphics.getWidth()/32*25,Gdx.graphics.getHeight()/3);
		//szöveg
		text = new OneSpriteStaticActor("Menu/justszoveg.png");
		stage.addActor(text);
		text.setPosition(stage.getWidth()/2-text.getWidth()/2,stage.getHeight()/2-text.getHeight()/2);
    }


    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.act(delta);
		stage.draw();

		if (elapsedTime > 3.0 && Assets.manager.update()) {
			Assets.afterLoaded();
			MenuStage.music = Assets.manager.get(Assets.RELAXING);
			MenuStage.music.play();
			MenuStage.playing = true;
			game.setScreen(new MenuScreen(game));
		}

		elapsedTime+=delta;

	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);
	}
}