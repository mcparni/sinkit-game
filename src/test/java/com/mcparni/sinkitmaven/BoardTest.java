package com.mcparni.sinkitmaven;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class BoardTest {
    
    static Board b;
    
    @Before
    public void setUp() {
       b = new Board();
    }
    
    @Test
    public void initialShipsListinConstructor() {
        Board board = new Board();
        int len = board.getShipCount();
        assertEquals(len, 0);
        System.out.println("* initialShipsListinConstructor()");
        
    }
    
     @Test 
    public void initialBoardArrayinConstructor() {
        String[][] array = b.getBoard();
        int len = array.length;
        assertEquals(len, 13);
        System.out.println("* initialBoardArrayinConstructor()");
    }
    
    @Test
    public void allShipsAddedToBoard() {
        b.addAllShipsAtRandom();
        int len = b.getShipCount();
        assertEquals(len, 11);
        System.out.println("* allShipsAddedToBoard()");
    }
    
    @Test
    public void initialRowsinConstructor() {
        System.out.println("* initialRowsinConstructor()");
        assertEquals(b.getRows(), 11);
        
    }
    
    @Test
    public void initialColsinConstructor() {
        assertEquals(b.getColumns(), 13);
        System.out.println("* initialColumnsinConstructor()");
        
    }
    @Test
    public void testBoardPrint() {
        b.printBoard();
        int i = 1;
        assertEquals(i, 1);
        System.out.println("* testBoardPrint()");
    }
    
   @Test
   public void testBombResults() {

       b.addShip(1, 1,1, "k");
       b.addShip(2, 0, 3, "a");
       int miss = b.bomb(8, 2);
       int alreadyBombed = b.bomb(8, 2);
       int hit = b.bomb(0, 3);
       int sinked = b.bomb(1, 1);
       
       assertEquals(miss, -1);
       assertEquals(alreadyBombed, 0);
       assertEquals(hit, 1);
       assertEquals(sinked, 2);
       
       System.out.println("* testBombResults()");  
   }
    
    @Test
    public void testShipAddingToBoard() {
        b.addShip(8, 0, 0, "a");
        int len = b.getShipCount();
        assertEquals(len, 1);
        System.out.println("* testShipAddingToBoard()");
    }
   
    @Test
    public void testBoardClass() {
        Board board = new Board(); 
        int boardTest;
        if(board instanceof Board) {
            boardTest = 1;
        } else {
            boardTest = 0;
        }
       
        assertEquals(boardTest, 1);
        System.out.println("* testBoardClass()");        
    }
    
}
