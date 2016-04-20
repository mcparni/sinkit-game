package com.mcparni.sinkitmaven;

/**
 * @author  mcparni
 * @version 1.0 
 */


public class Ship {
    
    private int cols;
    private int rows;
    private int shipType;
    private String[][] ship;
    private int x;
    private int y;
    private int hitCount;
    private String character;
    
    /**
    * Constructs a Ship Class. 
    */
    public Ship() {
        //System.out.println("Ship initialized");
        this.cols = 1;   
        this.rows = 1;
        this.x = 1;
        this.y = 1;
        this.shipType = 1;
        this.character = "-";
        this.hitCount = 0;
        this.ship = new String[this.cols][this.rows];
        
    }
    /**
    * @return how many squares of space current ship allocates in x axis.
    */
    public int getColumns() {
        return this.cols;
    }
    /**
    * @return how many squares of space current ship allocates in y axis.
    */
    public int getRows() {
        return this.rows;
    }
    /**
    * @return current ship type. Can be 1,2,4,6 or 8.
    */
    public int getShipType() {
        return this.shipType;
    }
    /**
    * @return returns an String array of ship (this Ship class).
    */
    public String[][] getShip() {
        return this.ship;
    }
    
    /**
    * @return returns Ships' position in x axis
    */
    public int getX() {
        return this.x;
    }
    
    /**
    * @return returns Ships' position in y axis
    */
    public int getY() {
        return this.y;
    }
    
    /**
    * @return returns Ships' character symbol
    */
    public String getCharacter() {
        return this.character;
    }
    
    /**
    * @param x sets Ship's position in x axis
    */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
    * @param y sets Ship's position in y axis
    */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
    * @param x coordinate of hit in x axis
    * @param y coordinate of hit in y axis
    * @return boolean whether the ship is sinked or not
    */
    public boolean registerHit(int x, int y) {
        boolean sinked;
        this.ship[x][y] = "x";
        this.hitCount += 1;
        
        if(this.hitCount == this.shipType) {
            sinked = true;
        } else {
            sinked = false;
        }

        return sinked;
    }
    
    /**
     * Prints this ship.
     */
    public void printShip() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j <this.cols; j++) {
               System.out.print(this.ship[j][i] + "  ");    
            }
            System.out.println();
        }
    }

    private int getRandomIntegerBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     * Builds a ship of given type as a parameter.
     * @param type many squares of space ship takes
     * on a board grid. Valid values(1,2,4,6,8) defaults to 1
     * @param character is a string that symbols a ship in array
     */
    public void buildShip(int type, String character) {
        switch(type) {
            case 1 :
               this.cols = 1;
               this.rows = 1;               
               this.shipType = type;
               break;
            case 2 :
               this.cols = 2;
               this.rows = 1;
               this.shipType = type;
               break;
            case 4 :
               this.cols = 2;
               this.rows = 2;
               this.shipType = type;
               break;
            case 6 :
               this.cols = 2;
               this.rows = 3;
               this.shipType = type;
               break;
            case 8 :
               this.cols = 2;
               this.rows = 4;
               this.shipType = type;
               break;
            default :
               this.cols = 1;
               this.rows = 1;
               this.shipType = 1;
               break;
               
        }
        this.character = character;
       
        String[][] _ship = new String[this.cols][this.rows];


        // to be implemented
        int rotateState = getRandomIntegerBetween(0, 1);

       
        for(int i = 0; i < this.cols; i++) {
            for(int j = 0; j < this.rows; j++) {
                _ship[i][j] = character;
            }
        } 
        this.ship = _ship;  
    }
    
        
}
