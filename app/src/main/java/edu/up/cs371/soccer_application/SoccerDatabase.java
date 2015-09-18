package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {
    Hashtable<String, SoccerPlayer> tableHash = new Hashtable<String, SoccerPlayer>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        if(tableHash.containsKey(firstName + "##" + lastName)) {
            return false;
        }
        else {
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber,teamName);
            tableHash.put(firstName + "##" + lastName,newPlayer);
            return true;
        }
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if(tableHash.containsKey(firstName + "##" + lastName)) {
            tableHash.remove(firstName+"##"+lastName);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        return tableHash.get(firstName+"##"+lastName);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpGoals();
        return true;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpAssists();
        return true;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpShots();
        return true;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpSaves();
        return true;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpFouls();
        return true;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpYellowCards();
        return true;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if (tableHash.get(firstName+"##"+lastName)==null)
            return false;
        tableHash.get(firstName+"##"+lastName).bumpRedCards();
        return true;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if(teamName == null)
            return tableHash.size();
        int i = 0;
        Iterator<SoccerPlayer> a = tableHash.values().iterator();
        while (a.hasNext()) if (a.next().getTeamName().equals(teamName))
            i++;
        return i;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        Iterator<SoccerPlayer> a=tableHash.values().iterator();
        SoccerPlayer player=null;
        if (teamName==null) {
            for (int x = 0; x <= idx&&a.hasNext(); x++)
                player=a.next();
            return player;
        }
        else {
            int i=0;
            while (a.hasNext()) {
                if ((player = a.next()).getTeamName().equals(teamName)) {
                    if (idx == i)
                        return player;
                    i++;
                }
            }
        }
        return null;

    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String a = scan.nextLine();
                String b = scan.nextLine();
                SoccerPlayer player = new SoccerPlayer(a, b, Integer.parseInt(scan.nextLine()), (scan.nextLine()));
                int c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpGoals();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpAssists();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpFouls();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpShots();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpSaves();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpRedCards();
                c = Integer.parseInt(scan.nextLine());
                for (int i = 0; i < c; i++)
                    player.bumpYellowCards();
                scan.nextLine();
                tableHash.put(a + "##" + b, player);
            }
        }
        catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }


    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (SoccerPlayer player:tableHash.values()){
                pw.println(logString(player.getFirstName()));
                pw.println(logString(player.getLastName()));
                pw.println(logString(Integer.toString(player.getUniform())));
                pw.println(logString(player.getTeamName()));
                pw.println(logString(Integer.toString(player.getGoals())));
                pw.println(logString(Integer.toString(player.getAssists())));
                pw.println(logString(Integer.toString(player.getFouls())));
                pw.println(logString(Integer.toString(player.getShots())));
                pw.println(logString(Integer.toString(player.getSaves())));
                pw.println(logString(Integer.toString(player.getRedCards())));
                pw.println(logString(Integer.toString(player.getYellowCards())));
                pw.println(logString(""));
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            return false;
        }
        return true;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        //Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        HashSet<String> tables = new HashSet<String>();
        for (SoccerPlayer player : tableHash.values()) {
            if (!tables.contains(player.getTeamName())) {
                tables.add(player.getTeamName());
            }
        }
        return tables;
    }

}
