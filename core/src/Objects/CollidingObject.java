package Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollidingObject extends GameObject {

    public CollidingObject(Sprite sprite){
        super(sprite);
    }

    public CollidingObject(Sprite sprite, float x, float y){
        super(sprite);
       this.setSpritePosition(x,y);
    }
    public CollidingObject(int id, float x, float y){
        super(id, x, y);
    }

    public Rectangle getBoundingRectangle(){

        return sprite.getBoundingRectangle();
    }

    public Rectangle getRightRectangle(){
        return new Rectangle(this.getX()+getWidth()+5,this.getY()+10,5,getHeight()-50);
    }

    public Rectangle getBottomRectangle(){
        return new Rectangle(this.getX(),this.getY(),this.getWidth()-10,5);
    }

    public Rectangle getTopRectangle(){
        return new Rectangle(this.getX(),this.getY() + this.getHeight(), this.getWidth()-10, 5);
    }

    public boolean checkCollision(Rectangle rect){
        return Intersector.overlaps(this.getBoundingRectangle(),rect);
    }

}
