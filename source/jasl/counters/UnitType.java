// ************************************************************************** //
// UnitType.java - This interface is part of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2006                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define public constant and required method
 * associated with the unit type of <A HREF="Fighting.html">Fighting</A> units. The method is intended for
 * operation on a String member variable within the implementing class.
 *
 * @version 2.0
 * @author Copyright (C) 2006-2012 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/UnitType.html">Source code</A>
 */

public interface UnitType
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying a more
	// specific description of a <A HREF="Fighting.html">Fighting</A> unit using an objects toString()
	// method.

	/**
	 * Provides a label for the unit's more precise nationality, type, or
	 * capability : <B>Unit Type</B>
	 */

	public static final String UNIT_TYPE_LABEL = "Unit Type";

	// Access methods

	/**
	 * Return the formal / specific type of a unit. This provides more
	 * accurate identification and application of attributes associated with
	 * specific unit types. For example, it may specify vehicle names
	 * (Pz VIb, T-34/76, etc.) as well as special infantry designations
	 * (Gurkha, Paratroopers, etc.).
	 *
	 * @return a <CODE>String</CODE> specifying the more precise description of the
	 * unit's nationality, type, or capability.
	 *
	 * @see Infantry.UnitTypes
	 */

	public abstract String unitType();
}
