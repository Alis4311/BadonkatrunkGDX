package Objects;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Entity superclass for objects in the game.
 * @author Christoffer Book, Tim Normark
 */
public abstract class GameObject {
    protected Sprite sprite;

    /**
     * Constructor for a GameObject
     * @param sprite - The sprite to apply to the gameobject.
     */
    GameObject(Sprite sprite){
        this.sprite = sprite;
    }

    /**
     * Get the X coordinate where the gameobject is.
     * @return the X Coordinate of the gameobject.
     */
    public float getX(){
        return this.sprite.getX();
    }
    /**
     * Get the X coordinate where the gameobject is.
     * @return the X Coordinate of the gameobject.
     */
    public float getY(){
        return this.sprite.getY();
    }

    /**
     * Set the position of the object.
     * @param x - the new X coordinate.
     * @param y - the new Y coordinate.
     */
    public void setSpritePosition(float x, float y){
        sprite.setPosition(x,y);
    }

    /**
     * Get the width of the object.
     * @return the width.
     */
    public float getWidth(){
        return sprite.getWidth();
    }

    /**
     * Get the height of the object.
     * @return the height
     */
    public float getHeight(){
        return sprite.getHeight();
    }

    /**
     * Method for the gameobject to draw itself onto a batch.
     * @param batch
     */
    public void draw(Batch batch){

        sprite.draw(batch);
    }

}
