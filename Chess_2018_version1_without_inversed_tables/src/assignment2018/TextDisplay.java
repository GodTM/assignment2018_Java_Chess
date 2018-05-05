package assignment2018;
import assignment2018.codeprovided.Pieces;
import assignment2018.codeprovided.Display;
import assignment2018.codeprovided.Piece ;
import assignment2018.Board ;

// should implement DisplayBoard(Pieces P) method of the interface

public class TextDisplay implements Display {
    private Board  currentBoard;
    private char[][] boardToDisplay ;

    public TextDisplay(Board b){
        this.currentBoard = b ;
        this.boardToDisplay = new char[8][8] ;
    }

    public void displayBoard(Pieces pieces){

    }

    public void displayPieces(){
        System.out.println() ;
        System.out.println();
        this.boardToDisplay = new char[8][8];
        Pieces white = this.currentBoard.getWhitePieces() ;
        Pieces black = this.currentBoard.getBlackPieces() ;

        /*
        * note that the board to be displayed is a Char 2D array ..
        * In the nested loops the positions of Char at the same at (x,y) == the Character of the Piece at the Piece 2D -array
        * */

        //assignning the white pieces ;
        for(int whiteIterator = 0 ; whiteIterator<white.getNumPieces();whiteIterator++){
            Piece tempPiece = white.getPiece(whiteIterator) ;

            for (int row = 0; row < this.boardToDisplay.length; row++) {
                for (int col = 0; col < this.boardToDisplay[row].length; col++) {

                    boardToDisplay[tempPiece.getX()][tempPiece.getY()] = tempPiece.getChar();
                }
            }
        }

        // assigning the black pieces
        for(int blackIterator = 0 ; blackIterator<black.getNumPieces() ; blackIterator++){
          Piece tempPiece = black.getPiece(blackIterator) ;
          for (int row = 0; row < this.boardToDisplay.length; row++) {
                for (int col = 0; col < this.boardToDisplay[row].length; col++) {

                    boardToDisplay[tempPiece.getX()][tempPiece.getY()] = tempPiece.getChar();
                }
          }
        }

        // assigning (.)

        for(int row=0; row<this.boardToDisplay.length ; row++){
            for(int col=0 ; col < this.boardToDisplay[row].length ; col++){

                if(!((int)boardToDisplay[row][col]>=65 && (int)boardToDisplay[col][row] <=122)){

                    boardToDisplay[row][col] = '.' ;
                }

            }
        }

        // now printing ..
        System.out.print(" |ABCDEFGH");
        System.out.println() ;
        System.out.print("-----------"); System.out.println();
        for(int row=0; row <this.boardToDisplay.length ;row++ ){
            System.out.print(8-row+"|");
            for(int col = 0 ; col<this.boardToDisplay[row].length ; col++){
                System.out.print(String.valueOf(this.boardToDisplay[col][row])) ;
            }
            System.out.println();
        }



    }

    public static boolean coordinatesCheck(String s){
       boolean isValidCoord = false ;
       if(s.trim().length()==2){
           String first = s.substring(0).toUpperCase() ;
           String second = s.substring(1).toUpperCase() ;

           boolean firstValid = false ;
           boolean secondValid = false ;
           if (first.charAt(0)>=65 && first.charAt(0)<=72){
               firstValid = true ;
           }
           if(second.charAt(0)>=49 && second.charAt(0)<=56){
               secondValid = true ;
           }
        if(firstValid && secondValid){
               isValidCoord = true ;
        }
       }

       return isValidCoord ;
    }




}
