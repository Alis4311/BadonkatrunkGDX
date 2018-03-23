package Vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;


/**
 *Abstract Entity class that can represent different vehicles that the player uses in the game.
 *
 * author Tim Normark
 */
public abstract class Vehicle {
    private VehicleSound vehicleSound;
    private Sprite image;
    private float accelerationRate;
    private float speed;
    private float maxSpeed;
    private float jumpHeight;
    private TextureAtlas textureAtlas;
    private String currentAtlasKey;
    private int currentFrame;
    private float gravity = 5f;

    private boolean grounded; // ska vara sann om fordonet rör vid mark, falsk annars.
    private DrivingAnimation drivingAnimation;

    Vehicle(String drivingAnimationAtlas, Sound engineSound, Sound jumpSound, float maxSpeed,
            float accelerationRate, float jumpHeight) {
        vehicleSound = new VehicleSound(engineSound, jumpSound);
        this.maxSpeed = maxSpeed;
        this.accelerationRate = accelerationRate;
        this.speed = 0f;
        this.jumpHeight = jumpHeight;
        drivingAnimation = new DrivingAnimation();

        currentAtlasKey = "0001";
        currentFrame = 1;
        textureAtlas = new TextureAtlas(Gdx.files.internal(drivingAnimationAtlas));
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(currentAtlasKey);
        image = new Sprite(region);

        setGrounded(true); // För att fordonet ska fungera vid test. Detta ska ställas in utifrån sen.
    }

    private void setRegion(TextureRegion region) {
        image.setRegion(region);
    }

    public Rectangle getBoundingRectangle() {
        return image.getBoundingRectangle();
    }

    public float getX() {
        return image.getX();
    }

    public float getY() {
        return image.getY();
    }

    private void setPosition(float xPosition, float yPosition) {
        if(yPosition > 0){
            image.setPosition(xPosition, yPosition);
            if(grounded)
                setGrounded(false);
        } else if(yPosition <= 0) {
            image.setPosition(xPosition,0);
            if(!grounded)
                setGrounded(true);
        }

    }

    public void draw(Batch batch) {
        image.draw(batch);
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

        if(speed < maxSpeed && grounded) {
            speed += accelerationRate;
        }

        setPosition(getX() + speed, getY() - gravity);

    }

    public void idling() {
        vehicleSound.decelerate();
        if(speed > 0) {
           speed = Math.max(speed - accelerationRate, 0);
           setPosition(getX() + speed, getY() - gravity);
        }
    }

    public void jump() {
        if(grounded) {
            vehicleSound.jump();
            setPosition(getX()+speed, getY()+jumpHeight*Gdx.graphics.getDeltaTime());
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
            jump(); // Enbart för test. Ta bort inför skarpt läge.

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
