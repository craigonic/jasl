// ************************************************************************** //
// Firepower.java - This interface is part of the <B>counters</B> package, which     //
//                  contains the class definitions and implementations for    //
//                  objects used to represent the virtual playing pieces in   //
//                  jASL.                                                     //
//                                                                            //
//                  NOTE: This program is based on Advanced Squad Leader,     //
//                        which was created by The Avalon Hill Game Company,  //
//                        and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.               //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2012                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required methods
 * associated with the firepower and range of a <A HREF="Fighting.html">Fighting</A> unit. The methods are
 * intended for operation on the integer and String member variables within the
 * implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2012-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Firepower.html">Source code</A>
 */

public interface Firepower
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the values returned by the access methods in the output of a <A HREF="Unit.html">Unit</A>
	// objects toText() method.

	/**
	 * Provides a label for a unit's firepower : <B>Firepower</B>
	 */

	public static final String FIREPOWER_LABEL = "Firepower";

	/**
	 * Provides a label for a unit's firepower equivalent:
	 * <B>Firepower Equivalent</B>
	 */

	public static final String FIREPOWER_EQUIV_LABEL = "Firepower Equivalent";

	/**
	 * Provides a label for a unit's normal range : <B>Normal Range</B>
	 */

	public static final String NORMAL_RANGE_LABEL = "Normal Range";

	// The following constants define the minimums for the attributes
	// defined by this interface.

	/** Minimum valid firepower equivalent value : <B>0</B> */

	public static final int MIN_FIREPOWER = 0;

	/** Minimum valid range value : <B>0</B> */

	public static final int MIN_RANGE = 0;

	// Access methods

	/**
	 * Return the firepower designation for a unit.
	 *
	 * This may be an infantry fire table (IFT) value or a weapon type
	 * description (e.g. 88LL, FT, etc.).
	 *
	 * @return a <CODE>String</CODE> specifying the unit's firepower.
	 */

	public abstract String firepower();

	/**
	 * Return the firepower equivalent for a unit.
	 *
	 * For units where the return value of firepower() is a number, it is
	 * simply the integer representation, intended for direct application in
	 * the infantry fire table (IFT). Otherwise, it is the translation of a
	 * label (e.g. 88LL) into the corresponding IFT value.
	 *
	 * @return an <CODE>int</CODE> specifying the unit's firepower equivalent.
	 */

	public abstract int firepowerEquivalent();

	/**
	 * Return the maximum range that a unit may fire its weapon(s) at full
	 * effect.
	 *
	 * @return an <CODE>int</CODE> specifying the normal range of the unit's weapon(s).
	 */

	public abstract int normalRange();
}
