package Objects;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class GameObject {
    protected Sprite sprite;


    GameObject(Sprite sprite){
        this.sprite = sprite;
    }

    public float getX(){
        return this.sprite.getX();
    }

    public float getY(){
        return this.sprite.getY();
    }

    public void setSpritePosition(float x, float y){
        sprite.setPosition(x,y);
    }

    public float getWidth(){
        return sprite.getWidth();
    }

    public float getHeight(){
        return sprite.getHeight();
    }

    public void draw(Batch batch){

        sprite.draw(batch);
    }

}
