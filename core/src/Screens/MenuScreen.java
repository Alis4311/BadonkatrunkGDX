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

/**
 * @ author Peder Nilsson, Daniel Rosdahl
 */

/**
 * Screen that shows the main menu of the game that the
 * will start on if the user has played the game before
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private Badonkatrunk badonkatrunk;

    /**
     * Constructor that sets instances of OrthograpgicCamera, Viewport
     * and Stage which buttons are added to.
     *
     * @param badonkatrunk
     */
    public MenuScreen(final Badonkatrunk badonkatrunk){
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        this.badonkatrunk = badonkatrunk;

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        MenuButton menuButton = new MenuButton();
        ImageButton titlePicture = menuButton.CreateImageButton("badonkatrunk.png", 100, 300);
        titlePicture.setSize(300,300);
        ImageButton buttonPlay = menuButton.CreateImageButton("PlayButton.png", 186, 190);
        ImageButton buttonLevels = menuButton.CreateImageButton("LevelsButton.png", 186, 80);
        ImageButton buttonChangeUsername = menuButton.CreateImageButton("userAvatar.png", 217, 310);
        stage.addActor(buttonPlay);
        stage.addActor(buttonLevels);
        stage.addActor(buttonChangeUsername);
        stage.addActor(titlePicture);

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

    /**
     * Renders the background color and the stage
     *
     * @param delta
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void dispose() {

    }

    /**
     * Plays the menu music
     */

    @Override
    public void show() {
        badonkatrunk.playMenuMusic();
    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void resize(int width, int height) {

    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void pause() {

    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void resume() {

    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void hide() {

    }
}
