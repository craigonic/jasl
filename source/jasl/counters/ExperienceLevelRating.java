// ************************************************************************** //
// ExperienceLevelRating.java - This interface is part of the <B>counters</B>        //
//                              package, which contains the class definitions //
//                              and implementations for objects used to       //
//                              represent the virtual playing pieces in jASL. //
//                                                                            //
//                              NOTE: This program is based on Advanced Squad //
//                                    Leader, which was created by The Avalon //
//                                    Hill Game Company, and lives on at      //
//                                    <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                   //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2016                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required method
 * associated with the experience level (ELR) rating of an <A HREF="Infantry.html">Infantry</A> unit. The
 * method is intended for operation on the integer variable within the
 * implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/ExperienceLevelRating.html">Source code</A>
 */

public interface ExperienceLevelRating
{
	// Symbolic constants

	// The following constants are provided primarily for use in displaying
	// the values returned by the access method in the output of a <A HREF="Unit.html">Unit</A>
	// objects toText() method.

	/**
	 * Provides a label for a unit's experience level rating :
	 * <B>Experience Level Rating</B>
	 */

	public static final String ELR_LABEL = "Experience Level Rating";

	// The following constants define the minimum and maximum for the
	// experience level rating value.

	/** <A NAME="_MIN_ELR_"></A>
	 * Minimum valid experience level rating value : <B>0</B>
	 */

	public static final int MIN_ELR = 0;

	/** <A NAME="_MAX_ELR_"></A>
	 * Maximum valid experience level rating value : <B>5</B>
	 */

	public static final int MAX_ELR = 5;

	// Access methods

	/**
	 * Return the experience level rating of a unit. This is a value between
	 * MIN_ELR and MAX_ELR, inclusive.
	 *
	 * @return an <CODE>int</CODE> specifying the experience level rating for the unit.
	 */

	public abstract int experienceLevelRating();
}
