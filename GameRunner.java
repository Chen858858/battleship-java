/**
 * Runs the Battleship game with a smarter computer player than the original game.
 *
 * @author Junchen Wang
 * @version 22 May 2019
 */

import java.util.Scanner;
import java.util.ArrayList;

public class GameRunner
{
    private static Player computer=new Player();
    private static Player human=new Player();
    private static ArrayList<String> compCoorsToTry=new ArrayList<String>();
    private static String prevShipCoor="";
    private static ArrayList<String> prevShipCoorList=new ArrayList<String>();
    private static String humanShipsAttacked="Ships hit: ";
    private static int humanAttacksCount=0;
    
    public static void main(String[] args){
        
        System.out.println("BATTLESHIP - JUNCHEN WANG\n--------------------------\n");
        
        Scanner in=new Scanner(System.in);
        
        //Computer player places ships.
        System.out.println("Computer placing ships...");
        computerPlaceShip(2);
        computerPlaceShip(3);
        computerPlaceShip(3);
        computerPlaceShip(4);
        computerPlaceShip(5);
        System.out.println("Computer placed ships.\n");

        System.out.println("Board key: ~ = vacant coordinate, X = ship coordinate, * = attack coordinate");
        System.out.println("Example of coordinate: A5 (Column: A-J) (Row: 1-10)\n");
        
        //Human player places ships.
        System.out.println("Place your Destroyer (length 2):");
        humanPlaceShip(2);
        System.out.println("Place your Submarine (length 3):");
        humanPlaceShip(3);
        System.out.println("Place your Carrier (length 5):");
        humanPlaceShip(5);
        System.out.println("Place your Cruiser (length 3):");
        humanPlaceShip(3);
        System.out.println("Place your Battleship (length 4):");
        humanPlaceShip(4);
        
        System.out.println("\nThe attacking begins!\n");
        System.out.println("You will be shown 2 boards: after the computer attacks, your board of ships, after you attack, the board of your attacks on the computer.\n");
        System.out.println("If you want to see ships you have hit, type \"ships\". This only works if you have made 50 attacks or more.");
        for(double i=0.0;i<1200.0;i+=.000001){}
        
        //Attacking begins.
        boolean goOn=true;
        while(goOn==true){
            //Computer attacks.
            String compAttackCoor=computerAttackShip();
            computer.placeAttackOnAttacksBoard(compAttackCoor);
            System.out.println("The computer attacked " + compAttackCoor.toUpperCase() + ".");
            human.printShipsBoard();
            for(double i=0.0;i<700.0;i+=.000001){}
            if(human.allPlayersShipsAttacked()==true) break;
            //Human attacks.
            System.out.print("\nYour turn - ");
            humanAttackShip();
            humanAttacksCount++;
            for(double i=0.0;i<800.0;i+=.000001){}
            if(computer.allPlayersShipsAttacked()==true) break; 
        }
        
        //After all of one player's ships are attacked.
        if(human.allPlayersShipsAttacked()) System.out.println("\nYou lose! Better luck next time.");
        else System.out.println("You are the champion!");
        for(double i=0.0;i<100.0;i+=.000001){}
        System.out.println("\n_____/\\\\\\\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\\\\\\\_ ");       
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println(" ___/\\\\\\//////////____/\\\\\\//////////__ ");       
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("  _\\/\\\\\\_____________\\/\\\\\\_____________ ");      
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("   _\\/\\\\\\____/\\\\\\\\\\\\\\_\\/\\\\\\____/\\\\\\\\\\\\\\_ ");    
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("    _\\/\\\\\\___\\/////\\\\\\_\\/\\\\\\___\\/////\\\\\\_ ");    
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("     _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_ ");   
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("      _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_ ");  
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("       _\\//\\\\\\\\\\\\\\\\\\\\\\\\/__\\//\\\\\\\\\\\\\\\\\\\\\\\\/__ "); 
        for(double i=0.0;i<600.0;i+=.000001){}
        System.out.println("        _\\/////////////____\\/////////////____ ");   
    }
    
    /**
    * Places a ship for the computer player.
    *
    * @param length The length of the ship.
    */
    public static void computerPlaceShip(int length){
        boolean shipPlaced=false;
         
        while(shipPlaced==false){
            int dirInt=(int)(Math.random()*10+1);
            if(dirInt%2==0) shipPlaced=computer.placeShip(genRandCoor(),'v',length);
            else shipPlaced=computer.placeShip(genRandCoor(),'h',length);
        }
        for(int i=0;i<100000;i++){for(int j=0;j<7500;j++){}}
        System.out.println("Ship placed.");
    }
    
    /**
     * Place a ship for the human player.
     * 
     * @param length The length of the ship.
     */
    public static void humanPlaceShip(int length){
        boolean shipPlaced=false;
        Scanner in=new Scanner(System.in);
        
        while(shipPlaced==false){
            System.out.println("Starting coordinate of ship:");
            String coor=in.nextLine();
            System.out.println("Direction of ship (v = vertical, h = horizontal):");
            String inputDir=in.nextLine();
            char dir=' ';
            if(inputDir.length()>0)
                dir=inputDir.charAt(0);
            shipPlaced=human.placeShip(coor, dir, length);
            if(shipPlaced==false) System.out.println("Ship not able to be placed! Try again.");
            else System.out.println("Ship placed.");
        }
        
        human.printShipsBoard();
        
    }
    
    
    /**
     * Computer attacks a valid coordinate on the human player's board.
     *
     * @return attackCoor The coordinate attacked.
     */
    public static String computerAttackShip(){
        String attackCoor="";
        boolean attacked=false;
        boolean attackedShip=false;
        
        if(compCoorsToTry.size()>0){
            while(attacked==false || compCoorsToTry.size()>0){
                int index=(int)(Math.random()*(compCoorsToTry.size()-1)+0);
                attackCoor=compCoorsToTry.get(index);
                attacked=human.attackLocationCheck(attackCoor);
                if(attacked==true){
                    attackedShip=human.attackLocation(attackCoor);
                    compCoorsToTry.clear();
                    if(attackedShip==true){
                        prevShipCoor=attackCoor;
                        prevShipCoorList.add(attackCoor);
                    }
                    else
                        fillCompCoorsToTry(prevShipCoor);
                    break;
                }
                else compCoorsToTry.remove(index);
                if(compCoorsToTry.size()==0 && attacked==false){
                    if(prevShipCoorList.size()>1){
                        String firstCoor=prevShipCoorList.get(0);
                        prevShipCoor=firstCoor;
                        prevShipCoorList.clear();
                        prevShipCoorList.add(firstCoor);
                        fillCompCoorsToTry(firstCoor);
                    }
                    else{
                        prevShipCoorList.clear();
                        break;
                    }
                }
                if(compCoorsToTry.size()==0) break;
            }
            
        }
        if(attacked==false)
            while(attacked==false){
                attackCoor=genRandCoor();
                attacked=human.attackLocationCheck(attackCoor);
                if(attacked==true) attackedShip=human.attackLocation(attackCoor);
            }
        if(attackedShip==true){
            fillCompCoorsToTry(attackCoor);
            prevShipCoor=attackCoor;
            System.out.print("The computer hit one of your ships. ");
            prevShipCoorList.add(attackCoor);
        }
        return attackCoor;
    }
    
    /**
     * Human attacks a coordinate on the computer player's board.
     */
    public static void humanAttackShip(){
        boolean attacked=false;
        Scanner in=new Scanner(System.in);
        
        while(attacked==false){
            System.out.println("Coordinate:");
            String coor=in.nextLine();
            if(coor.equals("ships")){
                if(humanAttacksCount>=50) System.out.println(humanShipsAttacked);
                else System.out.println("Ships hit not available yet. " + (50-humanAttacksCount) + " more attacks needed.");
            }
            else{
                attacked=computer.attackLocationCheck(coor);
                if(attacked==true){
                    System.out.print(coor + " is a valid location. ");
                    boolean isShip=computer.attackLocation(coor);
                    if(isShip){
                        System.out.println("It was a ship.");
                        humanShipsAttacked=humanShipsAttacked+coor+" ";
                    }
                    else System.out.println("It was not a ship.");
                    human.placeAttackOnAttacksBoard(coor);
                    human.printAttacksBoard();
                    System.out.println("\n");
                }
                else System.out.println("Invalid location!");
            }
        }
    }
    
    /**
     * Fills up the compCoorsToTry array with coordinates the computer should test to attack, the coordinates around the center coordinate.
     * 
     * @coor The center coordinate.
     */
    public static void fillCompCoorsToTry(String coor){
        char col=coor.charAt(0);
        int colCharVal=(int)col;
        int row=Integer.parseInt(coor.substring(1,coor.length()));
        String newCoor=col+Integer.toString(row+1);
        compCoorsToTry.add(newCoor);
        newCoor=col+Integer.toString(row-1);
        compCoorsToTry.add(newCoor);
        newCoor=(char)(colCharVal+1)+Integer.toString(row);
        compCoorsToTry.add(newCoor);
        newCoor=(char)(colCharVal-1)+Integer.toString(row);
        compCoorsToTry.add(newCoor);
    }
    
    /**
    * Generates a random coordinate.
    *
    * @return randCoor The random coordinate.
    */
    public static String genRandCoor(){
        String randCoor="";
         
        String col=" ";
        int colInt=(int)(Math.random()*10+1);
        switch(colInt){
            case 1: col="a"; break;
            case 2: col="b"; break;
            case 3: col="c"; break;
            case 4: col="d"; break;
            case 5: col="e"; break;
            case 6: col="f"; break;
            case 7: col="g"; break;
            case 8: col="h"; break;
            case 9: col="i"; break;
            case 10: col="j"; break;
            default: col="a"; break;
        }
     
     
        int row=(int)(Math.random()*10+1);
         
        randCoor=col+row;
         
        return randCoor;
    }
}