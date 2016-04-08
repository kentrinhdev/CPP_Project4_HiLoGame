package highlow;

public class HighLowValidator {
	//TODO - CODE THE VALIDATOR
	//add the method that will validate all the input
	//collected from the form
	
	//static variable to hold any error that occurs
	private static String errorMessage = "";
		
	//-------------------------------------------------------------------------------------------------
	//	Validate the Go Guess text field
	//-------------------------------------------------------------------------------------------------
	private static boolean isValidGo(String aGo){
		boolean result = false;

		try{
			int toInt = Integer.parseInt(aGo);

			if(aGo.matches("\\d+") && aGo != null && aGo.length() <= 3 && toInt >= 1 && toInt < 101){
				result = true;
			}
			} catch(NumberFormatException e) {
				return result;
			}
			return result;
		}
	//-------------------------------------------------------------------------------------------------
	//	Create Getter method for isValidGo validator message
	//-------------------------------------------------------------------------------------------------
	public static String getGo(String aGo){
		if(isValidGo(aGo) == false){
			errorMessage += "Guess Number entered: [" + aGo + "] is invalid! \nRe-Enter a number between 1 and 100: " + "       \n";
		}
		return aGo;
	}
	//-------------------------------------------------------------------------------------------------
	//	Create Getter method for error message
	//-------------------------------------------------------------------------------------------------
	//method to Get and return the error message	
	public static String getError(){
		return errorMessage;
	}
	//-------------------------------------------------------------------------------------------------
	//	Create method to clear out error messages
	//-------------------------------------------------------------------------------------------------
	//method to clear the error message
	public static void clearError(){
		errorMessage = "";
	}

}//End of HighLowValidator class