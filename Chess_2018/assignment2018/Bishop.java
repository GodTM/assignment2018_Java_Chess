package assignment2018;

import assignment2018.codeprovided.Piece;
import assignment2018.codeprovided.PieceCode;
import java.util.*;

public class Bishop extends Piece{

    public Bishop(int ix, int iy, int c, Board b) {
        super(PieceCode.BISHOP, ix, iy, c, b);
    }

    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whiteBishop();
        else
            return blackBishop();
    }


    // method to return list of legal moves for a white pawn
    private ArrayList<Move> whiteBishop() {

        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // return null if the pawn is at the edge of the board, or if the
        // next move takes it out of range
        /*if ((getBoard().outOfRange(x, y + 1)))
            return null;
        */

        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied

        for(int change = 1 ; change<=7; change++) {

            if (validNormalMove(x+ change,y+change)){
                theMove = new Move(this, x, y, x+ change, y + change, false);
                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x + change, y + change)) {
                        theMove.setOccupied(true);
                    }


                    whiteMoves.add(theMove);
                }

            }

            // generates a move if its can be moved
            if (validNormalMove(x-change,y-change)){


                theMove = new Move(this, x, y, x-change, y -change, false);
                // if the move can  be made ,check for any other pieces on the way
                if(theMove.straightLineCheckDiagonal()){

                    if(takeOverCheck(x-change,y -change)){
                        theMove.setOccupied(true);
                    }

                }
                whiteMoves.add(theMove);
            }


            if (validNormalMove(x-change,y+change)){
                theMove = new Move(this, x, y, x-change, y + change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x - change, y + change)) {
                        theMove.setOccupied(true);
                    }
                    whiteMoves.add(theMove);
                }
            }

            if (validNormalMove(x+change,y-change)){
                theMove = new Move(this, x, y, x+change, y - change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x + change, y - change)) {
                        theMove.setOccupied(true);
                    }
                    whiteMoves.add(theMove);
                }
            }
        }


        if (whiteMoves.isEmpty())
            return null;
        return whiteMoves;
    }


    private ArrayList<Move> blackBishop() {

        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // return null if the pawn is at the edge of the board, or if the
        // next move takes it out of range
        /*if ((getBoard().outOfRange(x, y + 1)))
            return null;
        */

        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied

        for(int change = 1 ; change<=7; change++) {

            if (validNormalMove(x+ change,y+change)){
                theMove = new Move(this, x, y, x+ change, y + change, false);
                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x + change, y + change)) {
                        theMove.setOccupied(true);
                    }


                    blackMoves.add(theMove);
                }

            }

            // generates a move if its can be moved
            if (validNormalMove(x-change,y-change)){


                theMove = new Move(this, x, y, x-change, y -change, false);
                // if the move can  be made ,check for any other pieces on the way
                if(theMove.straightLineCheckDiagonal()){

                    if(takeOverCheck(x-change,y -change)){
                        theMove.setOccupied(true);
                    }

                }
                blackMoves.add(theMove);
            }


            if (validNormalMove(x-change,y+change)){
                theMove = new Move(this, x, y, x-change, y + change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x - change, y + change)) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x+change,y-change)){
                theMove = new Move(this, x, y, x+change, y - change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(x + change, y - change)) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }
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
            // note that in general the Move object will be set to false but after the if statement the isoccupied will be set to true
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
