package com.chris.badonkatrunk;

import MapTest.Map;
import Vehicles.Car;
import Vehicles.Vehicle;
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
		OrthographicCamera camera;
		float cameraY;
		Vehicle vehicle;
		Map level;
		@Override
		public void create() {
			batch = new SpriteBatch();
			level  = new Map(new Sprite(new Texture(Gdx.files.internal("test.png"))));
			cameraY = -0.1f;
            vehicle = new Car(level);

			camera = new OrthographicCamera(500, 400);

			camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
			//camera.position.set(camera.viewportWidth/2,vehicle.getY()/2,0);
		}

		@Override
		public void render() {
			/*
				TODO: Write reset method
			 */
			if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				vehicle.dispose();

				//this.dispose();
				this.create();

			}
			if (camera.position.x < level.getWidth() - camera.viewportWidth / 2) {

				camera.translate(1f, 0, 0);
				//camera.position.y = vehicle.getY()/2 + camera.viewportHeight/2;
				camera.update();
				/*
				TODO: Write death method.
				 */
				if(camera.position.x > vehicle.getX()+camera.viewportWidth/2+vehicle.getBoundingRectangle().width){
					vehicle.dispose();
					this.create();
				}
			}

			if(vehicle.getX()+vehicle.getBoundingRectangle().width >= camera.viewportWidth){
				camera.position.x = vehicle.getX()+vehicle.getBoundingRectangle().width;
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


}

