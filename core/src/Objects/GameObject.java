package Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    protected Sprite sprite;

    public GameObject(){

    }
    public GameObject(Sprite sprite){
        this.sprite = sprite;
    }

    public Float getX(){
        return this.sprite.getX();
    }

    public Float getY(){
        return this.sprite.getY();
    }

    public void setX(float x){
        this.sprite.setX(x);
    }

    public void setY(float y){
        this.sprite.setY(y);
    }

    public void draw(SpriteBatch batch){
        batch.draw(sprite.getTexture(), getX(),getY());
    }


}
