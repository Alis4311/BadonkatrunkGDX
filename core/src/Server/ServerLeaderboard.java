package Server;


import ClientHighScore.HighScore;

import java.util.ArrayList;

/**
 * The Leaderboard containing list of highscores for each track.
 * Provides a leaderboard, compares a users time, can add the tracktime
 * plus a user nick to a leaderboard for each track.
 *
 * @author Peder Nilsson & xxxx &  xxxx & xxxxx
 *
 * Bara ett utkast -  fixa och trixa - allihopa  - go!!
 */

public class ServerLeaderboard {

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
        addEmptyHighScore(highScoreLevel1);
        addEmptyHighScore(highScoreLevel2);
        addEmptyHighScore(highScoreLevel3);
        addEmptyHighScore(highScoreLevel4);
        addEmptyHighScore(highScoreLevel5);
        addEmptyHighScore(highScoreLevel6);
        addEmptyHighScore(highScoreLevel7);
        addEmptyHighScore(highScoreLevel8);
        addEmptyHighScore(highScoreLevel9);
        addEmptyHighScore(highScoreLevel10);
    }

    private void compareTime(ArrayList<HighScore> highScoreLevel, HighScore score) {
        for (int i = 0; i < NUMBER_OF_HIGH_SCORES_PER_LEVEL; i++){
            if (score.getMilliSecTime() < highScoreLevel.get(i).getMilliSecTime()){
                update(highScoreLevel, score, i);
                break;
            }
        }
    }

    private void update(ArrayList<HighScore> highScoreLevel, HighScore score, int position){
        highScoreLevel.add(position, score);
        newLeaderboard = highScoreLevel;
    }

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

    private void addEmptyHighScore(ArrayList<HighScore> highScoreLevel){
        for (int i = 0; i < NUMBER_OF_LEVELS; i++){
            highScoreLevel.add(new HighScore(0, 0, "AAA"));
        }
    }

}
