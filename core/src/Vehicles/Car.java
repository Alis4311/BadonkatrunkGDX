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
    private TextureAtlas textureAtlas;
    private String currentAtlasKey = new String("0001");

    private TextureAtlas.AtlasRegion region;
    public Car() {
        //setImage(new Sprite(new Texture(Gdx.files.internal("Car.png"))));
        setAccelerationRate(2);
        setSpeed(5);
        setJumpHeight(50);
        setJumpSound(Gdx.audio.newSound(Gdx.files.internal("CarJump.wav")));
        setAccelerateSound(Gdx.audio.newSound(Gdx.files.internal("enginesound.wav")));
        textureAtlas = new TextureAtlas(Gdx.files.internal("car.atlas"));
        region = textureAtlas.findRegion("0001");

        setImage(new Sprite(region));
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               currentFrame++;
                               if(currentFrame > 4)
                                   currentFrame = 1;

                               // ATTENTION! String.format() doesnt work under GWT for god knows why...
                               currentAtlasKey = String.format("%04d", currentFrame);
                               image.setRegion(textureAtlas.findRegion(currentAtlasKey));
                           }
                       }
                ,0,1/20.0f);
    }
}
