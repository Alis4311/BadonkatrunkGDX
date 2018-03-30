package Objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class DecorativeObject extends GameObject {

    public DecorativeObject(Sprite sprite){
        super(sprite);
    }

    public DecorativeObject(Sprite sprite, float x, float y){
        super(sprite);
        this.setSpritePosition(x,y);
    }
}
