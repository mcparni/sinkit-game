package com.mcparni.sinkitmaven;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
    private JPanel humanBoard;
    
    private GridBagLayout gridBagLayout;
    private GridBagConstraints constraints;

    private JTextArea statusText;
    private JButton continueButton;
    
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
        
        this.MAIN_FRAME = new JFrame("Sink-it");
        this.MAIN_FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.STAGE = new JPanel();
        this.MAIN_FRAME.getContentPane().add(this.STAGE,BorderLayout.LINE_END);
        
        this.CONTROL_VIEW = new JPanel();
        this.CONTROL_VIEW.setLayout(new BoxLayout(this.CONTROL_VIEW, BoxLayout.Y_AXIS));
        this.CONTROL_VIEW.setSize(this.CONTROL_VIEW_SIZE);
        this.STAGE.add(this.CONTROL_VIEW,BorderLayout.CENTER);
        
        this.ACTION_VIEW = new JPanel(new CardLayout());
        this.ACTION_VIEW.setSize(this.ACTION_VIEW_SIZE);
        this.STAGE.add(this.ACTION_VIEW, BorderLayout.LINE_END);
              
        this.computerBoard = new JPanel();
        this.computerBoard.setLayout(new GridBagLayout());
        
        this.humanBoard = new JPanel();
        
        
        this.constraints = new GridBagConstraints();
    
        this.statusText = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(statusText); 
        this.statusText.setBackground(COLOR_BASE);
        this.statusText.setOpaque(true);
        this.statusText.setEditable(false);
     
        //this.statusText.setSize(250, 300);
        
        
        this.continueButton = new JButton("Continue");
        this.continueButton.setName("continueButton");
        this.CONTROL_VIEW.add(statusText);
        
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.CONTROL_VIEW.add(continueButton);
       
        
        this.MAIN_FRAME.pack();
        this.MAIN_FRAME.setResizable(false);
        this.MAIN_FRAME.setVisible(true);
        this.MAIN_FRAME.setSize(this.WINDOW_SIZE);
    }
    
    public void clearMessages() {
        this.statusText.setText("");
    }
    
    public void publishMessage(String message) {
        String currentMessage = this.statusText.getText();
        
        this.statusText.setText(currentMessage +"\n"+message);
    }
    
    public void markComputerHit(int x, int y, Board board) {
                
                Dimension d = new Dimension(this.SQUARE, this.SQUARE);
 
                this.constraints.fill = GridBagConstraints.NONE;
                this.constraints.weightx = 0;

                JLabel square = new JLabel("");
                square.setOpaque(true);
                square.setSize(d);
                String character = board.getBoard()[x][y];
                Color color;
                if(character.equals("-")) {
                    color = this.COLOR_MISS;
                } else {
                    color = this.COLOR_HIT;
                }
                square.setBackground(color);
                
                System.out.println("x: " + character);
                square.setMinimumSize(d);
                square.setPreferredSize(d);
                square.setBorder(BorderFactory.createLineBorder(this.COLOR_BORDER));
                this.constraints.gridx = (x + 1);
                this.constraints.gridy = (y +1);
               
                this.humanBoard.add(square, this.constraints, 0); 
                
                this.constraints.gridx = 1;
                this.constraints.gridy = 1;
        

        
                System.out.println("x: " + x + "y: " + y);
                this.humanBoard.setComponentZOrder(square, 0);
                this.humanBoard.revalidate();
                this.humanBoard.repaint();
      
    }
    
    public void hideContinue() {
        this.continueButton.setVisible(false);
    }
    public void showContinue() {
        this.continueButton.setVisible(true);
    }
    
    public JButton getContinue() {
        return this.continueButton;
    }
    
    
    public void markHitOrMiss(int x, int y, Board board, Component square) {

       String character = board.getBoard()[x][y];
       System.out.println("Char: " + character);
       square.setBackground(resolveColor(character));

    }
    /**
     * @return this square size in pixels.
     * 
     */
    public int getSquareSize() {
        return this.SQUARE;
    }
    
    public void clearBoard() {
        System.out.println("Clearing");
            boolean empty = false;
           // this.humanBoard.removeAll();
           // this.humanBoard.revalidate();
           // this.humanBoard.repaint();
           this.ACTION_VIEW.removeAll();
           this.ACTION_VIEW.revalidate();
           this.ACTION_VIEW.repaint();
           
            
    }
    
    private Color resolveColor(String character) {
        
        Color color;

        //String[]characters = {"a","b","c","d","e","f","g","h","i","j","k","l"};
        switch (character) {
            case "o":
                System.out.println("char: " + character);
                return this.COLOR_MISS;
            case "x":
                return this.COLOR_HIT;
            case "a":
                return this.COLOR_8;
            case "b":
            case "c":
                return this.COLOR_6;
            case "d":
            case "e":
                return this.COLOR_4;
            case "f":
            case "g":
            case "h":
                return this.COLOR_2;
            case "i":
            case "j":
            case "k":
                return this.COLOR_1;
            default:
                return COLOR_BASE;
        }
        
    }
    public void drawHumanBoard(Board board) {
        Dimension d = new Dimension(this.SQUARE, this.SQUARE);
        JPanel _humanBoard = new JPanel();
        _humanBoard.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.NONE;
        //c.weightx = 0;
        
        
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
                c.gridx = i;
                c.gridy = j;
               
                _humanBoard.add(square, c, 0); 
                
                
            }
        }
        c.gridx = 1;
        c.gridy = 1;
        

        
        this.ACTION_VIEW.add(_humanBoard, 0);
        
        _humanBoard.revalidate();
        _humanBoard.repaint();
        this.humanBoard = _humanBoard;
       
    }
    public void drawComputerBoard(Board board) {
        
        
        
        Dimension d = new Dimension(this.SQUARE, this.SQUARE);
 
        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.weightx = 0;
        
        
        for(int i = 1; i < (board.getColumns() +1); i++) {
            for(int j = 1; j < (board.getRows() +1); j++) {
                JLabel square = new JLabel("");
                square.setOpaque(true);
                square.setSize(d);
                //square.setBackground(COLOR_BASE);
                //System.out.println("color: " + resolveColor(character));
                /*String character = board.getBoard()[i-1][j-1];
                square.setBackground(resolveColor(character));*/
                square.setBackground(COLOR_BASE);
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
    
    public void swapBoardOrder() {
        //this.ACTION_VIEW.remove
        CardLayout cl = (CardLayout)(this.ACTION_VIEW.getLayout());
        cl.next(this.ACTION_VIEW);
        System.out.println("c: " + this.ACTION_VIEW.getComponentCount());
        /*JPanel temp = (JPanel) this.ACTION_VIEW.getComponent(0);
        JPanel temp2 = (JPanel) this.ACTION_VIEW.getComponent(1);*/
        
    }
    
    public JPanel humanBoard() {
        return this.humanBoard;
    }
    
    public JPanel getComputerBoard() {
        return this.computerBoard;
    }
    


    
}
