package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;

import java.util.concurrent.TimeUnit;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

/**
 * Screen to display the WinScreen that is shown after completing a level.
 *
 * @author Tim Normark
 */
public class WinScreen implements Screen {
    private Badonkatrunk badonkatrunk;
    private int mapNbr;
    private Stage stage;
    private BitmapFont font;

    /**
     * Creates instance of the WinScreen. Creates all the GUI-components for the Screen.
     *
     * @param badonkatrunk Reference to the "main"-class, that is responsible for the switching of screens.
     * @param mapNbr The level that has been played, and won.
     * @param levelTime long value representing the milliseconds it took for the player to complete the level.
     */
    public WinScreen(final Badonkatrunk badonkatrunk, final int mapNbr, final long levelTime) {
        this.badonkatrunk = badonkatrunk;
        this.mapNbr = mapNbr;
        long levelTime1 = levelTime;

        double seconds = ((double)levelTime / 1000) % 60 ;

        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        Skin skinButton = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("textButton.txt"));
        skinButton.addRegions(buttonAtlas);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        textButtonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        textButtonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        textButtonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");
        TextButton winButton = new TextButton("Finish", textButtonStyle);
        winButton.setText("Congratulations! \n Time: " + seconds + "s");
        winButton.setHeight(50);
        winButton.setWidth(100);
        winButton.setPosition(200,400);
        stage.addActor(winButton);

        MenuButton menuButton = new MenuButton();
        ImageButton buttonNextLevel = menuButton.CreateImageButton("nextlevelButton.png", 122, 300);
        ImageButton buttonLevels = menuButton.CreateImageButton("levelsButtonBig.png", 122, 200);
        ImageButton buttonHighScore = menuButton.CreateImageButton("highScoreButton.png", 122, 100);

        buttonNextLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, Math.min(mapNbr + 1, 10)));
                WinScreen.this.dispose();

            }
        });
        buttonLevels.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LevelsScreen(badonkatrunk));
                WinScreen.this.dispose();

            }
        });

        buttonHighScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new HighScoreScreen(badonkatrunk, mapNbr, levelTime));
                WinScreen.this.dispose();

            }
        });


        stage.addActor(buttonLevels);
        stage.addActor(buttonHighScore);
        if(mapNbr != 10) {
            stage.addActor(buttonNextLevel);
        }
    }

    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself. The method draws the GUI components of the object on the screen.
     *
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            badonkatrunk.setScreen(new LoadScreen(badonkatrunk, Math.min(mapNbr + 1, 10)));
        }
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

    /**
     * Disposes resources that the object has used, to prevent memory-leak.
     */
    @Override
    public void dispose() {
        font.dispose();
        stage.dispose();

    }

}
