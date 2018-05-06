package assignment2018 ;
import assignment2018.codeprovided.*;
import java.util.Scanner ;
import java.util.ArrayList  ;

/**
 *HumanPlayer.java
 *Class for creating a Human Player object
 *
 *@author Eeshan Jaiswal    2/05/2018
 * */
public class HumanPlayer extends Player{

    /**
     * Constructor for the random Player
     * @param n  the name of the random Player
     * @param p     the set of pieces of the random player
     * @param b     the board on which the pieces will be set
     * @param o    the opponent player of the Random Player
     *
     * */
    public HumanPlayer(String n, Pieces p, Board b, Player o){
        super(n,p,b,o) ;
        }


    /**
     *This method takes in the coordinates TO and FROM on the chessboard and moves the piece from the initial to the final position
     *
     * @return boolean  true, when the move is made
     * */

    public boolean makeMove(){
        // the initial and the final coordinates of the move
        boolean moveMade = false ;

            String fromCoords;
            String toCoords;

            boolean finalCoordsCheck = false;
            boolean initialCoordsCheck = false;
            int xInitial = -1;
            int yInitial = -1;
            int xFinal =   -1;
            int yFinal =   -1;

            System.out.println("\n Enter coordinates") ;
            Scanner input = new Scanner(System.in);

            while (!initialCoordsCheck) {
                System.out.println("From : ");
                fromCoords = input.nextLine().trim().toUpperCase();
                if(TextDisplay.coordinatesCheck(fromCoords)){

                    // trying to convert the first letter into a char and then an int
                    String convert = fromCoords.substring(0) ;

                        xInitial = fromCoords.charAt(0) - 65 ;
                        yInitial = Math.abs(Integer.parseInt(fromCoords.substring(1)) -8);
                        initialCoordsCheck = true ;
                        System.out.println("right initial coords") ;
                }
                else{
                    System.out.println("the initial coordinates are wrong (the length is wrong)")  ;
                }
            }

            while (!finalCoordsCheck) {
                System.out.println("To : ");
                toCoords = input.nextLine().trim().toUpperCase();

                if(TextDisplay.coordinatesCheck(toCoords)){
                        xFinal = toCoords.charAt(0) - 65 ;
                        yFinal = Math.abs(Integer.parseInt(toCoords.substring(1))-8 ) ;
                        finalCoordsCheck = true ;
                        System.out.println("right final coords") ;
                } else {
                        System.out.println("The final coordinates are wrong ") ;
                }
            }

            // check the initial coordinates and the final coordinates ..

            if(initialCoordsCheck && finalCoordsCheck && !((xFinal==xInitial && yInitial==yFinal))) {

                Pieces myPieces = this.getPieces() ;
                if(this.getBoard().occupied(xInitial,yInitial) ) {
                    Piece pieceAtInitialPos = null ;

                    for(int myPiecesIterator = 0 ; myPiecesIterator< myPieces.getNumPieces();myPiecesIterator++){
                        if(myPieces.getPiece(myPiecesIterator).getX()==xInitial &&myPieces.getPiece(myPiecesIterator).getY()==yInitial){
                            pieceAtInitialPos = myPieces.getPiece(myPiecesIterator) ;


                        }
                    }
                    if (pieceAtInitialPos==null){
                        makeMove() ;
                    }
                    else if(myPieces!=null){
                        ArrayList<Move> all_moves =  pieceAtInitialPos.availableMoves() ;

                        if(all_moves==null){
                            System.out.println("All moves is null... may be errornous or INVALID MOVE ") ;
                            return moveMade ; // if no move available return false

                        }
                        else if(all_moves!=null) {

                            Move tempMoveFalse = new Move(pieceAtInitialPos, pieceAtInitialPos.getX(), pieceAtInitialPos.getY(), xFinal, yFinal, false);
                            Move tempMoveTrue = new Move(pieceAtInitialPos, pieceAtInitialPos.getX(), pieceAtInitialPos.getY(), xFinal, yFinal, true);
                            System.out.println(" moves are available") ;

                            for (Move m : all_moves) {
                                if(m.sameMove(tempMoveFalse)){

                                    pieceAtInitialPos.setPosition(xFinal,yFinal);
                                    moveMade = true ;
                                    System.out.println("making a blank move") ;
                                    break ;
                                }

                                // ensuring that the place to move to is occupied
                                else if(m.sameMove(tempMoveTrue) && this.getBoard().occupied(xFinal,yFinal)){

                                    // iterating over opponent's pieces  and removing the one with coordinates xFinal,yFinal ;
                                    if(this.getOpponent()==null){
                                        System.out.println("Set the opponent") ;
                                    }

                                    for(int toBeRemovedIterator=0; toBeRemovedIterator< this.getOpponent().getPieces().getNumPieces() ; toBeRemovedIterator++){

                                            if(this.getOpponent().getPieces().getPiece(toBeRemovedIterator).getX() == xFinal &&this.getOpponent().getPieces().getPiece(toBeRemovedIterator).getY()== yFinal){

                                                // deleting the piece
                                                Piece toBeRemoved = this.getOpponent().getPieces().getPiece(toBeRemovedIterator) ;
                                                this.getOpponent().getPieces().delete(toBeRemoved);
                                                pieceAtInitialPos.setPosition(xFinal,yFinal);
                                                moveMade = true ;
                                                System.out.println("Making a takeOver Move") ;
                                                break ;
                                            }
                                    }
                                }
                            }
                        }
                    }

                    //  final check
                    if(this.getBoard().occupied(xFinal,yFinal) && this.getBoard().getPiece(xFinal,yFinal).equals(pieceAtInitialPos)){
                        moveMade = true;
                    }

                }
                else{
                    System.out.println("No piece is there at the initial position") ;
                    return moveMade ;
                }

            }

        return moveMade ;
    }



}
