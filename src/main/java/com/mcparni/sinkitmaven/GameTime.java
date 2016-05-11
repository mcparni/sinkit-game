package com.mcparni.sinkitmaven;


public class GameTime {
    int sec;
    long start;
    long end;
    long result;
        
    public GameTime() {
        this.start = System.nanoTime();
        
        
    }
    public void stopTime() {
        this.end= System.nanoTime();
        long elapsedTime = this.end - this.start;
        this.start = 0;
        this.end = 0;
        double seconds = (double)elapsedTime / 1000000000.0;
        this.sec = (int)seconds;
       
    }
    public int getSeconds() {
        return this.sec;
    }
}
