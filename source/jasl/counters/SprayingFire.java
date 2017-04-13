// ************************************************************************** //
// SprayingFire.java - This interface is part of the <B>counters</B> package, which  //
//                     contains the class definitions and implementations for //
//                     objects used to represent the virtual playing pieces   //
//                     in jASL.                                               //
//                                                                            //
//                     NOTE: This program is based on Advanced Squad Leader,  //
//                           which was created by The Avalon Hill Game        //
//                           Company, and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.   //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2011                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constant and required method
 * associated with a flag indicating whether or not a <A HREF="Unit.html">Unit</A> has spray fire
 * capability or not. The method is intended for operation on a boolean member
 * variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2011-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/SprayingFire.html">Source code</A>
 */

public interface SprayingFire
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying whether or
	// not a <A HREF="Unit.html">Unit</A> has this capability using an objects toText() method.

	/**
	 * Provides a label indicating if a unit has spray fire capability :
	 * <B>Can Spray Fire ?</B>
	 */

	public static final String CAN_SPRAY_FIRE_LABEL = "Can Spray Fire ?";

	// Access methods

	/**
	 * Indicate if a unit is capable of spraying fire (attacking two adjacent
	 * hexes with a single fire action). This is indicated on the physical
	 * counter by an underscored range value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit has this capability.
	 */

	public abstract boolean canSprayFire();
}
