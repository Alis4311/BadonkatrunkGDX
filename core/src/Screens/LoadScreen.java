package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chris.badonkatrunk.Badonkatrunk;

import static com.badlogic.gdx.Gdx.gl;

public class LoadScreen implements Screen {
    private Badonkatrunk badonkatrunk;
    private Texture logo;
    private SpriteBatch spriteBatch;

    public LoadScreen(Badonkatrunk badonkatrunk) {
        this.badonkatrunk = badonkatrunk;
    }

    @Override
    public void show() {
        logo = new Texture(Gdx.files.internal("error.png"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(logo, 0, 0);
        spriteBatch.end();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        //badonkatrunk.setScreen(new GameScreen(badonkatrunk));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
