// ************************************************************************** //
// Status.java - This interface is part of the <B>ui.data</B> package, which         //
//               contains the class definitions and implementations for       //
//               objects used to store and manage the state of an instance of //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at Multi-Man Publishing.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2011                            //
// ************************************************************************** //

package jasl.ui.data;

import java.util.List;

/**
 * This interface is used to define the public constants, using an enum, and
 * required methods associated with the status of a <A HREF="../../counters/Unit.html">Unit</A>.
 * A <U>Unit</U> primarily defines attributes so its state is managed through a <A HREF="Stack.html">Stack</A>.
 * Note that in this case a "Stack" refers to the management of a single unit,
 * not a group of units. This is reflected in this documentation by the use of
 * "unit" instead of "stack".
 *
 * The methods are intended for operation on a 64 bit integer member variable
 * within the implementing class.
 *
 * @version 3.1
 * @author Copyright (C) 2011-2024 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../../source/jasl/ui/data/Status.html">Source code</A>
 */

public interface Status
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the status
	// of a unit using an implementing class's toText() method.

	/**
	 * Provides a label for a unit's status : <B>Status</B>
	 */

	public static final String STATUS_LABEL = "Status";

	/**
	 * Recognized status values.
	 * <P>
	 * These constants include a corresponding label and a value, which is
	 * intended for use as a mask to set or check for a specific value.
	 */

	public enum States
	{
		/** <A NAME="_NORMAL_"></A>
		 * Indicates that the status of a unit is <B>Normal</B>.
		 */

		NORMAL("Normal",0x00000000),

		// The following items typically reflect the state of a counter
		// when it is inverted.

		// <A HREF="../../counters/Infantry.html">Infantry</A> specific states.

		/** <A NAME="_BROKEN_"></A>
		 * Indicates that the status of a unit is <B>Broken</B>.
		 */

		BROKEN("Broken",0x00000001),

		/** <A NAME="_DESPERATE_"></A>
		 * Indicates that the status of a unit is <B>Desperate</B>.
		 *
		 * Note that this status is associated directly with BROKEN
		 * (i.e., it is expected that a DESPERATE unit is also BROKEN).
		 * This is to be enforced through the method implementations.
		 */

		DESPERATE("Desperate",0x00000002);

		// Private data members

		// The label associated with the enum constant.

		private final String _label;

		// The value associated with the enum constant. This value
		// should represent a single bit in a 64-bit integer.

		private final long _value;

		// Constructor

		States(String label,long value)
		{
			_label = label;
			_value = value;
		}

		// Public access methods

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the constant.
		 */

		public final String toString()
		{
			return _label;
		}

		/**
		 * Returns the value associated with the enum constant.
		 *
		 * @return the <CODE>long</CODE> (bit mask) associated with the constant.
		 */

		public final long value()
		{
			return _value;
		}
	}

	// Access methods

	/**
	 * Return the current status of a unit.
	 *
	 * @return an <B>unmodifiable</B> <CODE>List</CODE> of States describing the unit status.
	 *
	 * @see #clearStatus
	 * @see #setStatus
	 */

	public abstract List<States> status();

	// Update methods

	/**
	 * Change the status of a unit.
	 *
	 * @param state the state to be cleared or removed. This value will
	 * be used as a mask to clear the corresponding bit in the status value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see #status
	 */

	public abstract boolean clearStatus(States state);

	/**
	 * Change the status of a unit.
	 * <P>
	 * The interpretation of the parameter value will be determined by the
	 * implementor of this interface.
	 *
	 * @param state the state to be set or applied. This value will be
	 * used as a mask to set the corresponding bit in the status value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see #status
	 */

	public abstract boolean setStatus(States state);
}
