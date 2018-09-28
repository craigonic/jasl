// ************************************************************************** //
// Dice.java - This class is used to simulate the rolling of two standard     //
//             dice. It is intended to support the classes more directly      //
//             associated with the game itself.                               //
//                                                                            //
//             All of the calculations (die rolls) are performed              //
//             automatically when an instance of the Dice class is            //
//             instantiated. No interpretation of the result is performed by  //
//             this class. The calling program may only retrieve the results  //
//             of the rolling of the white and colored dice individually, as  //
//             well as the combined result.                                   //
//                                                                            //
// Written By: Craig R. Campbell  -  September 1999                           //
// ************************************************************************** //

package jasl.utilities;

/**
 * This is a utility class used to provide the simulated result(s) of rolling
 * one or two six sided dice.
 *
 * @version 3.1
 * @author Copyright (C) 1999-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/utilities/Dice.html">Source code</A>
 */

public final class Dice
{
	// Private symbolic constants

	// These constants are used to define the minimum and maximum possible
	// result values for a single die. The MAX_ROLL constant is also used in
	// the constructor to set the maximum random value.

	private static final int MIN_ROLL = 1;
	private static final int MAX_ROLL = 6;

	// Private data members

	// This variable contains the result of rolling the white die.

	private int _whiteDieValue;

	// This variable contains the result of rolling the colored die.

	private int _coloredDieValue;

	// This variable contains the combined result of rolling the two dice.

	private int _combinedResult;

	// Constructor

	/**
	 * Construct a new <CODE>Dice</CODE> instance.
	 * <P>
	 * When the object is created, both dice are "rolled" automatically.
	 */

	public Dice()
	{
		// Roll the white die.

		int tmpResult = (int)Math.ceil(Math.random() * MAX_ROLL);

		// Check the result and assert if it does not fall within the
		// expected range.

		assert((tmpResult >= MIN_ROLL) && (tmpResult <= MAX_ROLL));

		// Copy the result to the _whiteDieValue member variable.

		_whiteDieValue = tmpResult;

		// Roll the colored die.

		tmpResult = (int)Math.ceil(Math.random() * MAX_ROLL);

		// Check the result and assert if it does not fall within the
		// expected range.

		assert((tmpResult >= MIN_ROLL) && (tmpResult <= MAX_ROLL));

		// Copy the result to the _coloredDieValue member variable.

		_coloredDieValue = tmpResult;

		// Sum the white and colored dice rolls and copy the value to
		// the _combinedResult member variable.

		_combinedResult = _whiteDieValue + _coloredDieValue;
	}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe
	 * the current instance.
	 * <P>
	 * Each value is preceded by a label.
	 *
	 * @return a <CODE>String</CODE> describing the result of the last roll of the dice.
	 */

	public String toText()
	{
		// Define local constants.

		String WHITE_DIE_LABEL       = "White Die";
		String COLORED_DIE_LABEL     = "Colored Die";
		String COMBINED_RESULT_LABEL = "Combined Result";

		int    LABEL_WIDTH           = 20;
		int    VALUE_WIDTH           =  5;

		// Create a buffer to store the string to be returned,
		// initializing it with the values that define the header.

		StringBuffer returnString = new StringBuffer();

		// Add the information describing the data stored in this class
		// instance.

		// White Die

		returnString.append(Messages.formatTextString(WHITE_DIE_LABEL,
		                    LABEL_WIDTH,true,false));

		returnString.append(Messages.formatTextString(Integer.toString(whiteDieValue()),
		                    VALUE_WIDTH,false,false));

		// Colored Die

		returnString.append(Messages.formatTextString(COLORED_DIE_LABEL,
		                    LABEL_WIDTH,true,false));

		returnString.append(Messages.formatTextString(Integer.toString(coloredDieValue()),
		                    VALUE_WIDTH,false,false));

		// Combined Result

		returnString.append(Messages.formatTextString(COMBINED_RESULT_LABEL,
		                    LABEL_WIDTH,true,false));

		returnString.append(Messages.formatTextString(Integer.toString(combinedResult()),
		                    VALUE_WIDTH,false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the result of rolling the white die.
	 *
	 * @return an <CODE>int</CODE> specifying the white die value.
	 */

	public int whiteDieValue()
	{
		return _whiteDieValue;
	}

	/**
	 * Return the result of rolling the colored die.
	 *
	 * @return an <CODE>int</CODE> specifying the colored die value.
	 */

	public int coloredDieValue()
	{
		return _coloredDieValue;
	}

	/**
	 * Return the result of combining the values of the two dice.
	 *
	 * @return an <CODE>int</CODE> specifying the sum of the white and colored die
	 * values.
	 */

	public int combinedResult()
	{
		return _combinedResult;
	}
}
