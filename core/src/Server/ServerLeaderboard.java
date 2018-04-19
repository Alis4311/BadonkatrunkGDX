package Server;


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

    private ArrayList<Long> ldrBrdLevel1 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdLevel2 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdLevel3 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdLevel4 = new ArrayList<Long>();


    public ServerLeaderboard() {

    }

    public static void compareTime(int levelNr, long levelTime) {

    }

    public ArrayList update(int levelNr, Long levelTime){

        return null;
    }


}
