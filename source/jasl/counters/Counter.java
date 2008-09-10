// ************************************************************************** //
// Counter.java - This interface is part of the <B>counters</B> package, which       //
//                contains the class definitions and implementations for      //
//                objects used to represent the virtual playing pieces in     //
//                jASL.                                                       //
//                                                                            //
//                NOTE: This program is based on Advanced Squad Leader, which //
//                      was created by The Avalon Hill Game Company, and      //
//                      lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                   //
//                                                                            //
// Written By: Craig R. Campbell  -  September 2001                           //
//                                                                            //
// $Id: Counter.java,v 1.11 2008/09/10 04:20:54 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.io.*;

/**
 * This interface is used to define public constants for the classes in the
 * counters package.
 *
 * @version 1.11
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Counter.html">Source code</A>
 */

public interface Counter extends Serializable
{
	// Public symbolic constants

	// These constants are available to all programs and can be used to specify
	// parameters for new objects or to build menus.

	// The following strings are used to build the error messages (see below)
	// that appear when an exception is thrown. Each subclass will have a
	// CLASS_NAME constant defined in it for this purpose as well.

	// The name of the method throwing the exception. These constants are
	// defined as necessary within each subclass.

	/**
	 * Indicates that a message originated in the constructor of a specific
	 * class : <B>constructor</B>
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String CONSTRUCTOR = "constructor";

	// This is a separator used in messages associated with illegal argument
	// exceptions where there is a conflict between two distinct values (see
	// checking of unitType in the <A HREF="Fighting.html">Fighting</A> constructor for an example).

	/**
	 * Separates multiple invalid parameters in error messages for exceptions :
	 * <B> and </B>
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String AND_SEPARATOR = " and ";

	// Error messages.

	/**
	 * Indicates that a null parameter was received by a constructor or method :
	 * <B>Null parameter received.</B>
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String NULL_PARAMETER_MSG = "Null parameter received.";

	/**
	 * Indicates that a zero length parameter was received by a constructor or
	 * method :
	 * <B>Invalid parameter received (zero length).</B>
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String ZERO_LENGTH_PARAMETER_MSG =
		"Invalid parameter received (zero length).";

	/**
	 * Indicates that one or more invalid parameters was received by a
	 * constructor or method :
	 * <B>Invalid parameter(s) received : </B>
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String INVALID_PARAMETER_MSG =
		"Invalid parameter(s) received : ";

	/**
	 * A label used in the toString() method to build exception messages :
	 * <B>(toString) - </B>
	 * It is appended to the class name in each toString() method definition.
	 *
	 * @see Unit#buildErrorMessage
	 */

	public static final String TO_STRING_LABEL = "(toString) - ";

	// The following strings are provided primarily for use in the toString()
	// method.

	/**
	 * Indicates the current value of a boolean data member : <B>Yes</B>
	 *
	 * @see #toString
	 */

	public static final String YES = "Yes";

	/**
	 * Indicates the current value of a boolean data member : <B>No</B>
	 *
	 * @see #toString
	 */

	public static final String NO = "No";

	// Access methods

	/**
	 * Display the value of each of the private data members that describe an
	 * instance of a class that implements this interface. Each value should be
	 * preceded by a label defined in this interface. There should be no more
	 * than two values, including labels, in each line of output.
	 *
	 * @return a tabular <CODE>String</CODE>, 80 characters wide, with one or more lines.
	 */

	public abstract String toString();
}
