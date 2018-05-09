package assignment2018 ;
import assignment2018.codeprovided.* ;
import java.util.* ;
import assignment2018.TextDisplay ;
import assignment2018.Board ;

/**
 * RandomPlayer.java
 * Class to make a Random Player
 *
 * @author  Eeshan Jaiswal (esjaiswal1@sheffield.ac.uk) 2/05/2018
 * */

public class RandomPlayer extends Player {


    /**
     * Constructor for the random Player
     * @param name  the name of the random Player
     * @param p     the set of pieces of the random player
     * @param b     the board on which the pieces will be set
     * @param op    the opponent player of the Random Player
     *
     * */
    public RandomPlayer(String name, Pieces p, Board b, Player op) {
        super(name, p, b, op);
    }


    /**
     * this version of makeMove method of the Player class selects a random player
     * and then if moves are available for that Piece, it selects one of the moves. If no moves are available the method is called recursively
     *
     * @return  boolean     true, if the move is made by the random player successfully
     * */

    public boolean makeMove(){

        Pieces myPieces = this.getPieces() ;
        int randomPieceNumber = (int)(Math.floor(Math.random()*16)) ;
        boolean moveMade = false ;
        Piece toBeMoved ;

        if(myPieces!=null){

            toBeMoved = myPieces.getPiece(randomPieceNumber);

            ArrayList<Move> all_moves = toBeMoved.availableMoves() ; // all the available moves
            if(all_moves==null){
                System.out.println("No moves available =for the randomly selected piece.. choosing another") ;
                makeMove() ;// calling again because no moves are available for the current piece
            } else{

                int maxMoves = all_moves.size() ;
                int randomlySelectedMove = (int)(Math.floor(Math.random()* maxMoves)) ;

                Move randomMove = all_moves.get(randomlySelectedMove) ; // randomly Selected move

                // normal Move
                if(!randomMove.occupied()){
                    toBeMoved.setPosition(randomMove.getFinalX(),randomMove.getFinalY());
                    moveMade = true ;
                }

                /*
                * randomly select a move and then if the move involves taking out another piece
                *  then iterate over the opponent pieces to find the piece which has the coordinates same as the final
                * coordinates of the move object, then delete the opponent piece from the arrayList
                * */

                else if(randomMove.occupied()){
                    Piece toBeDeleted ;
                    for(int oppIterator = 0 ; oppIterator<this.getOpponent().getPieces().getNumPieces();oppIterator++){

                        toBeDeleted = this.getOpponent().getPieces().getPiece(oppIterator) ;
                        if(toBeDeleted.getX()==randomMove.getFinalX() && toBeDeleted.getY()==randomMove.getFinalY()){

                            this.getOpponent().deletePiece(toBeDeleted); // delete the piece if the finalX and finalY are the same as coords of the opp's Piece
                            toBeMoved.setPosition(randomMove.getFinalX(),randomMove.getInitialY());
                            moveMade = true ;
                        }
                    }
                }
            }
        }
        return moveMade ;

    }

}


