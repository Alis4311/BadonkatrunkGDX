package Screens;

import ClientHighScore.ClientConnection;
import ClientHighScore.HighScore;
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

public class HighScoreScreen implements Screen {


    private Stage stage;
    private Camera camera;

    private Badonkatrunk badonkatrunk;

    public HighScoreScreen(final Badonkatrunk badonkatrunk, int mapNbr, long time) {
        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        this.badonkatrunk = badonkatrunk;

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        HighScore highScore = new HighScore(mapNbr, time, badonkatrunk.username);
        ClientConnection connection = new ClientConnection("127.0.0.1",3464,highScore, this);
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

    public void isOnLeaderboard(boolean onLeaderboard){
        if(onLeaderboard){
            //TODO: Display congratulations.
        }
    }

    public void showLeaderboard(ArrayList<HighScore> newLeaderboard){
        for (HighScore score : newLeaderboard) {
            //TODO: compose list and display.
        }
    }
}
