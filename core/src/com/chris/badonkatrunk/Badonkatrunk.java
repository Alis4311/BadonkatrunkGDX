package com.chris.badonkatrunk;


import Testing.TestVehicleTest;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import Music.BadonkaMusic;
import Screens.EnterNameScreen;
import Screens.MenuScreen;
import Screens.WinScreen;
import Vehicles.VehicleFactory;

/**
 * Sets up the batch for Badonkatrunk with 'MenuScreen' as first screen,
 * reads username and previous progress from file.
 * If no previous activity registerd a file is created.
 * 'EnterNameScreen'is set up, loaded and ready for use.
 *
 * @ author Tim Normark,
 */

public class Badonkatrunk extends Game {
    private BadonkaMusic menuMusic;
    public int highestUnlockedLevel = 1;
    public static SpriteBatch batch;
    public String username;


    /**
     * Creates instances of a class for playing the music, reads
     * previously logged activity and username.
     * If no previous activity a username is asked for when button is pressed.
     */

    @Override
    public void create() {
        menuMusic = new BadonkaMusic("loop3.mp3");
        FileHandle file = Gdx.files.local("unlockedLevels.txt");
        FileHandle fileInternal = Gdx.files.internal("unlockedLevels.txt");
        //Since we need to be able to write to this file, we need to copy it to local storage.
        if (!file.exists()) {
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
        FileHandle usernameInternal = Gdx.files.internal("username.txt");
//        if (!fileUsername.exists()) {
//            usernameInternal.copyTo(fileUsername);
//        }
//        String usernameFromFile = fileUsername.readString();
//        if (usernameFromFile.isEmpty()) {
//
//            this.setScreen(new EnterNameScreen(this));
//        } else {
//            this.username = usernameFromFile;
//            this.setScreen(new MenuScreen(this));
//        }
        new TestVehicleTest();
    }


    /**
     * Renders data.
     */

    @Override
    public void render() {
        super.render();
    }


    /**
     * Plays music in menu.
     */

    public void playMenuMusic() {
        menuMusic.play();
    }


    /**
     * Music fade out at screen exit.
     */

    public void stopMenuMusic() {
        menuMusic.fadeOut();
    }


}

