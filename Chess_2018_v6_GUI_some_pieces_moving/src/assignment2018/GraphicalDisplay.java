package assignment2018;
/**
 *
 * GraphicalDisplay.java
 * Displays all the pieces on the board
 *
 * @author  Eeshan Jaiswal  created 8/5/2018
 *
 *
 *
 * */

import assignment2018.codeprovided.Piece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;
import java.util.ArrayList ;
import java.awt.* ;
import java.awt.image.BufferedImage;
import javax.swing.* ;
import javax.imageio.* ;
import java.io.* ;



public class GraphicalDisplay extends GeneralFrameGui implements ActionListener {
    private ArrayList<BufferedImage> white_pieces ;
    private ArrayList<BufferedImage> black_pieces ;
    private JButton[][] buttons  ;
    private Board board ;

    public GraphicalDisplay(Board b) {
        // chessBoard buttons
         buttons = new JButton[8][8];
         this.board = b ;


        // The side panels
        JPanel numberPanel = new JPanel(new GridLayout(0, 1));// at the left side of the window
        JPanel alphaPanel = new JPanel(new GridLayout(1, 0));// at the top of the window




        //generating numeral side panel
        for (int counter = 0; counter <= 8; counter++) {
            if (counter == 0) {
                numberPanel.add(new JButton(""));
            } else {
                numberPanel.add(new JButton(Integer.toString(counter)));
                alphaPanel.add(new JButton(Character.toString((char) ('A' + counter - 1))));// adding the elements to the alpha panel
            }
        }



        Container contentPane = getContentPane() ;
        contentPane.setLayout(new GridLayout(8,8));
        JPanel chessBoard = new JPanel(new GridLayout(8,8))  ;

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for(int row=0 ; row<buttons.length ; row++){
            for(int cols=0 ; cols<buttons[row].length; cols++){
                buttons[row][cols] = new JButton() ;

                // adding the same listener to every button object on the 2D button array ..
                buttons[row][cols].addActionListener(this) ;
                buttons[row][cols].setMargin(buttonMargin);


                buttons[row][cols].setMargin(new Insets(0, 0, 0, 0));
                buttons[row][cols].setContentAreaFilled(false);
                buttons[row][cols].setFocusPainted(false);
                buttons[row][cols].setBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0));
                buttons[row][cols].setOpaque(true);

                if((row+cols) % 2 !=0){
                    buttons[row][cols].setBackground(Color.GRAY);
                }
                contentPane.add(buttons[row][cols]) ;
            }
        }

        setResizable(false);





    }

    public String imageBuild(char c){
        String address = "" ;

        switch(c) {

            case 'p':
                address = "src/assignment2018/resources/white_pawn.png";
                break;
            case 'P':
                address = "src/assignment2018/resources/black_pawn.png";
                break  ;
            case 'r':
                address = "src/assignment2018/resources/white_rook.png";
                break ;
            case 'R':
                address = "src/assignment2018/resources/black_rook.png";
                break ;
            case 'n':
                address = "src/assignment2018/resources/white_knight.png";
                break  ;
            case 'N':
                address = "src/assignment2018/resources/black_knight.png";
                break ;
            case 'b':
                address = "src/assignment2018/resources/white_bishop.png";
                break ;
            case 'B':
                address = "src/assignment2018/resources/black_bishop.png";
                break ;
            case 'k':
                address = "src/assignment2018/resources/white_king.png";
                break ;
            case 'K':
                address = "src/assignment2018/resources/black_king.png";
                break ;
            case 'q':
                address = "src/assignment2018/resources/white_queen.png";
                break ;
            case 'Q':
                address = "src/assignment2018/resources/black_queen.png";
                break ;
            default:
                address = "";

        }
        return address ;

    }

    public char addressToChar(String address){

        char imageCharacter ;
        switch(address) {

            case "src/assignment2018/resources/white_pawn.png":
                imageCharacter = 'p';
                break;
            case "src/assignment2018/resources/black_pawn.png":
                imageCharacter = 'P';
                break;
            case "src/assignment2018/resources/white_rook.png":
                imageCharacter = 'r';
                break;
            case "src/assignment2018/resources/black_rook.png":
                imageCharacter = 'R';
                break;
            case "src/assignment2018/resources/white_knight.png":
                imageCharacter = 'n';
                break;
            case "src/assignment2018/resources/black_knight.png":
                imageCharacter = 'N';
                break;
            case "src/assignment2018/resources/white_bishop.png":
                imageCharacter = 'b';
                break;
            case "src/assignment2018/resources/black_bishop.png":
                imageCharacter = 'B';
                break;
            case "src/assignment2018/resources/white_king.png":
                imageCharacter = 'k';
                break;
            case "src/assignment2018/resources/black_king.png":
                imageCharacter = 'K';
                break;
            case "src/assignment2018/resources/black_queen.png":
                imageCharacter = 'q';
                break;
            case "src/assignment2018/resources/white_queen.png":
                imageCharacter = 'Q';
                break;
            default:
                return '.';
        }
        return imageCharacter ;
    }




    /**
     *
     *
     *
     *
     * */
    public void initialize (){

        this.board.initialize();

        for(int rows=7 ; rows>=0 ; rows--){
            for(int cols=7 ; cols>=0 ; cols--){
                if(board.occupied(rows,cols)){
                    Image image_to_be_added = null ;
                    try {
                         image_to_be_added = ImageIO.read(new File(imageBuild(this.board.getPiece(rows, cols).getChar())));
                    }catch(Exception ex){
                        System.out.println(ex) ;
                    }
                    buttons[cols][rows].setIcon(new ImageIcon(image_to_be_added)) ;

                }
            }
        }
    }

    JButton pieceToBeMoved = null ;
    JButton destination = null  ;
    ArrayList <Move> all_moves = null  ;

    public void actionPerformed(ActionEvent e){
        Piece toBeMoved ;


        if(e.getSource() instanceof JButton){

            // checking whether mouse click presses the button or not
            JButton buttonClicked = (JButton)e.getSource();
            if(pieceToBeMoved==null ){
                 pieceToBeMoved = buttonClicked ;

                for(int row=0 ; row<8 ; row++){
                    for(int col = 0 ; col<8; col++){
                        if(this.buttons[col][row] == e.getSource() && this.board.occupied(row,col)){
                            // generate all the moves of the piece at the particular position
                            toBeMoved = this.board.getPiece(row,col);

                            // throws NUll pointer exception
                            try {
                                all_moves = toBeMoved.availableMoves();


                            }catch(Exception ex){
                                System.out.println(ex) ;
                            }
                        }
                    }
                }
            }
            else{
                int finalX , finalY  ;
                for(int row = 0 ; row<8 ; row++){
                    for(int col = 0 ; col<8 ; col++){
                        if(buttons[col][row]==e.getSource()){
                            finalX= row ;
                            finalY = col ;
                            if(all_moves!=null){

                                for(Move m:all_moves){
                                    if(m.getFinalX()==finalX && m.getFinalY()==finalY) {
                                        buttonClicked.setIcon(pieceToBeMoved.getIcon());
                                        pieceToBeMoved.setIcon(null);
                                    }

                                }
                            }
                            pieceToBeMoved = null  ;
                        }
                    }
                }

            }



        }

    }





    /**
     *
     * temporary method to check the source of the images are correct or not
     * */
    public void ImageCheck(){
        String [] add_array = {"src/assignment2018/resources/white_pawn.png", "src/resources/black_pawn.png" , "resources/white_rook.png" , "resources/black_rook.png", "resources/white_bishop.png",
                                    "src/resources/black_bishop.png", "resources/white_queen.png", "resources/black_queen.png","resources/white_king.png", "resources/black_king.png"} ;


        for(String text:add_array){
            File imageCheck = new File(text) ;
            if(imageCheck.exists()){
                System.out.println(text+" exists") ;
            }
            else{
                System.out.println(text+" does not exist") ;
            }
        }
    }


    public static void  main(String args[]){

        Board thisBoard = new Board() ;

        GraphicalDisplay one = new GraphicalDisplay(thisBoard) ;
        one.initialize();
        one.setVisible(true);


    }

}
