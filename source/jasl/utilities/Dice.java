// ************************************************************************** //
// Dice.java - This class is used to simulate the rolling of two standard     //
//             dice. It is intended to support the classes within the         //
//             Counters package and thus does not fall within the heirarchy   ////             of classes derived from Unit.                                  //
//                                                                            //
//             All of the calculations (die rolls) are performed              //
//             automatically when an instance of the Dice class is            //
//             instantiated. No interpretation of the result is performed by  //
//             this class. The calling program may only retrieve the results  //
//             of the rolling of the white and colored dice individually, as  //
//             well as the combined result.                                   //
//                                                                            //
// Written By: Craig R. Campbell  -  September 1999                           //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/utilities/Dice.java,v 1.2 1999/12/31 07:15:38 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Dice class - A utility class used by other classes to provide the          //
//              simulated result(s) of rolling one or two dice.               //
// ************************************************************************** //

public class Dice
{
	// Private symbolic constants

	// These constants are used to define the minimum and maximum possible
	// result values for a single die. The MAX_ROLL constant is also used in the
	// rollIt method to set the maximum random value.

	private static final int MIN_ROLL = 1;
	private static final int MAX_ROLL = 6;

	// This constant is used to initialize member and local variables.

	private static final int INITIAL_VALUE = 0;

	// Private data members

	// This variable contains the result of rolling the white die.

	private int whiteDieValue;

	// This variable contains the result of rolling the colored die.

	private int coloredDieValue;

	// This variable contains the combined result of rolling the two dice.

	private int combinedResult;

	// This variable is used to store the result of "rolling" a die while it is
	// checked to verify that it falls within the expected range.

	private int tmpResult;

	// The following string is used as a message for any exceptions that may
	// result during the simulated roll of the dice.

	private static final String badResultError = 
	    "Error: Dice(constructor) - Invalid result : ";

	// Constructor

	// Default constructor.

	public Dice()
		throws IllegalStateException
	{
		// Initialize the data members.

		whiteDieValue   = INITIAL_VALUE;
		coloredDieValue = INITIAL_VALUE;
		combinedResult  = INITIAL_VALUE;
		tmpResult       = INITIAL_VALUE;

		// Roll the dice. If the method fails, assume that a failure has
		// occurred in calculating one of the die rolls (the result did not fall
		// within the expected range). If this happens, throw the exception.

		if (!(rollEm()))
		{
			throw new IllegalStateException(badResultError + tmpResult);
		}
	}

	// Private methods

	// rollEm - A function to roll each die, verify that a valid result occurs,
	//          and copy the value to the appropriate member variable. If an
	//          invalid result occurs, the method returns false, which causes
	//          the constructor to throw an exception.

	private boolean rollEm()
	{
		// Roll the white die.

		tmpResult = rollIt();

		// Check the result and return false if it does not fall within the
		// expected range.

		if ((tmpResult < MIN_ROLL) || (tmpResult > MAX_ROLL))
		{
			return(false);
		}

		// Copy the result to the whiteDieValue member variable.

		whiteDieValue = tmpResult;

		// Reset the tmpResult variable.

		tmpResult = INITIAL_VALUE;

		// Roll the colored die.

		tmpResult = rollIt();

		// Check the result and return false if it does not fall within the
		// expected range.

		if ((tmpResult < MIN_ROLL) || (tmpResult > MAX_ROLL))
		{
			return(false);
		}

		// Copy the result to the coloredDieValue member variable.

		coloredDieValue = tmpResult;

		// Sum the white and colored dice rolls and copy the value to the
		// combinedResult member variable.

		combinedResult = whiteDieValue + coloredDieValue;

		// Return true to indicate that all of the die rolls were completed
		// successfully.

		return(true);
	}
 
	// rollIt - A function to roll a single die.

	private int rollIt()
	{
		return((int)Math.ceil(Math.random()*MAX_ROLL));
	}

	// Public access methods

	// getWhiteDieValue - A function to return the value of the whiteDieValue
	//                    member variable to the calling program.

	public int getWhiteDieValue()
	{
		return(whiteDieValue);
	}

	// getColoredDieValue - A function to return the value of the
	//                      coloredDieValue member variable to the calling
	//                      program.

	public int getColoredDieValue()
	{
		return(coloredDieValue);
	}

	// getCombinedResult - A function to return the value of the combinedResult
	//                     member variable to the calling program.

	public int getCombinedResult()
	{
		return(combinedResult);
	}

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging.

	public void showValues()
	{
		// Display header.

		System.out.println("Dice instance values:");
		System.out.println("---------------------");

		// Display data stored in this class.

		System.out.print("White Die : " + getWhiteDieValue());
		System.out.print("\tColored Die : " + getColoredDieValue());
		System.out.println("\tCombined Result : " + getCombinedResult());
	}
}
