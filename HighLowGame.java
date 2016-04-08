package highlow;
//Import all needed Java utilities
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;

/*///------------------------------------------------------------
 * Author: Khiem Ken Trinh
 * Application Name: Hi-Lo Guess-So Game
 * Authored Date: 03/21/2016
 *///------------------------------------------------------------

//This is the application class that starts up the application and
//this application class contains the MAIN METHOD
//Class that defines the HighLowGame class
public class HighLowGame {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//Instantiate a new HighLowFrame object called inst
		HighLowFrame inst = new HighLowFrame();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
		
		//Instantiate a new JFrame object called winListener
		JFrame winListener = new JFrame("Window Listener");
		WindowListener listener = new WindowAdapter() {
		
		@Override
		public void windowClosing(WindowEvent w) {
		    System.exit(0);
		}
		  };
		  	winListener.addWindowListener(listener);
		  	winListener.setSize(100, 100);
		  	winListener.setVisible(false);
		  	
		//Display the Game Rules by calling the gameRules method from the HighLowFrame class as soon as the form loads
		inst.gameRules();
		//Show default text inside of the txtGo field from the HighLowFrame class as soon as the form loads
		inst.setGoText();
		//Hide the Mystery Number by calling the hideMysteryNumber method from the HighLowFrame class as soon as the form loads
		inst.hideMysteryNumber();

	}//End of Main method

}//End of HighLowGame class