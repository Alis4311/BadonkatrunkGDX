package MapTest;

import Objects.CollidingObject;
import Objects.DecorativeObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.io.*;
import java.util.LinkedList;

public class MapLoader {

    public static Map load(int mapNbr){
        File file = new File(mapNbr+".txt");
        int theme = 1; // Load theme 1 if something is broken. 
        LinkedList<DecorativeObject> backgroundObjects = new LinkedList<DecorativeObject>();
        LinkedList<CollidingObject> obstacleObjects = new LinkedList<CollidingObject>();
        int goalXCoordinates = 0;

        System.out.println(file.getAbsolutePath());
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            theme = Integer.parseInt(br.readLine());
            System.out.println(theme);
            int nbrOfObstacleObjects = Integer.parseInt(br.readLine());
            System.out.println(nbrOfObstacleObjects);
            for(int i = 0; i < nbrOfObstacleObjects; i++) {
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForObstacleObject(type));
                sprite.setPosition((int)xPosition,(int)yPosition);
                obstacleObjects.add(new CollidingObject(sprite));
            }
            int nbrOfDecorativeObjects = Integer.parseInt(br.readLine());
            for(int i = 0; i < nbrOfDecorativeObjects; i++){
                float xPosition = Float.parseFloat(br.readLine());
                float yPosition = Float.parseFloat(br.readLine());
                int type = Integer.parseInt(br.readLine());
                Sprite sprite = new Sprite(getTextureForDecorativeObject(type));
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

    private static Texture getTextureForObstacleObject(int type){
        //TODO: Add all objecttypes to switch case.
        Texture texture;
        switch (type){
            case 1:
                texture = new Texture(Gdx.files.internal("cobble.png"));
                break;
            default:
                texture = new Texture(Gdx.files.internal("cobble.png"));
                break;
        }

        return texture;
    }

    private static Texture getTextureForDecorativeObject(int type){
        //TODO: Add all objecttypes to switch case.
        Texture texture;
        switch (type){
            case 1:
                texture = new Texture(Gdx.files.internal("house.png"));
                break;
            case 2:
                texture = new Texture(Gdx.files.internal("finish.png"));
                break;
            default:
                texture = new Texture(Gdx.files.internal("finish.png"));
                break;
        }

        return texture;
    }

    private static Sprite getBackgroundImageForTheme(int theme){
        //TODO: Write switch case for the three themes.
        return new Sprite(new Texture(Gdx.files.internal("bakgrund2.png")));
    }
}
