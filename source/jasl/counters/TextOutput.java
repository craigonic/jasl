// ************************************************************************** //
// TextOutput.java - This interface is part of the <B>counters</B> package, which    //
//                   contains the class definitions and implementations for   //
//                   objects used to represent the virtual playing pieces in  //
//                   jASL.                                                    //
//                                                                            //
//                   NOTE: This program is based on Advanced Squad Leader,    //
//                         which was created by The Avalon Hill Game Company, //
//                         and lives on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.            //
//                                                                            //
// Written By: Craig R. Campbell  -  May 2009                                 //
//                                                                            //
// $Id: TextOutput.java,v 1.1 2009/11/01 04:56:06 craig Exp $
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and method used to
 * generate a text representation of the state of each of the classes in the
 * <B>counters</B> package.
 *
 * @version 1.1
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/TextOutput.html">Source code</A>
 */

public interface TextOutput
{
	// Public symbolic constants

	// These items are intended to be used as a parameter to the
	// formatTextString() method of the <A HREF="../utilities/Messages.html">Messages</A> class.

	/**
	 * The width of the first column of output from the toString() method in
	 * classes that implement this interface : <B>20</B>.
	 */

	public static final int FIRST_COLUMN_LABEL_WIDTH = 20;

	/**
	 * The width of the second column of output from the toString() method
	 * in classes that implement this interface : <B>17</B>.
	 */

	public static final int SECOND_COLUMN_VALUE_WIDTH = 17;

	/**
	 * The width of the third column of output from the toString() method in
	 * classes that implement this interface : <B>26</B>.
	 */

	public static final int THIRD_COLUMN_LABEL_WIDTH = 26;

	/**
	 * The width of the fourth column of output from the toString() method
	 * in classes that implement this interface : <B>17</B>.
	 */

	public static final int FOURTH_COLUMN_VALUE_WIDTH = 17;

	// Access methods

	/**
	 * Display the value of each of the private data members that describe
	 * an instance of a class that implements this interface. There should
	 * be no more than two values, including labels, in each line of output.
	 * The width of each column should be the corresponding value above.
	 *
	 * @return a tabular <CODE>String</CODE>, 80 characters wide, with one or more lines.
	 */

	public abstract String toString();
}
