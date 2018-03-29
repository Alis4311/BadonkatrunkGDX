package MapTest;

import Objects.CollidingObject;
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
    private LinkedList<Sprite> backgroundObjectList;
    private Vehicle vehicle;

    /**
     * Constructor that instantiates local variables and sets the background to the one in the parameter.
     * @param background - The sprite that is the background for the map.
     */
    public Map(Sprite background){
        this.background = background;
        ObstacleObjectList = new LinkedList<CollidingObject>();
        CollidingObject object = new CollidingObject(new Sprite (new Texture(Gdx.files.internal("badlogic.jpg"))));
        CollidingObject object2 = new CollidingObject(new Sprite (new Texture(Gdx.files.internal("badlogic.jpg"))));

        object.setX(500);
        object.setY(0);
        object.setSize(64,64);
        object2.setX(800);
        object2.setY(0);
        object2.setSize(100,150);
        addObstacleObjects(object);
        addObstacleObjects(object2);
        backgroundObjectList = new LinkedList<Sprite>();
        this.background.setPosition(0,0);
    }

    /**
     * Set the vehicle associated.
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
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
     * @param sprite - The Sprite that decorates the background.
     */
    public void addBackgroundObjectList(Sprite sprite){
        backgroundObjectList.add(sprite);
    }

    /**
     * Returns a Sprite from the gameBackgroundObjectList.
     * @param index - The index of the element in the list that returns.
     * @return - A Sprite.
     */
    public Sprite getBackgroundObjectList(int index) {
        return backgroundObjectList.get(index);
    }

    /**
     * Returns the X coordinate of the goal in the map.
     * @return - int.
     */
    public int getGoalXCoordinates() {
        goalXCoordinates = (int)getBackground().getWidth();
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

    public void draw(SpriteBatch batch){
        this.background.draw(batch);
        for(CollidingObject object : ObstacleObjectList){
            object.draw(batch);
        }

    }
}
