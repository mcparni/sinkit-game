package com.mcparni.sinkitmaven;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board {
    
    private final JPanel board;
    private final int SQUARE = 50;
    private final Color borderGrey = new Color(242, 242, 242);
    private GridBagConstraints c;

    /**
    * Constructs a Board Class. 
    * Makes a board for boats. 13 x 12 squares of each 50 x 50 pixels in size.
    */
    public Board() {
        System.out.println("Board initialized");
        this.board = new JPanel();
        this.board.setBackground(Color.WHITE);
        this.board.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
              
        int squareSize = SQUARE;
        
        Dimension d = new Dimension();
        d.width = SQUARE;
        d.height = SQUARE;
        
        for(int i = 1; i < 14; i++) {
            for(int j = 1; j < 12; j++) {
                JLabel square = new JLabel("");
                square.setOpaque(false);
                square.setSize(SQUARE, SQUARE);
                square.setBackground(new Color(255, 164, 233));
                square.setMinimumSize(d);
                square.setPreferredSize(d);
                square.setBorder(BorderFactory.createLineBorder(this.borderGrey));
                c.gridx = i;
                c.gridy = j;
                this.board.add(square, c);
            }
        }
    }
    
    /**
    * Adds a Ship to the board 
    * @param type A ship's type: 1,2,4,6 or 8.
    * @param x position in x axis from left to right.
    * @param y position in y axis from top to bottom.
    */
    public void addShip(int type, int x, int y) {
        
        if(x > 13 || x < 0) {
            x = 1;
        }
        
        if(y > 11 || y < 0) {
            y = 1;
        }
        
        Ship t = new Ship();
        t.buildShip(type);
                
        c.gridwidth = t.getColumns();
        c.gridheight = t.getRows();
    
        c.gridx = x;
        c.gridy = y;
        
        this.board.add(t.getShip(), c, 0);
    }
    
    /**
    * @return JPanel of this board.
    */
    public JPanel getBoard() {
        return this.board;
    }
    
  
      
}
