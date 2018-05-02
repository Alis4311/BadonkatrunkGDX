package com.chris.badonkatrunk;

import Screens.EnterNameScreen;
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
        public String username;

		@Override
		public void create() {

			FileHandle file = Gdx.files.local("unlockedLevels.txt");
			FileHandle fileInternal = Gdx.files.internal("unlockedLevels.txt");
			if(!file.exists()){
				fileInternal.copyTo(file);
			}

			try {
				BufferedReader br = new BufferedReader(file.reader());
				highestUnlockedLevel = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}
			batch = new SpriteBatch();
			System.out.println(highestUnlockedLevel);

			FileHandle fileUsername = Gdx.files.local("username.txt");
			String usernameFromFile = fileUsername.readString();
			if(usernameFromFile.isEmpty()){
				this.username = usernameFromFile;
				this.setScreen(new EnterNameScreen(this));
			}
			else{
				this.setScreen(new MenuScreen(this));
			}
		}

		@Override
		public void render() {
            super.render();
		}
}

