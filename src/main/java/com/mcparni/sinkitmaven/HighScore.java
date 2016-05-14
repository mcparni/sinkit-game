package com.mcparni.sinkitmaven;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author  mcparni
 * @version 1.0 
 */

/**
 * Class for highscores. 
 * This class reads highscores from file and stores the data in various arrays.
 * 
 */
public class HighScore {
    private final String filename;
    private ArrayList<String> namesAndTimes;
    private ArrayList<String> names;
    private ArrayList<Integer> times;

    /**
    * Constructor for highscores.
    * 
    */
    public HighScore()  {
        this.filename = "highscore.txt";
        this.namesAndTimes = new ArrayList();
        this.times = new ArrayList();
        this.names = new ArrayList();
        readFromFile();
    }
    
    
    /**
     * Reads the given text file from line to line and stores data in to array list.
     */
    private void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();   
                this.namesAndTimes.add(line);
                
            }
            updateArrays();
        } catch(Exception e) { 
            System.out.println("Exception: " + e);
        }
    }
    /**
     * Updates data differently to different arrays.
     * There are data for only names, only times and both.
     */
    private void updateArrays() {
        for(int i = 0; i < this.namesAndTimes.size()- 1; i++) {
            String time = this.namesAndTimes.get(i);
            int start = time.indexOf(" ");
            String name = time.substring(0,start);
            time = time.substring(start + 1, time.length());
            this.times.add(Integer.parseInt(time));
            this.names.add(name);
        }
    }

    /**
     * Writes a new entry to text file.
     */
    private void writeToFile() {
        try {
            BufferedWriter bw = null;
            bw = new BufferedWriter(new FileWriter(this.filename));
            bw.newLine();
            for (int i = 0; i < this.namesAndTimes.size() - 1; i++) {
              bw.write(this.namesAndTimes.get(i).toString());
              bw.newLine();
            }
            bw.flush();  
            bw.close();  
        } catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
    /**
     * Return highscore times as minutes and seconds.
     * @return minsAndSecs as a String ArrayList
     */
    public ArrayList<String> getMinutesAndSeconds() {
        ArrayList<String> minsAndSecs = new ArrayList();
        for(int i = 0; i < this.times.size(); i++) {
            int minutes = (this.times.get(i) / 60)  % 60;
            int seconds = this.times.get(i) % 60;
            String result = minutes + "m " + seconds +"s";
            minsAndSecs.add(result);
        }
        return minsAndSecs;
    }

    /**
     * Return highscore names and times (in seconds).
     * @return namesAndTimes as a  String ArrayList.
     */
    public ArrayList<String> getNamesAndTimes() {
        return this.namesAndTimes;
    }
    
    /**
     * Return highscore names.
     * @return names as a String Array List.
     */
    public ArrayList<String> getNames() {
        return this.names;
    }
    
    /**
     * Return highscore times (in seconds).
     * @return times as a Integer Array List.
     */
    public ArrayList<Integer> getTimes() {
        return this.times;
    }
    
    /**
     * Checks if the time is good enough for the highscore list.
     * @param score is time in seconds.
     * @return index in the arrays where the score should be placed.
     * Should the index value be -1, it means that the score offered was
     * not good enough.
     */
    public int testHighScore(int score) {
        int index = -1;
        for(int i = 0; i < this.times.size(); i++) {
            
            if(score < this.times.get(i)) {
                    index = i;
                    return index;
            }
        }
        return index;
    }
    
    /**
     * Place new entry to highscore list.
     * @param seconds is the game time in seconds.
     * @param index is the place in array.
     * @param name is the players' name.
     */
    public void enterNewEntry(int seconds, int index, String name) {
        this.times.add(index, seconds);
        this.names.add(index, name);
        this.namesAndTimes.add(index, name + " " + seconds);
        this.times.remove((this.times.size() - 1));
        this.names.remove((this.names.size() - 1));
        this.namesAndTimes.remove((this.namesAndTimes.size() -1));
        
        try {
            writeToFile();
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }


}
