package assignment2018 ;
import assignment2018.codeprovided.* ;
import java.util.* ;
import assignment2018.TextDisplay ;
import assignment2018.Board ;
//import assignment2018. ;

public class RandomPlayer extends Player {

    private boolean isYourTurn;

    public RandomPlayer(String name, Pieces p, Board b, Player op) {
        super(name, p, b, op);
    }


    public boolean makeMove(){

        Pieces myPieces = this.getPieces() ;
        int randomPieceNumber = (int)(Math.random()*16) ;
        boolean moveMade = false ;
        Piece toBeMoved ;

        if(myPieces!=null){

            toBeMoved = myPieces.getPiece(randomPieceNumber);
            // all the available moves
            ArrayList<Move> all_moves = toBeMoved.availableMoves() ;
            if(all_moves==null){
                System.out.println("No moves available =for the randomly selected piece.. choosing another") ;
                makeMove() ;// calling again because no moves are available for the current piece
            }
            else{
                int maxMoves = all_moves.size() ;
                int randomlySeletedMove = (int) (Math.random()* maxMoves) ;

                // randomly Selected move  ====
                Move randomMove = all_moves.get(randomlySeletedMove) ;

                // normal Move
                if(!randomMove.occupied()){
                    toBeMoved.setPosition(randomMove.getFinalX(),randomMove.getFinalY());
                    moveMade = true ;
                }
                // needs to take over another piece
                // takeOver move
                else if(randomMove.occupied()){

                    Piece toBeDeleted ;
                    for(int oppIterator = 0 ; oppIterator<this.getOpponent().getPieces().getNumPieces();oppIterator++){

                        toBeDeleted = this.getOpponent().getPieces().getPiece(oppIterator) ;
                        if(toBeDeleted.getX()==randomMove.getFinalX() && toBeDeleted.getY()==randomMove.getFinalY()){
                            // delete the piece if the finalX and finalY are the same as coords of the opp's Piece
                            this.getOpponent().deletePiece(toBeDeleted);
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


