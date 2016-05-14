package com.mcparni.sinkitmaven;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author  mcparni
 * @version 1.0 
 */

    /**
     * Constructs a GUI class.
     * 
     * This class is responsible for all the visual representation.
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
    
    private int amountOfButtons;
    
    private JButton[] buttons;
    
    private JButton gameButton1;
    private JButton gameButton2;
    private JButton gameButton3;
    
    private JButton approveShips;
    
    
    
    
    
    public GUI() {
        this.amountOfButtons = 3;
        
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
        this.ACTION_VIEW.setPreferredSize(this.ACTION_VIEW_SIZE);
        //this.ACTION_VIEW.setBackground(new Color(198, 241, 255));
        this.STAGE.add(this.ACTION_VIEW, BorderLayout.LINE_END);
              
        this.statusText = new JTextArea(5, 20);
        this.statusText.setPreferredSize(new Dimension(360,400));
        
        JScrollPane scrollPane = new JScrollPane(statusText); 
        this.statusText.setBackground(COLOR_BASE);
        this.statusText.setOpaque(true);
        this.statusText.setEditable(false);
        this.CONTROL_VIEW.add(statusText);
        
        this.buttons = new JButton[this.amountOfButtons];
        
        this.gameButton1 = new JButton();
        this.gameButton1.setName("gameButton1");
        
        gameButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.CONTROL_VIEW.add(gameButton1);
        
        this.gameButton2 = new JButton();
        this.gameButton2.setName("gameButton2");
        
        gameButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.CONTROL_VIEW.add(gameButton2);
        
        this.gameButton3 = new JButton();
        this.gameButton3.setName("gameButton3");
        
        this.buttons[0] = this.gameButton1;
        this.buttons[1] = this.gameButton2;
        this.buttons[2] = this.gameButton3;
        
        phaseInitial();
        
        
        gameButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.CONTROL_VIEW.add(gameButton3);
        
        this.MAIN_FRAME.pack();
        this.MAIN_FRAME.setResizable(false);
        this.MAIN_FRAME.setVisible(true);
        this.MAIN_FRAME.setSize(this.WINDOW_SIZE);
        
        
    }
    
    public void clearViews() {
    
    }
    
    public void makeStartView() {
    
    }
    
    public void startNewGame() {
        this.computerBoard = new JPanel();
        this.computerBoard.setLayout(new GridBagLayout());
        
        this.humanBoard = new JPanel();
        
        
        this.constraints = new GridBagConstraints();
    
        
     
        //this.statusText.setSize(250, 300);
        
        
        this.continueButton = new JButton("Continue");
        this.continueButton.setName("continueButton");
        
        
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.CONTROL_VIEW.add(continueButton);
        
        
    }
    
    public void phaseInitial() {
        buttons[0].setText("New Game");
        buttons[1].setText("Instructions");
        buttons[2].setText("Close Program");
        this.ACTION_VIEW.setVisible(false);
    }
    
    public void phaseShipSelect() {
        this.clearMessages();
        this.publishMessage("Are you happy with the current ship layout?");
        buttons[0].setText("Yes");
        buttons[1].setText("No");
        buttons[2].setText("Quit Game");
        this.ACTION_VIEW.setVisible(true);
    }
    
    /**
     * @return this main frame as a JFrame
     */
    public JFrame getFrame() {
        return this.MAIN_FRAME;
    }
    
    /**
     * Clears status text field from messages.
     */
    public void clearMessages() {
        this.statusText.setText("");
    }
    
    /**
     * Publish a message in status text field.
     * @param message is the message to be published.
     */
    public void publishMessage(String message) {
        String currentMessage = this.statusText.getText();
        
        this.statusText.setText(currentMessage +"\n"+message);
    }
    
    /**
     * Presents visual bomb result by computer.
     * @param x is the x coordinate of bomb.
     * @param y is the y coordinate of bomb.
     * @param type is the type of the bombing. 
     * Expecting -1, 1 or 2.
     * 
     */
    public void markComputerHit(int x, int y, int type) {
                
        Dimension d = new Dimension(this.SQUARE, this.SQUARE);

        this.constraints.fill = GridBagConstraints.NONE;
        this.constraints.weightx = 0;

        JLabel square = new JLabel("");
        square.setOpaque(true);
        square.setSize(d);
        Color color;
        if(type == -1) {
            color = this.COLOR_MISS;
        } else {
            color = this.COLOR_HIT;
        }
        square.setBackground(color);

        square.setMinimumSize(d);
        square.setPreferredSize(d);
        square.setBorder(BorderFactory.createLineBorder(this.COLOR_BORDER));
        this.constraints.gridx = (x + 1);
        this.constraints.gridy = (y + 1);

        this.humanBoard.add(square, this.constraints, 0); 

        this.constraints.gridx = 1;
        this.constraints.gridy = 1;



        System.out.println("x: " + x + "y: " + y);
        this.humanBoard.setComponentZOrder(square, 0);
        this.humanBoard.revalidate();
        this.humanBoard.repaint();

    }
    
    /**
     * Shows a prompt for player's name for highscore list.
     * @return name as a String of user input.
     */
    public String getPlayerNamePrompt() { 
        String name = JOptionPane.showInputDialog(this.MAIN_FRAME, "Congratulations! You've made it to top list.\nWhat's your name?");
        System.out.printf("The user's name is '%s'.\n", name);
        return name;
    }
    
    /**
     * Hides continue button from the view.
     */
    public void hideContinue() {
        this.continueButton.setVisible(false);
    }
    
    /**
     * shows the continue button in the view.
     */
    public void showContinue() {
        this.continueButton.setVisible(true);
    }
    
    /**
     * @return game UI buttons as an array.
     */
    public JButton[] getButtons() {
       return this.buttons;
     
    }
    
    /**
     * returns the continue button.
     * @return continue button as a JButton.
     */
    public JButton getContinue() {
        return this.continueButton;
    }
    
    /**
     * Presents visually humans' bomb result.
     * @param x is the x coordinate of the bomb.
     * @param y is the y coordinate of the bomb.
     * @param board is the board of hit.
     * @param square is the square where bomb hits.
     */
    public void markHitOrMiss(int x, int y, Board board, Component square) {

       String character = board.getBoard()[x][y];
       System.out.println("bombin Char: " + character);
       //square.setBackground(resolveColor(character));
      
       JLabel s = new JLabel();
       s = (JLabel) square;
       ImageIcon icon = null;
       
       if(character.equals("o")) {
           icon = createImageIcon("miss.png");

       } else {
           icon = createImageIcon("hit.png");
       }
       
       s.setIcon(icon);
       square =  (JLabel) s;
       square = (JLabel)square;
       this.computerBoard.revalidate();
       this.computerBoard.repaint();
    }
    /**
     * @return this square size in pixels.
     * 
     */
    public int getSquareSize() {
        return this.SQUARE;
    }
    
    /**
     * clears the ACTION_VIEW of both boards.
     */
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
    
    /**
     * Solves a color to return by given character.
     * @param character is string character to get the color by.
     * @return color by character.
     */
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
    
    /**
     * Draws a humans' board.
     * @param board to draw. 
     */
    public void drawHumanBoard(Board board) {
        Dimension d = new Dimension(this.SQUARE, this.SQUARE);
        
        /*JPanel humanBoardBase = new JPanel();
        humanBoardBase.setLayout(new CardLayout());
        humanBoardBase.setSize(this.ACTION_VIEW_SIZE);*/
        
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
                square.setBackground(COLOR_BASE);
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
        
        /*for(int i = 0; i < board.getShips().size(); i++) {
            
             JLabel shipGfx;
             
             if(i == 0) {
                ImageIcon icon = createImageIcon("gfx/square8.png");

                shipGfx  = new JLabel(icon);
             } else {
                 shipGfx = new JLabel("");
             }
             
             Dimension shipSize = new Dimension(board.getShips().get(i).getColumns(), board.getShips().get(i).getRows());
             shipGfx.setOpaque(true);
             shipGfx.setSize(shipSize);
             
             shipGfx.setMinimumSize(shipSize);
             shipGfx.setPreferredSize(shipSize);
             
             shipGfx.setBackground(COLOR_HIT);
             c.gridx = board.getShips().get(i).getX();
             c.gridy = board.getShips().get(i).getY();
             
             System.out.println("te: " + shipSize + " x: " + c.gridx + " y: " + c.gridy );
             
             _humanBoard.add(shipGfx, c);
        }
        
        c.gridx = 1;
        c.gridy = 1;*/
        

        
        this.ACTION_VIEW.add(_humanBoard, 0);
        
        _humanBoard.revalidate();
        _humanBoard.repaint();
        this.humanBoard = _humanBoard;
        
        
       
    }
    
    /**
     * Creates an ImageIcon object
     * @param path where the bitmap is supposed to be,
     * @return new ImageIcon if the image exists at path.
     * Otherwise null.
     * 
     */
    
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Draws a computers' board.
     * @param board to draw.
     */
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
    
    /**
     * Handles the visibility of the boards.
     * 
     */
    public void swapBoardOrder() {
        //this.ACTION_VIEW.remove
        CardLayout cl = (CardLayout)(this.ACTION_VIEW.getLayout());
        cl.next(this.ACTION_VIEW);
        System.out.println("c: " + this.ACTION_VIEW.getComponentCount());
        /*JPanel temp = (JPanel) this.ACTION_VIEW.getComponent(0);
        JPanel temp2 = (JPanel) this.ACTION_VIEW.getComponent(1);*/
        
    }
    
    /**
     * Returns a visual presentation of humans' board.
     * @return humanBoard as a JPanel.
     */
    public JPanel humanBoard() {
        return this.humanBoard;
    }
    
    /**
     * Returns a visual presentation of computers' board.
     * @return computerBoard as a JPanel.
     */
    public JPanel getComputerBoard() {
        return this.computerBoard;
    }
    


    
}
