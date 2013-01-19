// ************************************************************************** //
// Infantry.java - This class is a member of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import jasl.utilities.Messages;

/**
 * This class is used to define the characteristics which are common to all
 * infantry units. It is intended strictly as a superclass, not to be
 * instantiated directly.
 *
 * @version 2.0
 * @author Copyright (C) 1998-2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Infantry.html">Source code</A>
 */

abstract class Infantry extends Mobile implements Firepower, Identity, Morale, Portability
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the value returned by their corresponding access methods in the <A HREF="Unit.html">Unit</A>
	// objects toString() method.

	/**
	 * Provides a label for a unit's basic point value : <B>Basic Point Value</B>
	 */

	public static final String BPV_LABEL = "Basic Point Value";

	/**
	 * Provides a label for a unit's experience level rating :
	 * <B>Experience Level Rating</B>
	 */

	public static final String ELR_LABEL = "Experience Level Rating";

	// Recognized Infantry unit types.

	/**
	 * Recognized unit type values for Infantry. These are used to identify
	 * additional characteristics/abilities or a more specific nationality.
	 */

	public enum UnitTypes
	{
		/** <A NAME="_NONE_"></A>
		 * Indicates that a more specific nationality, ability,
		 * characteristic, etc. does not apply to an Infantry unit.
		 */

		NONE(""),

		/** <A NAME="_PARATROOPS_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Paratroops</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>American</B>.
		 *
		 * @see Nationality.Nationalities#AMERICAN
		 */

		PARATROOPS("Paratroops"),

		/** <A NAME="_AIRBORNE_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Airborne</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		AIRBORNE("Airborne"),

		/** <A NAME="_ANZAC_"></A>
		 * Indicates that a unit's more precise nationality is <B>ANZAC</B>. If
		 * this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		ANZAC("ANZAC"),

		/** <A NAME="_CANADIAN_"></A>
		 * Indicates that a unit's more precise nationality is <B>Canadian</B>.
		 * If this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		CANADIAN("Canadian"),

		/** <A NAME="_FREE_FRENCH_"></A>
		 * Indicates that a unit's more precise nationality is
		 * <B>Free French</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		FREE_FRENCH("Free French"),

		/** <A NAME="_FREE_POLISH_"></A>
		 * Indicates that a unit's more precise nationality is
		 * <B>Free Polish</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		FREE_POLISH("Free Polish"),

		/** <A NAME="_GUARDSMEN_"></A>
		 * Indicates that a unit's more precise type is <B>Guardsmen</B>. If
		 * this value is specified as the unitType parameter for an
		 * object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		GUARDSMEN("Guardsmen"),

		/** <A NAME="_GURKHA_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Gurkha</B>. If this value is specified as the unitType parameter
		 * for an object, the nationality parameter must be <B>British</B>.
		 *
		 * @see Nationality.Nationalities#BRITISH
		 */

		GURKHA("Gurkha"),

		/** <A NAME="_SISSI_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Sissi</B>. If this value is specified as the unitType parameter
		 * for an object, the nationality parameter must be <B>FINNISH</B>.
		 *
		 * @see Nationality.Nationalities#FINNISH
		 */

		SISSI("Sissi"),

		/** <A NAME="_ENGINEERS_"></A>
		 * Indicates that a unit's more precise type or capability is
		 * <B>Engineers</B>. If this value is specified as the unitType
		 * parameter for an object, the nationality parameter must be
		 * <B>German</B>.
		 *
		 * @see Nationality.Nationalities#GERMAN
		 */

		ENGINEERS("Engineers"),

		/** <A NAME="_COMMISSAR_"></A>
		 * Indicates that a unit's more precise type is <B>Commissar</B>. This
		 * value may only be specified as the unitType parameter for a
		 * <B>Leader</B> object. The nationality parameter must be
		 * <B>Russian</B>.
		 *
		 * @see Nationality.Nationalities#RUSSIAN
		 * @see Leader
		 */

		COMMISSAR("Commissar"),

		/** <A NAME="_GUARDS_"></A>
		 * Indicates that a unit's more precise type is <B>Guards</B>. If this
		 * value is specified as the unitType parameter for an object,
		 * the nationality parameter must be <B>Russian</B>.
		 *
		 * @see Nationality.Nationalities#RUSSIAN
		 */

		GUARDS("Guards");

		// Private data members

		// The label associated with the enum constant.

		private final String label;

		// Constructor

		UnitTypes(String label)
		{
			this.label = label;
		}

		// Public access method

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the constant.
		 */

		public String label()
		{
			return label;
		}
	}

	// The following constants define the minimums and maximums for their
	// corresponding attributes.

	/**
	 * Maximum valid firepower (equivalent) value : <B>8</B>
	 *
	 * This item (obviously) applies to Infantry units only.
	 */

	private static final int MAX_FIREPOWER = 8;

	/**
	 * Minimum valid basic point value : <B>0</B>
	 */

	private static final int MIN_BPV = 0;

	/** <A NAME="_MIN_ELR_"></A>
	 * Minimum valid experience level rating value : <B>0</B>
	 */

	private static final int MIN_ELR = 0;

	/** <A NAME="_MAX_ELR_"></A>
	 * Maximum valid experience level rating value : <B>5</B>
	 */

	private static final int MAX_ELR = 5;

	// Private data members

	// This variable stores the firepower of the unit that this object
	// represents. Note that it is specified to the constructor as
	// firepower, but this item (which has the matching type, both to the
	// constructor and for application in the IFT) is used to store it.

	private int _firepowerEquivalent;

	// This variable stores the normal range of the unit that this object
	// represents.

	private int _normalRange;

	// This variable contains a simple identifier for the unit, typically a
	// single alphanumeric character. It is also used to store the full name
	// for <A HREF="Leader.html">Leader</A>s and Heroes.

	private String _identity;

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

	// The following string is used as messages for any exceptions that may
	// be generated by bad data being passed to the constructor.

	private static final String CLASS_NAME = "Infantry";

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(CLASS_NAME,Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);

	// Constructor

	// During the instantiation of derived concrete classes the parameters
	// are passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Infantry(Descriptions description,Nationalities nationality,
	                   UnitTypes unitType,int movement,int portageCapacity,
	                   int firepower,int normalRange,int morale,
	                   int brokenMorale,boolean canSelfRally,
	                   int portageValue,int basicPointValue,
	                   int experienceLevelRating)
	{
		// Since the unitType is ultimately stored as a string in a
		// parent class, check the specified value against any of the
		// other parameters that determine if it is valid or not.

		if (((unitType == UnitTypes.PARATROOPS) &&
		     (nationality != Nationalities.AMERICAN)) ||
		    (((unitType == UnitTypes.AIRBORNE)    ||
		      (unitType == UnitTypes.ANZAC)       ||
		      (unitType == UnitTypes.CANADIAN)    ||
		      (unitType == UnitTypes.FREE_FRENCH) ||
		      (unitType == UnitTypes.FREE_POLISH) ||
		      (unitType == UnitTypes.GUARDSMEN)   ||
		      (unitType == UnitTypes.GURKHA))     &&
		     (nationality != Nationalities.BRITISH))  ||
		    ((unitType == UnitTypes.SISSI) &&
		     (nationality != Nationalities.FINNISH))  ||
		    ((unitType == UnitTypes.ENGINEERS) &&
		     (nationality != Nationalities.GERMAN))   ||
		    (((unitType == UnitTypes.COMMISSAR) ||
		      (unitType == UnitTypes.GUARDS)) &&
		     (nationality != Nationalities.RUSSIAN)))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   nationality.label() +
			                                   Messages.AND_SEPARATOR +
			                                   unitType.label());
		}

		if ((unitType == UnitTypes.COMMISSAR) &&
		    (description != Descriptions.LEADER))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   description.label() +
			                                   Messages.AND_SEPARATOR +
			                                   unitType.label());
		}

		// Pass the first 5 parameters to the superclass constructor. If
		// any exceptions are thrown, assume that they will be caught
		// and handled by the program creating the object.

		super(description,nationality,unitType.label(),movement,
		      portageCapacity);

		// Check the value of each remaining parameter and copy the
		// value to the local copy of the corresponding variable if an
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
	}

	// Protected access methods

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

	protected String toString()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the string defined in the parent class
		// version of this method.

		StringBuffer returnString = new StringBuffer(super.toString());

		// Add the information describing the data stored in this class
		// instance.

		// Identity

		returnString.append(Messages.formatTextString(IDENTITY_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(identity(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

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

		returnString.append(Messages.formatTextString(normalRange(),
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

		// Return the completed string to calling program.

		return returnString.toString();
	}

	// Public access methods

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
	 * leader. This is indicated on the back of the physical counter by a
	 * square around the broken morale value.
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
	 * Return the portage value of a unit. This is a measure of the "cost"
	 * to another unit to carry it.
	 *
	 * @return an <CODE>int</CODE> specifying the portage value of the unit.
	 */

	public final int portageValue()
	{
		return _portageValue;
	}

	/**
	 * Return the basic point value of a unit. This value is used to
	 * determine battlefield integrity and for design your own (DYO)
	 * scenarios.
	 *
	 * @return an <CODE>int</CODE> specifying the basic point value of the unit.
	 */

	public final int basicPointValue()
	{
		return _basicPointValue;
	}

	/**
	 * Return the experience level rating of a unit. This is a value between
	 * the <A HREF="#_MIN_ELR_">minimum</A> and <A HREF="#_MAX_ELR_">maximum</A>, inclusive.
	 *
	 * @return an <CODE>int</CODE> specifying the experience level rating for the unit.
	 */

	public final int experienceLevelRating()
	{
		return _experienceLevelRating;
	}

	// Public game actions

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

		if ((isStatusSet(States.BROKEN)) ||
		    (isStatusSet(States.DESPERATE)))
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
