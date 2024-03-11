
/**
 * A board of Battleship.
 *
 * @author Junchen Wang
 * @version 15 May 2019
 */

import java.util.ArrayList;

public class Board
{
    private char[][] board;
    private ArrayList<Integer> xShips;
    private ArrayList<Integer> yShips;
    
    /**
     * Constructor for Board. Creates a 10 by 10 2-dimensional char array, each index initialized with ~.
     */
    public Board(){
        board = new char[10][10];
        for(int c=0;c<10;c++){
            for(int r=0;r<10;r++)
                board[c][r]='~';
        }
        xShips = new ArrayList<Integer>();
        yShips = new ArrayList<Integer>();
    }
    
    /**
     * Prints the Board.
     */
    public void printBoard(){
        System.out.println("   A B C D E F G H I J");
        for(int r=0;r<10;r++){
            if(r!=9) System.out.print((r+1)+"  ");
            if(r==9) System.out.print((r+1)+" ");
            for(int c=0;c<10;c++){
                System.out.print(board[c][r] + " ");
            }
            System.out.println();
        }
        
    }
    
    /**
     * Tests out if a ship can be put in the location requested and it that is possible, place a ship there. Ships are placed from the
     * starting coordinate right to left or up to down.
     * 
     * @param coor Starting coordinate.
     * @param dir Which direction to place the ship (v=vertical/h=horizontal).
     * @param length Length of the ship.
     * 
     * @return validLocation Boolean which says if the location requested is available or not.
     */
    public boolean placeShip(String coor, char dir, int length){
        boolean validLocation=true;
        int coorX=0, coorY=0;
        dir=Character.toLowerCase(dir);
        
        if(dir!='v' && dir!='h') validLocation=false;
        
        if(coor.length()==0 || coor.length()==1) validLocation=false;
        
        if(validLocation==true){
            switch(coor.substring(0,1).toLowerCase()){
                case "a": coorX=0; break;
                case "b": coorX=1; break;
                case "c": coorX=2; break;
                case "d": coorX=3; break;
                case "e": coorX=4; break;
                case "f": coorX=5; break;
                case "g": coorX=6; break;
                case "h": coorX=7; break;
                case "i": coorX=8; break;
                case "j": coorX=9; break;
                default: validLocation=false;
            }
            
            for(int c=1;c<coor.length();c++){
                if(!Character.isDigit(coor.charAt(c))) validLocation=false;
            }
        }
        
        if(validLocation==true){
            coorY=Integer.parseInt(coor.substring(1,coor.length()))-1;
            if(coorY>9 || coorY<0) validLocation=false;
        }
        
        if(validLocation==true){
            if(dir=='h'){
                for(int i=coorX;i<coorX+length;i++){
                    if(i<=9){
                        if(board[i][coorY]=='X') validLocation=false; 
                    }
                    else if(i>9) validLocation=false;
                }
                
                if(validLocation==true){
                    for(int i=coorX;i<coorX+length;i++){
                        board[i][coorY]='X';
                        xShips.add(i);
                        yShips.add(coorY);
                    }
                }
            }
            
            else if(dir=='v'){
                for(int i=coorY;i<coorY+length;i++){
                    if(i<=9){
                        if(board[coorX][i]=='X') validLocation=false;
                    }
                    else if(i>9) validLocation=false;
                }
                
                if(validLocation==true){
                    for(int i=coorY;i<coorY+length;i++){
                        board[coorX][i]='X';
                        xShips.add(coorX);
                        yShips.add(i);
                    }
                }
            }
    }
        return validLocation;
    }
    
    /**
     * Checks if the coordinate requested to be attacked has already been attacked or not.
     * 
     * @param coor Coordinate requested to be attacked.
     * 
     * @return validLocation Returns if coordinate has been attacked or not (true=not attacked, false=already attacked).
     */
    public boolean attackLocationCheck(String coor){
        boolean validLocation=true;
        int coorX=0, coorY=0;
        
        if(coor.length()==0 || coor.length()==1) validLocation=false;
        
        if(validLocation==true){
            switch(coor.substring(0,1).toLowerCase()){
                case "a": coorX=0; break;
                case "b": coorX=1; break;
                case "c": coorX=2; break;
                case "d": coorX=3; break;
                case "e": coorX=4; break;
                case "f": coorX=5; break;
                case "g": coorX=6; break;
                case "h": coorX=7; break;
                case "i": coorX=8; break;
                case "j": coorX=9; break;
                default: validLocation=false;
            }
            
            for(int c=1;c<coor.length();c++){
                if(!Character.isDigit(coor.charAt(c))) validLocation=false;
            }
        }
        
        if(validLocation==true){
            coorY=Integer.parseInt(coor.substring(1,coor.length()))-1;
            if(coorY>9 || coorY<0) validLocation=false;
        }
        
        if(validLocation==true)
            if(board[coorX][coorY]=='*') validLocation=false;
        
        return validLocation;
    }
    
    /**
     * Attacks coordinate and returns if coordinate was a ship.
     * 
     * @param coor Coordinate to attack.
     * 
     * @return shipLocation If coordinate was a ship or not.
     */
    public boolean attackLocation(String coor){
        boolean shipLocation=false;
        int coorX=0, coorY=0;
        switch(coor.substring(0,1).toLowerCase()){
            case "a": coorX=0; break;
            case "b": coorX=1; break;
            case "c": coorX=2; break;
            case "d": coorX=3; break;
            case "e": coorX=4; break;
            case "f": coorX=5; break;
            case "g": coorX=6; break;
            case "h": coorX=7; break;
            case "i": coorX=8; break;
            case "j": coorX=9; break;
        }
        
        coorY=Integer.parseInt(coor.substring(1,coor.length()))-1;
        
        if(board[coorX][coorY]=='X') shipLocation=true;
        
        board[coorX][coorY]='*';
        
        return shipLocation;
    }
    
    /**
     * Returns if all the ships have been attacked.
     * 
     * @return allAttacked Boolean to say if all ships have been attacked.
     */
    public boolean allAttacked(){
        boolean allAttacked=true;
        for(int i=0;i<xShips.size();i++){
            if(board[xShips.get(i)][yShips.get(i)]=='X') allAttacked=false;
        }
        return allAttacked;
    }
}
