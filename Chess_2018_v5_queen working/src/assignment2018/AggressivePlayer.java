package assignment2018 ;
import java.util.* ;
import assignment2018.codeprovided.Player;
import assignment2018.codeprovided.Piece ;
import assignment2018.codeprovided.Pieces ;

import assignment2018.* ;


public class AggressivePlayer extends Player{

    boolean isYourTurn ;
    public AggressivePlayer(String name ,Pieces piece, Board board , Player opponent) {

        super(name, piece, board, opponent);
    }


    // for each corressponding  piece in my piece array, take coordinates of each move , now compare those with the coordinates of Piece objects in the
    // opponent's Pieces ArrayList



    public boolean makeMove(){

        boolean moveMade = false ;
        int highestValueMove = 0 ;
        // to generate random moves only if the of all the moves from the list of moves are not takeOverMoves are present

        boolean noTakeOverMovePresent = true ;
        Pieces myPieces = this.getPieces() ;
        for(int iterator = 0; iterator<myPieces.getNumPieces();iterator++){
            //for(Piece myPiece:this.getPieces()){
            Piece myPiece = myPieces.getPiece(iterator) ;
            ArrayList<Move> allMovesOfTempPiece=myPiece.availableMoves();
            for(Move move:allMovesOfTempPiece){

                Move possibleMove= move;
                // if the Move involves taking down an opponent piece , then find the most aggressive move

                /*
                 *
                 * temp  -to be removed - find a way to iterate over all the moves(check if all moves have occupied = false)
                 * */
                List<Integer> pieceValueArray = new ArrayList<>();
                if(possibleMove.occupied()){
                    noTakeOverMovePresent = false ;


                    Piece opPiece = null;
                    for(int opIterator = 0; opIterator<= this.getOpponent().getPieces().getNumPieces() ; opIterator++ ){
                        opPiece = this.getOpponent().getPieces().getPiece(opIterator);

                        // the opponent piece's X and Y coords are equal to the final X and Y coords of the move, then store the pieceValue of
                        if(opPiece.getX()==possibleMove.getFinalX()&&opPiece.getY()==possibleMove.getFinalY()){
                            int tempOppPieceValue = opPiece.getValue() ;// value of each piece in the opponent Pieces ArrayList
                            pieceValueArray.add(tempOppPieceValue) ;//highestValueMove = tempOppPieceValue;
                        }
                    }

                    highestValueMove = Collections.max(pieceValueArray) ;// deleting the piece and setting is your Turn to False  ;
                    // deleting the piece with the highest value and then sets isyourturn = false

                    // check for the piece with the highest value in the opponent ArrayList and Delete it
                    int pieceNumberInOppArrayList = 0  ;
                    Piece toBeDeleted = null ;
                    for(int iterator2 = 0 ; iterator2<=this.getOpponent().getPieces().getNumPieces(); iterator2++){
                        toBeDeleted = this.getOpponent().getPieces().getPiece(iterator2) ;
                        pieceNumberInOppArrayList += 1;
                        if(toBeDeleted.getValue() == highestValueMove){

                            Piece p = this.getOpponent().getPieces().getPiece(pieceNumberInOppArrayList) ;
                            Pieces pieces = this.getOpponent().getPieces() ;
                            pieces.delete(p) ;
                            moveMade  = true ;
                        }
                    }
                }
                // SELECT A MOVE AT RANDOM
                // random moves only applied only if no TakeOverMoves are present in the Move ArrayList
                else if(!possibleMove.occupied()  && noTakeOverMovePresent){
                    randomMove();
                    if(randomMove()){
                        moveMade = true ;
                    }
                }


            }

        }
        return moveMade ;

    }

    public  boolean randomMove(){

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

            if (xInitialRandom <= 8 && yInitialRandom <= 8) {

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
            if (xFinalRandom <= 8 && yFinalRandom <= 8) {

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

            if (this.getBoard().occupied(xInitial, yInitial)) {
                Piece pieceAtInitialPos = this.getBoard().getPiece(xInitial, yInitial);
                // the method below takes care of verifying that the color of the pieces are different
                this.getBoard().setPosition(xFinal, yFinal,pieceAtInitialPos);

                // verifying that the piece at the final position is actually the piece that we moved
                if (this.getBoard().getPiece(xFinal, yFinal).equals(pieceAtInitialPos)) {
                    moveMade = true;
                }
            } else {
                System.out.println("No piece is there at the initial position");
            }

        }
        return moveMade;

    }

}
