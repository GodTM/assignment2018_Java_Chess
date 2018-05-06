package assignment2018;
import assignment2018.codeprovided.Pieces;
import assignment2018.codeprovided.Piece ;
import assignment2018.codeprovided.* ;
import assignment2018.Move ;

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

    public Pieces getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(Pieces blackPieces) {
        this.blackPieces = blackPieces;
    }

    public Pieces getWhitePieces(){
        return this.whitePieces ;
    }

    public Piece[][] getBoard(){
        return this.board ;

    }

    // method for initializing the Board
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
     *@return   : true if the position is inside the board
     * */
    public boolean outOfRange(int i , int j){
        boolean a , b ;
        a = (i <=7 && i >=0) ;
        b = (j <=7 && j>=0 ) ;

        boolean c = !(a==b)  ;
        return c ;

    }


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


    public void setPosition(int i ,int j , Piece p){

      this.board[i][j] = p ;

    }


    /**
     *
     *
     * @return  the Piece object at a particular location
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

