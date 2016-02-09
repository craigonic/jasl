// ************************************************************************** //
// Position.java - This class is a member of the <B>ui.data</B> package, which       //
//                 contains the class definitions and implementations for     //
//                 objects used to store and manage the state of an instance  //
//                 of jASL.                                                   //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  January 2016                             //
// ************************************************************************** //

package jasl.ui.data;

import java.io.Serializable;

import jasl.utilities.Messages;

/**
 * This class is used to define the attributes of a specific position ("hex") on
 * the virtual playing board. It includes a label, X and Y coordinates, and
 * other data.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Position.html">Source code</A>
 */

public final class Position implements Serializable
{
	// Symbolic constants

	/**
	 * Provides a label for the identifier associated with a position :
	 * <B>Label</B>
	 */

	public static final String POSITION_LABEL = "Label";

	/**
	 * Provides a label for the x-axis coordinate associated with a position
	 * : <B>X Position</B>
	 */

	public static final String X_POSITION_LABEL = "X Position";

	/**
	 * Provides a label for the y-axis coordinate associated with a position
	 * : <B>Y Position</B>
	 */

	public static final String Y_POSITION_LABEL = "Y Position";

	// This constant is used as part of the error messages (see below) that
	// are generated when an exception is thrown.

	private static final String CLASS_NAME = Position.class.getSimpleName();

	// Private data members

	// This variable is used to store the label associated with the position
	// an instance of this class represents (e.g. "1H5"). It (obviously) may
	// not be null or empty.

	private String _label;

	// These variables are used to store just what their name implies. Their
	// intended use is (obviously) to represent coordinates within an image
	// file, from Google Maps, etc.

	private double _xPosition;
	private double _yPosition;

	// The following strings are used as messages for any exceptions that
	// may be generated by bad data being passed to the constructor.

	private static final String nullPointerError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.NULL_PARAMETER_MSG);

	private static final String zeroLengthArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.ZERO_LENGTH_PARAMETER_MSG);

	// Constructor

	/**
	 * Construct a new <CODE>Position</CODE>.
	 *
	 * @param label the identifier associated with the position.
	 * Example - <B>1H5</B>
	 * @param xPosition the x-axis coordinate associated with the position.
	 * Example - <B>42.7363</B>
	 * @param yPosition the y-axis coordinate associated with the position.
	 * Example - <B>173.9431</B>
	 *
	 * @throws NullPointerException in the case of a null label argument.
	 * @throws IllegalArgumentException in the case of an invalid argument
	 * (e.g. the label argument is empty).
	 */

	public Position(String label,double xPosition,double yPosition)
	{
		// Copy the value of each remaining parameter to the
		// corresponding variable if an exception is not found.

		// Label

		if (null == label)
		{
			throw new NullPointerException(nullPointerError);
		}

		if (0 == label.length())
		{
			throw new IllegalArgumentException(zeroLengthArgumentError);
		}

		_label = label;

		// XY coordinates. These are not expected to be negative, but
		// we leave their application/validity to the user.

		_xPosition = xPosition;
		_yPosition = yPosition;
	}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe
	 * the current instance.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toText()
	{
		// Create a buffer to store the string to be returned.

		StringBuffer returnString = new StringBuffer();

		// Add the information describing the data stored in this class
		// instance.

		// Position label

		returnString.append(Messages.formatTextString(POSITION_LABEL,
		                                              20,//FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(toString(),
		                                              17,//SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		returnString.append(POSITION_LABEL + ":\t" + toString());

		// X axis position value

		returnString.append(Messages.formatTextString(X_POSITION_LABEL,
		                                              20,//FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Double.toString(xPosition()),
		                                              17,//SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Y axis position value

		returnString.append(Messages.formatTextString(Y_POSITION_LABEL,
		                                              26,//THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Double.toString(yPosition()),
		                                              17,//FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the label associated with a position.
	 *
	 * @return a <CODE>String</CODE> specifying the position label.
	 */

	public String toString()
	{
		return _label;
	}

	/**
	 * Display the JSON representation each of the private data members that
	 * describe the current instance.
	 *
	 * @return a <CODE>String</CODE> containing the JSON data.
	 */

	public String toJSON()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the values that define the header (since
		// this is the top level of the class hierarchy).

		StringBuffer returnString = new StringBuffer("{\n");

		// Add the information describing the data stored in this class
		// instance.

		String INDENT = " ";

		returnString.append(INDENT +
		                    "\"" + POSITION_LABEL + "\":\"" + toString() + "\"");
		returnString.append(INDENT +
		                    "\"" + X_POSITION_LABEL + "\":" + xPosition());
		returnString.append(INDENT +
		                    "\"" + Y_POSITION_LABEL + "\":" + yPosition());

		returnString.append("\n}");

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the x-axis component of a position.
	 *
	 * @return a <CODE>double</CODE> specifying the X coordinate value.
	 */

	public final double xPosition()
	{
		return _xPosition;
	}

	/**
	 * Return the y-axis component of a position.
	 *
	 * @return a <CODE>double</CODE> specifying the Y coordinate value.
	 */

	public final double yPosition()
	{
		return _yPosition;
	}
}
