// ************************************************************************** //
// Nationality.java - This interface is part of the <B>counters</B> package, which   //
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
 * required method associated with the nationality of a <A HREF="Fighting.html">Fighting</A> unit. The
 * method is intended for operation on a String member variable within the
 * implementing class.
 *
 * @version 3.0
 * @author Copyright (C) 2006-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Nationality.html">Source code</A>
 */

public interface Nationality
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the
	// nationality of a <A HREF="Unit.html">Unit</A> using an objects toText() method.

	/**
	 * Provides a label for a unit's nationality : <B>Nationality</B>
	 */

	public static final String NATIONALITY_LABEL = "Nationality";

	/**
	 * Recognized nationality values. The nationality best describes the
	 * army that the <A HREF="Unit.html">unit</A> belongs to, not necessarily the true nationality
	 * of the soldiers in it.
	 */

	public enum Nationalities
	{
		/** <A NAME="_ALLIED_MINOR_"></A>
		 * Indicates that a unit's nationality is <B>Allied Minor</B>.
		 */

		ALLIED_MINOR("Allied Minor"),

		/** <A NAME="_AMERICAN_"></A>
		 * Indicates that a unit's nationality is <B>American</B>.
		 */

		AMERICAN("American"),

		/** <A NAME="_AXIS_MINOR_"></A>
		 * Indicates that a unit's nationality is <B>Axis Minor</B>.
		 */

		AXIS_MINOR("Axis Minor"),

		/** <A NAME="_BRITISH_"></A>
		 * Indicates that a unit's nationality is <B>British</B>.
		 */

		BRITISH("British"),

		/** <A NAME="_FINNISH_"></A>
		 * Indicates that a unit's nationality is <B>Finnish</B>.
		 */

		FINNISH("Finnish"),

		/** <A NAME="_FRENCH_"></A>
		 * Indicates that a unit's nationality is <B>French</B>.
		 */

		FRENCH("French"),

		/** <A NAME="_GERMAN_"></A>
		 * Indicates that a unit's nationality is <B>German</B>.
		 */

		GERMAN("German"),

		/** <A NAME="_ITALIAN_"></A>
		 * Indicates that a unit's nationality is <B>Italian</B>.
		 */

		ITALIAN("Italian"),

		/** <A NAME="_JAPANESE_"></A>
		 * Indicates that a unit's nationality is <B>Japanese</B>.
		 */

		JAPANESE("Japanese"),

		/** <A NAME="_PARTISAN_"></A>
		 * Indicates that a unit's nationality is <B>Partisan</B>.
		 */

		PARTISAN("Partisan"),

		/** <A NAME="_RUSSIAN_"></A>
		 * Indicates that a unit's nationality is <B>Russian</B>.
		 */

		RUSSIAN("Russian");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		Nationalities(String label)
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
	 * Return the nationality of a unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's nationality.
	 */

	public abstract String nationality();
}
