// ************************************************************************** //
// Identity.java - This interface is part of the <B>counters</B> package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader,      //
//                       which was created by The Avalon Hill Game Company,   //
//                       and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2012                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constant and required methods
 * associated with the identifier for a <A HREF="Unit.html">Unit</A>. The methods are intended for
 * operation on a String member variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2012-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Identity.html">Source code</A>
 */

public interface Identity
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the
	// identity of a <A HREF="Unit.html">Unit</A> using an objects toText() method.

	/**
	 * Provides a label for a unit's identity : <B>Identity</B>
	 */

	public static final String IDENTITY_LABEL = "Identity";

	// Access methods

	/**
	 * Return the identity of a unit. This is typically a single
	 * alphanumeric character, but it may also be a full name (e.g. for
	 * leaders and heroes).
	 *
	 * @return a <CODE>String</CODE> specifying the unit's identity.
	 */

	public abstract String identity();

	// Update methods

	/**
	 * Set a new identity for a unit.
	 *
	 * @param newIdentity the new identity of the unit.
	 */

	public abstract void setIdentity(String newIdentity);
}
