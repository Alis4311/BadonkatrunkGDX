package MapTest;

import Objects.CollidingObject;
import Objects.DecorativeObject;
import Vehicles.Vehicle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.LinkedList;

/**
 * A class that holds info about the map used in Badonkatrunk.
 * @author Markus Wendler
 */
public class Map {
    private Sprite background;
    private int goalXCoordinates;
    private LinkedList<CollidingObject> ObstacleObjectList;
    private LinkedList<DecorativeObject> backgroundObjectList;
    private int theme;

    /**
     * Constructor that instantiates local variables and sets the background to the one in the parameter.
     * @param background - The sprite that is the background for the map.
     */
    public Map(Sprite background){
        theme = 1;
        this.background = background;
        backgroundObjectList = new LinkedList<DecorativeObject>();
        ObstacleObjectList = new LinkedList<CollidingObject>();
        goalXCoordinates = (int)background.getWidth()-75;
        addGoalPost();
        Texture cobbleTexture = new Texture(Gdx.files.internal("cobble.png"));
        for(int i= 0; i<background.getWidth(); i+=32){
            addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),i,0));
        }

        addBackgroundObjectList(new DecorativeObject(new Sprite(new Texture(Gdx.files.internal("house.png"))),600,32));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),664,32));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),632,64));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),664,64));
        addObstacleObjects(new CollidingObject(new Sprite(cobbleTexture),500,96));

        this.background.setPosition(0,0);

    }

    /**
     * Returns the background Sprite.
     * @return - Sprite.
     */
    public Sprite getBackground(){
        return background;
    }

    /**
     * Adds sprites to the list that holds objects that has collision.

     */
    public void addObstacleObjects(CollidingObject object){
        ObstacleObjectList.add(object);
    }

    /**
     * Returns a Sprite from the gameObstacleObjectList.
     * @return - A Sprite.
     */
    public LinkedList<CollidingObject> getGameObstacleObjects() {
        return ObstacleObjectList;
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
        this.background.draw(batch);
        for(DecorativeObject decorativeObject : backgroundObjectList){
            decorativeObject.draw(batch);
        }
        for(CollidingObject object : ObstacleObjectList){
            object.draw(batch);
        }

    }

    private void addGoalPost(){
        DecorativeObject goalPost = new DecorativeObject(new Sprite(new Texture(Gdx.files.internal("finish.png"))));
        goalPost.setX(goalXCoordinates);
        goalPost.setY(32);
        goalPost.setSize(goalPost.getWidth(),goalPost.getHeight()*2);
        backgroundObjectList.add(goalPost);
    }

}
