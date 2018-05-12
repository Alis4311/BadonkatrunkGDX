package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;

public class MenuScreen implements Screen {

    private MenuButton menuButton = new MenuButton();
    private Stage stage;
    private ImageButton buttonPlay;
    private ImageButton buttonLevels;
    private ImageButton buttonChangeUsername;
    private Camera camera;

    private Badonkatrunk badonkatrunk;

    public MenuScreen(final Badonkatrunk badonkatrunk){
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        this.badonkatrunk = badonkatrunk;

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        buttonPlay = menuButton.CreateImageButton("PlayButton.png", 186, 300);
        buttonLevels = menuButton.CreateImageButton("LevelsButton.png", 186, 150);
        buttonChangeUsername = menuButton.CreateImageButton("changeUser.png", 10, 400);
        buttonChangeUsername.setSize(50,50);
        stage.addActor(buttonPlay);
        stage.addActor(buttonLevels);
        stage.addActor(buttonChangeUsername);

        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
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
        buttonChangeUsername.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new EnterNameScreen(badonkatrunk));
                stage.dispose();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            badonkatrunk.setScreen(new LoadScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {
        badonkatrunk.playMenuMusic();
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
