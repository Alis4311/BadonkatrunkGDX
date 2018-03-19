package Vehicles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *Abstract Entity class that can represent different vehicles that the player uses in the game.
 *
 * author Tim Normark
 */
public abstract class Vehicle {
    private Sprite image;
    private int accelerationRate;
    private int speed;
    private int maxSpeed;
    private int jumpHeight;
    private Sound accelerateSound;
    private Sound jumpSound;
    private boolean grounded;

    public void setImage(Sprite image) {
        if(image != null) {
            this.image = image;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Sprite getImage() {
        return this.image;
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

    public void setPosition(int xPosition, int yPosition) {
        //this.xPosition = xPosition;
        image.setPosition(xPosition, yPosition);
    }

    public void draw(Batch batch) {
        image.draw(batch);
    }


    public int getAccelerationRate() {
        return accelerationRate;
    }

    public void setAccelerationRate(int accelerationRate) {
        this.accelerationRate = accelerationRate;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public void setAccelerateSound(Sound accelerateSound) {
        this.accelerateSound = accelerateSound;
    }

    public void setJumpSound(Sound jumpSound) {
        this.jumpSound = jumpSound;
    }

    public void playAccelerateSound() {
        accelerateSound.play();
    }

    public void loopAccelerateSound() {
        accelerateSound.loop();
    }

    public void stopAccelerateSound() {
        accelerateSound.stop();
    }

    public void playJumpSound() {
        jumpSound.play();
    }

    public void loopJumpSound() {
        jumpSound.loop();
    }

    public void stopJumpSound() {
        jumpSound.stop();
    }

    public void accelerate() {
        if(speed < maxSpeed) {
            speed += accelerationRate;
            loopAccelerateSound();
        }
    }

    public void idling() {
        stopAccelerateSound();
        if(speed > 0) {
            speed -= accelerationRate;
        }
    }

    public void jump() {
        if(grounded) {
            playJumpSound();

        }
    }

}
