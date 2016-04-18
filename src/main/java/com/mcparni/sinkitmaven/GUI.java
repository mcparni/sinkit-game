package com.mcparni.sinkitmaven;

import java.awt.Color;


public class GUI {
    
    private final Color COLOR_8;
    private final Color COLOR_6;
    private final Color COLOR_4;
    private final Color COLOR_2;
    private final Color COLOR_1;
    private final Color COLOR_MISS;
    private final Color COLOR_HIT;
    private final Color COLOR_BORDER;
    private final Color COLOR_BASE;
    
    public GUI() {
        // squareEight red
        COLOR_8 = new Color(255, 164, 164);        
        // squareSix pink
        COLOR_6 = new Color(255, 164, 233);
        // squareFour orange
        COLOR_4 = new Color(255, 206, 164);
        // squareTwo blue
        COLOR_2 = new Color(164, 205, 255);
        // squareOne green;
        COLOR_1 = new Color(176, 255, 164);
        // red
        COLOR_MISS = new Color(200, 200, 200);
        // black
        COLOR_HIT = new Color(0, 0, 0);
        // grey
        COLOR_BORDER = new Color(242, 242, 242);
        // white
        COLOR_BASE = new Color(255, 255, 255);
    }
    
}
