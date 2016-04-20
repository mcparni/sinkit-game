package com.mcparni.sinkitmaven;

import java.util.ArrayList;

/**
 * @author  mcparni
 * @version 1.0 
 */


public class Board {
    
    private String[][] board;
    private final int ROWS = 11;
    private final int COLUMNS = 13;
    private final int EIGHTS = 1;
    private final int SIXES = 2;
    private final int FOURS = 2;
    private final int TWOS = 3;
    private final int ONES = 3;
    private ArrayList<Ship> ships;

    /**
    * Constructs a Board Class. 
    * Makes a board for boats. 13 x 11 units.
    */
    public Board() {
        //System.out.println("Board initialized");
        
        this.board = new String[this.COLUMNS][this.ROWS];
        this.ships = new ArrayList();
       
        for(int i = 0; i < this.ROWS; i++) {    
            for(int j = 0; j < this.COLUMNS; j++) {
                this.board[j][i] = "-";
                
            }
        }
       
    }
    
    /**
    *  Prints current board.
    */
    public void printBoard() {
     for(int i = 0; i < this.ROWS; i++) {  
            for(int j = 0; j < this.COLUMNS; j++) {
               System.out.print(this.board[j][i] + "  "); 
            }
            System.out.println();
        }
    }
    
     /**
    * @return String array of this board.
    */
    public String[][] getBoard() {
        return this.board;
    }
    
    private boolean isWithinBounds(int x, int y) {
        boolean withinBounds = true;
        if(x > this.COLUMNS-1 || x < 0 || y > this.ROWS-1 || y < 0) {
            withinBounds = false;
            
        }
        return withinBounds;
    }
    
    /**
    * Adds a Ship to the board 
    * @param type A ship's type: 1,2,4,6 or 8.
    * @param x position in x axis from left to right.
    * @param y position in y axis from top to bottom.
    * @param character is a string that symbols a ship in array
    */
    public void addShip(int type, int x, int y, String character) {
        
        if(!isWithinBounds(x, y)) {
            x = 1;
            y = 1;
        }
        
        Ship ship = new Ship();
        ship.buildShip(type, character);
        ship.setX(x);
        ship.setY(y);
        
        this.ships.add(ship);
        updateShipsOnBoard(ship);   
     
    }
    
    /**
     * Updates the ships in the board array.
     * @param ship is the specific ship in question of Ship class. 
     * 
     */
    private void updateShipsOnBoard(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        int columns = ship.getColumns();
        int rows = ship.getRows();
        
        for(int i = x; i < x + columns; i++) {
            for(int j = y; j < y + rows; j++) {
                this.board[i][j] = ship.getShip()[i-x][j-y];
            }
        } 
    }
    
    
    /**
     * Bombs the board at given coordinates and returns
     * whether it's a hit, miss, hit and sink or if the
     * area has been already bombed.
     * @param x coordinate of bomb in x axis
     * @param y coordinate of bomb in y axis
     * @return bombresult as an integer.
     * -1 : is a miss., 
     *  0 : is already bombed, 
     *  1 : ship is hit but not sinked., 
     *  2 : ship is hit and sinked.
     */
    public int bomb(int x , int y) {
                
        int bombResult = -1;
        
        if(!isWithinBounds(x, y)) {
            x = 1;
            y = 1;
        }
        if(this.board[x][y].equals("x") || this.board[x][y].equals("o")) {
             
            bombResult = 0;
        } else {
            int shipIndex = this.bombHitTestWithShip(x, y);
            
            if(shipIndex == -1) {
                bombResult = -1;
                this.board[x][y] = "o";
            } else {                
                bombResult = hitShip(x, y,shipIndex);
            }
        }
       // System.out.println("bomb: " + bombResult);
        return bombResult;
    }
    
    /**
     * Get the amount of ships on board
     * @return size of ships array list.
    */
    public int getShipCount() {
        return this.ships.size();
    }
    
    /**
     * Get width of the board
     * @return integer of how many columns is the width.
    */
    public int getColumns() {
        return this.COLUMNS;
    }
    
    /**
     * Get height of the board
     * @return integer of how many rows is the height.
    */
    public int getRows() {
        return this.ROWS;
    }
    
    private int hitShip(int x , int y, int shipIndex) {
            
        int bombResult;

        Ship s = this.ships.get(shipIndex);
        int columns = s.getColumns();
        int rows = s.getRows();
        int shipX = s.getX();
        int shipY = s.getY();
        int localX = x - shipX;
        int localY = y - shipY;

        if(s.registerHit(localX, localY)) {
            this.ships.remove(shipIndex);
            bombResult = 2;
        } else {
            bombResult = 1;
        }
        this.board[x][y] = "x";
        this.updateShipsOnBoard(s);

        return bombResult;
             
    }

    
   /**
    * Adds all ships to board at random positions.
    */
    
    public void addAllShipsAtRandom() {
        
        String[]characters = {"a","b","c","d","e","f","g","h","i","j","k","l"};
        int cursor = 0;
    
        // Square Eight type of SHIP
        for(int i = 0; i < this.EIGHTS; i++) {
            Ship s8 = new Ship();
            s8.buildShip(8, characters[cursor]);
            this.placeShip(s8);
            cursor +=1;
            
        }
        // Square Six type of SHIP
        for(int i = 0; i < this.SIXES; i++) {
            Ship s6 = new Ship();
            s6.buildShip(6, characters[cursor]);
            this.placeShip(s6);
            cursor +=1;
            
        }
        // Square Four type of SHIP
        for(int i = 0; i < this.FOURS; i++) {
            Ship s4 = new Ship();
            s4.buildShip(4, characters[cursor]);
            this.placeShip(s4);
            cursor +=1;
        }
       
        // Square Two type of SHIP
        for(int i = 0; i < this.TWOS; i++) {
            Ship s2 = new Ship();
            s2.buildShip(2, characters[cursor]);
            this.placeShip(s2);
            cursor +=1;
            
        }
         // Square One type of SHIP
        for(int i = 0; i < this.ONES; i++) {
            Ship s1 = new Ship();
            s1.buildShip(1, characters[cursor]);
            this.placeShip(s1);
            cursor +=1;
            
        }
        
    
    }
    
    private void placeShip(Ship s) {
        int x = 0;
        int y = 0;
        boolean hits = false;
        
        while(!hits) {
            x = this.getRandomIntegerBetween(0, (this.COLUMNS - 1));
            y = this.getRandomIntegerBetween(0, (this.ROWS - 1));
            s.setX(x);
            s.setY(y);
            if(!this.hitTestWithBoard(s) && !this.hitTestWithShip(s)) {
                hits = true;
            }
        }  
        this.addShip(s.getShipType(), x, y, s.getCharacter());
    }
    
    private int getRandomIntegerBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    private boolean hitTestWithBoard(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        int width = ship.getColumns();
        int height = ship.getRows();  
      
        if((x + width -1) > this.COLUMNS -1 || (y + height -1) > this.ROWS -1 ) {
            // hit to wall
            return true;
        }
        
        return false;
        
     
    }
    
    private int bombHitTestWithShip(int x, int y) {
        

        int width = 1;
        int height = 1;
        int shipIndex = -1;
        
        for(int i = 0; i< ships.size(); i++) {
            Ship test = ships.get(i);
            if((x + width -1) >= test.getX()
                    && x < test.getX() + test.getColumns()
                    && (y + height -1) >= test.getY()
                    && y  <  test.getY()  + test.getRows()) {
                
                shipIndex = i;
            }
        }
        
        return shipIndex;
    }
    
    private boolean hitTestWithShip(Ship ship) {
        
        int x = ship.getX();
        int y = ship.getY();
        int width = ship.getColumns();
        int height = ship.getRows();
        boolean hitTest = false;
        
        for(int i = 0; i< ships.size(); i++) {
            Ship test = ships.get(i);
            if((x + width -1) >= test.getX()
                    && x < test.getX() + test.getColumns()
                    && (y + height -1) >= test.getY()
                    && y  <  test.getY()  + test.getRows()) {
                
                hitTest = true;
            }
        }
        
        return hitTest;
    }
         
}
