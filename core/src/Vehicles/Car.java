package Vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

/**
 * Class that represents a the car that the player uses in the game.
 *
 * author Tim Normark
 */
public class Car extends Vehicle {

    public Car() {
        super("car.atlas", Gdx.audio.newSound(Gdx.files.internal("enginesound.wav")),
                Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")), 8, 0.15f,
                2000);
    }
}
