// ************************************************************************** //
// Unit.java - This class is a member of the <B>counters</B> package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Game Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/Products/tabid/58/CategoryID/4/Default.aspx">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
// ************************************************************************** //

package jasl.counters;

import java.io.Serializable;

import jasl.utilities.Messages;

/**
 * This class is used to define the basic components of a counter. It is
 * intended strictly as a superclass, not to be instantiated directly.
 *
 * @version 4.0
 * @author Copyright (C) 1998-2015 Craig R. Campbell (craigonic@gmail.com)
 * @see <A HREF="../../../source/jasl/counters/Unit.html">Source code</A>
 */

public abstract class Unit implements Serializable, TextOutput, Description
{
	// Private data members

	// This item provides a descriptive name for the derived object of this
	// class. It is set to the enum value associated with name of the class
	// being instantiated (e.g. <A HREF="Description.html#_SQUAD_">"Squad"</A>).

	private Descriptions _description;

	// Constructors.

	// During the instantiation of derived concrete classes the parameter
	// is passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Unit(Descriptions description)
	{
		_description = description;
	}

	// This declaration is redundant for Java and C++ usage, but it is
	// necessary in order for it to appear in the <A HREF="http://gcc.gnu.org/onlinedocs/gcj/About-CNI.html#About-CNI">CNI</A> header file, which is
	// used by <A HREF="http://www.swig.org/">SWIG</A> to build bindings for scripting languages.

	protected Unit() {}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe
	 * the current instance.
	 *
	 * Each value is preceded by a label defined in this class or the
	 * interface associated with the item. There are no more than two
	 * values, including labels, in each line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toText()
	{
		// Create a buffer to store the string to be returned,
		// initializing it with the values that define the header (since
		// this is the top level of the class hierarchy).

		StringBuffer returnString = new StringBuffer();

		// Add the information describing the data stored in this class
		// instance.

		returnString.append(Messages.formatTextString(DESCRIPTION_LABEL,
		                                              FIRST_COLUMN_LABEL_WIDTH,
		                                              true,false));

		returnString.append(Messages.formatTextString(description(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	// The toString() method implementation is included here for the same
	// reason as the empty constructor above.

	/**
	 * Return an abbreviated description, which may include attributes, of a
	 * unit.
	 *
	 * This method overrides the parent implementation, and it is intended
	 * that derived public classes provide their own version. If not, this
	 * one will return the same value as description().
	 *
	 * @return a <CODE>String</CODE> specifying a simple description of the unit.
	 */

	public String toString()
	{
		return description();
	}

	/**
	 * Return the description of a unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit description.
	 */

	public final String description()
	{
		return _description.label();
	}
}
