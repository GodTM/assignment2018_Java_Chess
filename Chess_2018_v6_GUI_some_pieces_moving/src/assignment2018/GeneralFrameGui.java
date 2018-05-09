package assignment2018 ;
import java.awt.* ;
import javax.swing.* ;

public class GeneralFrameGui extends JFrame{


    public GeneralFrameGui(){
        setTitle("Chess GUI"); // setting the title of the GUI window

        Toolkit toolkit = Toolkit.getDefaultToolkit() ;
        Dimension screenDimensions = toolkit.getScreenSize() ;
        setSize(screenDimensions.height,screenDimensions.height) ; // the size of the GUI window
        setLocation(new Point(0,0));// the starting point of the window

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ; // tells the program to be closed upon pressing x
        setVisible(true);

    }


}