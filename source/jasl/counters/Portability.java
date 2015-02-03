// ************************************************************************** //
// Portability.java - This interface is part of the <B>counters</B> package, which   //
//                    contains the class definitions and implementations for  //
//                    objects used to represent the virtual playing pieces in //
//                    jASL.                                                   //
//                                                                            //
//                    NOTE: This program is based on Advanced Squad Leader,   //
//                          which was created by The Avalon Hill Game         //
//                          Company, and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.    //
//                                                                            //
// Written By: Craig R. Campbell  -  March 2012                               //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required method
 * associated with the portage value of a <A HREF="Unit.html">Unit</A>. The method is intended for
 * operation on an integer member variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2012-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Portability.html">Source code</A>
 */

public interface Portability
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the portage
	// value of a <A HREF="Unit.html">Unit</A> using an objects toText() method.

	/**
	 * Provides a label for a unit's portage value : <B>Portage Value</B>
	 */

	public static final String PORTAGE_VALUE_LABEL = "Portage Value";

	// The following constants define the possible range of a portage value.

	/** Minimum valid portage value : <B>0</B> */

	public static final int MIN_PORTAGE_VALUE = 0;

	/** Maximum valid portage value : <B>99</B> */

	public static final int MAX_PORTAGE_VALUE = 99;

	// Access methods

	/**
	 * Return the portage value of a unit. This is a measure of the "cost"
	 * to another unit to carry it.
	 *
	 * @return an <CODE>int</CODE> specifying the portage value.
	 */

	public abstract int portageValue();
}
