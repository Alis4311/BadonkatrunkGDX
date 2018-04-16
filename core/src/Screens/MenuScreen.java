package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.chris.badonkatrunk.Badonkatrunk;

public class MenuScreen implements Screen {

    private MenuButton menuButton = new MenuButton();
    private Stage stage;
    private ImageButton buttonPlay;
    private ImageButton buttonLevels;
    private ImageButton buttonExit;

    private Badonkatrunk badonkatrunk;

    public MenuScreen(final Badonkatrunk badonkatrunk){
        this.badonkatrunk = badonkatrunk;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttonPlay = menuButton.CreateImageButton("PlayButton.png", 150, 350);
        buttonLevels = menuButton.CreateImageButton("LevelsButton.png", 150, 200);
        buttonExit = menuButton.CreateImageButton("ExitButton.png", 150, 50);
        stage.addActor(buttonPlay);
        stage.addActor(buttonLevels);
        stage.addActor(buttonExit);

        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new GameScreen(badonkatrunk));
                stage.dispose();
            }
        });
        buttonLevels.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LevelsScreen(badonkatrunk));
                stage.dispose();
            }
        });
        buttonExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
        stage.draw();

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
