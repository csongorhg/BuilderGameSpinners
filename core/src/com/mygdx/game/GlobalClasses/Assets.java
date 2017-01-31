//https://github.com/tuskeb/mester
//zene http://soundimage.org/
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
	public static final AssetDescriptor<Music> RELAXING
			= new AssetDescriptor<Music>("Music/Music.mp3", Music.class);



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
	public static final AssetDescriptor<Texture> WALL
			= new AssetDescriptor<Texture>("Menu/wall.png", Texture.class);
	public static final AssetDescriptor<Texture> GRID
			= new AssetDescriptor<Texture>("Menu/racs.png", Texture.class);
	public static final AssetDescriptor<Texture> FOG
			= new AssetDescriptor<Texture>("Tiles/kod.png", Texture.class);
	public static final AssetDescriptor<Texture> GRASS_BLOCK
			= new AssetDescriptor<Texture>("Tiles/fu.png", Texture.class);
	public static final AssetDescriptor<Texture> WATER_BLOCK
			= new AssetDescriptor<Texture>("Tiles/viz.png", Texture.class);
	public static final AssetDescriptor<Texture> TREE_BLOCK
			= new AssetDescriptor<Texture>("Tiles/fa_1.png", Texture.class);
	public static final AssetDescriptor<Texture> TREE2_BLOCK
			= new AssetDescriptor<Texture>("Tiles/fa_2.png", Texture.class);
	public static final AssetDescriptor<Texture> TREE3_BLOCK
			= new AssetDescriptor<Texture>("Tiles/fa_3.png", Texture.class);
	public static final AssetDescriptor<Texture> STONE_BLOCK
			= new AssetDescriptor<Texture>("Tiles/ko_1.png", Texture.class);
	public static final AssetDescriptor<Texture> STONE2_BLOCK
			= new AssetDescriptor<Texture>("Tiles/ko_2.png", Texture.class);

	public static final AssetDescriptor<Texture> HOUSE
			= new AssetDescriptor<Texture>("Buildings/haz.png", Texture.class);
	public static final AssetDescriptor<Texture> STONE_WORKER
			= new AssetDescriptor<Texture>("Buildings/kofejto.png", Texture.class);
	public static final AssetDescriptor<Texture> WOOD_WORKER
			= new AssetDescriptor<Texture>("Buildings/favago.png", Texture.class);
	public static final AssetDescriptor<Texture> CITY_HALL
			= new AssetDescriptor<Texture>("Buildings/var.png", Texture.class);
	public static final AssetDescriptor<Texture> MILL
			= new AssetDescriptor<Texture>("Buildings/malom.png", Texture.class);
	public static final AssetDescriptor<Texture> MILLMEZO
			= new AssetDescriptor<Texture>("Buildings/mezo.png", Texture.class);
	public static final AssetDescriptor<Texture> BRIDGE
			= new AssetDescriptor<Texture>("Buildings/hid.png", Texture.class);
	public static final AssetDescriptor<Texture> FISHDOCK
			= new AssetDescriptor<Texture>("Buildings/halasz.png", Texture.class);
	public static final AssetDescriptor<Texture> BARRACK
			= new AssetDescriptor<Texture>("Buildings/kikepzo.png", Texture.class);
	public static final AssetDescriptor<Texture> WATER_WELL
			= new AssetDescriptor<Texture>("Buildings/kut.png", Texture.class);

	public static final AssetDescriptor<Texture> BANYA
			= new AssetDescriptor<Texture>("Interface/banya.png", Texture.class);
	public static final AssetDescriptor<Texture> BARAKK
			= new AssetDescriptor<Texture>("Interface/barakk.png", Texture.class);
	public static final AssetDescriptor<Texture> ARANY
			= new AssetDescriptor<Texture>("Interface/coin.png", Texture.class);
	public static final AssetDescriptor<Texture> FAVAGO
			= new AssetDescriptor<Texture>("Interface/favago.png", Texture.class);
	public static final AssetDescriptor<Texture> HALASZ
			= new AssetDescriptor<Texture>("Interface/halasz.png", Texture.class);
	public static final AssetDescriptor<Texture> HAZ
			= new AssetDescriptor<Texture>("Interface/haz.png", Texture.class);
	public static final AssetDescriptor<Texture> KUT
			= new AssetDescriptor<Texture>("Interface/kut.png", Texture.class);
	public static final AssetDescriptor<Texture> MEAT
			= new AssetDescriptor<Texture>("Interface/meat.png", Texture.class);
	public static final AssetDescriptor<Texture> MEZO
			= new AssetDescriptor<Texture>("Interface/mezo.png", Texture.class);
	public static final AssetDescriptor<Texture> PEOPLE
			= new AssetDescriptor<Texture>("Interface/people.png", Texture.class);
	public static final AssetDescriptor<Texture> STONE
			= new AssetDescriptor<Texture>("Interface/stone.png", Texture.class);
	public static final AssetDescriptor<Texture> WOOD
			= new AssetDescriptor<Texture>("Interface/wood.png", Texture.class);
	public static final AssetDescriptor<Texture> FAHATTER
			= new AssetDescriptor<Texture>("Interface/fahatter.png", Texture.class);
	public static final AssetDescriptor<Texture> HID
			= new AssetDescriptor<Texture>("Interface/hid.png", Texture.class);
	public static final AssetDescriptor<Texture> REDX
			= new AssetDescriptor<Texture>("Interface/redX.png", Texture.class);
	public static final AssetDescriptor<Texture> DG
			= new AssetDescriptor<Texture>("Interface/downgrade.png", Texture.class);
	public static final AssetDescriptor<Texture> UG
			= new AssetDescriptor<Texture>("Interface/upgrade.png", Texture.class);

	public static final AssetDescriptor<Texture> KIJELOLES
			= new AssetDescriptor<Texture>("Tiles/kijeloles.png", Texture.class);

	public static final AssetDescriptor<Texture> FUSNOW_BLOCK
			= new AssetDescriptor<Texture>("Tiles/havas/fuSnow.png", Texture.class);



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
		manager.load(FOG);
		manager.load(GRASS_BLOCK);
		manager.load(WATER_BLOCK);
		manager.load(TREE_BLOCK);
		manager.load(TREE2_BLOCK);
		manager.load(TREE3_BLOCK);
		manager.load(STONE_BLOCK);
		manager.load(STONE2_BLOCK);
		manager.load(HOUSE);
		manager.load(STONE_WORKER);
		manager.load(WOOD_WORKER);
		manager.load(CITY_HALL);
		manager.load(BANYA);
		manager.load(BARAKK);
		manager.load(ARANY);
		manager.load(FAVAGO);
		manager.load(HALASZ);
		manager.load(HAZ);
		manager.load(KUT);
		manager.load(MEAT);
		manager.load(MEZO);
		manager.load(PEOPLE);
		manager.load(STONE);
		manager.load(WOOD);
		manager.load(FAHATTER);
		manager.load(WALL);
		manager.load(KIJELOLES);
		manager.load(MILL);
		manager.load(MILLMEZO);
		manager.load(BRIDGE);
		manager.load(BARRACK);
		manager.load(FISHDOCK);
		manager.load(WATER_WELL);
		manager.load(HID);
		manager.load(REDX);
		manager.load(DG);
		manager.load(UG);

		manager.load(FUSNOW_BLOCK);

		//ATLAS
		manager.load(EXPLOSION_TEXTUREATLAS);



		//ZENE
		manager.load(RELAXING);


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
