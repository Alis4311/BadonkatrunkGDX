package Screens;

import Map.Map;
import Vehicles.Vehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.chris.badonkatrunk.Badonkatrunk;

/**
 *
 *
 * @author Christoffer Book, Tim Normark, Peder Nilsson, Daniel Rosdahl
 */
public class GameScreen implements Screen{
    OrthographicCamera camera;
    Vehicle vehicle;
    Map level;
    SpriteBatch batch;
    ShapeRenderer shape;

    public static boolean isPaused;
    public static boolean isPausedForJump;
    public static boolean isPausedForAcceleration;
    public static boolean isPausedForButton;
    public static boolean returnToLevels;

    private Badonkatrunk badonkatrunk;
    private int mapNbr;
    private boolean clockStarted;
    private long startTime;
    private BitmapFont font;

    private Sprite pauseButton;
    private Sprite resumeButton;
    private Sprite levelsButton;
    private Sprite arrow1 = new Sprite(new Texture(Gdx.files.internal("Introbana/arrowRight2.png")));
    private Sprite arrow2 = new Sprite(arrow1);
    private Sprite arrow3 = new Sprite(arrow1);
    private Sprite arrow = new Sprite(new Texture(Gdx.files.internal("Introbana/arrowUpShow.png")));
    private Sprite whiteCar = new Sprite(new Texture(Gdx.files.internal("Introbana/carWhite1.png")));
    private Sprite line = new Sprite(new Texture(Gdx.files.internal("line.png")));
    private Sprite tapRight = new Sprite(new Texture(Gdx.files.internal("Introbana/handRight.png")));
    private Sprite tapLeft = new Sprite(new Texture(Gdx.files.internal("Introbana/handLeft.png")));

    public GameScreen(Badonkatrunk badonkatrunk, Vehicle vehicle, Map level, int mapNbr) {
        this.badonkatrunk = badonkatrunk;
        this.vehicle = vehicle;
        this.level = level;
        this.mapNbr = mapNbr;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = Badonkatrunk.batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,500,500);

        shape = new ShapeRenderer();
        isPaused = true;
        clockStarted = false;
        startTime = 0;



        pauseButton = new Sprite(new Texture(Gdx.files.internal("pauseButton.png")));
        resumeButton = new Sprite(new Texture(Gdx.files.internal("resumeButton.png")));
        levelsButton = new Sprite(new Texture(Gdx.files.internal("levelsButtonBig.png")));

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!isPaused){

            if(clockStarted == false) {
                clockStarted = true;
                startTime = System.currentTimeMillis();
            }

            //Kolla om kameran nått slutet av banan, om inte så ska den röra sig åt höger.
            if (camera.position.x < level.getGoalXCoordinates() && camera.position.x < level.getWidth() -camera.viewportWidth/2) {

                camera.translate(vehicle.getScreenSpeed(), 0, 0);
                //camera.position.y = vehicle.getY()/2 + camera.viewportHeight/2;
                camera.update();


                //Kolla om fordonet hamnat bakom kameran, i så fall ska banan starta om.
                if(camera.position.x > vehicle.getX()+camera.viewportWidth/2+vehicle.getBoundingRectangle().width){
                    //vehicle.dispose();
                    restart();
                }
                //Kolla om bilen "kör om" kameran, i så fall ska kameran följa med bilen frammåt.
                if(vehicle.getX()+vehicle.getBoundingRectangle().width >= camera.position.x + camera.viewportWidth / 2){
                    camera.position.x = vehicle.getX()+vehicle.getBoundingRectangle().width - camera.viewportWidth / 2;
                }
            }


            //Kolla om fordonet är i överkant av kamerans vy, i så fall följer kameran med uppåt, men inte ovanför bakgrundsbilden.
            if(vehicle.getY() > camera.position.y + camera.viewportHeight/4 && camera.position.y + camera.viewportHeight /2 <= level.getHeight()) {
                camera.position.y = vehicle.getY() - camera.viewportWidth / 4;
                //Fullösning? om kameran hamnar ovanför bakgrundsbilden så hoppar den ner till bakgrundsbildens övre kant.
                if(camera.position.y + camera.viewportHeight / 2 > level.getHeight()) {
                    camera.position.y = level.getHeight() - camera.viewportHeight / 2;
                }
            }
            //Kolla om fordonet är i underkant av kamerans vy, i så fall följer kameran med nedåt.
            else if(vehicle.getY() < camera.position.y - camera.viewportHeight/4 && camera.position.y >= camera.viewportHeight /2) {
                camera.position.y = vehicle.getY() + camera.viewportHeight / 4;
                //Fullösning? om kameran hamnar nedanför bakgrundsbilden så hoppar den upp till bakgrundsbildens nedre kant.
                if(camera.position.y -camera.viewportHeight / 2 < 0) {
                    camera.position.y = camera.viewportHeight / 2;
                }
            }

            //Kolla om fordonet hamnat nedanför kamerans synfält
            if(vehicle.getY() + vehicle.getHeight() < camera.position.y - camera.viewportHeight/2) {
                restart();
            }

            //Kolla om fordonet kommit i mål.
            if(vehicle.getX() >= level.getGoalXCoordinates()){
                nextLevel(startTime);
            }
            camera.update();
        }
        vehicle.update(isPaused);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        level.draw(batch, camera.position.x);

        long currentExpiredTime = System.currentTimeMillis() - startTime;
        if(currentExpiredTime > 1000000){
            currentExpiredTime = 0;
        }
        String timeString = "Time: " + currentExpiredTime / 1000;
        font.draw(batch, timeString,camera.position.x - (timeString.length()*7)/2,camera.position.y + 200);

        pauseButton.setPosition(camera.position.x - (camera.viewportWidth / 2) + 15, camera.position.y + (camera.viewportHeight / 2) - 50);
        pauseButton.draw(batch, 1f);

        if(isPausedForJump){
            doJumpPauseStuff();
        }
        if(isPausedForAcceleration){
            doAcceleratePauseStuff();
        }
        if(isPausedForButton) {
            doPauseForButtonStuff();
        }
        if(returnToLevels) {
            returnToLevels();
        }
        vehicle.draw(batch);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        font.dispose();
        shape.dispose();
        vehicle.dispose();
    }

    public void restart(){
        badonkatrunk.setScreen(new LoadScreen(badonkatrunk, mapNbr));
        vehicle.dispose();
        this.dispose();
    }

    private void nextLevel(long startTime){
        int nextLevel = Math.min(mapNbr+1,10);
        if(nextLevel > badonkatrunk.highestUnlockedLevel){
            badonkatrunk.highestUnlockedLevel = nextLevel;
            FileHandle file = Gdx.files.local("unlockedLevels.txt");
            file.writeString(nextLevel+"",false);

        }

        long levelTime = System.currentTimeMillis() - startTime;
        vehicle.dispose();
        badonkatrunk.playMenuMusic();
        badonkatrunk.setScreen(new WinScreen(badonkatrunk, mapNbr, levelTime));
        this.dispose();
    }

    public void returnToLevels() {
        returnToLevels = false;
        vehicle.dispose();
        badonkatrunk.playMenuMusic();
        badonkatrunk.setScreen(new LevelsScreen(badonkatrunk));
        dispose();
    }

    public void doJumpPauseStuff(){

        arrow.setPosition(camera.position.x - 100,300);
        arrow.draw(batch);
        whiteCar.setPosition(camera.position.x -100,400);
        whiteCar.setSize(64,64);
        line.setPosition(camera.position.x,0);
        line.setSize(10, camera.viewportHeight);
        line.draw(batch);
        tapLeft.setPosition(camera.position.x - 330, -80);
        tapLeft.setScale(0.7f);
        whiteCar.draw(batch);
        tapLeft.draw(batch);
    }

    public void doAcceleratePauseStuff(){

        arrow2.setScale(1.5f);
        arrow3.setScale(2f);
        arrow1.setPosition(camera.position.x +100, 400);
        arrow2.setPosition(camera.position.x +132, 400);
        arrow3.setPosition(camera.position.x +164, 400);

        line.setPosition(camera.position.x,0);
        line.setSize(10, camera.viewportHeight);
        line.draw(batch);

        tapRight.setPosition(camera.position.x -170, -80);
        tapRight.setScale(0.7f);
        tapRight.draw(batch);
        arrow1.draw(batch);
        arrow2.draw(batch);
        arrow3.draw(batch);
    }

    public void doPauseForButtonStuff() {
        resumeButton.setPosition(camera.position.x  - 120, camera.position.y + 40);
        levelsButton.setPosition(camera.position.x - 120, camera.position.y - 100);
        resumeButton.draw(batch);
        levelsButton.draw(batch);
    }
}
