package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollidingObject extends GameObject {
    protected Rectangle boundingRectangle;

    public CollidingObject(){

    }
    public CollidingObject(Sprite sprite){
        super(sprite);
        this.boundingRectangle = sprite.getBoundingRectangle();
    }

    public Rectangle getBoundingRectangle(){
        return this.boundingRectangle;
    }


    public boolean checkCollision(Rectangle rect){
        return Intersector.overlaps(this.boundingRectangle, rect);
    }




}
