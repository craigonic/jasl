// ************************************************************************** //
// TextOutput.java - This interface is part of the <B>counters</B> package, which    //
//                   contains the class definitions and implementations for   //
//                   objects used to represent the virtual playing pieces in  //
//                   jASL.                                                    //
//                                                                            //
//                   NOTE: This program is based on Advanced Squad Leader,    //
//                         which was created by The Avalon Hill Game Company, //
//                         and lives on at Multi-Man Publishing.              //
//                                                                            //
// Written By: Craig R. Campbell  -  May 2009                                 //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and methods used to
 * generate a formatted text representation of an instance of one of the public
 * classes in the <B>counters</B> package.
 *
 * @version 4.0
 * @author Copyright (C) 2009-2016 Craig R. Campbell (craigonic@gmail.com)
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
}
