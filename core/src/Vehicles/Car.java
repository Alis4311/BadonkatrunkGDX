package Vehicles;

import MapTest.Map;
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

    public Car(Map map) {
        super("car.atlas", Gdx.audio.newSound(Gdx.files.internal("enginesound.ogg")),
                Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")), 4, 0.3f,
                15, map);

        setPosition(50, 50);
    }
}
