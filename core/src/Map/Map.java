package Map;

import Objects.CollidingObject;
import Objects.DecorativeObject;
import Objects.PauseObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chris.badonkatrunk.Badonkatrunk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * A class that holds info about the map used in Badonkatrunk.
 * @author Markus Wendler
 */
public class Map {
    private Sprite background;
    private int goalXCoordinates;
    private LinkedList<CollidingObject> obstacleObjectList;
    private LinkedList<DecorativeObject> backgroundObjectList;
    private int theme;
    private boolean hasPauseObject;
    private PauseObject pauseObject;
    /**
     * Constructor that instantiates local variables and sets the background to the one in the parameter.
     * @param background - The sprite that is the background for the map.
     */
    public Map(Sprite background){
        theme = 1;
        this.background = background;

        backgroundObjectList = new LinkedList<DecorativeObject>();
        obstacleObjectList = new LinkedList<CollidingObject>();
        goalXCoordinates = (int)background.getWidth()-75;
        for(int i= 0; i<60; i++){

            CollidingObject object = new CollidingObject(new Sprite(new Texture(Gdx.files.internal("cobble.png"))));
            object.setSpritePosition(i*32,0);
            addObstacleObjects(object);

        }
        CollidingObject object = new CollidingObject(new Sprite(new Texture(Gdx.files.internal("cobble.png"))));
        object.setSpritePosition(600,32);
        addObstacleObjects(object);
        CollidingObject object2 = new CollidingObject(new Sprite(new Texture(Gdx.files.internal("cobble.png"))));

        object2.setSpritePosition(600,64);
        addObstacleObjects(object2);
        DecorativeObject goalpost = new DecorativeObject(new Sprite(new Texture(Gdx.files.internal("finish.png"))));
        goalpost.setSpritePosition(goalXCoordinates,32);
        addBackgroundObjectList(goalpost);
        this.pauseObject = new PauseObject(new Sprite(), 600,0);
        this.hasPauseObject = true;
        this.background.setPosition(0,0);
    }

    public Map(Sprite background, LinkedList<CollidingObject> obstacleObjectList, LinkedList<DecorativeObject> backgroundObjectList, int goalXCoordinates, int theme){
        this.theme = theme;
        this.background = background;
        this.goalXCoordinates  = goalXCoordinates;
        this.obstacleObjectList = obstacleObjectList;
        this.backgroundObjectList = backgroundObjectList;

    }

    public void dispose(){

    }

    /**
     * Returns the background Sprite.
     * @return - Sprite.
     */
    private Sprite getBackground(){
        return background;
    }

    /**
     * Adds sprites to the list that holds objects that has collision.

     */
    private void addObstacleObjects(CollidingObject object){
        obstacleObjectList.add(object);
    }

    /**
     * Returns a Sprite from the gameObstacleObjectList.
     * @return - A Sprite.
     */
    public LinkedList<CollidingObject> getGameObstacleObjects() {
        return obstacleObjectList;
    }

    /**
     * Adds sprites to the list that holds objects that has no collision.
     * @param object - The Sprite that decorates the background.
     */
    public void addBackgroundObjectList(DecorativeObject object){
        backgroundObjectList.add(object);
    }

    /**
     * Returns a Sprite from the gameBackgroundObjectList.
     * @return - A Sprite.
     */
    public LinkedList<DecorativeObject> getBackgroundObjectList() {
        return backgroundObjectList;
    }

    /**
     * Returns the X coordinate of the goal in the map.
     * @return - int.
     */
    public int getGoalXCoordinates() {
        return goalXCoordinates;
    }

    /**
     * Returns the width of the background Sprite.
     * @return - int.
     */
    public int getWidth(){
        return (int)getBackground().getWidth();
    }

    /**
     * Returns the height of the background Sprite.
     * @return - int.
     */
    public int getHeight(){
        return (int)getBackground().getHeight();
    }

    public int getTheme() {
        return theme;
    }

    public void draw(SpriteBatch batch){

        batch.draw(background.getTexture(),0,0);
        for(DecorativeObject decorativeObject : backgroundObjectList){
            decorativeObject.draw(batch);
        }
        for(CollidingObject object : obstacleObjectList){
            object.draw(batch);
        }

    }


    public PauseObject getPauseObject(){
        return this.pauseObject;
    }
    public boolean hasPauseObject(){
        return hasPauseObject;
    }


}
