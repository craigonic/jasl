// ************************************************************************** //
// Morale.java - This interface is part of the <B>counters</B> package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at Multi-Man Publishing.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2012                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required methods
 * associated with the morale of an <A HREF="Infantry.html">Infantry</A> unit.
 * The methods are intended for operation on integer and boolean member
 * variables within the implementing class.
 *
 * @version 2.0
 * @author Copyright (C) 2012-2024 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Morale.html">Source code</A>
 */

public interface Morale
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the output of the access methods in the output of a <A HREF="Unit.html">Unit</A> objects
	// toText() method.

	/**
	 * Provides a label for an infantry unit's normal morale value : <B>Morale</B>
	 */

	public static final String MORALE_LABEL = "Morale";

	/**
	 * Provides a label for an infantry unit's broken morale value :
	 * <B>Broken Morale</B>
	 */

	public static final String BROKEN_MORALE_LABEL = "Broken Morale";

	/**
	 * Provides a label indicating if an infantry unit has self rally
	 * capability : <B>Can Self Rally ?</B>
	 */

	public static final String CAN_SELF_RALLY_LABEL = "Can Self Rally ?";

	// The following constants define the possible range of morale values.

	/**
	 * Minimum valid morale (normal and broken) value : <B>0</B>
	 */

	public static final int MIN_MORALE = 0;

	/**
	 * Maximum valid morale (normal and broken) value : <B>10</B>
	 */

	public static final int MAX_MORALE = 10;

	// Access methods

	/**
	 * Return the morale level of a unit when it is in its normal state.
	 *
	 * @return an <CODE>int</CODE> specifying the normal morale level of the unit.
	 */

	public abstract int morale();

	/**
	 * Return the morale level of a unit when it is in the broken state.
	 *
	 * @return an <CODE>int</CODE> specifying the broken morale level of the unit.
	 */

	public abstract int brokenMorale();

	/**
	 * Return if a unit has the ability to rally without the presence of a
	 * leader. This is indicated on the back of the physical counter by a
	 * square around the broken morale value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit has this capability.
	 *
	 * @see Leadership
	 */

	public abstract boolean canSelfRally();
}
