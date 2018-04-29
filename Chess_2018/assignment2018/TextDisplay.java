package assignment2018;

import assignment2018.codeprovided.Display;

// should implement DisplayBoard(Pieces P) method of the interface

public class TextDisplay implements Display {
    private Board  currentBoard;

    public TextDisplay(Board b){

        this.currentBoard = b ;

    }

    public displayBoard(Pieces pieces){
        Piece tempPiece ;

        for(int iterator = 0 ; iterator<pieces.getNumPieces(); iterator++){

            // returns the piece from the pieces arrayList
            tempPiece = pieces.getPiece(iterator) ;

            // set the piece at the position of X and Y coordinates of the temp piece into a character
            currentBoard[tempPiece.getX()][tempPiece.getY()] = tempPiece.getChar();
        }
    }




}
