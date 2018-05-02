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

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

public class WinScreen implements Screen {
    private Badonkatrunk badonkatrunk;
    private int mapNbr;
    private long levelTime;

    private Stage stage;
    private MenuButton menuButton = new MenuButton();
    private ImageButton buttonNextLevel;
    private ImageButton buttonLevels;

    private BitmapFont font;
    private Skin skinButton;
    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton winButton;

    public WinScreen(final Badonkatrunk badonkatrunk, final int mapNbr, long levelTime) {
        this.badonkatrunk = badonkatrunk;
        this.mapNbr = mapNbr;
        this.levelTime = levelTime;
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skinButton=new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("textButton.txt"));
        skinButton.addRegions(buttonAtlas);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        textButtonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        textButtonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        textButtonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");
        winButton=new TextButton("Finish",textButtonStyle);
        winButton.setText("Congratulations! \n Time: " + levelTime);
        winButton.setHeight(230);
        winButton.setWidth(500);
        winButton.setPosition(10,10);
        stage.addActor(winButton);

        buttonNextLevel = menuButton.CreateImageButton("PlayButton.png", 150, 300);
        buttonLevels = menuButton.CreateImageButton("LevelsButton.png", 150, 150);

        buttonNextLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //badonkatrunk.setScreen(new LoadScreen(badonkatrunk));

                badonkatrunk.setScreen(new GameScreen(badonkatrunk, mapNbr + 1));
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

        stage.addActor(buttonNextLevel);
        stage.addActor(buttonLevels);
    }

    @Override
    public void show() {
        System.out.println(levelTime);
        //badonkatrunk.setScreen(new GameScreen(badonkatrunk, mapNbr + 1));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            badonkatrunk.setScreen(new GameScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
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

    @Override
    public void dispose() {

    }

}
