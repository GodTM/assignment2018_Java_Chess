package assignment2018;
import javax.swing.* ;
import java.awt.* ;
import  javax.swing.JMenuBar ;
import assignment2018.codeprovided.Board ;
//import


class Application extends JFrame{
    private Board b ;
    public Application(String s, Board b){

        this.b = b ;
        // title of the application.
        setTitle(s) ;

        Toolkit toolkit = Toolkit.getDefaultToolkit()  ;
        // sets the app size to the size of the screen
        Dimension screenDimensions = toolkit.getScreenSize() ;
        setSize(screenDimensions.width , screenDimensions.height) ;


        //==========================
        JMenuBar menuBar = new JMenuBar() ;
        this.setJMenuBar(menuBar);

        JMenu main = new JMenu("Chess") ;
        menuBar.add(main) ;

        JMenuItem settings = new JMenuItem("Settings") ;
        main.add(settings) ;

        //============================


        // exits the application upon closing of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String args[]){

        Board tempBoard = new Board(8 , 8) ;

        Application sample = new Application("Chess",tempBoard) ;
    }


}
