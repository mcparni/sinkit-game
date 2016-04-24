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
        Board computerBoard = new Board();
        Board humanBoard = new Board();
        
        humanBoard.addAllShipsAtRandom();
        
        computerBoard.addAllShipsAtRandom(); 
        
        this.currentBoard = humanBoard;
        humanBoard.printBoard();
        System.out.println("ships: " + humanBoard.getShipCount());
        
        System.out.println("bomb: " + this.currentBoard.getBoard()[11][0]);
        gui = new GUI();
        gui.drawHumanBoard(humanBoard);
        gui.drawComputerBoard(computerBoard);
        //handleBombResult(board.bomb(11, 0));
        //board.printBoard();
        //bombInput(board);
        
        addComputerBoardClickListener();
        
        randomNewShipsInput();
  
    }
    
    private void addComputerBoardClickListener() {
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
     * Debug for handling new board randomization.
     * 
     */
    private void randomNewShipsInput() {
        Scanner sc = new Scanner(System.in);    
        boolean stop = false;
        while(!stop) {
            System.out.println("Are you happy with your ship layout (y/n)?");
            String s = sc.next();
            if(s.equals("n")) {
                /*stop = false;
                this.currentBoard.clearBoard();
                this.gui.clearBoard();
                this.currentBoard.addAllShipsAtRandom();
                this.currentBoard.printBoard();
                this.gui.drawHumanBoard(this.currentBoard);*/
                gui.swapBoardOrder();
            } else {
                stop = true;
            }
        }
        System.out.println("Human ready.");
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
       int squareSize = this.gui.getSquareSize();
       int x =  e.getComponent().getBounds().x;
       int y =  e.getComponent().getBounds().y;
       x /= squareSize;
       y /= squareSize;
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
