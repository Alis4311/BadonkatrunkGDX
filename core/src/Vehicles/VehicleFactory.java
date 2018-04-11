package Vehicles;

import MapTest.Map;
import com.badlogic.gdx.Gdx;
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

    private static Sound carEngineSound;
    private static Sound carJumpSound;

    public static Vehicle create(Map map) {
        Vehicle vehicle = null;
        switch(map.getTheme()) {

            case 1 :
                vehicle = new Vehicle("car.atlas", carEngineSound,
                        carJumpSound, 4, 0.3f,
                        15, map);
                break;

            case 2 :
                System.out.println("1");
                break;

            case 3 :
                System.out.println("1");
                break;
        }

        if(vehicle != null) {
            vehicle.setPosition(50, 50);
        }

        return vehicle;
    }

    public static void loadSoundObjects() {
        carEngineSound = Gdx.audio.newSound(Gdx.files.internal("enginesound.ogg"));
        carJumpSound = Gdx.audio.newSound(Gdx.files.internal("CarJump.wav"));
    }
}
