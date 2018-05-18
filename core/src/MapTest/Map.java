package MapTest;

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
        addGoalPost(32);
        for(int i= 0; i<60; i++){
            //addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture), i ,0));


            addObstacleObjects(new CollidingObject(0, i*32 ,0));

        }

        addObstacleObjects(new CollidingObject(0,600,32));
        addObstacleObjects(new CollidingObject(0,600,64));

/*
        addBackgroundObjectList(new DecorativeObject(new Sprite(new Texture(Gdx.files.internal("house.png"))),600,32));
        addObstacleObjects(new CollidingObject(0,664,32));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),632,64));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),664,64));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),500,96));
*/
        this.pauseObject = new PauseObject(new Sprite(), 600,0);
        this.hasPauseObject = true;
        this.background.setPosition(0,0);

        //saveMapToText(3);
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
        //this.background.draw(batch);
        batch.draw(background.getTexture(),0,0);
        for(DecorativeObject decorativeObject : backgroundObjectList){
            decorativeObject.draw(batch);
        }
        for(CollidingObject object : obstacleObjectList){
            object.draw(batch);
        }

    }

    private void addGoalPost(int height){
        DecorativeObject goalPost = new DecorativeObject(9,goalXCoordinates,height);
        goalPost.setSize(goalPost.getWidth(),goalPost.getHeight()*2);
        backgroundObjectList.add(goalPost);
    }
    public PauseObject getPauseObject(){
        return this.pauseObject;
    }
    public boolean hasPauseObject(){
        return hasPauseObject;
    }
    private void saveMapToText(int nbr){
        FileHandle file = Gdx.files.local(nbr+".txt");
        //File file = new File(nbr+".txt");


        try{
            int size  = obstacleObjectList.size();

            BufferedWriter bw = new BufferedWriter(file.writer(false));
            bw.write(this.theme + "");
            bw.newLine();
            bw.write(size + "" );
            bw.newLine();
            for(CollidingObject object : obstacleObjectList){
                bw.write(object.getX() + "");
                bw.newLine();
                bw.write(object.getY() + "");
                bw.newLine();
                bw.write(object.getId() + ""); //TODO: Add objecttype of collidingobject here. To decide what image to load.
                bw.newLine();
            }
            size = backgroundObjectList.size();
            bw.write(size + "");
            bw.newLine();
            for(DecorativeObject object : backgroundObjectList){
                bw.write(object.getX() + "");
                bw.newLine();
                bw.write(object.getY() + "");
                bw.newLine();
                bw.write(object.getId()+""); //TODO Add objecttype of decorativeobject here. To decide what image to load.
                bw.newLine();
            }
            bw.write(this.goalXCoordinates + "");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
