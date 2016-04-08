package highlow;
//Import all Java utilities
import java.awt.Component;
import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


//Establish class called Game
public class GamePlay {
	//Declare global variables
	private static final String GUESS = null;
	public int gNum,mNum,gNext,countOfGuesses = 1,gCount = 1,countOfRounds = 1,cr1 = 0,cr2 = 0,startIndex,endIndex;
	public String gNumString,gNextString,guessListAll,guessListCopy,
					tempGuessListString,roundCountString,eachRound,Summary="Summary of Guess Numbers: \r\n";
	
	public boolean endOfRound = false;
		
	ArrayList<String> guessList = new ArrayList<String>();
	ArrayList<String> summaryRoundOne;

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Method of random number generator that picks a number between 1 and 100
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public Object randomEngine(){
		//Generate random numbers between 1 and 100
		//This random number will be called the Mystery Number
		Random rn = new Random();
		int goToInt = rn.nextInt(100) + 1;
		return goToInt;
	}//End of randomEngine class

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Method of game play to handle if guess > mystery or guess < mystery or other
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int gamePlay(String gn,int mn){
		//Declare local variables to store player input and guess counts
		gNumString = gn;
		mNum = mn;
		int playAgain = 0,x = 0,gNumX = 0;
		boolean guessAgain = true;
		
		//Take the player string input from the HighLowFrame class called gn then store it in variable called gNumString
		try {
			//Convert integer gNumString value to string then store it in String variable called gNum
			gNum = Integer.parseInt(gNumString);
		}catch(NumberFormatException e){
			//System will Throw exception!
		}
		//MUST HAVE THIS OTHERWISE Guess Number in txtGo text field from HighLowFrame class is not captured
		//Store first Guess Number from the HighLowFrame class to the Array List called guessList
		guessList.add(gNumString);
		
		//While Loop #1 : Outer Loop
		while(guessAgain == true){
			//Game Play has four possible outcomes
			//Outcome #1: guessNumber matches the mysteryNumber
			if(gNum == mNum){
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtHi = new JTextField();
				HighLowFrame.txtHi.setText("WIN");
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtLo = new JTextField();
				HighLowFrame.txtLo.setText("WIN");
				
				//Tell the player that they are a winner by guessing correctly
				//Ask the player if they want to play again
				//Store this YES or NO input into a variable called playAgain
				playAgain = JOptionPane.showConfirmDialog(null, "PLAYER ONE WINS!\nGuess Number " + gNum + "\nmatched \n"
																+ "Mystery Number " + mNum + "\n\n"
																+ "PLAY AGAIN?\n\n"
																+ "Click YES to play again.\nClick NO to return to\nthe game menu.\n",
																"Hi-Lo Guess-So | Game Play",JOptionPane.YES_NO_OPTION);
				//Stop game play if the player selects NO
				if(playAgain == JOptionPane.NO_OPTION){
					if(countOfRounds > 1){
						//Scenario #1: By the end of Round 3 countOfRounds=3
						//If i=1 then it will increment two times until it gets to i=3
						//For Loop is to capture all of the guesses for each round
						//Loop goes from one all the way up to the maximum number of rounds played
						for(int i=1;i<countOfRounds;i++){
							
							//Create delimiters to separate the stored guesses for each round in the Array List
							//Start marker is the letter r and the current loop number
							//For example : First time through the loop will be r1
							String splitterStart = "r" + i;
							//End marker is the letter r and the current loop number plus one
							//For example : First time through the loop will be r2
							String splitterEnd = "r" + (i+1);
							//Use the markers to get the guessListCopy string index numbers
							startIndex = guessListCopy.indexOf(splitterStart);
							endIndex = guessListCopy.indexOf(splitterEnd);
							
							//Markers double so this must be taken out when getting only the guess numbers in brackets
							if(i<=2){
								//If loop is less than or equal to 2 then set x to loop value times 2
								x = i*2;
							}else if(i>2){
								//If loop is greater than 2 then set x to 4 since the maximum markers are only four characters
								x = 4;
							}else{
								//something else
							}
							
							//Use substring method and startIndex marker and endIndex marker to split guessListCopy
							//Then store the split values in a string variable called eachRound
							eachRound = guessListCopy.substring(startIndex + x, endIndex);
							//Add eachRound value and message to string variable called Summary
							//This will keep adding until the loop is done
							Summary += " + Round " + i + " = " + eachRound + "\r\n";
						}
						//Add guessList value and message to capture the final round guess numbers
						Summary += " + Round " + countOfRounds + " = " + guessList + "\r\n";
					}
					else if(countOfRounds == 1){
						//Add guessList value and message to Summary to capture the final round of guesses
						Summary = " + Round " + countOfRounds + " = " + guessList + "\r\n";
						//summaryRoundOne.clear();
					}
					//Use return to stop code run
					return gNum;
				}
				//Continue game play if the player selects YES
				else if(playAgain == JOptionPane.YES_OPTION){
					//Get a new Mystery Number by calling the randomEngine method
					mNum = (int) randomEngine();
					
					//To mark start of new round add a zero to the guessList Array List
					//[null]r1[1,2,3]r2r2[4,5,6,7,]r3r3[8,9,10]
					String splitPointStart = "r" + countOfRounds;
					guessListCopy += splitPointStart;
					
					//Increment count number of rounds by one
					countOfRounds++;
					//Copy values in guessList Array List to string variable called guessListCopy
					guessListCopy += guessList.toString();
					
					//To mark end of new round add a zero to the guessList Array List
					//[null]r1[1,2,3]r2r2[4,5,6,7,]r3r3[8,9,10]
					String splitPointEnd = "r" + countOfRounds;
					guessListCopy += splitPointEnd;
					
					//Get the index of the markers in the Array List
					startIndex = guessListCopy.indexOf("r1");
				    endIndex = guessListCopy.indexOf("r2");
				    
					//Since playAgain YES selected a new round starts so clear out all values in guessList
				    guessList.clear();
				    summaryRoundOne.clear();
				    //Set the count for number of guesses back to zero
				    countOfGuesses = 0;
				}
			}
			//Outcome #2: guessNumber is greater than the mysteryNumber
			else if(gNum > mNum){
				
				if(gNum >= 10 && gNum <= 90){
					gNumX = gNum;
				}else if(gNum < 10){
					gNumX = gNum+9;
				}else if(gNum > 90){
					gNumX = gNum-9;
				}
				
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtHi = new JTextField();
				HighLowFrame.txtHi.setText("HI");
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtLo = new JTextField();
				HighLowFrame.txtLo.setText("HI");
				
				//Tell player that the guess number is too high
				JOptionPane.showMessageDialog(null, "Guess Number [ " + gNum + " ]\n is high compared to\nMystery Number [ X ]\n\n"
													+ "GO LOWER!\nTry [ " + (gNum-1) + " or " + (gNumX-10) + " or 1 ] : ",
													"Hi-Lo Guess-So | Too High",JOptionPane.YES_NO_OPTION);
			}
			//Outcome #3: guessNumber is lower than the mysteryNumber
			else if(gNum < mNum){
				
				if(gNum >= 10 && gNum <= 90){
					gNumX = gNum;
				}else if(gNum < 10){
					gNumX = gNum+9;
				}else if(gNum > 90){
					gNumX = gNum-9;
				}
				
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtLo = new JTextField();
				HighLowFrame.txtLo.setText("LO");
				//Instantiate a new HighLow object from the HighLowFrame class called h
				JTextField txtHi = new JTextField();
				HighLowFrame.txtHi.setText("LO");
				
				//Tell player that the guess number is too low
				JOptionPane.showMessageDialog(null, "Guess Number [ " + gNum + " ]\n is low compared to\nMystery Number [ X ]\n\n"
													+ "GO HIGHER!\nTry [ " + (gNum+1) + " or " + (gNumX+10) + " or 100 ] : ",
													"Hi-Lo Guess-So | Too Low",JOptionPane.YES_NO_OPTION);
			}
			//Outcome #4: guessNumber is determined to be greater or lower than the mysteryNumber
			//Player needs to enter the next guess
			
			//Show first input to capture error
			//Ask player for input to enter another Guess Number
			//Store the next Guess Number in variable called gNextString
			gNextString = JOptionPane.showInputDialog("Round " + countOfRounds + "\n"
														+ "------------------------------------------------\n"
														+ "Enter another Guess Number:\n"
														+ "------------------------------------------------\n"
														+ "Click OK to submit Guess Number\n"
														+ "Click CANCEL to end Game Play\n\n");
				//End game play if the Cancel button is selected
				if(gNextString == null){
					JOptionPane.showMessageDialog(null,"GAME PLAY TERMINATED!\nGuess Number was not captured.");
					return mNum;
				}
				validateInput(gNextString);
				HighLowValidator.getGo(gNextString);
				
				//While Loop #2 : Inner Loop
				//If input error captured then show second input message
				while(HighLowValidator.getError().length() != 0){
					//Ask player for input to enter another Guess Number
					//Store the next Guess Number in variable called gNextString
					gNextString = JOptionPane.showInputDialog("Round " + countOfRounds + "\nINVALID INPUT!\n\nEnter another Guess Number:\n"
																+ "Click OK to submit Guess Number\nOR\n"
																+ "Click CANCEL to end Game Play");
						//Player input when CANCEL is selected then show message and stop Game Play	
						if(gNextString == null){
							JOptionPane.showMessageDialog(null,"GAME PLAY TERMINATED!\nGuess Number was not captured.");
							return mNum;
						}
					validateInput(gNextString);
					HighLowValidator.getGo(gNextString);
				}//End of While Loop 2
			
				//If NO ERROR captured then run this code block
				if(HighLowValidator.getError().length() == 0){
					try{
						//Convert the gNextString value to integer then store it in variable called gNext
						gNext = Integer.parseInt(gNextString);
					}catch(NumberFormatException e){
						//System will Throw exception!
					}
						
					//Store next set of Guess Numbers 2nd,3rd,4th,etc from this class to the Array List
					guessList.add(gNextString);
					//Store next set of Guess Numbers taken from input stored in variable called gNextString to gNext
					summaryRoundOne = guessList;
					//Convert gNext input String to Integer then assign the new input value to a variable called gNum
					gNum = gNext;
					//Count of guesses for each round
					//Increment the number of guesses by one
					countOfGuesses++;
					//Total count of guesses of all rounds
					gCount++;
				}
				
		}//End of While Loop 1
		
		return countOfGuesses;

	}//End of gamePlay method

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Method to clear all Array Lists upon Reset from HighLowFrame class
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void resetClear(){
		guessList.clear();
		Summary = null;
		Summary = "";
		guessListCopy = null;
		guessListCopy = "";
		countOfGuesses = 1;
		countOfRounds = 1;
		roundCountString = "";
		gCount = 1;
	}//End of summaryResult method
		
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the values of the summary results
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public String summaryResult(){
		return Summary;
	}//End of summaryResult method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the values of the summary results for only round one
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public ArrayList<String> summaryRoundOne(){
		return summaryRoundOne;
	}//End of summaryRoundOne method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get value stored in guessListCopy copied to a local variable called guessListCopyLocal
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public String getListCopy(){
		String guessListCopyLocal = guessListCopy;
		//Return the count value
		return guessListCopyLocal;
	}//End of getListCopy method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get number of rounds played
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int getRoundCount(){
		//Return the count value
		return countOfRounds;
	}//End of getRoundCount method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the input value for next guess
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int getNextGuess(){
		//Pass value of countOfGuesses from gamePlay method to a variable called count
		gNum = gNext;
		//Return the count value
		return gNum;
	}//End of getNextGuess method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the Mystery Number
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int getMysteryNumber(){
		//Return the count value
		return mNum;
	}//End of getMysteryNumber method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the number of guesses for all rounds
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int getGuessCountTotal(){
		//Pass value of gCount from gamePlay method to a variable called guessCountRoundTotal
		int guessCountRoundTotal = gCount;
		//Return the count value
		return guessCountRoundTotal;
	}//End of getGuessCountTotal method
		
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Getter method to get the number of guesses
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int getPlayCount(){
		//Return the count value
		return countOfGuesses;
	}//End of getPlayCount method
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Validate txtGo text field and inputs
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public int validateInput(String gInt){
		String gNum = gInt;
		int rCount = 1;
		
		//Clear errors by calling the clearError method from the Validator class
		//	before we start processing we clear any errors
		HighLowValidator.clearError();
		
		//Take value entered for input then pass it through the validator then assign it to a variable called gInt
		HighLowValidator.getGo(gNum);
		
		//IF Loop that checks if there are any errors
		if(HighLowValidator.getError().length() != 0){
			//Show error message
			JOptionPane.showMessageDialog(null,HighLowValidator.getError());
			//Clear errors by calling the clearError method from the Validator class
			HighLowValidator.clearError();
			//HighLowFrame.txtGo.requestFocus();
		}
		//Return the count value
		return rCount;
	}//End of validateInput method

}//End of Game class