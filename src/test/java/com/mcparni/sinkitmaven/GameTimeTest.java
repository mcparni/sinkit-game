package com.mcparni.sinkitmaven;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTimeTest {
    
    static GameTime g;
    
    @Before
    public void setUp() {
       this.g = new GameTime();
    }
    
    @Test
    public void testStartTime() throws InterruptedException {
        this.g.startTime();
        Thread.sleep(1500);
        this.g.stopTime();
        int i = 0;
        if(g.getSeconds() > 0) {
            i = 1;
        }
        assertEquals(i, 1);
        System.out.println("* testStartTime()");
    }


    @Test
    public void testStopTime() throws InterruptedException {
        this.g.startTime();
        Thread.sleep(500);
        this.g.stopTime();
        int seconds = g.getSeconds();
        Thread.sleep(500);
        int secondsTwo = g.getSeconds();
        assertEquals(seconds, secondsTwo);
        System.out.println("* testStopTime()");
    }

    @Test
    public void testGetSeconds() {
        int seconds = this.g.getSeconds();
        int expResult = 0;
        assertEquals(expResult, seconds);
        System.out.println("* testStopTime()");
    }
    
}
