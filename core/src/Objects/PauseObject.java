package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class PauseObject extends CollidingObject {

    private boolean isTriggered;

    public PauseObject(Sprite sprite, float x, float y) {
        super(sprite, x, y);
        sprite.setSize(10, 500);
        isTriggered = false;
    }

    public boolean isTriggered(){
        return isTriggered;
    }

    public void trigger(){
        isTriggered = true;
    }
}
