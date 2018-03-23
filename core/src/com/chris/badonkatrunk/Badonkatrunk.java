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
		Vehicle car;
		Map level;

		boolean jump = true;

		@Override
		public void create() {
			batch = new SpriteBatch();
			level  = new Map(new Sprite(new Texture(Gdx.files.internal("test.png"))));
			cameraY = -0.1f;
            car = new Car();

			camera = new OrthographicCamera(580, 200);
			camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		}

		@Override
		public void render() {
			if(camera.position.x < level.getWidth()-camera.viewportWidth/2){
				camera.translate(1f,car.getY(),0);

				// Tim la till detta, enbart för att testa Vehicle:s accelerate och idling metoder.
				// Fordonet gasar tills den kommer förbi mitten av kamerans vy. Där släpper den på gasen.
				if(car.getY() <= 0) {
					car.setGrounded(true);
				}
				if(car.getX() < camera.position.x) {
					car.accelerate();
				} else {
					if(jump) {
						car.jump();
						jump = false;
					}
					car.idling();
				}
			}
			Gdx.gl.glClearColor(0, 1, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			level.getBackground().draw(batch);
			car.draw(batch);

			batch.end();

		}
	}
