package Vehicles;

import MapTest.Map;
import Objects.CollidingObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;


/**
 *Abstract Entity class that can represent different vehicles that the player uses in the game.
 *
 * author Tim Normark
 */
public abstract class Vehicle extends Objects.CollidingObject {
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
    private boolean grounded; // ska vara sann om fordonet rör vid mark, falsk annars.
    private DrivingAnimation drivingAnimation;
    private Map level;


    Vehicle(String drivingAnimationAtlas, Sound engineSound, Sound jumpSound, float maxSpeed,
            float accelerationRate, float jumpHeight, Map map) {
        super(new Sprite(new TextureAtlas.AtlasRegion(new TextureAtlas(Gdx.files.internal(drivingAnimationAtlas)).findRegion("0001"))));
        vehicleSound = new VehicleSound(engineSound, jumpSound);
        this.maxSpeed = maxSpeed;
        this.accelerationRate = accelerationRate;
        this.xSpeed = 0f;
        this.ySpeed = 0f;
        gravity = 1f;
        groundlevel = 0;
        this.jumpHeight = jumpHeight;
        drivingAnimation = new DrivingAnimation();

        currentAtlasKey = "0001";
        currentFrame = 1;
        textureAtlas = new TextureAtlas(Gdx.files.internal(drivingAnimationAtlas));
        level = map;
        map.setVehicle(this);
        setGrounded(true); // För att fordonet ska fungera vid test. Detta ska ställas in utifrån sen.
    }

    private void setRegion(TextureRegion region) {
        sprite.setRegion(region);
    }

    /**
     * Handle input from user before updating car.
     */
    private void processInput(){
        /**
         * For whatever reason we need to specify two inputs in order to not automatically loop the input.
         * TODO: figure out why?
         */
        float x0 = Gdx.input.getX(0);
        float x1 = Gdx.input.getX(1);
        /**
         * Define  "buttons" for the acceleration and the jump, at this point there are two "buttons" each covering one half of the screen.
         *
         */
        boolean accelerateTouch = (Gdx.input.isTouched(0) && x0 > Gdx.graphics.getWidth() / 2) || (Gdx.input.isTouched(1) && x1 > Gdx.graphics.getWidth() / 2);
        boolean jumpTouch = (Gdx.input.isTouched(0) && x0 < Gdx.graphics.getWidth() / 2) || (Gdx.input.isTouched(1) && x1 < Gdx.graphics.getWidth() / 2);

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || accelerateTouch){
            accelerate();
        } else {
            idling();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || jumpTouch){
            jump();
        }
    }

    /**
     * Update the position of the car, after processing user input.
     */
    public void update(){
        processInput();

        ySpeed -= gravity;
        for(CollidingObject object : level.getGameObstacleObjects()){
            if (this.checkCollision(object.getBoundingRectangle())){
                if(this.getRightRectangle().overlaps(object.getBoundingRectangle())){
                    setPosition(object.getX() - this.getWidth(), getY()+ySpeed);
                }

                if(this.getBottomRectangle().overlaps(object.getBoundingRectangle())){
                    groundlevel = object.getBoundingRectangle().y + object.getHeight()+5;

                    setPosition(getX() + xSpeed, object.getY()+object.getHeight() +5);
                }

            } else {

                groundlevel = 0;
                setPosition(getX() + xSpeed, getY()+ySpeed);
            }

            if(getY() > groundlevel){
                grounded = false;
            } else {
                grounded = true;
            }
        }
    }

    public void dispose(){
        vehicleSound.jumpSound.dispose();
        vehicleSound.engineSound.dispose();
    }

    protected void setPosition(float xPosition, float yPosition) {
        if(yPosition > groundlevel){
            sprite.setPosition(xPosition, yPosition);

        } else if(yPosition <= groundlevel ){
            sprite.setPosition(xPosition,groundlevel);

        }

    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
        if(grounded)
            showDrivingAnimation();
        else
            showJumpImage();

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
            vehicleSound.jump();
            ySpeed = this.jumpHeight;
            grounded = false;
        }
    }

    private void showDrivingAnimation() {
       if(Timer.instance() != null){
           Timer.instance().clear();
       }
        Timer.schedule(drivingAnimation,0,1/20.0f);
    }

    private void showJumpImage() {
        Timer.instance().clear();
        setRegion(textureAtlas.findRegion("0003"));
    }


    private class DrivingAnimation extends Timer.Task {
        @Override
        public void run() {
            currentFrame++;
            if(currentFrame > 4)
                currentFrame = 1;

            currentAtlasKey = String.format("%04d", currentFrame);
            setRegion(textureAtlas.findRegion(currentAtlasKey));
        }
    }

    private class VehicleSound {
        private Sound jumpSound;
        private Sound engineSound;
        private long engineSoundId;

        private final float MAXPITCH;
        private final float MINPITCH;
        private float pitchChangeRate;
        private float pitch;

        private final float MAXVOLUME;
        private final float MINVOLUME;
        private float volumeChangeRate;
        private float engineVolume;




        private VehicleSound(Sound engineSound, Sound jumpSound) {
            this.engineSound = engineSound;
            this.jumpSound = jumpSound;
            MAXPITCH = 2.0f;
            MINPITCH = 0.5f;
            pitchChangeRate = 0.1f;

            MAXVOLUME = 1.0f;
            MINVOLUME = 0.4F;
            volumeChangeRate = 0.1f;


            engineSoundId = engineSound.loop();
        }

        private void accelerate() {
            if(pitch < MAXPITCH) {
                engineSound.setPitch(engineSoundId, pitch);
                pitch += pitchChangeRate;

                if(engineVolume < MAXVOLUME) {
                    engineVolume += volumeChangeRate;
                    engineSound.setVolume(engineSoundId, engineVolume);
                }
            }
        }

        private void decelerate() {

            if(pitch > MINPITCH) {
                engineSound.setPitch(engineSoundId, pitch);
                pitch -= pitchChangeRate;

                if(engineVolume > MINVOLUME) {
                    engineVolume -= volumeChangeRate;
                    engineSound.setVolume(engineSoundId, engineVolume);
                }
            }
        }

        private void jump() {
            jumpSound.play();
        }
    }

}
