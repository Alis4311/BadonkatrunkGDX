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
            fileInternal.copyTo(file);
        }
        //File file = new File(mapNbr+".txt");
        int theme = 1; // Load theme 1 if something is broken. 
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
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
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn1.png"));
                break;
            case 12:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn2.png"));
                break;
            case 13:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn3.png"));
                break;
            case 14:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn4.png"));
                break;
            case 15:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn5.png"));
                break;
            case 16:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn6.png"));
                break;
            case 17:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn7.png"));
                break;
            case 18:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn8.png"));
                break;
            case 19:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn9.png"));
                break;
            case 20:
                texture = new Texture(Gdx.files.internal("ButtonPictures/btn10.png"));
                break;

        }
        return texture;
    }


    private static Sprite getBackgroundImageForTheme(int theme){
        //TODO: Write switch case for the three themes.
        return new Sprite(new Texture(Gdx.files.internal("cityBackground.png")));
    }
}
