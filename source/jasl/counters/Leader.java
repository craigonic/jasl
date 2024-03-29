// ************************************************************************** //
// Leader.java - This class is a member of the <B>counters</B> package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at Multi-Man Publishing.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import org.json.JSONException;
import org.json.JSONObject;

import jasl.utilities.JsonData;
import jasl.utilities.Messages;

/**
 * This class is used to represent a Leader counter.
 *
 * @version 6.0
 * @author Copyright (C) 1998-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Leader.html">Source code</A>
 */

public final class Leader extends Infantry implements Leadership
{
	// Symbolic constants

	// These constants are used in the constructor to pass the correct value
	// for a Leader for each attribute. Other types of units may allow the
	// calling program to set these values but they are the same for all
	// Leaders.

	private static final int MOVEMENT_ALLOWANCE = 6;
	private static final int PORTAGE_CAPACITY = 1;
	private static final int PORTAGE_VALUE = 0;

	// This constant is used as part of the error messages (see below) that
	// are generated when an exception is thrown.

	private static final String CLASS_NAME = Leader.class.getSimpleName();

	// The following string is used as a message for any exceptions that may
	// be generated by bad data being passed to the constructor.

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);

	// Private data members

	// This variable contains the modifier available to the derived object
	// of this class (e.g. "-2"). This modifier allows the leader to affect
	// the outcome of actions that affect other "units" that share the same
	// "space".

	private int _modifier;

	// Constructor

	/**
	 * Construct a new <CODE>Leader</CODE>.
	 *
	 * @param nationality the nationality of the leader. Example - <B><A HREF="Nationality.html#_BRITISH_">BRITISH</A></B>
	 * @param unitType a more specific nationality, type, or capability
	 * description for the leader. Example - <B><A HREF="Infantry.html#_CANADIAN_">CANADIAN</A></B>
	 * @param morale the morale level of the leader in its unbroken state.
	 * Example - <B>8</B>
	 * @param brokenMorale the morale level of the leader when it is
	 * broken. Example - <B>9</B>
	 * @param experienceLevelRating a value used for determining when a
	 * leader should be replaced with a lower quality leader. Example - <B>4</B>
	 * @param modifier the dice roll modifier (DRM) of the leader.
	 * Example - <B>-1</B>
	 *
	 * @throws IllegalArgumentException in the case of an invalid argument.
	 */

	public Leader(Nationalities nationality,InfantryTypes unitType,
	              int morale,int brokenMorale,int experienceLevelRating,
	              int modifier)
	{
		// Pass the first 5 arguments to the superclass constructor.
		// Note that several variables have been set with symbolic
		// constants. These are defined at the beginning of this class
		// and its superclasses. If any exceptions are thrown, assume
		// that they will be caught and handled by the program creating
		// the object.

		super(Descriptions.LEADER,nationality,unitType,
		      MOVEMENT_ALLOWANCE,PORTAGE_CAPACITY,MIN_FIREPOWER,
		      MIN_RANGE,morale,brokenMorale,true,PORTAGE_VALUE,0,
		      experienceLevelRating);

		// Check the value of the remaining argument and copy the value
		// to the local copy of the variable if an exception is not
		// found.

		// Modifier

		if ((modifier < MIN_LEADERSHIP_MODIFIER) ||
		    (modifier > MAX_LEADERSHIP_MODIFIER))
		{
			throw new IllegalArgumentException(invalidArgumentError + modifier);
		}

		_modifier = modifier;
	}

	// Public access methods

	/**
	 * Display a plain text representation of an instance of this class.
	 * <P>
	 * All of the attributes, beginning with the top-level class (<B><A HREF="Unit.html">Unit</A></B>) and
	 * continuing down the hierarchy to this level, are appended to the
	 * returned string. Each value is preceded by a label defined in this
	 * class or the interface associated with the item. There are no more
	 * than two values, including labels, in each line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toText()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the string defined in the parent class
		// version of this method.

		StringBuffer returnString = new StringBuffer(super.toText());

		// Add the information describing the data stored in this class
		// instance.

		returnString.append(Messages.formatTextString(MODIFIER_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(modifier()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return an abbreviated description, including attributes, of a leader.
	 * <P>
	 * The text includes the morale, modifier, and counter type. If an
	 * identity is set, it will also be included, shown in parentheses.
	 *
	 * @return a <CODE>String</CODE> specifying a simple description of the leader.
	 */

	public String toString()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the morale level of the leader.

		StringBuffer returnString =
			new StringBuffer(Integer.toString(morale()));

		// For positive modifier values, add the correct "sign".

		if (_modifier > 0)  returnString.append("+");
		if (0 == _modifier) returnString.append("-");

		// Add the modifier value and the counter type. For Russian
		// commissars, replace the description with their title.

		returnString.append(Integer.toString(_modifier) + " ");
		returnString.append((InfantryTypes.COMMISSAR == infantryType()) ?
		                    infantryType() : description());

		// If the identity has been set, add it to the end of the
		// string, in parentheses.

		appendIdentity(returnString);

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Generate a JSON representation of an instance of this class.
	 * <P>
	 * All of the attributes, beginning with the top-level class (<B><A HREF="Unit.html">Unit</A></B>) and
	 * continuing down the hierarchy to this level, are appended to the
	 * returned string. Each value is preceded by a label (key) defined in
	 * this class or the interface associated with the item. Entries at each
	 * level are successively indented to provide hierarchical formatting of
	 * the output.
	 *
	 * @return a <CODE>String</CODE> containing the JSON data.
	 *
	 * @see #fromJSON
	 */

	public String toJSON()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the string defined in the parent class
		// version of this method.

		StringBuffer returnString = new StringBuffer(super.toJSON());

		// Add the information describing the data stored in this class
		// instance.

		String INDENT = "     ";

		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(MODIFIER_LABEL,modifier()) +
		                    JSON_OBJECT_END);

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the dice roll modifier (DRM) of a leader.
	 *
	 * @return an <CODE>int</CODE> specifying the modifier value.
	 */

	public int modifier()
	{
		return _modifier;
	}

	// Update methods

	/**
	 * Update an instance of this class to reflect the settings within the
	 * specified JSON data.
	 * <P>
	 * The setting for each attribute, beginning with the top-level class
	 * (<B><A HREF="Unit.html">Unit</A></B>) and continuing down the hierarchy to this level, is either
	 * checked against the corresponding input value (the majority of cases)
	 * or updated with it.
	 *
	 * @param jsonData JSON formatted text <CODE>String</CODE>.
	 *
	 * @throws IllegalArgumentException in the case where non-matching data
	 * values are found within the text.
	 * @throws JSONException in the case where the text is not valid JSON or
	 * an expected "key" is not found.
	 *
	 * @see #toJSON
	 */

	public void fromJSON(String jsonData)
	{
		// Start by going up the (class) hierarchy. Checks for a null or
		// zero length argument will be made at the top level (Unit).

		super.fromJSON(jsonData);

		// Check the values specific to this class.

		try
		{
			JSONObject jsonObject       = new JSONObject(jsonData);
			String     exceptionDetails = "";

			int modifier = jsonObject.getInt(MODIFIER_LABEL);

			if (modifier != _modifier)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					modifier +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					MODIFIER_LABEL;
			}

			if (!exceptionDetails.isEmpty())
			{
				throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
				                                                              JsonData.FROM_JSON_METHOD_NAME,
				                                                              exceptionDetails));
			}
		}

		catch (JSONException exception)
		{
			throw new JSONException(Messages.buildErrorMessage(CLASS_NAME,
			                                                   JsonData.FROM_JSON_METHOD_NAME,
			                                                   exception.getMessage()));
		}
	}
}
