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
 * Class that holds all attributes, images, sounds and functionality to represent a Vehicle in the Game.
 * A Vehicle object handles all logic of it's own functionality and should be able to be used with simple
 * public method calls such as accelerate() jump() etc.
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

    /**
     * Creates a Vehicle object.
     *
     * @param drivingAnimationAtlas The path of the atlas file that is used to control the driving animation.
     * @param engineSound The Sound object that represents this objects engine sound.
     * @param jumpSound The Sound object that represents this objects jump sound.
     * @param maxSpeed float value representing this objects maximum driving speed.
     * @param accelerationRate float value representing this objects acceleration rate.
     * @param jumpHeight float value representing this objects jumping power.
     * @param gravity float value representing the gravity force that will affect on this object.
     * @param map The Map that the vehicle will be used, and played, on.
     */
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

    /**
     * Sets the texture and coordinates to the specified region.
     * @param region
     */
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
     * The method is used every frame of the Game. Updates the position of the car, after processing the input from user
     * and then handles collision between the Vehicle and other CollidingObjects.
     */
    public void update(boolean isPaused){
        processInput();
        if(!isPaused){
            ySpeed = Math.max(ySpeed-gravity, -31);
            collisionHandling();
        }

    }

    /**
     * Disposes resources that the object has used, to prevent memory-leak.
     */
    public void dispose(){
        vehicleSound.stop();
        textureAtlas.dispose();
    }

    /**
     * Sets the position of the object to the given coordinates.
     * @param xPosition x-coordinate
     * @param yPosition y-coordinate
     */
    protected void setPosition(float xPosition, float yPosition) {
        if(yPosition > groundlevel){
            sprite.setPosition(xPosition, yPosition);

        } else if(yPosition <= groundlevel ){
            ySpeed = 0;
            sprite.setPosition(xPosition,groundlevel);

        }


    }

    /**
     * Checks for collision between the Vehicle and all other CollidingObjects of the level. If the vehicle has
     * collided the method stops the Vehicle from moving further in the direction of the collision. The method then
     * sets the new position of Vehicle after all collisions have been adjusted for.
     */
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

    /**
     * @return a float variable that is used to determine the screens scroll speed in GameScreen
     */
    public float getScreenSpeed(){
        return this.screenSpeed;
    }

    /**
     * @return A Rectangle from the top of the Vehicle used for collision detection.
     */
    public Rectangle getTopRectangle(){
        return new Rectangle(this.getX(),this.getY() + this.getHeight()/2, this.getWidth()-10, 5);
    }

    /**
     * Sets grounded-attribute of object
     *
     * @param grounded boolean value to represent grounded-attribute.
     */
    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
        if(grounded) {
            showDrivingAnimation();
        }
        else {
            showJumpImage();
        }
    }

    /**
     * Method for making the Vehicle accelerate. The method should be called each frame that the Vehicle should continue
     * to accelerate.
     */
    public void accelerate() {
        vehicleSound.accelerate();

        if(xSpeed < maxSpeed) {
            xSpeed += accelerationRate;
        }
    }

    /**
     * Method for making the Vehicle decelerate and eventually stop. The method should be called every frame that the
     * Vehicle should continue idling.
     */
    public void idling() {
        vehicleSound.decelerate();
        if(xSpeed > 0) {
           xSpeed = Math.max(xSpeed - accelerationRate, 0);
       }
    }

    /**
     * Method for making the Vehicle jump. If method is called when the Vehicle is already jumping or falling, nothing will happen.
     */
    public void jump() {
        if(grounded) {
            if(ySpeed <= 0) {
                vehicleSound.jump();
            }
            ySpeed = this.jumpHeight;
            grounded = false;
        }
    }

    /**
     * Method for making the car start showing it's driving animation. The method is used every time the Vehicle
     * is not jumping or falling.
     */
    private void showDrivingAnimation() {
       if(Timer.instance() != null){
           Timer.instance().clear();
       }
        Timer.schedule(drivingAnimation,0,1/10.0f);
    }

    /**
     * Method for making the Vehicle stop showing it's driving animation and instead show a still image of the Vehicle.
     * Used every time the Vehicle jumps or falls.
     */
    private void showJumpImage() {
        Timer.instance().clear();
        setRegion(textureAtlas.findRegion("0003"));
    }

    /**
     * Method that recieves input from user. Method is called every time the user presses a key (on computer).
     * The method determines what key was pressed. If user pressed the right-arrow-key a flag in the object is set
     * to indicate that the Vehicle should accelerate. If the space-key was pressed a flag in the objet is set to indicate
     * that the Vehicle should jump. This method is for making the Game testable on a computer, not intended to be used
     * in final Android-product.
     * @param keycode int value representing the pressed key.
     * @return Always returns false.
     */
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

    /**
     * Method that recieves input from user. Method is called every time the user releases a key (on computer).
     * The method determines what key was released. If user released the right-arrow-key a flag in the object is set
     * to indicate that the Vehicle should stop accelerating. If the space-key was released a flag in the objet is set
     * to indicate that the Vehicle should stop jumping. This method is for making the Game testable on a computer, not
     * intended to be used in final Android-product.
     * @param keycode int value representing the released key.
     * @return Always returns false.
     */
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

    /**
     * Method that recieves input from user. Method is called every time the user presses down on the screen.
     * The method determines if the screen-touch was done on the left side of the screen or the right side. If the screen-touch
     * was done on the left side of the screen a flag in the object is set to indicate that the Vehicle should jump. If
     * the screen-touch was done on the right side of the screen a flag in the object is set to indicate that the Vehicle
     * should accelerate.
     * @param screenX X-coordinate of the screen-touch
     * @param screenY Y-coordinate of the screen-touch
     * @param pointer not used
     * @param button not used
     * @return Always returns false.
     */
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

    /**
     * Method that recieves input from user. Method is called every time the user releases a press-down from the screen.
     * The method determines if the touch-up was done on the left side of the screen or the right side. If the touch-up
     * was done on the left side of the screen a flag in the object is set to indicate that the Vehicle should stop jumping.
     * If the touch-up was done on the right side of the screen a flag in the object is set to indicate that the Vehicle
     * should stop accelerating.
     * @param screenX X-coordinate of the touch-up
     * @param screenY Y-coordinate of the touch-up
     * @param pointer not used
     * @param button not used
     * @return Always returns false.
     */
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

    /**
     * Private class in Vehicle class that works as a Timer.Task with the only job of changing the shown image in
     * the driving-animation of the Vehicle.
     */
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

    /**
     * Private class in Vehicle class that handles the Sounds of the Vehicle object. Each Vehicle has a engine sound and
     * a jump sound. The class encapsulates these two sounds and has methods for playing the jump sound and manipulating
     * the engine sound to simulate acceleration and idling.
     */
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

        /**
         * Creates instance of VehicleSound. The object will use the given Sounds. This constructor sets the engine-sound
         * to be looping and starts playing it once it has loaded.
         * @param engineSound The Sound object that will represent the engine-sound of the Vehicle
         * @param jumpSound The Sound object that will represent the jump-sound of the Vehicle
         */
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

        /**
         * Method for manipulating the engine-sound to simulate the Vehicle accelerating. The method should be called
         * every frame that the Vehicle should continue to accelerate.
         */
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

        /**
         * Method for manipulating the engine-sound to simulate the Vehicle decelerating. The method should be called
         * every frame that the Vehicle should continue to decelerate.
         */
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

        /**
         * Method for plaing the jump-sound held by the object.
         */
        private void jump() {
            jumpSound.stop();
            jumpSound.play();

        }

        /**
         * Method for stopping and disposing all Sound-objects of this instance.
         */
        public void stop() {
            engineSound.stop(engineSoundId);
            engineSound.dispose();
            jumpSound.dispose();
        }
    }

}
