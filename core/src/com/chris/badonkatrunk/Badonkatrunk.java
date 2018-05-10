package com.chris.badonkatrunk;

import Music.BadonkaMusic;
import Screens.EnterNameScreen;
import Screens.MenuScreen;

import Screens.WinScreen;
import Vehicles.VehicleFactory;
import com.badlogic.gdx.*;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Badonkatrunk extends Game{
        public static SpriteBatch batch;
        public int highestUnlockedLevel = 0;
        public String username;
        private BadonkaMusic menuMusic;
        private BadonkaMusic gameMusic;


		@Override
		public void create() {
			menuMusic = new BadonkaMusic("loop2.ogg");
			gameMusic = new BadonkaMusic("loop2.ogg");
			FileHandle file = Gdx.files.local("unlockedLevels.txt");
			FileHandle fileInternal = Gdx.files.internal("unlockedLevels.txt");
			if(!file.exists()){
				fileInternal.copyTo(file);
			}

			try {
				BufferedReader br = new BufferedReader(file.reader());
				highestUnlockedLevel = Integer.parseInt(br.readLine());
                //highestUnlockedLevel = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			batch = new SpriteBatch();
			System.out.println(highestUnlockedLevel);

			FileHandle fileUsername = Gdx.files.local("username.txt");
			FileHandle usernameInternal = Gdx.files.internal("username.txt");
			if(!fileUsername.exists()){
				usernameInternal.copyTo(fileUsername);
			}
			String usernameFromFile = fileUsername.readString();
			if(usernameFromFile.isEmpty()){

				this.setScreen(new EnterNameScreen(this));
			}
			else{
				this.username = usernameFromFile;
				this.setScreen(new MenuScreen(this));
				//this.setScreen(new WinScreen(this,1,9000));
			}



		}

		@Override
		public void render() {
            super.render();
		}

		public void playMenuMusic() {
			menuMusic.play();
		}

		public void stopMenuMusic() {
			menuMusic.fadeOut();
		}

		public void playGameMusic() {
			gameMusic.play();
		}

		public void stopGameMusic() {
			gameMusic.fadeOut();
		}
}

