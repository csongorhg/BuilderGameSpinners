package com.mygdx.game.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BuildigsClasses.Barrack;
import com.mygdx.game.BuildigsClasses.Bridge;
import com.mygdx.game.BuildigsClasses.FishDock;
import com.mygdx.game.BuildigsClasses.House;
import com.mygdx.game.BuildigsClasses.Mill;
import com.mygdx.game.BuildigsClasses.MillCircle;
import com.mygdx.game.BuildigsClasses.StoneWorker;
import com.mygdx.game.BuildigsClasses.WaterWell;
import com.mygdx.game.BuildigsClasses.WoodCutter;
import com.mygdx.game.End.EndScreen;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.Menu.MenuStage;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayingMechanism.Buildings;
import com.mygdx.game.PlayingMechanism.Statistics;
import com.mygdx.game.PlayingMechanism.TimeStepper;
import com.mygdx.game.WorldGenerate.Generator;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.Vector;


/**
 * Created by mordes on 2017.01.14..
 */
abstract public class PlayStage extends MyStage implements GestureDetector.GestureListener{

    private IngameMenu ingameMenu;

    public static mapActor[][] mapActors;

    private Generator generator;

    private int cityx = 0,cityy = 0;

    public static int mapWidth;
    public static int mapHeight;
    private int citycount = 0;

    private boolean updateFustrumNeed = true;

    //pref
    public static final String PREFS = "MAP";
    private Preferences preferences;
    private static int nap = 0;
    private static String saveMap = "";
    //prefstatistic
    private Preferences prefstatistic;
    private static String saveStatistic ="";

    public static int ujepulet[] = {0, 0, 0, 0};

    private Vector<GridPoint2> v;

    private boolean nemvolt = true, varos = false, kell = false;

    public PlayStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    abstract public void selectMapActor(mapActor mapActor);



    public void init() {

        ingameMenu = new IngameMenu(new ExtendViewport(1280, 720, new OrthographicCamera(1280,720)), getBatch(), game);

        prefstatistic = Gdx.app.getPreferences(PlayScreen.PREFstatistic);
        preferences = Gdx.app.getPreferences(PlayScreen.PREFS);


        setCameraTargetX(0);
        setCameraTargetY(0);
        setCameraTargetZoom(2);
        setCameraMoveSpeed(999999);
        setCameraZoomXY(0,0,2);
        addBackEventStackListener();




        mapWidth=50;
        mapHeight=50;
        fillArea();

        if(kell)setCameraMoveToXY(cityx*128,cityy*128-128,((OrthographicCamera)getCamera()).zoom,9999);
        else setCameraMoveToXY(cityx*128+256,(mapHeight-1-cityy)*128+128,((OrthographicCamera)getCamera()).zoom,9999);
        //kiködösítés
        int seged;

        seged = cityy;
        cityy = cityx;
        cityx = seged;

        setDefaultFog();
        fog((byte)cityx,(byte)cityy);

        if(v.size() > 0){
            for (int i = 0; i < v.size(); i++) {
                GridPoint2 p = v.get(i);
                waterFog((byte)(p.x+1),(byte)p.y);
                waterFog((byte)(p.x-1),(byte)p.y);
            }
        }



    }

    private  void statisticupdate(){
        if(!prefstatistic.getString(PlayScreen.PREFstatistic,"").equals("")){
            String[] t = prefstatistic.getString(PlayScreen.PREFstatistic).split(";");

            Statistics.legtobblakos = Integer.parseInt(t[0]);

            Statistics.lakosokszama = Integer.parseInt(t[1]);
            Statistics.fa = Integer.parseInt(t[2]);
            Statistics.ko = Integer.parseInt(t[3]);
            Statistics.arany = Integer.parseInt(t[4]);
            Statistics.kaja = Integer.parseInt(t[5]);

            Statistics.lakosokszamaValt = Integer.parseInt(t[6]);
            Statistics.faValt = Integer.parseInt(t[7]);
            Statistics.koValt = Integer.parseInt(t[8]);
            Statistics.aranyValt = Integer.parseInt(t[9]);
            Statistics.kajaValt = Integer.parseInt(t[10]);

            Statistics.epuletekszama = Integer.parseInt(t[11]);
            Statistics.kutakszama = Integer.parseInt(t[12]);
            //Statistics.lakosokszama = Integer.parseInt(t[13]);

            TimeStepper.elteltnap = Integer.parseInt(t[14]);
            TimeStepper.elteltido = Float.parseFloat(t[15]);
            System.out.println("PREF:"+TimeStepper.elteltnap);
        }
    }


    private void fillArea() {

        int[][] world;

        if(preferences.getString(PlayScreen.PREFS,"").equals("")){ //nincs világ
            generator = new Generator(mapWidth,mapHeight); //50x50-as terület
            world = generator.getWORLD();
            varos = true;
        }
        else{
            String[] sor = preferences.getString(PlayScreen.PREFS).split("#");
            world = new int[mapWidth][mapHeight];
            for (int i = 0; i < sor.length; i++) {
                String[] t = sor[i].split(";");
                for (int j = 0; j < t.length; j++) {
                    world[i][j] = Integer.parseInt(t[j]);
                }
            }
            statisticupdate();
            varos = false;
        }

        mapActors = new mapActor[mapWidth][mapHeight];




        //System.out.println(generator.toString());

        citycount = 0;

        OneSpriteStaticActor oneSpriteStaticActor = new OneSpriteStaticActor(Assets.manager.get(Assets.GRASS_BLOCK));
        oneSpriteStaticActor.setSize(128,128);

        //int i = mapActors.length-1;
        //int j;

        v = new Vector<GridPoint2>();
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                switch (world[i][j]){
                    case 0: //fű
                        mapActors[i][j] = new grassActor(i,j, 128, 128);
                        addActor(mapActors[i][j]);
                        final mapActor g = mapActors[i][j];
                        mapActors[i][j].addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                selectMapActor(g);
                            }
                        });
                        break;
                    case 1: //víz
                        mapActors[i][j] = new waterActor(i,j, 128, 128);
                        addActor(mapActors[i][j]);
                        final mapActor wa = mapActors[i][j];
                        mapActors[i][j].addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                selectMapActor(wa);
                            }
                        });
                        break;
                    case 2: //fa
                        mapActors[i][j] = new woodActor(i,j, 128, 128);
                        addActor(mapActors[i][j]);
                        final mapActor w = mapActors[i][j];
                        mapActors[i][j].addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                selectMapActor(w);
                            }
                        });
                        break;
                    case 3: //kő
                        mapActors[i][j] = new stoneActor(i,j, 128,128);
                        addActor(mapActors[i][j]);
                        final mapActor s = mapActors[i][j];
                        mapActors[i][j].addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                selectMapActor(s);
                            }
                        });
                        break;
                    case 9: //város
                        if(varos){
                            citycount++;
                            if(citycount == 4) {
                                mapActors[i][j] = new cityActor(i, j, citycount, 256, 256);
                                addActor(mapActors[i][j]);
                                final mapActor c = mapActors[i][j];
                                mapActors[i][j].addListener(new ClickListener() {
                                    @Override
                                    public void clicked(InputEvent event, float x, float y) {
                                        super.clicked(event, x, y);
                                        selectMapActor(c);
                                    }
                                });

                                cityx = j;
                                cityy = i;
                            } else {
                                mapActors[i][j] = new Bridge(i, j, 128, 128);
                                addActor(mapActors[i][j]);
                            }
                        }else {
                            mapActors[i][j] = new cityActor(i, j, citycount, 256, 256);
                            addActor(mapActors[i][j]);
                            final mapActor c = mapActors[i][j];
                            mapActors[i][j].addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    selectMapActor(c);
                                }
                            });
                            cityx = j;
                            cityy = i;
                        }
                        break;
                    case 11: //favágó
                        ujEpulet(i,j, new WoodCutter(i,j,128,128));
                        break;
                    case 12: //halasz
                        ujEpulet(i,j, new FishDock(i,j,128,128));
                        break;
                    case 13: //hid
                        ujEpulet(i,j, new Bridge(i,j,128,128));
                        v.add(new GridPoint2(i,j));
                        break;
                    case 14: //ház
                        ujEpulet(i,j, new House(i,j,128,128));
                        break;
                    case 15: //barakk
                        ujEpulet(i,j, new Barrack(i,j,128,128));
                        break;
                    case 16: //bánya
                        ujEpulet(i,j, new StoneWorker(i,j,128,128));
                        break;
                    case 17: //kut
                        ujEpulet(i,j, new WaterWell(i,j,128,128));
                        break;
                    case 18: //malom
                        ujEpulet(i,j, new Mill(i,j,128,128));
                        break;
                    case 19: //mező
                        ujEpulet(i,j, new MillCircle(i,j,128,128));
                        break;
                }
                if(mapActors[i][j].getY()<0){
                    mapActors[i][j].setPosition((mapActors[i][j].getPosArrayY())*128,(mapHeight-mapActors[i][j].getPosArrayX())*128-128);
                    if(mapActors[i][j] instanceof cityActor){
                        cityx = i;
                        cityy = j;
                        kell = true;
                    }
                }
            }
        }
    }

    private void ujEpulet(int i, int j, mapActor m){
        if(mapActors[i][j] != null)
            mapActors[i][j].remove();
        mapActors[i][j] = m;
        addActor(mapActors[i][j]);
        mapActors[i][j].setPosition((mapActors[i][j].getPosArrayY())*128,
                (mapHeight-mapActors[i][j].getPosArrayX())*128-128);  //helyes pozicionálás
        final mapActor ww = mapActors[i][j];
        mapActors[i][j].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                selectMapActor(ww);
            }
        });
    }


    private void setDefaultFog(){
        //Kezdetben minden legyen köd!
        for (int i = 0; i< mapActors.length; i++){
            for(int j = 0; j<mapActors[i].length; j++)
            {
                mapActors[i][j].setFog(true);
            }
        }
    }

    private void waterFog(byte x, byte y){
        Queue<GridPoint2> points = new Queue<GridPoint2>();

        points.addLast(new GridPoint2(x,y));

        while (points.size>0){
            GridPoint2 p = points.removeFirst();

            if (p.x<mapWidth-1 && mapActors[p.x + 1][p.y].isFog() && (mapActors[p.x+1][p.y] instanceof waterActor)) {
                points.addLast(new GridPoint2(p.x + 1, p.y));
                mapActors[p.x + 1][p.y].setFog(false);
            }
            if (p.x>0 && mapActors[p.x - 1][p.y].isFog() && (mapActors[p.x-1][p.y] instanceof waterActor)) {
                points.addLast(new GridPoint2(p.x - 1, p.y));
                mapActors[p.x - 1][p.y].setFog(false);
            }
            //System.out.println("Y: "+p.y+", mapHeight: "+mapHeight);
            //System.out.println("X: "+p.x+", mapHeight: "+mapWidth);
            if (p.y<mapHeight-1 && mapActors[p.x][p.y +1 ].isFog() && (mapActors[p.x][p.y+1] instanceof waterActor)) {
                points.addLast(new GridPoint2(p.x, p.y + 1));
                mapActors[p.x][p.y + 1].setFog(false);
            }
            if (p.y>0 && mapActors[p.x][p.y-1].isFog() && (mapActors[p.x][p.y-1] instanceof waterActor)) {
                points.addLast(new GridPoint2(p.x, p.y - 1));
                mapActors[p.x][p.y - 1].setFog(false);
            }
        }
    }

    private void fog(byte x, byte y){

        Queue<GridPoint2> points = new Queue<GridPoint2>();

        points.addLast(new GridPoint2(x,y));

        while (points.size>0){
            GridPoint2 p = points.removeFirst();
            //if(!(mapActors[p.x][p.y] instanceof waterActor) && !mapActors[p.x][p.y].isFog()){
                if (p.x<mapWidth-1 && mapActors[p.x + 1][p.y].isFog() && !(mapActors[p.x+1][p.y] instanceof waterActor)) {
                    points.addLast(new GridPoint2(p.x + 1, p.y));
                    mapActors[p.x + 1][p.y].setFog(false);
                }
                if (p.x>0 && mapActors[p.x - 1][p.y].isFog() && !(mapActors[p.x-1][p.y] instanceof waterActor)) {
                    points.addLast(new GridPoint2(p.x - 1, p.y));
                    mapActors[p.x - 1][p.y].setFog(false);
                }
                if (p.y<mapHeight-1 && mapActors[p.x][p.y +1 ].isFog() && !(mapActors[p.x][p.y+1] instanceof waterActor)) {
                    points.addLast(new GridPoint2(p.x, p.y + 1));
                    mapActors[p.x][p.y + 1].setFog(false);
                }
                if (p.y>0 && mapActors[p.x][p.y-1].isFog() && !(mapActors[p.x][p.y-1] instanceof waterActor)) {
                    points.addLast(new GridPoint2(p.x, p.y - 1));
                    mapActors[p.x][p.y - 1].setFog(false);
                }
            //}
            //System.out.println(p.x + " - " + p.y);
        }

        boolean[][] isWaterFog = new boolean[mapActors.length][mapActors[0].length];


        //System.out.println("Amit csináltam");

        for (int i = 0; i < mapActors.length; i++){
            for(int j = 1; j < mapActors[i].length - 1; j++) {
                if (mapActors[i][j + 1].isFog() && !mapActors[i][j].isFog() && !isWaterFog[i][j]) {
                    isWaterFog[i][j + 1] = true;
                    mapActors[i][j + 1].setFog(false);
                }
                else if (mapActors[i][j - 1].isFog() && !mapActors[i][j].isFog() && !isWaterFog[i][j]) {
                    isWaterFog[i][j - 1] = true;
                    mapActors[i][j - 1].setFog(false);
                }
                //System.out.print(isWaterFog[i][j] ? "1" : "0");
            }
            //System.out.println();
        }



    }

    @Override
    public void act(float delta) {
        super.act(delta);
        fixCamera();

        if(!MenuStage.music.isPlaying() && MenuStage.playing){
            MenuStage.music.stop();
            MenuStage.music.play();
        }
        //itt kezeli az eltelt időt
        TimeStepper.STEP(delta);

        if(TimeStepper.vege){
            preferences.putString(PlayScreen.PREFS,"");
            prefstatistic.putString(PlayScreen.PREFstatistic,"");
            dispose();
            game.setScreen(new EndScreen(game), false);
        }

        if (TimeStepper.elteltnap != nap) {
            tuzTorles();
            mapSave();
            statisticSave();
        }

        if (isUpdateFustrumNeed()){
            updateFrustum(1.4f);
            updateFustrumNeed = false;
        }
        if (zoomcount > 0) {
            zoomcount--;
        }

        ingameMenu.act(delta);
        if(ujepulet[0] == 1) {
            epit_e();
        }

        if (!TimeStepper.nyarvan && nemvolt){
            nemvolt = false;
            winterActors();
        }
        if(TimeStepper.nyarvan && !nemvolt ) {
            nemvolt = true;
            summerActors();
        }

        if (TimeStepper.tuzvan) {
            System.out.println("ég: "+Statistics.kutakszama);
            tuz();
        }
    }

    private void tuzTorles() {
        for (int i = 0; i < mapActors.length; i++) {
            for (int j = 0; j < mapActors[i].length; j++) {
                if (mapActors[i][j].isFire()) {
                    OneSpriteStaticActor oneSpriteStaticActor;
                    if (mapActors[i][j] instanceof FishDock) {
                        ujEpulet(i, j, new waterActor(i, j,128,128));
                        Statistics.epuletekszama--;
                        Buildings.lerombol("farm");
                    }
                    else {
                        ujEpulet(i, j, new grassActor(i, j,128,128));
                        if(mapActors[i][j] instanceof WoodCutter) {
                            Statistics.epuletekszama--;
                            Buildings.lerombol("faKitermelo");
                        }
                        else if(mapActors[i][j] instanceof StoneWorker){
                            Statistics.epuletekszama--;
                            Buildings.lerombol("banya");
                        }
                        else if(mapActors[i][j] instanceof Barrack){
                            Statistics.epuletekszama--;
                            Buildings.lerombol("kikepzo");
                        }
                        else if(mapActors[i][j] instanceof House){
                            Statistics.epuletekszama--;
                            Buildings.lerombol("haz");
                        }

                    }
                }
            }
        }
    }

    private void tuz(){
        for (int i = 0; i < mapActors.length; i++) {
            for (int j = 0; j < mapActors[i].length; j++) {
                if (!(mapActors[i][j] instanceof cityActor) && !(mapActors[i][j] instanceof WaterWell)
                        && !(mapActors[i][j] instanceof stoneActor) && !(mapActors[i][j] instanceof grassActor)
                        && !(mapActors[i][j] instanceof woodActor) && !(mapActors[i][j] instanceof waterActor)
                        && !(mapActors[i][j] instanceof Bridge) && !(mapActors[i][j] instanceof Mill)
                        && !(mapActors[i][j] instanceof MillCircle)) {
                    mapActors[i][j].setFire(TimeStepper.tuzvan);
                    TimeStepper.tuzvan = false;
                }
            }
        }
    }

    private void winterActors() {
        for (int i = 0; i < mapActors.length; i++) {
            for (int j = 0; j < mapActors[i].length; j++) {
                mapActors[i][j].setWinter();
            }
        }
    }

    private void summerActors(){
        for (int i = 0; i < mapActors.length; i++) {
            for (int j = 0; j < mapActors[i].length; j++) {
                mapActors[i][j].setSummer();
            }
        }
    }

    private void epit_e() {
            if(ujepulet[3] == 11){
                if(Buildings.epuletFejlesztes("faKitermelo")) {
                    ujEpulet(ujepulet[1], ujepulet[2], new WoodCutter(ujepulet[1], ujepulet[2],128,128));
                    Statistics.epuletekszama++;
                }

            }
            else if(ujepulet[3] == 12){
                if(Buildings.epuletFejlesztes("farm")) {
                    ujEpulet(ujepulet[1], ujepulet[2], new FishDock(ujepulet[1], ujepulet[2],128,128));
                    Statistics.epuletekszama++;
                }

            }
            else if(ujepulet[3] == 13){
                Statistics.epuletekszama++;
                if(Buildings.epuletFejlesztes("hid")){
                    ujEpulet(ujepulet[1], ujepulet[2], new Bridge(ujepulet[1], ujepulet[2],128,128));
                    if(mapActors[ujepulet[1]][ujepulet[2]] != null) {
                        //fog((byte)ujepulet[1], (byte)ujepulet[2]);
                        tovabbepit(ujepulet[1], ujepulet[2]);
                    }
                }
            }
            else if(ujepulet[3] == 14){
                if(Buildings.epuletFejlesztes("haz")){
                    Statistics.epuletekszama++;
                    ujEpulet(ujepulet[1], ujepulet[2], new House(ujepulet[1], ujepulet[2],128,128));
                }
            }
            else if(ujepulet[3] == 15){
                if(Buildings.epuletFejlesztes("kikepzo")) {
                    Statistics.epuletekszama++;
                    ujEpulet(ujepulet[1], ujepulet[2], new Barrack(ujepulet[1], ujepulet[2],128,128));
                }
            }
            else if(ujepulet[3] == 16){
                if(Buildings.epuletFejlesztes("banya")) {
                    Statistics.epuletekszama++;
                    ujEpulet(ujepulet[1], ujepulet[2], new StoneWorker(ujepulet[1], ujepulet[2],128,128));
                }
            }
            else if(ujepulet[3] == 17){
                if(Buildings.epuletFejlesztes("kut")) {
                    Statistics.kutakszama++;
                    ujEpulet(ujepulet[1], ujepulet[2], new WaterWell(ujepulet[1], ujepulet[2], 128, 128));
                }
            }
            else if(ujepulet[3] == 18) {
                if (Buildings.epuletFejlesztes("farmnagy")) {
                    Statistics.epuletekszama++;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            if (!(i == 0 && j == 0)) {
                                ujEpulet(ujepulet[1] + i, ujepulet[2] + j, new MillCircle(ujepulet[1] + i, ujepulet[2] + j, 128, 128));
                            }
                        }
                    }
                    ujEpulet(ujepulet[1], ujepulet[2], new Mill(ujepulet[1], ujepulet[2], 128, 128));
                }
                //mapActors[ujepulet[1]][ujepulet[2]].setPosition((mapActors[ujepulet[1]][ujepulet[2]].getPosArrayY())*128,(100-mapActors[ujepulet[1]][ujepulet[2]].getPosArrayX())*128-128);

            }
            selectMapActor(mapActors[ujepulet[1]][ujepulet[2]]);
        ujepulet[0] = 0;
    }

    private void tovabbepit(int x, int y){
        boolean b = true;
        if(mapActors[x][y-1] instanceof waterActor) b = true;
        else if(mapActors[x][y+1] instanceof waterActor) b = false;

        int i=1;
        do{
            if(b) ujEpulet(x, y-i, new Bridge(x, y-i,128,128));
            else ujEpulet(x, y+i, new Bridge(x, y+i,128,128));
            i++;
            waterFog((byte)(x+1),(byte)(b ? y-i : y+i));
            waterFog((byte)(x-1),(byte)(b ? y-i : y+i));
        }while(mapActors[x][(b?y-i:y+i)] instanceof waterActor);
        fog((byte)x,(byte)(b ? y-i-2 : y+i+2));

    }

    private void mapSave(){
        System.out.println("Saving game...");
        nap = TimeStepper.elteltnap;

        saveMap = "";
        for (int i = 0; i < mapActors.length; i++) {
            for (int j = 0; j < mapActors[i].length; j++) {
                saveMap += mapActors[i][j].toString()+";";
            }
            saveMap += "#";
        }
        preferences.putString(PlayScreen.PREFS,saveMap);
        preferences.flush();


    }

    private void statisticSave(){
        saveStatistic = "" ;
        saveStatistic = Statistics.legtobblakos+";"+Statistics.lakosokszama+";"+Statistics.fa+";"+Statistics.ko+";"+Statistics.arany+";"+Statistics.kaja+";"+Statistics.lakosokszamaValt+";"+Statistics.faValt+";"+Statistics.koValt+";"+Statistics.aranyValt+";"+Statistics.kajaValt+";"+Statistics.epuletekszama+";"+Statistics.kutakszama+";"+1+";"+TimeStepper.elteltnap+";"+TimeStepper.elteltido;
        prefstatistic.putString(PlayScreen.PREFstatistic,saveStatistic);
        prefstatistic.flush();
    }


    private float cameraX=0, cameraY=0, cameraZoom=0;
    public boolean isUpdateFustrumNeed(){
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (Math.abs(cameraX - c.position.x)>128/c.zoom || Math.abs(cameraY - c.position.y)>128/c.zoom || Math.abs(cameraZoom - c.zoom)>0.2){
            updateFustrumNeed = true;
            cameraX = c.position.x;
            cameraY = c.position.y;
            cameraZoom = c.zoom;
        }
        return updateFustrumNeed;
    }

    private void fixCamera(){
        OrthographicCamera c = (OrthographicCamera)getCamera();

        if (c.position.x<getViewport().getWorldWidth()/2*c.zoom){
            c.position.x = getViewport().getWorldWidth()/2*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.x>mapWidth*128-getViewport().getWorldWidth()/2*c.zoom + 256*c.zoom){
            c.position.x = mapWidth*128-getViewport().getWorldWidth()/2*c.zoom + 256*c.zoom;
            setCameraTargetX(c.position.x);
        }
        if (c.position.y< getViewport().getWorldHeight()/2*c.zoom) {
            c.position.y = getViewport().getWorldHeight()/2*c.zoom;
            setCameraTargetY(c.position.y);
        }
        if (c.position.y>(mapHeight - 1)*128-getViewport().getWorldHeight()/2*c.zoom + ingameMenu.getHatterPosition()*c.zoom){
            c.position.y = (mapHeight - 1)*128-getViewport().getWorldHeight()/2*c.zoom + ingameMenu.getHatterPosition()*c.zoom;
            setCameraTargetY(c.position.y);
        }
    }


    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        setCameraTargetX(getCameraTargetX()-deltaX*c.zoom*(getViewport().getWorldWidth()/(float)Gdx.graphics.getWidth()));
        setCameraTargetY(getCameraTargetY()+deltaY*c.zoom*(getViewport().getWorldHeight()/(float)Gdx.graphics.getHeight()));
        setCameraMoveSpeed(2048);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }


    private int zoomcount = 2;
    private float zoom;

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.error("Builder",initialDistance + " : " + distance);
        OrthographicCamera c = (OrthographicCamera) getCamera();
        if (zoomcount==0){
            zoom = c.zoom;
        }else {
            c.zoom = zoom * initialDistance / distance;
        }
        if (c.zoom<0.5f){
            c.zoom = 0.5f;
        }
        if (c.zoom>2f){
            c.zoom = 2f;
        }
        c.update();
        setCameraTargetZoom(c.zoom);
        zoomcount = 2;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public void draw() {
        super.draw();
        ingameMenu.draw();
    }

    @Override
    public void dispose() {
        ingameMenu.dispose();

        super.dispose();

    }
}
