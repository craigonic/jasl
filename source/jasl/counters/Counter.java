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
// $Id: Counter.java,v 1.12 2009/05/23 05:32:34 craig Exp $
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define public constants for the classes in the
 * counters package.
 *
 * @version 1.12
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Counter.html">Source code</A>
 */

public interface Counter
{
	// Public symbolic constants

	/**
	 * The width of the first column of output from the toString() method in
	 * classes that implement this interface : <B>20</B>.
	 */

	public static final int FIRST_COLUMN_LABEL_WIDTH = 20;

	/**
	 * The width of the second column of output from the toString() method in
	 * classes that implement this interface : <B>17</B>.
	 */

	public static final int SECOND_COLUMN_VALUE_WIDTH = 17;

	/**
	 * The width of the third column of output from the toString() method in
	 * classes that implement this interface : <B>26</B>.
	 */

	public static final int THIRD_COLUMN_LABEL_WIDTH = 26;

	/**
	 * The width of the fourth column of output from the toString() method in
	 * classes that implement this interface : <B>17</B>.
	 */

	public static final int FOURTH_COLUMN_VALUE_WIDTH = 17;

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
