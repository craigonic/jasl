// ************************************************************************** //
// Unit.java - This class is a member of the Counters package, which contains //
//             the class definitions and implementations for objects used to  //
//             to represent the virtual playing pieces in jASL.               //
//                                                                            //
//             NOTE: This program is based on Advanced Squad Leader, a        //
//                   product of The Avalon Hill Game Company.                 //
//                                                                            //
// Written By: Craig R. Campbell  -  December 1998                            //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Unit.java,v 1.1 1999/06/09 06:00:44 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Unit class - This is the top-level class of the Counter package. All       //
//              the classes in this Package are derived from Unit.            //
// ************************************************************************** //

abstract public class Unit
{
	// Private data members

	// This variable contains a descriptive name for the derived object of this
	// class. It is typically set to the name of the child class being
	// instantiated (ie. "Squad").

	private String description;

	// Constructors

	// Default constructor. This is used to create arrays of this data type
	// which can be used to reference groups of objects derived from Unit.

	public Unit() {}
	
	// This constructor is used during the instantiation of classes derived
	// from Unit. The parameter is passed up the chain from the object being
	// created.

	protected Unit(String description)
	{
		// If the length of the String parameter passed to this function is
		// greater than zero, assume that it is OK and copy the value to the
		// local description variable. 

		if (description.length() > 0)
		{
			this.description = description;
		}
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Unit and each subclass includes a function
	//              with the same name and purpose. Since this is the top-level
	//              of the hierarchy, this version also includes a header.

	protected void showValues()
	{
		// Display header.

		System.out.println("Counter package instance values:");
		System.out.println("--------------------------------");

		// Display data stored in this class.

		System.out.println("Description\t: " + description);
	}

	// Public access methods

	// getDescription - A function to return the value of the description member
	//                  variable to the calling program.

	public String getDescription()
	{
		return (description);
	}

	// The following abstract methods are defined in the subclasses of Unit.
	// This is necessary in order to allow different types of instances derived
	// from Unit to be stored and accessed as the generic Unit type. It is also
	// necessary in order to access the public access methods without casting to
	// a specific instance type.

	// Fighting.java

	abstract public String getNationality();

	// Mobile.java

	abstract public byte getMovement();

	// Infantry.java

	abstract public String getStatus();
	abstract public boolean rally(boolean isLeaderPresent,byte modifier);
	abstract public boolean moraleCheck(byte modifier);

	// Personnel.java

 	abstract public byte getFirepower();
 	abstract public byte getNormalRange();

	// Leader.java
}
