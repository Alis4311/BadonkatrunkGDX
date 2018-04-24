package ClientHighScore;

public class HighScore {

    private int levelNbr;
    private int milliSecTime;
    private String userName;

    public HighScore(int levelNbr, int milliSecTime, String userName){
        this.levelNbr = levelNbr;
        this.milliSecTime = milliSecTime;
        this.userName = userName;
    }

    public int getLevelNbr() {
        return levelNbr;
    }

    public void setLevelNbr(int levelNbr) {
        this.levelNbr = levelNbr;
    }

    public int getMilliSecTime() {
        return milliSecTime;
    }

    public void setMiliSecTime(int miliSecTime) {
        this.milliSecTime = miliSecTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
