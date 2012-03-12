// ************************************************************************** //
// Morale.java - This interface is part of the <B>counters</B> package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                            //
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
 * @version 1.0
 * @author Copyright (C) 2012 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Morale.html">Source code</A>
 */

public interface Morale
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the output of the access methods in the output of a <A HREF="Unit.html">Unit</A> objects
	// toString() method.

	/**
	 * Provides a label for an infantry units normal morale value : <B>Morale</B>
	 */

	public static final String MORALE_LABEL = "Morale";

	/**
	 * Provides a label for an infantry units broken morale value :
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
	 * Return the morale level of a unit when it is in its <A HREF="Status.html#_NORMAL_STATE_">normal</A> state.
	 *
	 * @return a <CODE>int</CODE> specifying the normal morale level of the unit.
	 */

	public abstract int morale();

	/**
	 * Return the morale level of a unit when it is in the <A HREF="Status.html#_BROKEN_STATE_">broken</A> state.
	 *
	 * @return a <CODE>int</CODE> specifying the broken morale level of the unit.
	 */

	public abstract int brokenMorale();

	/**
	 * Return if a unit has the ability to rally without the presence of a
	 * <A HREF="Leader.html">leader</A>. This is indicated on the back of the physical counter by a
	 * square around the broken morale value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit has this capability.
	 */

	public abstract boolean canSelfRally();

	// Update methods

	/**
	 * Perform a morale or task check on a unit.
	 *
	 * @param modifier the applicable dice roll modifier (DRM) for the check.
	 * This includes <A HREF="Leadership.html">leadership</A> DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 */

	public abstract boolean check(int modifier);

	/**
	 * Attempt to restore a units status to <A HREF="Status.html#_NORMAL_STATE">normal</A>.
	 *
	 * @param leaderPresent indicates if a <A HREF="Leader.html">leader</A> is present, which may
	 * determine if a restoration attempt can be made or not.
	 * @param modifier the applicable dice roll modifier for the attempt.
	 * This includes <A HREF="Leadership.html">leadership</A> DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 */

	public abstract boolean restore(boolean leaderPresent,int modifier);
}
