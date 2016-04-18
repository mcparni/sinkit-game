package com.mcparni.sinkitmaven;
import java.util.Scanner;
import javax.swing.JFrame;

public class Game {
    
    /**
    * Constructs a Game Class. 
    * 
    * Initializes one board of ships with random locations.
    * 
    * 
    */
    public Game() {
        System.out.println("Game init");
        
        JFrame mainFrame = new JFrame("Sink-it");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Board board = new Board();
        board.addAllShipsAtRandom(); 
        board.printBoard();
        
        
        // You can test bombing with this following method:
        bombInput(board);

        /*mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setSize(1024,576);*/
        
    }
    
    /**
     *  Call this for bomb debugging.
     * 
     */
    
    private void bombInput(Board board) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        int y = 0;
        boolean stop = false;
        System.out.println("give 99 to x or y to quit bombing");
        while(!stop) {
            System.out.println("give an x value for the bomb (0-12): ");
            x = sc.nextInt();
            System.out.println("give an y value for the bomb (0-10): ");
            y = sc.nextInt();
            handleBombResult(board.bomb(x, y));
            System.out.println("Boats left: " + board.getShipCount());
            board.printBoard();
            if(x == 99 || y == 99) {
                stop = true;
            }
        }
        System.out.println("Bombing stopped.");
    }
    
    private void handleBombResult(int type) {
        switch (type) {
           case -1:
                //Miss
                System.out.println("Miss.");
                break;
           case 0:
                //Already used
                System.out.println("Already used block.");
                break;
           case 1:
                //Ship hit
                System.out.println("Ship hit.");
                break;
           case 2:
               //Sinked
                System.out.println("Sinked it!");
                break;
           default:
               System.out.println("Miss.");
               
        }
    
    }
    
    
}
