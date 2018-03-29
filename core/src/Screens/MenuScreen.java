package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.chris.badonkatrunk.Badonkatrunk;

public class MenuScreen implements Screen {
    private Badonkatrunk badonkatrunk;

    public MenuScreen(Badonkatrunk badonkatrunk){
        this.badonkatrunk = badonkatrunk;

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            badonkatrunk.setScreen(new GameScreen(badonkatrunk));
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

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
}
