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
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Leader.java,v 1.1 1999/06/23 05:30:06 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Leader class - This class is used to define the unique characteristics of  //
//                leader "units". Instances of this class may be instantiated //
//                directly.                                                   //
// ************************************************************************** //

public class Leader extends Infantry
{
	// Private data members

	// This variable contains the modifier available to the derived object of
	// this class (ie. "-2"). This modifier allows the leader to affect the 
	// outcome of actions that affect other "units" that share the same "space".

	private byte modifier;

	// This variable contains the name associated with to the derived object of
	// this class (ie. "Maj. Campbell"). It is used strictly as an identifier.

	private String name;

	// Constructors

	// This constructor is used to instantiate a Leader object.

	public Leader(String nationality,byte movement,byte morale,byte
	              brokenMorale,boolean canSelfRally,byte modifier,String name)
	{
		// After passing the first 7 parameters to the superclass constructor,
		// check the values of the remaining 2 parameters and copy the values to
		// the local copy of each corresponding variable.

		super("Leader",nationality,movement,morale,brokenMorale,canSelfRally);

		if ((modifier > -4) && (modifier < 4))
		{
			this.modifier = modifier;
		}

		if (name.length() > 0)
		{
			this.name = name;
		}
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

		System.out.println("Name\t: " + name + "\tModifier\t: " + modifier);
	}

	// getModifier - A function to return the value of the modifier member
	//               variable to the calling program.

	public byte getModifier()
	{
		return (modifier);
	}

	// getName - A function to return the value of the name member variable to 
	//           the calling program.

	public String getName()
	{
		return (name);
	}
}
