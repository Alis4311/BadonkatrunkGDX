package Vehicles;

import MapTest.Map;
import com.badlogic.gdx.Gdx;

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
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.ogg")), 4, 0.3f,
                        15, 1f, map);
                break;

            case 2 :
                vehicle = new Vehicle("tractor.atlas", Gdx.audio.newSound(Gdx.files.internal("tractor.ogg")),
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.ogg")), 2, 0.6f,
                        15, 1f, map);
                break;

            case 3 :
                vehicle = new Vehicle("rocket.atlas", Gdx.audio.newSound(Gdx.files.internal("rocket.ogg")),
                        Gdx.audio.newSound(Gdx.files.internal("CarJump.ogg")), 12, 2f,
                        15, 0.2f, map);
                break;
        }

        if(vehicle != null) {
            vehicle.setPosition(50, 100);
        }

        return vehicle;
    }

}
