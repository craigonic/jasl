// ************************************************************************** //
// Squad.java - This class is a member of the <B>counters</B> package, which         //
//              contains the class definitions and implementations for        //
//              objects used to represent the virtual playing pieces in jASL. //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                           //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import jasl.utilities.Messages;

/**
 * This class is used to represent a Squad counter.
 *
 * @version 2.0
 * @author Copyright (C) 1998-2013 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Squad.html">Source code</A>
 */

public final class Squad extends Personnel implements SprayingFire
{
	// Symbolic constants

	/**
	 * Provides a label indicating if a squad has assault fire capability :
	 * <B>Can Assault Fire ?</B>
	 */

	public static final String CAN_ASSAULT_FIRE_LABEL = "Can Assault Fire ?";

	/**
	 * Provides a label for a squad's smoke placement capability :
	 * <B>Smoke Placement Exponent</B>
	 */

	public static final String SMOKE_PLACEMENT_EXP_LABEL = "Smoke Placement Exponent";

	/** Minimum valid smoke placement exponent value : <B>0</B> */

	private static final int MIN_SMOKE_EXPONENT = 0;

	/** Maximum valid smoke placement exponent value : <B>3</B> */

	private static final int MAX_SMOKE_EXPONENT = 3;

	// Private data members

	// This flag variable indicates whether or not the squad that this
	// object represents is capable of assault fire and movement. This is
	// indicated on the physical counter by an underscored firepower value.

	private boolean _canAssaultFire;

	// This flag variable indicates whether or not the squad that this
	// object represents is capable of spraying fire. This is indicated on
	// the physical counter by an underscored range value.

	private boolean _canSprayFire;

	// This variable is used to indicate if the squad that this object
	// represents is capable of placing smoke grenades and the chance of
	// success.

	private int _smokePlacementExponent;

	// The following string is used as a message for any exceptions that may
	// be generated by bad data being passed to the constructor.

	private static final String invalidArgumentError =
		Messages.buildErrorMessage(Descriptions.SQUAD.label(),
		                           Messages.CONSTRUCTOR,
		                           Messages.INVALID_PARAMETER_MSG);

	// Constructor

	/**
	 * Construct a new <CODE>Squad</CODE>.
	 *
	 * @param nationality the nationality of the squad. Example - <B><A HREF="Nationality.html#_GERMAN_">GERMAN</A></B>
	 * @param unitType a more specific nationality, type, or capability
	 * description for the squad. Example - <B><A HREF="Infantry.html#_ENGINEERS_">ENGINEERS</A></B>
	 * @param firepower the inherent firepower of the squad. Example - <B>8</B>
	 * @param normalRange the maximum range that the squad's inherent
	 * firepower can be used at full strength. Example - <B>3</B>
	 * @param morale the morale level of the squad in its unbroken state.
	 * Example - <B>8</B>
	 * @param brokenMorale the morale level of the squad when it is broken.
	 * Example - <B>9</B>
	 * @param canSelfRally indicates if the squad can rally without the
	 * presence of a <A HREF="Leader.html">Leader</A>. Example - <B>false</B>
	 * @param basicPointValue the point "value" of the squad for the
	 * purpose of determining battlefield integrity and for design your own
	 * (DYO) scenarios. Example - <B>15</B>
	 * @param experienceLevelRating a value used for determining when a
	 * squad should be replaced with a lower quality squad or half squad(s).
	 * Example - <B>5</B>
	 * @param hasMaximumELR indicates if the squad inherently has the
	 * maximum experience level rating. Example - <B>true</B>
	 * @param classification the quality of the squad. Example - <B><A HREF="Classification.html#_FIRST_LINE_">FIRST_LINE</A></B>
	 * @param canAssaultFire indicates if the squad can use assault
	 * fire and movement. Example - <B>false</B>
	 * @param canSprayFire indicates if the squad can engage targets in
	 * multiple locations in a single fire action. Example - <B>true</B>
	 * @param smokePlacementExponent a value used to indicate the inherent
	 * smoke generation ability of the squad. Example - <B>2</B>
	 *
	 * @throws IllegalArgumentException in the case of an invalid argument.
	 */

	public Squad(Nationalities nationality,UnitTypes unitType,int firepower,
	             int normalRange,int morale,int brokenMorale,
	             boolean canSelfRally,int basicPointValue,
	             int experienceLevelRating,boolean hasMaximumELR,
	             Classifications classification,boolean canAssaultFire,
	             boolean canSprayFire,int smokePlacementExponent)
	{
		// Pass the first 11 parameters to the superclass constructor.
		// If any exceptions are thrown, assume that they will be caught
		// and handled by the program creating the object.

		super(Descriptions.SQUAD,nationality,unitType,firepower,
		      normalRange,morale,brokenMorale,canSelfRally,
		      basicPointValue,experienceLevelRating,hasMaximumELR,
		      classification);

		// Check the value of each remaining parameter and copy the
		// value to the local copy of the corresponding variable if an
		// exception is not found.

		// Assault Fire Capability

		_canAssaultFire = canAssaultFire;

		// Spraying Fire Capability

		_canSprayFire = canSprayFire;

		// Smoke Placement Capability

		if ((smokePlacementExponent < MIN_SMOKE_EXPONENT) ||
		    (smokePlacementExponent > MAX_SMOKE_EXPONENT))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   smokePlacementExponent);
		}

		_smokePlacementExponent = smokePlacementExponent;
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

		// Assault Fire Capability

		returnString.append(Messages.formatTextString(CAN_ASSAULT_FIRE_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Messages.getChoiceLabel(canAssaultFire()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,false));

		// Spraying Fire Capability

		returnString.append(Messages.formatTextString(CAN_SPRAY_FIRE_LABEL,
		                                              THIRD_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Messages.getChoiceLabel(canSprayFire()),
		                                              FOURTH_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Smoke Placement Capability

		returnString.append(Messages.formatTextString(SMOKE_PLACEMENT_EXP_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(Integer.toString(smokePlacementExponent()),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Indicate if a squad has assault fire and movement capabilities. This
	 * is indicated on the physical counter by an underscored firepower
	 * value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the squad has this capability.
	 */

	public boolean canAssaultFire()
	{
		return _canAssaultFire;
	}

	/**
	 * Indicate if a squad is capable of spraying fire (attacking two
	 * adjacent hexes with a single fire action). This is indicated on the
	 * physical counter by an underscored range value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the squad has this capability.
	 */

	public boolean canSprayFire()
	{
		return _canSprayFire;
	}

	/**
	 * Return the smoke placement capability of a squad. This is
	 * indicated on the physical counter as a superscript on the firepower
	 * value.
	 *
	 * @return an <CODE>int</CODE> specifying the Smoke Placement Exponent value.
	 */

	public int smokePlacementExponent()
	{
		return _smokePlacementExponent;
	}
}
