// ************************************************************************** //
// Counters.java - This file contains the Counters package, which contains    //
//                 the class definitions and implementations for objects used //
//                 to represent the virtual playing pieces in jASL.           //
//                                                                            //
//                 NOTE: This program is based on Advanced Squad Leader, a    //
//                       product of The Avalon Hill Game Company.             //
//                                                                            //
// Written By    : Craig R. Campbell  -  December 1998                        //
//                                                                            //
// $Header: /tmp/java/jasl.cvs/jasl/source/jasl/counters/Counters.java,v 1.1 1999/07/10 07:00:19 craig Exp $
// ************************************************************************** //

package Counters;

// ************************************************************************** //
// Unit class - This is the top-level class of the Counter package. All       //
//              the classes in this Package are derived from Unit.            //
// ************************************************************************** //

class Unit
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
}

// ************************************************************************** //
// Fighting class - This class is used to distinguish between units that have //
//                  dynamic characteristics (ie. movement, firing, etc). from //
//                  units that are used to modify a space on the "board"      //
//                  (ie. wire, foxholes, etc). Instances of this class may    //
//                  not be instantiated directly. It is strictly a parent     //
//                  class.                                                    //
// ************************************************************************** //

class Fighting extends Unit
{
	// Private data members

	// This variable contains the nationality of the derived object of this
	// class (ie. "American").

	private String nationality;

	// Constructors

	// This constructor is used during the instantiation of classes derived from
	// Fighting. The parameters are passed up the chain from the object being
	// created.

	protected Fighting(String description,String nationality)
	{
		// After passing the first parameter to the superclass constructor,
		// check the value of the remaining parameter and copy the value to
		// the local copy of the corresponding variable.

		super(description);

		if (nationality.length() > 0)
		{
			this.nationality = nationality;
		}
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Fighting and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Nationality\t: " + nationality);
	}

	// Public access methods

	// getNationality - A function to return the value of the nationality member
	//                  variable to the calling program.

	public String getNationality()
	{
		return (nationality);
	}
}

// ************************************************************************** //
// Mobile class - This class is used to distinguish between fighting units    //
//                that have the ability to move on their own (ie. squads,     //
//                leaders, tanks, etc) and those that are carried or towed    //
//                (ie. machine guns, artillery pieces, etc). Instances of     //
//                this class may not be instantiated directly. It is strictly //
//                a parent class.                                             //
// ************************************************************************** //

class Mobile extends Fighting
{
	// Private data members

	// This variable contains the movement allowance of the derived object of
	// this class (ie. "4").

	private byte movement;

	// Constructors

	// This constructor is used during the instantiation of classes derived from
	// Mobile. The parameters are passed up the chain from the object being
	// created.

	protected Mobile(String description,String nationality,byte movement)
	{
		// After passing the first 2 parameters to the superclass constructor,
		// check the value of the remaining parameter and copy the value to
		// the local copy of the corresponding variable.

		super(description,nationality);

		if (movement > 0)
		{
			this.movement = movement;
		}
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Mobile and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("Movement Allowance\t: " + movement);
	}

	// Public access methods

	// getMovementAllowance - A function to return the value of the movement
	//                        member variable to the calling program.

	public byte getMovementAllowance()
	{
		return (movement);
	}
}

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

		System.out.println("Morale\t: " + morale + "\tBroken Morale\t: " +
		                   brokenMorale);

		if (canSelfRally)
		{
			System.out.println("Can Self Rally ?\t: Yes");
		}

		else
		{
			System.out.println("Can Self Rally ?\t: No");
		}

		System.out.println("Status\t: " + getStatus());
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

// ************************************************************************** //
// Personnel class - This class is used to define the characteristics of all  //
//                   infantry "units" other than leaders, those that          //
//                   represent more than one combat soldier. The exception to //
//                   this definition is the Hero class. Instances of this     //
//                   class may not be instantiated directly. It is strictly a //
//                   parent class.                                            //
// ************************************************************************** //

class Personnel extends Infantry
{
	// Private data members

	// This variable contains the firepower available to the derived object of
	// this class (ie. "4").

	private byte firepower;

	// This variable contains the normal range of the derived object of this
	// class (ie. "6").

	private byte normalRange;

	// This variable contains the maximum portage points that the derived object
	// of this class can carry without affecting the number of movement points
	// available to it.

	private byte portageCapacity;

	// This variable contains the current number of portage points of equipment
	// that the derived object of this class is carrying.

	private byte portageLevel;

	// Constructors

	// This constructor is used to instantiate a Personnel object.

	protected Personnel(String description,String nationality,byte movement,byte
	                    morale,byte brokenMorale,boolean canSelfRally,byte
					    firepower,byte normalRange,byte portageCapacity)
	{
		// After passing the first 6 parameters to the superclass constructor,
		// check the values of the remaining 3 parameters and copy the values to
		// the local copy of each corresponding variable.

		super(description,nationality,movement,morale,brokenMorale,
		      canSelfRally);

		if (firepower > 0)
		{
			this.firepower = firepower;
		}

		if (normalRange > 0)
		{
			this.normalRange = normalRange;
		}

		if ((portageCapacity >= 0) && (portageCapacity < 11))
		{
			this.portageCapacity = portageCapacity;
		}

		this.portageLevel = 0; // Assume that weapon possession will be 
		                       // determined after the object is instantiated.
	}

	// Protected access methods

	// showValues - A function to display the value of the private data members
	//              of the current instance. The intent of this function is to
	//              provide text-based verification output for development and
	//              debugging. This function is only accessible to instances of
	//              the subclasses of Personnel and each subclass includes a
	//              function with the same name and purpose.

	protected void showValues()
	{
		// Display data stored in the parent class.

		super.showValues();

		// Display data stored in this class.

		System.out.println("NormalRange\t: " + normalRange + "\tFirepower\t: " +
		                   firepower);

		System.out.println("Portage Capacity\t: " + portageCapacity +
		                   "\tPortageLevel\t: " + portageLevel);
	}

	// Public access methods

	// getFirepower - A function to return the value of the firepower member
	//                variable to the calling program.

	public byte getFirepower()
	{
		return (firepower);
	}

	// getNormalRange - A function to return the value of the normalRange member
	//                  variable to the calling program.

	public byte getNormalRange()
	{
		return (normalRange);
	}
}

// ************************************************************************** //
// Squad class - This class is used to define the unique characteristics of   //
//               squad "units". Instances of this class may be instantiated   //
//               directly.                                                    //
// ************************************************************************** //

class Squad extends Personnel
{
	// Private data members

	// This variable contains the classification of the derived object of this
	// class. The status is stored as a single character locally. A descriptive
	// string is returned to the calling program if the value of this variable
	// is requested. The purpose of this variable is to describe the experience
	// level of the object that it represents and applies only to multi-man
	// units (squads, crews, etc). The following classifications are recognized:

	// 'E' - Elite
	// '1' - 1st Line
	// '2' - 2nd Line
	// 'G' - Green
	// 'C' - Conscript

	private char classification;

	// Constructor

	// This constructor is used to instantiate a Squad object.

	public Squad(String nationality,byte movement,byte morale,byte brokenMorale,
	             boolean canSelfRally,byte firepower,byte normalRange,byte
				 portageCapacity,char classification)
	{
		// After passing the first 8 parameters to the superclass constructor,
		// check the value of the remaining parameter and copy the value to
		// the local copy of the corresponding variable.

		super("Squad",nationality,movement,morale,brokenMorale,
		      canSelfRally,firepower,normalRange,portageCapacity);

		if ((classification == 'E') || (classification == '1') ||
		    (classification == '2') || (classification == 'G') ||
			(classification == 'C'))
		{
			this.classification = classification;
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

		System.out.println("Classification\t: " + getClassification());
	}

	// getClassification - A function to return the value of the classification
	//                     member variable to the calling program. The value is
	//                     stored as a single character, but an associated
	//                     descriptive string is returned.

	public String getClassification()
	{
		switch(classification)
		{
			case 'E' : return ("Elite");
			case '1' : return ("1st Line");
			case '2' : return ("2nd Line");
			case 'G' : return ("Green");
			case 'C' : return ("Conscript");
			default  : return ("Unknown");
		}
	}
}
