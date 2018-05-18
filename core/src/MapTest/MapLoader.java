package MapTest;

import Objects.CollidingObject;
import Objects.DecorativeObject;
import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.*;
import java.util.LinkedList;

public class MapLoader {

    public static Map load(int mapNbr){
        if(mapNbr == 1){
            GameScreen.isPausedForAcceleration = true;
            return new Map(new Sprite(new Texture(Gdx.files.internal("cityBackground.png"))));
        }
        FileHandle fileInternal = Gdx.files.internal(mapNbr+".txt");
        FileHandle file = Gdx.files.local(mapNbr+".txt");
        if(!Gdx.files.local(mapNbr+".txt").exists()){
           // if(true){
            fileInternal.copyTo(file);
        }
        //File file = new File(mapNbr+".txt");
        //int theme = 1; // Load theme 1 if something is broken.
        int theme = 0;
        LinkedList<DecorativeObject> backgroundObjects = new LinkedList<DecorativeObject>();
        LinkedList<CollidingObject> obstacleObjects = new LinkedList<CollidingObject>();
        int goalXCoordinates = 0;


        try{
            BufferedReader br = new BufferedReader(file.reader());
            theme = Integer.parseInt(br.readLine());
            System.out.println(theme);
            int nbrOfObstacleObjects = Integer.parseInt(br.readLine());
            System.out.println(nbrOfObstacleObjects);
            for(int i = 0; i < nbrOfObstacleObjects; i++) {
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForObject(type));
                sprite.setPosition((int)xPosition,(int)yPosition);
                obstacleObjects.add(new CollidingObject(sprite));
            }
            int nbrOfDecorativeObjects = Integer.parseInt(br.readLine());
            for(int i = 0; i < nbrOfDecorativeObjects; i++){
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForObject(type));
                sprite.setPosition((int)xPosition,(int)yPosition);
                backgroundObjects.add(new DecorativeObject(sprite));
            }

            goalXCoordinates = Integer.parseInt(br.readLine());
            br.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        GameScreen.isPaused = true;
        return new Map(getBackgroundImageForTheme(theme),obstacleObjects,backgroundObjects,goalXCoordinates,theme);
    }

    private static Texture getTextureForObject(int type) {
        //TODO: Add all objecttypes to switch case.
        Texture texture = new Texture(Gdx.files.internal("error.png"));
        switch (type) {
            case 0:
                texture = new Texture(Gdx.files.internal("cobble.png"));
                break;
            case 1:
                texture = new Texture(Gdx.files.internal("hayBale.png"));
                break;
            case 2:
                texture = new Texture(Gdx.files.internal("moonPiece.png"));
                break;
            case 3:
                texture = new Texture(Gdx.files.internal("moonGoalFlag.png"));
                break;
            case 4:
                texture = new Texture(Gdx.files.internal(""));
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                texture = new Texture(Gdx.files.internal("finish.png"));
                break;
            case 10:
                texture = new Texture(Gdx.files.internal("house.png"));
                break;
            case 11:
                texture = new Texture(Gdx.files.internal("number1.png"));
                break;
            case 12:
                texture = new Texture(Gdx.files.internal("number2.png"));
                break;
            case 13:
                texture = new Texture(Gdx.files.internal("number3.png"));
                break;
            case 14:
                texture = new Texture(Gdx.files.internal("number4.png"));
                break;
            case 15:
                texture = new Texture(Gdx.files.internal("number5.png"));
                break;
            case 16:
                texture = new Texture(Gdx.files.internal("number6.png"));
                break;
            case 17:
                texture = new Texture(Gdx.files.internal("number7.png"));
                break;
            case 18:
                texture = new Texture(Gdx.files.internal("number8.png"));
                break;
            case 19:
                texture = new Texture(Gdx.files.internal("number9.png"));
                break;
            case 20:
                texture = new Texture(Gdx.files.internal("number10.png"));
                break;

        }
        return texture;
    }


    private static Sprite getBackgroundImageForTheme(int theme){
        Sprite sprite = new Sprite();
        switch(theme){
            case 1:
                sprite = new Sprite(new Texture(Gdx.files.internal("cityBackground.png")));
                break;
            case 2:
                sprite = new Sprite(new Texture(Gdx.files.internal("farmBackground.png")));
                break;
            case 3:
                Texture texture = new Texture(Gdx.files.internal("spaceBackground.png"));
                texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
                sprite = new Sprite(texture);
                break;
        }
        return sprite;
    }
}
