package com.mcparni.sinkitmaven;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Ship implements MouseListener {
    
    private final int STEP = 50;
    private int cols = 1;
    private int rows = 1;
    private int shipType = 1;
    private final JPanel ship;
    private Color color;
    private int x;
    private int y;
    private int hitCount;
    private Color hitColor;
    
    /**
    * Constructs a Ship Class. 
    * Ship Class major component is a type of JPanel.
    * By default the ship is of type 1 which is one square of size (50x50px).
    */
    public Ship() {
        this.color = new Color(176, 255, 164);
        this.hitColor = new Color(255, 32, 32);
        this.cols = 1;   
        this.rows = 1;
        this.x = 1;
        this.y = 1;
        this.shipType = 1;
        this.hitCount = 0;
        ship = new JPanel();
        
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
    * @return RGB color value of current ship
    */
    public Color getColor() {
        return this.color;
    }
    /**
    * @return current ship type. Can be 1,2,4,6 or 8.
    */
    public int getShipType() {
        return this.shipType;
    }
    /**
    * @return how many pixels does one square allocate.
    */
    public int getSquareSize() {
        return this.STEP;
    }
    /**
    * @return returns a JPanel (this Ship class).
    */
    public JPanel getShip() {
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
     * Builds a ship of given type as a parameter.
     * @param type many squares of space ship takes
     * on a board grid. Valid values(1,2,4,6,8) defaults to 1
     */
   
    public void buildShip(int type) {
        
        switch(type) {
            case 1 :
               // squareOne green;
               this.color = new Color(176, 255, 164);
               this.cols = 1;
               this.rows = 1;
               this.shipType = type;
               break;
            case 2 :
               // squareTwo blue
               this.color = new Color(164, 205, 255);
               this.cols = 2;
               this.rows = 1;
               this.shipType = type;
               break;
            case 4 :
               // squareFour orange
               this.color = new Color(255, 206, 164);
               this.cols = 2;
               this.rows = 2;
               this.shipType = type;
               break;
            case 6 :
               // squareSix pink 
               this.color = new Color(255, 164, 233);
               this.cols = 2;
               this.rows = 3;
               this.shipType = type;
               break;
            case 8 :
               // squareEight red
               this.color = new Color(255, 164, 164);
               this.cols = 2;
               this.rows = 4;
               this.shipType = type;
               break;
            default :
                // squareOne green;
               this.color = new Color(176, 255, 164);
               this.cols = 1;
               this.rows = 1;
               this.shipType = 1;
               break;
               
        }
        
        int w = this.cols * STEP;
        int h = this.rows * STEP;
        Dimension d = new Dimension(w, h);
        this.ship.setSize(d);
        this.ship.setMinimumSize(d);
        this.ship.setPreferredSize(d);
        
        this.ship.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = this.cols;
        c.gridheight =  this.rows;
       
        for(int i = 1; i < (this.cols + 1); i++) {
            for(int j = 1; j < (this.rows + 1); j++) {
                c.gridx = i;
                c.gridy = j;
                c.gridwidth = 1;
                c.gridheight =  1;
                c.fill = GridBagConstraints.NONE;
                c.weightx = 0;
                this.ship.add(addShipBlock(), c, 0);
                
            }
        }
    }
    /**
     * Adds one square of colored label to Ship container.
     * This is called equal amount of times that a ship has squares.
     * To be used later to indicate hits.
     * @return square of JLabel type.
     */
    private JLabel addShipBlock() {
        
        JLabel square = new JLabel("");
        square.setOpaque(true);
        
        /*
        // CHANGE BACK
        // square.setBackground(this.color);
        */
        
        square.setBackground(Color.WHITE);
        square.setBorder(BorderFactory.createLineBorder(new Color(242, 242, 242)));
        
        
        Dimension d = new Dimension(this.STEP,this.STEP);
        square.setPreferredSize(d);
        square.setMinimumSize(d);
        square.addMouseListener(this);
        return square;
    } 
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // e.getX();
        // e.getY();
        JLabel target = (JLabel) e.getSource();
        Color targetColor = target.getBackground();
        if(this.hitColor.equals(targetColor)) {
            System.out.println("already clicked");
        } else {
            System.out.println("Ship hit!");
            this.hitCount +=1;
            target.setBackground(this.hitColor);
            if(this.hitCount == this.shipType) {
                System.out.println("Sinked it!");
            }
        }
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
