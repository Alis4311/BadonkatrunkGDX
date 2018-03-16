package Vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Class that represents a the car that the player uses in the game.
 *
 * author Tim Normark
 */
public class Car extends Vehicle {

    public Car() {
        setImage(new Sprite(new Texture(Gdx.files.internal("Car.png"))));
        setAccelerationRate(2);
        setSpeed(5);
        setJumpHeight(50);
        setJumpSound(Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")));
        setAccelerateSound(Gdx.audio.newSound(Gdx.files.internal("enginesound.wav")));
    }
}
