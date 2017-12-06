// ************************************************************************** //
// SniperActivationNumber.java - This interface is part of the <B>ui.data</B>        //
//                               package, which contains the class            //
//                               definitions and implementations for objects  //
//                               used to store and manage the state of an     //
//                               instance of jASL.                            //
//                                                                            //
//                               NOTE: This program is based on Advanced      //
//                                     Squad Leader, which was created by The //
//                                     Avalon Hill Game Company, and lives on //
//                                     at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.               //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2017                            //
// ************************************************************************** //

package jasl.ui.data;

/**
 * This interface is used to define the public constants and required methods
 * associated with the sniper activation number (SAN) for a <A HREF="Side.html">Side</A>. The methods
 * are intended for operation on the integer variable within the implementing
 * class.
 *
 * @version 1.0
 * @author Copyright (C) 2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/SniperActivationNumber.html">Source code</A>
 */

public interface SniperActivationNumber
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the values returned by the access method in the output of a <A HREF="Side.html">Side</A>
	// object's toText() method.

	/**
	 * Provides a label for a side's sniper activation number :
	 * <B>SAN</B>
	 */

	public static final String SAN_LABEL = "SAN";

	// The following constants define the minimum and maximum for the
	// sniper activation number value. For initialization purposes, the
	// minimum can be either 0 or 2. During game play the value cannot be
	// reduced to less than 1 (if it was greater than 0 to start with).

	/** <A NAME="_MIN_SAN_"></A>
	 * Minimum valid sniper activation number value : <B>0</B>
	 */

	public static final int MIN_SAN = 0;

	/** <A NAME="_MAX_SAN_"></A>
	 * Maximum valid sniper activation number value : <B>7</B>
	 */

	public static final int MAX_SAN = 7;

	// Access methods

	/**
	 * Return the sniper activation number for a side.
	 * <P>
	 * This is a value between MIN_SAN and MAX_SAN, inclusive.
	 *
	 * @return an <CODE>int</CODE> specifying the sniper activation number for the side.
	 *
	 * @see reduceSAN
	 */

	public abstract int sniperActivationNumber();

	// Update methods

	/**
	 * Reduce the sniper activation number for a side.
	 * <P>
	 * The stored value will be decremented by 1. It will not be reduced to
	 * less than 1 if the initial value was 2 or greater.
	 *
	 * @see sniperActivationNumber
	 */

	public abstract void reduceSAN();
}
