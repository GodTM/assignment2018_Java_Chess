package assignment2018;

import assignment2018.codeprovided.PieceCode;
//import assignment2018.codeprovided.

import java.util.*;

public class Rook extends assignment2018.codeprovided.Piece{


    public Rook(int ix, int iy, int c, Board b) {


        super(PieceCode.ROOK, ix, iy, c, b);
    }


    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whiteRook();
        else
            return blackRook();
    }


    private ArrayList<Move> whiteRook() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();



        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        for(int change = 1 ; change<=7; change++) {


            if (validNormalMove(x,y+change)){
                theMove = new Move(this, x, y, x, y + change, false);

                if(theMove.straightLineCheckVertical()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    whiteMoves.add(theMove);
                }
            }

            if (validNormalMove(x,y - change)){
                theMove = new Move(this, x, y, x, y - change, false);

                if(theMove.straightLineCheckVertical()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    whiteMoves.add(theMove);
                }
            }

            if (validNormalMove(x+change,y)){
                theMove = new Move(this, x, y, x+change, y, false);

                if(theMove.straightLineCheckHorizontal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    whiteMoves.add(theMove);
                }
            }

            if (validNormalMove(x - change,y)){
                theMove = new Move(this, x, y, x - change, y, false);

                if(theMove.straightLineCheckHorizontal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY() )) {
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



    private ArrayList<Move> blackRook() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();



        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        for(int change = 1 ; change<=7; change++) {


            if (validNormalMove(x,y+change)){
                theMove = new Move(this, x, y, x, y + change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x,y - change)){
                theMove = new Move(this, x, y, x, y - change, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x+change,y)){
                theMove = new Move(this, x, y, x+change, y, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x - change,y)){
                theMove = new Move(this, x, y, x - change, y, false);

                if(theMove.straightLineCheckDiagonal()) {

                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY() )) {
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
