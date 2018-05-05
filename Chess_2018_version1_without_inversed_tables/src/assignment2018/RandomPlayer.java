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
    /*
    public boolean getIsYourTurn(){
        return this.isYourTurn ;
    }
    public boolean setIsYourTurn(boolean b){

        return this.isYourTurn = b ;
    }*/

    /*
    public boolean makeMove() {
        boolean moveMade = false;
        // the initial and the final coordinates of the move

        String fromCoords;
        String toCoords;

        boolean finalCoordsCheck = false;
        boolean initialCoordsCheck = false;
        int xInitial = -1;
        int yInitial = -1;
        int xFinal = -1;
        int yFinal = -1;

        while (!initialCoordsCheck) {

            int xInitialRandom = (int) Math.random() * 9;
            int yInitialRandom = (int) Math.random() * 9;

            if (xInitialRandom <= 7 && yInitialRandom <= 7) {

                xInitial = xInitialRandom;
                yInitial = yInitialRandom;
                initialCoordsCheck = true;
            }
            else if (xInitialRandom > 8) {
                System.out.println("the initial x coordinate is being calculated");
            }
            else if (yInitialRandom > 8) {
                System.out.println("The initial Y coordinates are being calculated");

            }
        }


        while (!finalCoordsCheck) {

            int xFinalRandom = (int) Math.random() * 9;
            int yFinalRandom = (int) Math.random() * 9;
            if (xFinalRandom <= 7 && yFinalRandom <= 7) {

                xFinal = xFinalRandom;
                yFinal = yFinalRandom;
                finalCoordsCheck = true;
            }
            else if (xFinalRandom > 8) {
                System.out.println("the final x coordinate is being calculated");
            }
            else if (yFinalRandom > 8) {
                System.out.println("The final Y coordinates are being calculated");

            }

        }

        // make sure that the color of the pieces are not the same
        // error in the following line
        if (initialCoordsCheck && finalCoordsCheck) {

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
                if(myPieces!=null){
                    ArrayList<Move> all_moves = pieceAtInitialPos.availableMoves() ;
                    if(all_moves==null){
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
                            }
                            // ensuring that the place to move to is occupied
                            else if(m.sameMove(tempMoveTrue) && this.getBoard().occupied(xFinal,yFinal)){
                                // iterating over opponent's pieces  and removing the one with coordinates xFinal,yFinal ;
                                for(int toBeRemovedIterator=0; toBeRemovedIterator<= this.getOpponent().getPieces().getNumPieces() ; toBeRemovedIterator++){

                                    if(this.getOpponent().getPieces().getPiece(toBeRemovedIterator).getX()==xFinal &&this.getOpponent().getPieces().getPiece(toBeRemovedIterator).getY()==yFinal){

                                        // deleting the piece
                                        Piece toBeRemoved = this.getOpponent().getPieces().getPiece(toBeRemovedIterator) ;
                                        this.getOpponent().getPieces().delete(toBeRemoved);
                                        pieceAtInitialPos.setPosition(xFinal,yFinal);
                                        moveMade = true ;
                                        System.out.println("Making a takeOver Move") ;
                                    }
                                }
                            }
                        }
                    }
                }

                //  final check
                if(this.getBoard().getPiece(xFinal,yFinal).equals(pieceAtInitialPos)){
                    moveMade = true;
                }

            }
            else{
                System.out.println("No piece is there at the initial position") ;
                return moveMade ;
            }

        }
        return moveMade;

    }


    */

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


