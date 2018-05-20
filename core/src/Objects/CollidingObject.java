package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


/**
 * A colliding object in screens.
 *
 *  @ author
 */

public class CollidingObject extends GameObject {


    /**
     * Provides sprite for colliding objects.
     *
     * @param sprite
     */

    public CollidingObject(Sprite sprite) {
        super(sprite);
    }


    /**
     * Provides Sprite for colliding objects with coordinates.
     *
     * @param sprite
     * @param x
     * @param y
     */

    CollidingObject(Sprite sprite, float x, float y) {
        super(sprite);
        this.setSpritePosition(x, y);
    }


    /**
     * Contructs the instance with provided id and coordinates.
     *
     * @param id
     * @param x
     * @param y
     */

    public CollidingObject(int id, float x, float y) {
        super(id, x, y);
    }


    /**
     * Gets the surrounding Colliding box for current sprite.
     *
     * @return Rectangle
     */

    public Rectangle getBoundingRectangle() {

        return sprite.getBoundingRectangle();
    }


    /**
     * Returns the right colliding rectangle for this instance.
     *
     * @return Rectangle
     */

    public Rectangle getRightRectangle() {
        return new Rectangle(this.getX() + getWidth() + 5, this.getY() + 10, 5, getHeight() - 50);
    }

    /**
     * Returns the bottom colliding rectangle for this instance.
     *
     * @return Rectangle
     */

    public Rectangle getBottomRectangle() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth() - 10, 5);
    }

    /**
     * Returns the top colliding rectangle  for this instance.
     *
     * @return Rectangle
     */

    public Rectangle getTopRectangle() {
        return new Rectangle(this.getX(), this.getY() + this.getHeight(), this.getWidth() - 10, 5);
    }

    /**
     * Checks if instance is collided with on any side from provided Rectangle.
     *
     * @param rect
     * @return boolean
     */

    public boolean checkCollision(Rectangle rect) {
        return Intersector.overlaps(this.getBoundingRectangle(), rect);
    }

}
