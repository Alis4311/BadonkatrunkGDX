package com.chris.badonkatrunk;

import MapTest.Map;
import Screens.GameScreen;
import Screens.MenuScreen;
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

public class Badonkatrunk extends Game{
        public SpriteBatch batch;

		@Override
		public void create() {
            batch = new SpriteBatch();
			this.setScreen(new MenuScreen(this));
		}

		@Override
		public void render() {
            super.render();

		}


}

