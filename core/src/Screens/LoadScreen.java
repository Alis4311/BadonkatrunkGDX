package Screens;

import Map.Map;
import Map.MapLoader;
import Vehicles.Vehicle;
import Vehicles.VehicleFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chris.badonkatrunk.Badonkatrunk;

/**
 * Screen that displays the load screen and loads the resources necessary for playing the level.
 *
 * @author Tim Normark
 */
class    LoadScreen implements Screen {
    private Badonkatrunk badonkatrunk;
    private int mapNbr;
    private int counter = 0;
    private Stage stage;

    /**
     * Creates instance of LoadScreen
     * @param badonkatrunk Reference to the "main"-class, that is responsible for the switching of screens.
     * @param mapNbr The level chosen by the player.
     */
    public LoadScreen(Badonkatrunk badonkatrunk, int mapNbr) {
        this.badonkatrunk = badonkatrunk;
        this.mapNbr = mapNbr;

        Camera camera = new OrthographicCamera();
        Viewport viewport = new StretchViewport(500, 500, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        MenuButton menuButton = new MenuButton();
        ImageButton loadImage = menuButton.CreateImageButton("loadingBackground.png", 0, 0);
        ImageButton helmet = menuButton.CreateImageButton("helmet8.png", 150, 250);
        helmet.setSize(200,200);
        stage.addActor(loadImage);
        stage.addActor(helmet);
    }

    /**
     * Called when this screen becomes the current screen for the Badonkatrunk object. The method stops the music
     * that is played in all the Menus of the game.
     */
    @Override
    public void show() {
        badonkatrunk.stopMenuMusic();
    }

    /**
     * Called when the screen should render itself. The method draws the GUI components of the object on the screen.
     * While showing the "load screen" the method loads the resources for the level that is to be played and then changes
     * Screen in Badonkatrunk to GameScreen.
     * @param delta The time in seconds since the last render
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        if(counter > 50) {
            Map level = MapLoader.load(mapNbr);
            Vehicle vehicle = VehicleFactory.create(level);
            badonkatrunk.setScreen(new GameScreen(badonkatrunk, vehicle, level, mapNbr));
            stage.dispose();
        }
        counter++;

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
