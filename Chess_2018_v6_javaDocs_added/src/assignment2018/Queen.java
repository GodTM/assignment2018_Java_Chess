package assignment2018;

import assignment2018.Board;
import assignment2018.codeprovided.Piece;
import assignment2018.codeprovided.PieceCode;
import java.util.*;

/**
 * Queen.java
 *
 * Class to make make the queen and its moves
 *
 * @author  Eeshan Jaiswal (esjaiswal1@sheffield.ac.uk) 16/04/2018
 *
 * */
public class Queen extends Piece {

    /**
     *@param   ix    the x coordinate of the piece on the board
     *@param   iy    the y coordinate of the piece on the board
     *@param   c     the color of the Knight
     *@param   b     the Board on which the moves have to be made for this piece
     * */
    public Queen(int ix, int iy, int c, Board b) {
        super(PieceCode.QUEEN, ix, iy, c, b);
    }


    /**
     * method implements abstract availableMoves method in Piece class
     *
     * @return  the list of available moves by the queen of either color
     * */
    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whiteQueen();
        else
            return blackQueen();
    }

    /**
     * method to return list of legal moves for a white pawn
     *
     * @return  ArrayList   the list of all moves made by the white queen
     * */
    private ArrayList<Move> whiteQueen() {

        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();



        //create a new vector to store legal whiteMoves
        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;


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
                    whiteMoves.add(theMove);
                }

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




    /**
     * method to return list of legal moves for a black queen
     *
     * @return  ArrayList   the list of all moves made by the black queen
     * */
    private ArrayList<Move> blackQueen() {

        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();


        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;
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
                if(theMove.straightLineCheckDiagonal()){ // if the move can  be made ,check for any other pieces on the way
                    if(takeOverCheck(x-change,y -change)){
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }

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




            if (validNormalMove(x,y+change)){
                theMove = new Move(this, x, y, x, y + change, false);
                if(theMove.straightLineCheckVertical()) {
                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x,y - change)){
                theMove = new Move(this, x, y, x, y - change, false);
                if(theMove.straightLineCheckVertical()) {
                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x+change,y)){
                theMove = new Move(this, x, y, x+change, y, false);
                if(theMove.straightLineCheckHorizontal()) {
                    if (takeOverCheck(theMove.getFinalX(), theMove.getFinalY())) {
                        theMove.setOccupied(true);
                    }
                    blackMoves.add(theMove);
                }
            }

            if (validNormalMove(x - change,y)){
                theMove = new Move(this, x, y, x - change, y, false);
                if(theMove.straightLineCheckHorizontal()) {
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
