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
 * Class containing the actual "game-screen".
 *
 * @author Christoffer Book, Tim Normark, Peder Nilsson, Daniel Rosdahl
 */
public class GameScreen implements Screen{
    OrthographicCamera camera;
    Vehicle vehicle;
    Map level;
    SpriteBatch batch;
    ShapeRenderer shape;
    /**
     * Bunch of static variables used for pausing in different situations.
     */
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

    /**
     * Constructor for gamescreen.
     * @param badonkatrunk - the one and only badonkatrumk
     * @param vehicle - the vehicle to be used in this instance of game.
     * @param level - the level to play
     * @param mapNbr - the number of the level, for use when submitting highscore.
     */
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
        isPaused = true; // start every level paused.
        clockStarted = false; //clock is not started until player starts driving.
        startTime = 0;



        pauseButton = new Sprite(new Texture(Gdx.files.internal("pauseButton.png")));
        resumeButton = new Sprite(new Texture(Gdx.files.internal("resumeButton.png")));
        levelsButton = new Sprite(new Texture(Gdx.files.internal("levelsButtonBig.png")));

    }


    @Override
    public void show() {

    }

    /**
     * render method, update everything  and draw it to screen
     *
     * @param delta the difference in time since last call.
     */
    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!isPaused){

            if(clockStarted == false) {
                clockStarted = true;
                startTime = System.currentTimeMillis();
            }


            //If camera has reached the end of the level, don´t move.
            if (camera.position.x < level.getGoalXCoordinates() && camera.position.x < level.getWidth() -camera.viewportWidth/2) {

                camera.translate(vehicle.getScreenSpeed(), 0, 0);
                camera.update();


                //If vehicle has fallen behind the screen, restart level.
                if(camera.position.x > vehicle.getX()+camera.viewportWidth/2+vehicle.getBoundingRectangle().width){
                    //vehicle.dispose();
                    restart();
                }
                //If car passes screen, move screen in accordance to car.
                if(vehicle.getX()+vehicle.getBoundingRectangle().width >= camera.position.x + camera.viewportWidth / 2){
                    camera.position.x = vehicle.getX()+vehicle.getBoundingRectangle().width - camera.viewportWidth / 2;
                }
            }


            //If vehicle is above screen, follow it all the way to the top of the background but no further.
            if(vehicle.getY() > camera.position.y + camera.viewportHeight/4 && camera.position.y + camera.viewportHeight /2 <= level.getHeight()) {
                camera.position.y = vehicle.getY() - camera.viewportWidth / 4;
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
