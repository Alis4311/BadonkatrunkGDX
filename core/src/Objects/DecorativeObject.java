package Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A decorative object in Screens.
 *
 * @author Christoffer Book, Tim Normark, Peder Nilsson
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