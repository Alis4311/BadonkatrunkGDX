package Vehicles;

import Map.Map;
import Objects.CollidingObject;
import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;


/**
 *Abstract Entity class that can represent different vehicles that the player uses in the game.
 *
 * @author Tim Normark, Daniel Rosdahl
 */
public class Vehicle extends Objects.CollidingObject implements InputProcessor {
    private VehicleSound vehicleSound;
    private float accelerationRate;
    private float xSpeed;
    private float ySpeed;
    private float maxSpeed;
    private float jumpHeight;
    private TextureAtlas textureAtlas;
    private String currentAtlasKey;
    private int currentFrame;
    private float gravity;
    private float groundlevel;
    private boolean grounded;
    private DrivingAnimation drivingAnimation;
    private Map level;
    private float screenSpeed;

    private boolean accelerateTouch = false;
    private boolean jumpTouch = false;
    private boolean pauseTouch = false;
    private boolean resumeTouch = false;
    private boolean levelsTouch = false;

    Vehicle(String drivingAnimationAtlas, Sound engineSound, Sound jumpSound, float maxSpeed,
            float accelerationRate, float jumpHeight, float gravity, Map map, float screenSpeed) {
        super(new Sprite(new TextureAtlas(Gdx.files.internal(drivingAnimationAtlas)).findRegion("0001")));
        textureAtlas = new TextureAtlas(Gdx.files.internal(drivingAnimationAtlas));
        vehicleSound = new VehicleSound(engineSound, jumpSound);
        this.maxSpeed = maxSpeed;
        this.accelerationRate = accelerationRate;
        this.xSpeed = 0f;
        this.ySpeed = 0f;
        this.gravity = gravity;
        this.screenSpeed = screenSpeed;
        groundlevel = 0;
        this.jumpHeight = jumpHeight;
        drivingAnimation = new DrivingAnimation();

        currentAtlasKey = "0001";
        currentFrame = 1;

        level = map;
        Gdx.input.setInputProcessor(this);
    }

    private void setRegion(TextureRegion region) {
        sprite.setRegion(region);
    }

    /**
     * Handle input from user before updating car.
     */
    private void processInput(){

            if(GameScreen.isPausedForButton) {
                if(resumeTouch) {
                    GameScreen.isPausedForButton = false;
                    GameScreen.isPaused = false;

                    //För säkerhets skull, kan annars bli fel vid swipe.
                    pauseTouch = false;
                    resumeTouch = false;
                    jumpTouch = false;
                } else if(levelsTouch) {
                    GameScreen.returnToLevels = true;
                    GameScreen.isPausedForButton = false;
                    GameScreen.isPaused = false;
                }

            } else {
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || accelerateTouch) {
                    if (GameScreen.isPausedForAcceleration || (GameScreen.isPaused && !GameScreen.isPausedForJump)) {
                        GameScreen.isPaused = false;
                        GameScreen.isPausedForAcceleration = false;
                    }
                    accelerate();
                } else {
                    idling();
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || jumpTouch) {
                    if (GameScreen.isPausedForJump) {
                        GameScreen.isPausedForJump = false;
                        GameScreen.isPaused = false;
                    }
                    jump();
                }
                if (pauseTouch) {
                    GameScreen.isPausedForButton = true;
                    GameScreen.isPaused = true;
                }
            }
    }

    /**
     * Update the position of the car, after processing user input.
     */
    public void update(boolean isPaused){
        processInput();
        if(!isPaused){
            ySpeed = Math.max(ySpeed-gravity, -31);
            collisionHandling();
        }

    }


    public void dispose(){
        vehicleSound.stop();
        textureAtlas.dispose();
    }

    protected void setPosition(float xPosition, float yPosition) {
        if(yPosition > groundlevel){
            sprite.setPosition(xPosition, yPosition);

        } else if(yPosition <= groundlevel ){
            ySpeed = 0;
            sprite.setPosition(xPosition,groundlevel);

        }


    }

    private void collisionHandling(){
        boolean bottomRectCollision = false;
        for(CollidingObject object : level.getGameObstacleObjects()) {

            if (this.checkCollision(object.getBoundingRectangle())) {
                if (this.getRightRectangle().overlaps(object.getBoundingRectangle())) {
                    xSpeed = 0;
                }

                if (this.getBottomRectangle().overlaps(object.getBoundingRectangle())) {
                    groundlevel = object.getBoundingRectangle().y + object.getHeight()-2;
                    bottomRectCollision = true;
                }

                if(this.getTopRectangle().overlaps(object.getBoundingRectangle())){
                    if(ySpeed > 0){
                        ySpeed = 0;
                    }
                }

            }

        }

        if(level.hasPauseObject()){
            if(!level.getPauseObject().isTriggered()){
                if(this.checkCollision(level.getPauseObject().getBoundingRectangle())){
                    if(this.getRightRectangle().overlaps(level.getPauseObject().getBoundingRectangle())){
                        GameScreen.isPausedForJump = true;
                        GameScreen.isPaused = true;
                        level.getPauseObject().trigger();
                    }
                }
            }
        }


        if(bottomRectCollision){
            setGrounded(true);

        }else{
            groundlevel = -200;
            setGrounded(false);

        }

        setPosition(getX()+xSpeed, getY()+ ySpeed);
    }

    public float getScreenSpeed(){
        return this.screenSpeed;
    }

    public Rectangle getTopRectangle(){
        return new Rectangle(this.getX(),this.getY() + this.getHeight()/2, this.getWidth()-10, 5);
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
        if(grounded) {
            showDrivingAnimation();
        }
        else {
            showJumpImage();
        }
    }

    public void accelerate() {
        vehicleSound.accelerate();

        if(xSpeed < maxSpeed) {
            xSpeed += accelerationRate;
        }
    }

    public void idling() {
        vehicleSound.decelerate();
        if(xSpeed > 0) {
           xSpeed = Math.max(xSpeed - accelerationRate, 0);
       }
    }

    public void jump() {
        if(grounded) {
            if(ySpeed <= 0) {
                vehicleSound.jump();
            }
            ySpeed = this.jumpHeight;
            grounded = false;
        }
    }

    private void showDrivingAnimation() {
       if(Timer.instance() != null){
           Timer.instance().clear();
       }
        Timer.schedule(drivingAnimation,0,1/10.0f);
    }

    private void showJumpImage() {
        Timer.instance().clear();
        setRegion(textureAtlas.findRegion("0003"));
    }

    @Override
    public boolean keyDown(int keycode) {
        if(Input.Keys.RIGHT == keycode){
            accelerateTouch = true;
        }

        if(Input.Keys.SPACE == keycode){
            jumpTouch = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(Input.Keys.RIGHT == keycode){
            accelerateTouch = false;
        }

        if(Input.Keys.SPACE == keycode){
            jumpTouch = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Kolla om spelaren tryckt på höger sida av skärmen
        if(screenX >  Gdx.graphics.getWidth() / 2) accelerateTouch = true;

        //Kolla först om spelaren tryckt uppe till vänster, runt pausknapp. Annars om spelaren tryckt någonstans på vänster sida av skärmen.
        if(screenX < 75 && screenY < 75) {
            pauseTouch = true;
        } else if(screenX <  Gdx.graphics.getWidth() / 2) jumpTouch = true;

        //Kolla om spelaren tryckt inom x-koordinaterna som resume- och levels-knapparna ligger.
        if(screenX > Gdx.graphics.getWidth() / 4 && screenX < Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4)) {
            //Kolla om spelaren tryckt inom y-koordinaterna för resume-knapp, annars om inom koordinater för levels-knapp.
            if(screenY < Gdx.graphics.getHeight() / 2 && screenY > Gdx.graphics.getHeight() / 4) {
                resumeTouch = true;
            } else if(screenY > Gdx.graphics.getHeight() / 2 && screenY < Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4) {
                levelsTouch = true;
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(screenX >  Gdx.graphics.getWidth() / 2) accelerateTouch = false;
        if(screenX <  Gdx.graphics.getWidth() / 2) jumpTouch = false;
        if(screenX < 75 && screenY < 75) pauseTouch = false;

        //Kolla om spelaren släppt inom x-koordinaterna som resume- och levels-knapparna ligger.
        if(screenX > Gdx.graphics.getWidth() / 4 && screenX < Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4)) {
            //Kolla om spelaren släppt inom y-koordinaterna för resume-knapp, annars om inom koordinater för levels-knapp.
            if(screenY < Gdx.graphics.getHeight() / 2 && screenY > Gdx.graphics.getHeight() / 4) {
                resumeTouch = false;
            } else if(screenY > Gdx.graphics.getHeight() / 2 && screenY < Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4) {
                levelsTouch = false;
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    private class DrivingAnimation extends Timer.Task {
        @Override
        public void run() {
            currentFrame++;
            if (currentFrame >= 1 && currentFrame < 5) {
                currentAtlasKey = "0001";
            } else if(currentFrame >= 5 && currentFrame <10){
                currentAtlasKey = "0002";
            } else if(currentFrame >= 15 && currentFrame <20){
                currentAtlasKey = "0003";
            } else{

                /*if(currentFrame >= 20 && currentFrame < 25){
                currentAtlasKey = "0002";
            } else {*/
                currentFrame = 1;
            }



            //currentAtlasKey = String.format("%04d", currentFrame);
            setRegion(textureAtlas.findRegion(currentAtlasKey));
        }
    }

    private class VehicleSound {
        private Sound jumpSound;
        private Sound engineSound;
        private long engineSoundId;

        private final float MAX_PITCH;
        private final float MIN_PITCH;
        private float pitchChangeRate;
        private float pitch;

        private final float MAX_VOLUME;
        private final float MIN_VOLUME;
        private float volumeChangeRate;
        private float engineVolume;




        private VehicleSound(Sound engineSound, Sound jumpSound) {
            this.jumpSound = jumpSound;
            this.engineSound = engineSound;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e ) {}

            MAX_PITCH = 1.5f;
            MIN_PITCH = 0.8f;
            pitchChangeRate = 0.05f;

            MAX_VOLUME = 1.0f;
            MIN_VOLUME = 0.7F;

            volumeChangeRate = 0.05f;
            engineSoundId = this.engineSound.loop();
            pitch = MIN_PITCH;
            engineVolume = MIN_VOLUME;
            this.engineSound.setPitch(engineSoundId, pitch);
            this.engineSound.setVolume(engineSoundId, engineVolume);
    }


        private void accelerate() {
            if(pitch < MAX_PITCH) {
                pitch += pitchChangeRate;
                engineSound.setPitch(engineSoundId, pitch);

                if(engineVolume < MAX_VOLUME) {
                    engineVolume += volumeChangeRate;
                    engineSound.setVolume(engineSoundId, engineVolume);
                }
            }
        }

        private void decelerate() {

            if(pitch > MIN_PITCH) {
                pitch -= pitchChangeRate;
                engineSound.setPitch(engineSoundId, pitch);

                if(engineVolume > MIN_VOLUME) {
                    engineVolume -= volumeChangeRate;
                    engineSound.setVolume(engineSoundId, engineVolume);
                }
            }
        }

        private void jump() {
            jumpSound.stop();
            jumpSound.play();

        }

        public void stop() {
            engineSound.stop(engineSoundId);
            engineSound.dispose();
            jumpSound.dispose();
        }
    }

}
