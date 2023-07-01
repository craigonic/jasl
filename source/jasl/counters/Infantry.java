// ************************************************************************** //
// Infantry.java - This class is a member of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at Multi-Man Publishing.                //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import org.json.JSONException;
import org.json.JSONObject;

import jasl.utilities.JsonData;
import jasl.utilities.Messages;

/**
 * This class is used to define the characteristics which are common to all
 * infantry units. It is intended strictly as a superclass, not to be
 * instantiated directly.
 *
 * @version 6.0
 * @author Copyright (C) 1998-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Infantry.html">Source code</A>
 */

abstract class Infantry extends Mobile implements Firepower, Morale,
                                                  Portability, BasicPointValue,
                                                  ExperienceLevelRating
{
	// Symbolic constants

	/**
	 * Provides a label for an Infantry unit's more precise nationality,
	 * type, or capability : <B>Infantry Type</B>
	 */

	public static final String INFANTRY_TYPE_LABEL = "Infantry Type";

	// Maximum valid firepower (equivalent) value : <B>8</B>
	//
	// This item (obviously) applies to Infantry units only.

	private static final int MAX_FIREPOWER = 8;

	// This constant is used as part of the error messages (see below) that
	// are generated when an exception is thrown.

	private static final String CLASS_NAME = Infantry.class.getSimpleName();

	// The following string is used as a message for any exceptions that may
	// be generated by bad data being passed to the constructor.

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);

	// Private data members

	// This variable stores the firepower of the unit that this object
	// represents. Note that it is specified to the constructor as
	// firepower, but this item (which has the matching type, both to the
	// constructor and for application in the IFT) is used to store it.

	private int _firepowerEquivalent;

	// This variable stores the normal range of the unit that this object
	// represents.

	private int _normalRange;

	// This variable contains the normal morale value of the derived object
	// of this class.

	private int _morale;

	// This variable contains the morale value of the derived object of this
	// class when it is "broken".

	private int _brokenMorale;

	// This variable indicates whether or not it is possible for the derived
	// object of this class to "self rally". It is applied during the
	// execution of the restore() method.

	private boolean _canSelfRally;

	// This variable contains the number of portage points associated with a
	// derived object of this class (how much it costs to carry the unit).

	private int _portageValue;

	// This variable stores the basic point value of the unit that this
	// object represents. This is used in the calculation of Battlefield
	// Integrity and for design your own (DYO) scenarios.

	private int _basicPointValue;

	// This variable stores the experience level rating of the unit this
	// object represents. This is used in unit substitution and replacement.

	private int _experienceLevelRating;

	// This variable stores a more specific type, designation, nationality,
	// etc. for the unit this object represents. The text equivalent of this
	// value is passed to the superclass constructor.

	private InfantryTypes _infantryType;

	// Constructor

	// During the instantiation of derived concrete classes the arguments
	// are passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Infantry(Descriptions description,Nationalities nationality,
	                   InfantryTypes unitType,int movement,
	                   int portageCapacity,int firepower,int normalRange,
	                   int morale,int brokenMorale,boolean canSelfRally,
	                   int portageValue,int basicPointValue,
	                   int experienceLevelRating)
	{
		// Pass the first 5 arguments to the superclass constructor. If
		// any exceptions are thrown, assume that they will be caught
		// and handled by the program creating the object.

		super(description,nationality,unitType.toString(),movement,
		      portageCapacity);

		// Since the unitType is ultimately stored as a string in a
		// parent class, check the specified value against any of the
		// other arguments that determine if it is valid or not.

		if (((unitType == InfantryTypes.PARATROOPS) &&
		     (nationality != Nationalities.AMERICAN)) ||
		    (((unitType == InfantryTypes.AIRBORNE)    ||
		      (unitType == InfantryTypes.ANZAC)       ||
		      (unitType == InfantryTypes.CANADIAN)    ||
		      (unitType == InfantryTypes.FREE_FRENCH) ||
		      (unitType == InfantryTypes.FREE_POLISH) ||
		      (unitType == InfantryTypes.GUARDSMEN)   ||
		      (unitType == InfantryTypes.GURKHA))     &&
		     (nationality != Nationalities.BRITISH))  ||
		    ((unitType == InfantryTypes.SISSI) &&
		     (nationality != Nationalities.FINNISH))  ||
		    ((unitType == InfantryTypes.ENGINEERS) &&
		     (nationality != Nationalities.GERMAN))   ||
		    (((unitType == InfantryTypes.COMMISSAR) ||
		      (unitType == InfantryTypes.GUARDS)) &&
		     (nationality != Nationalities.RUSSIAN)))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   nationality.toString() +
			                                   Messages.AND_SEPARATOR +
			                                   unitType.toString());
		}

		if ((unitType == InfantryTypes.COMMISSAR) &&
		    (description != Descriptions.LEADER))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   description.toString() +
			                                   Messages.AND_SEPARATOR +
			                                   unitType.toString());
		}

		// Check the value of each remaining argument and copy the value
		// to the local copy of the corresponding variable if an
		// exception is not found.

		// Firepower

		if ((firepower < MIN_FIREPOWER) || (firepower > MAX_FIREPOWER))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   firepower);
		}

		_firepowerEquivalent = firepower;

		// Normal Range

		if (normalRange < MIN_RANGE)
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   normalRange);
		}

		_normalRange = normalRange;

		// Morale

		if ((morale < MIN_MORALE) || (morale > MAX_MORALE))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   morale);
		}

		_morale = morale;

		// Broken Morale

		if ((brokenMorale < MIN_MORALE) || (brokenMorale > MAX_MORALE))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   brokenMorale);
		}

		_brokenMorale = brokenMorale;

		// Self Rally Capability

		_canSelfRally = canSelfRally;

		// Portage Value

		if ((portageValue < MIN_PORTAGE_VALUE) ||
		    (portageValue > MAX_PORTAGE_VALUE))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   portageValue);
		}

		_portageValue = portageValue;

		// Basic Point Value

		if (basicPointValue < MIN_BPV)
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   basicPointValue);
		}

		_basicPointValue = basicPointValue;

		// Experience Level Rating

		if ((experienceLevelRating < MIN_ELR) ||
		    (experienceLevelRating > MAX_ELR))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   experienceLevelRating);
		}

		_experienceLevelRating = experienceLevelRating;

		// Infantry Type

		_infantryType = unitType;
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

		// Firepower

		returnString.append(Messages.formatTextString(FIREPOWER_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(firepower(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Firepower Equivalent

		returnString.append(Messages.formatTextString(FIREPOWER_EQUIV_LABEL,
		                                              THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(firepowerEquivalent()),
		                                              FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Normal Range

		returnString.append(Messages.formatTextString(NORMAL_RANGE_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(normalRange()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Morale

		returnString.append(Messages.formatTextString(MORALE_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(morale()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Broken Morale

		returnString.append(Messages.formatTextString(BROKEN_MORALE_LABEL,
		                                              THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(brokenMorale()),
		                                              FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Self Rally Capability

		returnString.append(Messages.formatTextString(CAN_SELF_RALLY_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Messages.getChoiceLabel(canSelfRally()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Portage Value

		returnString.append(Messages.formatTextString(PORTAGE_VALUE_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(portageValue()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Basic Point Value

		returnString.append(Messages.formatTextString(BPV_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(basicPointValue()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Experience Level Rating

		returnString.append(Messages.formatTextString(ELR_LABEL,
		                                              THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(experienceLevelRating()),
		                                              FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Infantry Type

		returnString.append(Messages.formatTextString(INFANTRY_TYPE_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(infantryType().name(),
		                                              80 - FIRST_COLUMN_LABEL_WIDTH,
		                                              false,true));

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

		String INDENT = "    ";

		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(FIREPOWER_LABEL,firepower()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(FIREPOWER_EQUIV_LABEL,firepowerEquivalent()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(NORMAL_RANGE_LABEL,normalRange()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(MORALE_LABEL,morale()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(BROKEN_MORALE_LABEL,brokenMorale()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(CAN_SELF_RALLY_LABEL,canSelfRally()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(PORTAGE_VALUE_LABEL,portageValue()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(BPV_LABEL,basicPointValue()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(ELR_LABEL,experienceLevelRating()) +
		                    JSON_OBJECT_SEPARATOR);
		returnString.append(INDENT +
		                    JsonOutput.buildJSONPair(INFANTRY_TYPE_LABEL,infantryType().name()) +
		                    JSON_OBJECT_SEPARATOR);

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the firepower designation for a unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's firepower designation.
	 */

	public final String firepower()
	{
		return Integer.toString(_firepowerEquivalent);
	}

	/**
	 * Return the firepower equivalent for a unit.
	 *
	 * @return an <CODE>int</CODE> specifying the unit's firepower equivalent.
	 *
	 * @see Firepower#firepowerEquivalent
	 */

	public final int firepowerEquivalent()
	{
		return _firepowerEquivalent;
	}

	/**
	 * Return the maximum range that a unit may fire its weapon(s) at full
	 * effect.
	 *
	 * @return an <CODE>int</CODE> specifying the normal range of the unit's weapon(s).
	 */

	public final int normalRange()
	{
		return _normalRange;
	}

	/**
	 * Return the morale level of a unit when it is in its normal state.
	 *
	 * @return an <CODE>int</CODE> specifying the normal morale level of the unit.
	 *
	 * @see Status.States#NORMAL
	 */

	public final int morale()
	{
		return _morale;
	}

	/**
	 * Return the morale level of a unit when it is in the broken state.
	 *
	 * @return an <CODE>int</CODE> specifying the broken morale level of the unit.
	 *
	 * @see Status.States#BROKEN
	 */

	public final int brokenMorale()
	{
		return _brokenMorale;
	}

	/**
	 * Return if a unit has the ability to rally without the presence of a
	 * leader.
	 * <P>
	 * This is indicated on the back of the physical counter by a square
	 * around the broken morale value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit has this attribute.
	 *
	 * @see Leader
	 */

	public final boolean canSelfRally()
	{
		return _canSelfRally;
	}

	/**
	 * Return the portage value of a unit.
	 * <P>
	 * This is a measure of the "cost" to another unit to carry it.
	 *
	 * @return an <CODE>int</CODE> specifying the portage value of the unit.
	 */

	public final int portageValue()
	{
		return _portageValue;
	}

	/**
	 * Return the basic point value of a unit.
	 * <P>
	 * This value is used to determine battlefield integrity and for design
	 * your own (DYO) scenarios.
	 *
	 * @return an <CODE>int</CODE> specifying the basic point value of the unit.
	 */

	public final int basicPointValue()
	{
		return _basicPointValue;
	}

	/**
	 * Return the experience level rating of a unit.
	 * <P>
	 * This is a value between the <A HREF="ExperienceLevelRating.html#_MIN_ELR_">minimum</A> and <A HREF="ExperienceLevelRating.html#_MAX_ELR_">maximum</A>, inclusive.
	 *
	 * @return an <CODE>int</CODE> specifying the experience level rating for the unit.
	 */

	public final int experienceLevelRating()
	{
		return _experienceLevelRating;
	}

	/**
	 * Return the formal / specific type of a unit.
	 * <P>
	 * Use the toString() method of the enum to retrieve the label
	 * associated with the value (e.g. "Canadian" for CANADIAN). The name()
	 * method returns its text representation (e.g. "CANADIAN").
	 *
	 * @return an <CODE>InfantryTypes</CODE> value specifying the more specific type,
	 * designation, nationality, etc. for the unit.
	 */

	public final InfantryTypes infantryType()
	{
		return _infantryType;
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

		String exceptionDetails = "";

		try
		{
			JSONObject jsonObject = new JSONObject(jsonData);

			String firepower =
				jsonObject.getString(FIREPOWER_LABEL);

			if (!firepower.equals(firepower()))
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					firepower +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					FIREPOWER_LABEL;
			}

			int firepowerEquivalent =
				jsonObject.getInt(FIREPOWER_EQUIV_LABEL);

			if (firepowerEquivalent != _firepowerEquivalent)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					firepowerEquivalent +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					FIREPOWER_EQUIV_LABEL;
			}

			int normalRange = jsonObject.getInt(NORMAL_RANGE_LABEL);

			if (normalRange != _normalRange)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					normalRange +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					NORMAL_RANGE_LABEL;
			}

			int morale = jsonObject.getInt(MORALE_LABEL);

			if (morale != _morale)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					morale +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					MORALE_LABEL;
			}

			int brokenMorale =
				jsonObject.getInt(BROKEN_MORALE_LABEL);

			if (brokenMorale != _brokenMorale)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					brokenMorale +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					BROKEN_MORALE_LABEL;
			}

			boolean canSelfRally =
				jsonObject.getBoolean(CAN_SELF_RALLY_LABEL);

			if (canSelfRally != _canSelfRally)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					Messages.getTruthLabel(canSelfRally).toLowerCase() +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					CAN_SELF_RALLY_LABEL;
			}

			int portageValue =
				jsonObject.getInt(PORTAGE_VALUE_LABEL);

			if (portageValue != _portageValue)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					portageValue +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					PORTAGE_VALUE_LABEL;
			}

			int basicPointValue = jsonObject.getInt(BPV_LABEL);

			if (basicPointValue != _basicPointValue)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					basicPointValue +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					BPV_LABEL;
			}

			int experienceLevelRating =
				jsonObject.getInt(ELR_LABEL);

			if (experienceLevelRating != _experienceLevelRating)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					experienceLevelRating +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					ELR_LABEL;
			}

			InfantryTypes infantryType =
				InfantryTypes.valueOf(jsonObject.getString(INFANTRY_TYPE_LABEL));

			if (infantryType != _infantryType)
			{
				exceptionDetails =
					JsonData.FROM_JSON_NON_MATCH_PREFIX +
					infantryType.name() +
					JsonData.FROM_JSON_FOR_SEPARATOR +
					INFANTRY_TYPE_LABEL;
			}

			if (!exceptionDetails.isEmpty())
			{
				throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
				                                                              JsonData.FROM_JSON_METHOD_NAME,
				                                                              exceptionDetails));
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

	/**
	 * Perform a morale or task check on a unit.
	 *
	 * @param modifier the applicable dice roll modifier (DRM) for the check.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see Leadership
	 * @see Status
	 */

	public final boolean check(int modifier)
	{
		return false;
	}

	/**
	 * Attempt to restore a unit's status to normal.
	 *
	 * @param leaderPresent indicates if a leader is present, which may
	 * determine if a restoration attempt can be made or not. Note that the
	 * leader is considered "present" only when in the normal (unbroken)
	 * state.
	 * @param modifier the applicable dice roll modifier for the attempt.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see Leader
	 * @see Status
	 */

	public final boolean restore(boolean leaderPresent,int modifier)
	{
		// Verify that the "unit" actually needs to be rallied.

		if ((status().contains(States.BROKEN)) ||
		    (status().contains(States.DESPERATE)))
		{
			// If the unit is capable of self-rallying (leaders and
			// some elite units) or a <B>unbroken</B> leader is present in
			// the same space, make the rally attempt.

			if (_canSelfRally || leaderPresent)
			{
				return true;
			}
		}

		// Unless determined otherwise above, the "unit" automatically
		// fails.

		return false;
	}
}
