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

    private ArrayList<Long> ldrBrdTrack1 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdTrack2 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdTrack3 = new ArrayList<Long>();
    private ArrayList<Long> ldrBrdTrack4 = new ArrayList<Long>();


    public ServerLeaderboard() {

    }

    public static void compareTime(int trackNr, long trackTime) {

    }

    public ArrayList update(int trackNr, Long Tracktime){

        return null;
    }


}
