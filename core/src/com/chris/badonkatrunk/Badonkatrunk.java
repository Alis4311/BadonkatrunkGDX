package com.chris.badonkatrunk;

import Vehicles.Car;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import javafx.scene.input.KeyCode;

public class Badonkatrunk extends ApplicationAdapter{
		SpriteBatch batch;
		Sprite theWorld;
		OrthographicCamera camera;
		float cameraY;
		Car car;


		@Override
		public void create() {

			batch = new SpriteBatch();
			theWorld = new Sprite(new Texture(Gdx.files.internal("test.png")));
			theWorld.setPosition(0, 0);
			cameraY = -0.1f;
            car = new Car();
			camera = new OrthographicCamera(240, 100);
			camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

		}

		@Override
		public void render() {
			if(camera.position.x < theWorld.getWidth()-camera.viewportWidth/2){
				camera.translate(1f,car.getImage().getY(),0);
				car.setPosition((int)car.getImage().getX()+2,(int)car.getImage().getY());
			}
			Gdx.gl.glClearColor(0, 1, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			theWorld.draw(batch);
			car.getImage().draw(batch);
			batch.end();

		}
	}
