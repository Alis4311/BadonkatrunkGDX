package Vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Car extends Vehicle {
    /**
     *
     * author Tim Normark
     */
    public Car() {
        setImage(new Sprite(new Texture(Gdx.files.internal("Car.png"))));
        setAccelerationRate(2);
        setSpeed(5);
        setJumpHeight(50);
        setJumpSound(Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")));

    }
}
