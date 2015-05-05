// ************************************************************************** //
// Description.java - This interface is part of the <B>counters</B> package, which   //
//                    contains the class definitions and implementations for  //
//                    objects used to represent the virtual playing pieces in //
//                    jASL.                                                   //
//                                                                            //
//                    NOTE: This program is based on Advanced Squad Leader,   //
//                          which was created by The Avalon Hill Game         //
//                          Company, and lives on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.    //
//                                                                            //
// Written By: Craig R. Campbell  -  December 2006                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants, using an enum, and
 * required method associated with the general description of a <A HREF="Unit.html">Unit</A>. The method
 * is intended for operation on a String member variable within the implementing
 * class.
 *
 * @version 3.0
 * @author Copyright (C) 2006-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Description.html">Source code</A>
 */

public interface Description
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the
	// description of a <A HREF="Unit.html">Unit</A> using an objects toText() method.

	/**
	 * Provides a label for a unit's counter type : <B>Description</B>
	 */

	public static final String DESCRIPTION_LABEL = "Description";

	/**
	 * Recognized description values. These constants represent the counter
	 * types that may be directly instantiated using the public classes in
	 * the <B>counters</B> hierarchy.
	 */

	public enum Descriptions
	{
		/** <A NAME="_CREW_"></A>
		 * Indicates that the counter type of a unit is <B>Crew</B>.
		 */

		CREW("Crew"),

		/** <A NAME="_HALF_SQUAD_"></A>
		 * Indicates that the counter type of a unit is <B>Half Squad</B>.
		 */

		HALF_SQUAD("Half Squad"),

		/** <A NAME="_LEADER_"></A>
		 * Indicates that the counter type of a unit is <B>Leader</B>.
		 *
		 * @see Leader
		 */

		LEADER("Leader"),

		/** <A NAME="_SQUAD_"></A>
		 * Indicates that the counter type of a unit is <B>Squad</B>.
		 *
		 * @see Squad
		 */

		SQUAD("Squad");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		Descriptions(String label)
		{
			_label = label;
		}

		// Public access method

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the constant.
		 */

		public String toString()
		{
			return _label;
		}
	}

	// Access methods

	/**
	 * Return the description of a unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit description.
	 */

	public abstract String description();
}
