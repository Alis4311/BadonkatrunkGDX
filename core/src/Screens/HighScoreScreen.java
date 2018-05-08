package Screens;

import ClientHighScore.ClientConnection;
import ClientHighScore.HighScore;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;

import java.util.ArrayList;

public class HighScoreScreen implements Screen {


    private Stage stage;
    private Camera camera;

    private BitmapFont font;
    private Skin skinButton;
    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle textButtonStyle;
    private Badonkatrunk badonkatrunk;
    private TextButton[] buttons;

    public HighScoreScreen(final Badonkatrunk badonkatrunk, final int mapNbr, final long time) {

        this.badonkatrunk = badonkatrunk;

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
        buttons = getButtons();
        MenuButton menuButton = new MenuButton();
       ImageButton buttonNextLevel = menuButton.CreateImageButton("nextLevel.png", 225, 400);
        ImageButton buttonLevels = menuButton.CreateImageButton("LevelsButton.png", 20, 400);
        buttonNextLevel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //badonkatrunk.setScreen(new LoadScreen(badonkatrunk));

                badonkatrunk.setScreen(new GameScreen(badonkatrunk, Math.min(mapNbr + 1, 10)));
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
        stage.addActor(buttonLevels);
        stage.addActor(buttonNextLevel);
        HighScore highScore = new HighScore(mapNbr, time, badonkatrunk.username);
        //ClientConnection connection = new ClientConnection("192.168.43.22",80,highScore, this);
        ClientConnection connection = new ClientConnection("127.0.0.1",8080,highScore, this);
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
            System.out.println("Congratulations!");
        } else {
            System.out.println("You suck!");
        }
    }

    public void showLeaderboard(ArrayList<HighScore> newLeaderboard){
        for(int i = 0; i<Math.min(10,newLeaderboard.size()); i++){
            buttons[i].setText(10-i + ".     " +newLeaderboard.get(9-i).getUserName() + "      " + newLeaderboard.get(9-i).getMilliSecTime());
        }

    }

    private TextButton[] getButtons(){
        TextButton[] buttons = new TextButton[10];
        for(int i = 0; i<buttons.length; i++){
            buttons[i] = new TextButton("",textButtonStyle);

            buttons[i].setText("");
            buttons[i].setHeight(50);
            buttons[i].setWidth(400);
            buttons[i].setPosition(50,(i*30)+30);
            stage.addActor(buttons[i]);
        }

        return buttons;
    }
}