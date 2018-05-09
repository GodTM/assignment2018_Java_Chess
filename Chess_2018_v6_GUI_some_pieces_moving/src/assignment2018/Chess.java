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

    /**
     * Constructor for the main chess application class
     * Takes in the name, and the color of the pieces of the user. Then asks and sets up the preferred opponent
     * */
    public Chess(){
        this.board  = new Board() ;
        this.board.initialize();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name") ;// FIRST enter the your name , enter the color of your pieces
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

        this.player2.setOpponent(this.humanPlayer); // set the opponent of the second player
        this.displayBoard = new TextDisplay(this.board) ;// then display the board

    }

    /**
     * continues to run the game until either one of the king dies. The program exits upon the death of a king piece
     *
     * */
    public void run() {

        boolean gameOver = false;

        // set as false initially

        while (!gameOver) {

            displayBoard.displayPieces();
            boolean player1MakeMove = false;
            boolean player2MakeMove = false;

            System.out.println() ;
            System.out.println("PLayer 1 chance : ") ;
            while (!player1MakeMove) {
                if (this.humanPlayer.makeMove()) {
                    player1MakeMove = true;
                    if(killedKing(this.player2)){
                        System.out.println("Player 1 wins ") ;
                        gameOver = true ;
                        System.exit(0) ;
                    }
                    displayBoard.displayPieces();
                }
            }
            System.out.println() ;
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
            // if both players have made the move == call the method again
            if(player1MakeMove && player2MakeMove){
                run() ;
            }
        }
    }


    /**
     * checks whether the king in a particular pieces object is present or not
     *
     * @param   p   the pieces object in which the king has to be found
     * @return      true if the king in a pieces object is absent
     * */
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