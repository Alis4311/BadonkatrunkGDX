package Testing;

import MapTest.Map;
import Objects.CollidingObject;
import Screens.GameScreen;
import Vehicles.Vehicle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class VehicleTest {
    private float accelerationRate;
    private float xSpeed;
    private float ySpeed;
    private float maxSpeed;
    private float jumpHeight;
    private TextureAtlas textureAtlas;
    private String currentAtlasKey;
    private int currentFrame;
    private float gravity;
    private float groundlevel;
    private boolean grounded;

    private boolean accelerateTouch = false;
    private boolean jumpTouch = false;
    private boolean pauseTouch = false;
    private boolean resumeTouch = false;
    private boolean levelsTouch = false;

    private boolean collision;
    private boolean collisionBoundingRectangle;
    private boolean collisionRightRectangle;

    public VehicleTest() {
        this.xSpeed = 0f;
        this.ySpeed = 0f;
        collisionHandling();
    }

    private void collisionHandling() {
        boolean bottomRectCollision = false;

        System.out.print("1A");
        if (this.checkCollision() && checkCollisionBoundingRectangle()) {
            System.out.print("2B");
            if (checkCollisionRightRectangle()) {
                System.out.print("4E");
            }

            if (this.getBottomRectangle().overlaps(object.getBoundingRectangle())) {
                System.out.print("6G");
            }

            if (this.getTopRectangle().overlaps(object.getBoundingRectangle())) {
                if (ySpeed > 0) {
                    System.out.print("9M");
                }
            }

        }
        if(level.hasPauseObject()){
        System.out.print("10O");
        if (!level.getPauseObject().isTriggered()) {
            System.out.print("11P");
            if (this.checkCollision(level.getPauseObject().getBoundingRectangle())) {
                System.out.print("12Q");
                if (this.getRightRectangle().overlaps(level.getPauseObject().getBoundingRectangle())) {
                    System.out.print("13U");
                    System.out.print("14V");
                }
            }
        }
    }


    if(bottomRectCollision)

    {
        System.out.print("18X");

    }else

    {
        System.out.print("17W");

    }
}

private boolean checkCollision(){
        return collision;
}

private void setCollision(boolean value){
        collision = value;
}

    private boolean checkCollisionBoundingRectangle(){
        return collisionBoundingRectangle;
    }

    private void setCollisionBoundingRectangle(boolean value){
        collisionBoundingRectangle = value;
    }

    private boolean checkCollisionRightRectangle(){
        return collisionRightRectangle;
    }

    private void setCollisionRightRectangle(boolean value){
        collisionRightRectangle = value;
    }

}
