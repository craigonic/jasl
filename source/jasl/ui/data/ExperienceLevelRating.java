// ************************************************************************** //
// ExperienceLevelRating.java - This interface is part of the <B>ui.data</B>         //
//                              package, which contains the class definitions //
//                              and implementations for objects used to store //
//                              and manage the state of an instance of jASL.  //
//                                                                            //
//                              NOTE: This program is based on Advanced Squad //
//                                    Leader, which was created by The Avalon //
//                                    Hill Game Company, and lives on at      //
//                                    Multi-Man Publishing.                   //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2016                            //
// ************************************************************************** //

package jasl.ui.data;

/**
 * This interface is used to define the public constants, using an enum, and
 * required methods associated with the experience level rating (ELR) of a
 * <A HREF="Stack.html">Stack</A>.
 *
 * Although the value generally applies to a side it will be set in the
 * applicable Stacks to allow for cases where it may differ (e.g., some of the
 * units inherently receive the maximum ELR while the others receive the value
 * set in the scenario). For (most) Stacks, where ELR does not apply the value
 * is set to zero. When battlefield integrity rules are in effect, changes
 * to the ELR will affect all of the units on a side.
 *
 * Note that in this context "Stack" refers to the management of a single unit,
 * not a group of units. This is reflected in this documentation by the use of
 * "unit" instead of "stack".
 *
 * The methods are intended for operation on an enum variable within the
 * implementing class.
 *
 * @version 2.0
 * @author Copyright (C) 2016-2024 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/ExperienceLevelRating.html">Source code</A>
 */

public interface ExperienceLevelRating
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the current
	// ELR of a unit using an implementing class's toText() method.

	/**
	 * Provides a label for a unit's experience level rating :
	 * <B>Experience Level Rating</B>
	 */

	public static final String ELR_LABEL = "Experience Level Rating";

	/**
	 * Recognized experience level rating values.
	 */

	public enum ExperienceLevelRatings
	{
		/** <A NAME="_ELR_N_A_"></A>
		 * Indicates that the experience level rating is not applicable
		 * to a unit.
		 */

		ELR_N_A("n/a",0),

		/** <A NAME="_ELR_ZERO_"></A>
		 * Indicates that the experience level rating of a unit is <B>0</B>.
		 */

		ELR_ZERO("0",0),

		/** <A NAME="_ELR_ONE_"></A>
		 * Indicates that the experience level rating of a unit is <B>1</B>.
		 */

		ELR_ONE("1",1),

		/** <A NAME="_ELR_TWO_"></A>
		 * Indicates that the experience level rating of a unit is <B>2</B>.
		 */

		ELR_TWO("2",2),

		/** <A NAME="_ELR_THREE_"></A>
		 * Indicates that the experience level rating of a unit is <B>3</B>.
		 */

		ELR_THREE("3",3),

		/** <A NAME="_ELR_FOUR_"></A>
		 * Indicates that the experience level rating of a unit is <B>4</B>.
		 */

		ELR_FOUR("4",4),

		/** <A NAME="_ELR_FIVE_"></A>
		 * Indicates that the experience level rating of a unit is <B>5</B>.
		 */

		ELR_FIVE("5",5);

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// The value associated with the enum constant.

		private final int _value;

		// Constructor

		ExperienceLevelRatings(String label,int value)
		{
			_label = label;
			_value = value;
		}

		// Public access methods

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the enum element.
		 */

		public final String toString()
		{
			return _label;
		}

		/**
		 * Returns the value associated with the enum constant.
		 *
		 * @return the <CODE>int</CODE> associated with the enum element.
		 */

		public final int value()
		{
			return _value;
		}
	}

	// Access methods

	/**
	 * Return the experience level rating of a unit.
	 *
	 * If applicable to a unit this is a value between 0 and 5, inclusive.
	 * Otherwise, it is ELR_N_A.
	 *
	 * @return an <CODE>ExperienceLevelRatings</CODE> specifying the experience level
	 * rating for the unit.
	 */

	public abstract ExperienceLevelRatings experienceLevelRating();

	// Update methods

	// Note that although these methods are apply to a single unit (Stack)
	// the action should be performed for <B>all</B> of the units on a side.

	/**
	 * Raise the experience level rating of a unit.
	 *
	 * If applicable to the unit and the current rating is not the maximum
	 * (5) the rating is increased by one.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the ELR of a unit was changed as a
	 * result of calling this method.
	 */

	public abstract boolean increaseELR();

	/**
	 * Lower the experience level rating of a unit.
	 *
	 * If applicable to the unit and the current rating is not the minimum
	 * (0) the rating is decreased by one.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the ELR of a unit was changed as a
	 * result of calling this method.
	 */

	public abstract boolean decreaseELR();
}
