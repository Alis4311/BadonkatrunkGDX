package com.chris.badonkatrunk;

import Screens.MenuScreen;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Badonkatrunk extends Game{
        public static SpriteBatch batch;

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

