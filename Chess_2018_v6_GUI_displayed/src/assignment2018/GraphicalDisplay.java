package assignment2018;

import java.beans.Visibility;
import java.util.ArrayList ;
import java.awt.* ;
import java.awt.image.BufferedImage;
import javax.swing.* ;
import javax.imageio.* ;
import java.io.* ;



public class GraphicalDisplay extends GeneralFrameGui  {
    private ArrayList<BufferedImage> white_pieces ;
    private ArrayList<BufferedImage> black_pieces ;
    private JButton[][] buttons  ;

    public GraphicalDisplay() {
        // chessBoard buttons
         buttons = new JButton[8][8];



        // The side panels
        JPanel numberPanel = new JPanel(new GridLayout(0, 1));// at the left side of the window
        JPanel alphaPanel = new JPanel(new GridLayout(1, 0));// at the top of the window
        //These will contain the images of the pieces of both colors

         this.white_pieces = new ArrayList<BufferedImage>();
         this.black_pieces = new ArrayList<BufferedImage>();

        // adding all the images
        /*
        try {

            BufferedImage white_pawn = ImageIO.read(new File("resources/white_pawn.png"));
            BufferedImage white_rook = ImageIO.read(new File("resources/white_rook.png"));
            BufferedImage white_knight = ImageIO.read(new File("resources/white_knight.png"));
            BufferedImage white_bishop = ImageIO.read(new File("resources/white_bishop.png"));
            BufferedImage white_queen = ImageIO.read(new File("resources/white_queen.png"));
            BufferedImage white_king = ImageIO.read(new File("resources/white_king.png"));


            // adding all the images to the ArrayList of White Images
            white_pieces.add(white_pawn) ;
            white_pieces.add(white_rook) ;
            white_pieces.add(white_bishop) ;
            white_pieces.add(white_knight) ;
            white_pieces.add(white_queen) ;
            white_pieces.add(white_king) ;


            BufferedImage black_pawn = ImageIO.read(new File("resources/black_pawn.png"));
            BufferedImage black_rook = ImageIO.read(new File("resources/black_rook.png"));
            BufferedImage black_bishop = ImageIO.read(new File("resources/black_bishop.png"));
            BufferedImage black_queen = ImageIO.read(new File("resources/black_queen.png"));
            BufferedImage black_king = ImageIO.read(new File("resources/black_king.png"));
            BufferedImage black_knight = ImageIO.read(new File("resources/black_knight.png")) ;

            // adding all the black pieces to the ArrayList of Buffered Images

            black_pieces.add(black_pawn);
            black_pieces.add(black_rook);
            black_pieces.add(black_bishop);
            black_pieces.add(black_queen);
            black_pieces.add(black_king);
            black_pieces.add(black_knight);



        }
        catch(IOException ex){
            System.out.println(ex) ;
        } */






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


    /**
     *
     *
     *
     *
     * */
    public void initialize (){

        Board initialBoard = new Board() ;
        initialBoard.initialize();

        char[][] temp = new char[][] ;
        for(int rows=0 ; rows<8 ; rows++){
            for(int cols=0 ; cols<8 ; cols++){
                if(initialBoard.occupied(rows,cols)){
                    Image image_to_be_added = null ;
                    try {
                         image_to_be_added = ImageIO.read(new File(imageBuild(initialBoard.getPiece(rows, cols).getChar())));
                    }catch(Exception ex){
                        System.out.println(ex) ;
                    }
                    buttons[rows][cols].setIcon(new ImageIcon(image_to_be_added)) ;

                }

            }
        }
    }

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

        GraphicalDisplay one = new GraphicalDisplay() ;
        one.initialize();
        one.setVisible(true);


    }

}
