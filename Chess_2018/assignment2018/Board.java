package assignment2018;
import assignment2018.codeprovided.*;

public class Board {

    private  Piece[][] board ;
    private int row ;
    private int column  ;
    private Pieces whitePieces  ;
    private Pieces blackPieces ;

    public Board(){

        this.row = 8 ;
        this.column = 8 ;

        this.board = new int[this.row][this.column];

    }

    // method for initializing the Board

    public Piece[][] returnBoard(){
        return this.board ;

    }

    public void Board initialize(){

        this.whitePieces = new MyPieces(this.board, PieceCode.WHITE) ;
        this.blackPieces = new MyPieces(this.board,PieceCode.BLACK) ;

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
    public boolean isValidPosition(int i , int j){
        boolean a , b ;
        a = (i <=7 && i >=0) ;
        b = (j <=7 && j>=0 ) ;

        boolean c = (a==b)  ;
        return c ;

    }


    public boolean isOccupied(int i , int j){

        if(isValidPosition(i,j)){

            // checking if any white piece has occupied a particular piece
            int numOfWhitePieces = whitePieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfWhitePieces ; iterator++){

                Piece tempPiece = whitePieces.get(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                  return true  ;
                }
            }

            // checking if any black piece has occupied the position
            int numOfBlackPieces = blackPieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfBlackPieces ; iterator++){

                Piece tempPiece = blackPieces.get(iterator);
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


    // if a
    public void insertPiece(Piece p , int i , int j){

        // first check if the piece to be inserted can be moved at that particular position
        if(p.getColourChar() =='w'){
           for(int iterator = 0 ; iterator< whitePieces.getNumPieces(); iterator++){
               // check if the pieces are identical
               if (p.equals(whitePieces.getPiece(iterator))){
                   // check available moves // if the particular piece can move to a particular position then

                   // defining a temp new move object to compare
                   Move tempMoveFalse = new Move(p,p.getX(),p.getY(),i,j,false) ;
                   Move tempMoveTrue  = new Move(p,p.getX(),p.getY(),i,j,true) ;
                   for(Move m : p.availableMoves()){

                       // if the final position is unoccupied, then make the move
                       if (m.sameMove(tempMoveFalse)){
                           p.setPosition(i,j) ;
                       }
                       // if the final position is occupied
                       // check if the move to be made has to eliminate a piece and check if the final positon is occupied
                       else if(m.sameMove(tempMoveTrue) && isOccupied(i,j)){
                           for(Piece toBeRemoved : blackPieces){

                               if(toBeRemoved.getX()==i && toBeRemoved.getY()==j){
                                   // delete the piece at the final position
                                   blackPieces.delete(toBeRemoved) ;

                                   // then insert the Piece p to the new Position
                                   p.setPosition(i,j) ;

                               }
                           }

                       }
                   }



               }


           }

        }

        else if(p.getColourChar() =='b'){
            for(int iterator = 0 ; iterator< blackPieces.getNumPieces(); iterator++){


                // check if the pieces are identical
                if (p.equals(blackPieces.getPiece(iterator))){
                    // check available moves // if the particular piece can move to a particular position then

                    // defining a temp new move object to compare
                    Move tempMoveFalse = new Move(p,p.getX(),p.getY(),i,j,false) ;
                    Move tempMoveTrue  = new Move(p,p.getX(),p.getY(),i,j,true) ;

                    // if there is p is present in the arrayList
                    for(Move m : p.availableMoves()){

                        // if the final position is unoccupied, then make the move
                        if (m.sameMove(tempMoveFalse)){
                            p.setPosition(i,j) ;
                        }
                        // if the final position is occupied
                        // check if the move to be made has to eliminate a piece and check if the final positon is occupied
                        else if(m.sameMove(tempMoveTrue) && isOccupied(i,j)){
                            for(Piece toBeRemoved : whitePieces){

                                if(toBeRemoved.getX()==i && toBeRemoved.getY()==j){
                                    // delete the piece at the final position
                                    blackPieces.delete(toBeRemoved) ;

                                    // then insert the Piece p to the new Position
                                    p.setPosition(i,j) ;

                                }
                            }

                        }

                    }
                }
                else{

                    System.out.println("There is no piece with the state of Piece provided")
                }
            }
        }
    }


    /**
     *
     *
     * @return  the Piece object at a particular location
     * */
    public Piece returnPiece(int i , int j){
        Piece tempPiece ;

        if(isOccupied(i,j)){


            // checking if any white piece has occupied a particular piece
            int numOfWhitePieces = whitePieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfWhitePieces ; iterator++){

                tempPiece = whitePieces.get(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                    return tempPiece ;
                }
            }

            // checking if any black piece has occupied the position
            int numOfBlackPieces = blackPieces.getNumPieces() ;
            for(int iterator=0 ; iterator<numOfBlackPieces ; iterator++){

                tempPiece = blackPieces.get(iterator);
                if(tempPiece.getX() == i && tempPiece.getY() ==j){
                    return  tempPiece ;
                }
            }

        }
        else {

            System.out.println("There is no piece at (%d , %d )",i,j) ;
        }


    }



    public void removePiece(Piece p , int i , int j){

        // first check if the coordinates at which the piece has to be removed is occupied or not ..
        if( isOccupied(i,j)){
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

            System.out.println("There is no piece at position ( %d , %d )" , i, j ) ;
        }

    }



}

