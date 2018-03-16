package MapTest;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;

public class Map {
    private Sprite background;
    private int goalXCoordinates;
    private LinkedList<Sprite> ObstacleObjectList;
    private LinkedList<Sprite> backgroundObjectList;

    public Map(Sprite background){
        this.background = background;
        ObstacleObjectList = new LinkedList<Sprite>();
        backgroundObjectList = new LinkedList<Sprite>();
    }

    public Sprite getBackground(){
        return background;
    }

    public void addObstacleObjects(Sprite sprite){
        ObstacleObjectList.add(sprite);
    }

    public Sprite getGameObstacleObjects(int index) {
        return ObstacleObjectList.get(index);
    }

    public void addBackgroundObjectList(Sprite sprite){
        backgroundObjectList.add(sprite);
    }

    public Sprite getBackgroundObjectList(int index) {
        return backgroundObjectList.get(index);
    }

    public int getGoalXCoordinates() {
        goalXCoordinates = (int)getBackground().getWidth();
        return goalXCoordinates;
    }
}
