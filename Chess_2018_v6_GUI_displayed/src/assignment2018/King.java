package assignment2018;

import assignment2018.Board;
import assignment2018.codeprovided.Piece;
import assignment2018.codeprovided.PieceCode;

import java.util.*;

/**
 *
 *King.java  creates the King object and its moves
 * @author Eeshan Jaiswal   16/04/2018
 *
 * */

public class King extends Piece {

    /**
     *@param   ix    the x coordinate of the piece on the board
     *@param   iy    the y coordinate of the piece on the board
     *@param   c     the color of the Knight
     *@param   b     the Board on which the moves have to be made for this piece
     * */
    public King(int ix, int iy, int c, Board b) {
        super(PieceCode.KING, ix, iy, c, b);
    }


    /**
     * method implements abstract availableMoves method in Piece class
     *
     * @return  ArrayList   the list of all available moves for King piece of a particular player
     *
     * */

    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whiteKing();
        else
            return blackKing();
    }

    /**
     *method to return list of legal moves for a white King piece
     * @return ArrayList list of all moves of white King
     * */

    private ArrayList<Move> whiteKing(){
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        // first legal move is to go from x,y to x,y+1
        if (validNormalMove(x, y + 1)) {
            theMove = new Move(this, x, y, x, y + 1, false);
            if(takeOverCheck(x, y+1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }


        // legal move is to go from x,y to x,y-1 if x,y-1 is unoccupied (bottom)
        if (validNormalMove(x, y - 1)) {
            theMove = new Move(this, x, y, x, y - 1, false);
            if(takeOverCheck(x, y-1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        //left
        // legal move to go left from x,y to x-1, y if x-1,y is unoccupied (left)

        if (validNormalMove(x-1, y)) {
            theMove = new Move(this, x, y, x-1, y, false);
            if(takeOverCheck(x-1, y)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        //right
        // legal move to go  right from x,y to x+1, y if x+1,y is unoccupied (right)

        if (validNormalMove(x+1, y)) {
            theMove = new Move(this, x, y, x+1, y, false);

            if(takeOverCheck(x+1, y)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }



        // legal move to diagonal top right for white king
        if (validNormalMove(x+1, y + 1)) {
            theMove = new Move(this, x, y, x+1, y + 1, false);
            if(takeOverCheck(x+1, y+1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // legal move to diagonal bottom right

        if (validNormalMove(x+1, y -1)) {
            theMove = new Move(this, x, y, x+1, y - 1, false);
            if(takeOverCheck(x+1, y-1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // legal move to diagonal bottom left
        if (validNormalMove(x-1, y -1)) {
            theMove = new Move(this, x, y, x-1, y - 1, false);
            if(takeOverCheck(x-1, y-1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // legal move to diagonal top left
        if (validNormalMove(x-1, y+1)) {
            theMove = new Move(this, x, y, x-1, y + 1, false);
            if(takeOverCheck(x-1, y+1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }



        if (whiteMoves.isEmpty())
            return null;
        return whiteMoves;
    }


    /**
     *method to return list of legal moves for a black King piece
     * @return ArrayList list of all moves of black King
     *
     * */
    private ArrayList<Move> blackKing() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;


        // the top and bottom are opposites coordinates for the  black and white kings  and so are left and right
        // all Unoccupied moves

        // first legal move is to go from x,y to x,y-1 if x,y-1 is unoccupied (top)
        if (validNormalMove(x, y - 1)) {
            theMove = new Move(this, x, y, x, y - 1, false);
            if(takeOverCheck(x, y-1)){
                theMove.setOccupied(true);
            }

            blackMoves.add(theMove);
        }


        // legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied (bottom)
        if (validNormalMove(x, y + 1)) {
            theMove = new Move(this, x, y, x, y + 1, false);
            if(takeOverCheck(x, y+1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        //left
        // legal move to go left from x,y to x+1, y if x+1,y is unoccupied (left)

        if (validNormalMove(x+1, y)) {
            theMove = new Move(this, x, y, x+1, y, false);
            if(takeOverCheck(x+1, y)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);

        }

        //right
        // legal move to go  right from x,y to x-1, y if x-1,y is unoccupied (right)

        if (validNormalMove(x-1, y)) {
            theMove = new Move(this, x, y, x-1, y, false);

            if(takeOverCheck(x-1, y)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }


        // legal move to diagonal top right for white king
        if (validNormalMove(x-1, y - 1)) {
            theMove = new Move(this, x, y, x-1, y - 1, false);
            if(takeOverCheck(x-1, y-1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // legal move to diagonal bottom  left

        if (validNormalMove(x+1, y +1)) {
            theMove = new Move(this, x, y, x+1, y + 1, false);

            if(takeOverCheck(x+1, y+1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // legal move to diagonal bottom right
        if (validNormalMove(x-1, y +1)) {
            theMove = new Move(this, x, y, x-1, y + 1, false);
            if(takeOverCheck(x-1, y+1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // legal move to diagonal top left
        if (validNormalMove(x+1, y-1)) {
            theMove = new Move(this, x, y, x+1, y - 1, false);
            if(takeOverCheck(x+1, y-1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }





        if (blackMoves.isEmpty())
            return null;
        return blackMoves;
    }



    // boolean to check validity when moving into unoccupied spaces
    // if the space is occupied by a piece of the same color, return false, becuase the king wont be able to move
    private boolean validNormalMove(int newX, int newY){

        // checks if the new position is out of range or not and if it is then it is occupied or not
        if (!getBoard().outOfRange(newX, newY) && !getBoard().occupied(newX, newY))
            return true;
        // false if in range, occupied , but piece of the same color
        else if(!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY) && (getBoard().getPiece(newX, newY).getColour() == this.getColour()))
            return false ;

        // true if in range, occupied and in the piece of the same color
        // note that in general the Move object will be set to false but after the if statement the occupied will be set to true
        else if(!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()))
            return true ;

        else
            return false;
        }


    boolean takeOverCheck(int newX, int newY){
        if (!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()))
            return true;
        else
            return false;
    }


}
