// ************************************************************************** //
// Leadership.java - This interface is part of the <B>counters</B> package, which    //
//                   contains the class definitions and implementations for   //
//                   objects used to represent the virtual playing pieces in  //
//                   jASL.                                                    //
//                                                                            //
//                   NOTE: This program is based on Advanced Squad Leader,    //
//                         which was created by The Avalon Hill Game Company, //
//                         and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.              //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2012                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants and required method
 * associated with the die roll modifier (DRM) attribute of a <A HREF="Leader.html">Leader</A>. The method
 * is intended for operation on an integer member variable within the
 * implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2012-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Leadership.html">Source code</A>
 */

public interface Leadership
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the
	// modifier associated with a <A HREF="Leader.html">Leader</A> using an objects toText() method.

	/**
	 * Provides a label for a leaders modifier value : <B>Modifier</B>
	 */

	public static final String MODIFIER_LABEL = "Modifier";

	// The following constants define the possible range of leadership
	// modifier values.

	/** Minimum valid leadership modifier value : <B>-3</B> */

	public static final int MIN_LEADERSHIP_MODIFIER = -3;

	/** Maximum valid leadership modifier value : <B>1</B> */

	public static final int MAX_LEADERSHIP_MODIFIER = 1;

	// Access methods

	/**
	 * Return the dice roll modifier (DRM) of a leader.
	 *
	 * @return an <CODE>int</CODE> specifying the modifier value.
	 */

	public abstract int modifier();
}
