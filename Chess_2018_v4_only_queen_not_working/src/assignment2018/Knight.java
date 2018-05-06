package assignment2018;

import assignment2018.Board;
import assignment2018.codeprovided.*;
import assignment2018.codeprovided.PieceCode;

import java.util.*;

public class Knight  extends Piece{

    public Knight(int ix, int iy, int c, Board b) {
        super(PieceCode.KNIGHT, ix, iy, c, b);
    }



    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whiteKnight();
        else
            return blackKnight();
    }


    // method to return list of legal moves for a white pawn
    private ArrayList<Move> whiteKnight() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();


        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;


        // normal Traversal Moves

        // considering the knight is placed on the board such that it can make all possible directions we move in clockwise direction
        // in -all there are maximum 8 possible moves
        // other directions are taken care of by the validNormalMove (non-traversable)

        // moving in a clockwise direction

        // top right - 1
        if (validNormalMove(x+1, y + 2)) {
            theMove = new Move(this, x, y, x+1, y + 2, false);
            if(takeOverCheck(x+1, y + 2)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // top-right - 2
        if (validNormalMove(x+2, y + 1)) {
            theMove = new Move(this, x, y, x+2, y + 1, false);
            if(takeOverCheck(x+2, y + 1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // bottom-right - 1

        if (validNormalMove(x+2, y - 1)) {
            theMove = new Move(this, x, y, x+2, y - 1, false);
            if(takeOverCheck(x+2, y -1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // bottom-right -2

        if (validNormalMove(x+1, y - 2)) {
            theMove = new Move(this, x, y, x+1, y - 2, false);
            if(takeOverCheck(x+1, y -2)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // bottom -left -1

        if (validNormalMove(x-1, y - 2)) {
            theMove = new Move(this, x, y, x-1, y -2, false);
            if(takeOverCheck(x-1, y - 2)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // bottom-left -2
        if (validNormalMove(x-2, y - 1)) {
            theMove = new Move(this, x, y, x-2, y - 1, false);
            if(takeOverCheck(x-2, y - 1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        // top -left-1

        if (validNormalMove(x-2, y + 1)) {
            theMove = new Move(this, x, y, x-2, y + 1, false);
            if(takeOverCheck(x-2, y + 1)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        //top-left -2

        if (validNormalMove(x-1, y + 2)) {
            theMove = new Move(this, x, y, x-1, y + 2, false);
            if(takeOverCheck(x-1, y + 2)){
                theMove.setOccupied(true);
            }
            whiteMoves.add(theMove);
        }

        if (whiteMoves.isEmpty()) {
            return null;
        }
        return whiteMoves;
    }


    private ArrayList<Move> blackKnight() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // return null if the pawn is at the edge of the board, or if the
        // next move takes it out of range
       /* if ((getBoard().outOfRange(x, y + 1)))
            return null;
        */
        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;


        // normal Traversal Moves

        // considering the knight is placed on the board such that it can make all possible directions we move in clockwise direction
        // in -all there are maximum 8 possible moves
        // other directions are taken care of by the validNormalMove (non-traversable)

        // moving in a clockwise direction

        //
        // bottom-left - 2
        if (validNormalMove(x+1, y + 2)) {
            theMove = new Move(this, x, y, x+1, y + 2, false);
            if(takeOverCheck(x+1, y + 2)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // bottom-left-1
        if (validNormalMove(x+2, y + 1)) {
            theMove = new Move(this, x, y, x+2, y + 1, false);
            if(takeOverCheck(x+2, y + 1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // top-left - 2

        if (validNormalMove(x+2, y - 1)) {
            theMove = new Move(this, x, y, x+2, y - 1, false);
            if(takeOverCheck(x+2, y -1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // top-left-1

        if (validNormalMove(x+1, y - 2)) {
            theMove = new Move(this, x, y, x+1, y - 2, false);
            if(takeOverCheck(x+1, y - 2)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // top -right -2

        if (validNormalMove(x-1, y - 2)) {
            theMove = new Move(this, x, y, x-1, y -2, false);
            if(takeOverCheck(x-1, y - 2)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // top-right-1
        if (validNormalMove(x-2, y - 1)) {
            theMove = new Move(this, x, y, x-2, y - 1, false);
            if(takeOverCheck(x-2, y - 1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        // bottom-right-2

        if (validNormalMove(x-2, y + 1)) {
            theMove = new Move(this, x, y, x-2, y + 1, false);
            if(takeOverCheck(x-2, y + 1)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        //bottom-right -1

        if (validNormalMove(x-1, y + 2)) {
            theMove = new Move(this, x, y, x-1, y + 2, false);
            if(takeOverCheck(x-1, y + 2)){
                theMove.setOccupied(true);
            }
            blackMoves.add(theMove);
        }

        if (blackMoves.isEmpty()) {
            return null;
        }
        return blackMoves;

    }


    /*

    // method for takeover moves by Knight
    private boolean takeOverCheck(int newX, int newY) {
        if (!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()))
            return true;
        else
            return false;
    }


    // boolean to check validity when moving into unoccupied spaces
    // if the space is occupied by a piece of the same color, return false, becuase the knight wont be able to move
    private boolean validNormalMove(int newX, int newY){

        if (!getBoard().outOfRange(newX, newY) && !getBoard().occupied(newX, newY))
            return true;

        else if(!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY) && (getBoard().getPiece(newX, newY).getColour() == this.getColour()))
            return false ;
        else
            return false;

    }
    */

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
