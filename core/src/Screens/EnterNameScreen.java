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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;
import com.sun.javafx.scene.control.skin.TextFieldSkin;


public class EnterNameScreen implements Screen {
    private MenuButton menuButton = new MenuButton();
    private Stage stage;
    private TextField tfUsername;
    private ImageButton btnContinue;
    private Badonkatrunk badonkatrunk;

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
        style.fontColor = new Color(255,255,255,255);



        tfUsername = new TextField("",style);
        tfUsername.setMessageText("Write username here");
        tfUsername.setMaxLength(3);
        tfUsername.setPosition(150, 300);
        tfUsername.setWidth(170);

        stage.addActor(tfUsername);
        btnContinue = menuButton.CreateImageButton("arrowRight.png", 160, 150);
        stage.addActor(btnContinue);

        btnContinue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle fileUsername = Gdx.files.local("username.txt");
                String username = tfUsername.getText().toUpperCase();
                System.out.println(username);
                badonkatrunk.username = username;
                fileUsername.writeString(username, false);

                badonkatrunk.setScreen(new MenuScreen(badonkatrunk));
                stage.dispose();

            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
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
