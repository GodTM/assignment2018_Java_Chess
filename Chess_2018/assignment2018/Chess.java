import java.util.* ;

public class Chess{

    private HumanPlayer hp1;
    private Player p2 ;
    private Board b ;


    public Chess(Board b){

        this.b = b  ;

        Scanner input = new Scanner(System.in);
        // FIRST enter the your name , enter the color of your pieces

        String userName = input.nextLine().toLowerCase().trim() ;
        System.out.println("Please select the color of pieces W / B ") ;
        String colorOfPiecesOfUser ;
        boolean validColor = false ;

        while(!validColor) {
            colorOfPiecesOfUser = input.nextLine().trim().toUpperCase();

            if(colorOfPiecesOfUser=="W"|| colorOfPiecesOfUser=="B"){
                validColor = true ;
                }
        }

        this.hp1 =  null ;


        // then select the opponent type

        System.out.println("Random Player = r %n Agressive Player = a %n Human Player = h %n Choose your opponent :") ;
        String potentialOpponent ;

        correctOpponentSelected = false ;
        // taking input to set up the opponent ;
        while(!correctOpponentSelected) {
            // taking the input to set up the opponent
            potentialOpponent  = input.nextLine().trim().toLowerCase() ;

            if(potentialOpponent == "r" || potentialOpponent=="a"|| potentialOpponent =="h"){

                correctOpponentSelected = true ;
            }
        }

        // declaring the opponent
        if(potentialOpponent =="r"){
            if(colorOfPiecesOfUser="W") {
                this.p2 = new RandomPlayer("Random Computer",b.blackPieces ,this.b,this.hp1);
            }
            else if(colorOfPiecesOfUser="B"){
                this.p2 = new RandomPlayer("Random Computer",b.whitePieces ,this.b,this.hp1);
            }
        }
        else if(potentialOpponent=="a"){

            if(colorOfPiecesOfUser="W") {
                this.p2 = new AggressivePlayer("Agressive Computer",b.blackPieces ,this.b,this.hp1);
            }
            else if(colorOfPiecesOfUser="B"){
                this.p2 = new AggressivePlayer("Agressive Computer",b.whitePieces ,this.b,this.hp1));
            }
        }
        else if(potentialOpponent=="h"){
            System.out.println("ENTER THE NAME OF THE OPPONENT HUMAN PLAYER : ");
            opponentName = input.nextLine().toLowerCase().trim() ;

            if(colorOfPiecesOfUser="W") {
                this.p2 = new HumanPlayer(opponentName,b.blackPieces ,this.b,this.hp1) ;
            }
            else if(colorOfPiecesOfUser="B"){
                this.p2 = new HumanPlayer(opponentName,b.whitePieces ,this.b,this.hp1) ;
            }
        }

        // declaring the user player
        if(colorOfPiecesOfUser=="W") {
            this.hp1 = new HumanPlayer(userName,b.whitePieces,this.b,this.p2 ) ;
        }
        else{
            this.hp1 = new HumanPlayer(userName,b.blackPieces,this.b,this.p2 ) ;
        }

    }



    public static void main(String args[]){






        gameFinished = false ;
        while(!gameFinished){

            boolean whiteChance = true ;
            boolean blackChance = false ;




        }

    }

}