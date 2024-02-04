// ************************************************************************** //
// Phase.java - This interface is part of the <B>ui.data</B> package, which contains //
//              the class definitions and implementations for objects used to //
//              store and manage the state of an instance of jASL.            //
//                                                                            //
//              NOTE: This program is based on Advanced Squad Leader, which   //
//                    was created by The Avalon Hill Game Company, and lives  //
//                    on at Multi-Man Publishing.                             //
//                                                                            //
// Written By: Craig R. Campbell  -  February 2016                            //
// ************************************************************************** //

package jasl.ui.data;

/**
 * This interface is used to define the public constants, using an enum, and
 * required methods associated with the phases within each half of a turn during
 * a jASL session.
 *
 * The methods are intended for operation on an Phases enum member variable
 * within the implementing class.
 *
 * @version 2.0
 * @author Copyright (C) 2016-2024 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Phase.html">Source code</A>
 */

public interface Phase
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the current
	// phase using an implementing class's toText() method.

	/**
	 * Provides a label for a current phase : <B>Phase</B>
	 */

	public static final String PHASE_LABEL = "Phase";

	/**
	 * Recognized phase values.
	 */

	public enum Phases
	{
		/** <A NAME="_RALLY_"></A>
		 * Indicates that the current segment of a turn is the <B>Rally</B>
		 * phase.
		 */

		RALLY("Rally"),

		/** <A NAME="_PREP_FIRE_"></A>
		 * Indicates that the current segment of a turn is the <B>Prep Fire</B>
		 * phase.
		 */

		PREP_FIRE("Prep Fire"),

		/** <A NAME="_MOVEMENT_"></A>
		 * Indicates that the current segment of a turn is the <B>Movement</B>
		 * phase.
		 */

		MOVEMENT("Movement"),

		/** <A NAME="_DEFENSIVE_FIRE_"></A>
		 * Indicates that the current segment of a turn is the
		 * <B>Defensive Fire</B> phase.
		 */

		DEFENSIVE_FIRE("Defensive Fire"),

		/** <A NAME="_ADVANCING_FIRE_"></A>
		 * Indicates that the current segment of a turn is the
		 * <B>Advancing Fire</B> phase.
		 */

		ADVANCING_FIRE("Advancing Fire"),

		/** <A NAME="_ROUT_"></A>
		 * Indicates that the current segment of a turn is the <B>Rout</B>
		 * phase.
		 */

		ROUT("Rout"),

		/** <A NAME="_ADVANCE_"></A>
		 * Indicates that the current segment of a turn is the <B>Advance</B>
		 * phase.
		 */

		ADVANCE("Advance"),

		/** <A NAME="_CLOSE_COMBAT_"></A>
		 * Indicates that the current segment of a turn is the
		 * <B>Close Combat</B> phase.
		 */

		CLOSE_COMBAT("Close Combat");

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// Constructor

		Phases(String label)
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
	 * Return the current phase within a turn.
	 *
	 * @return a <CODE>Phases</CODE> value specifying the phase.
	 */

	public abstract Phases phase();

	// Update methods

	/**
	 * Move to the next phase within a turn.
	 *
	 * If the current phase is the last one (Close Combat) the new setting
	 * should be the first one (Rally).
	 *
	 * @return a <CODE>boolean</CODE> indicating if the phase within the turn was changed
	 * as a result of calling this method.
	 */

	public abstract boolean nextPhase();
}
