
/**
 * A player of Battleship.
 *
 * @author Junchen Wang
 * @version 10 May 2019
 */
public class Player
{
    private Board shipsBoard;
    private Board attacksBoard;
    
    /**
     * Constructor method. Creates a Board object for the ships and a Board object for the player's attacks on the opponent.
     */
    public Player(){
        shipsBoard = new Board();
        attacksBoard = new Board();
    }
    
    /**
     * Prints the Board shipsBoard.
     */
    public void printShipsBoard(){
        shipsBoard.printBoard();
    }
    
    /**
     * Prints the Board attacksBoard.
     */
    public void printAttacksBoard(){
        attacksBoard.printBoard();
    }
    
    /**
     * Places a ship in shipsBoard if the location is valid.
     * 
     * @param coor Starting coordinate.
     * @param dir Which direction to place the ship (v=vertical/h=horizontal).
     * @param length Length of the ship.
     * 
     * @return Boolean which says if the location requested is available or not.
     */
    public boolean placeShip(String coor, char dir, int length){
        return shipsBoard.placeShip(coor,dir,length);
    }
    
    /**
     * Checks if a coordinate is a valid location to attack on shipsBoard.
     * 
     * @param coor Coordinate to check if it is a valid location to attack.
     * 
     * @return If the coordinate can be attacked.
     */
    public boolean attackLocationCheck(String coor){
        return shipsBoard.attackLocationCheck(coor);
    }
    
    /**
     * Attacks a location on shipsBoard and returns if a ship was hit or not.
     * 
     * @param coor Coordinate to attack.
     * 
     * @return If the coordinate hit was a ship.
     */
    public boolean attackLocation(String coor){
        return shipsBoard.attackLocation(coor);
    }
    
    /**
     * Places an attack on attacksBoard.
     * 
     * @param coor Coordinate to place attack.
     */
    public void placeAttackOnAttacksBoard(String coor){
        attacksBoard.attackLocation(coor);
    }
    
    /**
     * Checks if all of the player's ships on shipsBoard have been attacked.
     * 
     * @return If all the player's ships have been attacked.
     */
    public boolean allPlayersShipsAttacked(){
        return shipsBoard.allAttacked();
    }
}
