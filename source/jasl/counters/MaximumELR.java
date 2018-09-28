// ************************************************************************** //
// MaximumELR.java - This interface is part of the <B>counters</B> package, which    //
//                   contains the class definitions and implementations for   //
//                   objects used to represent the virtual playing pieces in  //
//                   jASL.                                                    //
//                                                                            //
//                   NOTE: This program is based on Advanced Squad Leader,    //
//                         which was created by The Avalon Hill Game Company, //
//                         and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2016                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constant and required method
 * associated with a flag indicating whether or not a <A HREF="Personnel.html">Personnel</A> unit has the
 * maximum ELR (experience level rating) or not. The method is intended for
 * operation on a boolean member variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/MaximumELR.html">Source code</A>
 */

public interface MaximumELR
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying whether or
	// not a <A HREF="Unit.html">Unit</A> has this capability using an objects toText() method.

	/**
	 * Provides a label indicating if these personnel have the maximum
	 * experience level rating : <B>Has Maximum ELR ?</B>
	 */

	public static final String HAS_MAXIMUM_ELR_LABEL = "Has Maximum ELR ?";

	// Access methods

	/**
	 * Indicate if a unit inherently has the maximum experience level
	 * rating.
	 *
	 * This is used to determine how a unit is replaced. It is indicated on
	 * the physical counter by an underscored morale value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit has this attribute.
	 */

	public abstract boolean hasMaximumELR();
}
