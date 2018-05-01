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
        public int highestUnlockedLevel = 0;

		@Override
		public void create() {
			FileHandle file = Gdx.files.local("unlockedLevels.txt");
			file.writeString("3",false);
            System.out.println(Gdx.files.internal("cityBackground.png").file().getAbsolutePath());
			System.out.println(file.file().getAbsolutePath());
			try {
				BufferedReader br = new BufferedReader(file.reader());
				highestUnlockedLevel = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			batch = new SpriteBatch();
			System.out.println(highestUnlockedLevel);
			this.setScreen(new MenuScreen(this));
		}

		@Override
		public void render() {
            super.render();
		}
}

