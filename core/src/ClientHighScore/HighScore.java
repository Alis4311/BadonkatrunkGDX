package ClientHighScore;

import java.io.Serializable;

/**
 * Class that holds information about the high score on a specific map.
 * @author Markus Wendler
 */
public class HighScore implements Serializable {

    private int levelNbr;
    private long milliSecTime;
    private String userName;

    public HighScore(int levelNbr, long milliSecTime, String userName){
        this.levelNbr = levelNbr;
        this.milliSecTime = milliSecTime;
        this.userName = userName;
    }

    /**
     * Returns which map the high score belongs to.
     * @return - int, map number.
     */
    public int getLevelNbr() {
        return levelNbr;
    }

    /**
     * Assigns the high score to a specific map.
     * @param levelNbr - the current map.
     */
    public void setLevelNbr(int levelNbr) {
        this.levelNbr = levelNbr;
    }

    /**
     * Returns the current high scores time in milliseconds.
     * @return - int, the time in milliseconds.
     */
    public long getMilliSecTime() {
        return milliSecTime;
    }

    /**
     * Sets the time for the map.
     * @param miliSecTime - the time the map was finished on.
     */
    public void setMiliSecTime(long miliSecTime) {
        this.milliSecTime = miliSecTime;
    }

    /**
     * Returns the username of the client that owns this high score.
     * @return - String, the username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username for this high score.
     * @param userName - the username for the owner of this high score.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
