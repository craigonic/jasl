// ************************************************************************** //
// Classification.java - This interface is part of the <B>counters</B> package,      //
//                       which contains the class definitions and             //
//                       implementations for objects used to represent the    //
//                       virtual playing pieces in jASL.                      //
//                                                                            //
//                       NOTE: This program is based on Advanced Squad        //
//                             Leader, which was created by The Avalon Hill   //
//                             Game Company, and lives on at                  //
//                             Multi-Man Publishing.                          //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2006                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define public constants, using an enum, and
 * required method associated with the classification of <A HREF="Personnel.html">Personnel</A> units. The
 * method is intended for operation on a Classifications enum member variable
 * within the implementing class.
 *
 * @version 5.0
 * @author Copyright (C) 2006-2017 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Classification.html">Source code</A>
 */

public interface Classification
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the
	// classification of a <A HREF="Personnel.html">Personnel</A> unit using an object's toText()
	// method.

	/**
	 * Provides a label for a personnel (MMC) unit's classification :
	 * <B>Classification</B>
	 *
	 * @see Personnel
	 */

	public static final String CLASSIFICATION_LABEL = "Classification";

	/**
	 * Recognized classification values.
	 */

	public enum Classifications
	{
		/** <A NAME="_SS_"></A>
		 * Indicates that the classification of a unit is <B>SS</B>.
		 * <P>
		 * This value is applicable (obviously) only when the
		 * nationality of the unit is <B>German</B>.
		 *
		 * @see Nationality.Nationalities#GERMAN
		 */

		SS("SS"),

		/** <A NAME="_ELITE_"></A>
		 * Indicates that the classification of a unit is <B>Elite</B>.
		 */

		ELITE("Elite"),

		/** <A NAME="_FIRST_LINE_"></A>
		 * Indicates that the classification of a unit is <B>1st Line</B>.
		 */

		FIRST_LINE("1st Line"),

		/** <A NAME="_SECOND_LINE_"></A>
		 * Indicates that the classification of a unit is <B>2nd Line</B>.
		 */

		SECOND_LINE("2nd Line"),

		/** <A NAME="_GREEN_"></A>
		 * Indicates that the classification of a unit is <B>Green</B>.
		 */

		GREEN("Green"),

		/** <A NAME="_CONSCRIPT_"></A>
		 * Indicates that the classification of a unit is <B>Conscript</B>.
		 */

		CONSCRIPT("Conscript"),

		/** <A NAME="_NONE_"></A>
		 * Indicates that a classification does not apply to a Personnel
		 * unit.
		 * <P>
		 * This value is applicable only when the "nationality" of the
		 * unit is <B>Partisan</B>.
		 *
		 * @see Nationality.Nationalities#PARTISAN
		 */

		NONE("");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		Classifications(String label)
		{
			_label = label;
		}

		// Public access method

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the constant.
		 */

		public final String toString()
		{
			return _label;
		}
	}

	// Access methods

	/**
	 * Return the classification of a unit.
	 * <P>
	 * This is indicated on the front of the physical counter by an
	 * alphanumeric character in the upper right corner.
	 *
	 * @return a <CODE>Classifications</CODE> value specifying the unit classification.
	 */

	public abstract Classifications classification();
}
