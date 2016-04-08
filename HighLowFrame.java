package highlow;
//Import applicable utilities
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Color;
import java.awt.font.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


//Class that defines the HighLowFrame
//Interfaces with ActionListener and ItemListener
public class HighLowFrame extends javax.swing.JFrame implements ActionListener, ItemListener{
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	//Declare Private Global variables
	//Declare static fields
	public static JTextField txtHi,txtLo;
	//Declare JPanel for the form
	private JPanel jPanel1;
	//Declare labels on the form
	private JLabel lblTitle,lblRoundCount,lblGuessCount,lblGo;
	//Declare buttons at the bottom of the form
	private JButton btnGameRules,btnReset,btnReveal;
	private JButton btnSummary,btnExport,btnConceal;
	private JButton btnGo,btnExit;
	//Declare variables referenced by GamePlay class
	private String aGoInt,mn;
	//Declare Public Global variables
	public JTextField txtRoundCount,txtMysteryNumber,txtGuessCount,txtGo;
	public String guessNumberString,mysteryNumberString,guessCount,roundCountString,summaryCopy;
	public int guessNumber,gCount,nextGuess,roundCount,summaryInt;
	
	//Instantiate a new Game object from the GamePlay class called g
	GamePlay g = new GamePlay();
	//Call method to generate random number from the Game class
	//Assign to global variable called mysteryNumber
	public int mysteryNumber = (Integer) g.randomEngine();
		
		//Constructor for HighLowFrame
		public HighLowFrame(){
			
			//Try and catch method
			try {
				//Instantiate new instance of GridLayout object called thisLayout
				GridLayout thisLayout = new GridLayout(1,1);
				//Set number of columns
				thisLayout.setColumns(1);
				//Set horizontal and vertical gaps
				thisLayout.setHgap(5);
				thisLayout.setVgap(5);
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
				//Set the layout for the objects to be placed into the panel
				getContentPane().setLayout(thisLayout);
				//Set up panel by creating a new JPanel object
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanel1Layout.rowHeights = new int[] {1, 2, 1, 1, 250, 1, 2, 2, 1, 25, 10};
				jPanel1Layout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
				jPanel1Layout.columnWidths = new int[] {1, 1, 1, 100, 100, 100, 117};
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(499, 426));
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Label for the header title of the application
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				lblTitle = new JLabel();
				jPanel1.add(lblTitle, new GridBagConstraints(3, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblTitle.setText("Welcome to Hi-Lo Guess-So  ");
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Labels and Text Fields for the form
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				lblRoundCount = new JLabel();
				jPanel1.add(lblRoundCount, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblRoundCount.setText("Round Count");
				
				txtRoundCount = new JTextField();
				jPanel1.add(txtRoundCount, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
				txtRoundCount.setPreferredSize(new java.awt.Dimension(110, 24));
				txtRoundCount.setEnabled(false);
				
				Font rCountFont = new Font("SansSerif", Font.BOLD, 50);
				txtRoundCount.setFont(rCountFont);
				txtRoundCount.setHorizontalAlignment(JTextField.CENTER);
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Labels and Text fields for Mystery Number
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				txtMysteryNumber = new JTextField();
				jPanel1.add(txtMysteryNumber, new GridBagConstraints(3, 4, 3, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				txtMysteryNumber.setPreferredSize(new java.awt.Dimension(110, 24));
				
				Font mysteryFont = new Font("SansSerif", Font.BOLD, 150);
				txtMysteryNumber.setFont(mysteryFont);
				txtMysteryNumber.setHorizontalAlignment(JTextField.CENTER);
				txtMysteryNumber.setEnabled(false);
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Labels and Text Fields for guesses
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				lblGuessCount = new JLabel();
				jPanel1.add(lblGuessCount, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblGuessCount.setText("Guess Count");
				
				txtGuessCount = new JTextField();
				jPanel1.add(txtGuessCount, new GridBagConstraints(6, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
				txtGuessCount.setPreferredSize(new java.awt.Dimension(110, 24));
				txtGuessCount.setEnabled(false);
				
				Font gCountFont = new Font("SansSerif", Font.BOLD, 50);
				txtGuessCount.setFont(gCountFont);
				txtGuessCount.setHorizontalAlignment(JTextField.CENTER);
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Text Fields for txtHi and txtLo
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				txtHi = new JTextField();
				jPanel1.add(txtHi, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
				txtHi.setPreferredSize(new java.awt.Dimension(110, 24));
				txtHi.setEnabled(false);
				
				Font rHiFont = new Font("SansSerif", Font.BOLD, 50);
				txtHi.setFont(rHiFont);
				txtHi.setHorizontalAlignment(JTextField.CENTER);
				txtHi.setText(" ");
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Text Fields for txtLo and txtHi
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				txtLo = new JTextField();
				jPanel1.add(txtLo, new GridBagConstraints(6, 4, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
				txtLo.setPreferredSize(new java.awt.Dimension(110, 24));
				txtLo.setEnabled(false);
				
				Font gLoFont = new Font("SansSerif", Font.BOLD, 50);
				txtLo.setFont(gLoFont);
				txtLo.setHorizontalAlignment(JTextField.CENTER);
				txtLo.setText(" ");
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Buttons for rules summary and review
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				btnGameRules = new JButton();
				jPanel1.add(btnGameRules, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnGameRules.setText("Game Rules");
				btnGameRules.addActionListener(this);
				
				btnSummary = new JButton();
				jPanel1.add(btnSummary, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnSummary.setText("Scoreboard");
				btnSummary.addActionListener(this);
				
				btnReveal = new JButton();
				jPanel1.add(btnReveal, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnReveal.setText("Reveal");
				btnReveal.addActionListener(this);
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Buttons for Reset Export and Conceal
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				btnReset = new JButton();
				jPanel1.add(btnReset, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnReset.setText("Reset");
				btnReset.addActionListener(this);
				
				btnExport = new JButton();
				jPanel1.add(btnExport, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnExport.setText("Export");
				btnExport.addActionListener(this);
				
				btnConceal = new JButton();
				jPanel1.add(btnConceal, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				btnConceal.setText("Conceal");
				btnConceal.addActionListener(this);
				
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				// Button and text for GO button and exit button
				//------------------------------------------------------------------------------------------------------------------------------------------------------------
				btnGo = new JButton();
				jPanel1.add(btnGo, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				btnGo.setText("     GO     ");
				btnGo.addActionListener(this);
				SwingUtilities.getRootPane(btnGo).setDefaultButton(btnGo);
				
				txtGo = new JTextField();
				jPanel1.add(txtGo, new GridBagConstraints(3, 9, 3, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				txtGo.setPreferredSize(new java.awt.Dimension(110, 24));
				txtGo.requestFocus();
			
				btnExit = new JButton();
				jPanel1.add(btnExit, new GridBagConstraints(6, 9, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				btnExit.setText("Exit");
				btnExit.addActionListener(this);

				pack();
				this.setSize(600, 500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//End of HighLowFrame constructor

		//Responses to any actions
		public void actionPerformed(ActionEvent e) {
			
			//based on the object that triggered the event
			// call the appropriate method
			Object o = e.getSource();
			
			//If GO button is clicked Call matchNumber
		    if (o == this.btnGo) {
		    	//String goToInt = null;
				matchNumber();
			}
		    //If Game Rules button is clicked Call gameRules
		    else if (o == this.btnGameRules) {
			    gameRules();
		    }
		    //If Mystery Number button is clicked Call showMysteryNumber
		    else if (o == this.btnReveal) {
			    showMysteryNumber();
		    }
		    //If Reset button is clicked Call resetForm
			else if (o == this.btnReset) {
			    resetForm();
			}
		    //If Conceal button is clicked Call hideMysteryNumber
		    else if (o == this.btnConceal) {
			    hideMysteryNumber();
		    }
		    //If Summary button is clicked Call showSummary
		    else if (o == this.btnSummary) {
			    showSummary();
		    }
		    //If Export button is clicked Call exportSummary
		    else if (o == this.btnExport) {
			    try {
					exportSummary();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		    //If Exit button is clicked Call exitApp
		    else if (o == this.btnExit) {
		      	try {
					exitApp();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		}//End actionPerformed method
	
		//Return prices based on which combo box item is selected
		public void itemStateChanged(ItemEvent e) {
			//example of method to display the price of the product
			//based on what item was selected
			Object o = e.getSource();
		}//End of itemStateChanged method

		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to display Games Rules on form load
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void gameRules() {
			Object[] options = {"OK"};
		    Component gRulesFrame = null;
			int gRules = JOptionPane.showOptionDialog(gRulesFrame," + + + + + + + Welcome to Hi-Lo Guess-So + + + + + + + +\n"
																	+ "---------------------------------------------------------------------------------\n"
																	+ "Game Rules: \n"
																	+ "---------------------------------------------------------------------------------\n"
																	+ "\t + The Game Engine selects a random number\n"
																	+ "\t -- called the Mystery Number as the game starts.\n"
																	+ "\t + The Mystery Number is hidden in a box behind the X.\n"
																	+ "\t + Enter a number between 1 and 100.\n"
																	+ "\t + This number is known as the Guess Number.\n"
																	+ "\t + Click the GO button to try and match the Mystery Number.\n"
																	+ "\t + The Game Engine will return with one of three results.\n"
																	+ "\t + One: The Guess Number is too high.\n"
																	+ "\t + Two: The Guess Number is too low.\n"
																	+ "\t + Three: The Guess Number matches the Mystery Number.\n"
																	+ "\t + If the Guess Number is too high or too low\n"
																	+ "\t -- then the next Guess Number can entered.\n"
																	+ "\t + If the Guess Number matches the Mystery Number\n"
																	+ "\t -- a winner emerges and game play ends.",
																	"Hi-Lo Guess-So | Game Rules",JOptionPane.PLAIN_MESSAGE,
																	JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			//Put cursor in the txtGo text field
			txtGo.requestFocus();
			
		}//End of gameRules class
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to set text in txtGo field on start up
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void setGoText() {
			//Set default text inside of the txtGo text field on game form load
			txtGo.setText("Enter a Guess Number between 1 and 100");
			txtGo.selectAll();
		}//End of hideText class
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to reveal the Mystery Number
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void showMysteryNumber() {
			//Take value in mysteryNumber then convert it to a string then store it in variable called mnReveal
			String mnReveal = Integer.toString(mysteryNumber);
			//Take value in mnReveal and set it in txtMysteryNumber text field
			txtMysteryNumber.setText(mnReveal);
			//Put cursor in the txtGo text field
			txtGo.requestFocus();
		}//End of hideText class
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to conceal the Mystery Number
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void hideMysteryNumber() {
			//Hide the Mystery Number by setting the letter X in the txtMysteryNumber text field
			txtMysteryNumber.setText("X");
		}//End of hideText class
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to reset all text fields on form
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public void resetForm() {
			//Regenerate a random Mystery Number by calling randomEngine from the GamePlay class
			mysteryNumber = (int) g.randomEngine();
			
			//Reset all Array List and Counts and String values by calling the resetClear method from the GamePlay class
			g.resetClear();
			
			//Change the GO button from blocked click to unblocked click
			btnGo.setEnabled(true);
			//Change the txtGo text field from blocked data entry to allowed data entry
			txtGo.setEnabled(true);
			
			//Set default text inside of the txtGo text field on game form load
			setGoText();
			//Put cursor in the txtGo text field
			txtGo.requestFocus();

			//Reset all stored values for rounds and guesses back to zero
			//Set the Round Count back to zero
			txtRoundCount.setText("0");
			//Set the Mystery Number back to hidden
			txtMysteryNumber.setText("X");
			//Set the Guess Count back to zero
			txtGuessCount.setText("0");

			//Tell player that the form has been reset
			JOptionPane.showMessageDialog(null,"GAME RESET\nAll Round Counts and Guess Counts are cleared.\n"
												+ "Guess Number and Mystery Number have been reset. \t\n");
		}
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to match Guess number to the Mystery number
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
		public String matchNumber(){
			//Declare local variables
			guessNumberString = aGoInt;
			boolean startGame = true;
			
			while(startGame == true){
				//First round and first guess of game play
				//Check player input from the entry form to see if Guess Number is valid between 1 and 100
				//Get value from player entered Guess Number in txtGo text field
				//Assign it to a variable called gInt
				guessNumberString = txtGo.getText();
				//Check for errors by calling the validateInput method from the GamePlay class
				g.validateInput(guessNumberString);
				//Pass it through the validator
				HighLowValidator.getGo(guessNumberString);
				//-----------------------------------------------------------------------------------------------------------------------------------
				// Option A : ERRORS EXISTS : Stop game play
				//-----------------------------------------------------------------------------------------------------------------------------------
				//If validation fails then put cursor in txtGo text field and stop running code
				if(HighLowValidator.getError().length() != 0){
					txtGo.requestFocus();
					return guessNumberString;
				}
				//-----------------------------------------------------------------------------------------------------------------------------------
				// Option B : NO ERRORS : Start game play
				//-----------------------------------------------------------------------------------------------------------------------------------
				//Initiate game play by calling the gamePlay method from the GamePlay class
				//Use the parameters of the GamePlay class gamePlay method to pass value stored in gInt
				g.gamePlay(guessNumberString, mysteryNumber);
				
				//Take the returned guess count value from the getPlayCount method of the GamePlay class then assign it to a variable called gCount
				gCount = g.getGuessCountTotal();
				//Take the int gCount value then convert it to a String then assign it to a variable called guessCount
				guessCount = Integer.toString(gCount);
				//Take the guessCount value then set the txtGuessCount text field to this value
				txtGuessCount.setText(guessCount);
				
				//Take the roundCount value convert it to string then assign it to a variable called roundCountString
				roundCountString = Integer.toString(g.getRoundCount());
				//Take the roundCountString value then set it to txtRoundCount text field
				txtRoundCount.setText(roundCountString);
				
				//Set boolean to false to get out of the while loop
				startGame = false;
			}//End of WHILE LOOP

			//---------------------------------------------------------------------------------------------------------------------------------------
			// Game Play ended from the GamePlay class
			// Run code block to show Summary and tell player that the round is over
			//---------------------------------------------------------------------------------------------------------------------------------------
			
				//Since player guessed correctly set txtMysteryNumber to show the Mystery Number
				//Take the mysteryNumber value convert to string then assign it to the variable called mysteryNumberString
				txtMysteryNumber.setText(Integer.toString(g.getMysteryNumber()));
				//Display in the txtGo text field the previous Mystery Number
				txtGo.setText(g.gNextString);
				
				//JOptionPane.showMessageDialog(null,g.summaryResult());
				summaryCopy = g.summaryResult();
				
				//Game Play Round is over
				//Tell player the number of rounds played and what to do next
				JOptionPane.showMessageDialog(null,"ROUND OVER\n"
													+ "------------------------------------------\n"
													+ "Number of Rounds: [ " + roundCountString + " ] \n"
													+ "------------------------------------------\n"
													+ summaryCopy + "\nReset to play again.");
				
				//Set txtGo text field and GO button to disabled
				txtGo.setEnabled(false);
				btnGo.setEnabled(false);
			
			return guessNumberString;
		
		}//End of matchNumber method
	
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to show game summary statistics
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------
		public int showSummary() {
			Object[] options = {"OK"};
		    Component gRulesFrame = null;
		    //Add create Game Scoreboard Summary then store it in variable called summaryInt
			summaryInt = JOptionPane.showOptionDialog(gRulesFrame," + + + + + + + Welcome to Hi-Lo Guess-So + + + + + + + +\n"
																	+ "---------------------------------------------------------------------------------\n"
																	+ "Game Scoreboard: \n"
																	+ "---------------------------------------------------------------------------------\n"
																	+ "\t + Total Number of Rounds: [" + roundCountString + "]\n"
																	+ "\t + Total Number of Guesses: [" + g.getGuessCountTotal() + "]\n"
																	+ "---------------------------------------------------------------------------------\n"
																	+ "\t " + g.summaryResult()
																	+ "---------------------------------------------------------------------------------\n"
																	+ "\t + Final Mystery Number: [" + g.getMysteryNumber() + "]\n"
																	+ "\t + Final Round Number of Guesses: [" + g.countOfGuesses + "]\n"
																	+ "\t + Final Round Guess Numbers: \n\t\t   " + g.summaryRoundOne + "\n",
																	"Hi-Lo Guess-So | Game Scoreboard",JOptionPane.PLAIN_MESSAGE,
																	JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			txtGo.requestFocus();
			return summaryInt;
		}//End of showSummary method

		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		// Method to Export Individual Orders 
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
	    public void exportSummary() throws IOException{
	    	String exportFile = "C:/HiLo/HiLoDB.txt";
			FileWriter fw = null;
			PrintWriter pw = null;
			BufferedWriter bw = null;
			
			//display line write to file then set it to append data to file
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportFile,true), "utf-8"))){

				//--------------------------------------------------------------------------------------------------------------------------------------------------
				// Export and append all of the order detail to a text file
				//--------------------------------------------------------------------------------------------------------------------------------------------------
				writer.append("\r\n+ + + + + + + SCOREBOARD SUMMARY + + + + + + +\r\n"
								+ "----------------------------------------------"
								+ "\r\n + Total Number of Rounds: [" + roundCountString + "]\r"
								+ "\r\n + Total Number of Guesses: [" + g.getGuessCountTotal() + "]\r\n"
								+ "----------------------------------------------\r\n"
								+ g.summaryResult()
								+ "----------------------------------------------"
								+ "\r\n + Final Mystery Number: [" + g.getMysteryNumber() + "]"
								+ "\r\n + Final Round Number of Guesses: [" + txtGuessCount.getText() + "]"
								+ "\r\n + Final Round Guess Numbers: \r\n   " + g.guessList + "\r\n");

				// Export completes then show a success message
				JOptionPane.showMessageDialog(null,"EXPORT COMPLETE! \nThe Game Summary Statistics was added to a Text File Database."
													+ "\nMystery Number: [" + g.getMysteryNumber() + "] has been exported to HiLoDB.txt.");
				//--------------------------------------------------------------------------------------------------------------------------------------------------

					// Create ProcessBuilder
					ProcessBuilder p = new ProcessBuilder();
					//Create choice variable
					int aChoice;
					//Ask the user if they want to open the database file
					aChoice = JOptionPane.showConfirmDialog(null,"Open the HiLoDB.txt database file?\nClick YES to open the file or "
								+ "click NO to return to the entry form.","Hi-Lo Guess-So | Open Database Option",JOptionPane.YES_NO_OPTION);
					if(aChoice == JOptionPane.YES_OPTION){
						// Use command "notepad.exe" and open the file.
						p.command("notepad.exe", "C:/HiLo/HiLoDB.txt");
						p.start();
				}
				else{
					//Return to the data entry form
					}

			}catch(IOException ioEx){
				JOptionPane.showMessageDialog(null, ioEx);
			}
			finally{
				try{
					pw.close();
					bw.close();
					fw.close();
				}
				catch(Exception ex){
				}
			}
	    }//End of exportOrder method

	  //---------------------------------------------------------------------------------------------------------------------------------------------------------------
	  // Method to exit the application
	  //---------------------------------------------------------------------------------------------------------------------------------------------------------------
	  public void exitApp() throws IOException{
		  //Tell player the game is over and thank them for playing
		  JOptionPane.showMessageDialog(null, "GAME PLAY ENDED\nThank you for playing!\n+++ Hi-Lo Guess-So +++\n");
		  System.exit(0);
	  }//End of exitApp method

}//End of HighLowFrame class