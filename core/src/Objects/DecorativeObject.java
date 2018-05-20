package Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * An decorative object in Sprites.
 *
 * @ author
 */

public class DecorativeObject extends GameObject {


    /**
     * Sets up the sprite to decorate on.
     * @param sprite
     */

    public DecorativeObject(Sprite sprite){
        super(sprite);
    }



    /**
     *
     *  Sets up the sprite to decorate on with coordinates.
     *
     * @param sprite
     * @param x
     * @param y
     */

    public DecorativeObject(Sprite sprite, float x, float y){
        super(sprite);
        this.setSpritePosition(x,y);
    }



    /**
     *
     * Sets up the sprite to decorate on with id and coordinates.
     *
     * @param id
     * @param x
     * @param y
     */

    public DecorativeObject(int id, float x, float y){
        super(id,x,y);
    }
}
