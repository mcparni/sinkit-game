package com.mcparni.sinkitmaven;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author  mcparni
 * @version 1.0 
 */



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
    
    private final int SQUARE;
    
    private final Dimension WINDOW_SIZE;
    private final Dimension ACTION_VIEW_SIZE;
    private final Dimension CONTROL_VIEW_SIZE;
   
    private final JFrame MAIN_FRAME;
    private final JPanel STAGE;
    private final JPanel CONTROL_VIEW;
    private final JPanel ACTION_VIEW;
        
    private JPanel computerBoard;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints constraints;
    
    public GUI() {
        System.out.println("GUI init.");
        // squareEight red
        this.COLOR_8 = new Color(255, 164, 164);        
        // squareSix pink
        this.COLOR_6 = new Color(255, 164, 233);
        // squareFour orange
        this.COLOR_4 = new Color(255, 206, 164);
        // squareTwo blue
        this.COLOR_2 = new Color(164, 205, 255);
        // squareOne green;
        this.COLOR_1 = new Color(176, 255, 164);
        // red
        this.COLOR_MISS = new Color(200, 200, 200);
        // black
        this.COLOR_HIT = new Color(0, 0, 0);
        // grey
        this.COLOR_BORDER = new Color(242, 242, 242);
        // white
        this.COLOR_BASE = new Color(255, 255, 255);
        
        this.WINDOW_SIZE = new Dimension(1024,576);
        
        this.ACTION_VIEW_SIZE = new Dimension(650, 550);
        
        this.CONTROL_VIEW_SIZE = new Dimension(374,550);
        
        this.SQUARE = 50;
        
        MAIN_FRAME = new JFrame("Sink-it");
        MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        STAGE = new JPanel();
        MAIN_FRAME.getContentPane().add(STAGE,BorderLayout.LINE_END);
        
        CONTROL_VIEW = new JPanel();
        CONTROL_VIEW.setSize(this.CONTROL_VIEW_SIZE);
        STAGE.add(CONTROL_VIEW,BorderLayout.CENTER);
        
        ACTION_VIEW = new JPanel();
        ACTION_VIEW.setSize(this.ACTION_VIEW_SIZE);
        STAGE.add(ACTION_VIEW, BorderLayout.LINE_END);
              
        computerBoard = new JPanel();
        this.computerBoard.setLayout(new GridBagLayout());
        
        constraints = new GridBagConstraints();
        
        MAIN_FRAME.pack();
        MAIN_FRAME.setVisible(true);
        MAIN_FRAME.setSize(this.WINDOW_SIZE);
    }
    
    public void markHitOrMiss(int x, int y, Board board, Component square) {

       String character = board.getBoard()[x][y];
       System.out.println("Char: " + character);
       square.setBackground(resolveColor(character));

    }
    
    
    private void clearBoard() {
        System.out.println("Clearing");
            boolean empty = false;
            while(!empty) {
                if(this.computerBoard.getComponentCount() > 0) {
                    this.computerBoard.remove(0);
                    empty = false;
                } else {
                    empty = true;
                }
            }
    }
    
    private Color resolveColor(String character) {
        
        
        
        if(character.equals("o")) {
            System.out.println("char: " + character);
            return COLOR_MISS;
        } else if(character.equals("x")) {
            return COLOR_HIT;
        } else {
            return COLOR_BASE;
        }
        
    }
    public void drawBoard(Board board) {
        
        
        
        Dimension d = new Dimension(this.SQUARE, this.SQUARE);
        
        
        
        
       
        
       // this.computerBoard.setLocation(this.CONTROL_VIEW_SIZE.width +1 ,0);
        
        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.weightx = 0;
        
        //System.out.println("X: " + this.computerBoard.getX() + " Y " + this.computerBoard.getY());
        
        for(int i = 1; i < (board.getColumns() +1); i++) {
            for(int j = 1; j < (board.getRows() +1); j++) {
                JLabel square = new JLabel("");
                square.setOpaque(true);
                square.setSize(d);
                //square.setBackground(COLOR_BASE);
                //System.out.println("color: " + resolveColor(character));
                String character = board.getBoard()[i-1][j-1];
                square.setBackground(resolveColor(character));
                square.setMinimumSize(d);
                square.setPreferredSize(d);
                square.setBorder(BorderFactory.createLineBorder(this.COLOR_BORDER));
                this.constraints.gridx = i;
                this.constraints.gridy = j;
               
                this.computerBoard.add(square, this.constraints); 
            }
        }
        
        this.ACTION_VIEW.add(this.computerBoard, 0);
       
    }
    

    
    public JPanel getComputerBoard() {
        return this.computerBoard;
    }
    


    
}
