package Server;


import ClientHighScore.HighScore;

import java.util.ArrayList;

/**
 * The Leaderboard containing list of high scores for each track.
 * Provides a leaderboard, compares a users time, can add the tracktime
 * plus a username to a leaderboard for each track.
 *
 * @author Peder Nilsson & Markus Wendler
 *
 */

public class ServerLeaderboard {

    private boolean onLeaderboard;
    private ArrayList<HighScore> newLeaderboard;

    private ArrayList<HighScore> highScoreLevel1 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel2 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel3 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel4 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel5 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel6 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel7 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel8 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel9 = new ArrayList<HighScore>();
    private ArrayList<HighScore> highScoreLevel10 = new ArrayList<HighScore>();


    private final int NUMBER_OF_HIGH_SCORES_PER_LEVEL = 10;
    private final int NUMBER_OF_LEVELS = 10;



    public ServerLeaderboard(){
        onLeaderboard = false;

        addEmptyHighScore(highScoreLevel1,1);
        addEmptyHighScore(highScoreLevel2,2);
        addEmptyHighScore(highScoreLevel3,3);
        addEmptyHighScore(highScoreLevel4,4);
        addEmptyHighScore(highScoreLevel5,5);
        addEmptyHighScore(highScoreLevel6,6);
        addEmptyHighScore(highScoreLevel7,7);
        addEmptyHighScore(highScoreLevel8,8);
        addEmptyHighScore(highScoreLevel9,9);
        addEmptyHighScore(highScoreLevel10,10);
    }

    /**
     * Method that compares a new high score with the existing high scores on the leaderboard.
     * @param highScoreLevel - all the top high scores on a specific map.
     * @param score - the new high score.
     */
    private void compareTime(ArrayList<HighScore> highScoreLevel, HighScore score) {
        for (int i = 0; i < NUMBER_OF_HIGH_SCORES_PER_LEVEL; i++){
            if (score.getMilliSecTime() < highScoreLevel.get(i).getMilliSecTime()){
                update(highScoreLevel, score, i);
                break;
            }
        }
    }

    /**
     * Updates the current leaderboard and sets new value on the variables to return.
     * @param highScoreLevel - the current high score list (leaderboard)
     * @param score - the new high score.
     * @param position - the position on the leaderboard that the new high score should be.
     */
    private void update(ArrayList<HighScore> highScoreLevel, HighScore score, int position){
        onLeaderboard = true;
        highScoreLevel.add(position, score);
        newLeaderboard = highScoreLevel;
    }

    /**
     * Chooses the right ArrayList (leaderboard) associated with the new high score.
     * @param score - the new high score.
     * @return ArrayList<HighScore>, the new leaderboard.
     */
    public ArrayList<HighScore> checkHighScore(HighScore score){
        switch (score.getLevelNbr()){
            case 1 :
                compareTime(highScoreLevel1, score);
                break;

            case 2 :
                compareTime(highScoreLevel2, score);
                break;

            case 3 :
                compareTime(highScoreLevel3, score);
                break;

            case 4 :
                compareTime(highScoreLevel4, score);
                break;

            case 5 :
                compareTime(highScoreLevel5, score);
                break;

            case 6 :
                compareTime(highScoreLevel6, score);
                break;

            case 7 :
                compareTime(highScoreLevel7, score);
                break;

            case 8 :
                compareTime(highScoreLevel8, score);
                break;

            case 9 :
                compareTime(highScoreLevel9, score);
                break;

            case 10 :
                compareTime(highScoreLevel10, score);
                break;
        }

        return newLeaderboard;
    }

    /**
     * Sets the fist leaderboard to be empty.
     * @param highScoreLevel - the leaderboard to set.
     */
    private void addEmptyHighScore(ArrayList<HighScore> highScoreLevel, int mapnumber){
        for (int i = 0; i < NUMBER_OF_HIGH_SCORES_PER_LEVEL; i++){
            highScoreLevel.add(new HighScore(mapnumber, 600000, "AAA"));
        }
    }

    /**
     * Returns if the new high score is on the leaderboard.
     * @return - boolean, true if new high score is on leaderboard.
     */
    public boolean isOnLeaderboard(){
        return onLeaderboard;
    }

}
