// ************************************************************************** //
// Status.java - This interface is part of the <B>counters</B> package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, which  //
//                     was created by The Avalon Hill Game Company, and lives //
//                     on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">Multi-Man Publishing</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  November 2011                            //
// ************************************************************************** //

package jasl.counters;

/**
 * This interface is used to define the public constants, using an enum, that
 * describe the status of a <A HREF="Unit.html">Unit</A>. It also includes
 * required methods to set and retrieve it. These methods are intended for
 * operation on an integer member variable within the implementing class.
 *
 * @version 1.0
 * @author Copyright (C) 2011 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Status.html">Source code</A>
 */

public interface Status
{
	// Symbolic constants

	// This constant is provided primarily for use in displaying the output
	// of the status() method in the output of a <A HREF="Unit.html">Unit</A> objects toString()
	// method.

	/**
	 * Provides a label for a units status : <B>Status</B>
	 */

	public static final String STATUS_LABEL = "Status";

	// The following constants are the text labels associated with each
	// element of the States enum. They are used in the String returned by
	// the status() method.

	/** <A NAME="_NORMAL_"></A>
	 * Indicates that a units current status is <B>Normal</B>.
	 */

	public static final String NORMAL = "Normal";

	// Infantry status labels

	/** <A NAME="_BROKEN_"></A>
	 * Indicates that a units current status is <B>Broken</B>.
	 */

	public static final String BROKEN = "Broken";

	/** <A NAME="_DESPERATE_"></A>
	 * Indicates that a units current status is <B>Desperate</B>.
	 */

	public static final String DESPERATE = "Desperate";

	/**
	 * Recognized status values. These constants include a corresponding
	 * label and a value, which is intended for use as a mask to set or
	 * check for a specific value.
	 */

	public enum States
	{
		/** <A NAME="_NORMAL_STATE_"></A>
		 * Indicates that the status of a unit is <B>Normal</B>.
		 */

		NORMAL(NORMAL,0x00000000),

		// The following items typically reflect the state of a counter
		// when it is inverted.

		// <A HREF="Infantry.html">Infantry</A> specific states.

		/** <A NAME="_BROKEN_STATE_"></A>
		 * Indicates that the status of a unit is <B>Broken</B>.
		 */

		BROKEN(BROKEN,0x00000001),

		/** <A NAME="_DESPERATE_STATE_"></A>
		 * Indicates that the status of a unit is <B>Desperate</B>.
		 */

		DESPERATE(DESPERATE,0x00000002);

		// Private data members

		// The label associated with the enum constant.

		private final String label;

		// The value associated with the enum constant. This value
		// should represent a single bit in a 32-bit integer.

		private final int value;

		// Constructor

		States(String label,int value)
		{
			this.label = label;
			this.value = value;
		}

		// Access methods

		/**
		 * Returns the label associated with the enum constant.
		 *
		 * @return the <CODE>String</CODE> associated with the enum element.
		 */

		public String label()
		{
			return label;
		}

		/**
		 * Returns the value associated with the enum constant.
		 *
		 * @return the <CODE>int</CODE> (bit mask) associated with the enum element.
		 */

		public int value()
		{
			return value;
		}
	}

	// Access methods

	/**
	 * Return the current status of a unit.
	 *
	 * @return a comma delimited <CODE>String</CODE> describing the unit status. The list
	 * will include one or more of the string constants, defined in this
	 * interface, that are associated with unit status.
	 *
	 * @see #setStatus
	 */

	public abstract String status();

	/**
	 * Determine if the status of a unit includes the indicated state.
	 *
	 * @param state the state of interest. This value will be used as a
	 * mask to determine if the corresponding bit is set.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the unit is in the specified state.
	 */

	public abstract boolean isStatusSet(States state);

	// Update methods

	/**
	 * Change the status of a unit. The interpretation of the parameter
	 * value will be determined by the implementor of this interface.
	 *
	 * @param state the new state. This value will be used as a mask
	 * to set the corresponding bit in the status value.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of the unit was changed as
	 * a result of calling this method.
	 *
	 * @see #status
	 */

	public abstract boolean setStatus(States state);
}
