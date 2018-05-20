package com.chris.badonkatrunk;


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
 * If no previous activity a file is created.
 * 'EnterNameScreen'is set up and loaded and ready for use.
 *
 * @ author
 */

public class Badonkatrunk extends Game {
    private BadonkaMusic menuMusic;
    private BadonkaMusic gameMusic;
    public int highestUnlockedLevel = 1;
    public static SpriteBatch batch;
    public String username;


    /**
     * Creates instances of two classes for playing the music, reads
     * previoulsy logged activity and username.
     * If no previous activity a username is asked for when button is pressed.
     */

    @Override
    public void create() {
        menuMusic = new BadonkaMusic("loop3.mp3");
        gameMusic = new BadonkaMusic("loop2.ogg");
        FileHandle file = Gdx.files.local("unlockedLevels.txt");
        FileHandle fileInternal = Gdx.files.internal("unlockedLevels.txt");
        if (!file.exists()) {
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
        if (!fileUsername.exists()) {
            usernameInternal.copyTo(fileUsername);
        }
        String usernameFromFile = fileUsername.readString();
        if (usernameFromFile.isEmpty()) {

            this.setScreen(new EnterNameScreen(this));
        } else {
            this.username = usernameFromFile;
            this.setScreen(new MenuScreen(this));
            //this.setScreen(new WinScreen(this,1,9000));
        }
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


    /**
     * Plays music when user reached goal in a level.
     */

    public void playGameMusic() {
        gameMusic.play();
    }


    /**
     * Fades out music from level exit.
     */

    public void stopGameMusic() {
        gameMusic.fadeOut();
    }
}

