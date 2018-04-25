package Screens;

import MapTest.Map;
import MapTest.MapLoader;
import Vehicles.VehicleFactory;
import Vehicles.Vehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.chris.badonkatrunk.Badonkatrunk;


public class GameScreen implements Screen {
    OrthographicCamera camera;
    Vehicle vehicle;
    Map level;
    SpriteBatch batch;
    ShapeRenderer shape;

    private Badonkatrunk badonkatrunk;

    public GameScreen(Badonkatrunk badonkatrunk, int mapNbr) {
        this.badonkatrunk = badonkatrunk;
        batch = badonkatrunk.batch;
        //level  = new Map(new Sprite(new Texture(Gdx.files.internal("cityBackground.png"))));
        level = MapLoader.load(mapNbr);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,500,500);
        vehicle = VehicleFactory.create(level);
        shape = new ShapeRenderer();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

				//TODO: Write reset method

        //Kolla om kameran nått slutet av banan, om inte så ska den röra sig åt höger.
        if (camera.position.x < level.getGoalXCoordinates() && camera.position.x < level.getWidth() -camera.viewportWidth/2) {

            camera.translate(1.5f, 0, 0);
            //camera.position.y = vehicle.getY()/2 + camera.viewportHeight/2;
            camera.update();

				//TODO: Write death method.

            //Kolla om fordonet hamnat bakom kameran, i så fall ska banan starta om.
            if(camera.position.x > vehicle.getX()+camera.viewportWidth/2+vehicle.getBoundingRectangle().width){
                //vehicle.dispose();
                restart();
            }
            //Kolla om bilen "kör om" kameran, i så fall ska kameran följa med bilen frammåt.
            if(vehicle.getX()+vehicle.getBoundingRectangle().width >= camera.position.x + camera.viewportWidth / 2){
                camera.position.x = vehicle.getX()+vehicle.getBoundingRectangle().width - camera.viewportWidth / 2;
            }
        }


        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        vehicle.update();


        //Kolla om fordonet är i överkant av kamerans vy, i så fall följer kameran med uppåt, men inte ovanför bakgrundsbilden.
        if(vehicle.getY() > camera.position.y + camera.viewportHeight/4 && camera.position.y + camera.viewportHeight /2 <= level.getHeight()) {
            camera.position.y = vehicle.getY() - camera.viewportWidth / 4;
            //Fullösning? om kameran hamnar ovanför bakgrundsbilden så hoppar den ner till bakgrundsbildens övre kant.
            if(camera.position.y + camera.viewportHeight / 2 > level.getHeight()) {
                camera.position.y = level.getHeight() - camera.viewportHeight / 2;
            }
        }
        //Kolla om fordonet är i underkant av kamerans vy, i så fall följer kameran med nedåt.
        else if(vehicle.getY() < camera.position.y - camera.viewportHeight/4 && camera.position.y >= camera.viewportHeight /2) {
            camera.position.y = vehicle.getY() + camera.viewportHeight / 4;
            //Fullösning? om kameran hamnar nedanför bakgrundsbilden så hoppar den upp till bakgrundsbildens nedre kant.
            if(camera.position.y -camera.viewportHeight / 2 < 0) {
                camera.position.y = camera.viewportHeight / 2;
            }
        }

        //Kolla om fordonet hamnat nedanför kamerans synfält
        if(vehicle.getY() + vehicle.getHeight() < camera.position.y - camera.viewportHeight/2) {
            restart();
        }

        //Kolla om fordonet kommit i mål.
        if(vehicle.getX() >= level.getGoalXCoordinates()){
            restart();
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        level.draw(batch);
        vehicle.draw(batch);

        batch.end();
        /*shape.begin(ShapeRenderer.ShapeType.Line);
        shape.rect(vehicle.getTopRectangle().x,vehicle.getTopRectangle().y,vehicle.getTopRectangle().width,vehicle.getTopRectangle().height);
        shape.rect(vehicle.getBottomRectangle().x,vehicle.getBottomRectangle().y,vehicle.getBottomRectangle().width,vehicle.getBottomRectangle().height);
        shape.rect(vehicle.getRightRectangle().x,vehicle.getRightRectangle().y,vehicle.getRightRectangle().width,vehicle.getRightRectangle().height);
        shape.end();*/

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
        vehicle.dispose();
    }

    public void restart(){
        badonkatrunk.setScreen(new GameScreen(badonkatrunk, badonkatrunk.highestUnlockedLevel));
        vehicle.dispose();
        this.dispose();
    }
}
