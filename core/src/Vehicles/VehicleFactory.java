package Vehicles;

import Map.Map;
import com.badlogic.gdx.Gdx;

/**
 * Class that works as Factory that creates Vehicle-objects.
 *
 * author Tim Normark
 */
public class VehicleFactory {

    /**
     * Factory-method that creates and returns a Vehicle object. The type of Vehicle is depended on the theme attribute
     * of the given Map object.
     * @param map The Map object that is evaluated to decide what type of Vehicle should be returned by the method.
     * @return A vehicle object.
     */
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

        // initializes the vehicles position in the GameScreen.
        if(vehicle != null) {
            vehicle.setPosition(50, 100);
        }

        return vehicle;
    }

}
