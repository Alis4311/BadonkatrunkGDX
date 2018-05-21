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

class    LoadScreen implements Screen {
    private Badonkatrunk badonkatrunk;
    private int mapNbr;
    private int counter = 0;

    private Stage stage;

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

    @Override
    public void show() {
        badonkatrunk.stopMenuMusic();

    }

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
