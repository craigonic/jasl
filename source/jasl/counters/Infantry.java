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
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Infantry.java,v 1.5 2000/08/13 06:02:07 craig Exp $
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
	// Protected symbolic constants

	// The following symbolic constants are used by this class and its 
	// sub-classes to define morale value limits and the legal values for the
	// status member variable.

	// Morale value limits. These apply to both standard and broken morale.

	protected static final int MIN_MORALE =  0;
	protected static final int MAX_MORALE = 10;
	
	// Legal status values. DEFAULT_STATUS, which is defined in Fighting.java,
	// is also a legal value. Its value is "Unknown". Additional values will be
	// added here as necessary.

	protected static final String BROKEN    = "Broken";
	protected static final String DESPERATE = "Desperate";
	protected static final String NORMAL    = "Normal";
	
	// BPV (Basic Point Value) lower limit.

	protected static final int MIN_BPV = 0;

	// ELR (Experience Level Rating) lower limit.

	protected static final int MIN_ELR = 0;

	// ELR (Experience Level Rating) upper limit.

	protected static final int MAX_ELR = 5;

	// Private symbolic constants

	// This constant is used as part of the error messages (see below) that
	// that are generated when an exception is thrown.

	private static final String CLASS_NAME = "Infantry";

	// Private data members

	// This variable contains the normal morale value of the derived object of
	// this class.

	private int morale;

	// This variable contains the morale value of the derived object of this
	// class when it is "broken".

	private int brokenMorale;

	// This variable contains the current status of the derived object of this
	// class. The legal status values are defined as protected symbolic
	// constants above.

	private String status;

	// This variable indicates whether or not it is possible for the derived
	// object of this class to "self rally". This attribute is not visible to
	// the calling program, but is applied during the execution of the rally()
	// method.

	private boolean canSelfRally;

	// This variable stores the basic point value of the unit that this object
	// represents. This is used in the calculation of Battlefield Integrity and
	// other morale related effects.

	private int basicPointValue;

	// This variable stores the experience level rating of the unit this object
	// represents. This is used in unit substitution and replacement.

	private int experienceLevelRating;

	// This variable is used to indicate that the unit that this object
	// represents is automatically given the maximum ELR on initialization. The
	// flag also affects how the unit is replaced.

	private boolean hasMaxELR;

	// The following string is used as messages for any exceptions that may be
	// generated by bad data being passed to the constructor.

	private static final String invalidArgumentError = 
	    ERROR_MSG_PREFIX + CLASS_NAME + CONSTRUCTOR + INVALID_PARAM_MSG;

	// Constructor

	// This constructor is used during the instantiation of classes derived from
	// Infantry. The parameters are passed up the chain from the object being
	// created.

	protected Infantry(String description,String nationality,String identity,
	                   String unitType,int firepower,int normalRange,
	                   int movement,int portageCapacity,int portageValue,
	                   int morale,int brokenMorale,boolean canSelfRally,
	                   int basicPointValue,int experienceLevelRating,
	                   boolean hasMaxELR)
		throws IllegalArgumentException
	{
		// Pass the first 9 parameters to the superclass constructor.
		// If any exceptions are thrown, assume that they will be caught and
		// handled by the program creating the object.

		super(description,nationality,identity,unitType,firepower,normalRange,
		      movement,portageCapacity,portageValue);

		// Check the value of each remaining parameter and copy the value to
		// the local copy of the corresponding variable if an exception is not
		// found.

		// Morale
	
		if ((morale < MIN_MORALE) || (morale > MAX_MORALE))
		{
			throw new IllegalArgumentException(invalidArgumentError + morale);
		}
			
		this.morale = morale;

		// Broken Morale

		if ((brokenMorale < MIN_MORALE) || (brokenMorale > MAX_MORALE))
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   brokenMorale);
		}
			
		this.brokenMorale = brokenMorale;

		// Self Rally Capability

		this.canSelfRally = canSelfRally;

		// Basic Point Value

		if (basicPointValue < MIN_BPV)
		{
			throw new IllegalArgumentException(invalidArgumentError +
			                                   basicPointValue);
		}
			
		this.basicPointValue = basicPointValue;

		// Maximum ELR flag

		this.hasMaxELR = hasMaxELR;

		// Experience Level Rating
	
		if (hasMaxELR)
		{
			this.experienceLevelRating = MAX_ELR;
		}

		else if ((experienceLevelRating < MIN_ELR) ||
		         (experienceLevelRating > MAX_ELR))
		{
			throw new IllegalArgumentException(invalidArgumentError +
		                                       experienceLevelRating);
		}

		else
		{
			this.experienceLevelRating = experienceLevelRating;
		}

		// Assume all units are unbroken when instantiated.

		this.status = NORMAL; 
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
			System.out.print("Can Self Rally ?        : Yes");
		}

		else
		{
			System.out.print("Can Self Rally ?        : No");
		}

		System.out.println("\tStatus          : " + getStatus() + "\tBPV\t: " +
		                   basicPointValue);

		System.out.println("Morale                  : " + morale +
		                   "\tBroken Morale   : " + brokenMorale);

		if (hasMaxELR)
		{
			System.out.print("Has Maximum ELR ?       : Yes");
		}

		else
		{
			System.out.print("Has Maximum ELR ?       : No");
		}

		System.out.println("\tELR             : " + experienceLevelRating);
	}

	// Public access methods

	// getStatus - A function to return the value of the status member variable
	//             to the calling program.

	public String getStatus()
	{
		return (status);
	}

	// Public game actions

	// rally - A function to execute an attempt to rally (restore the status of
	//         the "unit" from Broken and/or Desperate to Normal). The two
	//         parameters are used to determine if the attempt can be made and
	//         any modifications that may affect the attempt. The function 
	//         returns a boolean value to indicate the success or failure of the
	//         attempt.

	// NOTE: DEFAULT_RESULT has a value of "false" and is defined in
	//       Fighting.java 

	public boolean rally(boolean isLeaderPresent,int modifier)
	{
		// Verify that the "unit" actually needs to be rallied.

		if ((status.equals(BROKEN)) || (status.equals(DESPERATE)))
		{
			// If the unit is capable of self-rallying (leaders and some elite
			// units) or a unbroken leader is present in the same space, make
			// the rally attempt.

			if ((canSelfRally) || (isLeaderPresent))
			{
				return (true);
			}
		}

		// Unless determined otherwise above, the "unit" automatically fails.

		return (DEFAULT_RESULT);
	}

	// moraleCheck - A function to execute a check on the morale of the "unit".
	//               The parameter is used to modify the attempt. The function
	//               returns a boolean value to indicate the success or failure
	//               of the check.

	public boolean moraleCheck(int modifier)
	{
		return (DEFAULT_RESULT);
	}
}
