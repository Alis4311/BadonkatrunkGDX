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

import java.util.ArrayList;

public class LevelsScreen implements Screen{
    private MenuButton menuButton = new MenuButton();
    private Stage stage;
    private ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btnBack;
    private ImageButton[] buttons;
    private Camera camera;
    private Badonkatrunk badonkatrunk;

    public LevelsScreen(final Badonkatrunk badonkatrunk){
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(600, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        buttons = new ImageButton[10];
        btnBack = menuButton.CreateImageButton("arrowLeft.png", 10, 450);
        buttons[0] = menuButton.CreateImageButton("number1.png", 64, 320);
        buttons[1] = menuButton.CreateImageButton("number2.png", 192, 320);
        buttons[2] = menuButton.CreateImageButton("number3.png", 320, 320);
        buttons[3] = menuButton.CreateImageButton("number4.png", 448, 320);
/*        ImageButton lock4 = menuButton.CreateImageButton("lock.png", (int)(btn4.getX()+btn4.getWidth()-64),(int)btn4.getY()+64);
        lock4.setSize(64,64);*/
        buttons[4] = menuButton.CreateImageButton("number5.png", 128, 192);
        buttons[5] = menuButton.CreateImageButton("number6.png", 256, 192);
        buttons[6] = menuButton.CreateImageButton("number7.png", 384, 192);
        buttons[7] = menuButton.CreateImageButton("number8.png", 128, 64);
        buttons[8] = menuButton.CreateImageButton("number9.png", 256, 64);
        buttons[9] = menuButton.CreateImageButton("number10.png", 384, 64);

        stage.addActor(btnBack);
        for(int i = 0; i <buttons.length; i++){
            stage.addActor(buttons[i]);
        }
        btnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new MenuScreen(badonkatrunk));
                stage.dispose();
            }
        });
        buttons[0].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 1));
                stage.dispose();
            }
        });
        buttons[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 2));
                stage.dispose();
            }
        });
        buttons[2].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 3));
                stage.dispose();
            }
        });
        buttons[3].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 4));
                stage.dispose();
            }
        });
        buttons[4].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 5));
                stage.dispose();
            }
        });
        buttons[5].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 6));
                stage.dispose();
            }
        });
        buttons[6].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 7));
                stage.dispose();
            }
        });
        buttons[7].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 8));
                stage.dispose();
            }
        });
        buttons[8].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 9));
                stage.dispose();
            }
        });
        buttons[9].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new LoadScreen(badonkatrunk, 10));
                stage.dispose();
            }
        });

        for(int i = 0; i<10; i++){
            if(i+1 > badonkatrunk.highestUnlockedLevel){
                buttons[i].setDisabled(true);
                ImageButton button = menuButton.CreateImageButton("lock.png", (int)(buttons[i].getX()+buttons[i].getWidth()-64),(int)buttons[i].getY()+64);
                button.setSize(64,64);
                stage.addActor(button);
            }
        }

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
