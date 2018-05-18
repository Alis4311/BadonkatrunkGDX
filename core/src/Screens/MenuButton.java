package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class MenuButton{

    public ImageButton CreateImageButton(String imagePath, int x, int y) {
        Texture texture = new Texture(Gdx.files.internal(imagePath));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        ImageButton menuButton = new ImageButton(textureRegionDrawable);
        menuButton.setPosition(x, y);

        return menuButton;
    }
}