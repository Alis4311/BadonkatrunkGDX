package Screens;

import MapTest.Map;
import Vehicles.Car;
import Vehicles.Vehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chris.badonkatrunk.Badonkatrunk;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    Vehicle vehicle;
    Map level;
    SpriteBatch batch;


    private Badonkatrunk badonkatrunk;

    public GameScreen(Badonkatrunk badonkatrunk){
        this.badonkatrunk = badonkatrunk;
        batch = badonkatrunk.batch;
        level  = new Map(new Sprite(new Texture(Gdx.files.internal("test.png"))));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400,400);
        vehicle = new Car(level);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

				//TODO: Write reset method


        if (camera.position.x < level.getWidth() - camera.viewportWidth / 2) {

            camera.translate(1f, 0, 0);
            //camera.position.y = vehicle.getY()/2 + camera.viewportHeight/2;
            camera.update();

				//TODO: Write death method.

            if(camera.position.x > vehicle.getX()+camera.viewportWidth/2+vehicle.getBoundingRectangle().width){
                vehicle.dispose();
            }
            if(vehicle.getX()+vehicle.getBoundingRectangle().width >= camera.position.x + camera.viewportWidth / 2){
                camera.position.x = vehicle.getX()+vehicle.getBoundingRectangle().width - camera.viewportWidth / 2;
            }
        }


        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        vehicle.update();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        level.draw(batch);

        batch.end();


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
