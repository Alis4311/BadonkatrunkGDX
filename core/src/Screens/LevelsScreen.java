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


/**
 * Screen showing 10 levels. Levelbuttons are pictures and are checked every time if Level
 * is unlocked or locked - this is shown with another picture and buttons are
 * .setEnabled() true or false accordingly.
 *
 * @ author  XXX & Peder Nilsson
 */

class LevelsScreen implements Screen {
    private Stage stage;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private ImageButton btn6;
    private ImageButton btn7;
    private ImageButton btn8;
    private ImageButton btn9;
    private ImageButton btn10;
    private Camera camera;
    private Badonkatrunk badonkatrunk;


    /**
     * Construct this screen in provided Batch.
     * Sets up instances of OrtographicCamera, Viewport, Stage and adds listener to every button.
     *
     * @param badonkatrunk
     */

    public LevelsScreen(final Badonkatrunk badonkatrunk) {
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(600, 600, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        ImageButton[] buttons = new ImageButton[13];
        MenuButton menuButton = new MenuButton();
        ImageButton btnBack = menuButton.CreateImageButton("arrowLeft.png", 64, 480);
        btnBack.setSize(100,100);
        ImageButton titlePicture = menuButton.CreateImageButton("badonkatrunk.png", 220, 400);
        titlePicture.setSize(300,300);
        buttons[0] = menuButton.CreateImageButton("number1.png", 64, 320);
        buttons[1] = menuButton.CreateImageButton("number2.png", 192, 320);
        buttons[2] = menuButton.CreateImageButton("number3.png", 320, 320);
        buttons[3] = menuButton.CreateImageButton("number4.png", 448, 320);
        buttons[4] = menuButton.CreateImageButton("number5.png", 192, 192);
        buttons[5] = menuButton.CreateImageButton("number6.png", 320, 192);
        buttons[6] = menuButton.CreateImageButton("number7.png", 448, 192);
        buttons[7] = menuButton.CreateImageButton("number8.png", 192, 64);
        buttons[8] = menuButton.CreateImageButton("number9.png", 320, 64);
        buttons[9] = menuButton.CreateImageButton("number10.png", 448, 64);
        buttons[10] = menuButton.CreateImageButton("carLevelscreen.png", 315, 450);
        buttons[11] = menuButton.CreateImageButton("tractorLevelscreen.png", 78, 180);
        buttons[12] = menuButton.CreateImageButton("RocketLevelscreen.png", 64, 64);


        // adds  every button on stage.
        stage.addActor(titlePicture);
        stage.addActor(btnBack);
        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setSize(100,100);
            stage.addActor(buttons[i]);
        }


        // adds listeners to every button.

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

        //Checks if level is not completed, disable button and adds a picture of a lock

        for (int i = 0; i < 10; i++) {
            if (i + 1 > badonkatrunk.highestUnlockedLevel) {
                buttons[i].setDisabled(true);
                ImageButton button = menuButton.CreateImageButton("lock.png", (int) (buttons[i].getX() + buttons[i].getWidth() - 35), (int) buttons[i].getY() - 44);
                button.setSize(64, 64);
                stage.addActor(button);
            }
        }

    }


    /**
     * Renders information and sets color.
     *
     * @param delta
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            badonkatrunk.setScreen(new LoadScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
        }
    }


    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void dispose() {

    }

    /**
     * Compulsory inherited method - superclass version is used.
     */

    @Override
    public void show() {

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
