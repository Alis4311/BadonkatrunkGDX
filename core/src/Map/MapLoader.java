package Map;

import Objects.CollidingObject;
import Objects.DecorativeObject;
import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.*;
import java.util.LinkedList;

/**
 *  Helper class used to read textfiles containing levels.
 * @author Christoffer Book
 */
public class MapLoader {
    /**
     * Takes the map to load as inparameter, reads the file, converts to objects, returns a map based on created objects.
     *
     * The files follow the following pattern
     * line 1: theme of the level.
     * line 2: number of colliding objects;
     * for as many objects as read in line 2;
     *      line3: xCoordinate of the object.
     *      line4: yCoordinate of the object.
     *      line5: objectType (Used to load the correct texture).
     * line6: number of decorative objects.
     * for as many objects as read in line6;
     *      line7: xCoordinate of the object.
     *      line8: yCoordinate of the object.
     *      line9: objectType (Used to load the correct texture).
     * line10: xPosition of the goalline. This is where the level ends.
     *
     * This class and it´s functionality is not directly linked to any requirements.
     * It´s more of a Quality of Life implementation for developers.
     * @param mapNbr - the number of the level to load, technically this is a reference to the file.
     * @return {@link Map}
     * @author Christoffer Book
     */
    public static Map load(int mapNbr){
        /*If the level is 1 use a different constructor to create the map, in order to setup the pauses
         * required to make the level an introductionlevel.
         */
        if(mapNbr == 1){
            GameScreen.isPausedForAcceleration = true;
            return new Map(new Sprite(new Texture(Gdx.files.internal("cityBackground.png"))));
        }
        FileHandle file = Gdx.files.internal(mapNbr+".txt");

        int theme = 0;
        LinkedList<DecorativeObject> backgroundObjects = new LinkedList<DecorativeObject>();
        LinkedList<CollidingObject> obstacleObjects = new LinkedList<CollidingObject>();
        int goalXCoordinates = 0;


        try{
            BufferedReader br = new BufferedReader(file.reader());
            theme = Integer.parseInt(br.readLine()); //Read the theme.
            int nbrOfObstacleObjects = Integer.parseInt(br.readLine()); //Read number of obstacleobjects.
            //For the number of obstacleobjects, read coordinates and type, and create an object, place in list.
            for(int i = 0; i < nbrOfObstacleObjects; i++) {
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForObject(type));
                sprite.setPosition((int)xPosition,(int)yPosition);
                obstacleObjects.add(new CollidingObject(sprite));
            }
            int nbrOfDecorativeObjects = Integer.parseInt(br.readLine()); // Read number of decorativeobjects.
            // For the number of decorativeobjects, read coordinates and type, create object and place in list.
            for(int i = 0; i < nbrOfDecorativeObjects; i++){
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForObject(type));
                sprite.setPosition((int)xPosition,(int)yPosition);
                backgroundObjects.add(new DecorativeObject(sprite));
            }

            goalXCoordinates = Integer.parseInt(br.readLine());// Read the position of the goalline.
            br.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        GameScreen.isPaused = true; //Pause the game, since each level starts with the game paused.
        return new Map(getBackgroundImageForTheme(theme),obstacleObjects,backgroundObjects,goalXCoordinates,theme); // build level based on the objects read.
    }

    /**
     * Take the "type" of object, and return the appropriate texture.
     * @param type -  a number linked to a specific texture.
     * @return {@link Texture}
     * @author Christoffer Book
     */
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

    /**
     * Take the theme as number and return the appropriate texture linked to that number.
     * @param theme - The number indicating the theme.
     * @return {@link Texture}
     * @author Christoffer Book
     */
    private static Sprite getBackgroundImageForTheme(int theme){
        Sprite sprite;
        switch(theme){
            case 1:
                sprite = new Sprite(new Texture(Gdx.files.internal("cityBackground.png")));
                break;
            case 2:
                sprite = new Sprite(new Texture(Gdx.files.internal("farmBackground.png")));
                break;
            case 3:
                Texture texture = new Texture(Gdx.files.internal("spaceBackgroundx2.png"));
                texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
                sprite = new Sprite(texture);
                break;
            default:
                sprite = new Sprite(new Texture(Gdx.files.internal("cityBackground.png")));
                break;
        }
        return sprite;
    }
}
