package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollidingObject extends GameObject {


    public CollidingObject(Sprite sprite){
        super(sprite);

    }

    public Rectangle getBoundingRectangle(){

        return new Rectangle(getX(),getY(),getWidth(),getHeight());
    }


    public boolean checkCollision(Rectangle rect){
        return true;
    }




}
