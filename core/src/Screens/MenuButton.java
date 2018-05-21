package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * @author Daniel Rosdahl
 */

/**
 * The class serves as a simpler way of creating an ImageButton that is used in the different Screens
 */
class MenuButton{

    /**
     * Method that creates an ImageButton
     *
     * @param imagePath the file location of an image
     * @param x the x coordinate for the button
     * @param y the y coordinate for the button
     * @return the newly created ImageButton
     */
    public ImageButton CreateImageButton(String imagePath, int x, int y) {
        Texture texture = new Texture(Gdx.files.internal(imagePath));
        TextureRegion textureRegion = new TextureRegion(texture);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(textureRegion);
        ImageButton menuButton = new ImageButton(textureRegionDrawable);
        menuButton.setPosition(x, y);

        return menuButton;
    }
}