package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuButton{
    private ImageButton menuButton;
    private Texture texture;
    private TextureRegion textureRegion;
    private TextureRegionDrawable textureRegionDrawable;

    public ImageButton CreateImageButton(String imagePath, int x, int y) {
        texture = new Texture(Gdx.files.internal(imagePath));
        textureRegion = new TextureRegion(texture);
        textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        menuButton = new ImageButton(textureRegionDrawable);
        menuButton.setPosition(x, y);

        return menuButton;
    }
}