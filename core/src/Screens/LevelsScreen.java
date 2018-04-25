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

        btn1 = menuButton.CreateImageButton("number1.png", 64, 320);
        btn2 = menuButton.CreateImageButton("number2.png", 192, 320);
        btn3 = menuButton.CreateImageButton("number3.png", 320, 320);
        btn4 = menuButton.CreateImageButton("number4.png", 448, 320);
        btn5 = menuButton.CreateImageButton("number5.png", 128, 192);
        btn6 = menuButton.CreateImageButton("number6.png", 256, 192);
        btn7 = menuButton.CreateImageButton("number7.png", 384, 192);
        btn8 = menuButton.CreateImageButton("number8.png", 128, 64);
        btn9 = menuButton.CreateImageButton("number9.png", 256, 64);
        btn10 = menuButton.CreateImageButton("number10.png", 384, 64);

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
                badonkatrunk.setScreen(new GameScreen(badonkatrunk, 1));
            }
        });
        btn2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                badonkatrunk.setScreen(new GameScreen(badonkatrunk, 2));
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

        switch(badonkatrunk.highestUnlockedLevel) {
            case 1 : btn2.setDisabled(true);
            case 2 : btn3.setDisabled(true);
            case 3 : btn4.setDisabled(true);
            case 4 : btn5.setDisabled(true);
            case 5 : btn6.setDisabled(true);
            case 6 : btn7.setDisabled(true);
            case 7 : btn8.setDisabled(true);
            case 8 : btn9.setDisabled(true);
            case 9 : btn10.setDisabled(true);
            case 10 : ;
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            badonkatrunk.setScreen(new GameScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
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
