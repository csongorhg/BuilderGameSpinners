package com.mygdx.game.mapActorInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game.IngameMenu;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Play.cityActor;
import com.mygdx.game.Play.grassActor;
import com.mygdx.game.Play.mapActor;

/**
 * Created by tuskeb on 2017. 01. 26..
 */

public class MapActorStage extends MyStage {

    protected mapActor mapactor;
    protected Actor selectActor = null;
    protected final int meret = 256;
    private TextButton textButton;


    public Group getActorGroup() {
        return actorGroup;
    }

    private Group actorGroup;

    public MapActorStage(MyGdxGame game, mapActor g) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), new SpriteBatch(), game);
        if (g != null) {
            mapactor = g;
            addSelectActor(g.getStage());
        }

        addActor(actorGroup = new Group());
        actorGroup.setPosition(getViewport().getWorldWidth()-meret, 0);
        actorGroup.setSize(meret, getViewport().getWorldHeight());


        OneSpriteStaticActor oneSpriteStaticActor;
        actorGroup.addActor(oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FAHATTER)){
            @Override
            public void init() {
                super.init();
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                    }
                });
            }
        });
        oneSpriteStaticActor.setPosition(0,0);
        oneSpriteStaticActor.setSize(actorGroup.getWidth(), actorGroup.getHeight());

        init();
        actorGroup.addActor(textButton);
    }

    @Override
    public void init() {
        textButton = new MyButton("Exit game", textButtonStyle(50));
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        textButton.setSize(meret, textButton.getHeight());
        textButton.setPosition(0, 0);
    }


    public void addSelectActor(Stage stage){
        if(mapactor != null) {
            this.selectActor = new OneSpriteStaticActor(Assets.manager.get(Assets.FOG)) {
                @Override
                public void init() {
                    super.init();
                    if(!(mapactor instanceof cityActor)) {
                        setPosition(mapactor.getX(), mapactor.getY());
                        setSize(128, 128);
                    }else{
                        setPosition(mapactor.getX()-128, mapactor.getY());
                        setSize(256, 256);
                    }
                    setTouchable(Touchable.disabled);
                }
            };
            stage.addActor(selectActor);
        }
    }

    @Override
    public void dispose() {
        if (selectActor != null && selectActor.getStage()!=null) {
            selectActor.getStage().getActors().removeValue(selectActor, true);
        }
        super.dispose();
    }

    private TextButton.TextButtonStyle textButtonStyle(int a){
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
        p.setColor(0.1f,0.2f,0.2f, 0.5f);
        p.fill();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
        p.setColor(0.3f,0.5f,0.8f, 0.5f);
        p.fill();
        style.over = new TextureRegionDrawable(new TextureRegion(new Texture(p)));
        p.setColor(1f,0.5f,0.8f, 1f);
        p.fill();
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/acmeregular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter meret = new FreeTypeFontGenerator.FreeTypeFontParameter();
        meret.size = a;
        meret.characters = Assets.CHARS;
        BitmapFont font = generator.generateFont(meret);
        generator.dispose();
        style.font = font;

        return style;
    }

}
