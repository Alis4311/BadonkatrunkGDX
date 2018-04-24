package com.chris.badonkatrunk;

import Screens.MenuScreen;

import Vehicles.VehicleFactory;
import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Badonkatrunk extends Game{
        public static SpriteBatch batch;
		public int currentlevel;
		@Override
		public void create() {
			currentlevel = 0;
			batch = new SpriteBatch();
			this.setScreen(new MenuScreen(this));
		}

		@Override
		public void render() {
            super.render();
		}
}

