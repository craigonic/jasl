// ************************************************************************** //
// Fighting.java - This class is a member of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import jasl.utilities.Messages;

/**
 * This class is used to define the basic characteristics of a combat unit
 * (nationality, status, etc). It is intended strictly as a superclass, not to
 * be instantiated directly.
 *
 * @version 2.0
 * @author Copyright (C) 1998-2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Fighting.html">Source code</A>
 */

abstract class Fighting extends Unit implements Identity, Nationality, Status, UnitType
{
	// Private data members

	// This variable contains the nationality of the derived object of this
	// class (e.g. "American" or "German").

	private Nationalities _nationality;

	// This variable is used to store the specific type of the counter. It
	// is intended for vehicles (Pz VIb, T-34/76, etc.) and weapons
	// (FlaK 30, ATR, etc.) for more precise identification. Infantry units
	// may have infantry designations such as SS, Gurkha, Paratroopers, etc.
	// Examples of possible values for are found in the <A HREF="Infantry.html">Infantry.UnitTypes</A>
	// enum. If one of these values is specified, it will be checked against
	// the specified nationality and, in some cases, the description.
	//
	// This item may be empty, but not null.

	private String _unitType;

	// This variable contains a simple identifier for the unit, typically a
	// single alphanumeric character. It is also used to store the full name
	// for <A HREF="Leader.html">Leader</A>s and Heroes.

	private String _identity;

	// This variable contains the current status of the derived object of
	// this class. The status values are defined in the <A HREF="Status.html">Status.States</A> enum.
	// This value is used as a bit-field with the state values corresponding
	// to the individual bits.

	private int _status;

	// The following strings are used as messages for any exceptions that
	// may be generated by bad data being passed to the constructor.

	// This constant is used as part of the error messages (see below) that
	// are generated when an exception is thrown.

	private static final String CLASS_NAME = "Fighting";

	private static final String nullPointerError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.NULL_PARAMETER_MSG);

	// Constructor

	// During the instantiation of derived concrete classes the parameters
	// are passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Fighting(Descriptions description,Nationalities nationality,
	                   String unitType)
	{
		// Pass the first parameter to the superclass constructor. If
		// any exceptions are thrown, assume that they will be caught
		// and handled by the program creating the object.

		super(description);

		// Copy the value of each remaining parameter to the
		// corresponding variable if an exception is not found.

		// Nationality

		_nationality = nationality;

		// Unit Type

		if (unitType == null)
		{
			throw new NullPointerException(nullPointerError);
		}

		_unitType = unitType;

		// Set the initial status to the default. This value will be set
		// more specifically through the use of the setStatus() method.

		_status = States.NORMAL.value();
	}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe
	 * the current instance. All of the members, beginning with the
	 * top-level class (<B><A HREF="Unit.html">Unit</A></B>) and continuing down the hierarchy to this
	 * level, are appended to the returned string. Each value is preceded by
	 * a label defined in this class or the interface associated with the
	 * item. There are no more than two values, including labels, in each
	 * line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toString()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the string defined in the parent class
		// version of this method.

		StringBuffer returnString = new StringBuffer(super.toString());

		// Add the information describing the data stored in this class
		// instance.

		// Nationality

		returnString.append(Messages.formatTextString(NATIONALITY_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(nationality(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Unit Type

		returnString.append(Messages.formatTextString(UNIT_TYPE_LABEL,
		                                              THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(unitType(),
		                                              FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Identity

		returnString.append(Messages.formatTextString(IDENTITY_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(identity(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Status

		returnString.append(Messages.formatTextString(STATUS_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(status(),
		                                              80 - FIRST_COLUMN_LABEL_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the nationality of a unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's nationality.
	 */

	public final String nationality()
	{
		return _nationality.label();
	}

	/**
	 * Return the formal / specific type of a unit. This provides more
	 * accurate identification and application of attributes associated with
	 * specific unit types. For example, it may specify vehicle names
	 * (Pz VIb, T-34/76, etc.) as well as special infantry designations
	 * (Gurkha, Paratroopers, etc.).
	 *
	 * @return a <CODE>String</CODE> specifying the more precise description of the
	 * unit's nationality, type, or capability.
	 *
	 * @see Infantry.UnitTypes
	 */

	public final String unitType()
	{
		return _unitType;
	}

	/**
	 * Return the identity of a unit. This is typically a single
	 * alphanumeric character, but it may also be a full name (e.g. for
	 * leaders and heroes).
	 *
	 * The default setting is an empty string.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's identity.
	 *
	 * @see #setIdentity
	 */

	public final String identity()
	{
		return _identity;
	}

	/**
	 * Return the current status of a unit.
	 *
	 * @return a comma delimited <CODE>String</CODE> describing the unit status.
	 *
	 * @see #clearStatus
	 * @see #setStatus
	 */

	public final String status()
	{
		return States.NORMAL.label();
	}

	/**
	 * Determine if the status of a unit includes the indicated state.
	 *
	 * @param state the state of interest.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit is in the specified state.
	 */

	public boolean isStatusSet(States state)
	{
		return false;
	}

	// Public update methods

	/**
	 * Set a new identity for a unit.
	 *
	 * @param newIdentity the new identity.
	 *
	 * @throws NullPointerException in the case of a null identity
	 * @throws IllegalArgumentException in the case of a zero length identity
	 *
	 * @see #identity
	 */

	public final void setIdentity(String newIdentity)
	{
		// Define local constants.

		String METHOD_NAME = "setIdentity";

		// Check the parameters received and throw the appropriate
		// exception if necessary.

		if (newIdentity == null)
		{
			throw new NullPointerException(Messages.buildErrorMessage(CLASS_NAME,
			                                                          METHOD_NAME,
			                                                          Messages.NULL_PARAMETER_MSG));
		}

		if (newIdentity.length() == 0)
		{
			throw new IllegalArgumentException(Messages.buildErrorMessage(CLASS_NAME,
			                                                              METHOD_NAME,
			                                                              Messages.ZERO_LENGTH_PARAMETER_MSG));
		}

		_identity = newIdentity;
	}

	/**
	 * Change the status of a unit.
	 *
	 * @param state the state to be cleared or removed.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see #status
	 */

	public final boolean clearStatus(States state)
	{
		if (!isStatusSet(state))
		{
			return true;
		}

		return false;
	}

	/**
	 * Change the status of a unit.
	 *
	 * @param state the state to be set or applied.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see #status
	 */

	public final boolean setStatus(States state)
	{
		if (!isStatusSet(state))
		{
			return true;
		}

		return false;
	}
}
