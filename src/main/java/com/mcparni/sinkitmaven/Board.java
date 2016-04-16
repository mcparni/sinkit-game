package com.mcparni.sinkitmaven;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board implements MouseListener {
    
    private final JPanel board;
    private final int SQUARE = 50;
    private final int ROWS = 11;
    private final int COLUMNS = 13;
    private final int EIGHTS = 1;
    private final int SIXES = 2;
    private final int FOURS = 2;
    private final int TWOS = 3;
    private final int ONES = 3;
    private final Color borderGrey;
    private Color missColor;
    private Color baseColor;
    private GridBagConstraints c;
    private ArrayList<Ship> ships;

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
        ships = new ArrayList();
        
        this.baseColor = new Color(255, 255, 255);
        this.missColor = new Color(200, 200, 200);
        this.borderGrey = new Color(242, 242, 242);
              
        int squareSize = SQUARE;
        
        Dimension d = new Dimension();
        d.width = SQUARE;
        d.height = SQUARE;
        
        for(int i = 1; i < (this.COLUMNS +1); i++) {
            for(int j = 1; j < (this.ROWS +1); j++) {
                JLabel square = new JLabel("");
                square.setOpaque(true);
                square.setSize(SQUARE, SQUARE);
                square.setBackground(this.baseColor);
                square.setMinimumSize(d);
                square.setPreferredSize(d);
                square.setBorder(BorderFactory.createLineBorder(this.borderGrey));
                c.gridx = i;
                c.gridy = j;
               
                this.board.add(square, c);
                
                
            }
        }
        addClickListeners();
    }
    
    private void addClickListeners() {
        int amountOfSquares = this.board.getComponentCount();
        for(int i = 0; i < amountOfSquares; i++) {
            JLabel square = (JLabel) this.board.getComponent(i);
            square.addMouseListener(this);
        }
    }
    
    /**
    * Adds a Ship to the board 
    * @param type A ship's type: 1,2,4,6 or 8.
    * @param x position in x axis from left to right.
    * @param y position in y axis from top to bottom.
    */
    public void addShip(int type, int x, int y) {
        
        if(x > this.ROWS || x < 0) {
            x = 1;
        }
        
        if(y > COLUMNS || y < 0) {
            y = 1;
        }
        
        Ship t = new Ship();
        t.buildShip(type);
        t.setX(x);
        t.setY(y);
                
        c.gridwidth = t.getColumns();
        c.gridheight = t.getRows();
    
        c.gridx = x;
        c.gridy = y;
        
        this.board.add(t.getShip(), c, 0);
    }
    
    public void addAllShipsAtRandom() {
        
        /*
        // DEBUG Manual ship
        
        Ship te = new Ship();
        te.buildShip(8);
        int _x = 4;
        int _y = 4;
        te.setX(_x);
        te.setY(_y);
        c.gridwidth = te.getColumns();
        c.gridheight = te.getRows();
    
        c.gridx = _x;
        c.gridy = _y;
       
        ships.add(te);
        this.board.add(te.getShip(), c, 0);
        
        */
        
        // Square Eight type of SHIP
        for(int i = 0; i < this.EIGHTS; i++) {
            Ship s8 = new Ship();
            s8.buildShip(8);
            this.placeShip(s8);
            
        }
        // Square Six type of SHIP
        for(int i = 0; i < this.SIXES; i++) {
            Ship s6 = new Ship();
            s6.buildShip(6);
            this.placeShip(s6);
            
        }
        // Square Four type of SHIP
        for(int i = 0; i < this.FOURS; i++) {
            Ship s4 = new Ship();
            s4.buildShip(4);
            this.placeShip(s4);
            
        }
       
        // Square Two type of SHIP
        for(int i = 0; i < this.TWOS; i++) {
            Ship s2 = new Ship();
            s2.buildShip(2);
            this.placeShip(s2);
            
        }
         // Square One type of SHIP
        for(int i = 0; i < this.ONES; i++) {
            Ship s1 = new Ship();
            s1.buildShip(1);
            this.placeShip(s1);
            
        }
        
    
    }
    
    private void placeShip(Ship s) {
        int x;
        int y;
        boolean hits = false;
        
        while(!hits) {
            x = this.getRandomIntegerBetween(1, this.ROWS);
            y = this.getRandomIntegerBetween(1, this.COLUMNS);
            s.setX(x);
            s.setY(y);
            
            c.gridx = x;
            c.gridy = y;
            c.gridwidth = s.getColumns();
            c.gridheight = s.getRows();
            if(!this.hitTestWithBoard(s) && !this.hitTestWithShip(s)) {
                hits = true;
            }
        }  
        ships.add(s);
        this.board.add(s.getShip(), c, 0);  
    }
    
    private int getRandomIntegerBetween(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    private boolean hitTestWithBoard(Ship ship) {
        int x = ship.getX();
        int y = ship.getY();
        int width = ship.getColumns();
        int height = ship.getRows();
        System.out.println("x: " + x + "width: "+ width);
        
        if((x + width -1) > this.COLUMNS || (y + height -1) > this.ROWS ) {
            System.out.println("WALL HIT  x:  " + x + " y: " + y);
            return true;
        }
        
        return false;
        
     
    }
    
    private boolean hitTestWithShip(Ship ship) {
        
        int x = ship.getX();
        int y = ship.getY();
        int width = ship.getColumns();
        int height = ship.getRows();
        
        for(int i = 0; i< ships.size(); i++) {
            Ship test = ships.get(i);
            if((x + width -1) >= test.getX()
                    && x < test.getX() + test.getColumns()
                    && (y + height -1) >= test.getY()
                    && y  <  test.getY()  + test.getRows()) {
                
                System.out.println("Hit");
                return true;
            }
        }
        
        return false;
    }
    
    /**
    * @return JPanel of this board.
    */
    public JPanel getBoard() {
        return this.board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // e.getX();
        // e.getY();
        JLabel target = (JLabel) e.getSource();
        Color targetColor = target.getBackground();
        if(this.missColor.equals(targetColor)) {
            System.out.println("already clicked");
        } else {
            System.out.println("Miss!");
            target.setBackground(this.missColor);
        }
        //System.out.println("Clicked:" + e.getX() + " " + e.getXOnScreen());
     
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
