package Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * An decorative object in Sprites.
 *
 * @ author Tim Normark, Peder Nilsson
 */

public class DecorativeObject extends GameObject {


    /**
     * Sets up the sprite to decorate on.
     *
     * @param sprite
     */

    public DecorativeObject(Sprite sprite) {
        super(sprite);
    }

}