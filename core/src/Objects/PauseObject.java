package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class PauseObject extends CollidingObject {

    private boolean isJumpPause;

    public PauseObject(Sprite sprite, float x, float y) {
        super(sprite, x, y);
        sprite.setSize(10,500);
        isJumpPause = true;
    }
}
