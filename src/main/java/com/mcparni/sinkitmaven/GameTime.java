package com.mcparni.sinkitmaven;

/**
 * @author  mcparni
 * @version 1.0 
 */

/**
 * This is the class that handles game's time.
 * This is basically the score that player gets from game. The lower the time,
 * the better the position in the list.
 */
public class GameTime {
    private int sec;
    private long start;
    private long end;
    private long result;
        
    /**
    * Constructor for GameTime class.
    */
    public GameTime() {
        this.start = 0;
        this.end = 0;
        this.sec = 0;
        this.result = 0;
    }
    
    /**
    * Starts measuring the time in nano seconds.
    */
    public void startTime() {
        this.start = System.nanoTime();
    }
    
    /**
    * Stops measuring the time and converts it to seconds.
    */
    public void stopTime() {
        this.end= System.nanoTime();
        long elapsedTime = this.end - this.start;
        this.start = 0;
        this.end = 0;
        double seconds = (double)elapsedTime / 1000000000.0;
        this.sec = (int)seconds;
       
    }
    
    /**
     * Returns time used for the game.
     * @return sec as integer for game time in seconds.
     */
    public int getSeconds() {
        return this.sec;
    }
}
