package com.mcparni.sinkitmaven;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author  mcparni
 * @version 1.0 
 */


public class Game implements MouseListener, ActionListener {
    
    Board currentBoard;
    GUI gui;
    boolean turn;
    boolean gameOver;
    String winner;
    
    Board humanBoard;
    Board computerBoard;
    int messageCount;
    ArrayList<Point> pointList;
    
    
    GameTime gametime;
    HighScore highscore;
    
    /**
    * Constructs a Game Class. 
    * 
    * Initializes the controller class.
    * 
    * 
    */
    public Game() {
        System.out.println("Game init");
        this.gui = new GUI();
        addButtonListeners();
        displayHighScores();
        phaseInitial();
        //startNewGame();
    }
    
    private void displayHighScores() {
        try {
            this.highscore = new HighScore();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        String scoresText = "SinkIt!\n\nHIGHSCORES\n\n";
        int entries = this.highscore.getNamesAndTimes().size();
        for(int i = 0; i < entries -1; i ++) {
            scoresText += this.highscore.getNames().get(i) + "\t\t" +this.highscore.getTimes().get(i) + "\n";
        }
        this.gui.publishMessage(scoresText);
        
    }
    
    private void addButtonListeners() {
        for(int i = 0; i < gui.getButtons().length; i ++) {
            gui.getButtons()[i].addActionListener(this);
        }        
    }
    
    private void startNewGame() {
        this.turn = false;
        this.gameOver = false;
        this.winner = "-";
        this.messageCount = 0;
                
        this.computerBoard = new Board();
        this.humanBoard = new Board();

        this.gametime = new GameTime();
        this.gametime.startTime();
        
        this.humanBoard.addAllShipsAtRandom();
        this.computerBoard.addAllShipsAtRandom(); 
        
        gui.clearMessages();
        gui.startNewGame();
        gui.drawComputerBoard(this.computerBoard);
        gui.drawHumanBoard(this.humanBoard);
        
        this.pointList = new ArrayList();
     
       
        for(int i = 0; i < humanBoard.getColumns(); i++) {
            for(int j = 0; j < humanBoard.getRows(); j++) {
                Point p = new Point(i, j);
                this.pointList.add(p);
            }
        }

        phaseShipSelect();

    }   
    
    private void endGame() {
        this.turn = true;
        this.gameOver = true;
        this.winner = "-";
        this.messageCount = 0;
        
        this.computerBoard = null;
        this.humanBoard = null;
        this.highscore = null;
        this.gametime.stopTime();
        this.pointList.clear();
        removeComputerBoardClickListeners();
    
    }
    
      
    /**
     * Checks if the current score is enough for the top list
     * @param seconds is the time used in the game in seconds.
     */
    private void checkNewHighScore(int seconds) {
        int index = this.highscore.testHighScore(seconds);
        if(index > -1) {
            String name = gui.getPlayerNamePrompt();
            try{
                this.highscore.enterNewEntry(seconds, index, name);
            } catch (Exception e) {
                System.out.println("exception: " + e);
            }
        }
        
        phaseGameOver();
       
    }
    
    /**
     * Game is over and this method presents the winner.
     * @param winner is either Computer or Human.
     */
    private void presentWinner(String winner) {
       this.gametime.stopTime();
       removeComputerBoardClickListeners();
       gui.clearMessages();
       gui.publishMessage(winner + " is the winner");
       if(winner.equals("Human")) {
           int seconds = this.gametime.getSeconds();
           checkNewHighScore(seconds);
       } else {
           phaseGameOver();
       }
    }
    
    
    /**
     * Checks whether the game is over or not.
     * If not handles the turns between human and computer.
     */
    private void checkGameOver() {
        
        if(this.humanBoard.getShipCount() == 0) {
            this.gameOver = true;
            this.winner = "Computer";
            presentWinner(this.winner);
            
        } else if (this.computerBoard.getShipCount() == 0) {
            this.gameOver = true;
            this.winner = "Human";
            presentWinner(this.winner);
            
        } else {
            if(this.turn) {

                int randomPosition = this.getRandomIntegerBetween(0, (this.pointList.size() -1));
               // gui.swapBoardOrder();        
                //this.currentBoard = this.humanBoard;
                int x = this.pointList.get(randomPosition).x;
                int y = this.pointList.get(randomPosition).y;
                int type = this.humanBoard.bomb(x, y);
                handleBombResult(type);
                gui.markComputerHit(x, y, type);
                this.pointList.remove(randomPosition);
                System.out.println("human: " + this.pointList.size());
                this.humanBoard.printBoard();
                

            } else {
                System.out.println("turn galse:");
                System.out.println("computer:");
                this.computerBoard.printBoard();
        
                //gui.swapBoardOrder(); 

            }
        }
    
    }
    
    private void makeNewShipLayout() {
        this.humanBoard.clearBoard();
        this.gui.clearBoard();
        this.humanBoard.addAllShipsAtRandom();
        this.humanBoard.printBoard();
        this.gui.drawHumanBoard(this.humanBoard);
        
    }
    

    /**
     * Removes click listeners on computers' board.
     */
    private void removeComputerBoardClickListeners() {
        int size = gui.getComputerBoard().getComponentCount();
        for(int i = 0; i < size; i++) {
            gui.getComputerBoard().getComponent(i).removeMouseListener(this);
        }

    }
    
    /**
     * Adds click listeners on computers' board.
     */
    private void addComputerBoardClickListener() {
      int size = gui.getComputerBoard().getComponentCount();
      for(int i = 0; i < size; i++) {
          gui.getComputerBoard().getComponent(i).addMouseListener(this);
      }
    }
    
    
    /**
     * Handles bombing the computers' board.
     * @param x is the x coordinate of the bomb.
     * @param y is the y coordinate of the bomb.
     * @param e is the mouse event. Click in this context.
     */
    private void bomb(int x, int y, MouseEvent e) {
        System.out.println("bom");
        Board b = this.computerBoard;
        handleBombResult(b.bomb(x, y));
        
        gui.markHitOrMiss(x, y, b, e.getComponent());
    }
    
    
    /**
     * Returns a random integer between given integers
     * @param min is the lowest desired integer.
     * @param max is the highest desired integer.
     * @return random integer.
     */
    private int getRandomIntegerBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
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
                //gui.swapBoardOrder();
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
    /**
     * Method to handle different bombing results 
     * returned from bombing the board.
     * 
     * @param type is type of result:
     * -1 = miss
     *  0 = already bombed square
     *  1 = ship hit
     *  2 = ship hit and sinked.
     */
    private void handleBombResult(int type) {
        
        String message = "";
        String player;
        this.messageCount +=1;
        if(this.messageCount % 2 != 0) {
            this.gui.clearMessages();
        } else {
            
        }
        
        if(this.turn) {
           player = "Computer";
        } else {
            player = "Human";
            
        }
        switch (type) {
           case -1:
                this.turn = !this.turn;
                System.out.println(" miss.");
                message = " miss";
                this.gui.publishMessage(this.messageCount +": "+ player + message);
                break;
           case 0:
                //Already used
                this.turn = this.turn;
                System.out.println(" already used block.");
                message = " already used. Try again.";
                this.gui.publishMessage(this.messageCount +": "+ player + message);
                break;
           case 1:
                //Ship hit
                this.turn = !this.turn;
                System.out.println(" hit.");
                message = " hit.";
                this.gui.publishMessage(this.messageCount +": "+ player + message);
                break;
           case 2:
               //Sinked
                this.turn = !this.turn;
                System.out.println(" sinked it!");
                message = " sinked it!";
                this.gui.publishMessage(this.messageCount +": "+ player + message); 
                break;
           default:
               this.turn = !this.turn;
               System.out.println(" miss.");
               message = " miss.";
               this.gui.publishMessage(this.messageCount +": "+ player + message);
        }
        
        if(this.messageCount % 2 == 0) {
            this.gui.publishMessage(getShipsLeft());
        } else {
            
        }
        
        checkGameOver();

    }
    
    private String getShipsLeft() {
        String shipsLeft = "\n\nShips left:\n\nHuman\t\tComputer\n" + this.humanBoard.getShipCount() + "\t\t" + this.computerBoard.getShipCount();
        return shipsLeft;
    }
    
    
    /**
     * Handles the clicking on computers' board.
     * @param e is the mouse event.
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       int squareSize = this.gui.getSquareSize();
       int x =  e.getComponent().getBounds().x;
       int y =  e.getComponent().getBounds().y;
       x /= squareSize;
       y /= squareSize;
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
    
    private void phaseBeginBombing() {
        this.gui.clearMessages();
        addComputerBoardClickListener();
        this.gui.publishMessage("It's your turn. Start bombing by selecting a square of choice from the bottom board.");
        this.gui.setCursorCrosshair();
        this.gui.getButtons()[0].setVisible(false);
        this.gui.getButtons()[1].setVisible(false);
    }
    private void phaseInitial() {
        this.gui.getButtons()[0].setVisible(true);
        this.gui.getButtons()[1].setVisible(false);
        this.gui.getButtons()[2].setVisible(true);
        this.gui.getButtons()[0].setText("New Game");
        this.gui.getButtons()[1].setText("Instructions");
        this.gui.getButtons()[2].setText("Close Program");
        
    }
    private void phaseShipSelect() {
        this.gui.clearMessages();
         this.gui.getButtons()[1].setVisible(true);
        this.gui.publishMessage("Your ships are on the top right board.\nAre you happy with the ships like that?");
        this.gui.getButtons()[0].setText("Yes");
        this.gui.getButtons()[1].setText("No");
        this.gui.getButtons()[2].setText("Quit Game");
       // this.ACTION_VIEW.setVisible(true);
    }
    private void phaseGameOver() {
        this.gui.clearMessages();
        this.gui.clearViews();
        endGame();
        displayHighScores();
        phaseInitial();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch(e.getActionCommand()) {
            case "New Game":
               System.out.println("new");
               startNewGame();
               
               break;
            case "Instructions":
               System.out.println("inst");
               break;
            case "Close Program":
               System.exit(0);
               break;
            case "No":
               makeNewShipLayout();
               break;
            case "Yes":
                System.out.println("yes");
                phaseBeginBombing();
                // start game etc, clear texts
               //this.gui.swapBoardOrder();
               break;
            case "Quit Game":
               System.out.println("quit");
               phaseGameOver();
               break;
        }
        
       
    }
    
    
}
