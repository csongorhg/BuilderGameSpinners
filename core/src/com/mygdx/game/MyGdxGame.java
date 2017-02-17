package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Loading.LoadingScreen;
import com.mygdx.game.MyBaseClasses.MyScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class MyGdxGame extends Game {

	public final Stack<Class> backButtonStack = new Stack();

	public Label.LabelStyle getLabelStyle() {
		return getLabelStyle(100, Color.YELLOW);
	}

	public Label.LabelStyle getLabelStyle(int size) {
		return getLabelStyle(size, Color.YELLOW);
	}

	public Label.LabelStyle getLabelStyle(int size, Color color) {
		Label.LabelStyle style;
		style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
		style.fontColor = color;
		switch (size){
			case 10:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT10);
				break;
			case 25:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT25);
				break;
			case 50:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT50);
				break;
			case 80:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT80);
				break;
			case 100:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT100);
				break;
			case 140:
				style.font = Assets.manager.get(Assets.ACMEREGULAR_FONT140);
				break;
			default:
				new Exception("Nem kezelt betűméret: " + size);
		}
		return style;
	}

	public TextButton.TextButtonStyle getTextButtonStyle() {

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ACMEREGULAR_FONT100);

		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONUP)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONOVER)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TEXTBUTTONDOWN)));

		return textButtonStyle;
	}


	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void setScreen(Screen screen) {
		setScreen(screen,true);
	}

	public void setScreenBackByStackPop(){
		if (backButtonStack.size()>1){
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this),false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		else
		{
			Gdx.app.exit();
		}
	}


	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen!=null) {
			if (pushToStack) {backButtonStack.push(prevScreen.getClass());}
			try {
				prevScreen.dispose();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		super.setScreen(screen);
	}

}
