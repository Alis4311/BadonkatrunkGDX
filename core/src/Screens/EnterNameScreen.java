package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;
import com.sun.javafx.scene.control.skin.TextFieldSkin;

/**
 * @author Daniel Rosdahl, Peder Nilsson
 */

/**
 * Class that is used to save a username to a file.
 */
public class EnterNameScreen implements Screen {
    private Stage stage;
    private TextField tfUsername;
    private Badonkatrunk badonkatrunk;
    private ImageButton btnContinue;

    /**
     * Constructor that sets instances of OrthograpgicCamera, Viewport
     * and Stage which a button and textfield is added to.
     *
     * @param badonkatrunk
     */
    public EnterNameScreen(final Badonkatrunk badonkatrunk){
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        this.badonkatrunk = badonkatrunk;

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        style.fontColor = Color.YELLOW;


        tfUsername = new TextField("",style);
        tfUsername.setMessageText("Enter nick here!\n- - -");
        tfUsername.setAlignment(Align.center);
        tfUsername.setMaxLength(3);
        tfUsername.setPosition(70, 250, Align.center);
        tfUsername.setHeight(80);
        tfUsername.setWidth(500);
        style.font.getData().setScale(3,3);


        stage.addActor(tfUsername);
        MenuButton menuButton = new MenuButton();
        this.btnContinue = menuButton.CreateImageButton("arrowRight.png", 360, 360);
        this.btnContinue.setSize(100, 100);
        stage.addActor(btnContinue);

        btnContinue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                writeUsername();
                badonkatrunk.setScreen(new MenuScreen(badonkatrunk));
                stage.dispose();

            }
        });
    }

    /**
     * writes the username from the textfield
     */

    public void writeUsername(){
        FileHandle fileUsername = Gdx.files.local("username.txt");
        String username = tfUsername.getText().toUpperCase();
        System.out.println(username);
        badonkatrunk.username = username;
        fileUsername.writeString(username, false);
    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void show() {

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

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void dispose() {

    }
}
