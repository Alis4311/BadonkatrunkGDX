package Vehicles;

import MapTest.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

/**
 * Class that represents a the car that the player uses in the game.
 *
 * author Tim Normark
 */
public class VehicleFactory {

    public static Vehicle create(Map map) {

        Vehicle vehicle = null;
        switch(map.getTheme()) {

            case 1 :
                vehicle = new Vehicle("car.atlas", Gdx.audio.newSound(Gdx.files.internal("enginesound.ogg")),
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")), 4, 0.3f,
                        15, map);
                break;

            case 2 :
                vehicle = new Vehicle("tractor.atlas", Gdx.audio.newSound(Gdx.files.internal("enginesound.ogg")),
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")), 4, 0.3f,
                        15, map);
                break;

            case 3 :
                vehicle = new Vehicle("rocket.atlas", Gdx.audio.newSound(Gdx.files.internal("enginesound.ogg")),
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")), 4, 0.3f,
                        15, map);
                break;
        }

        if(vehicle != null) {
            vehicle.setPosition(50, 100);
        }

        return vehicle;
    }

}
