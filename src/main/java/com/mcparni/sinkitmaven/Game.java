package com.mcparni.sinkitmaven;
import javax.swing.JFrame;


public class Game {
    
    /**
    * Constructs a Game Class. 
    * Initializes the main frame where everything happens of size
    * 1024 x 576 pixels.
    * 
    * Creates a board for human players' ships.
    * 
    * Creates and places all the human players' ships to the board.
    * 
    */
    public Game() {
        System.out.println("Game init");
        
        JFrame gameFrame = new JFrame("Sink-it");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Board board = new Board();  
        gameFrame.getContentPane().add(board.getBoard());
        
        // Add initial test Ships
    
        // Square8
        board.addShip(8, 3, 2);
        // Square6
        board.addShip(6, 6, 1);
        // Square6
        board.addShip(6, 9, 1);
        // Square4
        board.addShip(4, 6, 7);
        // Square4
        board.addShip(4, 9, 8);
        // Square2
        board.addShip(2, 3, 7);
        // Square2
        board.addShip(2, 6, 5);
        // Square2
        board.addShip(2, 9, 5);
        // Square1
        board.addShip(1, 3, 9);
        // Square1
        board.addShip(1, 5, 10);
        // Square1
        board.addShip(1, 7, 10);
         
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.setSize(1024,576);
        
    }
    
    
}
