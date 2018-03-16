package MapTest;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

/**
 * A class that holds info about the map used in Badonkatrunk.
 * @author Markus Wendler
 */
public class Map {
    private Sprite background;
    private int goalXCoordinates;
    private LinkedList<Sprite> ObstacleObjectList;
    private LinkedList<Sprite> backgroundObjectList;

    /**
     * Constructor that instantiates local variables and sets the background to the one in the parameter.
     * @param background - The sprite that is the background for the map.
     */
    public Map(Sprite background){
        this.background = background;
        ObstacleObjectList = new LinkedList<Sprite>();
        backgroundObjectList = new LinkedList<Sprite>();
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
     * @param sprite - The Sprite that the car can collide with.
     */
    public void addObstacleObjects(Sprite sprite){
        ObstacleObjectList.add(sprite);
    }

    /**
     * Returns a Sprite from the gameObstacleObjectList.
     * @param index - The index of the element in the list that returns.
     * @return - A Sprite.
     */
    public Sprite getGameObstacleObjects(int index) {
        return ObstacleObjectList.get(index);
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
    public int getBackgroundWidth(){
        return (int)getBackground().getWidth();
    }

    /**
     * Returns the height of the background Sprite.
     * @return - int.
     */
    public int getBackgroundHeight(){
        return (int)getBackground().getHeight();
    }
}
