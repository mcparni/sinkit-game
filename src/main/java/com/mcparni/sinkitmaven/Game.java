package com.mcparni.sinkitmaven;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

/**
 * @author  mcparni
 * @version 1.0 
 */


public class Game implements MouseListener {
    
    Board currentBoard;
    GUI gui;
    
    /**
    * Constructs a Game Class. 
    * 
    * Initializes one board of ships with random locations.
    * 
    * 
    */
    public Game() {
        System.out.println("Game init");
       
        
        /*Board board = new Board();
        board.addAllShipsAtRandom(); 
        board.printBoard();
        
        
        // You can test bombing with this following method:*/
        
        this.currentBoard = new Board();
        Board board = new Board();
        
        
        board.addAllShipsAtRandom(); 
        this.currentBoard = board;
        System.out.println("bomb: " + this.currentBoard.getBoard()[11][0]);
        gui = new GUI();
        gui.drawBoard(board);
        //handleBombResult(board.bomb(11, 0));
        //board.printBoard();
        //bombInput(board);
        addBoardClickListener();
 
        
    }
    
    private void addBoardClickListener() {
      //gui.getComputerBoard().addMouseListener(this);
      int size = gui.getComputerBoard().getComponentCount();
      for(int i = 0; i < size; i++) {
          gui.getComputerBoard().getComponent(i).addMouseListener(this);
      }
    }
    
    private void bomb(int x, int y, MouseEvent e) {
        Board b = this.currentBoard;
        handleBombResult(b.bomb(x, y));
        
        System.out.println("Boats left: " + b.getShipCount());
        b.printBoard();
       // gui.drawBoard(b);
        gui.markHitOrMiss(x, y, b, e.getComponent());
    }
    
    
    /**
     *  Call this for bomb debugging.
     * @param board is the board one wishes to bomb.
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
            
             System.out.println("inputted: x " + x + " y: " + y);
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

     @Override
    public void mouseClicked(MouseEvent e) {
       /* int x = e.getX();
        int y = e.getY();
       
       
       
       
           */
       int x =  e.getComponent().getBounds().x;
       int y =  e.getComponent().getBounds().y;
       x /= 50;
       y /= 50;
       System.out.println("x: " + x + " y: " + y);
       this.bomb(x, y, e); 

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    
}
