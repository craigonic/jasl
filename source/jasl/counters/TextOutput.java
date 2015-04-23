// ************************************************************************** //
// TextOutput.java - This interface is part of the <B>counters</B> package, which    //
//                   contains the class definitions and implementations for   //
//                   objects used to represent the virtual playing pieces in  //
//                   jASL.                                                    //
//                                                                            //
//                   NOTE: This program is based on Advanced Squad Leader,    //
//                         which was created by The Avalon Hill Game Company, //
//                         and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.            //
//                                                                            //
// Written By: Craig R. Campbell  -  May 2009                                 //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and methods used to
 * generate a formatted text representation of an instance of one of the public
 * classes in the <B>counters</B> package.
 *
 * @version 3.0
 * @author Copyright (C) 2009-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/TextOutput.html">Source code</A>
 */

public interface TextOutput
{
	// Public symbolic constants

	// These items are intended to be used as a parameter to the
	// formatTextString() method of the <A HREF="../utilities/Messages.html">Messages</A> class.

	/**
	 * The width of the first column of output from the toText() method in
	 * classes that implement this interface : <B>20</B>.
	 */

	public static final int FIRST_COLUMN_LABEL_WIDTH = 20;

	/**
	 * The width of the second column of output from the toText() method
	 * in classes that implement this interface : <B>17</B>.
	 */

	public static final int SECOND_COLUMN_VALUE_WIDTH = 17;

	/**
	 * The width of the third column of output from the toText() method in
	 * classes that implement this interface : <B>26</B>.
	 */

	public static final int THIRD_COLUMN_LABEL_WIDTH = 26;

	/**
	 * The width of the fourth column of output from the toText() method
	 * in classes that implement this interface : <B>17</B>.
	 */

	public static final int FOURTH_COLUMN_VALUE_WIDTH = 17;

	// These items are intended to be used in implementations of the
	// toJSON() method (and any helper method(s) called from them).

	/**
	 * The text designating the beginning and end of a key or a string
	 * value in JSON output.
	 */

	public static final String JSON_DOUBLE_QUOTE = "\"";

	/**
	 * The text separating a key and a non-string (number or boolean) value
	 * in JSON output.
	 */

	public static final String JSON_KEY_OTHER_VALUE_SEPARATOR =
		JSON_DOUBLE_QUOTE + ":";

	/**
	 * The text separating a key and a string value in JSON output.
	 */

	public static final String JSON_KEY_STRING_VALUE_SEPARATOR =
		JSON_KEY_OTHER_VALUE_SEPARATOR + JSON_DOUBLE_QUOTE;

	/**
	 * The text indicating the beginning of an object container in JSON
	 * output.
	 *
	 * Note that the string includes a CR-LF ('\n') after the curly brace.
	 */

	public static final String JSON_OBJECT_START = "{\n";

	/**
	 * The text separating an object entry from the next one in JSON output.
	 *
	 * Note that the string includes a CR-LF ('\n') after the comma.
	 */

	public static final String JSON_OBJECT_SEPARATOR = ",\n";

	/**
	 * The text indicating the end of an object container in JSON output.
	 *
	 * Note that the string includes a CR-LF ('\n') before the curly brace.
	 */

	public static final String JSON_OBJECT_END = "\n}";

	// Access methods

	/**
	 * Display a plain text representation of an instance of a class that
	 * implements this interface.
	 *
	 * There should be no more than two values, including labels, in each
	 * line of output. The width of each column should be the corresponding
	 * value above.
	 *
	 * @return a tabular <CODE>String</CODE>, 80 characters wide, with one or more lines.
	 */

	public abstract String toText();

	/**
	 * Display a JSON representation of an instance of a class that
	 * implements this interface.
	 *
	 * @return a <CODE>String</CODE> containing the JSON data.
	 */

	public abstract String toJSON();
}
