// ************************************************************************** //
// Unit.java - This class is a member of the <B>counters</B> package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Game Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
//                                                                            //
// $Id: Unit.java,v 1.15 2008/11/25 07:44:24 craig Exp $
// ************************************************************************** //

package jasl.counters;

/**
 * This class is used to define the basic components of a counter.
 *
 * @version 1.15
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Unit.html">Source code</A>
 */

public abstract class Unit implements Counter, Description
{
	// Protected symbolic constants

	// These constants are available to all subclasses and are used to define
	// the default return values / settings for the public methods defined in
	// this class (see comment below) or in its subclasses.

	/** <A NAME="_DEFAULT_INT_VALUE_"></A>
	 * Default integer value for initialization and return purposes : <B>0</B>
	 */

	protected static final int DEFAULT_INT_VALUE = 0;

	/** <A NAME="_DEFAULT_FLAG_VALUE_"></A>
	 * Default flag value for initialization and return purposes : <B>false</B>
	 */

	protected static final boolean DEFAULT_FLAG_VALUE = false;

	/** <A NAME="_DEFAULT_STRING_VALUE_"></A>
	 * Default string value for initialization and return purposes : <B>Unknown</B>
	 */

	protected static final String DEFAULT_STRING_VALUE = "Unknown";

	/**
	 * The width of the first column of output from the toString() method in
	 * this class and it's subclasses : <B>20</B>.
	 *
	 * @see #formatTextString
	 * @see #toString
	 */

	protected static final int FIRST_COLUMN_LABEL_WIDTH = 20;

	/**
	 * The width of the second column of output from the toString() method in
	 * this class and it's subclasses : <B>17</B>.
	 *
	 * @see #formatTextString
	 * @see #toString
	 */

	protected static final int SECOND_COLUMN_VALUE_WIDTH = 17;

	/**
	 * The width of the third column of output from the toString() method in
	 * this class and it's subclasses : <B>26</B>.
	 *
	 * @see #formatTextString
	 * @see #toString
	 */

	protected static final int THIRD_COLUMN_LABEL_WIDTH = 26;

	/**
	 * The width of the fourth column of output from the toString() method in
	 * this class and it's subclasses : <B>17</B>.
	 *
	 * @see #formatTextString
	 * @see #toString
	 */

	protected static final int FOURTH_COLUMN_VALUE_WIDTH = 17;

	/** <A NAME="_NORMAL_"></A>
	 * Indicates that the unit's current status is <B>Normal</B>
	 *
	 * @see #getStatus
	 */

	protected static final String NORMAL = "Normal";

	// Private symbolic constants

	private static final String CLASS_NAME = "Unit";

	// Private data members

	// This item provides a descriptive name for the derived object of this
	// class. It is set to the enum value associated with name of the class
	// being instantiated (ie. <A HREF="Description.html#_SQUAD_">"Squad"</A>).

	private Descriptions description;

	// Constructors

	// Default constructor.

	/**
	 * Construct a new <CODE>Unit</CODE>. This is an "empty" object and is intended for use
	 * primarily as a reference to one of the derived public object types.
	 *
	 * @see Leader
	 * @see Squad
	 */

	public Unit() {}

	// This constructor is used during the instantiation of classes derived
	// from Unit. The parameter is passed up the chain from the object being
	// created.

	protected Unit(Descriptions description)
	{
		this.description = description;
	}

	// Public static methods

	/**
	 * Build a descriptive error message to help pinpoint the location where an
	 * exception occurred.
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

		// After verifying the method name specified, add it to the buffer in
		// a set of parentheses.

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
	 * necessary to make the length equal to a specific value. This function is
	 * used primarily to create the tabular output for the toString() method.
	 * The last character in the returned string will always be a space.
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
	 * @throws IllegalArgumentException in the case of a zero length input string
	 * @throws IllegalArgumentException in the case of a column width that is
	 * less than 2
	 *
	 * @see #toString
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

		// Check the parameters received and throw the appropriate exception
		// if necessary.

		if (inputString == null)
		{
			throw new NullPointerException(buildErrorMessage(CLASS_NAME,
			                                                 METHOD_NAME,
			                                                 NULL_PARAMETER_MSG));
		}

		if (inputString.length() == 0)
		{
			throw new IllegalArgumentException(buildErrorMessage(CLASS_NAME,
			                                                     METHOD_NAME,
			                                                     ZERO_LENGTH_PARAMETER_MSG));
		}

		if (columnWidth < MIN_STRING_LENGTH)
		{
			throw new IllegalArgumentException(buildErrorMessage(CLASS_NAME,
			                                                     METHOD_NAME,
			                                                     invalidColumnWidth));
		}

		// Create a buffer to store the formatted version of the input string.

		StringBuffer formattedTextString = new StringBuffer(inputString);

		// Set the length of the buffer to the specified column width. This will
		// truncate the input string automatically if its length exceeds the
		// column width.

		formattedTextString.setLength(columnWidth);

		// If the length of the input string is less than the column width,
		// append spaces to it until it reaches the desired length.

		for (int i = inputString.length();i < (columnWidth - 1);i++)
		{
			formattedTextString.setCharAt(i,SPACE);
		}

		// If the input string is a label, replace the next to last character
		// with a ":".

		if (isALabel)
		{
			formattedTextString.setCharAt(columnWidth - 2,SEPARATOR);
		}

		// Overwrite the last character in the string with a SPACE. This is to
		// provide separation between this string and any characters that are
		// added to the right of it.

		formattedTextString.setCharAt(columnWidth - 1,SPACE);

		// If the addNewLine is set, add a carriage return to the end of the
		// string.

		if (addNewLine)
		{
			formattedTextString.append(NEW_LINE);
		}

		// Return the completed string to calling program.

		return (formattedTextString.toString());
	}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe the
	 * current instance. Each value is preceded by a label defined in the
	 * <A HREF="Counter.html">Counter</A> interface. There are no more than two values, including labels,
	 * in each line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toString()
	{
		// Define local constants.

		String METHOD_LABEL = CLASS_NAME + TO_STRING_LABEL;

		// Create a buffer to store the string to be returned, initializing it
		// with the values that define the header (since this is the top level
		// of the class hierarchy).

		StringBuffer returnString = new StringBuffer();

		// Add the information describing the data stored in this class
		// instance.

		try
		{
			returnString.append(formatTextString(DESCRIPTION_LABEL,
			                                     FIRST_COLUMN_LABEL_WIDTH,
			                                     true,false));
		}

		catch (NullPointerException exception)
		{
			System.err.println(METHOD_LABEL + exception);
		}

		catch (IllegalArgumentException exception)
		{
			System.err.println(METHOD_LABEL + exception);
		}

		try
		{
			returnString.append(formatTextString(getDescription(),
			                                     SECOND_COLUMN_VALUE_WIDTH,
			                                     false,true));
		}

		catch (NullPointerException exception)
		{
			System.err.println(METHOD_LABEL + exception);
		}

		catch (IllegalArgumentException exception)
		{
			System.err.println(METHOD_LABEL + exception);
		}

		// Return the completed string to calling program.

		return (returnString.toString());
	}

	/**
	 * Determine the description of this unit. This is the name of the public
	 * derived subclass that was specified to create this object. The recognized
	 * values are listed <A HREF="Description.html">here</A>.
	 *
	 * @return a <CODE>String</CODE> specifying the unit description.
	 */

	public String getDescription()
	{
		return (description.label());
	}

	// The following abstract methods are defined in the subclasses of Unit.
	// This is necessary in order to allow different public class types derived
	// from Unit to be stored and accessed as the generic Unit type. It is also
	// necessary in order to access the public access methods of the entire
	// hierarchy without casting to a specific class type.

	// Fighting.java

	/**
	 * Determine the identity of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's identity.
	 *
	 * @see Fighting#getIdentity
	 */

	abstract public String getIdentity();

	/**
	 * Determine the type of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying a more precise description of the unit type.
	 *
	 * @see Fighting#getUnitType
	 */

	abstract public String getUnitType();

	/**
	 * Determine the firepower of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's firepower.
	 *
	 * @see Fighting#getFirepower
	 */

	abstract public String getFirepower();

	/**
	 * Determine the maximum range that this unit may fire its weapon(s) at full
	 * effect.
	 *
	 * @return a <CODE>String</CODE> specifying the normal range of the unit's weapon(s).
	 *
	 * @see Fighting#getNormalRange
	 */

	abstract public String getNormalRange();

	/**
	 * Determine the current status of this unit.
	 *
	 * @return a comma delimited <CODE>String</CODE> describing the unit status.
	 *
	 * @see Fighting#getStatus
	 * @see Infantry#getStatus
	 */

	abstract public String getStatus();

	/**
	 * Attempt to restore this unit's status to <A HREF="#_NORMAL_">NORMAL</A>.
	 *
	 * @param isLeaderPresent indicates if a <B><A HREF="Leader.html">Leader</A></B> is present, which may
	 * determine if a restoration attempt can be made or not.
	 * @param modifier the applicable dice roll modifier for the attempt.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
	 * result of calling this method.
	 *
	 * @see Fighting#restore
	 * @see Infantry#restore
	 */

	abstract public boolean restore(boolean isLeaderPresent,int modifier);

	/**
	 * Perform a morale or task check on this unit.
	 *
	 * @param modifier the applicable dice roll modifier for the check.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
	 * result of calling this method.
	 *
	 * @see Fighting#check
	 * @see Infantry#check
	 */

	abstract public boolean check(int modifier);

	// Mobile.java

	/**
	 * Determine the number of movement factors or points available to this
	 * unit before it begins to move.
	 *
	 * @return a <CODE>String</CODE> specifying the movement capability in factors or points.
	 *
	 * @see Mobile#getMovement
	 */

	abstract public String getMovement();
}
