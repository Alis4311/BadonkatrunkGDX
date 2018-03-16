package Vehicles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * author Tim Normark
 */
public abstract class Vehicle {
    private Sprite image;
    private int xPosition;
    private int yPosition;
    private int accelerationRate;
    private int jumpHeight;
    private Sound accelerateSound;
    private Sound jumpSound;

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

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getYPosition() {
        return yPosition;
    }


    public int getAccelerationRate() {
        return accelerationRate;
    }

    public void setAccelerationRate(int accelerationRate) {
        this.accelerationRate = accelerationRate;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }


    public Sound getAccelerateSound() {
        return accelerateSound;
    }

    public void setAccelerateSound(Sound accelerateSound) {
        this.accelerateSound = accelerateSound;
    }

    public Sound getJumpSound() {
        return jumpSound;
    }

    public void setJumpSound(Sound jumpSound) {
        this.jumpSound = jumpSound;
    }

}
