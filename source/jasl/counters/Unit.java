// ************************************************************************** //
// Unit.java - This class is a member of the <B>counters</B> package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Game Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import jasl.utilities.JsonData;
import jasl.utilities.Messages;

/**
 * This class is used to define the basic components of a counter. It is
 * intended strictly as a superclass, not to be instantiated directly.
 *
 * @version 7.0
 * @author Copyright (C) 1998-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Unit.html">Source code</A>
 */

public abstract class Unit implements Serializable, TextOutput, JsonData,
                                      Description
{
	// Symbolic constants

	// This constant is applied within the toJSON() method, serving as the
	// key associated with the toString() "value".

	/**
	 * Provides a label intended for use with the output of toString() :
	 * <B>Unit Label</B>
	 *
	 * @see #toString
	 * @see #toJSON
	 */

	public static final String UNIT_LABEL = "Unit Label";

	// This constant is used as part of the error messages (see below) that
	// are generated when an exception is thrown.

	private static final String CLASS_NAME = Unit.class.getSimpleName();

	// Private data members

	// This item provides a descriptive name for the derived object of this
	// class. It is set to the enum value associated with name of the class
	// being instantiated (e.g. <A HREF="Description.html#_SQUAD_">"Squad"</A>).

	private Descriptions _description;

	// Constructors.

	// During the instantiation of derived concrete classes the argument
	// is passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Unit(Descriptions description)
	{
		_description = description;
	}

	// This declaration is redundant for Java and C++ usage, but it is
	// necessary in order for it to appear in the <A HREF="http://gcc.gnu.org/onlinedocs/gcc-6.3.0/gcj/About-CNI.html#About-CNI">CNI</A> header file, which is
	// used by <A HREF="http://www.swig.org/">SWIG</A> to build bindings for scripting languages.

	protected Unit() {}

	// Public access methods

	/**
	 * Display a plain text representation of an instance of this class.
	 * <P>
	 * Each value is preceded by a label defined in this class or the
	 * interface associated with the item. There are no more than two
	 * values, including labels, in each line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toText()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the values that define the header (since
		// this is the top level of the class hierarchy).

		StringBuffer returnString = new StringBuffer();

		// Add the information describing the data stored in this class
		// instance.

		returnString.append(Messages.formatTextString(DESCRIPTION_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(description().toString(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	// The toString() method implementation is included here for the same
	// reason as the empty constructor above.

	/**
	 * Return an abbreviated description, which may include attributes, of a
	 * unit.
	 * <P>
	 * This method overrides the parent implementation, and it is intended
	 * that derived public classes provide their own version. If not, this
	 * one will return the same value as description().toString().
	 *
	 * @return a <CODE>String</CODE> specifying a simple description of the unit.
	 */

	public String toString()
	{
		return description().toString();
	}

	/**
	 * Generate a JSON representation of an instance of this class.
	 * <P>
	 * Each value is preceded by a label (key) defined in this class or the
	 * the interface associated with the item. The elements are grouped in a
	 * JSON object, with this method (the top level) adding the initial '{'
	 * and the "bottom" (public) class implementation appending the closing
	 * '}'. Entries at each level are successively indented to provide
	 * hierarchical formatting of the output.
	 *
	 * @return a <CODE>String</CODE> containing the JSON data.
	 *
	 * @see #fromJSON
	 */

	public String toJSON()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the values that define the header (since
		// this is the top level of the class hierarchy).

		StringBuffer returnString = new StringBuffer(JSON_OBJECT_START);

		// Add the information describing the data stored in this class
		// instance.

		String INDENT = " ";

		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(DESCRIPTION_LABEL,description().name()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(UNIT_LABEL,toString()) +
		                    JSON_OBJECT_SEPARATOR);

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the description of a unit.
	 * <P>
	 * Use the toString() method of the enum to retrieve the label
	 * associated with the value (e.g. "Crew" for CREW). The name() method
	 * returns its text representation (e.g. "CREW").
	 *
	 * @return a <CODE>Descriptions</CODE> value specifying the unit description.
	 */

	public final Descriptions description()
	{
		return _description;
	}

	// Update methods

	/**
	 * Update an instance of this class to reflect the settings within the
	 * specified JSON data.
	 * <P>
	 * The setting for each attribute is checked against the corresponding
	 * input value.
	 *
	 * @param jsonData JSON formatted text <CODE>String</CODE>.
	 *
	 * @throws NullPointerException in the case of a null argument.
	 * @throws IllegalArgumentException in the case of a zero length
	 * argument or where non-matching data values are found within it.
	 * @throws JSONException in the case where the text is not valid JSON or
	 * an expected "key" is not found.
	 *
	 * @see #toJSON
	 */

	public void fromJSON(String jsonData)
	{
		// Check the argument received and throw the appropriate
		// exception if necessary.

		if (null == jsonData)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          JsonData.FROM_JSON_METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (jsonData.isEmpty())
		{
			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              JsonData.FROM_JSON_METHOD_NAME,
			                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
		}

		// Check the values specific to this class.

		String exceptionDetails = "";

		try
		{
			JSONObject jsonObject = new JSONObject(jsonData);

			Descriptions description =
				Descriptions.valueOf(jsonObject.getString(DESCRIPTION_LABEL));

			if (description != _description)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					description.name() +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					DESCRIPTION_LABEL;
			}

			if (!exceptionDetails.isEmpty())
			{
				throw new IllegalArgumentException(exceptionDetails);
			}
		}

		catch (IllegalArgumentException exception)
		{
			if (exceptionDetails.isEmpty())
			{
				exceptionDetails = exception.getMessage();
			}

			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              JsonData.FROM_JSON_METHOD_NAME,
			                                                              exceptionDetails));
		}

		catch (JSONException exception)
		{
			throw new JSONException(Messages.buildErrorMessage(CLASS_NAME,
			                                                   JsonData.FROM_JSON_METHOD_NAME,
			                                                   exception.getMessage()));
		}
	}
}
