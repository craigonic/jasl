// ************************************************************************** //
// Mobility.java - This interface is part of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       Company, and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.       //
//                                                                            //
// Written By: Craig R. Campbell  -  March 2012                               //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required methods
 * associated with the movement, portage capacity, and portage state of a <A HREF="Unit.html">Unit</A>.
 * The methods are intended for operation on integer member variables within the
 * implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2012 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Mobility.html">Source code</A>
 */

public interface Mobility
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the output of the access methods in the output of a <A HREF="Unit.html">Unit</A> objects
	// toString() method.

	/**
	 * Provides a label for a units movement allowance : <B>Movement</B>
	 */

	public static final String MOVEMENT_LABEL = "Movement";

	/**
	 * Provides a label for a units portage capacity : <B>Portage Capacity</B>
	 */

	public static final String PORTAGE_CAPACITY_LABEL = "Portage Capacity";

	/**
	 * Provides a label for a units portage level : <B>Portage Level</B>
	 */

	public static final String PORTAGE_LEVEL_LABEL = "Portage Level";

	// The following constants define the minimums for the attributes
	// defined by this interface.

	/** Minimum valid movement (factors or points) value : <B>0</B> */

	public static final int MIN_MOVEMENT = 0;

	/** Minimum valid portage capacity value : <B>0</B> */

	public static final int MIN_PORTAGE_CAPACITY = 0;

	/** Minimum valid portage level value : <B>0</B> */

	public static final int MIN_PORTAGE_LEVEL = 0;

	// Access methods

	/**
	 * Return the number of movement factors or points available to a unit
	 * before it begins to move. This value may be affected by the current
	 * portage level of the unit.
	 *
	 * @return an <CODE>int</CODE> specifying the movement capability of the unit in
	 * factors or points.
	 */

	public abstract int movement();

	/**
	 * Return the maximum number of portage points (PP) of <A HREF="Personnel.html">personnel</A> and/or
	 * equipment that a unit can carry without affecting the movement
	 * factors or points available to it.
	 *
	 * @return an <CODE>int</CODE> specifying the portage capacity of the unit.
	 */

	public abstract int portageCapacity();

	/**
	 * Return the number of portage points (PP) of <A HREF="Personnel.html">personnel</A> and/or
	 * equipment that a unit is currently carrying.
	 *
	 * @return an <CODE>int</CODE> specifying the current portage level of the unit.
	 */

	public abstract int portageLevel();
}