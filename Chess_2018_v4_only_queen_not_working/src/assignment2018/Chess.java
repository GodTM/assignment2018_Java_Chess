package assignment2018 ;

import java.util.* ;
import assignment2018.codeprovided.Player;
import assignment2018.HumanPlayer ;
import assignment2018.RandomPlayer;
import assignment2018.AggressivePlayer ;
import assignment2018.codeprovided.Pieces ;


public class Chess{

    private HumanPlayer humanPlayer;
    private Player player2 ;
    private Board board ;
    private TextDisplay displayBoard ;


    public Chess(){

        this.board  = new Board() ;
        // setting up the initial state
        this.board.initialize();

        Scanner input = new Scanner(System.in);
        // FIRST enter the your name , enter the color of your pieces
        System.out.println("Please enter your name") ;
        String userName = input.nextLine().toLowerCase().trim() ;
        System.out.println("Please select the color of pieces W / B ") ;
        String colorOfPiecesOfUser="" ;
        boolean validColor = false ;

        while(!validColor) {
            colorOfPiecesOfUser = input.nextLine().trim();

            if(colorOfPiecesOfUser.equalsIgnoreCase("W")|| colorOfPiecesOfUser.equalsIgnoreCase("B")){
                validColor = true ;
                break ;
                }
                //System.out.println("as") ;
        }


        this.humanPlayer =  null ;


        // then select the opponent type

        System.out.println("Choose your opponent :\n Random Player = r \n Aggressive Player = a \n Human Player = h \n ") ;
        String potentialOpponent ="" ;

        boolean correctOpponentSelected = false ;
        // taking input to set up the opponent ;
        while(!correctOpponentSelected) {
            // taking the input to set up the opponent
            potentialOpponent  = input.nextLine().trim() ;

            if(potentialOpponent.equalsIgnoreCase("r")|| potentialOpponent.equalsIgnoreCase("a")|| potentialOpponent.equalsIgnoreCase("h")){

                correctOpponentSelected = true ;
            }
        }

        // declaring the opponent
        if(potentialOpponent.equalsIgnoreCase("r")){
            if(colorOfPiecesOfUser.equalsIgnoreCase("W")) {
                this.player2 = new RandomPlayer("Random Computer",board.getBlackPieces() ,this.board,null);
            }
            else if(colorOfPiecesOfUser.equalsIgnoreCase("B")){
                this.player2 = new RandomPlayer("Random Computer",board.getWhitePieces() ,this.board,null);
            }
        }
        else if(potentialOpponent.equalsIgnoreCase("a")){

            if(colorOfPiecesOfUser.equalsIgnoreCase("W")) {
                this.player2 = new AggressivePlayer("Aggressive Computer",board.getBlackPieces() ,this.board,null);
            }
            else if(colorOfPiecesOfUser.equalsIgnoreCase("B")){
                this.player2 = new AggressivePlayer("Aggressive Computer",board.getWhitePieces() ,this.board,null);
            }
        }
        else if(potentialOpponent.equalsIgnoreCase("h")){
            System.out.println("ENTER THE NAME OF THE OPPONENT HUMAN PLAYER : ");
            String opponentName = input.nextLine().toLowerCase().trim() ;

            if(colorOfPiecesOfUser.equalsIgnoreCase("W")) {
                this.player2 = new HumanPlayer(opponentName,board.getBlackPieces() ,this.board,null) ;
            }
            else if(colorOfPiecesOfUser.equalsIgnoreCase("B")){
                this.player2 = new HumanPlayer(opponentName,board.getWhitePieces() ,this.board,null) ;
            }
        }

        // declaring the user player
        if(colorOfPiecesOfUser.equalsIgnoreCase("W")) {
            this.humanPlayer = new HumanPlayer(userName,board.getWhitePieces(),this.board,this.player2 ) ;
        }
        else{
            this.humanPlayer = new HumanPlayer(userName,board.getBlackPieces(),this.board,this.player2 ) ;
        }

        this.player2.setOpponent(this.humanPlayer);


        this.displayBoard = new TextDisplay(this.board) ;

    }

    public void run() {

        // displaying the board
        //TextDisplay displayBoard = new TextDisplay(this.board) ;
        //displayBoard.display(this.board.getWhitePieces()) ;//displayBoard.display(this.board.getBlackPieces()) ;

        boolean gameOver = false;
        // set as false initially
        /*
        boolean firstKingPresent = false;
        boolean secondKingPresent = false;

        for (int iterator = 0; iterator < this.humanPlayer.getPieces().getNumPieces(); iterator++) {
            if (this.humanPlayer.getPieces().getPiece(iterator).getChar() == 'k' || this.humanPlayer.getPieces().getPiece(iterator).getChar() == 'K') {
                firstKingPresent = true;
            }
        }
        for (int iterator = 0; iterator < this.humanPlayer.getOpponent().getPieces().getNumPieces(); iterator++) {
            if (this.humanPlayer.getOpponent().getPieces().getPiece(iterator).getChar() == 'k' || this.humanPlayer.getOpponent().getPieces().getPiece(iterator).getChar() == 'K') {
                secondKingPresent = true;
            }
        }
        */
        while (!gameOver) {

            displayBoard.displayPieces();

            boolean player1MakeMove = false;
            boolean player2MakeMove = false;

            System.out.println("PLayer 1 chance : ") ;
            while (!player1MakeMove) {
                if (this.humanPlayer.makeMove() ==true) {
                    player1MakeMove = true;
                    if(killedKing(this.player2)){
                        System.out.println("Player 1 wins ") ;
                        gameOver = true ;
                        System.exit(0) ;
                    }
                    displayBoard.displayPieces();
                    }
            }
            /*
            if (player1MakeMove) {
                displayBoard.displayPieces();
            }
            */
            System.out.println("Player2 chance") ;
            while (!player2MakeMove) {
                if (this.player2.makeMove()) {
                    player2MakeMove = true;
                    if(killedKing(this.humanPlayer)){
                        System.out.println("Player 2 wins ") ;
                        gameOver = true ;
                        System.exit(0) ;
                    }
                    displayBoard.displayPieces();
                    break ;
                    }
            }


            /*
            if (player2MakeMove) {
                displayBoard.displayPieces();
            }
            */
            // if both players have made the move == call the method again

            if(player1MakeMove && player2MakeMove){
                /*
                if(!firstKingPresent){
                    System.out.println("Player2 wins") ;
                    System.exit(0);

                }
                else if (!secondKingPresent) {
                    System.out.println("Player1 wins") ;
                    System.exit(0) ;

                } */
                run() ;
            }


        }



    }

    public boolean killedKing(Player p){
        int numberOfPieces = p.getPieces().getNumPieces();
        for(int iterator = 0  ; iterator< numberOfPieces;iterator++){

            if(p.getPieces().getPiece(iterator).getChar()=='k' || p.getPieces().getPiece(iterator).getChar()=='K'){
                return false ;
            }
        }
        return true ;
    }



    public static void main(String args[]){

        Chess newGame = new Chess() ;
        newGame.run() ;

    }

}