package assignment2018;
import assignment2018.codeprovided.Pieces;
import assignment2018.codeprovided.Piece ;
import assignment2018.codeprovided.* ;
import assignment2018.Move ;
/**
 * Board.java
 *Copyright (c) Eeshan Jaiswal 2018
 *Class to set up the Board  and the pieces on it
 *
 * @author  Eeshan Jaiswal (esjaiswal1@sheffield.ac.uk) 16/04/2018
 *
 * */
public class Board {

    private  Piece[][] board ;
    private int row ;
    private int column  ;
    private Pieces whitePieces  ;
    private Pieces blackPieces ;

    public Board(){

        this.row = 8 ;
        this.column = 8 ;

        this.board = new Piece[this.row][this.column];

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    /**
     * @return  Pieces  the black Pieces on the board
     * */

    public Pieces getBlackPieces() {
        return blackPieces;
    }


    /**
     * Assigns a list of pieces as black Pieces
     *@param blackPieces the Pieces object to be set as black pieces
     *
     * */

    public void setBlackPieces(Pieces blackPieces) {
        this.blackPieces = blackPieces;
    }


    /**
     * @return  Pieces  the White Pieces on the board
     * */

    public Pieces getWhitePieces(){
        return this.whitePieces ;
    }

    /**
     * returns the current Board object
     * @return  2D array of Piece objects which the board is constituted of
     * */

    public Piece[][] getBoard(){
        return this.board ;

    }


    /**
     *Sets up the Black and White Pieces of the board in position
     * */
    public void initialize(){

        this.whitePieces = new Pieces(this, PieceCode.WHITE) ;
        this.blackPieces = new Pieces(this,PieceCode.BLACK) ;

    }

    /*
    * logic of the method
    * 1. take the initial coordinates and get the max num pieces from the list of white and black pieces
    * 2. iterate over both the lists to find out which piece lies on the initial position (deal with the condition when there is no piece)
    * 3. if piece found, check the character, after character found cast the piece into the particular character
    * 4. After casting, get the ArrayList of available moves for that character ... After that, check if the final coordinates lie in the
    * availableMoves() arrayList
    * 5. If there then move , if occupied by opponent find the opponent at the final coordinates ;
    * get its character and remove it form the arrayList of the availablePieces(
    * */


    /**
     * checks whether a particular position is in the board or not
     *
     *@return    true if the position is inside the board
     * */
    public boolean outOfRange(int i , int j){
        boolean a , b ;
        a = (i <=7 && i >=0) ;
        b = (j <=7 && j>=0 ) ;

        boolean c = !(a==b)  ;
        return c ;

    }

    /**
     * Checks whether a particular coordinate of the board is occupied or not
     *
     *@param    i   the x Coordinate of the point which has to be checked
     *@param    j   the Y coordinate of the point which has to be checked
     *@return       true if the coordinate is a occupied by a piece
     * */

    public boolean occupied(int i , int j){

        if(!outOfRange(i,j)){

            // checking if any white piece has occupied a particular piece
            int numOfWhitePieces = whitePieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfWhitePieces ; iterator++){

                Piece tempPiece = whitePieces.getPiece(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                  return true  ;
                }
            }

            // checking if any black piece has occupied the position
            int numOfBlackPieces = blackPieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfBlackPieces ; iterator++){

                Piece tempPiece = blackPieces.getPiece(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                    return true  ;
                }
            }

            return false ;
        }
        else{
            return false ;
        }
    }



    /**
     * sets the position of the piece at a particular coordinate on the board
     * @param i the new  X coordinate of the piece to be set
     * @param j the new  Y coordinate of the piece to be set
     *
     * */

    public void setPosition(int i ,int j , Piece p){
        if(!outOfRange(i,j)) {
            this.board[i][j] = p;
        }
        else{
            System.out.println("The coordinates are out of bounds") ;
        }
    }



    /**
     * gives a piece at a particular location and returns null if there is no piece
     *
     *@param    i   the X Coordinate of the piece's current position
     *@param    j   the Y Coordinate of the piece's current position
     *@return      the Piece object at a particular location
     * */


    public Piece getPiece(int i , int j){
        Piece tempPiece ;

        if(occupied(i,j)){

            // checking if any white piece has occupied a particular piece
            int numOfWhitePieces = whitePieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfWhitePieces ; iterator++){

                tempPiece = whitePieces.getPiece(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                    return tempPiece ;
                }
            }

            // checking if any black piece has occupied the position
            int numOfBlackPieces = blackPieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfBlackPieces ; iterator++){

                tempPiece = blackPieces.getPiece(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                    return  tempPiece ;
                }
            }
        }
        else {
            System.out.format("There is no piece at (%d , %d )",i,j) ;
        }
        return null ;
    }




    /**
     * Deletes a piece at a particular position.. returns a custom message if not possible
     *
     * @Param   p   the piece to be deleted
     * @param   i   the X coordinate of the piece on the board
     * param    j   the Y coordinate of the piece on the board
     * */


    public void removePiece(Piece p , int i , int j){

        // first check if the coordinates at which the piece has to be removed is occupied or not ..
        if( occupied(i,j)){
             if( p.getX() == i && p.getY() == j ){
                 char color = p.getColourChar() ;
                 if (color=='w'){
                     whitePieces.delete(p) ;
                 }
                 else if(color=='b'){
                     blackPieces.delete(p)  ;
                 }
             }
        }

        // if not occupied return an error message ..
        else{

            System.out.format("There is no piece at position ( %d , %d )" , i, j ) ;
        }
    }





}

