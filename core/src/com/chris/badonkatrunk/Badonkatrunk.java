package com.chris.badonkatrunk;

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

public class Badonkatrunk extends ApplicationAdapter implements InputProcessor {
		SpriteBatch batch;
		Sprite theWorld;
		OrthographicCamera camera;


		@Override
		public void create() {
			batch = new SpriteBatch();
			theWorld = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
			theWorld.setPosition(0, 0);
			theWorld.setSize(50, 25);


			camera = new OrthographicCamera(250, 250);
			camera.position.set(0,0,0);

			Gdx.input.setInputProcessor(this);
		}

		@Override
		public void render() {
			camera.translate(1f, 0f);
			Gdx.gl.glClearColor(1, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			theWorld.draw(batch);
			batch.end();
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyDown(int keycode) {


			return false;
		}

		public void update(){

		}

		@Override
		public boolean keyTyped(char character) {
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}
	}
