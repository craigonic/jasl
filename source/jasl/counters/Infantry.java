// ************************************************************************** //
// Infantry.java - This class is a member of the Counters package, which      //
//                 contains the class definitions and implementations for     //
//                 objects used to represent the virtual playing pieces in    //
//                 jASL.                                                      //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader, a    //
//                       product of The Avalon Hill Game Company.             //
//                                                                            //
// Written By    : Craig R. Campbell  -  December 1998                        //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Infantry.java,v 1.1 1999/06/23 05:05:08 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Infantry class - This class, though derived from Unit, Fighting and        //
//                  Mobile, is the top-level class for describing the basic   //
//                  attributes of individual (leaders) and groups (squads) of //
//                  soldiers. Further detail for each type is provided by the //
//                  subclasses of Infantry. Instances of this class may not   //
//                  be instantiated directly. It is strictly a parent class.  //
// ************************************************************************** //

class Infantry extends Mobile
{
	// Private data members

	// This variable contains the normal morale value of the derived object of
	// this class.

	private byte morale;

	// This variable contains the morale value of the derived object of this
	// class when it is "broken".

	private byte brokenMorale;

	// This variable contains the current status of the derived object of this
	// class. The status is stored as a single character locally. A descriptive
	// string is returned to the calling program if the value of this variable
	// is requested. The purpose of this variable is to describe the current
	// status of the object that it represents. The following status values are
	// recognized:

	// 'N' - Normal
	// 'B' - Broken
	// 'D' - Desperate

	private char status;

	// This variable indicates whether or not it is possible for the derived
	// object of this class to "self rally". This attribute is not visible to
	// the calling program, but is applied during the execution of the rally()
	// method.

	private boolean canSelfRally;

	// Constructors

	// This constructor is used during the instantiation of classes derived from
	// Infantry. The parameters are passed up the chain from the object being
	// created.

	protected Infantry(String description,String nationality,byte movement,
	                   byte morale,byte brokenMorale,boolean canSelfRally)
	{
		// After passing the first 3 parameters to the superclass constructor,
		// check the value of each of the remaining parameters and copy the 
		// value to the local copy of the corresponding variable.

		super(description,nationality,movement);

		if ((morale > 0) && (morale < 11))
		{
			this.morale = morale;
		}

		if ((brokenMorale > 0) && (brokenMorale < 11))
		{
			this.brokenMorale = brokenMorale;
		}

		this.canSelfRally = canSelfRally;

		this.status = 'N'; // Assume all units are unbroken when instantiated.
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Infantry and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		if (canSelfRally)
		{
			System.out.print("Can Self Rally ?: Yes");
		}

		else
		{
			System.out.print("Can Self Rally ?: No");
		}

		System.out.println("\tStatus\t: " + getStatus());

		System.out.println("Morale\t: " + morale + "\tBroken Morale\t: " +
		                   brokenMorale);
	}

	// Public access methods

	// getStatus - A function to return the value of the status member variable
	//             to the calling program.

	public String getStatus()
	{
		switch(status)
		{
			case 'B' : return ("Broken");
			case 'D' : return ("Desperate");
			case 'N' : return ("Normal");
			default  : return ("Unknown");
		}
	}

	// Public game actions

	// rally - A function to execute an attempt to rally (restore the status of
	//         the "unit" from Broken and/or Desperate to Normal). The two
	//         parameters are used to determine if the attempt can be made and
	//         any modifications that may affect the attempt. The function 
	//         returns a boolean value to indicate the success or failure of the
	//         attempt.

	public boolean rally(boolean isLeaderPresent,byte modifier)
	{
		// Verify that the "unit" actually needs to be rallied.

		if ((status == 'B') || (status == 'D'))
		{
			// If the unit is capable of self-rallying (leaders and some elite
			// units) or a unbroken leader is present in the same space, make
			// the rally attempt.

			if ((canSelfRally) || (isLeaderPresent))
			{
				return(true);
			}

			// Otherwise, the "unit" automatically fails.

			else
			{
				return(false);
			}
		}

		return(true);
	}

	// moraleCheck - A function to execute a check on the morale of the "unit".
	//               The parameter is used to modify the attempt. The function
	//               returns a boolean value to indicate the success or failure
	//               of the check.

	public boolean moraleCheck(byte modifier)
	{
		return(true);
	}
}
