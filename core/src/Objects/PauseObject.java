package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Daniel Rosdahl
 */

/**
 * Class that creates a
 */
public class PauseObject extends CollidingObject {

    private boolean isTriggered;

    public PauseObject(Sprite sprite, float x, float y) {
        super(sprite);
        sprite.setSize(10, 500);
        sprite.setPosition(x,y);
        isTriggered = false;
    }

    public boolean isTriggered(){
        return isTriggered;
    }

    public void trigger(){
        isTriggered = true;
    }
}
