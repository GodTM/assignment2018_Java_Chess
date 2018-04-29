import assignment2018.codeprovided.*;

public class HumanPlayer extends Player{

    boolean isYourTurn ;
    public HumanPlayer(String n, Pieces p, Board b, Player o, boolean turn){
        super(n,p,b,o) ;
        this.isYourTurn = turn ;
    }

    public boolean getIsYourTurn() {
        return isYourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.isYourTurn = yourTurn;
    }


    public makeMove(){

        if(getIsYourTurn()){

            System.out.println("Enter the start and ending coordinates :") ;

            int

        }
    }
}
