package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.chris.badonkatrunk.Badonkatrunk;

public class LevelsScreen implements Screen{
    private MenuButton menuButton = new MenuButton();
    private Stage stage;
    private ImageButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;

    private Badonkatrunk badonkatrunk;

    public LevelsScreen(final Badonkatrunk badonkatrunk){
        this.badonkatrunk = badonkatrunk;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        btn1 = menuButton.CreateImageButton("ButtonPictures/btn1.png", 50, 380);
        btn2 = menuButton.CreateImageButton("ButtonPictures/btn2.png", 150, 380);
        btn3 = menuButton.CreateImageButton("ButtonPictures/btn3.png", 250, 380);
        btn4 = menuButton.CreateImageButton("ButtonPictures/btn4.png", 350, 380);
        btn5 = menuButton.CreateImageButton("ButtonPictures/btn5.png", 100, 240);
        btn6 = menuButton.CreateImageButton("ButtonPictures/btn6.png", 200, 240);
        btn7 = menuButton.CreateImageButton("ButtonPictures/btn7.png", 300, 240);
        btn8 = menuButton.CreateImageButton("ButtonPictures/btn8.png", 100, 100);
        btn9 = menuButton.CreateImageButton("ButtonPictures/btn9.png", 200, 100);
        btn10 = menuButton.CreateImageButton("ButtonPictures/btn10.png", 300, 100);

        stage.addActor(btn1);
        stage.addActor(btn2);
        stage.addActor(btn3);
        stage.addActor(btn4);
        stage.addActor(btn5);
        stage.addActor(btn6);
        stage.addActor(btn7);
        stage.addActor(btn8);
        stage.addActor(btn9);
        stage.addActor(btn10);

        btn1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new GameScreen(badonkatrunk));
            }
        });
        btn2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn5.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn6.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn7.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn8.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn9.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        btn10.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glBlendColor(255,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
