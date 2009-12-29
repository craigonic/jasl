// ************************************************************************** //
// Unit.java - This class is a member of the <B>counters</B> package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, which    //
//                   was created by The Avalon Hill Game Company, and lives   //
//                   on at <A HREF="http://www.multimanpublishing.com/ASL/asl.php">MultimanPublishing.com</A>.                            //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
//                                                                            //
// $Id: Unit.java,v 1.17 2009/12/29 06:44:26 craig Exp $
// ************************************************************************** //

package jasl.counters;

import java.io.*; // For Serializable
import jasl.utilities.Messages;

/**
 * This class is used to define the basic components of a counter.
 *
 * @version 1.17
 * @author Craig R. Campbell
 * @see <A HREF="../../../source/jasl/counters/Unit.html">Source code</A>
 */

public abstract class Unit implements Serializable, TextOutput, Description
{
	// Protected symbolic constants

	/** <A NAME="_NORMAL_"></A>
	 * Indicates that the unit's current status is <B>Normal</B>
	 *
	 * @see #getStatus
	 */

	protected static final String NORMAL = "Normal";

	// Private data members

	// This item provides a descriptive name for the derived object of this
	// class. It is set to the enum value associated with name of the class
	// being instantiated (ie. <A HREF="Description.html#_SQUAD_">"Squad"</A>).

	private Descriptions description;

	// Constructor.

	// During the instantiation of derived concrete classes the parameter
	// is passed up the inheritance tree from the constructor of the object
	// type being created.

	protected Unit(Descriptions description)
	{
		this.description = description;
	}

	// Public access methods

	/**
	 * Display the value of each of the private data members that describe
	 * the current instance. Each value is preceded by a label defined in
	 * this class or the interface associated with the item. There are no
	 * more than two values, including labels, in each line of output.
	 *
	 * @return a multi-line tabular <CODE>String</CODE>, 80 characters wide.
	 */

	public String toString()
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

		returnString.append(Messages.formatTextString(getDescription(),
		                                              SECOND_COLUMN_VALUE_WIDTH,
		                                              false,true));

		// Return the completed string to calling program.

		return returnString.toString();
	}

	/**
	 * Return the description of this unit. This is the name of the public
	 * derived subclass that was specified to create this object. The
	 * recognized values are listed <A HREF="Description.html">here</A>.
	 *
	 * @return a <CODE>String</CODE> specifying the unit description.
	 */

	public String getDescription()
	{
		return description.label();
	}

	// The following abstract methods are defined in the subclasses of Unit.
	// This is necessary in order to allow different public class types
	// derived from Unit to be stored and accessed as the generic Unit type.
	// It is also necessary in order to access the public access methods of
	// the entire hierarchy without casting to a specific class type.

	// Fighting.java

	/**
	 * Return the identity of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's identity.
	 *
	 * @see Fighting#getIdentity
	 */

	abstract public String getIdentity();

	/**
	 * Return the type of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying a more precise description of the unit
	 * type.
	 *
	 * @see Fighting#getUnitType
	 */

	abstract public String getUnitType();

	/**
	 * Return the firepower of this unit.
	 *
	 * @return a <CODE>String</CODE> specifying the unit's firepower.
	 *
	 * @see Fighting#getFirepower
	 */

	abstract public String getFirepower();

	/**
	 * Return the maximum range that this unit may fire its weapon(s) at
	 * full effect.
	 *
	 * @return a <CODE>String</CODE> specifying the normal range of the unit's weapon(s).
	 *
	 * @see Fighting#getNormalRange
	 */

	abstract public String getNormalRange();

	/**
	 * Return the current status of this unit.
	 *
	 * @return a comma delimited <CODE>String</CODE> describing the unit status.
	 *
	 * @see Fighting#getStatus
	 * @see Infantry#getStatus
	 */

	abstract public String getStatus();

	/**
	 * Attempt to restore this unit's status to <A HREF="#_NORMAL_">NORMAL</A>.
	 *
	 * @param isLeaderPresent indicates if a <B><A HREF="Leader.html">Leader</A></B> is present, which may
	 * determine if a restoration attempt can be made or not.
	 * @param modifier the applicable dice roll modifier for the attempt.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
	 * result of calling this method.
	 *
	 * @see Fighting#restore
	 * @see Infantry#restore
	 */

	abstract public boolean restore(boolean isLeaderPresent,int modifier);

	/**
	 * Perform a morale or task check on this unit.
	 *
	 * @param modifier the applicable dice roll modifier for the check.
	 * This includes leadership DRM as well as other factors.
	 *
	 * @return a <CODE>boolean</CODE> indicating if the status of a unit is changed as a
	 * result of calling this method.
	 *
	 * @see Fighting#check
	 * @see Infantry#check
	 */

	abstract public boolean check(int modifier);

	// Mobile.java

	/**
	 * Return the number of movement factors or points available to this
	 * unit before it begins to move.
	 *
	 * @return a <CODE>String</CODE> specifying the movement capability in factors or
	 * points.
	 *
	 * @see Mobile#getMovement
	 */

	abstract public String getMovement();
}
