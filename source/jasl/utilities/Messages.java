// ************************************************************************** //
// Messages.java - This class is part of the <B>utilities</B> package, which         //
//                 contains the definitions of objects used to support the    //
//                 classes more directly associated with the game itself.     //
//                                                                            //
// Written By: Craig R. Campbell  -  January 2009                             //
// ************************************************************************** //

package jasl.utilities;

/**
 * This class provides static methods used to generate messages for use in
 * exception and text output.
 *
 * @version 1.4
 * @author Copyright (C) 2009-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/utilities/Messages.html">Source code</A>
 */

public final class Messages
{
	// Public symbolic constants

	// The following strings are used to assemble the text that appears when
	// an exception is thrown and in other common messages. Their intended
	// purpose is to serve as arguments to buildErrorMessage().

	/**
	 * Indicates that a message originated in the constructor of a specific
	 * class : <B>constructor</B>
	 */

	public static final String CONSTRUCTOR =
		"constructor";

	// This separator may be used in messages associated with illegal
	// argument exceptions where there is a conflict between two distinct
	// values.

	/**
	 * Separates multiple invalid parameters in error messages for
	 * exceptions :
	 * <B> and </B>
	 */

	public static final String AND_SEPARATOR =
		" and ";

	// Error messages associated with common exceptions.

	/**
	 * Indicates that a null parameter was received by a constructor or
	 * method :
	 * <B>Null parameter received.</B>
	 */

	public static final String NULL_PARAMETER_MSG =
		"Null parameter received.";

	/**
	 * Indicates that a zero length parameter was received by a constructor
	 * or method :
	 * <B>Invalid parameter received (zero length).</B>
	 */

	public static final String ZERO_LENGTH_PARAMETER_MSG =
		"Invalid parameter received (zero length).";

	/**
	 * Indicates that one or more invalid parameters was received by a
	 * constructor or method :
	 * <B>Invalid parameter(s) received : </B>
	 */

	public static final String INVALID_PARAMETER_MSG =
		"Invalid parameter(s) received : ";

	// Private symbolic constants

	private static final String CLASS_NAME = "Messages";

	// Public static methods

	/**
	 * Build a descriptive error message to help pinpoint the location where
	 * an exception occurred.
	 *
	 * @param className the name of the class where the error occurred.
	 * Example - "Unit"
	 * @param methodName the name of the method where the error occurred.
	 * Example - "constructor"
	 * @param message the message describing the error.
	 * Example - "Null parameter received."
	 *
	 * @return a <CODE>String</CODE> containing the location in the code where the error
	 * occurred and a descriptive message.
	 * Example - <CODE>Unit(constructor) - Null parameter received.</CODE>
	 */

	public static final String buildErrorMessage(String className,
	                                             String methodName,
	                                             String message)
	{
		// Create a buffer to store the new error message.

		StringBuffer errorString = new StringBuffer();

		// Add the class name specified to the buffer after verifying it.

		if ((className != null) && (className.length() > 0))
		{
			errorString.append(className);
		}

		// After verifying the method name specified, add it to the
		// buffer in a set of parentheses.

		if ((methodName != null) && (methodName.length() > 0))
		{
			errorString.append('(');
			errorString.append(methodName);
			errorString.append(')');
		}

		// Add the message specified to the buffer after verifying it.

		if ((message != null) && (message.length() > 0))
		{
			errorString.append(" - ");
			errorString.append(message);
		}

		// Return the completed string to calling program.

		return (errorString.toString());
	}

	/**
	 * Create a formatted version of an input string by appending spaces as
	 * necessary to make the length equal to a specific value. This function
	 * is used primarily to create the tabular output for the toText()
	 * method. The last character in the returned string will always be a
	 * space.
	 *
	 * @param inputString the string to be formatted.
	 * Example - "Description"
	 * @param columnWidth the width of the returned string.
	 * Example - 15
	 * @param isALabel indicates if the string will be used as a label. If
	 * this flag is set, the second to last character in the returned string
	 * will be set to a ":".
	 * @param addNewLine indicates if a newline (CR-LF) should be added to
	 * the returned string.
	 *
	 * @return a <CODE>String</CODE> formatted to meet the specified parameters.
	 * Example - <CODE>Description  : </CODE>
	 *
	 * @throws NullPointerException in the case of a null input string
	 * @throws IllegalArgumentException in the case of a column width that
	 * is less than 2
	 */

	public static final String formatTextString(String inputString,
	                                            int columnWidth,
	                                            boolean isALabel,
	                                            boolean addNewLine)
	{
		// Define local constants.

		String METHOD_NAME        = "formatTextString";
		String NEW_LINE           = "\n";
		String invalidColumnWidth = "Invalid column width : " + columnWidth;

		int    MIN_STRING_LENGTH  = 2;

		char   SPACE              = ' ';
		char   SEPARATOR          = ':';

		// Check the parameters received and throw the appropriate
		// exception if necessary.

		if (inputString == null)
		{
			throw new NullPointerException(buildErrorMessage(CLASS_NAME,
			                                                 METHOD_NAME,
			                                                 NULL_PARAMETER_MSG));
		}

		if (columnWidth < MIN_STRING_LENGTH)
		{
			throw new IllegalArgumentException(buildErrorMessage(CLASS_NAME,
			                                                     METHOD_NAME,
			                                                     invalidColumnWidth));
		}

		// Create a buffer to store the formatted version of the input
		// string.

		StringBuffer formattedTextString = new StringBuffer(inputString);

		// Set the length of the buffer to the specified column width.
		// This will truncate the input string automatically if its
		// length exceeds the column width.

		formattedTextString.setLength(columnWidth);

		// If the length of the input string is less than the column
		// width, append spaces to it until it reaches the desired
		// length.

		for (int i = inputString.length();i < (columnWidth - 1);i++)
		{
			formattedTextString.setCharAt(i,SPACE);
		}

		// If the input string is a label, replace the next to last
		// character with a ":".

		if (isALabel)
		{
			formattedTextString.setCharAt(columnWidth - 2,SEPARATOR);
		}

		// Overwrite the last character in the string with a SPACE. This
		// is to provide separation between this string and any
		// characters that are added to the right of it.

		formattedTextString.setCharAt(columnWidth - 1,SPACE);

		// If the addNewLine is set, add a carriage return to the end of
		// the string.

		if (addNewLine)
		{
			formattedTextString.append(NEW_LINE);
		}

		// Return the completed string to calling program.

		return (formattedTextString.toString());
	}

	// The following methods translate a boolean value into its string
	// equivalent, based on the context that it is used in.

	/**
	 * Return a text representation of the value in the context of a choice
	 * (<B>Yes</B> or <B>No</B>)
	 */

	public static String getChoiceLabel(boolean value)
	{
		return (value) ? "Yes" : "No";
	}

	/**
	 * Return a text representation of the value in the context of a state
	 * (<B>On</B> or <B>Off</B>)
	 */

	public static String getStateLabel(boolean value)
	{
		return (value) ? "On" : "Off";
	}

	/**
	 * Return a text representation of the parameter as a truth value (<B>True</B>
	 * or <B>False</B>)
	 */

	public static String getTruthLabel(boolean value)
	{
		return (value) ? "True" : "False";
	}

	/**
	 * Return a text representation of the value as a number (<B>1</B> or <B>0</B>
	 */

	public static String getNumericLabel(boolean value)
	{
		return (value) ? "1" : "0";
	}
}
