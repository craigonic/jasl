// ************************************************************************** //
// Leader.java - This class is a member of the Counters package, which        //
//               contains the class definitions and implementations for       //
//               objects used to represent the virtual playing pieces in      //
//               jASL.                                                        //
//                                                                            //
//               NOTE: This program is based on Advanced Squad Leader, a      //
//                     product of The Avalon Hill Game Company.               //
//                                                                            //
// Written By  : Craig R. Campbell  -  December 1998                          //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Leader.java,v 1.3 2000/08/05 07:14:46 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Leader class - This class is used to define the unique characteristics of  //
//                leader "units". Instances of this class may be instantiated //
//                directly.                                                   //
// ************************************************************************** //

public class Leader extends Infantry
{
	// Private symbolic constants

	// These constants are used to determine if the value of the modifier
	// parameter passed to the constructor is valid.

	private static final int MIN_MODIFIER       = -3;
	private static final int MAX_MODIFIER       =  3;

	// These constants are used in the constructor to pass the correct value
	// of a Leader for each attribute. Other types of units may allow the 
	// calling program to set these values but they are the same for all
	// Leaders.

	private static final int MOVEMENT_ALLOWANCE = 6;
	private static final int PORTAGE_CAPACITY   = 1;
	private static final int PORTAGE_VALUE      = 0;

	// Private data members

	// This variable contains the modifier available to the derived object of
	// this class (ie. "-2"). This modifier allows the leader to affect the 
	// outcome of actions that affect other "units" that share the same "space".

	private int modifier;

	// The following string is used as a message for any exceptions that may be
	// generated by bad data being passed to the constructor.

	private static final String invalidArgumentError = 
	    "Error: Leader(constructor) - Invalid parameter received : ";

	// Constructor

	// This constructor is used to instantiate a Leader object.

	public Leader(String nationality,String identity,String unitType,int morale,
	              int brokenMorale,boolean canSelfRally,
	              int experienceLevelRating,int modifier)
		throws IllegalArgumentException
	{
		// Pass the first 7 parameters to the superclass constructor. Note
		// that several variables have been set with symbolic constants. These
		// are defined at the beginning of this class and its superclasses.
		// If any exceptions are thrown, assume that they will be caught and
		// handled by the program creating the object.

		super(LEADER,nationality,identity,unitType,MIN_FIREPOWER,MIN_RANGE,
		      MOVEMENT_ALLOWANCE,PORTAGE_CAPACITY,PORTAGE_VALUE,morale,
		      brokenMorale,canSelfRally,MIN_BPV,experienceLevelRating,
		      DEFAULT_RESULT);

		// Check the value of the remaining parameter and copy the value to
		// the local copy of the variable if an exception is not found.

		// Modifier

		if ((modifier < MIN_MODIFIER) || (modifier > MAX_MODIFIER))
		{
			throw new IllegalArgumentException(invalidArgumentError + modifier);
		}

		this.modifier = modifier;
	}

	// Public access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging.

	public void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Modifier                : " + modifier);
	}

	// getModifier - A function to return the value of the modifier member
	//               variable to the calling program.

	public int getModifier()
	{
		return (modifier);
	}
}
