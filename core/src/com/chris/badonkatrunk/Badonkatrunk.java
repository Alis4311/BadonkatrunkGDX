package com.chris.badonkatrunk;

import Screens.MenuScreen;

import Vehicles.VehicleFactory;
import com.badlogic.gdx.*;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.IOException;

public class Badonkatrunk extends Game{
        public static SpriteBatch batch;
        public int highestUnlockedLevel;

		@Override
		public void create() {
			FileHandle file = Gdx.files.local("unlockedLevels.txt");
			try {
				BufferedReader br = new BufferedReader(file.reader());
				highestUnlockedLevel = Integer.parseInt(br.readLine());
			} catch (Exception e) {}
			batch = new SpriteBatch();
			this.setScreen(new MenuScreen(this));
		}

		@Override
		public void render() {
            super.render();
		}
}

