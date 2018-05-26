package Testing;

import Vehicles.Vehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TestVehicleTest {
    public TestVehicleTest(){
        new VehicleTest(new Vehicle(new Sprite(new Texture(Gdx.files.internal("cobble.png"))), 0 , 0), 1, true, 100, 32);
    }

    public static void main(String[] args){
        new TestVehicleTest();
    }
}
