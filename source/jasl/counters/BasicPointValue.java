// ************************************************************************** //
// BasicPointValue.java - This interface is part of the <B>counters</B> package,     //
//                        which contains the class definitions and            //
//                        implementations for objects used to represent the   //
//                        virtual playing pieces in jASL.                     //
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
 * associated with the basic point value of an <A HREF="Infantry.html">Infantry</A> unit. The method is
 * intended for operation on the integer variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2016 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/BasicPointValue.html">Source code</A>
 */

public interface BasicPointValue
{
	// Symbolic constants

	// The following constant is provided primarily for use in displaying
	// the value returned by the access method in the output of a <A HREF="Unit.html">Unit</A>
	// objects toText() method.

	/**
	 * Provides a label for a unit's basic point value : <B>Basic Point Value</B>
	 */

	public static final String BPV_LABEL = "Basic Point Value";

	// The following constant defines the minimum for the basic point value.

	/**
	 * Minimum valid basic point value : <B>0</B>
	 */

	public static final int MIN_BPV = 0;

	// Access methods

	/**
	 * Return the basic point value of a unit. This value is used to
	 * determine battlefield integrity and for design your own (DYO)
	 * scenarios.
	 *
	 * @return an <CODE>int</CODE> specifying the basic point value of the unit.
	 */

	public abstract int basicPointValue();
}
