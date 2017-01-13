//https://github.com/tuskeb/mester
package com.mygdx.game.GlobalClasses;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {

	public static AssetManager manager;
	public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

	//FONT
	static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
	static {
		fontParameter.fontFileName = "Font/acmeregular.ttf";
		fontParameter.fontParameters.size = 100;
		fontParameter.fontParameters.characters = CHARS;
		fontParameter.fontParameters.color = Color.WHITE;
	}
	public static final AssetDescriptor<BitmapFont> ACMEREGULAR_FONT
			= new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);



	//MUSIC
	//http://www.bensound.com/royalty-free-music/track/relaxing
	public static final AssetDescriptor<Music> HAPPYROCK
			= new AssetDescriptor<Music>("Music/bensound-happyrock.mp3", Music.class);
	public static final AssetDescriptor<Music> RELAXING
			= new AssetDescriptor<Music>("Music/bensound-relaxing.mp3", Music.class);



	//ATLAS
	public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTUREATLAS
			= new AssetDescriptor<TextureAtlas>("Explosion/explosion.atlas", TextureAtlas.class);



	//TEXTURE
	public static final AssetDescriptor<Texture> TEST_TEXTURE
			= new AssetDescriptor<Texture>("TestImg/ratyitutu.png", Texture.class);
	public static final AssetDescriptor<Texture> SOUND
			= new AssetDescriptor<Texture>("Menu/sound.png", Texture.class);
	public static final AssetDescriptor<Texture> NOSOUND
			= new AssetDescriptor<Texture>("Menu/nosound.png", Texture.class);
	public static final AssetDescriptor<Texture> GATEWALL
			= new AssetDescriptor<Texture>("Menu/gatewall.png", Texture.class);
	public static final AssetDescriptor<Texture> GRID
			= new AssetDescriptor<Texture>("Menu/racs.png", Texture.class);



    public static void prepare() {
		manager = new AssetManager();
		Texture.setAssetManager(manager);
	}

	public static void load() {

		FileHandleResolver resolver = new InternalFileHandleResolver();

		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));



		//TEXTURA
		manager.load(TEST_TEXTURE);
		manager.load(SOUND);
		manager.load(NOSOUND);
		manager.load(GATEWALL);
		manager.load(GRID);



		//ATLAS
		manager.load(EXPLOSION_TEXTUREATLAS);



		//ZENE
		manager.load(RELAXING);
		manager.load(HAPPYROCK);


		//BETÜ
		manager.load(ACMEREGULAR_FONT);
	}

    public static void afterLoaded() {
        //manager.get(MUSIC).setLooping(true);
    }

	public static void unload() {
		manager.dispose();
	}


}
