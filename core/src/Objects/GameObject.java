package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {
    protected Sprite sprite;
    protected int id;

    public GameObject(Sprite sprite){
        this.sprite = sprite;
    }

    public GameObject(int id, float x, float y){
        sprite = getSpriteForID(id);
        this.id = id;
        setSpritePosition(x,y);
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

    public void setX(float x){
        this.sprite.setX(x);
    }

    public void setY(float y){
        this.sprite.setY(y);
    }

    public void setSize(float width, float height){
        this.sprite.setSize(width,height);
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

    public int getId(){
        System.out.println(this.id);
        return this.id;

    }

    private Sprite getSpriteForID(int id){
        Texture texture = new Texture(Gdx.files.internal("error.png"));
        switch(id){
            case 0: texture = new Texture(Gdx.files.internal("cobble.png"));
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9: texture = new Texture(Gdx.files.internal("finish.png"));
                break;
            case 10: texture =  new Texture(Gdx.files.internal("house.png"));
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
            case 17:
                break;
        }

        return new Sprite(texture);
    }


}
